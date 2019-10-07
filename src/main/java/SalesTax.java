import Models.TaxRates;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SalesTax {

    private final String currentDirectory = System.getProperty("user.dir");

    private final String taxRateFileName = "taxRate.csv";
    private final String basicSalesTaxRateName = "basicSalesTaxRate";
    private final String importSalesTaxRateName = "importSalesTaxRate";

    private final String inventoryFileName = "inventory.csv";

    private final String createNewFileMsg = "%s doesn't exist, already created a new file for it \n";
    private final String fileAlreadyExitMsg = "%s exists, will keep processing \n";
    private final String taxRateFileIsEmptyMsg = "Tax Rate file is empty, please check the file content\n";
    private final String unknownTaxRateNameMsg = "Found unknown Tax rate column name when reading row no.%d , please check your TaxRateFile content\n";

    public static void main(String[] args) {
//        System.out.println("Test printing current directory: " + System.getProperty("user.dir"));
//        System.out.println("Current OS is :" + System.getProperty("os.name"));
        SalesTax salesTax = new SalesTax();
//        System.out.println("create dir test: " + salesTax.createFileDir(salesTax.getTaxRateFileName()));
//        System.out.println("create dir test_02: " + salesTax.createFileDir(salesTax.getInventoryFileName()));


        TaxRates taxRates = new TaxRates();

        try {
            taxRates = salesTax.readTaxRateFile();

        } catch (IOException e) {
            System.out.println(e);
            //Logging
        }

        System.out.println("Print out the TaxRates obj got: " + taxRates);

    }

    public TaxRates readTaxRateFile() throws IOException {
        String taxRateFileDir = getTaxRateFileDirectory();
        File taxRateFile = new File(taxRateFileDir);

        if (taxRateFile.createNewFile()) {
            System.out.printf(createNewFileMsg, taxRateFileDir);
            //Logging

        } else {
            System.out.printf(fileAlreadyExitMsg, taxRateFileDir);

        }

        TaxRates taxRates = new TaxRates();

        Scanner scan = new Scanner(taxRateFile);

        String[] tempStringArray;

        int count = 0;
        if (scan.hasNextLine()) {
            while (scan.hasNextLine()) {
                count++;
                tempStringArray = scan.nextLine().split(",");

                if (basicSalesTaxRateName.equals(tempStringArray[0])) {
                    taxRates.setBasicSalesTaxRate(Float.valueOf(tempStringArray[1]));

                } else if (importSalesTaxRateName.equals(tempStringArray[0])) {
                    taxRates.setImportSalesTaxRate(Float.valueOf(tempStringArray[1]));

                } else {
                    System.out.printf(unknownTaxRateNameMsg, count);
                    //Logging
                }
            }

        } else {
            System.out.println(taxRateFileIsEmptyMsg);
            //Logging
        }

        return taxRates;
    }


    public void readInventoryFile() throws IOException {
        //TODO Implement the Inventory file reading

    }

    public void updateInventoryFile() throws IOException {
        //TODO Implement the Inventory file updating

    }

    public String getTaxRateFileDirectory() {
        return getFileDir(taxRateFileName);
    }

    public String getInventoryFileDirectory() {
        return getFileDir(inventoryFileName);
    }

    private String getFileDir(String fileName) {
        if (isWindows()) {
            //Handle the cases when user is using Windows
            return currentDirectory + "\\" + fileName;

        } else {
            //When user is using other Unix-like system
            return currentDirectory + "/" + fileName;
        }

    }

    private boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }


}