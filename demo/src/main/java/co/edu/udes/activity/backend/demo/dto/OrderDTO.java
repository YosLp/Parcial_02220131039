package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private Long customerId;
    private List<OrderItemDTO> items;
}
