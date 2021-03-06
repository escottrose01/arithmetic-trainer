package main.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.records.GameConfig;
import main.records.GameType;
import main.records.ProblemType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is responsible for controlling the MainMenu scene.
 */
public class MainMenuController {
    private GameType gameType = GameType.TIMED;
    private ProblemType problemType = ProblemType.ADDITION;
    private int minAnswer = 0;
    private int maxAnswer = 10;
    private int minLength = 2;
    private int maxLength = 2;
    private int gameLength = 5; // Might be minutes or questions

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;

    @FXML
    private RadioButton timedRadioButton;

    @FXML
    private ToggleGroup gametype;

    @FXML
    private RadioButton countRadioButton;

    @FXML
    private RadioButton zenRadioButton;

    @FXML
    private Label gameTypeTextFieldLabel;

    @FXML
    private TextField gameTypeTextField;

    @FXML
    private RadioButton additionRadioButton;

    @FXML
    private ToggleGroup gamemode;

    @FXML
    private RadioButton subtractionRadioButton;

    @FXML
    private RadioButton multiplicationRadioButton;

    @FXML
    private RadioButton divisionRadioButton;

    @FXML
    private RadioButton comboRadioButton;

    @FXML
    private RadioButton easyRadioButton;

    @FXML
    private ToggleGroup difficulty;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton hardRadioButton;

    @FXML
    private RadioButton customRadioButton;

    @FXML
    private VBox customOptions;

    @FXML
    private Label minimumAnswerTextFieldLabel;

    @FXML
    private TextField minimumAnswerTextField;

    @FXML
    private Label maximumAnswerTextFieldLabel;

    @FXML
    private TextField maximumAnswerTextField;

    @FXML
    private TextField minimumLengthTextField;

    @FXML
    private TextField maximumLengthTextField;

    /**
     * Quits the application.
     */
    @FXML
    void quit() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Starts the game by constructing the game scene and setting game parameters via the
     * GameConfig object.
     * @param event the event that called this method
     * @throws Exception on failing to read game.fxml
     */
    @FXML
    void start(ActionEvent event) throws Exception {
        // Load scene
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/game.fxml"));
        Parent pane = loader.load();
        Scene scene = new Scene(pane, 650, 400);

        // Configure scene
        GameController controller = loader.getController();
        controller.setVars(new GameConfig(
                gameType,
                problemType,
                minAnswer,
                maxAnswer,
                minLength,
                maxLength,
                gameLength
        ));
        controller.startGame();

        // Display scene
        stage.setScene(scene);
    }

    /**
     * Called when the game type buttons are pressed. Sets the game type based on the user
     * selection.
     * @param event the event that called this method
     */
    @FXML
    void updateGameType(ActionEvent event) {
        if (event.getSource() == timedRadioButton) {
            gameType = GameType.TIMED;
            gameTypeTextFieldLabel.setText("Time (minutes):");
            gameTypeTextFieldLabel.setVisible(true);
            gameTypeTextField.setVisible(true);
        } else if (event.getSource() == countRadioButton) {
            gameType = GameType.COUNT;
            gameTypeTextFieldLabel.setText("Questions:");
            gameTypeTextFieldLabel.setVisible(true);
            gameTypeTextField.setVisible(true);
        } else if (event.getSource() == zenRadioButton) {
            gameType = GameType.ZEN;
            gameTypeTextFieldLabel.setVisible(false);
            gameTypeTextField.setVisible(false);
        }
    }

    /**
     * Updates the game type option; i.e. the length of the game, which may represent either time
     * or number of questions asked.
     */
    @FXML
    void updateGameTypeOptions() {
        try {
            gameLength = Integer.parseInt(gameTypeTextField.getText());
            gameLength = Integer.max(gameLength, 1);
            gameLength = Integer.min(gameLength, 60);
        } catch (NumberFormatException ignored) {
        } finally {
            gameTypeTextField.setText(String.valueOf(gameLength));
            gameTypeTextField.positionCaret(gameTypeTextField.getLength());
        }
    }

    /**
     * Updates the options based on the option radio buttons.
     * @param event the event that called this method
     */
    @FXML
    void updateOptions(ActionEvent event) {
        if (event.getSource() == easyRadioButton) {
            minimumAnswerTextField.setText("0");
            maximumAnswerTextField.setText("10");
            minimumLengthTextField.setText("2");
            maximumLengthTextField.setText("2");
            updateMinimumAnswer();
            updateMaximumAnswer();
            updateMinimumLength();
            updateMaximumLength();
            customOptions.setDisable(true);
        } else if (event.getSource() == mediumRadioButton) {
            minimumAnswerTextField.setText("0");
            maximumAnswerTextField.setText("100");
            minimumLengthTextField.setText("2");
            maximumLengthTextField.setText("4");
            updateMinimumAnswer();
            updateMaximumAnswer();
            updateMinimumLength();
            updateMaximumLength();
            customOptions.setDisable(true);
        } else if (event.getSource() == hardRadioButton) {
            minimumAnswerTextField.setText("0");
            maximumAnswerTextField.setText("1000");
            minimumLengthTextField.setText("3");
            maximumLengthTextField.setText("6");
            updateMinimumAnswer();
            updateMaximumAnswer();
            updateMinimumLength();
            updateMaximumLength();
            customOptions.setDisable(true);
        } else if (event.getSource() == customRadioButton) {
            customOptions.setDisable(false);
        }
    }

    /**
     * Updates the minAnswer field based on user input into the minimumAnswerTextField, resolving
     * ordering conflicts if this field is higher than the maxAnswer.
     */
    @FXML
    void updateMinimumAnswer() {
        try {
            minAnswer = Integer.parseInt(minimumAnswerTextField.getText());
            minAnswer = Integer.max(minAnswer, 0);
            if (maxAnswer < minAnswer) {
                maximumAnswerTextField.setText(minimumAnswerTextField.getText());
                updateMaximumAnswer();
            }
        } catch (NumberFormatException ignored) {
        } finally {
            minimumAnswerTextField.setText(String.valueOf(minAnswer));
            minimumAnswerTextField.positionCaret(minimumAnswerTextField.getLength());
        }
    }

    /**
     * Updates the maxAnswer field based on user input into the maximumAnswerTextField, resolving
     * ordering conflicts if this field is smaller than the minAnswer.
     */
    @FXML
    void updateMaximumAnswer() {
        try {
            maxAnswer = Integer.parseInt(maximumAnswerTextField.getText());
            maxAnswer = Integer.max(maxAnswer, 0);
            if (maxAnswer < minAnswer) {
                minimumAnswerTextField.setText(maximumAnswerTextField.getText());
                updateMinimumAnswer();
            }
        } catch (NumberFormatException ignored) {
        } finally {
            maximumAnswerTextField.setText(String.valueOf(maxAnswer));
            maximumAnswerTextField.positionCaret(maximumAnswerTextField.getLength());
        }
    }

    /**
     * Updates the maxLength field based on user input into the minimumLengthTextField, resolving
     * ordering conflicts if this field is higher than the maxLength.
     */
    @FXML
    void updateMinimumLength() {
        try {
            minLength = Integer.parseInt(minimumLengthTextField.getText());
            minLength = Integer.max(minLength, 2);
            minLength = Integer.min(minLength, 10);
            if (maxLength < minLength) {
                maximumLengthTextField.setText(minimumLengthTextField.getText());
                updateMaximumLength();
            }
        } catch (NumberFormatException ignored) {
        } finally {
            minimumLengthTextField.setText(String.valueOf(minLength));
            minimumLengthTextField.positionCaret(minimumLengthTextField.getLength());
        }
    }

    /**
     * Updates the maxLength field based on user input into the maximumLengthTextField, resolving
     * ordering conflicts if this field is smaller than the minLength.
     */
    @FXML
    void updateMaximumLength() {
        try {
            maxLength = Integer.parseInt(maximumLengthTextField.getText());
            maxLength = Integer.max(maxLength, 2);
            maxLength = Integer.min(maxLength, 10);
            if (maxLength < minLength) {
                minimumLengthTextField.setText(maximumAnswerTextField.getText());
                updateMinimumLength();
            }
        } catch (NumberFormatException ignored) {
        } finally {
            maximumLengthTextField.setText(String.valueOf(maxLength));
            maximumLengthTextField.positionCaret(maximumLengthTextField.getLength());
        }
    }

    /**
     * Updates the problemType field based on the problem type radio buttons.
     * @param event the event that called this method
     */
    @FXML
    void updateProblemType(ActionEvent event) {
        if (event.getSource() == additionRadioButton) {
            problemType = ProblemType.ADDITION;
        } else if (event.getSource() == subtractionRadioButton) {
            problemType = ProblemType.SUBTRACTION;
        } else if (event.getSource() == multiplicationRadioButton) {
            problemType = ProblemType.MULTIPLICATION;
        } else if (event.getSource() == divisionRadioButton) {
            problemType = ProblemType.DIVISION;
        } else if (event.getSource() == comboRadioButton) {
            problemType = ProblemType.COMBO;
        }
    }

    /**
     * Loads a set of configuration settings into this scene.
     * @param config the configuration settings to load
     */
    public void setVars(GameConfig config) {
        // Set gameType
        gameType = config.getGameType();
        switch (gameType) {
            case TIMED:
                timedRadioButton.fire();
                break;
            case COUNT:
                countRadioButton.fire();
                break;
            case ZEN:
                zenRadioButton.fire();
                break;
        }

        // Set problemType
        problemType = config.getProblemType();
        switch (problemType) {
            case ADDITION:
                additionRadioButton.fire();
                break;
            case SUBTRACTION:
                subtractionRadioButton.fire();
                break;
            case MULTIPLICATION:
                multiplicationRadioButton.fire();
                break;
            case DIVISION:
                divisionRadioButton.fire();
                break;
            case COMBO:
                comboRadioButton.fire();
                break;
        }

        // Set minAnswer
        minimumAnswerTextField.setText(String.valueOf(config.getMinAnswer()));
        minimumAnswerTextField.fireEvent(new ActionEvent());

        // Set maxAnswer
        maximumAnswerTextField.setText(String.valueOf(config.getMaxAnswer()));
        maximumAnswerTextField.fireEvent(new ActionEvent());

        // Default to custom options
        customRadioButton.fire();

        // Set minLength
        minimumLengthTextField.setText(String.valueOf(config.getMinLength()));
        minimumLengthTextField.fireEvent(new ActionEvent());

        // Set maxLength
        maximumLengthTextField.setText(String.valueOf(config.getMaxLength()));
        maximumLengthTextField.fireEvent(new ActionEvent());

        // Set gameLength
        gameTypeTextField.setText(String.valueOf(config.getGameLength()));
        gameTypeTextField.fireEvent(new ActionEvent());
    }

    /**
     * Ensures that all fields were loaded properly from the fxml file.
     */
    @FXML
    void initialize() {
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert quitButton != null : "fx:id=\"quitButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert timedRadioButton != null : "fx:id=\"timedRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert gametype != null : "fx:id=\"gametype\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert countRadioButton != null : "fx:id=\"countRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert zenRadioButton != null : "fx:id=\"zenRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert gameTypeTextFieldLabel != null : "fx:id=\"gameTypeTextFieldLabel\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert gameTypeTextField != null : "fx:id=\"gameTypeTextField\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert additionRadioButton != null : "fx:id=\"additionRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert gamemode != null : "fx:id=\"gamemode\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert subtractionRadioButton != null : "fx:id=\"subtractionRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert multiplicationRadioButton != null : "fx:id=\"multiplicationRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert divisionRadioButton != null : "fx:id=\"divisionRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert comboRadioButton != null : "fx:id=\"comboRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert easyRadioButton != null : "fx:id=\"easyRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert difficulty != null : "fx:id=\"difficulty\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert mediumRadioButton != null : "fx:id=\"mediumRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert hardRadioButton != null : "fx:id=\"hardRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert customRadioButton != null : "fx:id=\"customRadioButton\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert customOptions != null : "fx:id=\"customOptions\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert minimumAnswerTextFieldLabel != null : "fx:id=\"minimumAnswerTextFieldLabel\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert minimumAnswerTextField != null : "fx:id=\"minimumAnswerTextField\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert maximumAnswerTextFieldLabel != null : "fx:id=\"maximumAnswerTextFieldLabel\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert maximumAnswerTextField != null : "fx:id=\"maximumAnswerTextField\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert minimumLengthTextField != null : "fx:id=\"minimumLengthTextField\" was not injected: check your FXML file 'mainmenu.fxml'.";
        assert maximumLengthTextField != null : "fx:id=\"maxiumumLengthTextField\" was not injected: check your FXML file 'mainmenu.fxml'.";
    }
}

