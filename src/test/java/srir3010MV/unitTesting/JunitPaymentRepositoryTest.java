package srir3010MV.unitTesting;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class JunitPaymentRepositoryTest {

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
        Payment payment = new Payment(2, PaymentType.Cash, 100.0);

        assertEquals(0, paymentRepository.getAll().size());
        paymentRepository.add(payment);
        assertEquals(1, paymentRepository.getAll().size());
    }

    @Test
    void getAllPayment() {

        Payment payment =  new Payment(2, PaymentType.Cash, 100.0);

        assertEquals(0, paymentRepository.getAll().size());
        paymentRepository.add(payment);
        paymentRepository.add(payment);
        paymentRepository.add(payment);
        assertEquals(3, paymentRepository.getAll().size());
        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(payment.toString(), paymentList.get(1).toString());
        assertEquals(2, paymentList.get(1).getTableNumber());
        assertEquals(PaymentType.Cash, paymentList.get(1).getType());
        assertEquals(100.0, paymentList.get(1).getAmount());
    }
}
