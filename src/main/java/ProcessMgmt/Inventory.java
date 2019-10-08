package ProcessMgmt;

import Models.Merchandise;

import java.io.IOException;
import java.util.Map;

public interface Inventory {

    boolean consumeMerchandiseInStorage(Merchandise ingredient, int qty);

    boolean addMerchandiseInStorage(Merchandise ingredient, int qty);

    Map<Merchandise, Integer> readInventoryFile() throws IOException;

    void updateInventoryFile(Map<Merchandise, Integer> updatedInventory) throws IOException;

    public String getInventoryFileDirectory();

}
