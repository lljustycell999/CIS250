package cis.pkg250.lab.pkg06;

public class Element {
    
    private long entry;
    private long startOfService;
    private long endOfService;
    private char customerType;
    
    public void SetEntry(long uEntry) {
        
        entry = uEntry;
        
    }
    
    public void SetStartOfService(long uStartOfService) {
        
        startOfService = uStartOfService;
        
    }

    public void SetEndOfService(long uEndOfService) {
        
        endOfService = uEndOfService;
        
    }

    public void SetCustomerType(char uCustomerType) {
        
        customerType = uCustomerType;
        
    }
    
    public long GetEntry( ) {
        
        return entry;
        
    }
    
    public long GetStartOfService( ) {
        
        return startOfService;
        
    }
    
    public long GetEndOfService( ) {
        
        return endOfService;
        
    }
    
    public char GetCustomerType( ) {
        
        return customerType;
        
    }
    
    public Element Clone( ) {
        
        Element temp;
        
        temp = new Element( );
        
        temp.SetEntry(entry);
        temp.SetStartOfService(startOfService);
        temp.SetEndOfService(endOfService);
        temp.SetCustomerType(customerType);
        
        return temp;
        
    }
       
}
