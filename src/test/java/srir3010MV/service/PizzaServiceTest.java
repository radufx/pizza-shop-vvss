package srir3010MV.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import srir3010MV.model.PaymentType;
import srir3010MV.repository.MenuRepository;
import srir3010MV.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.fail;

class PizzaServiceTest {

    private MenuRepository menuRepository;
    private PaymentRepository paymentRepository;
    private PizzaService pizzaService;

    private int table;
    private PaymentType paymentType;
    private double amount;


    @BeforeEach
    void setUp() {
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addPayment() {
        table = 2;
        paymentType = PaymentType.Cash;
        amount = 100.0;
        tryAddPayment(table, paymentType, amount);

        table = 'q';
        paymentType = PaymentType.Card;
        amount = 12.0;
        tryAddPayment(table, paymentType, amount);

        table = -2;
        paymentType = PaymentType.Cash;
        amount = 30.0;
        tryAddPayment(table, paymentType, amount);

        table = 'e';
        paymentType = PaymentType.Card;
        amount = 'r';
        tryAddPayment(table, paymentType, amount);

        table = 7;
        paymentType = PaymentType.Cash;
        amount = -1.0;
        tryAddPayment(table, paymentType, amount);

        table = 9;
        paymentType = PaymentType.Card;
        amount = -2.0;
        tryAddPayment(table, paymentType, amount);

        table = 10;
        paymentType = PaymentType.Cash;
        amount = 'f';
        tryAddPayment(table, paymentType, amount);



        table = 1;
        paymentType = PaymentType.Cash;
        amount = 10.0;
        tryAddPayment(table, paymentType, amount);

        table = 0;
        paymentType = PaymentType.Card;
        amount = 10.0;
        tryAddPayment(table, paymentType, amount);

        table = 2;
        paymentType = PaymentType.Cash;
        amount = 10.0;
        tryAddPayment(table, paymentType, amount);

        table = 8;
        paymentType = PaymentType.Card;
        amount = 10.0;
        tryAddPayment(table, paymentType, amount);

        table = 7;
        paymentType = PaymentType.Cash;
        amount = 10.0;
        tryAddPayment(table, paymentType, amount);

        table = 9;
        paymentType = PaymentType.Card;
        amount = 10.0;
        tryAddPayment(table, paymentType, amount);

        table = 6;
        paymentType = PaymentType.Cash;
        amount = 0.0;
        tryAddPayment(table, paymentType, amount);

        table = 6;
        paymentType = PaymentType.Card;
        amount = 1.0;
        tryAddPayment(table, paymentType, amount);

        table = 6;
        paymentType = PaymentType.Cash;
        amount = Integer.MAX_VALUE - 1;
        tryAddPayment(table, paymentType, amount);

        table = 6;
        paymentType = PaymentType.Card;
        amount = Integer.MAX_VALUE + 1;
        tryAddPayment(table, paymentType, amount);

        table = 6;
        paymentType = PaymentType.Cash;
        amount = Integer.MAX_VALUE;
        tryAddPayment(table, paymentType, amount);

    }

    private void tryAddPayment(int table, PaymentType paymentType, double amount){
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}