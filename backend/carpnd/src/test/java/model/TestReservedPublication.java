package model;

import org.junit.Test;
import org.joda.time.LocalDate;
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
        User customer = mock(User.class);
        reservedDays.add(LocalDate.now());
        AdressLocation acquireLocation = mock(AdressLocation.class);

        when(publication.getCostPerHour()).thenReturn( new MoneyAndAmountForPublication(10.00, CustomCurrencies.ARS));
        when(publication.getAcquireLocation()).thenReturn(acquireLocation);
        when(acquireLocation.createNew()).thenReturn(acquireLocation);
        ReservedPublication reservedPublication = new ReservedPublication(publication, reservedDays, customer, mock(AdressLocation.class));

        assertThat(reservedPublication.publication).isEqualTo(publication);
        assertThat(reservedPublication.reservedDays).isEqualTo(reservedDays);
    }


    @Test
    public void testReservedPublicationIsCreatedWithACopyOfFinalPriceButThatPricesNeverChange(){
        Publication publication = mock(Publication.class);
        List<LocalDate> reservedDays = new LinkedList<>();
        User customer = mock(User.class);
        AdressLocation acquireLocation = mock(AdressLocation.class);
        reservedDays.add(LocalDate.now());
        MoneyAndAmountForPublication costPerHour = new MoneyAndAmountForPublication(12.12, CustomCurrencies.ARS);

        when(publication.getCostPerHour()).thenReturn(costPerHour);
        when(publication.getCostPerHour().createNew()).thenReturn(costPerHour);

        when(publication.getAcquireLocation()).thenReturn(acquireLocation);
        when(acquireLocation.createNew()).thenReturn(acquireLocation);

        ReservedPublication reservedPublication = new ReservedPublication(publication, reservedDays, customer, mock(AdressLocation.class));

        assertThat(reservedPublication.getCostPerHour()).isNotEqualTo(costPerHour);
        assertThat(reservedPublication.getCostPerHour().getClass()).isEqualTo(costPerHour.getClass());
        MoneyAndAmountForPublication newCostPerHour = new MoneyAndAmountForPublication(24.12, CustomCurrencies.ARS);

        assertThat(reservedPublication.getCostPerHour()).isNotEqualTo(newCostPerHour);
    }

}
