package model;

import model.Exceptions.DayAlreadyDisabledException;
import model.Exceptions.DayAlreadyReservedException;
import model.Exceptions.DayDisabledException;
import model.Exceptions.DayNotReservedException;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


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

        assertThrows(DayDisabledException.class, ()-> enabledDays.reserveDay(dayOne));
        assertThrows(DayDisabledException.class, ()-> enabledDays.reserveDay(dayTwo));
        assertThrows(DayDisabledException.class, ()-> enabledDays.reserveDay(dayThree));
        assertThrows(DayDisabledException.class, ()-> enabledDays.reserveDay(dayFour));
    }

    @Test
    public void testReserveSomeDaysAndThatsDaysCannotBeReservedAgain(){
        PublicationsEnabledDays enabledDays = new PublicationsEnabledDays();
        LocalDate dayOne = LocalDate.now().plusDays(3);
        try {
            enabledDays.reserveDay(dayOne);
        } catch (DayAlreadyReservedException | DayDisabledException e) {
            assertFalse(true);
        }

        assertThrows(DayAlreadyReservedException.class, ()-> enabledDays.reserveDay(dayOne));
    }

    @Test
    public void testCheckIfSomeDayCanBeReserved(){
        PublicationsEnabledDays enabledDays = new PublicationsEnabledDays();
        LocalDate dayOne = LocalDate.now().plusDays(3);
        assertThat(enabledDays.canReserve(dayOne)).isEqualTo(true);

    }

    @Test
    public void testCheckIfSomeDayReservedCannotBeReserved(){
        PublicationsEnabledDays enabledDays = new PublicationsEnabledDays();
        LocalDate dayOne = LocalDate.now().plusDays(3);

        try {
            enabledDays.reserveDay(dayOne);
        } catch (Exception e) {
            assertFalse(true);
        }

        assertThat(enabledDays.canReserve(dayOne)).isEqualTo(false);
    }

    @Test
    public void testReleasedDaysReservedShowsAsEnabled() throws DayNotReservedException {
        PublicationsEnabledDays enabledDays = new PublicationsEnabledDays();
        LocalDate dayOne = LocalDate.now().plusDays(3);

        try {
            enabledDays.reserveDay(dayOne);
        } catch (Exception e) {
            assertFalse(true);
        }

        enabledDays.releaseDay(LocalDate.of(dayOne.getYear(), dayOne.getMonth(), dayOne.getDayOfMonth()));

    }


}
