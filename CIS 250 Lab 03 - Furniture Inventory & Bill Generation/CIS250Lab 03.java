package cis.pkg250.lab.pkg03;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday February 25, 2022
//
//  Project Name:   Laboratory 03   
//
//  Program Description: This program will manage a furniture inventory and 
//  generate bills. The program is menu-driven with choices to add and 
//  delete products for sale, increase and decrease an existing product's 
//  quantity, start a customer bill, and quit the program. Furniture 
//  information that will be held by a sub-arrays of objects include item IDs, 
//  quantities, prices, and notes. When generating bills, customer information 
//  will be requested by the user and displayed on the bill. The bill will be 
//  able to hold one furniture item, but the quantity can be as much as the 
//  item is available. Basic input error handling is also included. Once the 
//  user chooses to quit, all of the changes to the inventory will be saved to 
//  the same file through an overwrite method.  
// 
//  Project Number:  Project 03

public class CIS250Lab03 {

    public static void main(String[] args) throws IOException {
 
        Furniture [ ] theInventory;
        int numProducts;
        
        //Manage Furniture Inventory
        theInventory = new Furniture [5000];
        
        numProducts = LoadFurnitureInventory(theInventory);
        numProducts = PerformEachTask(numProducts, theInventory);
        SaveFurnitureInventory(numProducts, theInventory);
        
    }
    
    public static int LoadFurnitureInventory(Furniture [ ] theInventory) 
            throws IOException {
        
        int numProducts;
        File furnitureInfo;
        Scanner furnitureInfoSC;
        String itemID;
        int itemQuantityAvailable;
        double itemPrice;
        String itemNote;
        
        //Load Furniture Inventory
        numProducts = 0;
        furnitureInfo = new File("furnitureinventory.txt");
        furnitureInfoSC = new Scanner(furnitureInfo);
        while(numProducts < theInventory.length && furnitureInfoSC.hasNext( )) {
            
            itemID = furnitureInfoSC.nextLine( ); //from file
            itemQuantityAvailable = furnitureInfoSC.nextInt( ); 
                //from file
            itemPrice = furnitureInfoSC.nextDouble( ); //from file
            furnitureInfoSC.nextLine( );
            itemNote = furnitureInfoSC.nextLine( ); //from file
            
            theInventory[numProducts] = new Furniture( );
            
            theInventory[numProducts].SetItemID(itemID);
            theInventory[numProducts].SetItemQuantityAvailable(
                itemQuantityAvailable);
            theInventory[numProducts].SetItemPrice(itemPrice);
            theInventory[numProducts].SetItemNote(itemNote);
            numProducts++;
            
        }
        furnitureInfoSC.close( );
        return numProducts;
        
    }
    
    public static int PerformEachTask(int numProducts, Furniture [ ] 
        theInventory) {
       
        char userChoice;
       
        //Perform Each Task
        userChoice = 'Z';
        while(userChoice != 'F') {
           
            userChoice = GetChoice( );
            numProducts = PerformChoice(userChoice, numProducts, theInventory);
           
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
    
    public static int PerformChoice(char userChoice, int numProducts, Furniture
        [ ] theInventory) {
        
        //Perform Choice
        if(userChoice == 'A')
            numProducts = AddProduct(numProducts, theInventory);
        if(userChoice == 'B')
            numProducts = DeleteProduct(numProducts, theInventory);
        if(userChoice == 'C')
            IncreaseQuantityOfAProduct(numProducts, theInventory);
        if(userChoice == 'D')
            DecreaseQuantityOfAProduct(numProducts, theInventory);
        if(userChoice == 'E')
            GenerateBill(numProducts, theInventory);
        
        return numProducts;
        
    }
    
    public static int AddProduct(int numProducts, Furniture [ ] theInventory) {
        
        String searchItemID;
        int loc;
        
        //Add Product
        if(numProducts < theInventory.length) {
             
            searchItemID = GetItemID( );
            loc = SearchForMatchingItemID(numProducts, searchItemID, 
                theInventory);
            numProducts = AddNonmatchingProductInformation(numProducts, loc,  
                searchItemID, theInventory);
        
        }
        if(numProducts >= theInventory.length)
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
        String searchItemID, Furniture [ ] theInventory) {
        
        int loc;
        
        //Search for Matching Item ID
        loc = 0;
        while(loc < numProducts && searchItemID.compareToIgnoreCase(
            theInventory[loc].GetItemID( )) != 0)
            loc++;
        
        return loc;
        
    }
    
    public static int AddNonmatchingProductInformation(int numProducts, int loc, 
        String searchItemID, Furniture [ ] theInventory) {
        
        Scanner kbd = new Scanner(System.in);
        
        //Add Nonmatching Product Information
        if(numProducts < theInventory.length) {
                       
            if(loc < numProducts)
                System.out.println("Item ID is already in use!"); //to user
            if(loc >= numProducts) {
                
                theInventory[numProducts] = new Furniture( );
                
                theInventory[numProducts].SetItemID(searchItemID);
                theInventory[numProducts].SetItemQuantityAvailable(-1);
                while(theInventory[numProducts].GetItemQuantityAvailable( ) < 0
                    ) {

                    System.out.print("Enter VALID item quantity (No negative "
                        + "item quantities are allowed):  "); //to user
                    theInventory[numProducts].SetItemQuantityAvailable(
                        kbd.nextInt( )); //from user

                }
                theInventory[numProducts].SetItemPrice(-0.01);
                while(theInventory[numProducts].GetItemPrice( ) < 0) {

                    System.out.print("Enter VALID item price (No negative item "
                        + "prices are allowed):  "); //to user
                    theInventory[numProducts].SetItemPrice(kbd.nextDouble( ));
                        //from user

                }
                kbd.nextLine( );
                System.out.print("Enter item note:  "); //to user
                theInventory[numProducts].SetItemNote(kbd.nextLine( )); 
                    //from user
                System.out.println( );
                System.out.print(theInventory[numProducts].GetItemNote( ) + " "
                    + "have been added to the furniture inventory."); //to user
                numProducts++;
            
            }
            
        }
        System.out.println( );
        return numProducts;
    
    }
    
    public static int DeleteProduct(int numProducts, Furniture [ ] 
        theInventory) {
        
        String searchItemID;
        int loc;
        
        //Delete Product
        searchItemID = GetItemID( );
        loc = SearchForMatchingItemID(numProducts, searchItemID, theInventory);
        numProducts = PerformDeletion(loc, numProducts, theInventory);
        
        return numProducts;
        
    }
    
    public static int PerformDeletion(int loc, int numProducts, Furniture [ ] 
        theInventory) {
        
        //Perform Deletion
        if(loc >= numProducts)     
            System.out.println("Item was not found. Try again!"); //to user  
        if(loc < numProducts) {
                      
            System.out.println(theInventory[loc].GetItemNote( ) + " have been "
                + "deleted from the furniture inventory."); //to user
            
            theInventory[loc] = theInventory[numProducts - 1];
            
            theInventory[loc].SetItemID(theInventory[numProducts - 1].GetItemID
                ( ));
            theInventory[loc].SetItemQuantityAvailable(theInventory[
                numProducts - 1].GetItemQuantityAvailable( ));
            theInventory[loc].SetItemPrice(theInventory[numProducts - 1
                ].GetItemPrice( ));
            theInventory[loc].SetItemNote(theInventory[numProducts - 1
                ].GetItemNote( ));
            numProducts--;
            
        }
        return numProducts;
        
    }
    
    public static void IncreaseQuantityOfAProduct(int numProducts, Furniture
        [ ] theInventory) {
        
        String searchItemID;
        int loc;
        
        //Increase Quantity of a Product
        searchItemID = GetItemID( );
        loc = SearchForMatchingItemID(numProducts, searchItemID, theInventory);
        GetItemQuantityToAdd(loc, numProducts, theInventory);
        
    }
    
    public static void GetItemQuantityToAdd(int loc, int numProducts,
        Furniture [ ] theInventory) {
        
        int addItemQuantityAvailable;
        Scanner kbd = new Scanner(System.in);
        
        //Get Item Quantity to Add
        if(loc >= numProducts)     
            System.out.println("Item was not found. Try again!"); //to user    
        if(loc < numProducts) {
    
            addItemQuantityAvailable = -1;
            while(addItemQuantityAvailable < 0) {
                
                System.out.print("Enter the quantity you would like "
                    + "to add to the current quantity of " + theInventory[loc
                        ].GetItemNote( ) + " (Do not enter a negative quantity)"
                            + ":  "); //to user
                addItemQuantityAvailable = kbd.nextInt( ); //from user
                System.out.println( );
                
            }
            theInventory[loc].SetItemQuantityAvailable(theInventory[loc
                ].GetItemQuantityAvailable( ) + addItemQuantityAvailable);
            System.out.print(addItemQuantityAvailable + " " + theInventory[loc
                ].GetItemNote( ) + " has/have been added! Bringing its total "
                    + "item quantity to " + theInventory[loc
                        ].GetItemQuantityAvailable( )); //to user
            System.out.println( );
            
        }
        
    }
    
    public static void DecreaseQuantityOfAProduct(int numProducts, Furniture 
        [ ] theInventory) {
        
        String searchItemID;
        int loc;
        
        //Decrease Quantity of a Product
        searchItemID = GetItemID( );
        loc = SearchForMatchingItemID(numProducts, searchItemID, theInventory);
        GetItemQuantityToRemove(loc, numProducts, theInventory);
       
    }
    
    public static void GetItemQuantityToRemove(int loc, int numProducts, 
        Furniture [ ] theInventory) {
        
        int removeItemQuantityAvailable;
        Scanner kbd = new Scanner(System.in);
