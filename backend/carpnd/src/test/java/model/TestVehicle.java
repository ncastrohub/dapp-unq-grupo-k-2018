
package model;

import org.junit.Test;

import utils.builders.VehicleBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class TestVehicle {

    @Test
    public void vehicle(){

        Vehicle vehicle = VehicleBuilder.start()
                .withCapacity(5)
                .withType("familiar")
                .withDescripion("fiveDoors")
                .withPhoto("link")
                .build();

        assertThat(vehicle.capacity).isEqualTo(5);
        assertThat(vehicle.type).isEqualTo("familiar");
        assertThat(vehicle.description).isEqualTo("fiveDoors");
        assertThat(vehicle.photo).isEqualTo("link");

    }

}
