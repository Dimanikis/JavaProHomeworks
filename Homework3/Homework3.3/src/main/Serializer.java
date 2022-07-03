package main;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;

public class Serializer {


    public void serialization(Object obj) throws IOException, IllegalAccessException {
        FileOutputStream fos = new FileOutputStream("save.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Save.class)){
                if(Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                }
                ByteBuffer buffer = ByteBuffer.allocate(4);
                if(field.getType() == String.class){
                    buffer.putInt((field.get(obj)).toString().getBytes().length);
                    oos.write(buffer.array());
                    buffer = ByteBuffer.wrap((field.get(obj)).toString().getBytes());
                } else {
                    buffer.putInt((Integer) field.get(obj));
                }
                oos.write(buffer.array());
            }
        }
        oos.close();
    }

    public void deserialization(Object obj) throws IOException, IllegalAccessException {
        FileInputStream fis = new FileInputStream("save.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Save.class)){
                if(Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                }
                if(field.getType() == String.class){
                    field.set(obj, new String(ois.readNBytes(ByteBuffer.wrap(ois.readNBytes(4)).getInt())));
                } else {
                    field.set(obj, ByteBuffer.allocate(4).put(ois.readNBytes(4)).rewind().getInt());
                }
            }
        }
        ois.close();
    }

}