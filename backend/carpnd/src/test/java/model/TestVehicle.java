
package model;

import org.junit.Test;

import utils.builders.VehicleBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TestVehicle {

    @Test
    public void vehicle(){

        User user = mock(User.class);

        Vehicle vehicle = VehicleBuilder.start()
                .withCapacity(5)
                .withType(VehicleType.VAN)
                .withDescription("fiveDoors")
                .withPhoto("link")
                .withOwner(user)
                .build();

        assertThat(vehicle.capacity).isEqualTo(5);
        assertThat(vehicle.type).isEqualTo("familiar");
        assertThat(vehicle.description).isEqualTo("fiveDoors");
        assertThat(vehicle.photo).isEqualTo("link");
        assertThat(vehicle.owner).isEqualTo(user);

    }

}
