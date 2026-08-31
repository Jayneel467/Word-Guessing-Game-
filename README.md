# Word Guessing Game

A simple word guessing game built in Python and Java. The player tries to guess a hidden word by entering one letter at a time, and the game tracks correct and incorrect guesses until the word is revealed or the player runs out of turns.

## Features
- Random word selection from a predefined list
- Letter-by-letter guessing gameplay
- Remaining-turn tracking
- Progress display showing revealed and hidden letters
- Python and Java implementations
- Basic validation for single-character input

## Project Files
- `Word_game.py` - Python version
- `Word_game.java` - Java version
- `test_word_game.py` - Python tests for logic validation

## How to Run the Python Version

```bash
python Word_game.py
```

## How to Run the Java Version

```bash
javac Word_game.java
java Word_game
```

## Example Gameplay

```text
What is your name? Jayneel
Good luck, Jayneel!
_ _ _ _ _
Guess a character: a
Correct!
_ a _ _ _
```

## Testing

For the Python version:

```bash
python -m pytest -q
```

## License
This project is for educational and portfolio use.
