package ProcessMgmt;

import Models.Merchandise;

public interface Order {

    void addOrUpdateOrder(Merchandise merchandise, int qty);

}
