package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import MyException.PosicionNoExistenteException;
import View.Main;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AbrirLink;
import model.Hashtag;
import model.Link;

public class TrendsLinksController implements Initializable, AbrirLink{

	@FXML
    private ListView<String> linksListView;
	private ArrayList<Object> links;

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
		links = new ArrayList<Object>();
    	Collection<String> datos = new ArrayList<String>();
    	Main.getN().ordenamiento(links, tipo);
    	for (int j = 0; j < links.size() ; j++) {
    		String s = " ";
    		String dato = ((Link)links.get(j)).getPalabra() ;
    		datos.add(dato);
		}
    	return datos;
	}
    
    @FXML
    void openLink(MouseEvent event) throws IOException, URISyntaxException {
    	if(linksListView.getSelectionModel().getSelectedIndex() != -1) {
    		goLink(((Link)links.get(linksListView.getSelectionModel().getSelectedIndex())).getIdentificador());
    	}else {
    		try {
				throw new PosicionNoExistenteException();
			} catch (PosicionNoExistenteException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setContentText(e.getMessage());
	    		alert.showAndWait();
			}
    	}
    }
    
    @Override
	public void goLink(URI link) throws IOException{
		Desktop.getDesktop().browse(link);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		linksListView.getItems().addAll(inicioOrden('k'));
	}

}
