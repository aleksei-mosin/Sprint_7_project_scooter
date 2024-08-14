package order;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;

public class BaseOrderTest {
    public OrderSteps orderSteps;
    public int track;
    @Before
    public void setUp() {
        orderSteps = new OrderSteps();
    }
    @After
    @Step("Отменить заказ")
    public void cancelOrder() {
        orderSteps.cancelOrder(track);
    }
}
