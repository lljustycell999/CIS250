package cis.pkg250.lab.pkg02;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday February 18, 2022
//
//  Project Name:   Laboratory 02   
//
//  Program Description: This program will manage pie orders. The program is
//  menu-driven and will allow the user to add or remove item quantities for 
//  specific pies to or from a customer order. With either option, the program 
//  will request a valid product number and quantity. Then, the program will 
//  ask if the user is done creating the customer order. If the user is not 
//  done, the program will keep asking for valid product numbers and quantities
//  to add or remove until the user is done. Once done, an order summary 
//  reciept will be displayed that includes the charges of each desired pie, 
//  the subtotal, the tax, and the total due. After that, all quantities will 
//  be reset back to zero for the next customer order. Finally, the user will 
//  be asked to either start another customer order or quit the program. As a 
//  result, the user can process as many customers as desired. Error handling 
//  for product number and quantity input will be applied. 
// 
//  Project Number:  Project 02

public class CIS250Lab02 {

    public static void main(String[] args) throws IOException {
        
        Pies [ ] theOrder;
        
        //Manage Orders
        theOrder = new Pies [5];
        
        LoadItems(theOrder);
        ProcessEveryOrder(theOrder);
        
    }
    
    public static void LoadItems(Pies [ ] theOrder) throws IOException {
        
        int numPies;
        File productFile;
        Scanner productFileSC;
        
        //Load Items
        numPies = 0;
        productFile = new File("pieInventory.txt");
        productFileSC = new Scanner(productFile);
        while(numPies < theOrder.length && productFileSC.hasNext( )) {
            
            theOrder[numPies] = new Pies( );
            
            theOrder[numPies].LoadInfo(productFile, productFileSC);
            numPies++;
              
        }
        productFileSC.close( );
        
    }
    
    public static void ProcessEveryOrder(Pies [ ] theOrder) {
        
        char userChoice;
        
        //Process Every Order
        userChoice = 'Z';
        while(userChoice != 'Q') {
            
            ProcessAnOrder(theOrder);
            ProduceReciept(theOrder);
            ResetQuantities(theOrder);
            userChoice = GetUserChoice( );
                    
        }
        
    }
    
    public static void ProcessAnOrder(Pies [ ] theOrder) {
        
        char orderDone;
        char orderChoice;
        int itemNumber;
        int quantity;
        
        //Process an Order
        orderDone = 'G';
        while(orderDone != 'Y') {
            
            orderChoice = GetChoice( );
            itemNumber = GetItemNumber( );
            quantity = GetQuantity( );
            if(orderChoice == 'A')
                theOrder[itemNumber].IncreaseQuantity(quantity);
            else
                theOrder[itemNumber].DecreaseQuantity(quantity);
            orderDone = GetOrderDone( );
            
        }
        
    }
    
    public static char GetChoice( ) {
        
        char orderChoice;
        Scanner kbd = new Scanner(System.in);
        
        //Get Choice
        orderChoice = 'P';
        while(orderChoice != 'A' && orderChoice != 'R') {
            
            System.out.print("What would you like to do: Add an item quantity"
                + " to the customer order [A] or Remove an item quantity from "
                    + "the customer order [R]?  "); //to user
            orderChoice = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            
        }
        System.out.println( );
        return orderChoice;
        
    }
    
    public static int GetItemNumber( ) {
        
        int itemNumber;
        Scanner kbd = new Scanner(System.in);
        
        //Get Item Number
        itemNumber = 9;
        while(itemNumber != 0 && itemNumber != 1 && itemNumber != 2 && 
            itemNumber != 3 && itemNumber != 4) {
            
            System.out.print("Enter a VALID item number (0-4):  "); //to user
            itemNumber = kbd.nextInt( ); //from user
            
        }
        return itemNumber;
       
    }
    
    public static int GetQuantity( ) {
        
        int quantity;
        Scanner kbd = new Scanner(System.in);
