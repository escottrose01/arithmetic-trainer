package main.controller;

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
import main.data.expression.Expression;
import main.data.generator.ComboProblemGenerator;
import main.data.generator.ProblemGenerator;
import main.records.GameConfig;
import main.records.Operator;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController {
    private GameConfig config;
    private ProblemGenerator problemGenerator;
    private Expression currentExpression;

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

    @FXML
    void submitAnswer(ActionEvent event) {

    }

    public void updateLabelA(String s) {
        labelA.setText(s);
    }

    public void updateLabelB(String s) {
        labelB.setText(s);
    }

    public void getNewQuestion() {
        currentExpression = problemGenerator.generateProblem();
        equationLabel.setText(currentExpression.toString());
    }

    public void startGame() {
        getNewQuestion();
        answerBox.requestFocus();
    }

    public void setVars(GameConfig config) {
        this.config = config;

        switch (config.getProblemType()) {
            case ADDITION:
                problemGenerator = new ComboProblemGenerator(
                    config.getMinAnswer(),
                    config.getMaxAnswer(),
                    config.getMinLength(),
                    config.getMaxLength(),
                    new Operator[]{Operator.ADD}
                );
                break;
            case SUBTRACTION:
                problemGenerator = new ComboProblemGenerator(
                        config.getMinAnswer(),
                        config.getMaxAnswer(),
                        config.getMinLength(),
                        config.getMaxLength(),
                        new Operator[]{Operator.SUBTRACT}
                );
                break;
            case MULTIPLICATION:
                problemGenerator = new ComboProblemGenerator(
                        config.getMinAnswer(),
                        config.getMaxAnswer(),
                        config.getMinLength(),
                        config.getMaxLength(),
                        new Operator[]{Operator.MULTIPLY}
                );
                break;
            case DIVISION:
                problemGenerator = new ComboProblemGenerator(
                        config.getMinAnswer(),
                        config.getMaxAnswer(),
                        config.getMinLength(),
                        config.getMaxLength(),
                        new Operator[]{Operator.DIVIDE}
                );
                break;
            case COMBO:
                problemGenerator = new ComboProblemGenerator(
                        config.getMinAnswer(),
                        config.getMaxAnswer(),
                        config.getMinLength(),
                        config.getMaxLength(),
                        new Operator[]{Operator.ADD, Operator.SUBTRACT, Operator.MULTIPLY,
                                Operator.DIVIDE}
                );
                break;
        }
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