package nl.linkit.productmngt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.linkit.productmngt.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    AppUser findByUsername(String username);
}
