package co.edu.udes.activity.backend.demo.services;

import co.edu.udes.activity.backend.demo.models.Customer;
import co.edu.udes.activity.backend.demo.models.Product;
import co.edu.udes.activity.backend.demo.models.Order;
import co.edu.udes.activity.backend.demo.models.OrderItem;
import co.edu.udes.activity.backend.demo.repositories.CustomerRepository;
import co.edu.udes.activity.backend.demo.repositories.ProductRepository;
import co.edu.udes.activity.backend.demo.repositories.OrderItemRepository;
import co.edu.udes.activity.backend.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order createOrder(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return null;

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order addProductToOrder(Long orderId, Long productId, int quantity) {
        Order order = getOrderById(orderId);
        Product product = productRepository.findById(productId).orElse(null);
        if (order == null || product == null) return null;

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        orderItemRepository.save(item);

        return getOrderById(orderId);
    }

    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            return orderRepository.save(order);
        }
        return null;
    }

    public double calculateOrderTotal(Long orderId) {
        Order order = getOrderById(orderId);
        if (order == null) return 0;

        return order.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getRecentOrders(int days) {
        return orderRepository.findAll().stream()
                .filter(o -> o.getOrderDate().isAfter(LocalDateTime.now().minusDays(days)))
                .toList();
    }
}

