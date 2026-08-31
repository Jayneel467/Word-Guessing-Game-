import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Word_game {
    private static final String[] WORDS = {
            "rainbow", "computer", "science", "programming",
            "python", "mathematics", "player", "condition",
            "reverse", "water", "board", "geeks"
    };

    private static final int MAX_TURNS = 12;

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("What is your name? ");
            String name = input.nextLine();
            System.out.println("Good luck, " + name + "!");

            GameResult result = playGame(WORDS, MAX_TURNS, input);

            if (result.won) {
                System.out.println("You win!");
                System.out.println("The word is: " + result.word);
            } else {
                System.out.println("You lose!");
                System.out.println("The word was: " + result.word);
            }
        }
    }

    static boolean isValidGuess(String guess) {
        return guess != null && guess.length() == 1;
    }

    static String buildProgressDisplay(String word, String guessedLetters) {
        StringBuilder progress = new StringBuilder();
        for (char currentChar : word.toCharArray()) {
            if (guessedLetters.indexOf(String.valueOf(currentChar)) >= 0) {
                progress.append(currentChar).append(" ");
            } else {
                progress.append("_ ");
            }
        }
        return progress.toString().trim();
    }

    static boolean hasWon(String word, String guessedLetters) {
        for (char currentChar : word.toCharArray()) {
            if (guessedLetters.indexOf(String.valueOf(currentChar)) < 0) {
                return false;
            }
        }
        return true;
    }

    static GuessResult evaluateGuess(String word, String guess, String guessedLetters) {
        String normalizedGuess = guess.toLowerCase(Locale.ROOT);
        String normalizedGuesses = guessedLetters.toLowerCase(Locale.ROOT);

        if (normalizedGuesses.contains(normalizedGuess)) {
            return new GuessResult(false, guessedLetters, "You already guessed this character.");
        }

        String updatedLetters = guessedLetters + normalizedGuess;
        boolean correct = word.contains(normalizedGuess);
        return new GuessResult(correct, updatedLetters, correct ? "Correct!" : "Wrong!");
    }

    static GameResult playGame(String[] words, int maxTurns, Scanner input) {
        String word = words[ThreadLocalRandom.current().nextInt(words.length)];
        String guessedLetters = "";
        int turnsLeft = maxTurns;

        while (turnsLeft > 0) {
            System.out.println(buildProgressDisplay(word, guessedLetters));

            if (hasWon(word, guessedLetters)) {
                return new GameResult(true, word, turnsLeft);
            }

            System.out.print("Guess a character: ");
            String guess = input.next().trim().toLowerCase(Locale.ROOT);

            if (!isValidGuess(guess)) {
                System.out.println("Please enter a single character.");
                continue;
            }

            GuessResult result = evaluateGuess(word, guess, guessedLetters);
            guessedLetters = result.guessedLetters;

            if (result.correct) {
                System.out.println(result.message);
            } else {
                turnsLeft--;
                System.out.println(result.message);
                System.out.printf("You have %d guesses more.%n", turnsLeft);
            }

            if (turnsLeft == 0) {
                return new GameResult(false, word, turnsLeft);
            }
        }

        return new GameResult(false, word, turnsLeft);
    }

    static class GuessResult {
        final boolean correct;
        final String guessedLetters;
        final String message;

        GuessResult(boolean correct, String guessedLetters, String message) {
            this.correct = correct;
            this.guessedLetters = guessedLetters;
            this.message = message;
        }
    }

    static class GameResult {
        final boolean won;
        final String word;
        final int turnsLeft;

        GameResult(boolean won, String word, int turnsLeft) {
            this.won = won;
            this.word = word;
            this.turnsLeft = turnsLeft;
        }
    }
}

