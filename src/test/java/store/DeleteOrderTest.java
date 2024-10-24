package store;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.example.backend.models.common.Error;
import org.example.backend.models.store.Order;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.backend.factories.common.ErrorFactory.error404WithTypeUnknown;
import static org.example.backend.factories.store.OrderFactory.randomOrder;
import static org.example.backend.models.common.ErrorMessage.ORDER_NOT_FOUND_FOR_DELETE_ERROR;
import static org.example.backend.services.StoreService.deleteOrder;
import static org.example.backend.services.StoreService.placeOrderAndReturnResponse;
import static org.example.framework.Utilities.extractResponseAs;

public class DeleteOrderTest {

    @Test
    @Description("Check order can be deleted")
    public void checkOrderCanBeDeleted() {
        // Precondition
        Order order = randomOrder();
        Response response = placeOrderAndReturnResponse(order);
        assertThat(response.statusCode()).isEqualTo(200);

        // Action
        response = deleteOrder(order.getId());

        // Assert
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @Description("Check deleting not existing order")
    public void checkDeletingNotExistingOrder() {
        Response response = deleteOrder(-1);
        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(extractResponseAs(response, Error.class))
                .isEqualTo(error404WithTypeUnknown(ORDER_NOT_FOUND_FOR_DELETE_ERROR));
    }

}
