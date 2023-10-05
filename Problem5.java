import java.util.*;
import java.io.*;

public class Problem5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Scanner file = new Scanner(new File("search.txt"));

        while (true) {
            System.out.print("Please enter a word (or a space to exit): ");
            String word = input.nextLine();

            // Check if the user entered a space
            if (word.equals(" ")) {
                System.out.println("Exiting the program.");
                break;  // Exit the loop if a space is entered
            }

            boolean valid = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!Character.isLetter(c)) {
                    valid = false;
                    break;  // Exit the loop if an invalid character is found
                }
            }

            if (!valid) {
                System.out.println("Please enter a valid word!");
                continue;  // Continue to the next iteration of the loop
            }

            while (file.hasNextLine()) {
                String line = file.nextLine();
                if (line.contains(word)) {
                    System.out.println(line);
                }
            }

            // Reset the file scanner to read from the beginning
            file = new Scanner(new File("search.txt"));
        }

        input.close();
        file.close();
    }
}
