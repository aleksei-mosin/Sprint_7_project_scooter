package courier;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

 public class CreateCourierTest {
     protected final RandomGenerateCourier randomCourier = new RandomGenerateCourier();
     int courierId;
     private CourierSteps courierSteps;
     private CourierPattern courierPattern;
     private CourierAsserts courierAsserts;
     @Before
     @Step("Создание курьера для тестов")
     public void setUp() {
         courierSteps = new CourierSteps();
         courierPattern = randomCourier.newRandomCourier();
         courierAsserts = new CourierAsserts();
     }
     @Test
     @DisplayName("Создание курьера")
     @Description("Курьер создается успешно, курьера с схожими данными нет")
     public void createValidCourier() {
         ValidatableResponse responseCreateCourier = courierSteps.courierCreate(courierPattern);
         CourierLogin courierCred = CourierLogin.from(courierPattern);
         courierId = courierSteps.courierLogin(courierCred).extract().path("id");
         courierAsserts.courierCreate(responseCreateCourier);
     }
     @Test
     @DisplayName("Пустой логин при создании курьера")
     @Description("Создание курьера без логина")
     public void createCourierWithoutLogin() {
         courierPattern.setLogin("");
         ValidatableResponse responseLoginNull = courierSteps.courierCreate(courierPattern);
         courierAsserts.courierCreateError(responseLoginNull);

     }
     @Test
     @DisplayName("Пустой пароль при создании курьера")
     @Description("Создание курьера без пароля")
     public void createCourierWithoutPassword() {
         courierPattern.setPassword("");
         ValidatableResponse responsePasswordNull = courierSteps.courierCreate(courierPattern);
         courierAsserts.courierCreateError(responsePasswordNull);
     }
     @Test
     @DisplayName("Курьер создается с идентичными логином и паролем предыдущего курьера")
     @Description("Создание курьера с тем же логином")
     public void createDuplicateCourier() {
         courierSteps.courierCreate(courierPattern);
         ValidatableResponse responseCreateDuplicateCourier = courierSteps.courierCreate(courierPattern);
         courierAsserts.courierCreateLoginDuplicat(responseCreateDuplicateCourier);
     }
 }
