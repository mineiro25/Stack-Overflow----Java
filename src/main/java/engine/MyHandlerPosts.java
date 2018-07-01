 
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

public class MyHandlerPosts extends DefaultHandler {
    //Hash para guardar o objeto de Posts
    private HashMap<Long,Posts> hMapPosts;
    Posts post = new Posts();
    
    public MyHandlerPosts(){
        hMapPosts = new HashMap<>();
    }
    
    //metodo get para a lista de posts
    public HashMap<Long,Posts> getHashMapPosts(){
        return hMapPosts;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException{
        
        if(qName.equalsIgnoreCase("row")){
            //cria um novo objeto Post e poe no num map
            long id;
            if (attributes.getValue("Id")!=null) id = Long.parseLong(attributes.getValue("Id"));
            else id = Long.parseLong("0");
                
            long numAnswers;
            if (attributes.getValue("AnswerCount")!=null) numAnswers = Long.parseLong(attributes.getValue("AnswerCount"));
            else numAnswers = Long.parseLong("0");
            
            long numComments;
            if (attributes.getValue("CommentCount")!=null) numComments = Long.parseLong(attributes.getValue("CommentCount"));
            else numComments = Long.parseLong("0");
            
            String data, dataF;
            if ( attributes.getValue("CreationDate") != null){ 
                dataF = attributes.getValue("CreationDate");
                data = dataF.substring(0, 10);
            }else{
                data = "0";
            }
            
            long parentID;
            if (attributes.getValue("ParentId")!=null) parentID = Long.parseLong(attributes.getValue("ParentId"));
            else parentID = Long.parseLong("0");
            
            long score;
            if (attributes.getValue("Score")!=null) score = Long.parseLong(attributes.getValue("Score"));
            else score = Long.parseLong("0");
            
            String tag;
            if (attributes.getValue("Tags")!=null) tag = attributes.getValue("Tags");
            else tag = "0";
            
            long postType;
            if (attributes.getValue("PostTypeId")!=null) postType = Long.parseLong(attributes.getValue("PostTypeId"));
            else postType = Long.parseLong("0");
            
            String title;
            if (attributes.getValue("Title")!=null) title = attributes.getValue("Title");
            else title = "0";
            
            long userID;
            if (attributes.getValue("OwnerUserId")!=null) userID = Long.parseLong(attributes.getValue("OwnerUserId"));
            else userID = Long.parseLong("0");
            
            post = new Posts(id, title, userID, postType, data, tag, numAnswers, numComments, parentID, score);
            hMapPosts.put(post.getID(), post);
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
       if(qName.equalsIgnoreCase("row")){
           //adiciona o objeto Posts na lista
           post = new Posts();
           hMapPosts.put(post.getID(), post);
       } 
    }
}