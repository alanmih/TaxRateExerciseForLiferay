import Models.Merchandise;
import ProcessMgmt.TaxRatesImpl;
import ProcessMgmt.InventoryImpl;

import java.io.IOException;
import java.util.Map;

public class SalesTax {

    public static void main(String[] args) {

        TaxRatesImpl taxRates = new TaxRatesImpl();

        System.out.println("Tax Rates from the file: " + taxRates);
        System.out.println("===\n");


        InventoryImpl inventory = new InventoryImpl();

        Map<Merchandise, Integer> tempInventory = inventory.getInventory();

        Merchandise tempMerchandise = new Merchandise();

        System.out.println("Print out the inventory obj before the update: ");

        for (Map.Entry<Merchandise, Integer> entry : tempInventory.entrySet()) {
            if ("book".equals(entry.getKey().getName())) {
                tempMerchandise = entry.getKey();
            }
            System.out.println("The merchandise: " + entry.getKey() + "; Quantity: " + entry.getValue());
        }

        System.out.println("===\n\n");

        tempInventory.put(tempMerchandise, 29);

        try {
            inventory.updateInventoryFile(tempInventory);
            tempInventory = inventory.readInventoryFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Print out the inventory obj after the update: ");


        for (Map.Entry<Merchandise, Integer> entry : tempInventory.entrySet()) {
            System.out.println("The merchandise: " + entry.getKey() + "; Quantity: " + entry.getValue());
        }
    }

}