package org.example.backend.services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.backend.models.store.Order;

import static org.example.backend.services.ApiService.*;

public class StoreService {

    private static final String STORE_INVENTORY_PATH = "/store/inventory";
    private static final String STORE_ORDER_PATH = "/store/order";
    private static final String STORE_ORDER_ORDER_ID_PATH = "/store/order/%s";

    public static final ThreadLocal<Long> orderId = ThreadLocal.withInitial(() -> null);

    @Step
    public static Response placeOrderAndReturnResponse(Order order) {
        return sendPostRequest(STORE_ORDER_PATH, order);
    }

    @Step
    public static Order placeOrder(Order order) {
       Order actualOrder = sendPostRequest(STORE_ORDER_PATH, order, Order.class);
        orderId.set(actualOrder.getId());
        return actualOrder;
    }

    @Step
    public static Response deleteOrder(long id) {
        return sendDeleteRequest(String.format(STORE_ORDER_ORDER_ID_PATH, id));
    }

    @Step
    public static Order getOrderDetails(long id) {
        return sendGetRequest(String.format(STORE_ORDER_ORDER_ID_PATH, id), Order.class);
    }

    @Step
    public static <T> Response getOrderDetailsAsResponse(T id) {
        return sendGetRequest(String.format(STORE_ORDER_ORDER_ID_PATH, id));
    }

    @Step
    public static Response getStoreInventory() {
        return sendGetRequest(STORE_INVENTORY_PATH);
    }

}
