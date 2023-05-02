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

import static org.mockito.Mockito.mock;

public class PaymentIntegrationTest {
    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;

    @BeforeEach
    public void setUp() {
        final MenuRepository menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository("data/payments-test.txt");
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @AfterEach
    public void cleanEnvironment() {
        paymentRepository.clearAll();
    }

    @Test
    public void addPayment() {
        int table = 2;
        PaymentType paymentType = PaymentType.Cash;
        double amount = 100.0;
        Payment payment1 = new Payment(table, paymentType, amount);

        int initialSize = paymentRepository.getAll().size();
        try {
            pizzaService.addPayment(payment1.getTableNumber(), payment1.getType(), payment1.getAmount());
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(initialSize + 1, paymentRepository.getAll().size());
        paymentRepository.add(payment1);
        Assert.assertEquals(initialSize + 2, paymentRepository.getAll().size());
    }

    @Test
    public void getTotalAmount() {
        Payment payment1 = new Payment(1, PaymentType.Cash, 5.0);
        Payment payment2 = new Payment(2, PaymentType.Cash, 4.0);
        Payment payment3 = new Payment(1, PaymentType.Cash, 2.0);

        tryAddPayment(payment1.getTableNumber(), payment1.getType(), payment1.getAmount());
        tryAddPayment(payment2.getTableNumber(), payment2.getType(), payment2.getAmount());
        tryAddPayment(payment3.getTableNumber(), payment3.getType(), payment3.getAmount());

        Assert.assertEquals(11.0, pizzaService.getTotalAmountForType(PaymentType.Cash));
        paymentRepository.add(new Payment(3, PaymentType.Cash, 4.0));
        Assert.assertEquals(15.0, pizzaService.getTotalAmountForType(PaymentType.Cash));
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
