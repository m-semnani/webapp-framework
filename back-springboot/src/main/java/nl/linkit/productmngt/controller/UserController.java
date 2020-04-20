package nl.linkit.productmngt.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import nl.linkit.productmngt.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.linkit.productmngt.exception.ResourceNotFoundException;
import nl.linkit.productmngt.repository.AppUserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * List all users
	 * @return list of users
	 */
	@GetMapping("/user")
	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Get user by its Id
	 * @param userId id of the user
	 * @return user
	 * @throws ResourceNotFoundException when there is no user with provided userId
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<AppUser> getUserById(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		AppUser user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	/**
	 * Create a new user
	 * @param user object
	 * @return created user enriched by a database id
	 */
	@PostMapping("/user")
	public AppUser createUser(@Valid @RequestBody AppUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	/**
	 * update a user
	 * @param userId id of the user
	 * @param userDetails to be updated
	 * @return updated user
	 * @throws ResourceNotFoundException when there is no user with provided userId
	 */
	@PutMapping("/user/{id}")
	public ResponseEntity<AppUser> updateUser(@PathVariable(value = "id") Long userId,
											   @Valid @RequestBody AppUser userDetails) throws ResourceNotFoundException {
		AppUser user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		user.setEmailId(userDetails.getEmailId());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		user.setUsername(userDetails.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		final AppUser updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Delete a user
	 * @param userId id of the user
	 * @return true if the operation is successful
	 * @throws ResourceNotFoundException when there is no user with provided userId
	 */
	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		AppUser user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
