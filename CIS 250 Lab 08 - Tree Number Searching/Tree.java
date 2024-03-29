package cis.pkg250.lab.pkg08;

import java.io.*;

public class Tree {
    
    private Node root;
    private boolean created;
    private int levelCount;
    private int numbersGiven;
    private int totalLevelCount;
    
    public void GetLevelCountStats( ) {
        
        int averageLevelCount;
        
        averageLevelCount = totalLevelCount / numbersGiven;

        System.out.println("From start to finish, we traversed through " + 
            totalLevelCount + " levels in the tree"); //to user
        System.out.println("On average, we traversed through " + 
            averageLevelCount + " levels in the tree"); //to user
        
    }
    
    public void Delete(String searchValue) {
        
        if(root != null) {

            if(root.GetData( ).GetKey( ).equals(searchValue)) {
                
                root = PerformDeletion(root);
                
            }
            else
                if(root.GetData( ).GetKey( ).compareTo(searchValue) < 0)
                    root.SetLeft(DeleteSubTree(searchValue, root.GetLeft( )));
                else
                    root.SetRight(DeleteSubTree(searchValue, root.GetRight( )));
            
        }
      
    }
    
    private Node DeleteSubTree(String searchValue, Node aRoot) {
        
        if(aRoot != null) {

            if(aRoot.GetData( ).GetKey( ).equals(searchValue))       
                aRoot = PerformDeletion(aRoot);
            
        }
        else {
            
            if(aRoot.GetData( ).GetKey( ).compareTo(searchValue) < 0)
                aRoot.SetLeft(DeleteSubTree(searchValue, aRoot.GetLeft( )));
            else
                aRoot.SetRight(DeleteSubTree(searchValue, aRoot.GetRight( )));    
        
        }
        return aRoot;
            
    }
    
    private Node PerformDeletion(Node aRoot) {
        
        Node temp;
        Element smLargerData;
        
        if(aRoot.GetLeft( ) == null && aRoot.GetRight( ) == null) {
            
            temp = aRoot;
            aRoot = null;
            temp.SetData(null);
            temp = null;
            
        }
        else
            if(aRoot.GetLeft( ) == null) {
                
                temp = aRoot;
                aRoot = aRoot.GetRight( );
                temp.SetData(null);
                temp = null;
                
            }
            else
                if(aRoot.GetRight( ) == null) {
                    
                    temp = aRoot;
                    temp = aRoot.GetLeft( );
                    temp.SetData(null);
                    temp = null;
                    
                }
                else {
                    
                    smLargerData = FindSmallestLarger(aRoot.GetLeft( ));
                    aRoot.SetData(smLargerData);
                    aRoot.SetLeft(DeleteSubTree(smLargerData.GetKey( ), 
                        aRoot.GetLeft( )));
                    
                }
  
        return aRoot;
        
    }
    
    private Element FindSmallestLarger(Node anotherRoot) {
        
        Element smLargerData;
        
        if(anotherRoot.GetRight( ) == null)
            smLargerData = anotherRoot.GetData( ).Clone( );
        else
            smLargerData = FindSmallestLarger(anotherRoot.GetRight( ));
        
        return smLargerData;
        
    }
    
    public Element Retrieve(String searchValue) {

        Element returnData;
        
        levelCount = 0;
        numbersGiven++;
        if(root == null)
            returnData = null;
        else {
            
            levelCount++;
            totalLevelCount++;
            if(searchValue.equals(root.GetData( ).GetKey( )))
                returnData = root.GetData( ).Clone( );
            else
                if(searchValue.compareTo(root.GetData( ).GetKey( )) < 0)
                    returnData = RetrieveSubTree(searchValue, root.GetLeft( ));
                else
                    returnData = RetrieveSubTree(searchValue, root.GetRight( ));
            
        }
        System.out.println("We looked through " + levelCount + " levels in the "
            + "tree"); //to user
        
        return returnData;
        
    }
    
    private Element RetrieveSubTree(String searchValue, Node aRoot) {
        
        Element returnData;
        
        if(aRoot == null)
            returnData = null;
        else {
            
            levelCount++;
            totalLevelCount++;
            if(searchValue.equals(aRoot.GetData( ).GetKey( )))
                returnData = aRoot.GetData( ).Clone( );
            else
                if(searchValue.compareTo(aRoot.GetData( ).GetKey( )) < 0)
                    returnData = RetrieveSubTree(searchValue, aRoot.GetLeft( ));
                else
                    returnData = RetrieveSubTree(searchValue, 
                        aRoot.GetRight( ));
            
        }
        return returnData;
        
    }
    
    public void Save(PrintWriter outPW) {
        
        if(root != null) {
            
            root.GetData( ).SaveElement(outPW);
            
            SaveSubTree(outPW, root.GetLeft( ));
            
            SaveSubTree(outPW, root.GetRight( ));
            
        }
        
    }
    
    private void SaveSubTree(PrintWriter outPW, Node aRoot) {
        
        if(aRoot != null) {
            
            aRoot.GetData( ).SaveElement(outPW);
            
            SaveSubTree(outPW, aRoot.GetLeft( ));
            
            SaveSubTree(outPW, aRoot.GetRight( ));
            
        }
        
    }
    
    public void Display( ) {
        
        if(root != null) {

            root.GetData( ).DisplayElement( );
            
            System.out.println("Going to the left of " + 
                root.GetData( ).GetKey( )); //to user
            DisplaySubTree(root.GetLeft( ));

            System.out.println("Going to the right of " + 
                root.GetData( ).GetKey( )); //to user
            DisplaySubTree(root.GetRight( ));
            
        }
        
    }
    
    private void DisplaySubTree(Node aRoot) {
        
        if(aRoot != null) {
            
            aRoot.GetData( ).DisplayElement( );
            
            System.out.println("Going to the left of " + 
                aRoot.GetData( ).GetKey( )); //to user
            DisplaySubTree(aRoot.GetLeft( ));
            
            System.out.println("Going to the right of " + 
                aRoot.GetData( ).GetKey( )); //to user
            DisplaySubTree(aRoot.GetRight( ));
            
        }
        
    }
    
    public boolean IsEmpty( ) {

        boolean empty;
        
        empty = root == null;
        
        return empty;
        
    }
    
    public boolean IsFull( ) {

        boolean full, memErr;
        Element tElement;
        Node tNode;
        
        tElement = null;
        tNode = null;
        memErr = false;
        full = false;
        try {
            
            tElement = new Element( );
            tNode = new Node( );
            
        } catch(OutOfMemoryError e) {
            
            memErr = true;
            
        }
        if(memErr || tNode == null || tElement == null)
            full = true;
        
        tNode = null;
        tElement = null;
        
        return full;
        
    }
    
    public Tree( ) {

        created = false;
        root = null;
        numbersGiven = 0;
        totalLevelCount = 0;
        
    }
    
    public void Create( ) {

        if(created)
            Destroy( );
        root = null;
        created = true;
        
    }
    
    public void Destroy( ) {
        
    }
    
    public void Add(Element givenElement) {

        Node temp;
        
        if(root == null) {
            
            temp = new Node( );
            temp.SetLeft(null);
            temp.SetRight(null);
            temp.SetData(givenElement.Clone( ));
            root = temp;
            temp = null;
            
        }
        else
            if(givenElement.GetKey( ).compareTo(root.GetData( ).GetKey( )) < 0)
                root.SetLeft(AddNode(givenElement, root.GetLeft( )));
            else
                root.SetRight(AddNode(givenElement, root.GetRight( )));
        
    }
    
    private Node AddNode(Element givenElement, Node aRoot) {
        
        Node temp;
        
        if(aRoot == null) {
            
            temp = new Node( );
            temp.SetLeft(null);
            temp.SetRight(null);
            temp.SetData(givenElement.Clone( ));
            aRoot = temp;
            temp = null;
            
        }
        else
            if(givenElement.GetKey( ).compareTo(aRoot.GetData( ).GetKey( )) < 0)
                aRoot.SetLeft(AddNode(givenElement, aRoot.GetLeft( )));
            else
                aRoot.SetRight(AddNode(givenElement, aRoot.GetRight( )));
        
        return aRoot;
        
    }
    
}
