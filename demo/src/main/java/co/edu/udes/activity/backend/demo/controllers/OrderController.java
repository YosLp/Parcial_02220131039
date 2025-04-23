package com.example.ordermanagement.controllers;

import com.example.ordermanagement.dtos.OrderDTO;
import com.example.ordermanagement.dtos.OrderItemDTO;
import com.example.ordermanagement.models.Order;
import com.example.ordermanagement.models.OrderItem;
import com.example.ordermanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestParam Long customerId) {
        Order order = orderService.createOrder(customerId);
        return toDTO(order);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return toDTO(order);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<OrderDTO> getOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomer(customerId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/{orderId}/add-product")
    public OrderDTO addProductToOrder(@PathVariable Long orderId,
                                      @RequestParam Long productId,
                                      @RequestParam int quantity) {
        Order updated = orderService.addProductToOrder(orderId, productId, quantity);
        return toDTO(updated);
    }

    @PutMapping("/{orderId}/status")
    public OrderDTO updateOrderStatus(@PathVariable Long orderId,
                                      @RequestParam String newStatus) {
        return toDTO(orderService.updateOrderStatus(orderId, newStatus));
    }

    @GetMapping("/{orderId}/total")
    public double calculateOrderTotal(@PathVariable Long orderId) {
        return orderService.calculateOrderTotal(orderId);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/by-status")
    public List<OrderDTO> getOrdersByStatus(@RequestParam String status) {
        return orderService.getOrdersByStatus(status).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/recent")
    public List<OrderDTO> getRecentOrders(@RequestParam int days) {
        return orderService.getRecentOrders(days).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO toDTO(Order order) {
        if (order == null) return null;

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setCustomerId(order.getCustomer().getId());

        List<OrderItemDTO> items = order.getItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setProductPrice(item.getProduct().getPrice());
            itemDTO.setQuantity(item.getQuantity());
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }
}
