package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene sceneOne = new Scene(FXMLLoader.load(getClass().getResource("View/Design.fxml")));
        stage.setScene(sceneOne);
        stage.setTitle("Easy Focus");
        stage.sizeToScene();
        stage.show();
    }
}