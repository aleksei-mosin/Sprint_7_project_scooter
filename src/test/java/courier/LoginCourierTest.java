package courier;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTest {
    private final RandomGenerateCourier randomCourier = new RandomGenerateCourier();
    private int courierId;
    private  CourierAsserts courierAsserts;
    private CourierLogin courierLogin;
    private CourierSteps courierSteps;
    private CourierPattern courierPattern;

    @Before
    @Step("Создание тестовых данных для курьера")
    public void setUp() {
        courierSteps = new CourierSteps();
        courierPattern = randomCourier.newRandomCourier();
        courierSteps.courierCreate(courierPattern);
        courierLogin = CourierLogin.from(courierPattern);
        courierAsserts = new CourierAsserts();
    }

    @Test
    @DisplayName("Успешная авторизация курьера")
    @Description("Курьер успешно входит в систему")
    public void courierLoginValid() {
        ValidatableResponse responseLogin = courierSteps.courierLogin(courierLogin);
        courierAsserts.loginCourier(responseLogin);
        courierId = responseLogin.extract().path("id");
        responseLogin.assertThat().statusCode(200);
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Курьер не может войти в систему без логина")
    public void courierLoginErrorEmptyLogin() {
        CourierLogin courierWithoutLogin = new CourierLogin("", courierPattern.getPassword());
        ValidatableResponse responseLoginErrorMessage = courierSteps.courierLogin(courierWithoutLogin);
        courierAsserts.courierLoginWithoutData(responseLoginErrorMessage);
        responseLoginErrorMessage.assertThat().statusCode(400);

    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Курьер не может войти в систему без пароля")
    public void courierLoginErrorEmptyPassword() {
        CourierLogin courierWithoutPass = new CourierLogin(courierPattern.getLogin(), "");
        ValidatableResponse responseLoginErrorMessage = courierSteps.courierLogin(courierWithoutPass);
        courierAsserts.courierLoginWithoutData(responseLoginErrorMessage);
        responseLoginErrorMessage.assertThat().statusCode(400);
    }

    @Test
    @DisplayName("Авторизация курьера, с данными по которым не было регистрации")
    @Description("Курьер не может войти в систему с не зарегистрированным логином")
    public void courierLoginErrorAccountNotFound() {
        CourierLogin courierAccountNotFound = new CourierLogin(RandomGenerateCourier.NEW_LOGIN_FAKED, courierPattern.getPassword());
        ValidatableResponse responseLoginErrorMessage = courierSteps.courierLogin(courierAccountNotFound);
        courierAsserts.courierLoginWithIncorrectData(responseLoginErrorMessage);
        responseLoginErrorMessage.assertThat().statusCode(404);

    }
}
