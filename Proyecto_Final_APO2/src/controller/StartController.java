package controller;
import java.io.IOException;

import View.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class StartController {

	@FXML
    private Label addLabal;

    @FXML
    private Label searchLabel;

    @FXML
    private Label viewLabel;

    @FXML
    void addUser(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/View/NewUser.fxml"));
    	Parent parent = loader.load();
    	Scene scene = new Scene(parent);
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void more(ActionEvent event) {
    	Main.getN().getAlfa();
    }

    @FXML
    void searchUser(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/View/SelectionUser.fxml"));
    	Parent parent = loader.load();
    	Scene scene = new Scene(parent);
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
    
    @FXML
    void closeLabel(MouseEvent event) {
    	addLabal.setVisible(false);
    	searchLabel.setVisible(false);
    	viewLabel.setVisible(false);
    }
    
    @FXML
    void searchvisible(MouseEvent event) {
    	searchLabel.setVisible(true);
    }

    @FXML
    void viewVisible(MouseEvent event) {
    	viewLabel.setVisible(true);
    }
    
    @FXML
    void addVisible(MouseEvent event) {
    	addLabal.setVisible(true);
    }

    
}
