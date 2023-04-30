package srir3010MV.unitTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import srir3010MV.model.Payment;
import srir3010MV.model.PaymentType;
import srir3010MV.repository.MenuRepository;
import srir3010MV.repository.PaymentRepository;
import srir3010MV.service.PizzaService;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;


class JunitPizzaServiceTest {

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
        int table = 2;
        PaymentType paymentType = PaymentType.Cash;
        double amount = 100.0;

        Payment payment1 = new Payment(table, paymentType, amount);

        int initialSize = paymentRepository.getAll().size();
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            fail(e.getMessage());
        }

        assertEquals(initialSize + 1, paymentRepository.getAll().size());
    }

    @Test
    void getTotalAmount() {
        PaymentType paymentType = PaymentType.Cash;

        tryAddPayment(1, paymentType, 5.0);
        tryAddPayment(2, paymentType, 4.0);
        tryAddPayment(1, paymentType, 2.0);

        assertEquals(11.0, pizzaService.getTotalAmountForType(PaymentType.Cash));
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}
