/**
 *
 * @author Sir Elvis
 */
public class Message {
    protected String content, subject;
    
    Message(String title, String body){
        content = title;
        content = body;
    }
    
    public String toString(){
        return ("Subject: " + subject + "\n" + "Body: \n" + content);
    }
}
