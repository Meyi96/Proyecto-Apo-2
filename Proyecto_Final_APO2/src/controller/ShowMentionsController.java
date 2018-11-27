package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import View.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AbrirLink;
import model.Mencion;
public class ShowMentionsController implements Initializable, AbrirLink {
	
	@FXML
    private Label nombreUsuario;

    @FXML
    private ListView<String> mencionListView;
    private ArrayList menciones;

    @FXML
    void back(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/View/ViewTweets.fxml"));
    	Parent parent = loader.load();
    	Scene scene = new Scene(parent);
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
    
    public Collection<String> organizarCollection() {
    	menciones = Main.getN().getUsuarioActual().getMenciones();
    	Collection<String> datos = new ArrayList<String>();
    	for (int i = 0; i < menciones.size(); i++) {
			datos.add(((Mencion)menciones.get(i)).getPalabra());
		}
    	return datos;
    }
    
    @FXML
    void mostrarPerfil(MouseEvent event) throws IOException{
    	if(mencionListView.getSelectionModel().getSelectedIndex() != -1) {
    		goLink(((Mencion)menciones.get(mencionListView.getSelectionModel().getSelectedIndex())).conseguirURL());
    	}
    }
    
    @Override
	public void goLink(URI link) throws IOException{
		Desktop.getDesktop().browse(link);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nombreUsuario.setText(Main.getN().getUsuarioActual().getNombre());
		mencionListView.getItems().addAll(organizarCollection());
	}
}
