package engine;

public class Posts {

    private long id;
    private String title;
    private long userID;
    private long tipo;
    private String data;
    private String tag;
    private long numAnswers;
    private long numComments;
    private long parentID;
    private long score;


     public Posts(){
         this.id=0;
         this.title="";
         this.userID=0;
         this.tipo=0;
         this.data="";
         this.tag="";
         this.numAnswers=0;
         this.numComments=0;
         this.parentID=0;
         this.score=0;
     }

     public Posts(Long id, String title, Long userID, Long tipo, String data, String tag, Long numAnswers, Long numComments, long parentID, Long score){
        this.id=id;
        this.title=title;
        this.userID=userID;
        this.tipo=tipo;
        this.data=data;
        this.tag=tag;
        this.numAnswers=numAnswers;
        this.numComments=numComments;
        this.parentID=parentID;
        this.score=score;
    }



    public long getID() {
        return id;
     }
     
    public void setID(Long id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
     }
     
    public void setTitle(String title) {
        this.title = title;
    }



    public long getUserID() {
        return userID;
     }
     
    public void setUserID(Long userID) {
        this.userID = userID;
    }



    public long getTipo() {
        return tipo;
     }
     
    public void setTipo (Long tipo) {
        this.tipo = tipo;
    }



    public String getData() {
        return data;
     }
     
    public void setData(String data) {
        this.data = data;
    }



    public String getTag() {
        return tag;
     }
     
    public void setTag(String tag) {
        this.tag = tag;
    }



    public long getNumAnswers() {
        return numAnswers;
     }
     
    public void setNumAnswers (Long numAnswers) {
        this.numAnswers = numAnswers;
    }



    public long getNumComments() {
        return numComments;
     }
     
    public void setNumComments (Long numComments) {
        this.numComments = numComments;
    }



    public long getParentID() {
        return parentID;
     }
     
    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    

    public long getScore() {
        return score;
     }
     
    public void setScore(Long score) {
        this.score = score;
    }
 

    
    public boolean equals(Object obj) {
        
        if (this == obj) return true;
        
        if (obj == null)  return false;
        
        if (getClass() != obj.getClass()) return false;

        final Posts other = (Posts) obj;
        
        if (this.id != other.id) return false;
        
        return true;
    }
 
     @Override 
     public String toString() {
          return "<ID=" + id + ", Title=" + title + ", UserID=" + userID + ", Tipo=" + tipo + ", Data=" + data + ", Tags=" + tag + ", NumAnswers=" + numAnswers + ", NumComments=" + numComments + ", ParentID=" + parentID + ", Score=" + score + ">";
     }
}