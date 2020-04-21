package nl.linkit.productmngt.controller;

import nl.linkit.productmngt.exception.ResourceNotFoundException;
import nl.linkit.productmngt.model.AppUser;
import nl.linkit.productmngt.model.AuthorityType;
import nl.linkit.productmngt.model.Product;
import nl.linkit.productmngt.repository.AppUserRepository;
import nl.linkit.productmngt.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private AppUserRepository appUserRepository;

	/**
	 * List all products
	 * @return list of products
	 */
	@GetMapping("/product")
	public List<Product> getAllProducts() {

		List<Product> products;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(AuthorityType.ADMIN_ROLE.name())))
			products = productRepository.findAll();
		else {
			AppUser user = appUserRepository.findByUsername(authentication.getName());
			products = productRepository.findByAppUser(user);
		}

		logger.debug("{} Products fetched from DB.", products.size());
		return products;
	}

	/**
	 * Get product by its Id
	 * @param productId id of the product
	 * @return product
	 * @throws ResourceNotFoundException when there is no product with provided productId
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		checkOwnership(product);

		logger.debug("getProductById({}) found: {}", productId, product);
		return ResponseEntity.ok().body(product);
	}

	private void checkOwnership(Product product) throws ResourceNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority(AuthorityType.ADMIN_ROLE.name()))){
			if(!product.getAppUser().getUsername().equalsIgnoreCase(authentication.getName()))
				throw new ResourceNotFoundException("Access denied!");
		}
	}

	/**
	 * Create a new product
	 * @param product object
	 * @return created product enriched by a database id
	 */
	@PostMapping("/product")
	public Product createProduct(@Valid @RequestBody Product product) throws OperationNotSupportedException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(AuthorityType.ADMIN_ROLE.name())))
			throw new OperationNotSupportedException("Admin can not create a product yet ;)");

		AppUser user = appUserRepository.findByUsername(authentication.getName());
		product.setAppUser(user);
		@Valid Product saved = productRepository.save(product);

		logger.debug("Product created successfully: {}", saved);
		return saved;
	}

	/**
	 * update a product
	 * @param productId id of the product
	 * @param productDetails to be updated
	 * @return updated product
	 * @throws ResourceNotFoundException when there is no product with provided productId
	 */
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
											   @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		checkOwnership(product);

		product.setName(productDetails.getName());
		product.setQuantity(productDetails.getQuantity());
		final Product updatedProduct = productRepository.save(product);

		logger.debug("Product updated successfully: {}", updatedProduct);
		return ResponseEntity.ok(updatedProduct);
	}

	/**
	 * Delete a product
	 * @param productId id of the product
	 * @return true if the operation is successful
	 * @throws ResourceNotFoundException when there is no product with provided productId
	 */
	@DeleteMapping("/product/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId)
			throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		checkOwnership(product);

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		logger.debug("Product with id {} deleted successfully", productId);
		return response;
	}

	/**
	 * List of lack products
	 * @return list of lack products
	 */
	@GetMapping("/product/lack")
	public List<Product> getLackProducts() {
		List<Product> products =  getAllProducts().stream()
				.filter(product -> product.getQuantity() < 10)
				.collect(Collectors.toList());

		logger.debug("{} Lack Products fetched from DB.", products.size());
		return products;
	}

	/**
	 * List of excess products
	 * @return list of excess products
	 */
	@GetMapping("/product/excess")
	public List<Product> getExcessProducts() {
		List<Product> products = getAllProducts().stream()
				.filter(product -> product.getQuantity() > 20)
				.collect(Collectors.toList());

		logger.debug("{} Excess Products fetched from DB.", products.size());
		return products;
	}

}
