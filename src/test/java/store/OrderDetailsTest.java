package store;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.example.backend.models.common.Error;
import org.example.backend.models.store.Order;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.backend.customAssertions.OrderAssertion.assertOrdersAreEqual;
import static org.example.backend.factories.common.ErrorFactory.error404;
import static org.example.backend.factories.common.ErrorFactory.error404WithTypeUnknown;
import static org.example.backend.factories.store.OrderFactory.randomOrder;
import static org.example.backend.models.common.ErrorMessage.NUMBER_FORMAT_EXCEPTION_ERROR;
import static org.example.backend.models.common.ErrorMessage.ORDER_NOT_FOUND_ERROR;
import static org.example.backend.services.StoreService.*;
import static org.example.framework.Utilities.extractResponseAs;

public class OrderDetailsTest {

    @Test
    @Severity(CRITICAL)
    @Description("Check order details are equal to expected order")
    public void checkOrderDetailsAreEqualToExpectedOrder() {
        // Precondition
        Order expectedOrder = randomOrder();
        Response response = placeOrderAndReturnResponse(expectedOrder);
        assertThat(response.statusCode()).isEqualTo(200);

        // Action
        Order actualOrder = getOrderDetails(expectedOrder.getId());

        // Assert
        assertOrdersAreEqual(actualOrder, expectedOrder);
    }

    @Test
    @Severity(MINOR)
    @Description("Check order details for not existing order")
    public void checkOrderDetailsForNotExistingOrder() {
        Response response = getOrderDetailsAsResponse(-1);
        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(extractResponseAs(response, Error.class)).isEqualTo(error404(ORDER_NOT_FOUND_ERROR));
    }

    @Test
    @Severity(TRIVIAL)
    @Description("Check order details for not valid order ID")
    public void checkOrderDetailsForNotValidOrderId() {
        Response response = getOrderDetailsAsResponse(null);
        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(extractResponseAs(response, Error.class)).
                isEqualTo(error404WithTypeUnknown(NUMBER_FORMAT_EXCEPTION_ERROR));
    }

    @Test
    @Severity(MINOR)
    @Description("Check order details for deleted order")
    public void checkOrderDetailsForDeletedOrder() {
        // Precondition
        Order order = randomOrder();
        Response response = placeOrderAndReturnResponse(order);
        assertThat(response.statusCode()).isEqualTo(200);

        response = deleteOrder(order.getId());
        assertThat(response.statusCode()).isEqualTo(200);

        // Action
        response = getOrderDetailsAsResponse(order.getId());

        // Assert
        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(extractResponseAs(response, Error.class)).isEqualTo(error404(ORDER_NOT_FOUND_ERROR));
    }

}
