package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import View.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Tweet;
import model.Usuario;

public class ViewTweetsController implements Initializable{

	@FXML
    private ListView<String> dataListView;
	
    @FXML
    private Label nameLabel;
    private Usuario seleccionado;

    @FXML
    void OrderFecha(ActionEvent event) {
    	dataListView.getItems().clear();
    	dataListView.getItems().addAll(inicioOrden('a'));
    }

    @FXML
    void relaciones(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/View/ShowMentions.fxml"));
    	Parent parent = loader.load();
    	Scene scene = new Scene(parent);
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void back(MouseEvent event) throws IOException {
    	Main.getN().setUsuarioActual(null);
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/View/SelectionUser.fxml"));
    	Parent parent = loader.load();
    	Scene scene = new Scene(parent);
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }

    

    @FXML
    void orderPuntajeLikes(ActionEvent event) {
    	dataListView.getItems().clear();
    	dataListView.getItems().addAll(inicioOrden('o'));
    }
    
    public void usuarioSeleccionado(Usuario seleccionado) {
    	this.seleccionado = seleccionado;
    	dataListView.getItems().addAll(inicioOrden('a'));
    }
    
    public Collection<String>inicioOrden(char tipo){
		dataListView.getItems().clear();
    	ArrayList<Object> tweets = new ArrayList<Object>();
    	Collection<String> datos = new ArrayList<String>();
    	Main.getN().ordenamiento(tweets, tipo);
    	for (int j = 1; j < tweets.size() ; j++) {
    		String s = " ";
    		String dato = ((Tweet)tweets.get(j)).getTweetEntero(s) + " \nPuntaje: "+((Tweet)tweets.get(j)).calcularPuntajeTotal();
    		datos.add(dato);
		}
    	return datos;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText(Main.getN().getUsuarioActual().getNombre());
	}
    
}
