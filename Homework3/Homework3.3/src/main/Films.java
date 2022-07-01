package main;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Films implements Serializable {
    @Save
    public String name;
    public String genre;
    @Save
    private int rating;
    private String starring;

    private Date rDate;

    public Films(String name, String genre, int rating, String starring, Date rDate) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.starring = starring;
        this.rDate = rDate;
    }

    @Override
    public String toString() {
        return "Films{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", starring='" + starring + '\'' +
                ", rDate=" + rDate +
                '}';
    }
}
