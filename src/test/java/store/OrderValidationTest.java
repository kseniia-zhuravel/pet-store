package store;

import dataProviders.StoreDataProvider;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.example.backend.models.common.Error;
import org.example.backend.models.store.Order;
import org.example.backend.models.store.OrderStatus;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.backend.customAssertions.OrderAssertion.assertOrdersAreEqual;
import static org.example.backend.customAssertions.OrderAssertion.assertOrdersAreNotEqual;
import static org.example.backend.factories.common.ErrorFactory.error500;
import static org.example.backend.factories.store.OrderFactory.order;
import static org.example.backend.models.store.OrderStatus.PLACED;
import static org.example.backend.services.StoreService.placeOrder;
import static org.example.backend.services.StoreService.placeOrderAndReturnResponse;
import static org.example.framework.FakerUtils.getRandomInt;
import static org.example.framework.FakerUtils.getRandomWord;
import static org.example.framework.TimeUtilities.currentIso;
import static org.example.framework.Utilities.extractResponseAs;

public class OrderValidationTest extends BaseStoreTest {

    @Test(
            dataProvider = "intValidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the ID field validation with valid values")
    public void checkIdFieldValidationWithValidValues(int id) {
        Order expectedOrder = order(id, getRandomInt(), getRandomInt(), currentIso(), PLACED, true);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreEqual(actualOrder, expectedOrder);
    }

    @Test(
            dataProvider = "intInvalidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the ID field validation with invalid values")
    public void checkIdFieldValidationWithInvalidValues(int id) {
        Order expectedOrder = order(id, getRandomInt(), getRandomInt(), currentIso(), PLACED, true);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreNotEqual(actualOrder, expectedOrder);
        // Note: it's better to add field validation on BE and assert response error than "not equal"
    }

    @Test(
            dataProvider = "intValidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the Pet ID field validation with valid values")
    public void checkPetIdFieldValidationWithValidValues(int id) {
        Order expectedOrder = order(getRandomInt(), id, getRandomInt(), currentIso(), PLACED, true);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreEqual(actualOrder, expectedOrder);
    }

    @Test(
            dataProvider = "intInvalidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the Pet ID field validation with invalid values")
    public void checkPetIdFieldValidationWithInvalidValues(int id) {
        Order expectedOrder = order(getRandomInt(), id, getRandomInt(), currentIso(), PLACED, true);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreEqual(actualOrder, expectedOrder);
        // Note: it's better to add field validation on BE and assert response error than "equal"
    }

    @Test(
            dataProvider = "intValidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the quantity field validation with valid values")
    public void checkQuantityFieldValidationWithValidValues(int quantity) {
        Order expectedOrder = order(getRandomInt(), getRandomInt(), quantity, currentIso(), PLACED, true);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreEqual(actualOrder, expectedOrder);
    }

    @Test(
            dataProvider = "intInvalidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the quantity field validation with invalid values")
    public void checkQuantityFieldValidationWithInvalidValues(int quantity) {
        Order expectedOrder = order(getRandomInt(), getRandomInt(), quantity, currentIso(), PLACED, true);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreEqual(actualOrder, expectedOrder);
        // Note: it's better to add field validation on BE and assert response error than "equal"
    }

    @Test(
            dataProvider = "shipDateValidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the ship date field validation with valid values")
    public void checkShipDateFieldValidationWithValidValues(String shipDate) {
        Order expectedOrder = order(getRandomInt(), getRandomInt(), getRandomInt(), shipDate, PLACED, true);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreEqual(actualOrder, expectedOrder);
    }

    @Test
    @Description("Check the ship date field validation with invalid values")
    public void checkShipDateFieldValidationWithInvalidValues() {
        Order expectedOrder = order(getRandomInt(), getRandomInt(), getRandomInt(), getRandomWord(), PLACED, true);
        Response response = placeOrderAndReturnResponse(expectedOrder);
        assertThat(response.statusCode()).isEqualTo(500);
        assertThat(extractResponseAs(response, Error.class)).isEqualTo(error500());
    }

    @Test(
            dataProvider = "statusValidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the status field validation with valid values")
    public void checkStatusFieldValidationWithValidValues(OrderStatus status) {
        Order expectedOrder = order(getRandomInt(), getRandomInt(), getRandomInt(), currentIso(), status, true);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreEqual(actualOrder, expectedOrder);
    }

    @Test(
            dataProvider = "completeValidValues",
            dataProviderClass = StoreDataProvider.class
    )
    @Description("Check the status field validation with valid values")
    public void checkCompleteFieldValidationWithValidValues(boolean complete) {
        Order expectedOrder = order(getRandomInt(), getRandomInt(), getRandomInt(), currentIso(), PLACED, complete);
        Order actualOrder = placeOrder(expectedOrder);
        assertOrdersAreEqual(actualOrder, expectedOrder);
    }

}
