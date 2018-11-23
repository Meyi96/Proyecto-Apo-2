package controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewTweetsController {
	@FXML
    private TextArea dataTextArea;

    @FXML
    private Label nameLabel;

    @FXML
    private TextArea newTweetTextArea;

    @FXML
    void OrderNWord(ActionEvent event) {

    }

    @FXML
    void addTweet(ActionEvent event) {

    }

    @FXML
    void back(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/View/SelectionUser.fxml"));
    	Parent parent = loader.load();
    	Scene scene = new Scene(parent);
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void clean(ActionEvent event) {
    	newTweetTextArea.setText("");
    	dataTextArea.setText("nelson me la pela es un Hpta racista, que le gusta la verg...");
    }

    @FXML
    void orderDate(ActionEvent event) {

    }
}