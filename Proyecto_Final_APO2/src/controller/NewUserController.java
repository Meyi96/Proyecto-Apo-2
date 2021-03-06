package controller;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import Hilos.Hilo_Interfaz1;
import MyException.DatosEnEspaniolException;
import MyException.TextoVacioException;
import View.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.AbrirLink;

public class NewUserController implements Initializable, AbrirLink{
	
	@FXML
    private TextArea dataTextArea;
	
	  @FXML
	    private Circle cir1;

	    @FXML
	    private Circle cir2;

	    @FXML
	    private Circle cir4;

	    @FXML
	    private Circle cir3;
	    
	    @FXML
	    private Hyperlink hyper;
	    
	    private Hilo_Interfaz1 hilo;

    @FXML
    void add(ActionEvent event) throws IOException {
		try {
	    	String data = dataTextArea.getText();
	    	String[] posibleEspanol = data.split(" ");
	    	if(posibleEspanol[0].equals("Atajos")) {
	    		posibleEspanol = null;
	    		throw new DatosEnEspaniolException();
	    	}else {
	    		posibleEspanol = null;
	    		Main.getN().RegistrarUsuario(data);
		    	System.out.println();
	    	}
	    	dataTextArea.setText("");
	    	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Se a agregado con exito");
    		alert.showAndWait();
		} catch (TextoVacioException | DatosEnEspaniolException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText(e.getMessage());
    		alert.showAndWait();
		}
    }
    

    @FXML
    void clean(ActionEvent event) {
    	dataTextArea.setText("");
    }
    
    @FXML
    void back(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/View/Start.fxml"));
    	Parent parent = loader.load();
    	Scene scene = new Scene(parent);
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
    
    @FXML
    void goToVideo(ActionEvent event) throws IOException, URISyntaxException {
    	URI link = new URI("https://www.youtube.com/watch?v=MhQec4a26gU&feature=youtu.be");
    	goLink(link);
    }
    
    public boolean moverCirculos(boolean a) {
    	if(a) {
    		if(cir1.getLayoutY() <115) {
    			cir1.setLayoutY(cir1.getLayoutY()+1);
    			cir2.setLayoutX(cir2.getLayoutX()-1);
    			cir3.setLayoutY(cir3.getLayoutY()+1);
    			cir4.setLayoutX(cir4.getLayoutX()-1);
    			cir1.setFill(Paint.valueOf("#ffffff"));
    			cir3.setFill(Paint.valueOf("#ffffff"));
    			cir2.setFill(Paint.valueOf("#000000"));
    			cir4.setFill(Paint.valueOf("#000000"));
    		}else
    			a = false;
    	}
    	else {
    		if(cir1.getLayoutY()>15) {
    			cir1.setLayoutY(cir1.getLayoutY()-1);
    			cir2.setLayoutX(cir2.getLayoutX()+1);
    			cir3.setLayoutY(cir3.getLayoutY()-1);
    			cir4.setLayoutX(cir4.getLayoutX()+1);
    			cir2.setFill(Paint.valueOf("#ffffff"));
    			cir4.setFill(Paint.valueOf("#ffffff"));
    			cir1.setFill(Paint.valueOf("#000000"));
    			cir3.setFill(Paint.valueOf("#000000"));
    		}else {
    			a=true;
    		}
    	}
    	return a;
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hilo = new Hilo_Interfaz1(this);
		hilo.start();
	}


	@Override
	public void goLink(URI link) throws IOException{
		Desktop.getDesktop().browse(link);
	}

	

	
}
