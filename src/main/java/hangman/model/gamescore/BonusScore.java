package hangman.model.gamescore;

public class BonusScore implements GameScore {
    private int score = 0;
    private static final int bonus = 10;
    private static final int penalty = 5;
    private static final int min_score = 0;

    /**
     * Calculate the score of the game following the rules of the bonus mode:
     * - Start with 0 points
     * - The correct guesses have a bonus of 10 points
     * - The incorrect guesses have a penalty of 5 points
     * - The minimum score is 0 points
     * 
     * @throws IllegalArgumentException If almost one parameters are a negative
     *                                  number
     * 
     */
    @Override
    public int calculateScore(int correctCount, int incorrectCount) {
        if (correctCount < 0 || incorrectCount < 0) {
            throw new IllegalArgumentException("Parametro negativo");
        }
        score += correctCount * bonus;
        score -= incorrectCount * penalty;
        if (score < min_score) {
            return min_score;
        }
        return score;
    }
}
