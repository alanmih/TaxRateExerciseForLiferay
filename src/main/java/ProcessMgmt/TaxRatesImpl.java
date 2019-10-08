package ProcessMgmt;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaxRatesImpl implements TaxRates {

    private static Float basicSalesTaxRate;
    private static Float importSalesTaxRate;
    //Make above two tax rates universal

    private final String currentDirectory = System.getProperty("user.dir");
    private final String taxRateFileName = "taxRate.csv";

    private final String basicSalesTaxRateName = "basicSalesTaxRate";
    private final String importSalesTaxRateName = "importSalesTaxRate";

    private final String createNewFileMsg = "%s doesn't exist, already created a new taxRate file for it \n";
    private final String fileAlreadyExitMsg = "%s exists, will keep processing \n";

    private final String taxRateFileIsEmptyMsg = "Tax Rate file is empty, please check the file content and start the app again\n";
    private final String unknownTaxRateNameMsg = "Found unknown Tax rate column name when reading row no.%d , please check your TaxRateFile content\n";


    public TaxRatesImpl() {
        if (basicSalesTaxRate == null || importSalesTaxRate == null) {
            try {
                Map<String, Float> tempTaxRates = readTaxRateFile();

                if (basicSalesTaxRate == null) {
                    basicSalesTaxRate = tempTaxRates.get(basicSalesTaxRateName);

                }
                if (importSalesTaxRate == null) {
                    importSalesTaxRate = tempTaxRates.get(importSalesTaxRateName);
                }

            } catch (IOException e) {
                e.printStackTrace();
                //Logging
            }
        }
    }

    public float getBasicSalesTaxRate() {
        return basicSalesTaxRate;
    }

    public void setBasicSalesTaxRate(float basicSalesTaxRate) {
        this.basicSalesTaxRate = basicSalesTaxRate;
    }

    public float getImportSalesTaxRate() {
        return importSalesTaxRate;
    }

    public void setImportSalesTaxRate(float importSalesTaxRate) {
        this.importSalesTaxRate = importSalesTaxRate;
    }

    @Override
    public Map<String, Float> readTaxRateFile() throws IOException {

        String taxRateFileDir = getTaxRateFileDirectory();
        File taxRateFile = new File(taxRateFileDir);

        if (taxRateFile.createNewFile()) {
            System.out.printf(createNewFileMsg, taxRateFileDir);
            //Logging

        } else {
            System.out.printf(fileAlreadyExitMsg, taxRateFileDir);

        }

        Map<String, Float> tempTaxRates = new HashMap<>();

        Scanner scan = new Scanner(taxRateFile);

        String[] tempStringArray;

        int count = 0;
        if (scan.hasNextLine()) {

            while (scan.hasNextLine()) {
                count++;
                tempStringArray = scan.nextLine().split(",", 2);

                if (basicSalesTaxRateName.equals(tempStringArray[0].trim())) {
                    tempTaxRates.put(basicSalesTaxRateName, Float.valueOf(tempStringArray[1].trim()));

                } else if (importSalesTaxRateName.equals(tempStringArray[0].trim())) {
                    tempTaxRates.put(importSalesTaxRateName, Float.valueOf(tempStringArray[1].trim()));

                } else {
                    System.out.printf(unknownTaxRateNameMsg, count);
                    //Logging
                }
            }

        } else {
            System.out.println(taxRateFileIsEmptyMsg);
            //Logging

            System.exit(0);
            //If the taxRate file is empty, terminate the app and then remind the user to update the taxRate.csv and start the app again
        }

        return tempTaxRates;

    }

    @Override
    public String getTaxRateFileDirectory() {

        if (System.getProperty("os.name").startsWith("Windows")) {
            //Handle the cases when user is using Windows
            return currentDirectory + "\\" + taxRateFileName;

        } else {
            //When user is using other Unix-like system
            return currentDirectory + "/" + taxRateFileName;
        }

    }

    @Override
    public String toString() {
        return "TaxRates{" +
                "basicSalesTaxRate=" + basicSalesTaxRate +
                ", importSalesTaxRate=" + importSalesTaxRate +
                '}';
    }

}
