package courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class LoginCourierTest extends BaseTest {
    private int courierId;
    @Test
    @DisplayName("Успешная авторизация курьера")
    @Description("Курьер успешно входит в систему")
    public void courierLoginValid() {
        ValidatableResponse responseLogin = courierSteps.courierLogin(courierLogin);
        courierAsserts.courierValidLogin(responseLogin);
        courierId = responseLogin.extract().path("id");
    }
    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Курьер не может войти в систему без логина")
    public void courierLoginErrorEmptyLogin() {
        CourierLogin courierWithoutLogin = new CourierLogin("", courierPattern.getPassword());
        ValidatableResponse responseLoginErrorMessage = courierSteps.courierLogin(courierWithoutLogin);
        courierAsserts.courierLoginWithoutData(responseLoginErrorMessage);

    }
    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Курьер не может войти в систему без пароля")
    public void courierLoginErrorEmptyPassword() {
        CourierLogin courierWithoutPass = new CourierLogin(courierPattern.getLogin(), "");
        ValidatableResponse responseLoginErrorMessage = courierSteps.courierLogin(courierWithoutPass);
        courierAsserts.courierLoginWithoutData(responseLoginErrorMessage);
    }
    @Test
    @DisplayName("Авторизация курьера, с данными по которым не было регистрации")
    @Description("Курьер не может войти в систему с не зарегистрированным логином")
    public void courierLoginErrorAccountNotFound() {
        CourierLogin courierAccountNotFound = new CourierLogin(RandomGenerateCourier.NEW_LOGIN_FAKED, courierPattern.getPassword());
        ValidatableResponse responseLoginErrorMessage = courierSteps.courierLogin(courierAccountNotFound);
        courierAsserts.courierLoginWithIncorrectData(responseLoginErrorMessage);
    }
}
