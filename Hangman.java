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
        Scanner file = new Scanner(new File("res/display8.txt"));
        while (file.hasNext()){
            System.out.println(file.nextLine());}
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
        String guess = input.nextLine();
        guess = guess.toUpperCase ();
        do{
        if (guess.length() > 1 || !Character.isLetter(guess.charAt(0))){
            System.out.print("Type a single letter from A-Z.\nYour guess? ");
            guess = input.nextLine();
            guess = guess.toUpperCase ();
        }
        if (guesses.contains(guess)){
            System.out.print("You already guessed that letter.\nYour guess? ");
            guess = input.nextLine();
            guess = guess.toUpperCase ();
        }
            //guesses += guess;

        if (word.contains (guess))
        {
            System.out.println ("Correct!\n");
            num_guesses += 1;
            correct = true;
        }
        else
        {
            System.out.println ("Incorrect!\n");
            num_guesses += 1;
            guesses_left -= 1;
            correct = false;
        }
        if (guess.length() == 1 && Character.isLetter(guess.charAt(0))){
            guesses += guess;
        if (correct)
        {   new_hint = "";
            for(int i = 0; i < hint.length(); i++){
                if(word.charAt(i) == guess.charAt(0)){
                    new_hint += word.charAt(i);
                }else{
                    new_hint += hint.charAt(i);
                }
            }
            hint = new_hint;
        }if(guesses_left > 0 && !new_hint.equals(word))
        {
        System.out.print ("Secret word : " + new_hint + "\n\nYour guesses: " + guesses + "\n\nGuesses left: " + guesses_left + "\nYour guess? ");
        guess = input.nextLine();
        guess = guess.toUpperCase ();
        }else if(guesses_left > 0 && new_hint.equals(word))
        {
        System.out.print("You win! My word was \"" + word + "\"");
        }else{
            System.out.print("You Lost!");
            }
        }
        }while(guesses_left > 0 && !new_hint.equals(word));
        if (guesses_left == 0){
            System.out.println("");
        }
    }
}
