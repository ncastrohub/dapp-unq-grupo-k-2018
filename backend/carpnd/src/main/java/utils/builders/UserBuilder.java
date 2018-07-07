package utils.builders;

import api.forms.UserForm;
import model.*;

public class UserBuilder {

    private String name;
    private String lastName;
    private String cuil;
    private String email;

    public UserBuilder withName(String aName) {
        this.name = aName;
        return this;
    }

    public static User some(){
        User user = new User();
        user.availableMoney = new MoneyAndAmount(12.00, CustomCurrencies.ARS);
        user.email = "nachito@gmail.com";
        user.cuil = "121231232";
        user.lastName = "Castro";
        user.name = "Nazareno";
        user.addVehicle(VehicleBuilder.start().withPhoto("asdasd").withCapacity(2).withOwner(user).withDescription("Nice Car").withType(VehicleType.COUPE).build());
        return user;
    }

    public User getOne(){
        User user = new User();
        user.availableMoney = new MoneyAndAmount(12.00, CustomCurrencies.ARS);
        user.email = "nachito@gmail.com";
        user.cuil = "121231232";
        user.lastName = "Castro";
        user.name = "Nazareno";
        user.addVehicle(VehicleBuilder.start().withPhoto("asdasd").withCapacity(2).withOwner(user).withDescription("Nice Car").withType(VehicleType.COUPE).build());
        return user;
    }

    public static UserBuilder start() {
        return new UserBuilder();
    }

    public UserBuilder withLastName(String aLastName) {
        this.lastName = aLastName;
        return this;
    }

    public UserBuilder withCUIL(String aCuil) {
        this.cuil = aCuil;
        return this;
    }

    public UserBuilder withEmail(String aEmail) {
        this.email = aEmail;
        return this;
    }


    public UserForm buildForm() {
        UserForm userForm = new UserForm();
        userForm.name = this.name;
        userForm.lastName = this.lastName;
        userForm.cuil = this.cuil;
        userForm.email = this.email;
        return userForm;
    }

    public User build() {
        return new User(name, lastName, cuil, email);
    }

    public static User someUser() {
        return new User("name", "lastname", "12312312312", "name@email.com");
    }
}
