package engine;

/** Classe de comentários*/
public class Comentarios {

    private long id;
    private long userID;
    private String data;
    private long postID;
    private long score;
    private String texto;


     public Comentarios(){
         this.id = 0;
         this.userID = 0;
         this.data = "";
         this.postID = 0;
         this.score = 0;
         this.texto = "";

     }

     public Comentarios (long id, long userID, String data, long postID, long score, String texto)   {
          this.id = id;
          this.userID = userID;
          this.data = data;
          this.postID = postID;
          this.score = score;
          this.texto = texto;
     }


    public long getID() {
        return id;
     }
     
    public void setID(Long id) {
        this.id = id;
    }


    public long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
 
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
 
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) return true;
        
        if (obj == null)  return false;
        
        if (getClass() != obj.getClass()) return false;

        final Comentarios other = (Comentarios) obj;
        
        if (this.id != other.id) return false;
        
        return true;
    }
    /*
    exemplo:  <row Id="2" PostId="4" Score="0" Text="Beat me to it, eh?" CreationDate="2010-09-13T19:21:26.877" UserId="27" />
    */
     @Override 
     public String toString() {
          return "<ID=" + id + ", PostID=" + postID + ", Score=" + score + ", Text=" + texto + ", Date=" + data + ", UserID=" + userID +">";
     }
}