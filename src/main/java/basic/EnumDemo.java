package basic;

/**
 * @author Spark
 */
enum Color{

    RED,GREEN,BLUE;
}
public class EnumDemo {
    public static void main(String[] args) {
        Color red = Color.RED;
        System.out.println(Color.values());
    }
}
