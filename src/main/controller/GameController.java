package main.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.data.expression.Expression;
import main.data.generator.ComboProblemGenerator;
import main.data.generator.ProblemGenerator;
import main.records.GameConfig;
import main.records.GameType;
import main.records.Operator;

import java.net.URL;
import java.util.ResourceBundle;

// TODO: change to label bindings

/**
 * This class is responsible for controlling the Game scene.
 */
public class GameController {
    private GameConfig config;
    private ProblemGenerator problemGenerator;
    private Expression currentExpression;
    private int currentNumberQuestions;
    private Timeline timeline;
    private int currentTime;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button quitButton;

    @FXML
    private Label equationLabel;

    @FXML
    private TextField answerBox;

    @FXML
    private Label labelA;

    @FXML
    private Label labelB;

    /**
     * Quits this scene, returning to the main menu.
     * @param event the event that called this method
     * @throws Exception exception on scene loading
     */
    @FXML
    void quit(ActionEvent event) throws Exception {
        // Load scene
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/mainmenu.fxml"));
        Parent pane = loader.load();
        Scene scene = new Scene(pane, 650, 400);

        // Configure scene
        MainMenuController controller = loader.getController();
        controller.setVars(config);

        // Display scene
        stage.setScene(scene);
    }

    /**
     * submits an answer from the answerBox, reacting based on the correctness of the answer.
     */
    @FXML
    void submitAnswer() {
        try {
            int answer = Integer.parseInt(answerBox.getText());
            if (answer == currentExpression.evaluate()) {
                handleCorrectAnswer();
            } else {
                handleIncorrectAnswer();
            }
        } catch (NumberFormatException e) {
            handleIncorrectAnswer(); // Should it be incorrect or ignored?
        }
    }

    /**
     * Handles an incorrect answer.
     */
    private void handleIncorrectAnswer() {
        answerBox.setText("");
    }

    /**
     * Handles a correct answer by creating a new problem.
     */
    private void handleCorrectAnswer() {
        answerBox.setText("");
        if (config.getGameType() == GameType.COUNT) {
            --currentNumberQuestions;
            if (currentNumberQuestions == 0) {
                endGame();
            }
        } else {
            ++currentNumberQuestions;
        }
        labelB.setText(String.valueOf(currentNumberQuestions));
        getNewQuestion();
    }

    /**
     * Gets a new question from the problemGenerator and updates the equationLabel.
     */
    public void getNewQuestion() {
        currentExpression = problemGenerator.generateProblem();
        equationLabel.setText(currentExpression.toString());
    }

    /**
     * Starts the game by beginning the timer and problem counter and creating a question.
     */
    public void startGame() {
        if (config.getGameType() == GameType.TIMED) {
            startCountdown();
        } else if (config.getGameType() == GameType.COUNT) {
            currentNumberQuestions = config.getGameLength();
            startCountup();
        } else if (config.getGameType() == GameType.ZEN) {
            startCountup();
        }
        labelB.setText(String.valueOf(currentNumberQuestions));

        getNewQuestion();
        answerBox.requestFocus();
    }

    /**
     * Begins the timer countdown from the time contained in config.
     */
    private void startCountdown() {
        currentTime = 60 * config.getGameLength();
        labelA.setText(String.valueOf(currentTime));
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            --currentTime;
                            labelA.setText(String.valueOf(currentTime));
                            if (currentTime <= 0) {
                                timeline.stop();
                                endGame();
                            }
                        }
                ));
        timeline.playFromStart();
    }

    /**
     * Begins the timer countup from 0.
     */
    private void startCountup() {
        currentTime = 0;
        labelA.setText(String.valueOf(currentTime));
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            ++currentTime;
                            labelA.setText(String.valueOf(currentTime));
                        }
                )
        );
        timeline.playFromStart();
    }

    /**
     * Ends the game, displaying the results.
     */
    private void endGame() {
        // TODO: stub
        quitButton.fireEvent(new ActionEvent());
    }

    /**
     * Sets up the scene variables based on the data in config.
     * @param config the GameConfig object to get information from
     */
    public void setVars(GameConfig config) {
        this.config = config;
        
        Operator[] ops;
        switch (config.getProblemType()) {
            case ADDITION:
                ops = new Operator[]{Operator.ADD};
                break;
            case SUBTRACTION:
                ops = new Operator[]{Operator.SUBTRACT};
                break;
            case MULTIPLICATION:
                ops = new Operator[]{Operator.MULTIPLY};
                break;
            case DIVISION:
                ops = new Operator[]{Operator.DIVIDE};
                break;
            case COMBO:
                ops = new Operator[]{Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY,
                        Operator.DIVIDE};
                break;
            default:
                ops = new Operator[]{};
        }
        
        problemGenerator = new ComboProblemGenerator(
                config.getMinAnswer(),
                config.getMaxAnswer(),
                config.getMinLength(),
                config.getMaxLength(),
                ops
        );
    }

    @FXML
    void initialize() {
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'game.fxml'.";
        assert equationLabel != null : "fx:id=\"equationLabel\" was not injected: check your FXML file 'game.fxml'.";
        assert answerBox != null : "fx:id=\"answerBox\" was not injected: check your FXML file 'game.fxml'.";
        assert labelA != null : "fx:id=\"labelA\" was not injected: check your FXML file 'game.fxml'.";
        assert labelB != null : "fx:id=\"labelB\" was not injected: check your FXML file 'game.fxml'.";
    }
}