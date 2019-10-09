package ProcessMgmt;

import Models.Merchandise;
import org.apache.log4j.Logger;

public class CashRegisterImpl implements CashRegister {

    public final Logger logger = Logger.getLogger("Global");


    private float amount_to_pay = 0.0f;

    private float amount_tax_to_pay = 0.0f;

    final private String cashRegisterErrorMsg = "%f is not allowed, please enter a value again ";

    public float getAmountToPay() {
        return this.amount_to_pay;
    }

    public void setAmountToPay(float amount_to_pay) {
        this.amount_to_pay = amount_to_pay;
    }

    public float getAmountTaxToPay() {
        return this.amount_tax_to_pay;
    }

    public void setAmountTaxToPay(float amount_tax_to_pay) {
        this.amount_tax_to_pay = amount_tax_to_pay;
    }


    @Override
    public void addAmountToPay(float amount_to_pay_to_add) {
        if (amount_to_pay_to_add > 0) {
            this.amount_to_pay += amount_to_pay_to_add;
        } else {
            System.out.printf(cashRegisterErrorMsg + "\n", amount_to_pay_to_add);
            logger.warn("Entered amount_to_pay_to_add equal or less than 0");
        }
    }

    @Override
    public void addAmountTaxToPay(float amount_tax_to_pay_to_add) {
        if (amount_tax_to_pay_to_add >= 0) {
            this.amount_tax_to_pay += amount_tax_to_pay_to_add;
        } else {
            System.out.printf(cashRegisterErrorMsg + "\n", amount_tax_to_pay_to_add);
            logger.warn("Entered amount_tax_to_pay_to_add less than 0");

        }

    }

    @Override
    public Float getMerchandiseTaxAmount(Merchandise merchandise, Float basicSalesTaxRate, Float importSalesTaxRate) {

        String merchandiseCategory = merchandise.getMerchandiseCategory();
        float merchandiseTaxRate = ((merchandiseCategory == "others") ? basicSalesTaxRate : 0) + ((merchandise.isImported() == true) ? importSalesTaxRate : 0);

        return roundToNearest(merchandise.getUnitPrice() * merchandiseTaxRate);
        //Calculate a merchandise's amount to paid plus tax and return
    }

    private Float roundToNearest(float taxAmount) {
        return (float) Math.ceil(taxAmount * 20) / 20;
        // np/100 rounded up to the nearest 0.05
    }

}