package courier;

import constants.Endpoints;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class CourierSteps {
    public static RequestSpecification getBaseSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Endpoints.MAIN_URL);
    }
    @Step("Регистрация нового курьера")
    public ValidatableResponse courierCreate(CourierPattern courierPattern) {
        return getBaseSpec()
                .body(courierPattern)
                .when()
                .post(Endpoints.CREATE_COURIER)
                .then();
    }
    @Step("Авторизация курьера")
    public ValidatableResponse courierLogin(CourierLogin courier) {
        return getBaseSpec()
                .body(courier)
                .when()
                .post(Endpoints.LOGIN_COURIER)
                .then();
    }
}
