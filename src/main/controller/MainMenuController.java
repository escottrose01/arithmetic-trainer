package main.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import main.records.GameType;
import main.records.ProblemType;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController {
    private GameType gameType = GameType.TIMED;
    private ProblemType problemType = ProblemType.ADDITION;
    private int minAnswer = 0;
    private int maxAnswer = 10;
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
    void quit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void start() {
        // TODO: connect to game scene
    }

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

    @FXML
    void updateOptions(ActionEvent event) {
        if (event.getSource() == easyRadioButton) {
            minimumAnswerTextField.setText("0");
            maximumAnswerTextField.setText("10");
            updateMinimumAnswer();
            updateMaximumAnswer();
            customOptions.setVisible(false);
        } else if (event.getSource() == mediumRadioButton) {
            minimumAnswerTextField.setText("0");
            maximumAnswerTextField.setText("100");
            updateMinimumAnswer();
            updateMaximumAnswer();
            customOptions.setVisible(false);
        } else if (event.getSource() == hardRadioButton) {
            minimumAnswerTextField.setText("0");
            maximumAnswerTextField.setText("1000");
            updateMinimumAnswer();
            updateMaximumAnswer();
            customOptions.setVisible(false);
        } else if (event.getSource() == customRadioButton) {
            customOptions.setVisible(true);
        }
    }

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
    }
}

