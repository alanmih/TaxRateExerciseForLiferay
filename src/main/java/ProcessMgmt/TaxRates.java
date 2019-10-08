package ProcessMgmt;

import java.io.IOException;
import java.util.Map;

public interface TaxRates {

    Map<String, Float> readTaxRateFile() throws IOException;

    String getTaxRateFileDirectory();

}
