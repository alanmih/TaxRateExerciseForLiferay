package Models;

import ProcessMgmt.CashRegisterImpl;
import ProcessMgmt.OrderImpl;

public class Receipt {

    private OrderImpl order;
    //TODO Implement @Autowire

    private CashRegisterImpl cashRegister;
    //TODO Implement @Autowire

    public Receipt() {
    }

    public Receipt(OrderImpl order, CashRegisterImpl cashRegister) {
        this.order = order;
        this.cashRegister = cashRegister;
    }

    public OrderImpl getOrder() {
        return order;
    }

    public void setOrder(OrderImpl order) {
        this.order = order;
    }

    public CashRegisterImpl getCashRegister() {
        return cashRegister;
    }

    public void setCashRegister(CashRegisterImpl cashRegister) {
        this.cashRegister = cashRegister;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "order=" + order +
                ", cashRegister=" + cashRegister +
                '}';
    }
    //TODO modify the string print out to fit the output format required

}
