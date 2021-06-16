package sample.Controllers;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Timer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    ObservableList<String> sounds = FXCollections.observableArrayList("rain", "fire", "sea", "street");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public ComboBox<String> settingsBox;

    @FXML
    private Button aboutButton;

    @FXML
    private TextArea notes;

    @FXML
    private Button openButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button saveAsButton;

    @FXML
    private Button musicButton;

    @FXML
    private Button otherTimeTechn;

    @FXML
    private TextField setTimerField;

    @FXML
    private Button startStopButton;

    @FXML
    public static Stage STAGE;


    private String currName = null;
    private int counter = 0;
    private int counter2 = 0;
    public String nameSound = "rain.mp3";
    private MediaPlayer player;

    private void setTime(int time, TextField timerField){
        long timeNow = 1000*time;
        long second = (timeNow / 1000) % 60;
        long minute = (timeNow / (1000 * 60)) % 60;
        long hour = (timeNow / (1000 * 60 * 60)) % 24;
        timerField.setText(String.format("%02d:%02d:%02d", hour, minute, second));
    }

    public void read(){
        FileChooser choose = new FileChooser();
        try {
            File txt = choose.showOpenDialog(null);
            FileReader fr = new FileReader(txt);
            BufferedReader bf = new BufferedReader(fr);
            String line = bf.readLine();
            while (line != null) {
                notes.appendText(line + "\n");
                line = bf.readLine();
            }
        } catch (FileNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка загрузки файла!");
            alert.showAndWait();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Ошибка загрузки файла!");
            alert.showAndWait();
        }
    }
    public static int isDigit(String[] s) throws NumberFormatException{
        try {
            int counterLimit = Integer.parseInt(s[0]) * 60 * 60 + Integer.parseInt(s[1]) * 60 + Integer.parseInt(s[2]);
            return counterLimit;
        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Неверный символ!");
            alert.showAndWait();
            return 0;
        }
    }


    @FXML
    void initialize() {
        settingsBox.setValue("rain");
        settingsBox.setItems(sounds);
        settingsBox.setOnAction(actionEvent -> {
            nameSound = settingsBox.getValue() + ".mp3";
        });
        startStopButton.setOnAction(actionEvent -> {
            Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + nameSound);
            MediaPlayer playerMusic = new MediaPlayer(m);
            player = playerMusic;
            ++counter;
            if (counter == 1) {
                Timer time = new Timer();
                playerMusic.play();
                ++counter2;
                TimerTask timeTask = new TimerTask() {
                    String[] customTime = setTimerField.getText().split(":");
                    int counterLimit = isDigit(customTime);
                    @Override
                    public void run() {
                        --counterLimit;
                        setTime(counterLimit, setTimerField);
                        if (counterLimit == 0 || counter > 1 || counterLimit < 0) {
                            playerMusic.stop();
                            time.cancel();
                            Toolkit.getDefaultToolkit().beep();
                            setTime(0, setTimerField);
                            counter = 0;
                        }
                    }
                };
                time.schedule(timeTask, 0, 1000);
                playerMusic.seek(playerMusic.getStartTime());
            }
        });

        aboutButton.setOnAction(actionEvent -> {
            aboutButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/AboutApp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Ошибка загрузки окна!");
                alert.showAndWait();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Справка");
            stage.showAndWait();
        });

        saveButton.setOnAction(actionEvent -> {
            if(currName == null){
                FileChooser fileChooser = new FileChooser();
                File txt = fileChooser.showSaveDialog(null);
                currName = txt.getAbsolutePath();
                FileWriter w = null;
                try {
                    w = new FileWriter(txt);
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ошибка сохранения!");
                    alert.showAndWait();
                }
                try {
                    w.write(notes.getText());
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ошибка сохранения!");
                    alert.showAndWait();
                }
                try {
                    w.close();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ошибка сохранения!");
                    alert.showAndWait();
                }
            }
            else{
                File txt = new File(currName);
                currName = txt.getAbsolutePath();
                FileWriter w = null;
                try {
                    w = new FileWriter(txt);
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ошибка сохранения!");
                    alert.showAndWait();
                }
                try {
                    w.write(notes.getText());
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ошибка сохранения!");
                    alert.showAndWait();
                }
                try {
                    w.close();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Ошибка сохранения!");
                    alert.showAndWait();
                }
            }

        });

        saveAsButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File txt = fileChooser.showSaveDialog(null);
            currName = txt.getAbsolutePath();
            FileWriter w = null;
            try {
                w = new FileWriter(txt);
                currName = txt.getAbsolutePath();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Ошибка! Неправильный путь файла!");
                alert.showAndWait();
            }
            try {
                w.write(notes.getText());
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Ошибка сохранения!");
                alert.showAndWait();
            }
            try {
                w.close();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setHeaderText(null);
                alert.setContentText("Ошибка сохранения!");
                alert.showAndWait();
            }
        });

        openButton.setOnAction(actionEvent -> {
            read();
        });

        musicButton.setOnAction(actionEvent -> {
            if(counter2 > 0) {
                player.pause();
                --counter2;
            }
            else if(counter2 == 0 && counter == 1){
                player.play();
                ++counter2;
            }
        });

        otherTimeTechn.setOnAction(actionEvent -> {
            aboutButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/otherTechn.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Другие техники");
            stage.showAndWait();
        });
    }
}
