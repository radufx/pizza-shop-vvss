package srir3010MV.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import srir3010MV.model.PaymentType;
import srir3010MV.service.PizzaService;

import java.util.Optional;

public class PaymentAlert {
    private PizzaService service;

    public PaymentAlert(PizzaService service){
        this.service=service;
    }

    // @Override
    public void cardPayment() {
        System.out.println("--------------------------");
        System.out.println("Paying by card...");
        System.out.println("Please insert your card!");
        System.out.println("--------------------------");
    }
    // @Override
    public void cashPayment() {
        System.out.println("--------------------------");
        System.out.println("Paying cash...");
        System.out.println("Please show the cash...!");
        System.out.println("--------------------------");
    }
    // @Override
    public void cancelPayment() {
        System.out.println("--------------------------");
        System.out.println("Payment choice needed...");
        System.out.println("--------------------------");
    }
      public void showPaymentAlert(int tableNumber, double totalAmount ) {
        Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment for Table "+tableNumber);
        paymentAlert.setHeaderText("Total amount: " + totalAmount);
        paymentAlert.setContentText("Please choose payment option");
        ButtonType cardPayment = new ButtonType("Pay by Card");
        ButtonType cashPayment = new ButtonType("Pay Cash");
        ButtonType cancel = new ButtonType("Cancel");
        paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);
        Optional<ButtonType> result = paymentAlert.showAndWait();
        if (result.get() == cardPayment) {
            cardPayment();
            try {
                service.addPayment(tableNumber, PaymentType.Card,totalAmount);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (result.get() == cashPayment) {
            cashPayment();
            try {
                service.addPayment(tableNumber, PaymentType.Cash,totalAmount);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (result.get() == cancel) {
             cancelPayment();
        } else {
            cancelPayment();
        }
    }
}
