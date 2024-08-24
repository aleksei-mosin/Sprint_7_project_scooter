package courier;
import io.qameta.allure.Step;
import org.junit.Before;

public class BaseTest {
    RandomGenerateCourier randomCourier = new RandomGenerateCourier();
     CourierSteps courierSteps;
     CourierPattern courierPattern;
     CourierAsserts courierAsserts;
     CourierLogin courierLogin;
    @Before
    @Step("Создание тестовых данных для курьера")
    public void setUp() {
        courierSteps = new CourierSteps();
        courierPattern = randomCourier.newRandomCourier();
        courierSteps.courierCreate(courierPattern);
        courierLogin = CourierLogin.from(courierPattern);
        courierAsserts = new CourierAsserts();
    }
}
