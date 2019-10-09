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

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Merchandise, Integer> entry : orderOfSelectedItemsAndQty.entrySet()) {
            sb.append(entry.getValue() + " ");
            sb.append(entry.getKey().getName() + ": ");
            sb.append(String.format("%.2f", (entry.getKey().unitPrice + entry.getKey().getUnitPriceTax()) * entry.getValue()));
            sb.append("\n");
        }

        sb.append("Sales Taxs: " + String.format("%.2f", final_amount_tax_to_pay));
        sb.append("\n");
        sb.append("Total: " + String.format("%.2f", final_amount_to_pay));

        return sb.toString();
    }

}
