package org.example.backend.factories.store;

import org.example.backend.models.store.Order;
import org.example.backend.models.store.OrderStatus;

import static org.example.backend.models.store.OrderStatus.PLACED;
import static org.example.framework.FakerUtils.getRandomInt;
import static org.example.framework.TimeUtilities.currentIso;

public class OrderFactory {

    public static Order order(long id, int petId, int quantity, String shipDate, OrderStatus status, boolean complete) {
        return Order.builder()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .shipDate(shipDate)
                .status(status)
                .complete(complete)
                .build();
    }

    public static Order randomOrder() {
        return Order.builder()
                .id((long) getRandomInt())
                .petId(getRandomInt())
                .quantity(getRandomInt())
                .shipDate(currentIso())
                .status(PLACED)
                .complete(true)
                .build();
    }

}
