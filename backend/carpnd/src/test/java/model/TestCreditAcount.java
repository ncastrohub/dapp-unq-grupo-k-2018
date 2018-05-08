package model;

import model.Exceptions.NotEnoughCreditException;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TestCreditAcount {

    @Test
    public void whenATransactionIsMadeMoneyIsReservedForThatReservationButIsNotDebited() throws NotEnoughCreditException {
        User user = mock(User.class);
        Reservation reservation = mock(Reservation.class);
        CreditAccount acount = new CreditAccount(user);
        assertThat(acount.avaibleCredit()).isEqualTo(new MoneyAndAmount(0.00, CustomCurrencies.ARS));
        acount.addCredit(new MoneyAndAmount(400.00, CustomCurrencies.ARS));

        acount.reserveMoney(new MoneyAndAmount(200.00, CustomCurrencies.ARS), reservation);

        assertThat(acount.avaibleCredit()).isEqualTo(new MoneyAndAmount(200.00, CustomCurrencies.ARS));

    }

   /* @Test
    public void whenAPayIsConfirmedTheMoneyIsTransferedFromOneUserToOther(){

    }

    @Test
    public void whenAPaymentIsCanceledTheValueReturnToTheCreditAcountOwner(){

    }

    @Test
    public void canAddMoneyToTheAccount(){

    }*/

}
