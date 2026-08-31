import importlib.util
from pathlib import Path

module_path = Path(__file__).with_name("Word_game.py")
spec = importlib.util.spec_from_file_location("word_game_module", module_path)
word_game = importlib.util.module_from_spec(spec)
spec.loader.exec_module(word_game)


def test_is_valid_guess():
    assert word_game.is_valid_guess("a") is True
    assert word_game.is_valid_guess("ab") is False
    assert word_game.is_valid_guess("1") is True


def test_build_progress_display():
    assert word_game.build_progress_display("apple", {"a", "p"}) == "a p p _ _"


def test_guess_result_is_recorded():
    result = word_game.evaluate_guess("apple", "p", {"a"})
    assert result["correct"] is True
    assert result["guessed_letters"] == {"a", "p"}
