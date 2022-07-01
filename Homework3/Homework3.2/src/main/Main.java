package main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        TextConteiner tc = new TextConteiner("TEST");
        Class<TextConteiner> cls = TextConteiner.class;
        String path = null;
        if(cls.isAnnotationPresent(SaveTo.class)){
            SaveTo st = cls.getAnnotation(SaveTo.class);
            path = st.path();
        }
        Method[] methods = cls.getMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(Saver.class)){
                method.invoke(tc, path);
            }
        }

    }
}
