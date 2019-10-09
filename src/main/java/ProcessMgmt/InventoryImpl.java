package ProcessMgmt;

import Models.Merchandise;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryImpl implements Inventory {

    public final Logger logger = Logger.getLogger("Global");


    private static Map<Merchandise, Integer> inventory;
    //Make it private to ensure only the initiated inventory obj can access it and all inventory objs share this inventory variable


    private final String merchandiseNotInInventoryMsg = "Merchandise %s is not in the inventory";
    private final String qtyLessThanZeroMsg = "Quantity (value: %d) should not less than 0, please enter again";
    private final String storageNotEnoughMsg = "Quanity in storage: %d, is less than %d units to be consumed";
    private final String otherConsumeMerchandiseExceptionMsg = "Other exception happened when trying to consume %d units of merchandise: %s";
    private final String addNewMerchandiseMsg = "Added a new merchandise: %s; quantity: %d";
    private final String otherAddMerchandiseExceptionMsg = "Other exception happened when trying to add the merchandise to the inventory: %s";

    private final String currentDirectory = System.getProperty("user.dir");
    private final String inventoryFileName = "inventory.csv";
    private final String[] inventoryColumnNames = {"id", "name", "category", "unitPackage", "unitPrice", "imported", "stockQty"};


    private final String createNewFileMsg = "%s doesn't exist, already created a new inventory file for it \n";
    private final String fileAlreadyExitMsg = "%s exists, will keep processing \n";
    private final String inventoryFileIsEmptyMsg = "Inventory file is empty, please check and update the file content and start the app again\n";
    private final String updateInventorySuccessMsg = "Successfully updated the inventory file in %s\n";


    public InventoryImpl() {

        if (inventory == null) {
            try {
                inventory = readInventoryFile();

            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.toString());
            }
            //Automatically read the inventory file when inventory obj is initiated for the first time
        }
    }

    public Map<Merchandise, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Merchandise, Integer> inventory) {
        this.inventory = inventory;
        //TODO Check if setInventory here is necessary
    }

    @Override
    public synchronized boolean consumeMerchandiseInStorage(Merchandise merchandise, int qty) {
        if (inventory.containsKey(merchandise) && qty > 0 && inventory.get(merchandise) >= qty) {
            inventory.put(merchandise, inventory.get(merchandise) - qty);
            return true;

        } else if (inventory.containsKey(merchandise) == false) {
            System.out.printf(merchandiseNotInInventoryMsg, merchandise);
            logger.warn("Can not consume the merchandise because it is not in the inventory: " + merchandise);
            return false;

        } else if (qty <= 0) {
            System.out.printf(qtyLessThanZeroMsg, qty);
            logger.warn("Entered a quantity equal or less than 0 to consumed: " + qty + " for the merchandise: " + merchandise);
            return false;

        } else if (inventory.get(merchandise) < qty) {
            System.out.printf(storageNotEnoughMsg, inventory.get(merchandise), qty);
            logger.warn("Entered a quantity: " + qty + " for the merchandise: " + merchandise + ", which is greater than its stock: " + inventory.get(merchandise));
            return false;

        } else {
            System.out.printf(otherConsumeMerchandiseExceptionMsg, qty, merchandise);
            logger.warn(otherConsumeMerchandiseExceptionMsg + merchandise);
            return false;
        }

    }

    @Override
    public synchronized boolean addMerchandiseInStorage(Merchandise merchandise, int qty) {
        if (inventory.containsKey(merchandise) && qty >= 0) {
            inventory.put(merchandise, inventory.get(merchandise) + qty);
            return true;

        } else if (!inventory.containsKey(merchandise) && qty >= 0) {
            inventory.put(merchandise, qty);
            System.out.printf(addNewMerchandiseMsg, merchandise, qty);
            logger.warn("Added a new merchandise to stock: " + merchandise + ", quantity: " + qty);
            return true;

        } else if (qty < 0) {
            System.out.printf(qtyLessThanZeroMsg, qty);
            logger.warn("Entered a quantity equal or less than 0 to added: " + qty + " for the merchandise: " + merchandise);
            return false;

        } else {
            System.out.printf(otherAddMerchandiseExceptionMsg, merchandise);
            logger.warn(otherAddMerchandiseExceptionMsg + merchandise);
            return false;
        }

    }

    @Override
    public synchronized Map<Merchandise, Integer> readInventoryFile() throws IOException {
        String inventoryFileDir = getInventoryFileDirectory();
        File inventoryFile = new File(inventoryFileDir);

        if (inventoryFile.createNewFile()) {
            System.out.printf(createNewFileMsg, inventoryFileDir);

        } else {
            System.out.printf(fileAlreadyExitMsg, inventoryFileDir);

        }

        Map<Merchandise, Integer> tempInventory = new LinkedHashMap<Merchandise, Integer>();

        Scanner scan_02 = new Scanner(inventoryFile);

        String[] tempStringArray;

        if (scan_02.hasNextLine()) {

            while (scan_02.hasNextLine()) {
                tempStringArray = scan_02.nextLine().split(",", 7);

                if ("id".equals(String.valueOf(tempStringArray[0]))) {
                    continue;
                    //Skip the first row for column name

                } else {
                    tempInventory.put(new Merchandise(Integer.valueOf(tempStringArray[0].trim()),
                            tempStringArray[1],
                            tempStringArray[2].trim(),
                            tempStringArray[3].trim(),
                            Float.valueOf(tempStringArray[4].trim()),
                            Boolean.valueOf(tempStringArray[5])), Integer.valueOf(tempStringArray[6].trim()));
                }

            }

        } else {
            System.out.println(inventoryFileIsEmptyMsg);
            logger.fatal(inventoryFileIsEmptyMsg);

            System.exit(1);
            //If the inventory file is empty, terminate the app and then remind the user to update the inventory.csv and start the app again
        }

        return tempInventory;

    }

    @Override
    public synchronized void updateInventoryFile(Map<Merchandise, Integer> updatedInventory) throws IOException {
        String inventoryFileDir = getInventoryFileDirectory();

        try (FileWriter inventoryFileWriter = new FileWriter(inventoryFileDir)) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < inventoryColumnNames.length; i++) {
                stringBuilder.append(inventoryColumnNames[i] + ((i < inventoryColumnNames.length - 1) ? ',' : ""));
            }
            stringBuilder.append("\n");

            for (Map.Entry<Merchandise, Integer> entry : updatedInventory.entrySet()) {
                stringBuilder.append(String.valueOf(entry.getKey().getId()) + ',');
                stringBuilder.append(entry.getKey().getName() + ',');
                stringBuilder.append(entry.getKey().getMerchandiseCategory() + ',');
                stringBuilder.append(entry.getKey().getUnitPackage() + ',');
                stringBuilder.append(entry.getKey().getUnitPrice() + ",");
                stringBuilder.append(entry.getKey().isImported() + ",");
                stringBuilder.append(entry.getValue());

                stringBuilder.append("\n");
            }

            inventoryFileWriter.write(stringBuilder.toString());

            System.out.printf(updateInventorySuccessMsg, inventoryFileDir);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    @Override
    public String getInventoryFileDirectory() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            return currentDirectory + "\\" + inventoryFileName;
            //Handle the cases when user is using Windows

        } else {
            return currentDirectory + "/" + inventoryFileName;
            //When user is using other Unix-like system
        }

    }
}