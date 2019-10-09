package ProcessMgmt;

import java.io.IOException;
import java.util.Map;

public interface TaxRates {

    float getBasicSalesTaxRate();

    float getImportSalesTaxRate();

    Map<String, Float> readTaxRateFile() throws IOException;

    String getTaxRateFileDirectory();

}
