package ProcessMgmt;

import Models.Merchandise;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryImplTest {

    @Test
    public void consumeMerchandiseInStorage() {
        Merchandise merchandise_01 = new Merchandise(1, "imported book", "book", "generic", 20.5f, true);

        InventoryImpl inventory = new InventoryImpl();

        inventory.addMerchandiseInStorage(merchandise_01, 10);

        assertFalse(inventory.consumeMerchandiseInStorage(merchandise_01, 11));
        assertTrue(inventory.consumeMerchandiseInStorage(merchandise_01, 9));
        assertFalse(inventory.consumeMerchandiseInStorage(merchandise_01, -1));
    }

    @Test
    public void addMerchandiseInStorage() {
        Merchandise merchandise_01 = new Merchandise(1, "imported book", "book", "generic", 20.5f, true);

        InventoryImpl inventory = new InventoryImpl();

        inventory.addMerchandiseInStorage(merchandise_01, 10);

        assertEquals(10, inventory.getInventory().get(merchandise_01), 0);

        inventory.addMerchandiseInStorage(merchandise_01, 9);

        assertEquals(19, inventory.getInventory().get(merchandise_01), 0);
    }

    @Test
    public void getInventoryFileDirectory() {
        String currentDirectory = System.getProperty("user.dir");
        String inventoryFileDirectory;
        String inventoryFileName = "inventory.csv";

        if (System.getProperty("os.name").startsWith("Windows")) {
            inventoryFileDirectory =  currentDirectory + "\\" + inventoryFileName;
            //Handle the cases when user is using Windows

        } else {
            inventoryFileDirectory = currentDirectory + "/" + inventoryFileName;
            //When user is using other Unix-like system
        }

        assertEquals(inventoryFileDirectory, new InventoryImpl().getInventoryFileDirectory());
    }

}