package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Hilos.Hilo_Interfaz;
import View.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class StartController implements Initializable {

	@FXML
    private Label addLabal;

    @FXML
    private Label searchLabel;

    @FXML
    private Label viewLabel;
    
    @FXML
    private Circle cir1;

    @FXML
    private Circle cir2;

    @FXML
    private Circle cir3;
    
    private Hilo_Interfaz hilo;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	hilo = new Hilo_Interfaz(this);
    	hilo.start();
	}

    @FXML
    void addUser(ActionEvent event) throws IOException {
    	hilo.stop();
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
    	hilo.stop();
    	System.out.println(Main.getN().getAlfa());
    }

    @FXML
    void searchUser(ActionEvent event) throws IOException {
    	hilo.stop();
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
    
    public void cambioColores(String[] color, int a) {
    	if(a ==1) {
    		cir1.setFill(Paint.valueOf(color[0]));
    		cir2.setFill(Paint.valueOf(color[1]));
    		cir3.setFill(Paint.valueOf(color[2]));
    	}else if(a ==2) {
    		cir1.setFill(Paint.valueOf(color[2]));
    		cir2.setFill(Paint.valueOf(color[0]));
    		cir3.setFill(Paint.valueOf(color[1]));
    	}else {
    		cir1.setFill(Paint.valueOf(color[1]));
    		cir2.setFill(Paint.valueOf(color[2]));
    		cir3.setFill(Paint.valueOf(color[0]));
    	}
    }

	

    
}
