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
    protected List<Message> inbox;
    protected List<Message> sent;
    protected List<String> friendRequests;
    protected String status;

    User(String uName,String uPassword,String uUsername,String mail,String g, String pic){
        name = uName;
        password = uPassword;
        username = uUsername;
        email = mail;
        gender = Gender.valueOf(g);
        friends = new HashMap<>();
        profilePic = pic;
        status = "";
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
        return true;
    }
    
    protected int numFriends(){
        return friends.size();
    }
    
    protected boolean deleteFriend(String username){
        friends.remove(username);
        return true;
    }
    
    protected boolean sendMessage(Message message, User...receipients){
        for(User i: receipients)
            i.inbox.add(message);
        sent.add(message);
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
                return true;
            }
        }
        return false;
    }
    
    public void sendFriendRequest(String username){
        DashBoard.users.get(username).friendRequests.add(this.username);
    }
}
