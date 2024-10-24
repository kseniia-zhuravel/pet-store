package store;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.backend.services.StoreService.getStoreInventory;

public class StoreInventoryTest {

    @Test
    @Severity(NORMAL)
    @Description("Check getting store inventory details")
    public void checkGettingStoreInventoryDetails() {
        Response response = getStoreInventory();
        assertThat(response.statusCode()).isEqualTo(200);
    }

}
