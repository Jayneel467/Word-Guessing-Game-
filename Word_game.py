import random

WORDS = [
    "rainbow", "computer", "science", "programming",
    "python", "mathematics", "player", "condition",
    "reverse", "water", "board", "geeks"
]


def is_valid_guess(guess):
    return isinstance(guess, str) and len(guess) == 1


def build_progress_display(word, guessed_letters):
    letters = []
    for char in word:
        if char in guessed_letters:
            letters.append(char)
        else:
            letters.append("_")
    return " ".join(letters)


def evaluate_guess(word, guess, guessed_letters):
    guess = guess.lower()
    guessed_letters = set(guessed_letters)
    if guess in guessed_letters:
        return {"correct": False, "guessed_letters": guessed_letters, "message": "You already guessed this character."}

    guessed_letters.add(guess)
    return {
        "correct": guess in word,
        "guessed_letters": guessed_letters,
        "message": "Correct!" if guess in word else "Wrong!"
    }


def play_word_game(words=None, max_turns=12, input_func=input):
    if words is None:
        words = WORDS

    word = random.choice(words)
    guessed_letters = set()
    turns_left = max_turns

    while turns_left > 0:
        print(build_progress_display(word, guessed_letters))
        if all(char in guessed_letters for char in word):
            return {"status": "won", "word": word, "turns_left": turns_left}

        guess = input_func("Guess a character: ").strip().lower()

        if not is_valid_guess(guess):
            print("Please enter a single character.")
            continue

        result = evaluate_guess(word, guess, guessed_letters)
        guessed_letters = result["guessed_letters"]

        if result["correct"]:
            print(result["message"])
        else:
            turns_left -= 1
            print(result["message"])
            print(f"You have {turns_left} guesses more.")

        if turns_left == 0:
            return {"status": "lost", "word": word, "turns_left": turns_left}

    return {"status": "lost", "word": word, "turns_left": turns_left}


def main():
    name = input("What is your name? ")
    print(f"Good luck, {name}!")

    result = play_word_game()
    if result["status"] == "won":
        print("You win!")
        print(f"The word is: {result['word']}")
    else:
        print("You lose!")
        print(f"The word was: {result['word']}")


if __name__ == "__main__":
    main()

