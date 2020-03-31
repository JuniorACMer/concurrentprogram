package threadpool.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MyUnsafe {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);
    }
}
