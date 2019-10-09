package ProcessMgmt;

import Models.Merchandise;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashRegisterImplTest {

    @Test
    public void addAmountTaxToPay() {
        CashRegisterImpl cashRegister = new CashRegisterImpl();
        cashRegister.addAmountTaxToPay(15);
        assertEquals(15, cashRegister.getAmountTaxToPay(), 0);
    }

    @Test
    public void addAmountToPay() {
        CashRegisterImpl cashRegister = new CashRegisterImpl();
        cashRegister.addAmountToPay(30);
        assertEquals(30, cashRegister.getAmountToPay(),0);
    }

    @Test
    public void getMerchandiseTaxAmount() {
        CashRegisterImpl cashRegister = new CashRegisterImpl();

        Merchandise merchandise_01 = new Merchandise(1, "imported book", "book", "generic", 20.5f, true);
        Merchandise merchandise_02 = new Merchandise(2, "internal book", "book", "generic", 22.3f, false);
        Merchandise merchandise_03 = new Merchandise(3, "good paper", "sheet", "", 0.5f, true);

        Float basicSalesTaxRate = 0.1f;
        Float importSalesTaxRate = 0.05f;

        assertEquals(1.05, Math.ceil(cashRegister.getMerchandiseTaxAmount(merchandise_01, basicSalesTaxRate, importSalesTaxRate) * 100) /100,0);
        assertEquals(0, Math.ceil(cashRegister.getMerchandiseTaxAmount(merchandise_02,basicSalesTaxRate,importSalesTaxRate) * 100) / 100,0);
        assertEquals(0.1, Math.ceil(cashRegister.getMerchandiseTaxAmount(merchandise_03,basicSalesTaxRate,importSalesTaxRate) * 100) /100,0 );
    }


}