package cis.pkg250.lab.pkg04;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Monday March 14, 2022
//
//  Project Name:   Laboratory 04   
//
//  Program Description: This program will maintain appointments (or 
//  calenders). The calender information that will be managed include 
//  descriptions, dates/times, durations, priorities, locations, and comments.
//  The user will have menu choices to load a calender (The program will handle
//  any number of calenders at once), save a calender, create a new calender, 
//  add an entry, remove an entry, display an entire calender, display one 
//  entity, and display all entries for a given date.  
// 
//  Project Number:  Project 04

public class CIS250Lab04 {

    public static void main(String[] args) throws IOException {
        
        char userChoice;
        List theList;
        
        //Maintain Calendars
        theList = new List( );
        userChoice = 'M';
        while(userChoice != 'I') {
           
            userChoice = GetChoice( );
            PerformChoice(userChoice, theList);
        
        }
        theList.Destroy( );
        
    }
    
    public static char GetChoice( ) {
        
        char userChoice;
        Scanner kbd = new Scanner(System.in);
        
        //Get Choice
        userChoice = 'N';
        while(userChoice != 'A' && userChoice != 'B' && userChoice != 'C' && 
            userChoice != 'D' && userChoice != 'E' && userChoice != 'F' && 
                userChoice != 'G' && userChoice != 'H' && userChoice != 'I') {
            
            System.out.print("What would you like to do: Load a Calender [A], "
                + "Save a Calender [B], Create a New Calender [C], "
                    + "Add an entry to a calender [D], Remove an entry from a "
                        + "calender [E], Display an entire calender [F], "
                            + "Display one entry from a calender [G], "
                                + "Display all entries for a specifc date from"
                                    + " a calender [H], or Quit [I]?  "); 
                                        //to user
            userChoice = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            
        }
        System.out.println( );
        return userChoice;
        
    }
    
    public static void PerformChoice(char userChoice, List theList) throws 
        IOException {
        
        //Perform Choice
        if(userChoice == 'A')
            LoadCalender(theList);
        if(userChoice == 'B')
            SaveCalender(theList);
        if(userChoice == 'C')
            CreateNewCalender( );
        if(userChoice == 'D')
            AddEntry(theList);
        if(userChoice == 'E')
            RemoveEntry(theList);
        if(userChoice == 'F')
            DisplayEntireCalender(theList);
        if(userChoice == 'G')
            DisplayOneEntry(theList);
        if(userChoice == 'H')
            DisplayAllEntriesForASpecificDate(theList);
        
    }
    
    public static void LoadCalender(List theList) throws IOException {

        String fileName;
        
        //Load Calender
        fileName = GetCalenderFile( );
        LoadCalenderInformation(fileName, theList);
        
    }
    
    public static String GetCalenderFile( ) {
        
        Scanner kbd = new Scanner(System.in);
        String fileName;
        
        //Get Calender File
        System.out.print("Enter the name of the calender file you want:  "); 
            //to user
        fileName = kbd.nextLine( ); //from user
        System.out.println( );
        
        return fileName;
        
    }
    
    public static void LoadCalenderInformation(String fileName, List theList) {
        
        boolean error;
        File calenderFile;
        Scanner calenderFileSC;
        String description;
        String dateTime;
        String duration;
        String priority;
        String location;
        String comment;
        Element anElement;
        int calenderSize;
        
        //Load Calender Information
        calenderSize = 10;
        theList.Create(calenderSize, false);
        calenderFile = new File(fileName);
        error = false;
        try {
            calenderFileSC = new Scanner(calenderFile);
        } catch(Exception e) {
            
            System.out.println("File not found.");
            calenderFileSC = null;
            error = true;
            
        }
        while(!error && calenderFileSC.hasNext( ) && theList.IsFull( ) == 
            false) {
            
            description = calenderFileSC.nextLine( ); //from file
            dateTime = calenderFileSC.nextLine( ); //from file
            duration = calenderFileSC.nextLine( ); //from file
            priority = calenderFileSC.nextLine( ); //from file
            location = calenderFileSC.nextLine( ); //from file
            comment = calenderFileSC.nextLine( ); //from file
            
            anElement = new Element( );
            
            anElement.SetDescription(description);
            anElement.SetDateTime(dateTime);
            anElement.SetDuration(duration);
            anElement.SetPriority(priority);
            anElement.SetLocation(location);
            anElement.SetComment(comment);
            
            theList.Add(anElement);
            
        }
        calenderFileSC.close( );
        
    }
   
    public static void SaveCalender(List theList) throws IOException {
        
        String fileName;
        
        //Save Calender
        fileName = GetCalenderFile( );
        SaveCalenderInformation(fileName, theList);
        
    }
    
    public static void SaveCalenderInformation(String fileName, List theList) 
        throws IOException {
        
        PrintWriter calenderFilePW;
        Element retElement;
        
        //Save Calender Information
        theList.Reset( );
        calenderFilePW = new PrintWriter(fileName);
        while(theList.AtEnd( ) == false) {
            
            retElement = theList.Retrieve( );
            
            calenderFilePW.println(retElement.GetDescription( )); //to file
            calenderFilePW.println(retElement.GetDateTime( )); //to file
            calenderFilePW.println(retElement.GetDuration( )); //to file
            calenderFilePW.println(retElement.GetPriority( )); //to file
            calenderFilePW.println(retElement.GetLocation( )); //to file
            calenderFilePW.println(retElement.GetComment( )); //to file          
            theList.GetNext( );
            
        }
        System.out.println( );
        calenderFilePW.close( );
        
    }
    
    public static void CreateNewCalender( ) throws IOException {
        
        String fileName;
        
        //Create New Calender
        fileName = GetCalenderFile( );
        CreateCalender(fileName);
        
    }
    
    public static void CreateCalender(String fileName) throws IOException {
        
        FileWriter calenderFileFW;
        
        //Create Calender
        calenderFileFW = new FileWriter(fileName, true);
        
    }
    
    public static void AddEntry(List theList) throws IOException {
        
        String description;
        String dateTime;
        String duration;
        String priority;
        String location;
        String comment;
        Element anElement;
        Scanner kbd = new Scanner(System.in);
        
        //Add Entry
        if(theList.IsFull( ) == true)
            System.out.println("No more entries can fit!"); //to user
        if(theList.IsFull( ) == false) {
            
            System.out.print("Enter a description:  "); //to user
            description = kbd.nextLine( ); //from user
            System.out.print("Enter a date or time:  "); //to user
            dateTime = kbd.nextLine( ); //from user
            System.out.print("Enter a duration:  "); //to user
            duration = kbd.nextLine( ); //from user
            System.out.print("Enter a priority:  "); //to user
            priority = kbd.nextLine( ); //from user
            System.out.print("Enter a location:  "); //to user
            location = kbd.nextLine( ); //from user
            System.out.print("Enter a comment:  "); //to user
            comment = kbd.nextLine( ); //from user
            System.out.println( );
        
            anElement = new Element( );
            
            anElement.SetDescription(description);
            anElement.SetDateTime(dateTime);
            anElement.SetDuration(duration);
            anElement.SetPriority(priority);
            anElement.SetLocation(location);
            anElement.SetComment(comment);
            
            theList.Add(anElement);
                
        }
        
    }
    
    public static void RemoveEntry(List theList) {
        
        String entry;
        
        //Remove Entry
        entry = GetEntry( );
        DeleteMatchingEntry(entry, theList);
        
    }
    
    public static String GetEntry( ) {
        
        Scanner kbd = new Scanner(System.in);
        String entry;
        
        //Get Entry
        System.out.print("Enter an entry (description):  "); //to user
        entry = kbd.nextLine( ); //from user
        System.out.println( );
        
        return entry;
        
    }
    
    public static void DeleteMatchingEntry(String entry, List theList) {
        
        //Delete Matching Entry
        theList.Delete(entry);
        
    }
    
    public static void DisplayEntireCalender(List theList) {
        
        //Display Entire Calender
        Element retElement;
        theList.Reset( );
        while(theList.AtEnd( ) == false) {
            
            retElement = theList.Retrieve( );
            
            System.out.println(retElement.GetDescription( )); //to user
            System.out.println(retElement.GetDateTime( )); //to user
            System.out.println(retElement.GetDuration( )); //to user
            System.out.println(retElement.GetPriority( )); //to user
            System.out.println(retElement.GetLocation( )); //to user
            System.out.println(retElement.GetComment( )); //to user
            System.out.println( );
            
            theList.GetNext( );
            
        }
        
    }
   
    public static void DisplayOneEntry(List theList) {
        
        String entry;
        
        //Display One Entry
        entry = GetEntry( );
        SearchForMatchingEntry(entry, theList);
        DisplayEntry(theList);
        
    }
    
    public static void SearchForMatchingEntry(String entry, List theList) {
        
        //Search for Matching Entry
        theList.Search(entry);
        
    }
    
    public static void DisplayEntry(List theList) {
        
        //Display Entry
        Element retElement;
        if(theList.AtEnd( ) == false) {
            
            retElement = theList.Retrieve( );
            
            System.out.println(retElement.GetDescription( )); //to user
            System.out.println(retElement.GetDateTime( )); //to user
            System.out.println(retElement.GetDuration( )); //to user
            System.out.println(retElement.GetPriority( )); //to user
            System.out.println(retElement.GetLocation( )); //to user
            System.out.println(retElement.GetComment( )); //to user
            System.out.println( );
            
        }
        
    }
    
    public static void DisplayAllEntriesForASpecificDate(List theList) {
        
        String userDate;
        
        //Display All Entries for a Specific Date
        userDate = GetDate( );
        DisplayMatchingEntries(userDate, theList);
        
    }
    
    public static String GetDate( ) {
        
        String userDate;
        Scanner kbd = new Scanner(System.in);
        
        //Get Date
        System.out.print("Enter a date:  "); //to user
        userDate = kbd.nextLine( ); //from user
        System.out.println( );
        
        return userDate;
        
    }
    
    public static void DisplayMatchingEntries(String userDate, List theList) {
        
        //Display Matching Entries
        Element retElement;
        theList.Reset( );
        while(theList.AtEnd( ) == false) {
            
            retElement = theList.Retrieve( );
            
            if(userDate.compareToIgnoreCase(retElement.GetDateTime( )) == 0) {
                
                System.out.println(retElement.GetDescription( )); //to user
                System.out.println(retElement.GetDateTime( )); //to user
                System.out.println(retElement.GetDuration( )); //to user
                System.out.println(retElement.GetPriority( )); //to user
                System.out.println(retElement.GetLocation( )); //to user
                System.out.println(retElement.GetComment( )); //to user
                System.out.println( );
                
            }
            theList.GetNext( );
            
        }
        
    }
    
}
