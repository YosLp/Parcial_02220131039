package co.edu.udes.activity.backend.demo.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private double productPrice;
    private int quantity;
}
