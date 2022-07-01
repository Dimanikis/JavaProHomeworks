package main;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException {
        Films films1 = new Films("Pirates of the Caribbean","Adventure",9,"johnny depp",new Date(2003, Calendar.SEPTEMBER, 21));
        Serializer serializer = new Serializer();
        System.out.println(films1);
        serializer.serialization(films1);


    }
}
