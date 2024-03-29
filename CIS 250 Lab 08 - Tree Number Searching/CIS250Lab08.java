package cis.pkg250.lab.pkg08;

import java.util.Scanner;
import java.io.*;

//  NAME:           Justyce Countryman
//
//  Due Date:       Wendesday May 11, 2022
//
//  Project Name:   Laboratory 08   
//
//  Program Description: This program will load a file that contains long 
//  integers into a tree structure. The tree will hold up to 200,000 of these 
//  numbers. The user will be allowed to keep searching for numbers in the tree
//  until the user chooses to quit. For each search, the program will display 
//  the number of levels it takes to find the desired number, if the number was
//  found or not, and how many levels were examined before the search ends. 
//  After quitting, the program will calculate and display the average number 
//  of levels before the program ends.
// 
//  Project Number:  Project 08

public class CIS250Lab08 {

    public static void main(String[] args) throws IOException {
        
        Tree theTree;
        char done;
        String userStringNumber;
        
        //Manage Numbers
        theTree = new Tree( );
        theTree.Create( );
        done = 'L';
        LoadNumbers(theTree);
        while(done != 'Y') {
            
            userStringNumber = GetNumberForSearch( );
            PerformSearch(userStringNumber, theTree);
            done = CheckIfDone( );
            
        }
        theTree.GetLevelCountStats( );
        theTree.Destroy( );
        
    }
    
    public static void LoadNumbers(Tree theTree) throws IOException {
        
        File numberFile;
        Scanner numberFileSC;
        int numItems;
        String num;
        Element anElement;
        
        //Load Numbers
        numberFile = new File("string-numbers.txt");
        numberFileSC = new Scanner(numberFile);
        numItems = 0;
        while(numItems < 200000 && numberFileSC.hasNext( )) {
            
            num = numberFileSC.nextLine( ); //from file
            numItems++;
            
            anElement = new Element( );
            
            anElement.SetNumber(num);
            
            theTree.Add(anElement);
                   
        }
        numberFileSC.close( );
        
    }
    
    public static String GetNumberForSearch( ) {
        
        String userStringNumber;
        Scanner kbd = new Scanner(System.in);
        
        //Get Number for Search    
        System.out.print("Enter a string integer to search for:  "); //to user
        userStringNumber = kbd.nextLine( ); //from user
        
        return userStringNumber;
        
    }
    
    public static void PerformSearch(String userStringNumber, Tree theTree) {
        
        Element anElement;
        
        //Perform Search
        anElement = theTree.Retrieve(userStringNumber);
        
        if(anElement == null)
            System.out.println(userStringNumber + " was not in the tree."); 
                //to user
        else
            anElement.DisplayElement( );
        System.out.println( );
        
    }
    
    public static char CheckIfDone( ) {
        
        char done;
        Scanner kbd = new Scanner(System.in);
        
        //Check If Done
        done = 'L';
        while(done != 'Y' && done != 'N') {
            
            System.out.print("Are you done (Y/N):  "); //to user
            done = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            
        }
        System.out.println( );
        return done;
        
    }
    
}
