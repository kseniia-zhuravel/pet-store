package store;

import org.testng.annotations.AfterMethod;

import static org.example.backend.facades.StoreFacade.deleteOrderWithCheck;
import static org.example.backend.services.StoreService.orderId;

public class BaseStoreTest {

    @AfterMethod()
    public void deleteOrderAfterTest() {
        if (orderId.get() != null) {
            deleteOrderWithCheck(orderId.get());
            orderId.remove();
        }
    }

}
