package cis.pkg250.lab.pkg05;

import java.util.Scanner;

//  NAME:           Justyce Countryman
//
//  Due Date:       Wednesday March 23, 2022
//
//  Project Name:   Laboratory 05   
//
//  Program Description: This program will accept two positive integer values
//  from the keyboard and converts the first value (a base 10 number) into a
//  number in the base of the second. The program will process bases from 2 
//  through 16 and the user may process as many numbers as they want. The values
//  of 0-9 will be displayed regularly, but 10-15 will be shown as A-F. 
// 
//  Project Number:  Project 05

public class CIS250Lab05 {

    public static void main(String[] args) {
        
        Stack theStack;
        int num;
        int base;
        char done;
        
        //Convert Base 10 Numbers
        theStack = new Stack( );
        theStack.Create(500);
        done = 'I';
        while(done != 'Y') {
            
            num = GetBase10Number( );
            base = GetBaseNumber( );
            ConvertBase10NumberToBaseNumber(num, base, theStack);
            done = CheckIfDone( );
            
        }
        theStack.Destroy( );
        
    }
    
    public static int GetBase10Number( ) {
        
        int num;
        Scanner kbd = new Scanner(System.in);
        
        //Get Base 10 Number
        num = -5;
        while(num <= 0) {
            
            System.out.print("Enter a base 10 number (Must be positive):  "); 
                //to user
            num = kbd.nextInt( ); //from user
            if(num == 0)
                System.out.println("The base 10 number of 0 is the same as 0 "
                    + "in any other base! Try another number!"); //to user
            
        }
        System.out.println( );
        return num;
               
    }
    
    public static int GetBaseNumber( ) {
        
        int base;
        Scanner kbd = new Scanner(System.in);
        
        //Get Base Number
        base = 0;
        while(base < 2 || base > 16) {
            
            System.out.print("Enter a base number (Must be between 2 and "
                + "16):  "); //to user
            base = kbd.nextInt( ); //from user
            
        }
        System.out.println( );
        return base;
        
    }
    
    public static void ConvertBase10NumberToBaseNumber(int num, int base, 
        Stack theStack) {
        
        int remainder;
        Element anElement;
        
        //Convert Base 10 Number to Base Number
        System.out.print("The base 10 number of " + num + " is the same "
            + "as "); //to user
        while(num != 0 && !theStack.IsFull( )) {
            
            remainder = num % base;
            num = num / base;
            
            anElement = new Element( );
            
            anElement.SetRemainder(remainder);
            
            theStack.Push(anElement);
            
        }
        while(!theStack.IsEmpty( )) {
            
            anElement = theStack.Pop( );
            
            if(anElement.GetRemainder( ) < 10 && anElement.GetRemainder( ) >= 0)
                System.out.print(anElement.GetRemainder( )); //to user
            if(anElement.GetRemainder( ) == 10)
                System.out.print('A'); //to user
            if(anElement.GetRemainder( ) == 11)
                System.out.print('B'); //to user
            if(anElement.GetRemainder( ) == 12)
                System.out.print('C'); //to user
            if(anElement.GetRemainder( ) == 13)
                System.out.print('D'); //to user
            if(anElement.GetRemainder( ) == 14)
                System.out.print('E'); //to user
            if(anElement.GetRemainder( ) == 15)
                System.out.print('F'); //to user
            
        }
        System.out.print(" in base " + base + '.'); //to user
        System.out.println( );
        
    }
    
    public static char CheckIfDone( ) {
        
        char done;
        Scanner kbd = new Scanner(System.in);
        
        //Check if Done
        done = 'Q';
        while(done != 'Y' && done != 'N') {
           
            System.out.print("Are you done converting numbers? (Y/N)?  "); 
                //to user
            done = kbd.nextLine( ).toUpperCase( ).charAt(0); //from user
            
        }
        System.out.println( );
        return done;
        
    }
    
}
