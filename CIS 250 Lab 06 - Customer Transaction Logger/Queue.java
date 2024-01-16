package cis.pkg250.lab.pkg06;

public class Queue {
    
    private int front; 
    private int back;
    private Element [ ] theElements;
    
    public void Create(int specifiedCapacity) {

        theElements = new Element [specifiedCapacity + 1];
        front = 0;
        back = 0;
        
    }
    
    public void Destroy( ) {

        for(int cnt = 0; cnt < theElements.length; cnt++)
            theElements[cnt] = null;
        
        theElements = null;
        front = -100;
        back = -200;
        
    }
    
    public void Enqueue(Element givenElement) {

        theElements[back] = givenElement.Clone( );
        back++;
        if(back == theElements.length)
            back = 0;
        
    }
    
    public Element Dequeue( ) {

        Element returnElement;
        
        returnElement = theElements[front];

        front = (front + 1) % theElements.length;
        
        return returnElement;
        
    }
    
    public boolean IsEmpty( ) {
        
        boolean empty;
        
        if(front == back)
            empty = true;
        else
            empty = false;
        
        return empty;
        
    }
    
    public boolean IsFull( ) {
        
        boolean full;
        
        if((back + 1) % theElements.length == front)
            full = true;
        else
            full = false;
        
        return full;
        
    }
         
}
 
