package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UserList {
    private static final UserList usrList = new UserList();

    private final Gson gson;
    private final List<User> list = new LinkedList<>();

    public static UserList getInstance() {
        return usrList;
    }

    private UserList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(User u) {
        list.add(u);
    }

    public synchronized boolean contain(User u){
        for (User user : list) {
            if(user.getUsername().equals(u.getUsername()))
            return true;
        }
        return false;
    }

    public synchronized void online(User u){
        for (User user : list) {
            if(user.getUsername().equals(u.getUsername())){
                user.setStatus(true);
                offline(u);
            }
        }
    }

    public synchronized void offline(User u){
       new Timer(true).schedule(new TimerTask() {
                   public void run() {
                       for (User user : list) {
                        if(user.getUsername().equals(u.getUsername()))
                            user.setStatus(false);
                       }
                   }
       },10000);
    }

    public synchronized String toJSON(int n) {
        if (n >= list.size())
            return null;
        return gson.toJson(new JsonUser(list, n));
    }
}
