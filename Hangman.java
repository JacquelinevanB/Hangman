import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {
    "+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("\n********** WELCOME TO HANGMAN **********");
        System.out.println(gallows[6]);
        System.out.println("Can you guess the word???");
        System.out.println("Hit enter to start.");
        scan.nextLine();

        int misses = 0;

        char[] missedGuesses = new char[6];      

        // pick the word //
        char[] randomWord = randomWord();
        char[] placeholders = createPlaceholder(randomWord);
        System.out.println(randomWord);
        
        while (true) {
            // print gallow //        
            System.out.println("\n" + gallows[misses]);

            // print placeholders //
            System.out.print("Word:\t");
            printArray(placeholders);
            System.out.println("\n");

            // print missedGuesses //        
            System.out.print("Misses:\t");
            printArray(missedGuesses);
            System.out.println("\n\n");

            // check if the game needs to end
            if (Arrays.equals(randomWord, placeholders)) {
                System.out.println("Well done! You win!");
                System.exit(0);
            } else if (misses >= 6) {
                    System.out.println("Rest in peace...");
                    System.out.print("The word was: ");
                    printArray(randomWord);
                    System.exit(0);
            }

            // ask player for a guess //
            System.out.print("Guess:\t");
            char guess = scan.nextLine().charAt(0);
            System.out.println("\n");

            // check if guess is right //
            if (checkGuess(guess, randomWord)) {
                updatePlaceholders(guess, randomWord, placeholders);
            } else {
                missedGuesses[misses] = guess;
                misses++;
            }
            scan.close();
        }
    }
    
    // generates a random word to play
    public static char[] randomWord() {
        int index = (int) (Math.random() * words.length);
        return (words[index]).toCharArray();
    }

    // generates a new placeholder array with the length of the random word but filled with underscores
    public static char[] createPlaceholder(char[] randomWord) {
        char[] placeholders = new char[randomWord.length];
        for (int i = 0; i < randomWord.length; i++) {
            placeholders[i] = '_';
        }
        return placeholders;
    }

    // the underscore gets replaced by a correct guessed letter
    public static void updatePlaceholders(char guess, char[] randomWord, char[] placeholders) {
        for (int i = 0; i < randomWord.length; i++) {
            if (randomWord[i] == guess) {
                placeholders[i] = guess;
            } 
        }
    }

    // prints an array of characters
    public static void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("\n");
    }

    // checks if the guessed letter matches the word
    public static boolean checkGuess(char guess, char[] randomWord) {
        for (int i = 0; i < randomWord.length; i++) {
            if (guess == randomWord[i]) {
                return true;
            }                        
        }
        return false;
    }
}
 