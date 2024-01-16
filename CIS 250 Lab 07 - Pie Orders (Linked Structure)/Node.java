package cis.pkg250.lab.pkg07;

public class Node {
    
    private String name;
    private String address;
    private String cityStateZip;
    private String phoneNumber;
    private int firstPieQuantity;
    private int secondPieQuantity;
    private int thirdPieQuantity;
    private Node next;
    
    public void SetData(String uName, String uAddress, String uCityStateZip, 
        String uPhoneNumber, int uFirstPieQuantity, 
            int uSecondPieQuantity, int uThirdPieQuantity) {
        
        name = uName;
        address = uAddress;
        cityStateZip = uCityStateZip;
        phoneNumber = uPhoneNumber;
        firstPieQuantity = uFirstPieQuantity;
        secondPieQuantity = uSecondPieQuantity;
        thirdPieQuantity = uThirdPieQuantity;
        
    }
    
    public String GetName( ) {
        
        return name;
        
    }
    
    public String GetAddress( ) {
        
        return address;
        
    }
    
    public String GetCityStateZip( ) {
        
        return cityStateZip;
        
    }
    
    public String GetPhoneNumber( ) {
        
        return phoneNumber;
        
    }
    
    public int GetFirstPieQuantity( ) {
        
        return firstPieQuantity;
        
    }
    
    public int GetSecondPieQuantity( ) {
        
        return secondPieQuantity;
        
    }
    
    public int GetThirdPieQuantity( ) {
        
        return thirdPieQuantity;
        
    }
    
    public void SetNextNode(Node newNext) {
        
        next = newNext;
        
    }
    
    public Node GetNextNode( ) {
        
        return next;
        
    }
    
}
