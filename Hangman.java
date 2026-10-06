package eopproject;
import java.util.Scanner;

public class Hangman {
    
    public static void welcomeMessage(){
        System.out.println("Welcome to Hangman Game!");
        System.out.println("------------------------");
    }
//------------------------------------------------------------------------------    
    public static int storeCategory(){ 
        Scanner input = new Scanner(System.in);
        int choice;
        
        String[] categoryName = new String[4];
        categoryName[0] = "Movie Title";
        categoryName[1] = "Fictional Character";
        categoryName[2] = "Places in Malaysia";
        categoryName[3] = "Proverbs";
        
        System.out.println("\nThere are 4 categories : ");
        
        for(int i=0; i<4; i++)
            System.out.println(i+1 + ". " + categoryName[i]);

        do {
        System.out.print("\nEnter a category (1/2/3/4) : ");
        choice = input.nextInt();
        
        switch(choice){
            case 1 : System.out.println("You have chosen " + categoryName[0]); break;
            case 2 : System.out.println("You have chosen " + categoryName[1]); break;
            case 3 : System.out.println("You have chosen " + categoryName[2]); break;
            case 4 : System.out.println("You have chosen " + categoryName[3]); break;
            default : System.out.println("Invalid category! Try again");
        }
 
        }while(choice < 1 || choice > 4);
        
        return choice;
    }
//------------------------------------------------------------------------------    
    public static void gameStart(int choice){
        Scanner input = new Scanner(System.in);
        String secretWord = "";
        
        System.out.println("\nLets Start!");
        //select random phrase based on category yg user entered
        if(choice == 1){
            
            String[] movieTitle = new String[4];
            movieTitle[0] = "TRAIN TO BUSAN";
            movieTitle[1] = "INSIDE OUT";
            movieTitle[2] = "KPOP DEMON HUNTERS";
            
            int randomIndex = (int)(Math.random() * movieTitle.length);
            secretWord = movieTitle[randomIndex];
        }
        
        else if(choice == 2){
            
            String[] fictional = new String[4];
            fictional[0] = "HARRY POTTER";
            fictional[1] = "MICKY MOUSE";
            fictional[2] = "KAMADO TANJIRO";
            
            int randomIndex = (int)(Math.random() * fictional.length);
            secretWord = fictional[randomIndex];
        }
        
        else if(choice == 3){
            
            String[] places = new String[4];
            places[0] = "CAMERON HIGHLAND";
            places[1] = "KOTA KINABALU";
            places[2] = "SPLASH MANIA";
            
            int randomIndex = (int)(Math.random() * places.length);
            secretWord = places[randomIndex];
        }
        
        else {
            
            String[] proverbs = new String[4];
            proverbs[0] = "PRACTICE MAKES PERFECT";
            proverbs[1] = "ACTIONS SPEAK LOUDER THAN WORDS";
            proverbs[2] = "HONESTY IS THE BEST POLICY";
            
            int randomIndex = (int)(Math.random() * proverbs.length);
            secretWord = proverbs[randomIndex];
        }
        
        //array char sebab nk checking satu2 huruf 
        char[] displayWord = new char[secretWord.length()];
        
        //kalau phrase tu ada space, letak ' ' not '_' untuk index ada space
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == ' ')
                displayWord[i] = ' ';
            else
                displayWord[i] = '_';
        }

        int attempts = 6;
        boolean gameOver = false;

        while (!gameOver && attempts > 0) {

            // display the blank word
            for (int i = 0; i < displayWord.length; i++) {
                System.out.print(displayWord[i] + " "); //lepas '_' letak space ' ' supaya tanak dekat2 macam ni ____
            }

            System.out.println("\nAttempts left: " + attempts);
            System.out.println("1. Guess a letter");
            System.out.println("2. Guess the whole phrase");
            
            int option;
            
            do {
            //user pilih nak guess using letter atau the whole phrase   
            System.out.print("Choose option: ");
            option = input.nextInt();
            input.nextLine(); // clear buffer
            
            if (option < 1 || option > 2)
                System.out.println("Choose to enter letter(1) or phrase(2)!");
            
            }while (option < 1 || option > 2);

            // option 1 : teka huruf
            if (option == 1) {
                System.out.print("Enter a letter: ");
                char guess = input.nextLine().toUpperCase().charAt(0);

                boolean found = false;
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guess) {
                        displayWord[i] = guess;
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("Wrong letter!");
                    attempts--;
                }
            }

            // option 2 : teka phrase
            else if (option == 2) {
                System.out.print("Enter the phrase: ");
                String guessPhrase = input.nextLine().toUpperCase();

                if (guessPhrase.equals(secretWord)) {
                    System.out.println("Correct!^^ Good Game");
                    gameOver = true;
                } else {
                    System.out.println("Wrong phrase!");
                    attempts--;
                }
            }

            // check win
            if (String.valueOf(displayWord).equals(secretWord)) { //to check sama ada string equal/not, cannot use ___ == ___ !! kena guna .equals
                System.out.println("\nYou guessed the word!");
                gameOver = true;
            }
        }
        //kalau kalah(dah tak boleh attempt)
        if (attempts == 0) {
            System.out.println("\nGame Over!");
            System.out.println("The correct phrase was: " + secretWord);
        }

    }    
//------------------------------------------------------------------------------  
    public static void main(String[] args) {
        welcomeMessage();
        int choice = storeCategory();
        gameStart(choice);
    }
}
