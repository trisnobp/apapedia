package com.apapedia.order.dto.request;

import java.util.List;
import java.util.UUID;
import com.apapedia.order.model.OrderItem;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateOrderRequestDTO {
    private UUID seller;
    private UUID customer;
    private List<OrderItemDTO> listOrderItem;
}
