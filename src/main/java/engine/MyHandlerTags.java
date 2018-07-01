 
package engine;

import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Nuno
 * @author Carlos
 * 
 */
public class MyHandlerTags extends DefaultHandler{
    //Hash para guardar o objeto de Tags
    private HashMap<Long,Tags> hMapTags;
    Tags tag = new Tags();

    public MyHandlerTags() {
        hMapTags = new HashMap<>();
    }
    
    //metodo get para o hash de tags
    public HashMap<Long,Tags> getHashMapTags(){
        return hMapTags;
    }
    
    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException{
        
        if(qName.equalsIgnoreCase("row")){
            long id;
            if(attributes.getValue("Id")!=null){
                id = Long.parseLong(attributes.getValue("Id"));
            }else{
                id = Long.parseLong("0");
            }
            
            long count;
            if(attributes.getValue("Count")!=null){
                count = Long.parseLong(attributes.getValue("Count"));
            }else{
                count = Long.parseLong("0");
            }
            
            String tagName;
            if(attributes.getValue("TagName")!=null){
                tagName = attributes.getValue("TagName");
            }else{
                tagName = "0";
            }
            
            tag = new Tags(id, tagName, count);
            hMapTags.put(tag.getId(),tag);
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        if(qName.equalsIgnoreCase("row")){
            //adiciona o objeto Tag na hash
            tag = new Tags();
            hMapTags.put(tag.getId(), tag);
        }
    }
}
