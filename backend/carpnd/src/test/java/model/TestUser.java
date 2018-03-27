package model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestUser {


//Test a User
    public void createUserAndCheckExpectedValues(){
        User user = UserBuilder.withName("Nazareno")
                .withLastName("Castro")
                .withCUIL("23232323")
                .withEmail("ncastro@gmail.com");

        assertThat(user.name).isEqualTo("Nazareno");
        assertThat(user.lastName).isEqualTo("Castro");
        assertThat(user.cuil).isEqualTo("23232323");
        assertThat(user.email).isEqualTo("ncastro@gmail.com");

    }
//    Testear que el usuario se crea con un rate de owner en zero y un rate de customer en zero
    public void createUserAndOwnerAndCustomerReputationIsEmptyZero(){
        User user = UserBuilder.with

    }


//    Testear que el usuario se crea con credito cero

}
