package org.example.backend.facades;

import io.restassured.response.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.backend.services.StoreService.deleteOrder;

public class StoreFacade {

    public static void deleteOrderWithCheck(long id) {
        Response response = deleteOrder(id);
        assertThat(response.statusCode()).isEqualTo(200);
    }

}
