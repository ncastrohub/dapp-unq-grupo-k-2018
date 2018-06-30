package model;

import org.junit.Test;
import utils.builders.UserBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUser {

    @Test
    public void userIsCreatedWithSpectedValues(){

        User user = UserBuilder.start()
                .withName("Nazareno")
                .withLastName("Castro")
                .withCUIL("23232323")
                .withEmail("ncastro@gmail.com")
                .build();

        assertThat(user.name).isEqualTo("Nazareno");
        assertThat(user.lastName).isEqualTo("Castro");
        assertThat(user.cuil).isEqualTo("23232323");
        assertThat(user.email).isEqualTo("ncastro@gmail.com");

    }

}
