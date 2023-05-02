package srir3010MV.unitTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import srir3010MV.model.Payment;
import srir3010MV.model.PaymentType;
import srir3010MV.repository.MenuRepository;
import srir3010MV.repository.PaymentRepository;
import srir3010MV.service.PizzaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class MockitoPaymentTest {

    private Payment payment;


    @BeforeEach
    public void setUp() {
        payment = new Payment(5, PaymentType.Cash, 100.0);
    }

    @Test
    public void PaymentType() {
        Payment spyPayment = spy(payment);

        verify(spyPayment, times(0)).getType();

        assertEquals(PaymentType.Cash, spyPayment.getType());
        doReturn(PaymentType.Card).when(spyPayment).getType();
        assertEquals(PaymentType.Card, spyPayment.getType());

        verify(spyPayment, times(2)).getType();
    }

    @Test
    public void Amount() {
        Payment spyPayment = spy(payment);

        verify(spyPayment, times(0)).getAmount();
        verify(spyPayment, times(0)).setAmount(anyDouble());

        assertEquals(100.0, spyPayment.getAmount());
        spyPayment.setAmount(75.0);
        assertEquals(75.0, spyPayment.getAmount());
        doReturn(50.0).when(spyPayment).getAmount();
        assertEquals(50.0, spyPayment.getAmount());


        verify(spyPayment, times(3)).getAmount();
        verify(spyPayment, times(1)).setAmount(anyDouble());
    }
}
