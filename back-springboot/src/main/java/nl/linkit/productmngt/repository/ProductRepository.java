package nl.linkit.productmngt.repository;

import nl.linkit.productmngt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByQuantityLessThan(int lack);
    List<Product> findByQuantityGreaterThan(int excess);
}
