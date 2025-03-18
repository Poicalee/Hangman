import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

            String filepath = "C:\\Users\\Karol\\Desktop\\Java\\Hangman\\src\\words.txt";
            ArrayList<String> words = new ArrayList<>();

            try(BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                String line;
                while((line = reader.readLine()) != null) {
                    words.add(line.trim());
                }
            }
            catch (FileNotFoundException e){
                System.out.println("File not found");
            }
            catch (IOException e){
                System.out.println("I/O exception");
            }

        Random rand = new Random();

        String word = words.get(rand.nextInt(words.size()));

        Scanner sc = new Scanner(System.in);

        ArrayList<Character> wordState = new ArrayList<Character>();
        int wrongGuesses = 0;

        for(int i = 0; i < word.length(); i++){
            wordState.add('_');
        }
        System.out.println("*********************");;
        System.out.println("Welcome to hangman!!");
        System.out.println("*********************");

        System.out.print("Word: ");

        while(wrongGuesses < 8){
            for(char c : wordState){
                System.out.print(c + " ");
            }

            System.out.print("\nGuess a latter: ");
            char guess = sc.next().toLowerCase().charAt(0);

            System.out.println("********************");
            System.out.println("Your guess: " + guess);
            System.out.println("*********************");


            if(word.indexOf(guess) >= 0){
                System.out.println("You guessed correctly!\n");
                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) == guess){
                        wordState.set(i, guess);
                    }
                }
                if(!wordState.contains('_')){
                    System.out.println("*********************");
                    System.out.println(getHangman(wrongGuesses));
                    System.out.println("*********************");
                    System.out.println("You win");
                    System.out.println("The word was: " + word);
                    break;

                }
            }else{
                System.out.println("Wrong guess!\n");
                wrongGuesses++;
                System.out.println("*********************");
                System.out.println(getHangman(wrongGuesses));
                System.out.println("*********************");
            }
        }

        if(wrongGuesses >= 7){
            System.out.println("*********************");
            System.out.println(getHangman(wrongGuesses));
            System.out.println("*********************");
            System.out.println("You lost!\n");
            System.out.println("The word was: " + word);
        }

        sc.close();
        }

        static String getHangman ( int wrongGuesses){

            return switch (wrongGuesses) {
                case 0 -> """
                        
                        
                        
                        
                        """;
                case 1 -> """
                        
                        
                        
                        ___
                        """;
                case 2 -> """
                        |
                        |
                        |
                        |___
                        """;
                case 3 -> """
                         _
                        | o
                        |
                        |
                        |___
                        """;
                case 4 -> """
                         _
                        | o
                        | |
                        |
                        |___
                        """;
                case 5 -> """
                         _
                        | o
                        |/|
                        |
                        |___
                        """;
                case 6 -> """
                         _
                        | o
                        |/|\\
                        |
                        |___
                        """;
                case 7 -> """
                         _
                        | o
                        |/|\\
                        |/ 
                        |___
                        """;
                case 8 -> """
                         _
                        | o
                        |/|\\
                        |/ \\
                        |___
                        """;

                default -> "";
            };
        }
    }
