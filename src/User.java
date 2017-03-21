
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author Sir Elvis
 */
public class User implements Serializable{
    protected enum Gender{Male, Female};
    protected String username, password, name, email, profilePic;
    Gender gender;
    protected HashMap<String, User> friends;
    protected ArrayList<Message> inbox;
    protected ArrayList<Message> sent;
    protected ArrayList<String> friendRequests;
    protected String status;
    protected LinkedList<Message> all;
    
    protected void save(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.username + ".bin"));
            oos.writeObject(friends);
            oos.writeObject(inbox);
            oos.writeObject(sent);
            oos.writeObject(friendRequests);
            oos.close();
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    protected void load(){
        try{
            File file = new File(this.username + ".bin");
            if(file.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                friends = (HashMap<String, User>) ois.readObject();
                inbox = (ArrayList<Message>) ois.readObject();
                sent = (ArrayList<Message>) ois.readObject();
                friendRequests = (ArrayList<String>) ois.readObject();
                ois.close();
            }
            
        }
        catch(Exception e){e.printStackTrace();}
    }

    User(String uName,String uPassword,String uUsername,String mail,String g, String pic){
        name = uName;
        password = uPassword;
        username = uUsername;
        email = mail;
        gender = Gender.valueOf(g);
        friends = new HashMap<>();
        profilePic = pic;
        status = "";
        all = new LinkedList<>();
        inbox = new ArrayList<>();
        sent = new ArrayList<>();
        friendRequests = new ArrayList<>();
        save();
    }
    
    public static boolean signUp(String uName,String uPassword,String uUsername,String mail,String g, String pic){
        if(DashBoard.users.containsKey(uUsername))
            return false;
        DashBoard.users.put(uUsername, new User(uName, uPassword, uUsername, mail, g, pic));
        DashBoard.save();
        return true;
    }
    
    protected boolean addFriend(User friend){
        if (friends.containsKey(friend.username))
            return false;
        friends.put(friend.username, friend);
        save();
        return true;
    }
    
    protected int numFriends(){
        return friends.size();
    }
    
    protected boolean deleteFriend(String username){
        friends.remove(username);
        save();
        return true;
    }
    
    protected boolean sendMessage(Message message, User...receipients){
        for(User i: receipients)
            i.inbox.add(message);
        sent.add(message);
        save();
        return true;
    }
    
    protected String viewSent(){
        StringBuilder s = new StringBuilder();
        for(Message i: sent)
            s.append(i.toString());
        return s.toString();
    }
    
    protected String viewReceived(){
        StringBuilder s = new StringBuilder();
        for(Message i: inbox)
            s.append(i.toString());
        return s.toString();
    }
    
    public static boolean logIn(String username, String password){
        if(DashBoard.users.containsKey(username)){
            User user = DashBoard.users.get(username);
            if(user.password.equals(password)){
                DashBoard.currentUser = user;
                DashBoard.currentUser.load();
                return true;
            }
        }
        return false;
    }
    
    public void sendFriendRequest(String username){
        DashBoard.users.get(username).friendRequests.add(this.username);
        save();
        DashBoard.users.get(username).save();
    }
    
    public void confirmRequest(String username){
        this.friendRequests.remove(username);
        this.friends.put(username, DashBoard.users.get(username));
        DashBoard.users.get(username).addFriend(this);
    }
}
