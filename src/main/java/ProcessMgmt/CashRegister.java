package ProcessMgmt;

import Models.Merchandise;

import java.util.Map;

public interface CashRegister {

    float getAmountToPay();

    void setAmountToPay(float amount_to_pay);

    void addAmountToPay(float amount_to_pay_to_add);

    float getAmountTaxToPay();

    void setAmountTaxToPay(float amount_to_pay);

    void addAmountTaxToPay(float amount_to_pay_to_add);

    Float getMerchandiseTaxAmount(Merchandise merchandise, Float basicSalesTaxRate, Float importSalesTaxRate);

}
