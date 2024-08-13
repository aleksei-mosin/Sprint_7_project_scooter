package order;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class ParametrizedTest {
    private final List<String> colour;
    private int track;
    private OrderSteps orderSteps;

    public ParametrizedTest(List<String> colour) {
        this.colour = colour;
    }
    @Parameterized.Parameters(name = "Цвет самоката: {0}")
    public static Object[][] getScooterColour() {
        return new Object[][]{
                {List.of("GRAY")},
                {List.of("BLACK")},
                {List.of("GRAY, BLACK")},
                {List.of("")}
        };
    }
    @Before
    public void setUp() {
        orderSteps = new OrderSteps();
    }

    @Test
    @DisplayName("Создание заказа с самокатами всех цветов")
    @Description("Корретное создание заказа с самокатами разных цветов")
    public void OrderingWithScootersInDifferentColors() {
        OrderPattern orderPattern = new OrderPattern(colour);
        ValidatableResponse responseOrder = orderSteps.createNewOrder(orderPattern);
        track = responseOrder.extract().path("track");
        responseOrder.assertThat()
                .statusCode(201)
                .body("track", is(notNullValue()));
    }
    @After
    @Step("Отменить заказ")
    public void cancelOrder() {
        orderSteps.cancelOrder(track);
    }
}
