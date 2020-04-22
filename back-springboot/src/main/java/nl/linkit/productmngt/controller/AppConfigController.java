package nl.linkit.productmngt.controller;

import java.util.List;

import javax.validation.Valid;
import nl.linkit.productmngt.model.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.linkit.productmngt.exception.ResourceNotFoundException;
import nl.linkit.productmngt.repository.AppConfigRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AppConfigController {

	private static final Logger logger = LogManager.getLogger(AppConfigController.class);

	@Autowired
	private AppConfigRepository configRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * List all configs
	 * @return list of configs
	 */
	@GetMapping("/config")
	public List<AppConfig> getAllConfigs() {
		List<AppConfig> configs = configRepository.findAll();

		logger.debug("{} Configs fetched from DB.", configs.size());
		return configs;
	}

	/**
	 * Get config by its Id
	 * @param configId id of the config
	 * @return config
	 * @throws ResourceNotFoundException when there is no config with provided configId
	 */
	@GetMapping("/config/{id}")
	public ResponseEntity<AppConfig> getConfigById(@PathVariable(value = "id") Long configId)
			throws ResourceNotFoundException {
		AppConfig config = configRepository.findById(configId)
				.orElseThrow(() -> new ResourceNotFoundException("Config not found for this id :: " + configId));

		logger.debug("getConfigById({}) found: {}", configId, config);
		return ResponseEntity.ok().body(config);
	}

	/**
	 * update a config
	 * @param configId id of the config
	 * @param configDetails to be updated
	 * @return updated config
	 * @throws ResourceNotFoundException when there is no config with provided configId
	 */
	@PutMapping("/config/{id}")
	public ResponseEntity<AppConfig> updateConfig(@PathVariable(value = "id") Long configId,
											  @Valid @RequestBody AppConfig configDetails) throws ResourceNotFoundException {
		AppConfig config = configRepository.findById(configId)
				.orElseThrow(() -> new ResourceNotFoundException("Config not found for this id :: " + configId));

		// Just change value, but not name!
		config.setValue(configDetails.getValue());
		final AppConfig updatedConfig = configRepository.save(config);

		logger.debug("Config updated successfully: {}", updatedConfig);
		return ResponseEntity.ok(updatedConfig);
	}

}
