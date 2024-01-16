package cis.pkg250.lab.pkg08;

import java.io.*;

public class Element {
    
    private String num;
    
    public void SetNumber(String uNum) {
        
        num = uNum;
        
    }
    
    public String GetNumber( ) {
        
        return num;
        
    }
    
    public String GetKey( ) {
        
        return num;
        
    }
    
    public Element Clone( ) {
        
        Element temp;
        
        temp = new Element( );
        
        temp.SetNumber(num);
        
        return temp;
        
    }
    
    public void DisplayElement( ) {
        
        System.out.printf("%-20s\n", num); //to user
        
    }
    
    public void SaveElement(PrintWriter outPW) {
        
        outPW.printf("%-20s\n", num); //to file
        
    }
    
}
