package co.edu.udes.activity.backend.demo.repositories;

import com.example.ordermanagement.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
