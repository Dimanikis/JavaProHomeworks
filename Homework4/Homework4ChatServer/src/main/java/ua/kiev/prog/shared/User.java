package ua.kiev.prog.shared;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.ZoneId;



public class User {

    @Id
    private int id;
    private String username;
    private LocalDate lastOnline = LocalDate.now(ZoneId.of("Europe/Kiev"));

    private int mc;
    @Ignore
    private boolean status = false;

    public User(){

    }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(LocalDate lastOnline) {
        this.lastOnline = lastOnline;
    }

    public int getMc() {
        return mc;
    }

    public void setMc(int mc) {
        this.mc = mc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Client{" +
                (id == 0?"":("id = " + id + ", ")) +
                (username == null?"":("username = '" + username + '\'' + ", " ))+
                (lastOnline == null?"":("last online = " + lastOnline)) +
                (mc == 0?"":("message count = " + mc)) +
                '}';
    }
}
