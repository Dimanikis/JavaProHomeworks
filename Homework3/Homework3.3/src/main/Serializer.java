package main;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Serializer {

    public void serialization(Object obj) throws IOException, IllegalAccessException {
        Class<?> cls = obj.getClass();
        OutputStream os = new FileOutputStream("save.txt");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Save.class)){
                if(Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                }
                bos.write(field.getByte(obj));
                os.write(bos.toByteArray());
            }
        }
        os.flush();
        os.close();
    }

}
