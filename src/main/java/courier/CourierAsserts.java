package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class CourierAsserts {
    @Step("Регистрация курьера с валидными данными")
    public void courierCreate(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }

    @Step("Ответ если не хватает данных")
    public void courierCreateError(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Ответ если пользователь уже существует")
    public void courierCreateLoginDuplicat(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Проверка Id при завершении регистрации")
    public int loginCourier(ValidatableResponse response) {
        return response.assertThat()
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }

    @Step("Ответ при нехватки данных для входа")
    public void courierLoginWithoutData(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Ответ при невалидных данных авторизации")
    public void courierLoginWithIncorrectData(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
    @Step("Успешный вход пользователя")
    public void courierValidLogin(ValidatableResponse response){
        response.assertThat()
                .statusCode(200);
    }
}
