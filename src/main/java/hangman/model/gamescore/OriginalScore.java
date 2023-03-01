package hangman.model.gamescore;

public class OriginalScore implements GameScore {
    private int score = 100;
    private int wrong_letter = 10;
    private static final int min_score = 0;

    /**
     * Calculate the score of the game following the rules of the original mode:
     * - Start with 100 points
     * - The correct guesses have not bonus
     * - The incorrect guesses have a penalty of 10 points
     * - The minimum score is 0 points
     */
    @Override
    public int calculateScore(int correctCount, int incorrectCount) {
        if (correctCount < 0 || incorrectCount < 0) {
            throw new IllegalArgumentException("Parametro negativo");
        }
        score -= incorrectCount * wrong_letter;
        if (score <= min_score) {
            return min_score;
        }
        return score;
    }
}
