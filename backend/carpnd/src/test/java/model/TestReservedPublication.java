package model;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestReservedPublication {



    @Test
    public void testReservedPublicationIsCreatedWithFinalPriceAndDaysReserved(){
        Publication publication = mock(Publication.class);
        List<LocalDate> reservedDays = new LinkedList<>();
        reservedDays.add(LocalDate.now());

        ReservedPublication reservedPublication = new ReservedPublication(publication, reservedDays );

        assertThat(reservedPublication.publication).isEqualTo(publication);
        assertThat(reservedPublication.reservedDays).isEqualTo(reservedDays);
    }


    @Test
    public void testReservedPublicationIsCreatedWithACopyOfFinalPriceButThatPricesNeverChange(){
        Publication publication = mock(Publication.class);
        List<LocalDate> reservedDays = new LinkedList<>();
        reservedDays.add(LocalDate.now());
        MoneyAndAmount costPerHour = new MoneyAndAmount(12.12, CustomCurrencies.ARS);

        when(publication.getCostPerHour()).thenReturn(costPerHour);

        ReservedPublication reservedPublication = new ReservedPublication(publication, reservedDays);

        assertThat(reservedPublication.getCostPerHour()).isEqualTo(costPerHour);
        MoneyAndAmount newCostPerHour = new MoneyAndAmount(24.12, CustomCurrencies.ARS);

        when(publication.getCostPerHour()).thenReturn(newCostPerHour);
        assertThat(reservedPublication.getCostPerHour()).isEqualTo(costPerHour);
        assertThat(reservedPublication.getCostPerHour()).isNotEqualTo(newCostPerHour);
    }


}
