package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class ListOrderTest {
    @Test
    @DisplayName("Успешное получение списка заказов")
    @Description("Список заказов успешно отображется и отдается код 200")
    public void getOrderList() {
        OrderSteps partOrder = new OrderSteps();
        ValidatableResponse responseOrderList = partOrder.getOrderList();
        responseOrderList.assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
