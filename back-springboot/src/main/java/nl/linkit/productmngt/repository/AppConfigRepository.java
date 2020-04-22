package nl.linkit.productmngt.repository;

import nl.linkit.productmngt.model.AppConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppConfigRepository extends JpaRepository<AppConfig, Long>{
    AppConfig findByName(String name);
}
