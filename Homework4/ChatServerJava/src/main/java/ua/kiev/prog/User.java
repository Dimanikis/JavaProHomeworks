package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class User {
    private String username;
    private boolean Status = true;

    public User(String username) {
        this.username = username;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", Status=" + Status +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

}
