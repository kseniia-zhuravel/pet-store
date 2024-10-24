package dataProviders;

import org.testng.annotations.DataProvider;

import java.time.temporal.ChronoUnit;

import static org.example.backend.models.store.OrderStatus.*;
import static org.example.framework.FakerUtils.getRandomInt;
import static org.example.framework.TimeUtilities.*;

public class StoreDataProvider {

    @DataProvider(name = "intValidValues")
    public Object[][] intValidValues() {
        return new Object[][]{
                {1}, {getRandomInt()}, {Integer.MAX_VALUE}
        };
    }

    @DataProvider(name = "intInvalidValues")
    public Object[][] intInvalidValues() {
        return new Object[][]{
                {0}, {-1}, {Integer.MIN_VALUE}
        };
    }

    @DataProvider(name = "shipDateValidValues")
    public Object[][] shipDateValidValues() {
        return new Object[][]{
                {currentIso()},
                {pastIso(10, ChronoUnit.HOURS)},
                {pastIso(10, ChronoUnit.DAYS)},
                {pastIso(10, ChronoUnit.YEARS)},
                {futureIso(10, ChronoUnit.HOURS)},
                {futureIso(10, ChronoUnit.DAYS)},
                {futureIso(10, ChronoUnit.YEARS)},
                {""},
                {null}

        };
    }

    @DataProvider(name = "statusValidValues")
    public Object[][] statusValidValues() {
        return new Object[][]{
                {PLACED}, {APPROVED}, {DELIVERED}, {null}
        };
    }

    @DataProvider(name = "completeValidValues")
    public Object[][] completeValidValues() {
        return new Object[][]{
                {true}, {false}
        };
    }

}
