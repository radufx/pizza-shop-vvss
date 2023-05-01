package srir3010MV.integrationTesting;

import junit.framework.Assert;
import org.junit.jupiter.api.AfterEach;
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

class RepositoryIntegrationTest {

    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        final MenuRepository menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository("data/payments-test.txt");
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @AfterEach
    void cleanEnvironment() {
        paymentRepository.clearAll();
    }

    @Test
    void addPayment() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getAmount()).thenReturn(100.0);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment.getTableNumber()).thenReturn(1);

        int initialSize = paymentRepository.getAll().size();
        try {
            pizzaService.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(initialSize + 1, paymentRepository.getAll().size());
    }

    @Test
    void getTotalAmount() {
        Payment payment1 = mock(Payment.class);
        Payment payment2 = mock(Payment.class);
        Payment payment3 = mock(Payment.class);
        Mockito.when(payment1.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment1.getAmount()).thenReturn(5.0);
        Mockito.when(payment2.getAmount()).thenReturn(4.0);
        Mockito.when(payment2.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment3.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment3.getAmount()).thenReturn(2.0);

        tryAddPayment(1, payment1.getType(), payment1.getAmount());
        tryAddPayment(2, payment2.getType(), payment2.getAmount());
        tryAddPayment(1, payment3.getType(), payment3.getAmount());

        Assert.assertEquals(11.0, pizzaService.getTotalAmountForType(PaymentType.Cash));
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
