import Models.Merchandise;
import Models.Receipt;
import ProcessMgmt.CashRegisterImpl;
import ProcessMgmt.TaxRatesImpl;
import ProcessMgmt.InventoryImpl;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesTax {
    public static final Logger logger = Logger.getLogger("Global");

    private final String userTerminalWelcomeHeader = "\n*** Welcome to the Shopping System ***\n";

    InventoryImpl inventory = new InventoryImpl();
    TaxRatesImpl taxRates = new TaxRatesImpl();
    CashRegisterImpl cashRegister = new CashRegisterImpl();


    public static void main(String[] args) {

        logger.info("Started the App");

        SalesTax salesTax = new SalesTax();

        Receipt receipt = salesTax.generateReceipt(salesTax.getUserSelectedItems());
        //TODO calculate the amount to pay and print out the receipt based on userSelectedItemsAndQty


//        TaxRatesImpl taxRates = new TaxRatesImpl();
//
//        System.out.println("Tax Rates from the file: " + taxRates);
//        System.out.println("===\n");
//
//
//        InventoryImpl inventory = new InventoryImpl();
//
//        Map<Merchandise, Integer> tempInventory = inventory.getInventory();
//
//        CashRegisterImpl cashRegister = new CashRegisterImpl();
//
//
//        Merchandise tempMerchandise = new Merchandise();


//        System.out.println("Print out the inventory obj before the update: ");

//        for (Map.Entry<Merchandise, Integer> entry : tempInventory.entrySet()) {
//            if ("book".equals(entry.getKey().getName())) {
//                tempMerchandise = entry.getKey();
//            }
//
//            float merchandisePricePlusTax = entry.getKey().getUnitPrice() + cashRegister.getMerchandiseTaxAmount(entry.getKey(), taxRates.getBasicSalesTaxRate(), taxRates.getImportSalesTaxRate());
//
//            System.out.println("The merchandise: " + entry.getKey() + "; Quantity: " + entry.getValue() +
//                    "; unitPricePlusTaxAmount: " + merchandisePricePlusTax);
//
//        }


//
//        System.out.println("===\n\n");
//
//        tempInventory.put(tempMerchandise, 29);
//
//        try {
//            inventory.updateInventoryFile(tempInventory);
//            tempInventory = inventory.readInventoryFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Print out the inventory obj after the update: ");
//
//
//        for (Map.Entry<Merchandise, Integer> entry : tempInventory.entrySet()) {
//            System.out.println("The merchandise: " + entry.getKey() + "; Quantity: " + entry.getValue());
//        }


    }

    private Receipt generateReceipt(Map<Merchandise, Integer> userSelectedItemsAndQty){


        return null;
    }


    private Map<Merchandise, Integer> getUserSelectedItems() {

        System.out.println(userTerminalWelcomeHeader);


        boolean stopUiSign = false;

        Map<Merchandise, Integer> currentInventory = inventory.getInventory();

        Map<Merchandise, Integer> userSelectedItemsAndQty = new LinkedHashMap<>();
        //For storing user's selection of Merchandise and desired quantity

        Map<Integer, Merchandise> indexToMerchandiseObj = new LinkedHashMap<>();
        //For quickly access to the target after getting user's input


        Scanner scan = new Scanner(System.in);

        int userInputOpt;
        int userInputQty;

        do {
            userInputOpt = -1;
            userInputQty = -1;

            int itemIndex = 1;

            indexToMerchandiseObj.clear();

            System.out.println("** Current items available in the inventory **");
            for (Map.Entry<Merchandise, Integer> entry : currentInventory.entrySet()) {

                if (entry.getValue() > 0) {
                    System.out.println("No. " + itemIndex + " ; Name: " + entry.getKey().getName() + " ; Unit Price: " + entry.getKey().getUnitPrice() + " ; Stock Qty: " + entry.getValue());
                    indexToMerchandiseObj.put(itemIndex, entry.getKey());
                    itemIndex++;
                }

            }

            while (!indexToMerchandiseObj.containsKey(userInputOpt)) {

                try {
                    System.out.print("Please enter your option ( from 1 to " + (itemIndex - 1) + " ) or enter 0 to exit:");
                    userInputOpt = scan.nextInt();

                    if (userInputOpt == 0) {
                        break;
                    }

                } catch (InputMismatchException e) {
                    System.out.print("Your input should be equal or within " + currentInventory.size() + ", Please enter again\n");
                    scan.nextLine();

                }

            }

            if (userInputOpt == 0) {
                System.out.println("\nThank you for using our order system, see you next time!");
                logger.info("App exited based on the user input in the process");
                System.exit(0);
                //Exit the app if user input is 0
            }
            //Let the user select a merchandise which is available in the inventory

            Merchandise currentSelectedMerchandise = indexToMerchandiseObj.get(userInputOpt);
            int currentMerchandiseStockQty = currentInventory.get(currentSelectedMerchandise);

            while (userInputQty <= 0 || userInputQty > currentMerchandiseStockQty) {
                try {
                    System.out.print("Please enter the quantity you'd like to order  ( from 1 to " + currentMerchandiseStockQty + " ):");
                    userInputQty = scan.nextInt();

                } catch (InputMismatchException e) {
                    System.out.print("Quantity to consumed should be equal or within " + currentMerchandiseStockQty + ", Please enter again\n");
                    scan.nextLine();

                }

            }
            //Let the user enter the quantity of the selected merchandise they'd like to order


            inventory.consumeMerchandiseInStorage(currentSelectedMerchandise, userInputQty);

            if (userSelectedItemsAndQty.containsKey(currentSelectedMerchandise)) {
                userSelectedItemsAndQty.put(currentSelectedMerchandise, userSelectedItemsAndQty.get(currentSelectedMerchandise) + userInputQty);
            } else {
                userSelectedItemsAndQty.put(currentSelectedMerchandise, userInputQty);
            }
            //Record user selected merchandise and qty to the order

            System.out.println("\n\nWould you like to order other merchandises?");
            System.out.println("1: Yes");
            System.out.println("2: No (checkout and see the receipt)");

            userInputOpt = -1;
            //Reset userInputOpt

            while (userInputOpt != 1 && userInputOpt != 2) {
                try {
                    System.out.print("Please enter your option (1 or 2):");
                    userInputOpt = scan.nextInt();

                } catch (InputMismatchException e) {
                    System.out.print("Your input should be an integer (either 1 or 2), Please try again\n");
                    scan.nextLine();

                }
            }

            System.out.println("\n\n");

            stopUiSign = (userInputOpt == 2) ? true : false;


        } while (stopUiSign == false);

        return userSelectedItemsAndQty;

    }


}