package utils.builders;

import api.forms.UserForm;
import model.User;

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
