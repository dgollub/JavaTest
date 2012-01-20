package javatests;

/**
 * Some utility functions.
 * 
 */
public class Utils {
    
    public static void print(Object o) {
        System.out.println(o);
    }
    public static void print(String formattext, Object ... args) {
        System.out.println(String.format(formattext, args));
    }
}
