package org.example.backend.customAssertions;

import org.example.backend.models.store.Order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.framework.TimeUtilities.getUtcTimeFrom;

public class OrderAssertion {

    public static void assertOrdersAreEqual(Order actualOrder, Order expectedOrder) {
        assertThat(actualOrder)
                .usingRecursiveComparison()
                .ignoringFields("shipDate")
                .isEqualTo(expectedOrder);

        if (expectedOrder.getShipDate() == null || expectedOrder.getShipDate().isEmpty()) {
            assertThat(actualOrder.getShipDate()).isNull();
        } else {
            assertThat(actualOrder.getShipDate()).isEqualTo(getUtcTimeFrom(expectedOrder.getShipDate()));
        }
    }

    public static void assertOrdersAreNotEqual(Order actualOrder, Order expectedOrder) {
        assertThat(actualOrder)
                .usingRecursiveComparison()
                .ignoringFields("shipDate")
                .isNotEqualTo(expectedOrder);
    }

}
