package srir3010MV.service;

import srir3010MV.model.MenuDataModel;
import srir3010MV.model.Payment;
import srir3010MV.model.PaymentType;
import srir3010MV.repository.MenuRepository;
import srir3010MV.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount) throws Exception {
        Payment payment= new Payment(table, type, amount);

        if (table < 1 || table > 8) throw new Exception("Table must be between 1 and 8");
        if (amount <= 0.0) throw new Exception("Amount must be > 0");

        payRepo.add(payment);
    }

    public double getTotalAmountForType(PaymentType type){
        double total=0.0f;
        List<Payment> l=getPayments();
        if ((l==null) || l.isEmpty()) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                if(p.getAmount() > 0)
                    total+=p.getAmount();
        }
        return total;
    }

}
