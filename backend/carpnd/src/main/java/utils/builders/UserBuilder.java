package utils.builders;

import model.User;
import model.Vehicle;

public class UserBuilder {

    private String name;
    private String lastName;
    private String cuil;
    private String email;

    public UserBuilder withName(String aName) {
        this.name = aName;
        return this;
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

    public User build() {
        return new User(name, lastName, cuil, email);
    }

    public static User someUser() {
        return new User("name", "lastname", "123123", "name@email.com");
    }
}
