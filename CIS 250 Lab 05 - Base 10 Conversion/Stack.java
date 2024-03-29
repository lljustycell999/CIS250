package cis.pkg250.lab.pkg05;

public class Stack {
    
    private int top;
    private Element [ ] theElements;
    
    public void Create(int specifiedCapacity) {
        
        theElements = new Element [specifiedCapacity];
        top = 0;
        
    }
    
    public void Destroy( ) {
        
        for(int cnt = 0; cnt < theElements.length; cnt++)
            theElements[cnt] = null;
        
        theElements = null;
        top = -500;
        
    }
    
    public void Push(Element givenElement) {
        
        theElements[top] = givenElement.Clone( );
        top++;
        
    }
    
    public Element Pop( ) {
        
        Element returnElement;
        
        returnElement = theElements[top - 1];
        top--;
        
        return returnElement;
        
    }
    
    public boolean IsEmpty( ) {
        
        boolean empty;
        
        if(top == 0)
            empty = true;
        else
            empty = false;
        
        return empty;
        
    }
    
    public boolean IsFull( ) {
        
        boolean full;
        
        if(top == theElements.length)
            full = true;
        else
            full = false;
        
        return full;
        
    }
        
}
