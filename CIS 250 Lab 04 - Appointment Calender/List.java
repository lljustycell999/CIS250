package cis.pkg250.lab.pkg04;

public class List {
    
    private Element [ ] theElements;
    private int numItems;
    private int current;
    private boolean ordered;
    
    public void Destroy( ) {

        for(int cnt = 0; cnt < theElements.length; cnt++)
            theElements[cnt] = null;
        
        theElements = null;
        numItems = -100;
        current = -1;
        
    }
    
    public boolean IsEmpty( ) {

        boolean empty;
        
        if(numItems == 0)
            empty = true;
        else
            empty = false;
        
        return empty;
        
    }
    
    public Element Retrieve( ) {

        Element returnedElement;
        
        if(AtEnd( ) != true)
            returnedElement =  theElements[current].Clone( );
        else
            returnedElement =  null;
        
        return returnedElement;
        
    }
    
    public void GetNext( ) {
        
        if(AtEnd( ) == false)
            current++;
        
    }
    
    public void Reset( ) {

        current = 0;
        
    }
    
    public boolean AtEnd( ) {

        boolean end;
        
        end = (current == numItems);
        
        return end;
        
    }
    
    public boolean IsFull( ) {

        boolean full;
        
        if(numItems == theElements.length)
            full = true;
        else
            full = false;
        
        return full;
        
    }
    
    public void Create(int capacity, boolean uOrdered) {
        
        theElements = new Element[capacity];
        numItems = 0;
        current = 0;
        ordered = uOrdered;
        
    }
    
    public void Add(Element givenElement) {
        
        if(ordered == false)
            UnAdd(givenElement);
        else
            Insert(givenElement);
        
    }
    
    private void UnAdd(Element givenElement) {
        
        theElements[numItems] = givenElement.Clone( );
        numItems++;
        
    }
    
    private void Insert(Element givenElement) {

        Search(givenElement.GetKey( ));
        
        for(int cnt = numItems; cnt > current; cnt--)
            theElements[cnt] = theElements[cnt - 1];
        
        theElements[current] = givenElement.Clone( );
        numItems++;
        
    }
    
    public boolean Delete(String searchValue) {

        boolean found;
        
        found = Search(searchValue);
        
        if(found && !ordered)
            UnDel( );
        else
            if(found)
                Del( );
        
        return found;
        
    }
    
    private void Del( ) {
        
        for(int cnt = current; cnt < numItems - 1; cnt++)
            theElements[cnt] = theElements[cnt + 1];
        
        numItems--;
        theElements[numItems] = null;
        
    }
    
    private void UnDel( ) {
        
        theElements[current] = theElements[numItems - 1];
        numItems--;
        theElements[numItems] = null;
        
    }
    
    public boolean Search(String searchValue) {

        boolean found;
        
        if(ordered == false)
            found = UnSearch(searchValue);
        else
            found = OrSearch(searchValue);
        
        return found;
        
    }
    
    private boolean OrSearch(String searchValue) {
        
        boolean found;
        
        Reset( );
        while(!AtEnd( ) && 
          searchValue.compareToIgnoreCase(theElements[current].GetKey( )) > 0)
                    GetNext( );
        
        if(!AtEnd( ) && 
                searchValue.equalsIgnoreCase(theElements[current].GetKey( )))
            found = true;
        else
            found = false;
        
        return found;
        
    }
    
    private boolean UnSearch(String searchValue) {
        
        boolean found;

        Reset( );
        while(!AtEnd( ) && 
          searchValue.compareToIgnoreCase(theElements[current].GetKey( )) != 0)
                    GetNext( );
        
        if(!AtEnd( ))
            found = true;
        else
            found = false;
        
        return found;
        
    }
    
}
