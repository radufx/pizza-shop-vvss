package srir3010MV.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import srir3010MV.model.Payment;
import srir3010MV.model.PaymentType;
import srir3010MV.repository.MenuRepository;
import srir3010MV.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaServiceTestWBT {

    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;

    private int table;
    private PaymentType paymentType;
    private double amount;


    @BeforeEach
    public void setUp() {
        final MenuRepository menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository("data/payments-test.txt");
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @AfterEach
    public void tearDown() {
        paymentRepository.clearAll();
    }

    @Test
    @Tag("get-total-amount")
    @DisplayName("get-total-amount-tc-2")
    public void getTotalAmountTC2() {
        assertEquals(0, pizzaService.getTotalAmountForType(PaymentType.Cash));
    }

    @Test
    @Tag("get-total-amount")
    @DisplayName("get-total-amount-tc-3")
    public void getTotalAmountTC3() {
        paymentType = PaymentType.Cash;

        tryAddPayment(1, paymentType, 5.0);
        tryAddPayment(2, paymentType, 4.0);
        tryAddPayment(1, paymentType, 2.0);
        assertEquals(11.0, pizzaService.getTotalAmountForType(PaymentType.Cash));
    }

    @Test
    @Tag("get-total-amount")
    @DisplayName("get-total-amount-tc-5")
    public void getTotalAmountTC5() {
        paymentType = PaymentType.Card;

        tryAddPayment(1, paymentType, 5.0);
        tryAddPayment(2, paymentType, 4.0);
        tryAddPayment(1, paymentType, 9.0);
        assertEquals(0.0, pizzaService.getTotalAmountForType(PaymentType.Cash));
    }

    @Test
    @Tag("get-total-amount")
    @DisplayName("get-total-amount-tc-4")
    public void getTotalAmountTC4() {
        paymentType = PaymentType.Cash;

        tryAddPayment(1, paymentType, -5.0);
        tryAddPayment(2, paymentType, -4.0);
        tryAddPayment(1, paymentType, -1.0);
        assertEquals(0.0, pizzaService.getTotalAmountForType(PaymentType.Cash));
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            paymentRepository.add(new Payment(table, paymentType, amount));
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}