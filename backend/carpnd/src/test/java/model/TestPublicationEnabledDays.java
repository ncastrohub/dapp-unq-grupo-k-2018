package model;

import model.exceptions.DayAlreadyReservedException;
import model.exceptions.DayDisabledException;
import model.exceptions.DayNotReservedException;
import org.joda.time.LocalDate;
import org.junit.Test;

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

        enabledDays.setDisabled(dayOne.getDayOfWeek());
        enabledDays.setDisabled(dayTwo.getDayOfWeek());
        enabledDays.setDisabled(dayThree.getDayOfWeek());
        enabledDays.setDisabled(dayFour.getDayOfWeek());

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

        enabledDays.releaseDay(new LocalDate(dayOne.getYear(), dayOne.getMonthOfYear(), dayOne.getDayOfMonth()));

    }


}
