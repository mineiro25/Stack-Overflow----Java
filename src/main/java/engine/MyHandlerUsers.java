 
package engine;

/**
 *
 * @author Nuno
 * @author Carlos
 */
import java.util.HashMap;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandlerUsers extends DefaultHandler {
    //Lista para guardar o objeto de Posts
    private HashMap<Long,Usuarios> hMapUsers;
    private Usuarios user = null;

    public MyHandlerUsers() {
        hMapUsers = new HashMap<>();
    }
    
    //metodo get para a lista de posts
    public HashMap<Long,Usuarios> getHashMapUsers(){
        return hMapUsers;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException{
        
        if(qName.equalsIgnoreCase("row")){
            //cria um novo objeto Post e poe no num map
            long id;
            if (attributes.getValue("Id")!=null) {
                id = Long.parseLong(attributes.getValue("Id"));
            }else{ 
                id = Long.parseLong("0");
            }
            
            String nome;
            if(attributes.getValue("DisplayName")!=null){
                nome = attributes.getValue("DisplayName");
            }else{
                nome = "0";
            }
            
            String bio;
            if(attributes.getValue("AboutMe")!=null){
                bio = attributes.getValue("AboutMe");
            }else{
                bio = "0";
            }
            
            long reputacao;
            if (attributes.getValue("Reputation")!=null) {
                reputacao = Long.parseLong(attributes.getValue("Reputation"));
            }else{ 
                reputacao = Long.parseLong("0");
            }
            
            //inicializa o objeto user e atribui lhe o atributo id
            user = new Usuarios(id, nome, bio, reputacao);
            hMapUsers.put(user.getID(), user);
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
       if(qName.equalsIgnoreCase("row")){
           //adiciona o objeto Posts na lista
           hMapUsers.put(user.getID(), user);
       } 
    }
}
 