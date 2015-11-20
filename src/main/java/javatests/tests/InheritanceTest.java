package javatests.tests;

import java.util.ArrayList;
import javatests.Utils;

/**
 * Test some aspects of inheritance in Java.
 * 
 * This was an actual test question in the technical exam for a job interview.
 * 
 */
public class InheritanceTest {
    
    public static interface Mamal {
        public void useBrain();
    }
    
    public static class Animal {
        public void speak() {
            Utils.print("Hello, I am an animal.");
        }
    }
    
    public static class Elephant extends Animal implements Mamal {
        @Override
        public void speak() {
            Utils.print("Moo, I am an Elephant.");
        }
        @Override
        public void useBrain() {
            Utils.print("Elephant using its brain.");
        }
    }
    public static class Dog extends Animal implements Mamal {
        @Override
        public void speak() {
            Utils.print("Bark, I am a dog.");
        }
        @Override
        public void useBrain() {
            Utils.print("Dog using its brain.");
        }
    }
    
    public static void main(String[] args) {
        
        Utils.print(String.format("//// START %s ////", InheritanceTest.class.getSimpleName()));
        
        Animal a    = new Animal();
        Elephant b  = new Elephant();
        Dog c       = new Dog();
        
        ArrayList<Animal> array = new ArrayList<Animal>();
        array.add(a);
        array.add(b);
        array.add(c);
        
        for (Animal x : array) {
            x.speak();
            if (x instanceof Mamal) {
                ((Mamal)x).useBrain();
            }
        } 
        
        Utils.print(String.format("//// END   %s ////", InheritanceTest.class.getSimpleName()));
    }
}
