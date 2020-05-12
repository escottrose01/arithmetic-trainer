package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.MainMenuController;
import main.records.GameConfig;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Load scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/mainmenu.fxml"));
        Parent pane = loader.load();
        Scene scene = new Scene(pane, 650, 400);

        // Configure scene
        MainMenuController controller = loader.getController();
        controller.setVars(GameConfig.defaultConfig);
        stage.setScene(scene);

        // Display scene
        stage.show();
    }
}
