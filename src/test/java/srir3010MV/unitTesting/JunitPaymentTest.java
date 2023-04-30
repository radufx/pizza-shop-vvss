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

class JunitPaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment(5, PaymentType.Cash, 100.0);
    }
    @Test
    void PaymentType() {
        assertEquals(PaymentType.Cash, payment.getType());
        payment.setType(PaymentType.Card);
        assertEquals(PaymentType.Card, payment.getType());
    }

    @Test
    void Amount() {
        assertEquals(100.0, payment.getAmount());
        payment.setAmount(75.0);
        assertEquals(75.0, payment.getAmount());
    }
}
