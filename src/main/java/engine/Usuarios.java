package engine;
 

public class Usuarios {

    private long id;
    private String nome;
    private String bio;
    private long reputacao;


     public Usuarios(){
        this.id=0;
        this.nome="";
        this.bio ="";
        this.reputacao=0;
    }

     public Usuarios(Long id, String nome, String bio, Long reputacao){
        this.id = id;
        this.nome = nome;
        this.bio = bio;
        this.reputacao = reputacao;
    }

    public long getID() {
        return id;
     }
     
    public void setID(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
     }
     
    public void setNome (String nome) {
        this.nome = nome;
    }


    public String getBio() {
        return bio;
     }
     
    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getReputacao() {
        return reputacao;
     }
     
    public void setReputacao(Long reputacao) {
        this.reputacao = reputacao;
    }
    
    public boolean equals(Object obj) {
        
        if (this == obj) return true;
        
        if (obj == null)  return false;
        
        if (getClass() != obj.getClass()) return false;

        final Usuarios other = (Usuarios) obj;
        
        if (this.id != other.id) return false;
        
        return true;
    }
 
     @Override 
     public String toString() {
          return "<ID=" + id + ", Nome=" + nome + ", Bio=" + bio + ", Reputacao=" + reputacao + ">"; 
     }
}