package model;

import model.Exceptions.DayAlreadyDisabledException;
import model.Exceptions.DayDisabledException;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class TestPublicationEnabledDays {



    @Test
    public void testStartsWithAllDaysEnabled(){
        PublicationsEnabledDays enabledDays = new PublicationsEnabledDays();
        assertThat(enabledDays.getDisabledDays().size()).isEqualTo(0);
    }

    @Test
    public void testSetSomeDaysDisabledAndThatDaysCannotBeReserved(){
        PublicationsEnabledDays enabledDays = new PublicationsEnabledDays();
        LocalDate dayOne = LocalDate.now().plusDays(3);
        LocalDate dayTwo = LocalDate.now().plusDays(7);
        LocalDate dayThree = LocalDate.now().plusDays(8);
        LocalDate dayFour = LocalDate.now().plusDays(10);

        try {
            enabledDays.setDisabled(dayOne);
            enabledDays.setDisabled(dayTwo);
            enabledDays.setDisabled(dayThree);
            enabledDays.setDisabled(dayFour);
        } catch (DayAlreadyDisabledException e) {
            assertFalse(true);
        }

        assertThrows(DayDisabledException.class, ()->{
            enabledDays.reserveDay(dayOne);
        });
        assertThrows(DayDisabledException.class, ()->{
            enabledDays.reserveDay(dayTwo);
        });
        assertThrows(DayDisabledException.class, ()->{
            enabledDays.reserveDay(dayThree);
        });
        assertThrows(DayDisabledException.class, ()->{
            enabledDays.reserveDay(dayFour);
        });
    }
//
//    @Test
//    public void testReleaseDaysReservedShowsAsEnabled(){
//
//    }



}
