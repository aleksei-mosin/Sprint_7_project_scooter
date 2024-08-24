package order;


import constants.Endpoints;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    public static RequestSpecification requestSpec() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Endpoints.MAIN_URL);
    }
    @Step("Создание заказа")
    public ValidatableResponse createNewOrder(OrderPattern orderPattern) {
        return requestSpec()
                .body(orderPattern)
                .when()
                .post(Endpoints.CREATE_ORDERS)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return requestSpec()
                .when()
                .get(Endpoints.LIST_ORDERS)
                .then();
    }
    @Step("Отмена заказа")
    public ValidatableResponse cancelOrder(int track) {
        return requestSpec()
                .body(track)
                .when()
                .put(Endpoints.ORDER_PUT_CANCEL)
                .then();
    }

}
