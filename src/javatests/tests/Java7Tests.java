package javatests.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javatests.Utils;

/**
 * Test some Java 7 stuff.
 */
public class Java7Tests {
 
    private static class Person {
        
        public static class NameNotSetException extends Exception {};
        public static class NameIsEmptyException extends Exception {};
        
        private final String name;
        public Person(String name) {
            this.name = name;
        }
        public Person() {
            this.name = null;
        }
        public String getName() { return this.name; }
        @Override
        public String toString() { return this.name; }
        
        public int calculateAge() throws NameNotSetException, NameIsEmptyException {
            if (name == null)
                throw new NameNotSetException();
            if (name.trim().length() == 0)
                throw new NameIsEmptyException();
            return name.length();            
        }
        
    }
    
    public static void main(String[] args) {
        Utils.print(String.format("//// START %s ////", Java7Tests.class.getSimpleName()));
        
        testNullHandling();
        testTryWithRessources();
        testExceptionHandling();
        
        Utils.print(String.format("//// END   %s ////", Java7Tests.class.getSimpleName()));
    }
    //http://www.javacodegeeks.com/2011/07/java-7-try-with-resources-explained.html
    //http://blog.sanaulla.info/2011/07/10/java-7-project-coin-try-with-resources-explained-with-examples/
    private static void testTryWithRessources() {
        File file = new File("input.txt");
        Utils.print("Trying to read file %s", file.getAbsolutePath());
        try (InputStream is = new FileInputStream(file)) {
            int c;
            do {
                c = is.read();
                Utils.print("Data: %d", c);
            } while(c > 0);
        }
        catch (IOException ex) {
            Utils.print("Missing file %s", file.getAbsolutePath());
        }
    }
    //http://docs.oracle.com/javase/7/docs/technotes/guides/language/catch-multiple.html
    private static void testExceptionHandling() {        
        List<Person> list = new ArrayList();
        list.add(new Person("Daniel"));
        list.add(new Person());
        list.add(new Person(""));
        
        for (Person p : list) {
            try {
                Utils.print("Person %s is %d years old.", p, p.calculateAge());
            } catch (Person.NameIsEmptyException|Person.NameNotSetException ex) {
                Utils.print("Person threw an exception: %s", ex.getClass().getSimpleName());
            }
        }
        
    }    
    //http://blogs.oracle.com/darcy/entry/project_coin_final_five 
    //Elvis and Other Null-Safe Operators
    private static void testNullHandling() {
        //Person a = new Person();
        //Person b = new Person("Alfred");;
        //
        //Utils.print("A: %s", a?.getName());
        //Utils.print("B: %s", b?.getName()); 
        Utils.print("Elvis Null Safe stuff does not work with Java 7, yet.");
    }
}
