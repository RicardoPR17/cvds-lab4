package hangman.model.gamescore;

public class OriginalScore implements GameScore {
    private static final int score = 100;
    private static final int penalty = 10;
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
        int actualScore;
        actualScore = score - incorrectCount * penalty;
        if (actualScore <= min_score) {
            return min_score;
        }
        return actualScore;
    }
}
