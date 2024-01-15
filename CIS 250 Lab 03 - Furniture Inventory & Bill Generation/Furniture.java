package cis.pkg250.lab.pkg03;

public class Furniture {
    
    private String itemID;
    private int itemQuantityAvailable;
    private double itemPrice;
    private String itemNote;
    
    public void SetItemID(String userItemID) {
       
        itemID = userItemID;
        
    }
    
    public void SetItemQuantityAvailable(int userItemQuantityAvailable) {
        
        itemQuantityAvailable = userItemQuantityAvailable;
        
    }
    
    public void SetItemPrice(double userItemPrice) {
        
        itemPrice = userItemPrice;
        
    }
    
    public void SetItemNote(String userItemNote) {
        
        itemNote = userItemNote;
        
    }
    
    public String GetItemID( ) {
        
        return itemID;
        
    }
    
    public int GetItemQuantityAvailable( ) {
        
        return itemQuantityAvailable;
        
    }
    
    public double GetItemPrice( ) {
        
        return itemPrice;
        
    }
    
    public String GetItemNote( ) {
        
        return itemNote;
        
    }
    
}
