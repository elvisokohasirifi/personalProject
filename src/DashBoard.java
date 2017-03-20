import java.util.*;
import java.io.*;
public class DashBoard {
    protected static HashMap<String, User> users = new HashMap<>();
    protected static User currentUser = null;
    
    public static void save(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.bin"));
            oos.writeObject(users);
            oos.close();
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    public static void load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.bin"));
            users = (HashMap<String, User>) ois.readObject();
            ois.close();
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    public static void main(String args[]){
        save();
    }
}
