package Models;

public class TaxRates {
    private float basicSalesTaxRate;
    private float importSalesTaxRate;

    public TaxRates() {
    }

    public float getBasicSalesTaxRate() {
        return basicSalesTaxRate;
    }

    public void setBasicSalesTaxRate(float basicSalesTaxRate) {
        this.basicSalesTaxRate = basicSalesTaxRate;
    }

    public float getImportSalesTaxRate() {
        return importSalesTaxRate;
    }

    public void setImportSalesTaxRate(float importSalesTaxRate) {
        this.importSalesTaxRate = importSalesTaxRate;
    }

    @Override
    public String toString() {
        return "TaxRates{" +
                "basicSalesTaxRate=" + basicSalesTaxRate +
                ", importSalesTaxRate=" + importSalesTaxRate +
                '}';
    }

}
