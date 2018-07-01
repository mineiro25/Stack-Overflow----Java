 
package engine;

/**
 *
 * @author Nuno
 * @author Carlos
 * 
 */
import java.util.HashMap;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandlerComments extends DefaultHandler {
    //Lista para guardar o objeto de Comentarios
    private HashMap<Long,Comentarios> hMapComments;
    private Comentarios com = null;

    public MyHandlerComments() {
        hMapComments = new HashMap<>();
    }
    
    //metodo get para a lista de coms
    public HashMap<Long,Comentarios> getHashMapComments(){
        return hMapComments;
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
            
            long userID;
            if (attributes.getValue("UserId")!=null) {
                userID = Long.parseLong(attributes.getValue("UserId"));
            }else{ 
                userID = Long.parseLong("0");
            }
            
            String data, dataF;
            if ( attributes.getValue("CreationDate") != null){ 
                dataF = attributes.getValue("CreationDate");
                data = dataF.substring(0, 10);
            }else{
                data = "0";
            }
            
            long postID;
            if (attributes.getValue("PostId")!=null) {
                postID = Long.parseLong(attributes.getValue("PostId"));
            }else{ 
                postID = Long.parseLong("0");
            }
            
            long score;
            if (attributes.getValue("Score")!=null) {
                score = Long.parseLong(attributes.getValue("Score"));
            }else{ 
                score = Long.parseLong("0");
            }
            
            String texto;
            if(attributes.getValue("Text")!=null){
                texto = attributes.getValue("Text");
            }else{
                texto = "0";
            }
            
            //inicializa o objeto Post e atribui lhe o atributo id
            com = new Comentarios(id, userID, data, postID, score, texto);
            hMapComments.put(com.getID(), com);
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
       if(qName.equalsIgnoreCase("row")){
           //adiciona o objeto Comentarios na lista
           com = new Comentarios();
           hMapComments.put(com.getID(), com);
       } 
    }
}