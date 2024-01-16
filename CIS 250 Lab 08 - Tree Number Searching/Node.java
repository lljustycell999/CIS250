package cis.pkg250.lab.pkg08;

public class Node {
   
    private Node left, right;
    private Element data;
    
    public void SetLeft(Node uNode) {
        
        left = uNode;
        
    }
    
    public void SetRight(Node uNode) {
        
        right = uNode;
        
    }
    
    public Node GetLeft( ) {
        
        return left;
        
    }
    
    public Node GetRight( ) {
        
        return right;
        
    }
    
    public void SetData(Element uElement) {
        
        data = uElement;
        
    }
    
    public Element GetData( ) {
        
        return data;
        
    }
    
}
