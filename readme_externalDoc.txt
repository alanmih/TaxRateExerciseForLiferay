SalesTaxForLiferay is an App for the user to select merchandise from the items available in the inventory and calculate the amount to pay, amount tax to pay and show all related information from the receipt.


1. Please check the configuration files before launching the app
Please check if the following configuration files exist with the proper contents in your jar file directory:
inventory.csv
taxRate.csv

if no, please contact the app provider for the these two configuration files or
you can run the app and the app should be able to create a new empty file for you (either inventory.csv or taxRate.csv)

Please also be aware that the app can't be running properly without the proper contents in above two configuration files

For the sample file contents, you can refer to the following:


taxRate.csv:
============================================================={following are the sample content for taxRate.csv}

basicSalesTaxRate, 0.1
importSalesTaxRate, 0.05

============================================================={above are the sample content for taxRate.csv}


inventory.csv:
============================================================={following are the sample content for inventory.csv}

id,name,category,unitPackage,unitPrice,imported,stockQty
1,book,book,generic,12.49,false,5
2,music CD,others,generic,14.99,false,3
3,chocolate bar,food,bar,0.85,false,11
4,imported box of chocolates, food, box, 10.00, true, 3
5,imported box of chocolates,food,box,11.25,true,4
6,imported bottle of perfume,others,bottle,47.50,true,4
7,imported bottle of perfume,others,bottle,27.99,true,4
8,bottle of perfume,others,bottle,18.99,false,3
9,packet of headache pills,medicine,packet,9.75,false,7

============================================================={above are the sample content for inventory.csv}


2. The menu and the operations you can do

== Select items ==
At the beginning of the app, it will list up all the merchandise which are still available with stock quantity number than 0

Please select the item by entering the menu id, which should be an integer and be aware that any other formats such as string are not allowed
If you'd like to exit the app, you can just enter 0 here

After selecting the item, you'd have to enter the item quantity to consume, which should be an integer that within the corresponding stock quantity

Then you will see the following menu asking if you will like to keep ordering other items.
If you choose yes then the above item & quantity will be repeated again

merchandise
Would you like to order other merchandises?
1: Yes
2: No (checkout and see the receipt)


== Check out and see the receipt ==
After you finish selecting the items you'd like to order, please select "2: No (checkout and see the receipt)" to end the selection process

The receipt details which includes the total amount to pay and total tax amount to pay will be displayed