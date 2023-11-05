import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class Hangman
{
  public static void main (String[]args) throws IOException,
    UnsupportedAudioFileException, LineUnavailableException,
    InterruptedException
  {
    Hangman hangmanGame = new Hangman ();
      hangmanGame.run ();
  }

  public void run () throws IOException, UnsupportedAudioFileException,
    LineUnavailableException, InterruptedException
  {
    Scanner input = new Scanner (System.in);
    int gamesCount = 0;
    int gamesWon = 0;
    int best = 0;
      intro ();
    while (true)
      {
	String filename;
	do
	  {
	    System.out.print
	      ("Please choose a file (dict, large, small, oneword, twowords, wordlenghts): ");
	    filename = input.nextLine ();
	  }
	while (filename.isEmpty () || !filename.equals ("dict")
	       && !filename.equals ("large") && !filename.equals ("small")
	       && !filename.equals ("oneword")
	       && !filename.equals ("twowords")
	       && !filename.equals ("wordlengths"));

	int guessCount = playOneGame (getRandomWord (filename));
	  gamesWon += guessCount > 0 ? 1 : 0;
	  gamesCount++;
	if (guessCount > best)
	  {
	    best = guessCount;
	  }
	if (gamesCount >= 1)
	  {
	    System.out.print ("Play again (Y/N)? ");
	    String retry = input.nextLine ().toUpperCase ();
	    while (retry.isEmpty () || !retry.equals ("Y")
		   && !retry.equals ("N"))
	      {
		System.
		  out.print ("Illegal boolean format\nPlay again (Y/N)? ");
		retry = input.nextLine ().toUpperCase ();
	      }
	    if (retry.equals ("N"))
	      {
		stats (gamesCount, gamesWon, best);
		input.close ();
		break;
	      }
	  }
      }
    input.close ();
  }

  private void intro () throws UnsupportedAudioFileException, IOException,
    LineUnavailableException, InterruptedException
  {
    AudioInputStream Begin =
      AudioSystem.getAudioInputStream (new File ("res/audio/Begin.WAV"));
    Clip begin = AudioSystem.getClip ();
      begin.open (Begin);
      begin.start ();
    long duration = begin.getMicrosecondLength ();
      Thread.sleep ((duration / 1000) - 50);
      begin.stop ();
      begin.close ();
      System.out.println
      ("CMPS201 Hangman!\nI will think of a random word.\nYou'll try to guess its letters.\nEvery time you guess a letter that isn't in my word, a new body part of the hanging man appears.\nGuess correctly to avoid the gallows!");
  }

  private int playOneGame (String secretWord) throws IOException,
    UnsupportedAudioFileException, LineUnavailableException,
    InterruptedException
  {
    int guessCount = 8;
    //int gamesWon = 0;
    String guessedLetters = "";
    String hint = createHint (secretWord, guessedLetters);


    while (guessCount > 0 && !hint.equals (secretWord))
      {
	displayHangman (guessCount);
	System.out.println ("Secret word: " + hint);
	System.out.println ("Your guesses: " + guessedLetters);
	System.out.println ("Guesses left: " + guessCount);
	char guess = readGuess (guessedLetters);
	  guessedLetters += guess;

	if (secretWord.contains (String.valueOf (guess)))
	  {
	    System.out.println ("Correct!");
	    hint = createHint (secretWord, guessedLetters);
	    AudioInputStream Correct =
	      AudioSystem.getAudioInputStream (new
					       File
					       ("res/audio/Correct.WAV"));
	    Clip CorrecT = AudioSystem.getClip ();
	      CorrecT.open (Correct);
	      CorrecT.start ();
	    long durationc = CorrecT.getMicrosecondLength ();
	      Thread.sleep ((durationc / 1000) - 400);
	      CorrecT.stop ();
	      CorrecT.close ();
	  }
	else
	  {
	    guessCount--;
	    System.out.println ("Incorrect!");
	    AudioInputStream inCorrect =
	      AudioSystem.getAudioInputStream (new
					       File
					       ("res/audio/Incorrect.WAV"));
	    Clip inCorrecT = AudioSystem.getClip ();
	    inCorrecT.open (inCorrect);
	    inCorrecT.start ();
	    long durationinc = inCorrecT.getMicrosecondLength ();
	    Thread.sleep ((durationinc / 1000) - 400);
	    inCorrecT.stop ();
	    inCorrecT.close ();
	  }
      }

    if (hint.equals (secretWord))
      {
	displayHangman (guessCount);
	System.out.println ("You win! My word was \"" + secretWord + "\"");
	//gamesWon++;
	AudioInputStream Win =
	  AudioSystem.getAudioInputStream (new File ("res/audio/Win.WAV"));
	Clip win = AudioSystem.getClip ();
	win.open (Win);
	win.start ();
	long durationinw = win.getMicrosecondLength ();
	Thread.sleep ((durationinw / 1000) - 100);
	win.stop ();
	win.close ();
      }
    else
      {
	displayHangman (guessCount);
	System.out.println ("You Lost! My word was \"" + secretWord + "\"");
	AudioInputStream Lose =
	  AudioSystem.getAudioInputStream (new File ("res/audio/Lose.WAV"));
	Clip lose = AudioSystem.getClip ();
	lose.open (Lose);
	lose.start ();
	long durationinl = lose.getMicrosecondLength ();
	Thread.sleep ((durationinl / 1000) - 12500);
	lose.stop ();
	lose.close ();
      }

    return guessCount;
  }



  private String createHint (String secretWord, String guessedLetters)
  {
    String hint = "";
    for (int i = 0; i < secretWord.length (); i++)
      {
	char c = secretWord.charAt (i);
	if (guessedLetters.contains (String.valueOf (c)))
	  {
	    hint += c;
	  }
	else
	  {
	    hint += "-";
	  }
      }
    return hint;
  }


  private char readGuess (String guessedLetters)
  {
    Scanner input = new Scanner (System.in);
    String guess;

    do
      {
	System.out.print ("Your guess? ");
	guess = input.nextLine ().toUpperCase ();

	if (guess.isEmpty ())
	  {
	    System.out.print ("Type a single letter from A-Z.\n");
	    continue;
	  }

	if (guess.length () != 1 || !Character.isLetter (guess.charAt (0)))
	  {
	    System.out.print ("Type a single letter from A-Z.\n");
	    continue;
	  }

	if (guessedLetters.contains (guess))
	  {
	    System.out.print ("You already guessed that letter.\n");
	  }
	else
	  {
	    break;
	  }
      }
    while (true);

    guessedLetters += guess;
    return guess.charAt (0);
  }


  private void displayHangman (int guessCount) throws FileNotFoundException
  {
    Scanner file =
      new Scanner (new File ("res/display" + guessCount + ".txt"));
    while (file.hasNext ())
      {
	System.out.println (file.nextLine ());
      }
  }

  private void stats (int gamesCount, int gamesWon,
		      int best) throws UnsupportedAudioFileException,
    IOException, LineUnavailableException, InterruptedException
  {
    double win_percentage = (((double) gamesWon / gamesCount) * 100);
    if ((int) win_percentage == 100)
      {
	AudioInputStream Perfect =
	  AudioSystem.getAudioInputStream (new
					   File ("res/audio/Perfect.WAV"));
	Clip perfect = AudioSystem.getClip ();
	  perfect.open (Perfect);
	  perfect.start ();
	long durationinp = perfect.getMicrosecondLength ();
	  Thread.sleep ((durationinp / 1000) - 100);
	  perfect.stop ();
	  perfect.close ();
      }
    System.out.println ("\nOverall statistics:");
    System.out.println ("Games played: " + gamesCount);
    System.out.println ("Games won:    " + gamesWon);
    System.out.printf ("Win percent:  %.1f%%\n", win_percentage);
    System.out.println ("Best game:    " + best + " guess(es) remaining");
    System.out.println ("Thanks for playing!\n");
    AudioInputStream End =
      AudioSystem.getAudioInputStream (new File ("res/audio/End.WAV"));
    Clip end = AudioSystem.getClip ();
    end.open (End);
    end.start ();
    long durationine = end.getMicrosecondLength ();
    Thread.sleep ((durationine / 1000));
    end.stop ();
    end.close ();
  }

  private String getRandomWord (String filename) throws FileNotFoundException
  {
    Scanner dictionary = new Scanner (new File ("res/" + filename + ".txt"));
    Random rand = new Random ();
    String secretWord = "";
    int n = (rand.nextInt (dictionary.nextInt ()) + 1);
    for (int i = 0; i < n; i++)
      {
	secretWord = dictionary.next ();
      }
    return secretWord;
  }
}
