package co.edu.udes.activity.backend.demo.repositories;

import com.example.ordermanagement.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
