package com.company;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
	Main m1 = new Main();
    Class<?> cls = Main.class;
    Method method = cls.getMethod("test", int.class, int.class);
    Test test = method.getAnnotation(Test.class);
    method.invoke(m1, test.a(),test.b());
    }

    @Test(a=2, b=5)
    public void test(int a, int b){
        System.out.println(a);
        System.out.println(b);
    }

    @Target(ElementType.METHOD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Test {
        int a();
        int b();
    }

}
