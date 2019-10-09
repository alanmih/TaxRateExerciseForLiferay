package ProcessMgmt;

import Models.Merchandise;

import java.util.Map;

public interface CashRegister {

    void addAmountToPay(float amount_to_pay_to_add);

    void addAmountTaxToPay(float amount_to_pay_to_add);

    Float getMerchandiseTaxAmount(Merchandise merchandise, Float basicSalesTaxRate, Float importSalesTaxRate);

}
