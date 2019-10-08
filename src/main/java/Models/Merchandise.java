package Models;

import java.util.Arrays;

public class Merchandise {
    private enum MerchandiseCategory {
        food, book, medicine, others
    }

    private enum UnitPackage {
        generic, bar, box, bottle, packet
        //For the goods which don't have a unit name such as book, please use "generic"
    }

    int id;

    String name;

    MerchandiseCategory merchandiseCategory;

    UnitPackage unitPackage;

    float unitPrice;
    //If a Merchandise has more than 1 package unit, then it should be treated as different objects here
    //e.g. obj1: name=chocolate, unitPackage=box, unitPrice=10.00; obj2: name=chocolate, unitPackage=bar, unitPrice=0.85

    boolean imported;

    public Merchandise() {
    }

    public Merchandise(int id, String name, String merchandiseCategory, String unitPackage, float unitPrice, boolean imported) {
        this.id = id;
        this.name = name;

        if (Arrays.asList(UnitPackage.values()).contains(UnitPackage.valueOf(unitPackage))) {
            this.unitPackage = UnitPackage.valueOf(unitPackage);
        } else {
            this.unitPackage = UnitPackage.generic;
            //logging
        }

        this.unitPrice = unitPrice;

        if(Arrays.asList(MerchandiseCategory.values()).contains(MerchandiseCategory.valueOf(merchandiseCategory))){
            this.merchandiseCategory = MerchandiseCategory.valueOf(merchandiseCategory);
        }else{
            this.merchandiseCategory = MerchandiseCategory.others;
            //logging
        }

        this.imported = imported;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPackage() {
        return unitPackage.toString();
    }

    public void setUnitPackage(String unitPackage) {

        if (Arrays.asList(UnitPackage.values()).contains(UnitPackage.valueOf(unitPackage))) {
            this.unitPackage = UnitPackage.valueOf(unitPackage);
        } else {
            this.unitPackage = UnitPackage.generic;
            //logging
        }
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getMerchandiseCategory() {
        return merchandiseCategory.toString();
    }

    public void setMerchandiseCategory(String merchandiseCategory) {

        if(Arrays.asList(MerchandiseCategory.values()).contains(MerchandiseCategory.valueOf(merchandiseCategory))){
            this.merchandiseCategory = MerchandiseCategory.valueOf(merchandiseCategory);
        }else{
            this.merchandiseCategory = MerchandiseCategory.others;
            //logging
        }
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    @Override
    public String toString() {
        return "Merchandise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", merchandiseCategory=" + merchandiseCategory.toString() +
                ", unitPackage=" + unitPackage.toString() +
                ", unitPrice=" + unitPrice +
                ", imported=" + imported +
                '}';
    }
}
