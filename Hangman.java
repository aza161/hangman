import java.util.*;
import java.io.*;
public class Hangman
{
  public static void main (String[]args) throws FileNotFoundException
  {
    Scanner input = new Scanner (System.in);
    int total_games = 0;
    int win_count = 0;
    int best = 0;
      System.out.println
      ("CMPS201 Hangman!\nI will think of a random word. You'll try to guess its\nletters. Every time you guess a letter that isn't in my\nword, a new body part of the hanging man appears.\nGuess correctly to avoid the gallows!");
    do
      {
	String fname = "";
	int guesses_left = 8;
	int num_guesses = 0;
	while (true)
	  {
	    System.out.
	      print
	      ("Please choose a file (dict, large, small, oneword, twowords, wordlenghts): ");
	    fname = input.next ();
	    if (fname.equals ("dict") || fname.equals ("large")
		|| fname.equals ("small") || fname.equals ("oneword")
		|| fname.equals ("twowords") || fname.equals ("wordlengths"))
	      {
		break;
	      }			/*else {
				   System.out.print ("Please choose a file (dict, large, small, oneword, twowords, wordlenghts): ");
				   } */
	  }
	Scanner dictionary = new Scanner (new File ("res/" + fname + ".txt"));
	Random rand = new Random ();
	String word = "";
	int n = (rand.nextInt (dictionary.nextInt ()) + 1);
	for (int i = 0; i < n; i++)
	  {
	    word = dictionary.next ();
	  }
	Scanner file = new Scanner (new File ("res/display8.txt"));
	while (file.hasNext ())
	  {
	    System.out.println (file.nextLine ());
	  }
	System.out.print ("Secret word: ");
	String hint = "";
	String new_hint = "";
	for (int i = 1; i <= word.length (); i++)
	  {
	    hint += "-";
	  }
	System.out.print (hint);
	String guesses = "";
	boolean correct;
	input.nextLine ();
	System.out.print ("\nYour guesses: " + "\nGuesses left: " +
			  guesses_left + "\nYour guess? ");
	String guess = input.nextLine ();
	guess = guess.toUpperCase ();
	do
	  {
	    while (guess.length () > 1
		   || !Character.isLetter (guess.charAt (0)))
	      {
		System.out.
		  print ("Type a single letter from A-Z.\nYour guess? ");
		guess = input.nextLine ();
		guess = guess.toUpperCase ();
	      }
	    while (guesses.contains (guess))
	      {
		System.out.
		  print ("You already guessed that letter.\nYour guess? ");
		guess = input.nextLine ();
		guess = guess.toUpperCase ();
	      }
	    //guesses += guess;

	    if (guess.length () > 1)
	      {
		continue;
	      }

	    if (word.contains (guess))
	      {
		Scanner file1 =
		  new Scanner (new
			       File ("res/display" + guesses_left + ".txt"));
		while (file1.hasNext ())
		  {
		    System.out.println (file1.nextLine ());
		  }
		System.out.println ("Correct!");
		num_guesses += 1;
		correct = true;
	      }
	    else
	      {
		Scanner file1 =
		  new Scanner (new
			       File ("res/display" + (guesses_left - 1) +
				     ".txt"));
		while (file1.hasNext ())
		  {
		    System.out.println (file1.nextLine ());
		  }
		System.out.println ("Incorrect!");
		num_guesses += 1;
		guesses_left -= 1;
		correct = false;
	      }
	    if (guess.length () == 1 && Character.isLetter (guess.charAt (0)))
	      {
		guesses += guess;
		if (correct)
		  {
		    new_hint = "";
		    for (int i = 0; i < hint.length (); i++)
		      {
			if (word.charAt (i) == guess.charAt (0))
			  {
			    new_hint += word.charAt (i);
			  }
			else
			  {
			    new_hint += hint.charAt (i);
			  }
		      }
		    hint = new_hint;
		  }
		if (guesses_left > 0 && !new_hint.equals (word))
		  {
		    if (guesses_left == 7 && num_guesses == 1
			|| guesses_left == 6 && num_guesses == 2
			|| guesses_left == 5 && num_guesses == 3
			|| guesses_left == 4 && num_guesses == 4
			|| guesses_left == 3 && num_guesses == 5
			|| guesses_left == 2 && num_guesses == 6
			|| guesses_left == 1 && num_guesses == 7
			|| guesses_left == 0 && num_guesses == 8)
		      {
			System.out.print ("Secret word : " + hint +
					  "\nYour guesses: " + guesses +
					  "\nGuesses left: " + guesses_left +
					  "\nYour guess? ");
		      }
		    else
		      {
			System.out.print ("Secret word : " + new_hint +
					  "\nYour guesses: " + guesses +
					  "\nGuesses left: " + guesses_left +
					  "\nYour guess? ");
		      }
		    guess = input.nextLine ();
		    guess = guess.toUpperCase ();
		  }
		else if (guesses_left > 0 && new_hint.equals (word))
		  {
		    System.out.print ("You win! My word was \"" + word +
				      "\"");
		    total_games += 1;
		    win_count += 1;
		  }
		else
		  {
		    System.out.print ("You Lost!");
		    total_games += 1;
		  }
	      }
	  }
	while (guesses_left > 0 && !new_hint.equals (word));
	if (guesses_left == 0)
	  {
	    System.out.println (" My word was \"" + word + "\"");
	  }
	if (guesses_left > best)
	  {
	    best = guesses_left;
	  }
	if (total_games >= 1)
	  {
	    System.out.print ("Play again (Y/N)? ");
	    String retry = input.next ();
	    retry = retry.toUpperCase ();
	    while (!retry.equals ("Y") && !retry.equals ("N"))
	      {
		System.out.print ("Invalid input. Play again (Y/N)? ");
		retry = input.next ();
		retry = retry.toUpperCase ();
	      }
	    if (retry.equals ("N"))
	      {
		double win_percentage =
		  (((double) win_count / total_games) * 100);
		System.out.println ("Overall statistics:");
		System.out.println ("Games played: " + total_games);
		System.out.println ("Games won: " + win_count);
		System.out.printf ("Win percent: %.1f%%\n", win_percentage);
		System.out.println ("Best game: " + best +
				    " guess(es) remaining");
		System.out.println ("Thanks for playing!\n");
		break;
	      }
	  }
      }
    while (true);
  }
}
