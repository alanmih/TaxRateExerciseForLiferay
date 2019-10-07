package ProcessMgmt;

import Models.Merchandise;

import java.util.Map;

public interface Inventory {

    Map<Merchandise, Integer> getInventory();

    void initialize_Inventory();

    boolean consumeMerchandiseInStorage(Merchandise ingredient, int qty);

    boolean addMerchandiseInStorage(Merchandise ingredient, int qty);

    boolean addNewMerchandiseToStorage(Merchandise ingredient);

}
