package srir3010MV.unitTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import srir3010MV.model.Payment;
import srir3010MV.model.PaymentType;
import srir3010MV.repository.MenuRepository;
import srir3010MV.repository.PaymentRepository;
import srir3010MV.service.PizzaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class MockitoPizzaServiceTest {

    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;

    @BeforeEach
    public void setUp() {
        final MenuRepository menuRepository = new MenuRepository();
        paymentRepository = mock(PaymentRepository.class);
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    public void addPayment() {
        int table = 2;
        PaymentType paymentType = PaymentType.Cash;
        double amount = 100.0;

        Payment payment1 = new Payment(table, paymentType, amount);

        Mockito.when(paymentRepository.getAll()).thenReturn(List.of(payment1));

        int initialSize = 1;

        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            fail(e.getMessage());
        }

        Mockito.verify(paymentRepository, times(1)).add(any(Payment.class));

        Mockito.when(paymentRepository.getAll()).thenReturn(List.of(payment1, payment1));

        assertEquals(initialSize + 1, paymentRepository.getAll().size());
    }

    @Test
    public void getTotalAmount() {
        PaymentType paymentType = PaymentType.Cash;

        tryAddPayment(1, paymentType, 5.0);
        tryAddPayment(2, paymentType, 4.0);
        tryAddPayment(1, paymentType, 2.0);

        Mockito.verify(paymentRepository, times(3)).add(any(Payment.class));

        Payment payment1 = new Payment(1, paymentType, 5.0);
        Payment payment2 = new Payment(2, paymentType, 4.0);
        Payment payment3 = new Payment(1, paymentType, 2.0);

        Mockito.when(paymentRepository.getAll()).thenReturn(List.of(payment1, payment2, payment3));

        assertEquals(11.0, pizzaService.getTotalAmountForType(PaymentType.Cash));

        Mockito.verify(paymentRepository, times(1)).getAll();
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}
