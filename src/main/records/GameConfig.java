package main.records;

/**
 * This class models a set of game configuration settings.
 */
public class GameConfig {
    private GameType gameType;
    private ProblemType problemType;
    private int minAnswer;
    private int maxAnswer;
    private int minLength;
    private int maxLength;
    private int gameLength;

    // The default configuration
    public static final GameConfig defaultConfig = new GameConfig(
            GameType.TIMED,
            ProblemType.COMBO,
            0,
            10,
            2,
            2,
            10
    );

    public GameConfig(GameType gameType, ProblemType problemType, int minAnswer, int maxAnswer,
                      int minLength, int maxLength, int gameLength) {
        this.gameType = gameType;
        this.problemType = problemType;
        this.minAnswer = minAnswer;
        this.maxAnswer = maxAnswer;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.gameLength = gameLength;
    }

    public GameType getGameType() {
        return gameType;
    }

    public ProblemType getProblemType() {
        return problemType;
    }

    public int getMinAnswer() {
        return minAnswer;
    }

    public int getMaxAnswer() {
        return maxAnswer;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getGameLength() {
        return gameLength;
    }
}
