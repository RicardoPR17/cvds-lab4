package hangman.model.gamescore;

public interface GameScore {
    /**
     * @param correctCount   An integer that represent the number of correct guesses
     * 
     * @param incorrectCount An integer that represent the number of incorrect
     *                       guesses
     * 
     * @throws IllegalArgumentException If almost one parameters are a negative
     *                                  number
     * 
     * @return The actual score of the game
     */
    public int calculateScore(int correctCount, int incorrectCount);
}
