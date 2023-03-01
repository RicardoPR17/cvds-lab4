package hangman.model.gamescore;

import java.lang.Math;

public class PowerScore implements GameScore {
    private static final int score = 0;
    private static final int bonus = 5;
    private static final int penalty = 8;
    private static final int min_score = 0;
    private static final int max_score = 500;

    /**
     * Calculate the score of the game following the rules of the power mode:
     * - Start with 0 points
     * - The correct guesses have a bonus of 5 raise to the power of i, where i is
     * each number of correct guesses
     * - The incorrect guesses have a penalty of 8 points
     * - The minimum score is 0 points
     * - The maximum score is 500 points
     */
    @Override
    public int calculateScore(int correctCount, int incorrectCount) {
        int actualScore;
        actualScore = score;

        for (int i = 1; i <= correctCount; i++) {
            actualScore += Math.pow(bonus, i);
        }

        actualScore -= incorrectCount * penalty;

        if (actualScore < min_score) {
            return min_score;
        } else if (actualScore > max_score) {
            return max_score;
        }

        return actualScore;
    }
}
