SalesTaxForLiferay is an App for the user to select merchandise from the items available in the inventory and calculate the amount to pay, amount tax to pay and show all related information from the receipt.


Following are the features that each class category holds:

1. CashRegister
It can be used to hold the current amount_to_pay and amount_tax_to_pay before all numbers are confirmed and send to Receipt object for print out
Besides, it is where you can find the logic for calculating merchandise's tax amount (method: getMerchandiseTaxAmount) and rounding (roundToNearest)


2. Inventory
It is for inventory related operations such as add to or consume the merchandise from the inventory

Please be aware that the proper operations of the app depend on the inventory.csv with a proper content in it
If inventory.csv doesn't exist, then the App will automatically create a new one.
However, the App will still not be working properly if there is no proper content in inventory.csv
You can either refer to the following data schema and create your own inventory file content or just use the sample content that you can find in {projectDir}/sampleInventoryFileValues.txt

id (type: Integer),
name (type: String),
category (type: String),
unitPackage (type: String),
unitPrice (type: float),
imported (type: boolean),
stockQty (type: int)

The column name in inventory.csv is not required, as long as the data rows with the contents in the correct data types and the right order.
The App will automatically skip reading the column name row by detecting if the first cell contains "id"
For detail data reading/updating logic for inventory.csv, please refer to the methods: readInventoryFile and updateInventoryFile in InventoryImpl.java

The default directory of inventory.csv is your project folder's directory or jar file's directory
All related messages or configuration variables can be found in InventoryImpl.java


3. TaxRates
It is for holding the two important tax variables: basicSalesTaxRate and importSalesTaxRate which should be read from its configuration file taxRate.csv

Please be aware that the proper operations of the app depend on the taxRate.csv with a proper content in it
If taxRate.csv doesn't exist, then the App will automatically create a new one.
However, the App will still not be working properly if there is no proper content in taxRate.csv

Please notice that the data schema of taxRate.csv is different as below:
You can either refer to the following data schema and create your own taxRate file content or just use the sample content that you can find in {projectDir}/sampleTaxRateFileValues.txt

basicSalesTaxRate, 0.1
importSalesTaxRate, 0.05

The order is like {variable name}, {variable value}
The variable for holding basic sales tax rate is "basicSalesTaxRate" and the variable for holding import sales tax rate is "importSalesTaxRate".
The above variable names are configured in TaxRateImpl.java, therefore you can't change these variable names in taxRate.csv otherwise the app can not be reading the values properly

The default directory of taxRate.csv is your project folder's directory or jar file's directory
All related messages or configuration variables can be found in TaxRatesImpl.java


4. UI
This app is using CLI to interact with the user and its entry point (main method) is SalesTax.java
SalesTax.java also contains all UI related working or menu creation logic