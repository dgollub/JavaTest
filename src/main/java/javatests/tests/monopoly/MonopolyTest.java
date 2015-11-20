package javatests.tests.monopoly;

import java.util.ArrayList;
import java.util.List;
import javatests.Utils;

/**
 * Design/model an object hierarchy for the board game
 * Monopoly. 
 * 
 * Additional requirement: 
 * Make it easy to understand for management.
 * 
 * This was an actual question in a job interview.
 * 
 * Of course there are a "gazillion" different ways to 
 * "answer" this kind of question, therefore there can
 * be more than one correct answer.
 * 
 * This is my attempt. From 2011.
 * 
 * In 2015, I may solve it differently. But I am too lazy
 * to revisit this problem any time soon as I have other
 * more fun projects to work on.
 */
public class MonopolyTest {
    
    public static void main(String[] args) {
        Utils.print(String.format("//// START %s ////", MonopolyTest.class.getSimpleName()));
                
        Game game = new Game();        
        game.play(4);
        
        Utils.print(String.format("//// END   %s ////", MonopolyTest.class.getSimpleName()));
    }
    
    public static class Game {
        
        World world; 
        
        public void play(int numPlayers) {
            
            Utils.print("Playing a new game.");
            
            world = new World();
            
            world.initialize(numPlayers);

            world.start();

            while (world.isAlive()) {
                world.process();
            }

            world.cleanup();
            
            Utils.print("Game over");
        }
    }
    
    public static class World {
        
        private final static long MAX_TURNS = 65536;        
        
        private boolean running;        
        private long    turn;
        private Board   board;
        
        public boolean isAlive() { return running; }
        
        public void initialize(int numPlayers) {
            Utils.print("Creating a world for %d players.", numPlayers);  
            running = false;
            turn = 0;
            board = new Board();
        }
        
        public void cleanup() {
            Utils.print("Doing some cleaning in the world.");
            running = false;
            board = null;
            Utils.print("The world ran for %d turn(s).", turn);
        }
        
        public void start() {
            running = true;
            Utils.print("Starting world go round!");
        }
        
        public void process() {
            if (!running)
                return;
            if (turn >= MAX_TURNS) {
                running = false;
                Utils.print("Reached maximum number of turns [%d] for this world.", MAX_TURNS);
                return;
            }
            turn++;
            
        }
        
    }
    
    /**
     * The Board class implements a typical Monopoly board.
     * 
     * Straight from Wikipedia: 
     * http://en.wikipedia.org/wiki/Monopoly_(game)#Board
     * 
     * "The Monopoly game board consists of forty spaces containing twenty-eight properties
     * (twenty-two colored streets, four railway stations and two utilities), three Chance
     * spaces, three Community Chest spaces, a Luxury Tax space, an Income Tax space, and
     * the four corner squares: GO, (In) Jail/Just Visiting, Free Parking, and Go to Jail."
     * 
     */
    public static class Board {                
        
        private List<Field> fields;
        
        public Board() {
            fields = new ArrayList();            
            addFields(Field.FieldType.PROPERTY_STREET, 22);
            addFields(Field.FieldType.PROPERTY_RAILWAY, 4);
            addFields(Field.FieldType.PROPERTY_UTILITY, 2);
            addFields(Field.FieldType.CHANCE, 3);
            addFields(Field.FieldType.CHEST, 3);
            addFields(Field.FieldType.TAX_LUXURY, 1);
            addFields(Field.FieldType.TAX_INCOME, 1);
            addFields(Field.FieldType.GO, 1);
            addFields(Field.FieldType.JAIL, 1);
            addFields(Field.FieldType.GO_TO_JAIL, 1);
            addFields(Field.FieldType.PARKING, 1);
            Utils.print("Added %d fields to the board.", fields.size());
        }
        
        private void addFields(Field.FieldType type, int number) {
            for (int i = 0; i<number; i++) {
                //Utils.print("Adding a new field to the board: %s", type);
                fields.add(new Field(type));
            }
        }
        
        
    }
    
    public static class Field {
        
        public static enum FieldType {
            
            PROPERTY_STREET(1, "Street Property"),
            PROPERTY_RAILWAY(2, "Railway Property"),
            PROPERTY_UTILITY(3, "Utility Property"),
            CHANCE(4, "Chance field"),
            CHEST(5, "Community Chest field"),
            TAX_LUXURY(6, "Luxury Tax"),
            TAX_INCOME(7, "Income Tax"),
            GO(8, "Go (start field)"),
            JAIL(9, "Jail/Visiting"),
            GO_TO_JAIL(10, "Go To Jail"),
            PARKING(11, "Free Parking");
                        
            private final int type;
            private final String name;
            private FieldType(int type, String name) {
                this.type = type;
                this.name = name;
            }
            
            @Override
            public String toString() {
                return name;
            }
        }
        
        private final FieldType type;
        
        public FieldType getType() {
            return type;
        }
        public Field(FieldType type) {
            this.type = type;
        }
                
    }
    
    public static class Piece {
        
    }
    
    public static class PieceList {
        
    }
    
    
}
