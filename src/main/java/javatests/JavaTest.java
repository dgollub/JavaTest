package javatests;

import javatests.tests.FileSystemTest;
import javatests.tests.InheritanceTest;
import javatests.tests.Java7Tests;
import javatests.tests.monopoly.MonopolyTest;


public class JavaTest {
    
    public static void main(String[] args) {
        Java7Tests.main(args);
        InheritanceTest.main(args);
        MonopolyTest.main(args);
        FileSystemTest.main(args);
    }
    
}
