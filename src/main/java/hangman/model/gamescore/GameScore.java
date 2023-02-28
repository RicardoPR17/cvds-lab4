package hangman.model.gamescore;

public interface GameScore {
    /**
     * @param correctCount   An integer that represent the number of correct guesses
     * 
     * @param incorrectCount An integer that represent the number of incorrect
     *                       guesses
     * 
     * @return The actual score of the game
     */
    public int calculateScore(int correctCount, int incorrectCount);
}
