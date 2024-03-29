package cis.pkg250.lab.pkg07;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Friday April 29, 2022
//
//  Project Name:   Laboratory 07   
//
//  Program Description: This program manage bill orders for pies. For each 
//  sale the user puts in, the program will ask for a name, street address, 
//  city/state/zip, phone number, and quantity for three different types of 
//  pie. The program will have menu choices to load orders from a 
//  user-specified file, save orders to a user-specified file, empty orders 
//  from memory to start fresh, adding an order, deleting an order, 
//  displaying bills for all orders, and quitting the program. 
// 
//  Project Number:  Project 07

public class CIS250Lab07 {

    public static void main(String[] args) throws IOException {
        
        char userChoice;
        Node start;
        
        //Manage Bill Orders
        userChoice = 'M';
        start = null;
        while(userChoice != 'Q') {
            
            userChoice = GetChoice( );
            start = PerformChoice(userChoice, start);
            
        }
            
    }
    
    public static char GetChoice( )  {
        
        char userChoice;
        Scanner kbd = new Scanner(System.in);
        
        //Perform Each Choice
        userChoice = 'M';
        while(userChoice != 'A' && userChoice != 'B' && userChoice != 'C' && 
            userChoice != 'D' && userChoice != 'E' && userChoice != 'F' && 
                userChoice != 'Q') {
            
            System.out.print("What would you like to do: Load orders [A], "
                + "Save orders [B], Add an order [C], Delete an order [D], "
                    + "Empty all orders [E], Display all orders [F], or Quit "
                        + "[Q]?  "); //to user
            userChoice = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            
        }
        System.out.println( );
        return userChoice;
        
    }
    
    public static Node PerformChoice(char userChoice, Node start) 
        throws IOException {
        
        //Perform Choice
        if(userChoice == 'A')
            start = LoadOrders( );
        if(userChoice == 'B')
            SaveOrders(start);
        if(userChoice == 'C')
            start = AddOrder(start);
        if(userChoice == 'D')
            start = DeleteOrder(start);
        if(userChoice == 'E')
            start = EmptyOrders(start);
        if(userChoice == 'F')
            DisplayOrders(start);
        
        return start;
        
    }
    
    public static Node LoadOrders( ) throws IOException {
        
        File pieFile;
        Scanner pieFileSC;
        Scanner kbd = new Scanner(System.in);
        String fileName;
        String name;
        String address;
        String cityStateZip;
        String phoneNumber;
        int firstPieQuantity;
        int secondPieQuantity;
        int thirdPieQuantity;
        Node start;
        Node temp;
        
        //Load Orders
        start = null;
        System.out.print("Enter the file name:  "); //to user
        fileName = kbd.nextLine( ); //from user
        pieFile = new File(fileName);
        pieFileSC = new Scanner(pieFile);
        
        while(pieFileSC.hasNext( )) {
            
            name = pieFileSC.nextLine( ); //from file
            address = pieFileSC.nextLine( ); //from file
            cityStateZip = pieFileSC.nextLine( ); //from file
            phoneNumber = pieFileSC.nextLine( ); //from file
            firstPieQuantity = pieFileSC.nextInt( ); //from file
            secondPieQuantity = pieFileSC.nextInt( ); //from file
            thirdPieQuantity = pieFileSC.nextInt( ); //from file
            pieFileSC.nextLine( );
            
            temp = new Node( );
            
            temp.SetData(name, address, cityStateZip, phoneNumber, 
                firstPieQuantity, secondPieQuantity, thirdPieQuantity);
            temp.SetNextNode(start);
            start = temp;
            temp = null;
             
        }
        //pieFileSC.close( );
        
        return start;
        
    }
    
    public static void SaveOrders(Node start) throws IOException {
        
        PrintWriter pieFilePW;
        String fileName;
        Node current;
        Scanner kbd = new Scanner(System.in);
        
        //Save Orders
        System.out.print("Enter the file name:  "); //to user
        fileName = kbd.nextLine( ); //from user
        current = start;
        pieFilePW = new PrintWriter(fileName);
        while(current != null) {
            
            pieFilePW.println(current.GetName( )); //to file
            pieFilePW.println(current.GetAddress( )); //to file
            pieFilePW.println(current.GetCityStateZip( )); //to file
            pieFilePW.println(current.GetPhoneNumber( )); //to file
            pieFilePW.println(current.GetFirstPieQuantity( )); //to file
            pieFilePW.println(current.GetSecondPieQuantity( )); //to file
            pieFilePW.println(current.GetThirdPieQuantity( )); //to file
            
            current = current.GetNextNode( );
            
        }
        pieFilePW.close( );
        
    }
    
    public static Node AddOrder(Node start) {
        
        String name;
        String address;
        String cityStateZip;
        String phoneNumber;
        int firstPieQuantity;
        int secondPieQuantity;
        int thirdPieQuantity;
        Scanner kbd = new Scanner(System.in);
        Node temp;
        
        //Add Order
        firstPieQuantity = -1;
        secondPieQuantity = -2;
        thirdPieQuantity = -3;
        System.out.println("Enter the following (hit enter/return after each "
            + "one): Your name, address, city/state/zip and phone number:  "); 
                //to user   
        name = kbd.nextLine( ); //from user
        address = kbd.nextLine( ); //from user
        cityStateZip = kbd.nextLine( ); //from user
        phoneNumber = kbd.nextLine( ); //from user
        while(firstPieQuantity < 0) {
            
            System.out.print("Now, enter a POSITIVE quantity of raison pies:  "
                + ""); //to user
            firstPieQuantity = kbd.nextInt( ); //from user
        
        }
        while(secondPieQuantity < 0) {
            
            System.out.print("Enter a POSITIVE quantity of Twinkie® pies:  "); 
                //to user
            secondPieQuantity = kbd.nextInt( ); //from user
        
        }
        while(thirdPieQuantity < 0) {
            
            System.out.print("Finally, enter a POSITIVE quantity of nacho pies:"
                + "  "); //to user
            thirdPieQuantity = kbd.nextInt( ); //from user
        
        }
        temp = new Node( );
        
        temp.SetData(name, address, cityStateZip, phoneNumber, 
            firstPieQuantity, secondPieQuantity, thirdPieQuantity);
        temp.SetNextNode(start);
        start = temp;
        temp = null;
        
        return start;
        
    }
    
    public static Node DeleteOrder(Node start) {
        
        String searchPhoneNumber;
        Scanner kbd = new Scanner(System.in);
        Node current;
        Node previous;
        
        //Delete Order
        current = start;
        previous = null;
        System.out.print("To delete a person's order, please enter their phone "
            + "number:  "); //to user
        searchPhoneNumber = kbd.nextLine( ); //from user
        while(current != null && searchPhoneNumber.compareToIgnoreCase(
            current.GetPhoneNumber( )) != 0) {
            
            previous = current;
            current = current.GetNextNode( );
        
        }
        if(current == null)
            System.out.println("Unable to find anybody with that phone "
                + "number!"); //to user
        else {
            
            if(start == current) {
                
                start = current.GetNextNode( );
                current.SetData(null, null, null, null, 0, 0, 0);
                current.SetNextNode(null);
                current = start;
                
            }
            else {
                
                previous.SetNextNode(current.GetNextNode( ));
                current.SetData(null, null, null, null, 0, 0, 0);
                current.SetNextNode(null);
                current = previous.GetNextNode( );
                
            }
        
        }
        return start;
        
    }
    
    public static Node EmptyOrders(Node start) {
        
        Node current;
        Node previous;
        
        //Empty Orders
        current = start;
        previous = null;
        while(current != null) {
                
                start = current.GetNextNode( );
                current.SetData(null, null, null, null, 0, 0, 0);
                current.SetNextNode(null);
                current = start;
                              
        }
        return start;
        
    }
    
    public static void DisplayOrders(Node start) {
        
        Node current;
        
        //Display Orders
        current = start;
        System.out.printf("%-40s%-40s%-40s%-30s%-30s%-30s%-30s\n", "Name", 
            "Street Address", "City/State/Zip", "Phone Number", "Quantity of "
                + "raison pies", "Quantity of Twinkie® pies", "Quantity of "
                    + "nacho pies"); //to user
        while(current != null) {
            
            System.out.printf("%-40s%-40s%-40s%-30s%-30d%-30d%-30d\n", 
                current.GetName( ), current.GetAddress( ), 
                    current.GetCityStateZip( ), current.GetPhoneNumber( ), 
                        current.GetFirstPieQuantity( ), 
                            current.GetSecondPieQuantity( ), 
                                current.GetThirdPieQuantity( )); //to user
            current = current.GetNextNode( );
            
        }
        System.out.println( );
        
    }
    
}
