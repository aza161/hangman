import java.util.Scanner;
import java.io.*;
public class Hangman
{
    public static void main (String[]args) throws FileNotFoundException
    {
        Scanner input = new Scanner (System.in);
        System.out.println
                ("CMPS201 Hangman!\nI will think of a random word. You'll try to guess its\nletters. Every time you guess a letter that isn't in my\nword, a new body part of the hanging man appears.\nGuess correctly to avoid the gallows!\n");
        String word = "PROGRAMMER";
        System.out.print ("Secret word : ");
        String hint = "";
        String new_hint = "";
        for (int i = 1; i <= word.length (); i++)
        {
            hint += "-";
        }
        System.out.print (hint);
        int num_guesses = 0;
        String guesses = "";
        int guesses_left = 8;
        boolean correct;
        System.out.print ("\nYour guesses: " + "\nGuesses left: " + guesses_left + "\nYour guess? ");
        String guess = input.nextLine ();
        guess = guess.toUpperCase ();
        guesses += guess;
        while (guess.length() > 1){
            System.out.println("\nType a single letter from A-Z.");
        }
     /*   while (guesses.contains(guess)){
            System.out.println("\nYou already guessed that letter.");
        }*/
        while (guess.length() == 1){
        if (word.contains (guess))
        {
            System.out.println ("Correct!");
            num_guesses += 1;
            correct = true;
        }
        else
        {
            System.out.println ("Incorrect!");
            num_guesses += 1;
            guesses_left -= 1;
            correct = false;
        }
        if (correct)
        {
            for(int i = 0; i < hint.length(); i++){
                if(word.charAt(i) == guess.charAt(0)){
                    new_hint += word.charAt(i);
                }else{
                    new_hint += "-";
                }
            }
        }
        }
    }
}
