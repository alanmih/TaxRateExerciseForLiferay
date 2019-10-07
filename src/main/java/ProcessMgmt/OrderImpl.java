package ProcessMgmt;

import Models.Merchandise;
import java.util.HashMap;
import java.util.Map;

public class OrderImpl implements Order{

    private HashMap<Merchandise, Integer> selectedMerchandisesAndQtyObj = new HashMap<Merchandise, Integer>();

    public HashMap<Merchandise, Integer> getSelectedMerchandisesAndQtyObj() {
        return selectedMerchandisesAndQtyObj;
    }

    public void addOrUpdateOrder(Merchandise merchandise, int qty) {

        for(Map.Entry<Merchandise, Integer> entry: selectedMerchandisesAndQtyObj.entrySet()){
            if(entry.getKey().equals(merchandise)){
                selectedMerchandisesAndQtyObj.put(merchandise, selectedMerchandisesAndQtyObj.get(merchandise)+qty);
            }else{
                selectedMerchandisesAndQtyObj.put(merchandise, qty);
            }

        }

    }

}
