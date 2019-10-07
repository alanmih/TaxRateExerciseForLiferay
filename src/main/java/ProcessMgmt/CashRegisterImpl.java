package ProcessMgmt;

public class CashRegisterImpl implements CashRegister {

    private float amount_to_pay = 0.0f;

    private float amount_tax_to_pay = 0.0f;

    //TODO add TaxRates calculation logic

    final private String cashRegisterErrorMsg = "%f is not allowed, please enter a value which is greater than 0 ";

    public float getAmountToPay() {
        return this.amount_to_pay;
    }

    public void setAmountToPay(float amount_to_pay) {
        this.amount_to_pay = amount_to_pay;
    }

    public void addAmountToPay(float amount_to_pay_to_add) {
        if (amount_to_pay_to_add > 0) {
            this.amount_to_pay += amount_to_pay_to_add;
        }else{
            System.out.printf(cashRegisterErrorMsg+"\n", amount_to_pay_to_add);
            //logging
        }
    }

    public float getAmountTaxToPay() {
        return this.amount_tax_to_pay;
    }

    public void setAmountTaxToPay(float amount_tax_to_pay) {
        this.amount_tax_to_pay = amount_tax_to_pay;
    }

    public void addAmountTaxToPay(float amount_tax_to_pay_to_add) {
        if(amount_tax_to_pay_to_add > 0){
            this.amount_tax_to_pay += amount_tax_to_pay_to_add;
        }else{
            System.out.printf(cashRegisterErrorMsg+"\n", amount_tax_to_pay_to_add);
            //logging
        }

    }
}