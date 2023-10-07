The program begins by providing an introduction to the Hangman game, explaining the rules and setting the context for the player. It then enters a loop that allows the player to play multiple rounds of the game.

In each round, the program prompts the player to choose a category of words from a predefined set (dict, large, small, oneword, twowords, wordlengths). It reads a random word from the selected category and initializes the game with a set number of allowed incorrect guesses (8 in this case).

The game proceeds with the player attempting to guess letters in the secret word. The program displays the current state of the word with dashes representing unknown letters. The player inputs their guesses, and the program provides feedback on whether the guessed letters are correct or incorrect.

The hanging man display changes based on the number of incorrect guesses. The game continues until the player either correctly guesses the word or runs out of allowed incorrect guesses. After each round, the program prompts the player if they want to play again. The loop continues until the player decides to exit.

At the end of the game session, the program displays overall statistics, including the total number of games played, games won, win percentage, and the best game (highest number of guesses remaining). The player is thanked for playing.

To run the program:

1. Save the code in a file with a ".java" extension (e.g., Hangman.java).
2. Open a command prompt (CMD).
3. Navigate to the directory where the file is saved using the `cd` command.
4. Compile the Java code using the command: `javac Hangman.java`
5. Run the compiled program with the command: `java Hangman`

Enjoy playing Hangman in the command line!
