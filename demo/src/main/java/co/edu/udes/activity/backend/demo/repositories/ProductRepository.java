package co.edu.udes.activity.backend.demo.repositories;

import com.example.ordermanagement.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
