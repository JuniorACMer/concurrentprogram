package basic;

/**
 * @author Spark
 */
enum Color1{
    RED,
    GREEN,
    WRITE;
}
public class EnumDemo02 {
    public static void main(String[] args) {
        for(Color color : Color.values()){
            System.out.println(color.name()+""+color.ordinal());
        }
    }
}
