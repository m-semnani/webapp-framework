package nl.linkit.productmngt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.linkit.productmngt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
