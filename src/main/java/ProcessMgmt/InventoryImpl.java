package ProcessMgmt;

import Models.Merchandise;

import java.util.HashMap;
import java.util.Map;

public class InventoryImpl implements Inventory{

     private static Map<Merchandise, Integer> inventory;
     //For simulating the inventory, which would be best to store the data in a DB


    public Map<Merchandise, Integer> getInventory() {
        return null;
    }

    public void initialize_Inventory() {

    }

    public boolean consumeMerchandiseInStorage(Merchandise ingredient, int qty) {
        return false;
    }

    public boolean addMerchandiseInStorage(Merchandise ingredient, int qty) {
        return false;
    }

    public boolean addNewMerchandiseToStorage(Merchandise ingredient) {
        return false;
    }

}
