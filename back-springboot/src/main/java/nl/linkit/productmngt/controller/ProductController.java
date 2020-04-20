package nl.linkit.productmngt.controller;

import nl.linkit.productmngt.exception.ResourceNotFoundException;
import nl.linkit.productmngt.model.Product;
import nl.linkit.productmngt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	/**
	 * List all products
	 * @return list of products
	 */
	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
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
		return ResponseEntity.ok().body(product);
	}

	/**
	 * Create a new product
	 * @param product object
	 * @return created product enriched by a database id
	 */
	@PostMapping("/product")
	public Product createProduct(@Valid @RequestBody Product product) {
		return productRepository.save(product);
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

		product.setName(productDetails.getName());
		product.setQuantity(productDetails.getQuantity());
		final Product updatedProduct = productRepository.save(product);
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

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * List of lack products
	 * @return list of lack products
	 */
	@GetMapping("/product/lack")
	public List<Product> getLackProducts() {
		return productRepository.findByQuantityLessThan(10);
	}

	/**
	 * List of excess products
	 * @return list of excess products
	 */
	@GetMapping("/product/excess")
	public List<Product> getExcessProducts() {
		return productRepository.findByQuantityGreaterThan(20);
	}

}
