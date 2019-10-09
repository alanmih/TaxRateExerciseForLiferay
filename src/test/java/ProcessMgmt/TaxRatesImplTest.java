package ProcessMgmt;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaxRatesImplTest {

    @Test
    public void getTaxRateFileDirectory() {
        String currentDirectory = System.getProperty("user.dir");
        String taxRateFileName = "taxRate.csv";

        String taxRateFileDirectory;

        if (System.getProperty("os.name").startsWith("Windows")) {
            //Handle the cases when user is using Windows
            taxRateFileDirectory = currentDirectory + "\\" + taxRateFileName;

        } else {
            //When user is using other Unix-like system
            taxRateFileDirectory = currentDirectory + "/" + taxRateFileName;
        }

        assertEquals(taxRateFileDirectory, new TaxRatesImpl().getTaxRateFileDirectory());

    }
}