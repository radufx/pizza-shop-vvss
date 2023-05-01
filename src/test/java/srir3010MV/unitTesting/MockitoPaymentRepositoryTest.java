package srir3010MV.unitTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import srir3010MV.model.Payment;
import srir3010MV.model.PaymentType;
import srir3010MV.repository.MenuRepository;
import srir3010MV.repository.PaymentRepository;
import srir3010MV.service.PizzaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class MockitoPaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository("data/payments-test.txt");
    }

    @AfterEach
    void cleanEnvironment() {
        paymentRepository.clearAll();
    }

    @Test
    void addPayment() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.toString()).thenReturn("2,Cash,100.0");

        assertEquals(0, paymentRepository.getAll().size());
        paymentRepository.add(payment);
        assertEquals(1, paymentRepository.getAll().size());

        Mockito.verify(payment, times(0)).getAmount();
    }

    @Test
    void getAllPayment() {

        Payment payment = mock(Payment.class);
        String paymentString = "2,Cash,100.0";
        Mockito.when(payment.toString()).thenReturn(paymentString);
        Mockito.when(payment.getTableNumber()).thenReturn(2);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment.getAmount()).thenReturn(100.0);

        assertEquals(0, paymentRepository.getAll().size());
        paymentRepository.add(payment);
        paymentRepository.add(payment);
        paymentRepository.add(payment);
        assertEquals(3, paymentRepository.getAll().size());
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(paymentString, paymentList.get(1).toString());
        assertEquals(2, paymentList.get(1).getTableNumber());
        assertEquals(PaymentType.Cash, paymentList.get(1).getType());
        assertEquals(100.0, paymentList.get(1).getAmount());


        Mockito.verify(payment, times(1)).getTableNumber();
        Mockito.verify(payment, times(1)).getType();
        Mockito.verify(payment, times(1)).getAmount();
    }
}
