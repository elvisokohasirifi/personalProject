
import java.io.Serializable;

/**
 *
 * @author Sir Elvis
 */
public class Message implements Serializable {
    protected String content, subject;
    
    Message(String title, String body){
        content = title;
        content = body;
    }
    
    public String toString(){
        return ("Subject: " + subject + "\n" + "Body: \n" + content);
    }
}
