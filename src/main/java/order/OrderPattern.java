package order;

import java.util.List;

public class OrderPattern {
    private String firstName, lastName, address, metroStation, phone, rental, rentalPeriod, comment;
    private List<String> colour;

    public OrderPattern( List<String> colour) {
        this.firstName = "Яндекс";
        this.lastName = "Практикум";
        this.address = "Москва, улица Льва Толстого, дом 16";
        this.metroStation = "Парк культуры";
        this.phone = "+79993414433";
        this.rental = "10.09.2024";
        this.rentalPeriod = "7";
        this.comment = "Тестовый комментарий для курьера";
        this.colour = colour;
    }
}
