package courier;

public class CourierLogin {
    private String login,password;

    public CourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierLogin from(CourierPattern courierPattern) {
        return new CourierLogin(courierPattern.getLogin(), courierPattern.getPassword());
    }

}
