package cis.pkg250.lab.pkg04;

public class Element {
    
    private String description;
    private String dateTime;
    private String duration;
    private String priority;
    private String location;
    private String comment;
    
    public void SetDescription(String userDescription) {
        
        description = userDescription;
        
    }
    
    public void SetDateTime(String userDateTime) {
        
        dateTime = userDateTime;
        
    }
    
    public void SetDuration(String userDuration) {
        
        duration = userDuration;
        
    }
    
    public void SetPriority(String userPriority) {
        
        priority = userPriority;
        
    }
    
    public void SetLocation(String userLocation) {
        
        location = userLocation;
        
    }
    
    public void SetComment(String userComment) {
        
        comment = userComment;
        
    }
    
    public String GetDescription( ) {
        
        return description;
    }
    
    public String GetDateTime( ) {
        
        return dateTime;
        
    }
    
    public String GetDuration( ) {
        
        return duration;
        
    }
    
    public String GetPriority( ) {
        
        return priority;
        
    }
    
    public String GetLocation( ) {
        
        return location;
        
    }
    
    public String GetComment( ) {
        
        return comment;
        
    }
    
    public Element Clone( ) {
        
        Element temp;
        
        temp = new Element( );
        
        temp.SetDescription(description);
        temp.SetDateTime(dateTime);
        temp.SetDuration(duration);
        temp.SetPriority(priority);
        temp.SetLocation(location);
        temp.SetComment(comment);
        
        return temp;
        
    }
    
    public String GetKey( ) {
        
        return description;
        
    }
    
}
