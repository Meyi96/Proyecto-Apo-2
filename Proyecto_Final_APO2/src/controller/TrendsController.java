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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import model.Hashtag;

public class TrendsController implements Initializable{

    @FXML
    private ListView<String> hashatagListView;

    @FXML
    void OrderNWord(ActionEvent event) {

    }

    @FXML
    void back(MouseEvent event) throws IOException{
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
    	hashatagListView.getItems().clear();
		hashatagListView.getItems().addAll(inicioOrden('e'));
    }

    @FXML
    void ordenarAparicionLexi(ActionEvent event) {
    	hashatagListView.getItems().clear();
		hashatagListView.getItems().addAll(inicioOrden('i'));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hashatagListView.getItems().clear();
		hashatagListView.getItems().addAll(inicioOrden('e'));
	}
	
	public Collection<String> inicioOrden(char tipo){
		hashatagListView.getItems().clear();
    	ArrayList<Object> hashtag = new ArrayList<Object>();
    	Collection<String> datos = new ArrayList<String>();
    	Main.getN().ordenamiento(hashtag, tipo);
    	for (int j = 0; j < hashtag.size() ; j++) {
    		String s = " ";
    		String dato = ((Hashtag)hashtag.get(j)).getPalabra() + " Se repite: "+ ((Hashtag)hashtag.get(j)).getRepeticiones();
    		datos.add(dato);
		}
    	return datos;
	}

}
