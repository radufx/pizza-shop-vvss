package srir3010MV.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import srir3010MV.model.PaymentType;
import srir3010MV.repository.MenuRepository;
import srir3010MV.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaServiceTest {

    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;

    private int table;
    private PaymentType paymentType;
    private double amount;


    @BeforeEach
    public void setUp() {
        final MenuRepository menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @AfterEach
    public void tearDown() {
    }

    @Tag("add-payment-tc-3-4")
    @DisplayName("add-payment-tc-3-4")
    @ParameterizedTest
    @CsvSource({
            "-2, 30.0",
            "7, -1.0"
    })
    public void addPaymentTC34(int table, double amount) {
        paymentType = PaymentType.Cash;

        assertThrows(Exception.class, () -> pizzaService.addPayment(table, paymentType, amount), "Expected to throw, but it didn't");
    }

    @Tag("add-payment-tc-5-7")
    @DisplayName("add-payment-tc-5-7")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void addPaymentTC57(int table) {
        paymentType = PaymentType.Cash;
        amount = 10.0;

        int initialSize = paymentRepository.getAll().size();
        tryAddPayment(table, paymentType, amount);
        assertEquals(initialSize + 1, paymentRepository.getAll().size());
    }

    @Tag("add-payment-6-8")
    @DisplayName("add-payment-tc-6-8")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    public void addPaymentTC68(int table) {
        paymentType = PaymentType.Card;
        amount = 10.0;

        assertThrows(Exception.class, () -> pizzaService.addPayment(table, paymentType, amount), "Expected to throw, but it didn't");
    }

    @Test
    @Tag("add-payment-tc-3-4")
    @DisplayName("add-payment-tc-1")
    public void addPaymentTC1() {
        table = 2;
        paymentType = PaymentType.Cash;
        amount = 100.0;

        int initialSize = paymentRepository.getAll().size();
        tryAddPayment(table, paymentType, amount);
        assertEquals(initialSize + 1, paymentRepository.getAll().size());
    }

    @Test
    @Tag("add-payment-tc-3-4")
    @DisplayName("add-payment-tc-4")
    public void addPaymentTC4() {
        table = 7;
        paymentType = PaymentType.Cash;
        amount = -1.0;

        assertThrows(Exception.class, () -> pizzaService.addPayment(table, paymentType, amount), "Expected to throw, but it didn't");
    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}