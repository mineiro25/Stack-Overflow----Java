package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler; //para cirar os handlers
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser  {

    public HashMap<Long,Posts> postsParser(String dump) throws ParserConfigurationException, SAXException{
        HashMap<Long,Posts> resultado = new HashMap<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try{            
            SAXParser saxParser =  factory.newSAXParser();
            
            MyHandlerPosts handlerP = new MyHandlerPosts();
 
            saxParser.parse(dump+"Posts.xml", handlerP);
            resultado = handlerP.getHashMapPosts();

            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }

    public HashMap<Long,Usuarios> usersParser(String dump) throws ParserConfigurationException, SAXException{
        HashMap<Long,Usuarios> resultado = new HashMap<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try{            
            SAXParser saxParser =  factory.newSAXParser();
            
            MyHandlerUsers handlerU = new MyHandlerUsers();
 
            saxParser.parse(dump+"Users.xml", handlerU);
            resultado = handlerU.getHashMapUsers();
 
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }    
    
    public HashMap<Long,Comentarios> commentsParser(String dump) throws ParserConfigurationException, SAXException{
        HashMap<Long,Comentarios> resultado = new HashMap<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        
        try{
            SAXParser saxParser = factory.newSAXParser();
            
            MyHandlerComments handlerC = new MyHandlerComments();
            
            saxParser.parse(dump+"Comments.xml", handlerC);
            resultado = handlerC.getHashMapComments();
            
        }catch(ParserConfigurationException | SAXException |IOException ex){
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return resultado;
    }
    
    public HashMap<Long,Tags> tagsParser(String dump) throws ParserConfigurationException, SAXException{
        HashMap<Long,Tags> resultado = new HashMap<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try{            
            SAXParser saxParser =  factory.newSAXParser();
            
            MyHandlerTags handlerT = new MyHandlerTags();
 
            saxParser.parse(dump+"Tags.xml", handlerT);
            resultado = handlerT.getHashMapTags();

            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }
}
