package main;

import java.io.*;

public class Films implements Serializable {
    @Save
    public String name;
    public String genre;
    public int cost;
    @Save
    private int rating;
    @Save
    private String starring;

    public Films() {
    }

    public Films(String name, String genre,int cost ,int rating, String starring) {
        this.name = name;
        this.genre = genre;
        this.cost = cost;
        this.rating = rating;
        this.starring = starring;
    }

    @Override
    public String toString() {
        return "Films{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", cost=" + cost +
                ", rating=" + rating +
                ", starring='" + starring + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
