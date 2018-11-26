package controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import View.Main;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Hashtag;
import model.Link;

public class TrendsLinksController implements Initializable{

	@FXML
    private ListView<String> linksListView;

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
    void ordenOriginal(ActionEvent event) {
    	linksListView.getItems().addAll(inicioOrden('k'));
    }

    @FXML
    void ordenarLinks(ActionEvent event) {
    	linksListView.getItems().addAll(inicioOrden('m'));
    }
    
    public Collection<String> inicioOrden(char tipo){
		linksListView.getItems().clear();
    	ArrayList<Object> links = new ArrayList<Object>();
    	Collection<String> datos = new ArrayList<String>();
    	Main.getN().ordenamiento(links, tipo);
    	for (int j = 0; j < links.size() ; j++) {
    		String s = " ";
    		String dato = ((Link)links.get(j)).getPalabra() ;
    		datos.add(dato);
		}
    	return datos;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		linksListView.getItems().addAll(inicioOrden('k'));
	}

}
