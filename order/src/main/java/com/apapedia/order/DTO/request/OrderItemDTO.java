package com.apapedia.order.dto.request;
import com.apapedia.order.model.Order;
import com.apapedia.order.model.OrderItemId;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class OrderItemDTO {
    private UUID productId;
    private Integer quantity;
    private String productName;
    private Long productPrice;
}