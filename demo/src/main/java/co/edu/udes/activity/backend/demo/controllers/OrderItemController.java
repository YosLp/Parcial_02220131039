package co.edu.udes.activity.backend.demo.controllers;

import com.example.ordermanagement.dtos.OrderItemDTO;
import com.example.ordermanagement.models.OrderItem;
import com.example.ordermanagement.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDTO> getAllItems() {
        return orderItemService.getAll().stream().map(item -> {
            OrderItemDTO dto = new OrderItemDTO();
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());
            dto.setProductPrice(item.getProduct().getPrice());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderItemDTO getItemById(@PathVariable Long id) {
        OrderItem item = orderItemService.getById(id);
        if (item == null) return null;

        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setProductPrice(item.getProduct().getPrice());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        orderItemService.delete(id);
    }
}
