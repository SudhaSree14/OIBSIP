import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5;
        int totalRounds = 3;
        int totalScore = 0;
        System.out.println("Welcome to Guess the Number!");
        System.out.println("You have " + maxAttempts + " attempts to guess the number between " + lowerBound + " and " + upperBound + ".");

        for(int round = 1; round <= totalRounds; ++round) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attemptsLeft = maxAttempts;
            System.out.println("\nRound " + round + ": Guess the number!");

            for(; attemptsLeft > 0; --attemptsLeft) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number.");
                    int points = attemptsLeft * 10;
                    System.out.println("Round Score: " + points);
                    totalScore += points;
                    break;
                }

                System.out.println("Incorrect guess. Attempts left: " + (attemptsLeft - 1));
                if (userGuess < targetNumber) {
                    System.out.println("The number is higher.");
                } else {
                    System.out.println("The number is lower.");
                }
            }

            if (attemptsLeft == 0) {
                System.out.println("Sorry! You've run out of attempts. The correct number was: " + targetNumber);
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Total Score: " + totalScore);
        scanner.close();
    }
}
