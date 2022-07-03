package main;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException {
        Films films1 = new Films("Pirates of the Caribbean","Adventure",100,9,"johnny depp");
        Films films2 = new Films();
        Serializer serializer = new Serializer();
        System.out.println(films1);
        serializer.serialization(films1);
        System.out.println(films2);
        serializer.deserialization(films2);
        System.out.println(films2);


    }
}
