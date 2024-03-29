package cis.pkg250.lab.pkg01;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Monday February 7, 2022
//
//  Project Name:   Laboratory 01   
//
//  Program Description: This program will manage a furniture inventory and 
//  generate bills. The program is menu-driven with choices to add and 
//  delete products for sale, increase and decrease an existing product's 
//  quantity, start a customer bill, and quit the program. Furniture 
//  information initially obtained from parallel sub-arrays include item IDs, 
//  quantities, prices, and notes. When generating bills, customer information 
//  will be requested by the user and displayed on the bill. The bill will be 
//  able to hold one furniture item, but the quantity can be as much as the 
//  item is available. Basic input error handling is also included. Once the 
//  user chooses to quit, all of the changes to the inventory will be saved to 
//  the same file through an overwrite method.  
// 
//  Project Number:  Project 01

public class CIS250Lab01 {

    public static void main(String[] args) throws IOException {
 
        String [ ] itemID;
        int [ ] itemQuantityAvailable;
        double [ ] itemPrice;
        String [ ] itemNote;
        int numProducts;
        
        //Manage Furniture Inventory
        itemID = new String [5000];
        itemQuantityAvailable = new int [5000];
        itemPrice = new double [5000];
        itemNote = new String [5000];
        
        numProducts = LoadFurnitureInventory(itemID, itemQuantityAvailable, 
            itemPrice, itemNote);
        numProducts = PerformEachTask(numProducts, itemID, 
            itemQuantityAvailable, itemPrice, itemNote);
        SaveFurnitureInventory(numProducts, itemID, itemQuantityAvailable, 
            itemPrice, itemNote);
        
    }
    
    public static int LoadFurnitureInventory(String [ ] itemID, int [ ] 
        itemQuantityAvailable, double [ ] itemPrice, String [ ] itemNote) 
            throws IOException {
        
        int numProducts;
        File furnitureInfo;
        Scanner furnitureInfoSC;
        
        //Load Furniture Inventory
        numProducts = 0;
        furnitureInfo = new File("furnitureinventory.txt");
        furnitureInfoSC = new Scanner(furnitureInfo);
        while(numProducts < itemID.length && furnitureInfoSC.hasNext( )) {
            
            itemID [numProducts] = furnitureInfoSC.nextLine( ); //from file
            itemQuantityAvailable [numProducts] = furnitureInfoSC.nextInt( ); 
                //from file
            itemPrice [numProducts] = furnitureInfoSC.nextDouble( ); //from file
            furnitureInfoSC.nextLine( );
            itemNote [numProducts] = furnitureInfoSC.nextLine( ); //from file
            numProducts++;
            
        }
        furnitureInfoSC.close( );
        return numProducts;
        
    }
    
    public static int PerformEachTask(int numProducts, String [ ] itemID, int 
        [ ] itemQuantityAvailable, double [ ] itemPrice, String [ ] itemNote) {
       
        char userChoice;
       
        //Perform Each Task
        userChoice = 'Z';
        while(userChoice != 'F') {
           
            userChoice = GetChoice( );
            numProducts = PerformChoice(userChoice, numProducts, itemID, 
                itemQuantityAvailable, itemPrice, itemNote);
           
        }
        return numProducts;
       
    }
    
    public static char GetChoice( ) {
        
        char userChoice;
        Scanner kbd = new Scanner(System.in);
        
        //Get Choice
        userChoice = 'G';
        while(userChoice != 'A' && userChoice != 'B' && userChoice != 'C' && 
            userChoice != 'D' && userChoice != 'E' && userChoice != 'F') {
            
            System.out.print("What would you like to do: Add a product [A], "
                + "Delete a product [B], Increase the quantity of a product "
                    + "[C], Decrease the quantity of a product [D], Start a "
                        + "customer bill [E], or Quit and save the furniture "
                            + "inventory [F]?  "); //to user
            userChoice = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            System.out.println( );
            
        }
        return userChoice;
        
    }
    
    public static int PerformChoice(char userChoice, int numProducts, 
        String [ ] itemID, int [ ] itemQuantityAvailable, double [ ] itemPrice,
            String [ ] itemNote) {
        
        //Perform Choice
        if(userChoice == 'A')
            numProducts = AddProduct(numProducts, itemID, 
                itemQuantityAvailable, itemPrice, itemNote);
        if(userChoice == 'B')
            numProducts = DeleteProduct(numProducts, itemID, 
                itemQuantityAvailable, itemPrice, itemNote);
        if(userChoice == 'C')
            IncreaseQuantityOfAProduct(numProducts, itemID, itemNote, 
                itemQuantityAvailable);
        if(userChoice == 'D')
            DecreaseQuantityOfAProduct(numProducts, itemID, itemNote, 
                itemQuantityAvailable);
        if(userChoice == 'E')
            GenerateBill(numProducts, itemID, itemQuantityAvailable, itemNote, 
                itemPrice);
        
        return numProducts;
        
    }
    
    public static int AddProduct(int numProducts, String [ ] itemID, int [ ] 
        itemQuantityAvailable, double [ ] itemPrice, String [ ] itemNote) {
        
        String searchItemID;
        int loc;
        
        //Add Product
        if(numProducts < itemID.length) {
             
            searchItemID = GetItemID( );
            loc = SearchForMatchingItemID(numProducts, searchItemID, itemID);
            numProducts = AddNonmatchingProductInformation(numProducts, 
                itemID, itemQuantityAvailable, itemPrice, itemNote, loc, 
                    searchItemID);
        
        }
        if(numProducts >= itemID.length)
            System.out.println("The furniture inventory is full! You need "
                + "to delete at least one item before you can add any more.");
                    //to user
                    
        return numProducts;
        
    }
    
    public static String GetItemID( ) {
        
        String searchItemID;
        Scanner kbd = new Scanner(System.in);
        
        //Get Item ID
        System.out.print("Enter item ID number:  "); //to user
        searchItemID = kbd.nextLine( ); //from user
        System.out.println( );
        
        return searchItemID;
        
    }
    
    public static int SearchForMatchingItemID(int numProducts, 
        String searchItemID, String [ ] itemID) {
        
        int loc;
        
        //Search for Matching Item ID
        loc = 0;
        while(loc < numProducts && searchItemID.compareToIgnoreCase(
            itemID [loc]) != 0)
            loc++;
        
        return loc;
        
    }
    
    public static int AddNonmatchingProductInformation(int numProducts, 
        String [ ] itemID, int [ ] itemQuantityAvailable, double [ ] itemPrice, 
            String [ ] itemNote, int loc, String searchItemID) {
        
        Scanner kbd = new Scanner(System.in);
        
        //Add Nonmatching Product Information
        if(numProducts < itemID.length) {
                       
            if(loc < numProducts)
                System.out.println("Item ID is already in use!"); //to user
            if(loc >= numProducts) {
                
                itemID [numProducts] = searchItemID;
                itemQuantityAvailable [numProducts] = -1;
                while(itemQuantityAvailable [numProducts] < 0) {

                    System.out.print("Enter VALID item quantity (No negative "
                        + "item quantities are allowed):  "); //to user
                    itemQuantityAvailable [numProducts] = kbd.nextInt( ); 
                        //from user

                }
                itemPrice [numProducts] = -0.01;
                while(itemPrice [numProducts] < 0) {

                    System.out.print("Enter VALID item price (No negative item "
                        + "prices are allowed):  "); //to user
                    itemPrice [numProducts] = kbd.nextDouble( ); //from user

                }
                kbd.nextLine( );
                System.out.print("Enter item note:  "); //to user
                itemNote [numProducts] = kbd.nextLine( ); //from user
                System.out.println( );
                System.out.print(itemNote [numProducts] + " have been added to"
                    + " the furniture inventory."); //to user
                numProducts++;
            
            }
            
        }
        System.out.println( );
        return numProducts;
    
    }
    
    public static int DeleteProduct(int numProducts, String [ ] itemID, 
        int [ ] itemQuantityAvailable, double [ ] itemPrice, 
            String [ ] itemNote) {
        
        String searchItemID;
        int loc;
        
        //Delete Product
        searchItemID = GetItemID( );
        loc = SearchForMatchingItemID(numProducts, searchItemID, itemID);
        numProducts = PerformDeletion(loc, numProducts, itemID, 
            itemQuantityAvailable, itemPrice, itemNote);
        
        return numProducts;
        
    }
    
    public static int PerformDeletion(int loc, int numProducts, String [ ] 
        itemID, int [ ] itemQuantityAvailable, double [ ] itemPrice, 
            String [ ] itemNote) {
        
        //Perform Deletion
        if(loc >= numProducts)     
            System.out.println("Item was not found. Try again!"); //to user  
        if(loc < numProducts) {
                      
            System.out.println(itemNote [loc] + " have been deleted from the "
                + "furniture inventory."); //to user
            itemID [loc] = itemID [numProducts - 1];
            itemQuantityAvailable [loc] = itemQuantityAvailable 
                [numProducts - 1];
            itemPrice [loc] = itemPrice [numProducts - 1];
            itemNote [loc] = itemNote [numProducts - 1];
            numProducts--;
            
        }
        return numProducts;
        
    }
    
    public static void IncreaseQuantityOfAProduct(int numProducts, 
        String [ ] itemID, String [ ] itemNote, int [ ] itemQuantityAvailable) {
        
        String searchItemID;
        int loc;
        
        //Increase Quantity of a Product
        searchItemID = GetItemID( );
        loc = SearchForMatchingItemID(numProducts, searchItemID, itemID);
        GetItemQuantityToAdd(loc, numProducts, itemNote, itemQuantityAvailable);
        
    }
    
    public static int [ ] GetItemQuantityToAdd(int loc, int numProducts, 
        String [ ] itemNote, int [ ] itemQuantityAvailable) {
        
        int addItemQuantityAvailable;
        Scanner kbd = new Scanner(System.in);
        
        //Get Item Quantity to Add
        if(loc >= numProducts)     
            System.out.println("Item was not found. Try again!"); //to user    
        if(loc < numProducts) {
    
            addItemQuantityAvailable = -1;
            while(addItemQuantityAvailable < 0) {
                
                System.out.print("Enter the quantity you would like "
                    + "to add to the current quantity of " + itemNote [loc] + 
                        " (Do not enter a negative quantity):  "); //to user
                addItemQuantityAvailable = kbd.nextInt( ); //from user
                System.out.println( );
                
            }
            itemQuantityAvailable [loc] = itemQuantityAvailable [loc] + 
                addItemQuantityAvailable;
            System.out.print(addItemQuantityAvailable + " " + itemNote [loc]
                + " has/have been added! Bringing its total item quantity to "
                    + itemQuantityAvailable [loc]); //to user
            System.out.println( );
            
        }
        return itemQuantityAvailable;
        
    }
    
    public static void DecreaseQuantityOfAProduct(int numProducts, 
        String [ ] itemID, String [ ] itemNote, int [ ] itemQuantityAvailable) {
        
        String searchItemID;
        int loc;
        
        //Decrease Quantity of a Product
        searchItemID = GetItemID( );
        loc = SearchForMatchingItemID(numProducts, searchItemID, itemID);
        GetItemQuantityToRemove(loc, numProducts, itemNote, 
            itemQuantityAvailable);
       
    }
    
    public static int [ ] GetItemQuantityToRemove(int loc, int numProducts, 
        String [ ] itemNote, int [ ] itemQuantityAvailable) {
        
        int removeItemQuantityAvailable;
        Scanner kbd = new Scanner(System.in);
        
        //Get Item Quantity to Remove  
        if(loc >= numProducts)     
            System.out.println("Item was not found. Try again!"); //to user  
        if(loc < numProducts) {
        
            removeItemQuantityAvailable = -1;
            while(removeItemQuantityAvailable < 0) {
                
                System.out.print("Enter the quantity you would like to remove "
                    + "from the current quantity of " + itemNote [loc] + " "
                        + "(Do not enter a negative quantity):  "); //to user
                removeItemQuantityAvailable = kbd.nextInt( ); //from user
                System.out.println( );
                
            }
            if(itemQuantityAvailable [loc] - removeItemQuantityAvailable < 0)
                System.out.println("Quantity to remove was not accepted since "
                    + "it brings the quantity of that item down to a negative "
                        + "number."); //to user
            
            if(itemQuantityAvailable [loc] - removeItemQuantityAvailable >= 0) {
                
                itemQuantityAvailable [loc] = itemQuantityAvailable [loc] - 
                    removeItemQuantityAvailable;
                System.out.println(removeItemQuantityAvailable + " " + 
                    itemNote [loc] + " has/have been removed! Bringing its "
                        + "total item quantity to " + itemQuantityAvailable 
                            [loc]); //to user
                
            }
             
        }
        return itemQuantityAvailable;
        
    }
    
    public static void GenerateBill(int numProducts, String [ ] itemID, 
        int [ ] itemQuantityAvailable, String [ ] itemNote, double [ ] 
            itemPrice) {
        
        String customerName;
        String customerAddress;
        String customerCityStateZip;
        String searchItemID;
        int loc;
        int itemQuantity;
        double subtotal;
        double tax;
        double total;
        
        //Generate Bill
        customerName = GetCustomerName( );
        customerAddress = GetCustomerAddress( );
        customerCityStateZip = GetCustomerCityStateZip( );
        searchItemID = GetItemID( );
        loc = SearchForMatchingItemID(numProducts, searchItemID, itemID);
        itemQuantity = GetItemQuantity(loc, numProducts, itemQuantityAvailable,
            itemNote);
        subtotal = CalculateSubtotal(itemQuantity, itemPrice, loc, numProducts);
        tax = CalculateTax(subtotal);
        total = CalculateTotal(subtotal, tax);
        DisplayCustomerInformation(customerName, customerAddress, 
            customerCityStateZip);
        DisplayFurnitureHeading( );
        DisplayFurnitureSummary(itemID, itemNote, itemQuantity, itemPrice, 
            loc, numProducts);
        DisplayBillTotals(subtotal, tax, total);
        
    }
    
    public static String GetCustomerName( ) {
        
        String customerName;
        Scanner kbd = new Scanner(System.in);
        
        //Get Customer Name
        System.out.print("Enter customer name:  "); //to user
        customerName = kbd.nextLine( ); //from user
        
        return customerName;
        
    }
    
    public static String GetCustomerAddress( ) {
        
        String customerAddress;
        Scanner kbd = new Scanner(System.in);
        
        //Get Customer Address
        System.out.print("Enter customer address:  "); //to user
        customerAddress = kbd.nextLine( ); //from user
        
        return customerAddress;
        
    }
    
    public static String GetCustomerCityStateZip( ) {
        
        String customerCityStateZip;
        Scanner kbd = new Scanner(System.in);
        
        //Get Customer City, State, Zip
        System.out.print("Enter the city, state, and zip code of the "
            + "customer:  "); //to user
        customerCityStateZip = kbd.nextLine( ); //from user
        
        return customerCityStateZip;
        
    }
    
    public static int GetItemQuantity(int loc, int numProducts, 
        int [ ] itemQuantityAvailable, String [ ] itemNote) {
        
        int itemQuantity;
        Scanner kbd = new Scanner(System.in);
        
        //Get Item Quantity
        itemQuantity = 0;
        if(loc < numProducts) {
              
            itemQuantity = -1;
            while(itemQuantity < 0 || itemQuantity > itemQuantityAvailable 
                [loc]) {
                
                System.out.print("Enter the quantity of " + itemNote [loc] + 
                    " that the customer wants (Do not enter a negative "
                        + "quantity or a quantity that is more than what is "
                            + "available):  "); //to user
                itemQuantity = kbd.nextInt( ); //from user
                System.out.println( );
                
            }
            
        }
        return itemQuantity;
        
    }
    
    public static double CalculateSubtotal(int itemQuantity, double [ ] 
        itemPrice, int loc, int numProducts) {
        
        double subtotal;
        
        //Calculate Subtotal
        subtotal = 0.00;
        if(loc < numProducts)
            subtotal = itemQuantity * itemPrice [loc];
        
        return subtotal;
        
    }
    
    public static double CalculateTax(double subtotal) {
        
        double tax;
        
        //Calculate Tax
        tax = subtotal * 0.08;
        
        return tax;
        
    }
    
    public static double CalculateTotal(double subtotal, double tax) {
        
        double total;
        
        //Calculate Total
        total = subtotal + tax;
        
        return total;
        
    }
    
    public static void DisplayCustomerInformation(String customerName, 
        String customerAddress, String customerCityStateZip) {
        
        //Display Customer Information
        System.out.println("Customer Name: " + customerName); //to user
        System.out.println("Customer Address: " + customerAddress); //to user
        System.out.println("Customer City, State, and Zip Code: " + 
            customerCityStateZip); //to user
        System.out.println( );
        
    }
    
    public static void DisplayFurnitureHeading( ) {
        
        //Display Furniture Heading
        System.out.printf("%-20s%-30s%-30s%-1s\n", "Item ID", 
            "Item Description", "Item Quantity Purchased", "Price Per Item"); 
                //to user
        
    }
    
    public static void DisplayFurnitureSummary(String [ ] itemID, 
        String [ ] itemNote, int itemQuantity, double [ ] itemPrice, int loc, 
            int numProducts) {
        
        //Display Furniture Summary
        if(loc < numProducts)
            System.out.printf("%-20s%-30s%-30d%-1.2f\n", itemID [loc], 
                itemNote [loc], itemQuantity, itemPrice [loc]); //to user
        System.out.println( );
        
    }
    
    public static void DisplayBillTotals(double subtotal, double tax, 
        double total) {
        
        //Display Bill Totals
        System.out.printf("%-1s%-1.2f\n", "Subtotal: ", subtotal); //to user
        System.out.printf("%-1s%-1.2f\n", "Tax (8.0%): ", tax); //to user
        System.out.printf("%-1s%-1.2f\n", "Total: ", total); //to user
        System.out.println( );
        
    }
    
    public static void SaveFurnitureInventory(int numProducts, 
        String [ ] itemID, int [ ] itemQuantityAvailable, double [ ] itemPrice,
            String [ ] itemNote) throws IOException {
        
        PrintWriter saveFurnitureInfoPW;
        
        //Save Furniture Inventory
        saveFurnitureInfoPW = new PrintWriter("furnitureinventory.txt");
        for(int cnt = 0; cnt < numProducts; cnt++) {
            
            saveFurnitureInfoPW.println(itemID [cnt]); //to file
            saveFurnitureInfoPW.println(itemQuantityAvailable [cnt]); //to file
            saveFurnitureInfoPW.printf("%-1.2f\n", itemPrice [cnt]); //to file
            saveFurnitureInfoPW.println(itemNote [cnt]); //to file
            
        }
        saveFurnitureInfoPW.close( );
        
    }
    
}
