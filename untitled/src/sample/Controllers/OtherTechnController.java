package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class OtherTechnController {
    ObservableList<String> mood = FXCollections.observableArrayList("Надо сделать", "Делается", "Сделано");
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text text11;

    @FXML
    private Text text111;

    @FXML
    private Text text1111;

    @FXML
    private Text text11111;

    @FXML
    private Text text111111;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    private TextField text4;

    @FXML
    private TextField text5;

    @FXML
    private TextField text6;

    @FXML
    private TextField text7;

    @FXML
    private TextField text8;

    @FXML
    private ChoiceBox<String> choice1;

    @FXML
    private ChoiceBox<String> choice2;

    @FXML
    private ChoiceBox<String> choice3;

    @FXML
    private ChoiceBox<String> choice4;

    @FXML
    private ChoiceBox<String> choice5;

    @FXML
    private ChoiceBox<String> choice6;

    @FXML
    private ChoiceBox<String> choice7;

    @FXML
    private ChoiceBox<String> choice8;
    @FXML
    private TextField taskTextField;

    @FXML
    private Button yesq1;

    @FXML
    private Button yesq2;

    @FXML
    private Button yesq3;

    @FXML
    private Button noq1;

    @FXML
    private Button noq2;

    @FXML
    private Button noq3;

    @FXML
    private Button noq4;

    @FXML
    private Button yesq4;

    @FXML
    private TextArea delegateTextArea;

    @FXML
    private TextArea autoTextArea;

    @FXML
    private TextArea delayTextArea;

    @FXML
    private TextArea doTextArea;

    @FXML
    private TextArea deleteTextArea;


    public class Choice{
        public void setChoice(ChoiceBox choiceMood, TextField text){
            if (choiceMood.getValue() == "Сделано"){
                text.setStyle("-fx-text-fill: green;");
            }
            else if(choiceMood.getValue() == "Делается"){
                text.setStyle("-fx-text-fill: blue;");
            }
            else{
                text.setStyle("-fx-text-fill: black;");
            }
        }
        public void setMoods(ChoiceBox choiceMood){
            choiceMood.setItems(mood);
        }
    }

    @FXML
    void initialize() {
        Choice choiceMood = new Choice();
        choiceMood.setMoods(choice1);
        choiceMood.setMoods(choice2);
        choiceMood.setMoods(choice3);
        choiceMood.setMoods(choice4);
        choiceMood.setMoods(choice5);
        choiceMood.setMoods(choice6);
        choiceMood.setMoods(choice7);
        choiceMood.setMoods(choice8);
        choice1.setOnAction(actionEvent -> {
            choiceMood.setChoice(choice1, text1);
        });
        choice2.setOnAction(actionEvent -> {
            choiceMood.setChoice(choice2, text2);
        });
        choice3.setOnAction(actionEvent -> {
            choiceMood.setChoice(choice3, text3);
        });
        choice4.setOnAction(actionEvent -> {
            choiceMood.setChoice(choice4, text4);
        });
        choice5.setOnAction(actionEvent -> {
            choiceMood.setChoice(choice5, text5);
        });
        choice6.setOnAction(actionEvent -> {
            choiceMood.setChoice(choice6, text6);
        });
        choice7.setOnAction(actionEvent -> {
            choiceMood.setChoice(choice7, text7);
        });
        choice8.setOnAction(actionEvent -> {
            choiceMood.setChoice(choice8, text8);
        });

        yesq2.setVisible(false);
        noq2.setVisible(false);
        yesq3.setVisible(false);
        noq3.setVisible(false);
        yesq4.setVisible(false);
        noq4.setVisible(false);
        yesq1.setOnAction(actionEvent -> {
            yesq2.setVisible(true);
            noq2.setVisible(true);
        });
        noq1.setOnAction(actionEvent -> {
            deleteTextArea.appendText(taskTextField.getText());
            deleteTextArea.appendText("\n");
            yesq2.setVisible(false);
            noq2.setVisible(false);
            yesq3.setVisible(false);
            noq3.setVisible(false);
            yesq4.setVisible(false);
            noq4.setVisible(false);
        });
        noq2.setOnAction(actionEvent -> {
            delegateTextArea.appendText(taskTextField.getText());
            delegateTextArea.appendText("\n");
            yesq2.setVisible(false);
            noq2.setVisible(false);
            yesq3.setVisible(false);
            noq3.setVisible(false);
            yesq4.setVisible(false);
            noq4.setVisible(false);
        });
        yesq2.setOnAction(actionEvent -> {
            yesq3.setVisible(true);
            noq3.setVisible(true);
        });
        noq3.setOnAction(actionEvent -> {
            autoTextArea.appendText(taskTextField.getText());
            autoTextArea.appendText("\n");
            yesq2.setVisible(false);
            noq2.setVisible(false);
            yesq3.setVisible(false);
            noq3.setVisible(false);
            yesq4.setVisible(false);
            noq4.setVisible(false);
        });
        yesq3.setOnAction(actionEvent -> {
            yesq4.setVisible(true);
            noq4.setVisible(true);
        });
        noq4.setOnAction(actionEvent -> {
            delayTextArea.appendText(taskTextField.getText());
            delayTextArea.appendText("\n");
            yesq2.setVisible(false);
            noq2.setVisible(false);
            yesq3.setVisible(false);
            noq3.setVisible(false);
            yesq4.setVisible(false);
            noq4.setVisible(false);
        });
        yesq4.setOnAction(actionEvent -> {
            doTextArea.appendText(taskTextField.getText());
            doTextArea.appendText("\n");
            yesq2.setVisible(false);
            noq2.setVisible(false);
            yesq3.setVisible(false);
            noq3.setVisible(false);
            yesq4.setVisible(false);
            noq4.setVisible(false);
        });

    }
}
