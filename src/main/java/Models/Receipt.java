package Models;

import java.util.Map;

public class Receipt {

    private Map<Merchandise, Integer> orderOfSelectedItemsAndQty;

    private float final_amount_to_pay;
    private float final_amount_tax_to_pay;

    public Receipt() {
    }

    public Map<Merchandise, Integer> getOrderOfSelectedItems() {
        return orderOfSelectedItemsAndQty;
    }

    public void setOrderOfSelectedItems(Map<Merchandise, Integer> orderOfSelectedItems) {
        this.orderOfSelectedItemsAndQty = orderOfSelectedItems;
    }

    public float getFinal_amount_to_pay() {
        return final_amount_to_pay;
    }

    public void setFinal_amount_to_pay(float final_amount_to_pay) {
        this.final_amount_to_pay = final_amount_to_pay;
    }

    public float getFinal_amount_tax_to_pay() {
        return final_amount_tax_to_pay;
    }

    public void setFinal_amount_tax_to_pay(float final_amount_tax_to_pay) {
        this.final_amount_tax_to_pay = final_amount_tax_to_pay;
    }

    @Override
    public String toString() {

        return "Receipt{" +
                "orderOfSelectedItems=" + orderOfSelectedItemsAndQty +
                ", final_amount_to_pay=" + final_amount_to_pay +
                ", final_amount_tax_to_pay=" + final_amount_tax_to_pay +
                '}';
    }
    //TODO modify the string print out to fit the receipt output format required

}
