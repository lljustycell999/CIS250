package cis.pkg250.lab.pkg06;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Monday April 11, 2022
//
//  Project Name:   Laboratory 06   
//
//  Program Description: This program will log transactions for cashiers. 
//  Customers will be lined up via two physical queues to be serviced by one 
//  of several cashiers, with one line for VIP customers and the other for 
//  everybody else. The user can signal customer arrivals,  
//  the availability/non-availability of cashiers, and the end of the business 
//  day. The program will automatically log customer arrival time, the start of
//  their service with a cashier, and the time that the transaction is 
//  completed. Throughout program execution, customer transaction information 
//  will be logged to a file and processed later. At the end of the program, 
//  the average wait time and service times for all VIP customers, all other 
//  customers, and all customers will be displayed.
// 
//  Project Number:  Project 06

public class CIS250Lab06 {

    public static void main(String[ ] args) throws IOException {
        
        Queue regularQueue;
        Queue vipQueue;
        CashRegister [ ] aCashRegister;

        //Generate Cashier Stats
        regularQueue = new Queue( );
        vipQueue = new Queue( );
        aCashRegister = new CashRegister [5];
        regularQueue.Create(50);
        vipQueue.Create(50);
        ProcessEachCustomer(regularQueue, vipQueue, aCashRegister);
        DisplayStats( );
        regularQueue.Destroy( );
        vipQueue.Destroy( );
        
    }
    
    public static char ProcessEachCustomer(Queue regularQueue, Queue vipQueue,
        CashRegister [ ] aCashRegister) throws IOException {
        
        char userChoice;
        char storeStatus;
        int loc;
        
        //Process Each Customer
        storeStatus = 'L';
        loc = 0;
        for(int cnt = 0; cnt < aCashRegister.length; cnt++) {
            
            aCashRegister[cnt] = new CashRegister( );
            aCashRegister[cnt].SetClosed( );
         
        }
        while(storeStatus != 'C' || (!regularQueue.IsEmpty( ) && 
            !vipQueue.IsEmpty( )) || loc < aCashRegister.length) {
            
            userChoice = GetChoice( );
            storeStatus = PerformChoice(userChoice, regularQueue, vipQueue, 
                aCashRegister, storeStatus);
            loc = 0;
            while(loc < aCashRegister.length && aCashRegister[loc].GetStatus( ) 
                != 'B')
                loc++;
            if(!regularQueue.IsEmpty( ) || !vipQueue.IsEmpty( ))
                System.out.println("Note: You may not close the restaurant "
                    + "until all customers have been served!"); //to user
            
        }
        return storeStatus;
        
    }
    
    public static char GetChoice( ) {
        
        char userChoice;
        Scanner kbd = new Scanner(System.in);
        
        //Get Choice
        userChoice = 'L';
        while(userChoice != 'A' && userChoice != 'B' && userChoice != 'C' && 
            userChoice != 'D' && userChoice != 'E' && userChoice != 'F' 
                && userChoice != 'G') {
            
            System.out.print("What would you like to do: Open the store [A], "
                + "Close the store [B], Open a register [C], Close a register "
                    + "[D], Indicate that a customer has entered [E], Indicate "
                        + "that a customer is being served [F], or Indicate "
                            + "that a customer has been served [G]?  "); 
                                //to user
            userChoice = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            
        }
        System.out.println( );
        return userChoice;
        
    }
    
    public static char PerformChoice(char userChoice, Queue regularQueue, 
        Queue vipQueue, CashRegister [ ] aCashRegister, char storeStatus) 
            throws IOException {
        
        //Perform Choice
        if(userChoice == 'A' && storeStatus == 'O')
            System.out.println("You already opened The Sandwich Shop!"); 
                //to user
        if(userChoice == 'A' && storeStatus != 'O')
            storeStatus = OpenStore( );
        if(userChoice == 'B' && storeStatus != 'O')
            System.out.println("How about opening the store first before you "
                + "close it?"); //to user
        if(userChoice == 'B' && storeStatus == 'O' && regularQueue.IsEmpty( ) 
            && vipQueue.IsEmpty( ))
            storeStatus = CloseStore(aCashRegister);
        if(userChoice == 'C')
            OpenRegister(aCashRegister);
        if(userChoice == 'D')
            CloseRegister(aCashRegister);
        if((userChoice == 'E' || userChoice == 'F' || userChoice == 'G') && 
            storeStatus != 'O')
            System.out.println("You need to open the store before you can "
                + "process customers."); //to user
        if(userChoice == 'E' && storeStatus == 'O')
            CustomerEnters(regularQueue, vipQueue);
        if(userChoice == 'F' && storeStatus == 'O')
            CustomerServiceStarts(regularQueue, vipQueue, aCashRegister);
        if(userChoice == 'G' && storeStatus == 'O')
            CustomerEndsService(aCashRegister);
        
        return storeStatus;
        
    }
    
    public static char OpenStore( ) {
        
        char storeStatus;
        
        //Open Store
        storeStatus = 'O';
        System.out.println("The Sandwich Shop is open for business!"); 
            //to user
        
        return storeStatus;
        
    }
    
    public static char CloseStore(CashRegister [ ] aCashRegister) {
        
        char storeStatus;
        int loc;
        
        //Close Store
        loc = 0;
        storeStatus = 'O';
        while(loc < aCashRegister.length && aCashRegister[loc].GetStatus( ) 
            != 'B')
            loc++;
        if(loc < aCashRegister.length) 
            System.out.println("You may not close the restaurant "
                + "until all registers are no longer busy!"); //to user
        else {
            
            storeStatus = 'C';
            System.out.println("The Sandwich Shop is now closed! Here are the "
                + "stats (in milliseconds): "); //to user
            System.out.println( );
            
        }
        return storeStatus;
        
    }
    
    public static void OpenRegister(CashRegister [ ] aCashRegister) {
        
        int cashRegisterNumber;
        Scanner kbd = new Scanner(System.in);
         
        //Open Register
        cashRegisterNumber = 7;
        while(cashRegisterNumber != 0 && cashRegisterNumber != 1 && 
            cashRegisterNumber != 2 && cashRegisterNumber != 3 && 
                cashRegisterNumber != 4) {
            
            System.out.print("Enter a cash register to open (0-4):  "); 
                //to user
            cashRegisterNumber = kbd.nextInt( ); //from user
          
        }
        System.out.println( );
        if(aCashRegister[cashRegisterNumber].GetStatus( ) == 'C') {
            
            aCashRegister[cashRegisterNumber].SetOpen( );
            System.out.println("Cash Register " + cashRegisterNumber + " "
                + "is now open!"); //to user
            
        }
        else
            if(aCashRegister[cashRegisterNumber].GetStatus( ) == 'O')
                System.out.println("Cash Register " + cashRegisterNumber + " "
                    + "is already open!"); //to user
            else
                System.out.println("You may not open cash register " + 
                    cashRegisterNumber + " because it is busy right now!"); 
                        //to user
        
    }
    
    public static void CloseRegister(CashRegister [ ] aCashRegister ) {
        
        int cashRegisterNumber;
        Scanner kbd = new Scanner(System.in);
         
        //Close Register
        cashRegisterNumber = 7;
        while(cashRegisterNumber != 0 && cashRegisterNumber != 1 && 
            cashRegisterNumber != 2 && cashRegisterNumber != 3 && 
                cashRegisterNumber != 4) {
            
            System.out.print("Enter a cash register to close (0-4):  "); 
                //to user
            cashRegisterNumber = kbd.nextInt( ); //from user
          
        }
        System.out.println( );
        if(aCashRegister[cashRegisterNumber].GetStatus( ) == 'O') {
            
            aCashRegister[cashRegisterNumber].SetClosed( );
            System.out.println("Cash Register " + cashRegisterNumber + " "
                + "is now closed!"); //to user
        
        }
        else
            if(aCashRegister[cashRegisterNumber].GetStatus( ) == 'C')
                System.out.println("Cash Register " + cashRegisterNumber + " "
                    + "is already closed!"); //to user
            else
                System.out.println("You may not close cash register " + 
                    cashRegisterNumber + " because it is busy right now!"); 
                        //to user
        
    }
    
    public static void CustomerEnters(Queue regularQueue, Queue vipQueue) 
        throws IOException {
        
        Element anElement;
        Scanner kbd = new Scanner(System.in);
        char customerType;
        
        //Customer Enters
        customerType = 'L';
        if(!regularQueue.IsFull( ) || !vipQueue.IsFull( )) {
           
            anElement = new Element( );
        
            anElement.SetEntry(System.currentTimeMillis( ));
            
            while(customerType != 'R' && customerType != 'V') {
                
                System.out.print("Enter the type of customer: (R)egular or "
                    + "(V)ip? "); //to user
                customerType = kbd.nextLine( ).toUpperCase( ).charAt(0); 
                    //from user
            
            }
            System.out.println( );
            if(!regularQueue.IsFull( ) && customerType == 'R') {
                
                anElement.SetCustomerType(customerType);
                regularQueue.Enqueue(anElement);
                
            }
            if(regularQueue.IsFull( ) && customerType == 'R')
                System.out.println("Unable to put customer in the regular line "
                    + "because it is full!"); //to user
            if(!vipQueue.IsFull( ) && customerType == 'V') {
                
                anElement.SetCustomerType(customerType);
                vipQueue.Enqueue(anElement);
                
            }
            if(vipQueue.IsFull( ) && customerType == 'V')
                System.out.println("Unable to put customer in the VIP line "
                    + "because it is full!"); //to user
            
        }
              
    }
    
    public static void CustomerServiceStarts(Queue regularQueue, 
        Queue vipQueue, CashRegister [ ] aCashRegister) throws IOException {
        
        int loc;
        Element anElement;
        
        //Customer Service Starts
        loc = 0;
        if(!regularQueue.IsEmpty( ) || !vipQueue.IsEmpty( )) {
             
            while(loc < aCashRegister.length && aCashRegister[loc].GetStatus( )
                != 'O')
                loc++;
            if(loc < aCashRegister.length) {
                
                if(!vipQueue.IsEmpty( )) {
                    
                    anElement = vipQueue.Dequeue( );
                    anElement.SetStartOfService(System.currentTimeMillis( ));              
                    aCashRegister[loc].SetBusy( );
                    aCashRegister[loc].StartService(anElement);
                    System.out.println("Cash Register " + loc + " is now busy "
                        + "with a VIP customer!" ); //to user
                    
                }
                else {
                    
                    anElement = regularQueue.Dequeue( );
                    anElement.SetStartOfService(System.currentTimeMillis( ));              
                    aCashRegister[loc].SetBusy( );
                    aCashRegister[loc].StartService(anElement);
                    System.out.println("Cash Register " + loc + " is now busy "
                        + "with a regular customer!" ); //to user
                    
                }
                   
            }
            else
                System.out.println("There are no cash registers open yet!"); 
                    //to user
            
        }
        else
            System.out.println("There is nobody in either line yet!"); //to user
    
    }
    
    public static void CustomerEndsService(CashRegister [ ] aCashRegister) 
        throws IOException {
        
        Scanner kbd = new Scanner(System.in);
        int cashRegisterNumber;
        Element anElement;
        PrintWriter customerFilePW;
        FileWriter customerFileFW;
        
        //Customer Ends Service
        cashRegisterNumber = 7;    
        while(cashRegisterNumber != 0 && cashRegisterNumber != 1 && 
            cashRegisterNumber != 2 && cashRegisterNumber != 3 && 
                cashRegisterNumber != 4) {

            System.out.print("Enter the register that completed the service"
                + " (0-4):  "); //to user
            cashRegisterNumber = kbd.nextInt( ); //from user

        }
        System.out.println( );
        if(aCashRegister[cashRegisterNumber].GetStatus( ) == 'B') {

            aCashRegister[cashRegisterNumber].SetOpen( );

            anElement = aCashRegister[cashRegisterNumber].EndService( );
            anElement.SetEndOfService(System.currentTimeMillis( ));

            if(anElement.GetCustomerType( ) == 'V') {

                customerFileFW = new FileWriter("VIP.txt", true);
                customerFilePW = new PrintWriter(customerFileFW);
                customerFilePW.println(anElement.GetEntry( )); //to file
                customerFilePW.println(anElement.GetStartOfService( )); 
                    //to file
                customerFilePW.println(anElement.GetEndOfService( )); 
                    //to file
                customerFilePW.close( );

            }
            else {

                customerFileFW = new FileWriter("Regular.txt", true);
                customerFilePW = new PrintWriter(customerFileFW);
                customerFilePW.println(anElement.GetEntry( )); //to file
                customerFilePW.println(anElement.GetStartOfService( )); 
                    //to file
                customerFilePW.println(anElement.GetEndOfService( )); 
                    //to file
                customerFilePW.close( );

            }

        }
        else
            System.out.println("The cash register you entered was not "
                + "even busy!"); //to user
        
    }
    
    public static void DisplayStats( ) throws IOException {
       
        File customerFile;
        Scanner customerFileSC;
        long entry;
        long startService;
        long endService;
        long totalWaitTimeVIP;
        long totalWaitTimeRegular;
        long totalWaitTimeVIPAvg;
        long totalWaitTimeRegularAvg;
        long totalWaitTimeAll;
        long totalWaitTimeAllAvg;
        long totalServiceTimeVIP;
        long totalServiceTimeRegular;
        long totalServiceTimeVIPAvg;
        long totalServiceTimeRegularAvg;
        long totalServiceTimeAll;
        long totalServiceTimeAllAvg;        
        int numVIPs;
        int numRegulars;
        int numCustomers;
        
        //Display Stats
        customerFile = new File("VIP.txt");
        customerFileSC = new Scanner(customerFile);
        numVIPs = 0;
        numRegulars = 0;
        numCustomers = 0;
        totalWaitTimeVIP = 0;
        totalWaitTimeRegular = 0;
        totalServiceTimeVIP = 0;
        totalServiceTimeRegular = 0;
        totalWaitTimeVIPAvg = 0;
        totalServiceTimeVIPAvg = 0;
        totalWaitTimeRegularAvg = 0;
        totalServiceTimeRegularAvg = 0;
        totalWaitTimeAllAvg = 0;
        totalServiceTimeAllAvg = 0;
        while(customerFileSC.hasNext( )) {
            
            entry = customerFileSC.nextLong( ); //from file
            startService = customerFileSC.nextLong( ); //from file
            endService = customerFileSC.nextLong( ); //from file
            totalWaitTimeVIP = totalWaitTimeVIP + startService - entry;
            totalServiceTimeVIP = totalServiceTimeVIP + endService - 
                startService;
            numVIPs++;
            numCustomers++;
                   
        }
        if(numVIPs != 0) {
            
            totalWaitTimeVIPAvg = totalWaitTimeVIP / numVIPs;
            totalServiceTimeVIPAvg = totalServiceTimeVIP / numVIPs;
            
        }
        customerFile = new File("Regular.txt");
        customerFileSC = new Scanner(customerFile);
        while(customerFileSC.hasNext( )) {
            
            entry = customerFileSC.nextLong( ); //from file
            startService = customerFileSC.nextLong( ); //from file
            endService = customerFileSC.nextLong( ); //from file
            totalWaitTimeRegular = totalWaitTimeRegular + startService - entry;
            totalServiceTimeRegular = totalServiceTimeRegular + endService - 
                startService;   
            numRegulars++;
            numCustomers++;
            
        }
        if(numRegulars != 0) {
            
            totalWaitTimeRegularAvg = totalWaitTimeRegular / numRegulars;
            totalServiceTimeRegularAvg = totalServiceTimeRegular / numRegulars;
            
        }
        totalWaitTimeAll = totalWaitTimeVIP + totalWaitTimeRegular;
        totalServiceTimeAll = totalServiceTimeVIP + totalServiceTimeRegular;
        if(numCustomers != 0) {
           
            totalWaitTimeAllAvg = totalWaitTimeAll / numCustomers;
            totalServiceTimeAllAvg = totalServiceTimeAll / numCustomers;
        
        }
        System.out.printf("%43s%34s%26s\n", "VIP Customers", 
            "Regular Customers", "All Customers"); //to user
        System.out.printf("%-30s%-30d%-30d%-30d\n", "Average Wait Time: ", 
            totalWaitTimeVIPAvg, totalWaitTimeRegularAvg, totalWaitTimeAllAvg);
                //to user
        System.out.printf("%-30s%-30d%-30d%-30d\n", "Average Service Time: ", 
            totalServiceTimeVIPAvg, totalServiceTimeRegularAvg, 
                totalServiceTimeAllAvg); //to user
        
    }
    
}
