package cis.pkg250.lab.pkg02;

import java.util.Scanner;
import java.io.*;

public class Pies {
    
    private String description;
    private int quantity;
    private double price;
    
    public void LoadInfo(File productFile, Scanner productFileSC) 
        throws IOException {
        
        description = productFileSC.nextLine( ); //from file
        price = productFileSC.nextDouble( ); //from file
        productFileSC.nextLine( );
        
    }
   
    public void ResetQuantity( ) {
        
        quantity = 0;
        
    }
    
    public String GetDescription( ) {
        
        return description;
        
    }
    
    public int GetQuantity( ) {
        
        return quantity;
        
    }
    
    public double GetPrice( ) {
        
        return price;
        
    }
    
    public void IncreaseQuantity(int addedQuantity) {
        
        quantity = quantity + addedQuantity;
        
    }
    
    public void DecreaseQuantity(int decreasedQuantity) {
        
        quantity = quantity - decreasedQuantity;
        
    }
    
    public double GetExtendedCost( ) {
        
        double extendedCost;
        
        extendedCost = quantity * price;
        
        return extendedCost;
        
    }
    
    public void DisplayProductItemSummary( ) {
               
        if(quantity > 0) 
            System.out.printf("%-20d%-20s%-20.2f%-20.2f\n", quantity, 
                description, price, GetExtendedCost( )); //to user
 
    }
    
}
