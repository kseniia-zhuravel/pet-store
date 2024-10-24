package org.example.backend.models.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private Long id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private OrderStatus status;
    private Boolean complete;

}
