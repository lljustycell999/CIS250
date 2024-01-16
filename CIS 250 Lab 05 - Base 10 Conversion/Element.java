package cis.pkg250.lab.pkg05;

public class Element {
    
    private int remainder;
    
    public void SetRemainder(int uRemainder) {
        
        remainder = uRemainder;
        
    }
    
    public int GetRemainder( ) {
        
        return remainder;
        
    }
    
    public Element Clone( ) {
        
        Element temp;
        
        temp = new Element( );
        temp.SetRemainder(remainder);
        
        return temp;
        
    }

}
