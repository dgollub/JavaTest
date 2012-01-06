package javatests;

import java.util.ArrayList;
//import java.util.


public class JavaTests {

    public static void print(Object o) {
        System.out.println(o);
    }
    
    public static interface Mamal {
        public void useBrain();
    }
    
    public static class Animal {
        public void speak() {
            print("Hello, I am an animal.");
        }
    }
    
    public static class Elephant extends Animal implements Mamal {
        @Override
        public void speak() {
            print("Moo, I am an Elephant.");
        }
        @Override
        public void useBrain() {
            print("Elephant using its brain.");
        }
    }
    public static class Dog extends Animal implements Mamal {
        @Override
        public void speak() {
            print("Bark, I am a dog.");
        }
        @Override
        public void useBrain() {
            print("Dog using its brain.");
        }
    }
    
    public static void main(String[] args) {
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
    }
    
    
    
}
