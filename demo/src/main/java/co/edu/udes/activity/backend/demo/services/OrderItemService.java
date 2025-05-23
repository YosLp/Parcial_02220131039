package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.OrderItem;
import co.edu.udes.activity.backend.demo.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem getById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}

