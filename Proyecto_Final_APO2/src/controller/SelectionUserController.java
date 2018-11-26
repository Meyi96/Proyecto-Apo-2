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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Usuario;

public class SelectionUserController  implements Initializable{

	 @FXML
	    private ListView<String> usersListView;

	    @FXML
	    private Button selection;
	    private ArrayList<Object> usuarios;
	    
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
	    void filterNTweets(ActionEvent event) {
	    	selection.setVisible(false);
	    }

	    @FXML
	    void filterPolitics(ActionEvent event) {
	    	selection.setVisible(false);
	    }

	    @FXML
	    void filterSport(ActionEvent event) {
	    	selection.setVisible(false);
	    }

	    @FXML
	    void filterTechnology(ActionEvent event) {
	    	selection.setVisible(false);
	    }

	    @FXML
	    void orderNTweets(ActionEvent event) {
	    	usersListView.getItems().addAll(inicioOrden('t'));
	    }

	    @FXML
	    void orderName(ActionEvent event) {
	    	usersListView.getItems().addAll(inicioOrden('n'));
	    }

	    @FXML
	    void seletionUser(ActionEvent event) throws IOException {
	    	System.out.println(usersListView.getSelectionModel().getSelectedIndex());
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("/View/ViewTweets.fxml"));
	    	Parent parent = loader.load();
	    	ViewTweetsController controller = (ViewTweetsController)loader.getController();
	    	controller.usuarioSeleccionado((Usuario)usuarios.get(usersListView.getSelectionModel().getSelectedIndex()));
	    	Scene scene = new Scene(parent);
	    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	stage.setScene(scene);
	    	stage.show();
	    }

	    @FXML
	    void userSelectionList(MouseEvent event) {
	    	selection.setVisible(true);
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			usersListView.getItems().addAll(inicioOrden('n'));
		}
		
		public Collection<String>inicioOrden(char tipo){
			usersListView.getItems().clear();
	    	usuarios = new ArrayList<Object>();
	    	Collection<String> datos = new ArrayList<String>();
	    	Main.getN().ordenamiento(usuarios, tipo);
	    	for (int j = 0; j < usuarios.size() ; j++) {
	    		String dato = ((Usuario)usuarios.get(j)).getNombre() + " Cantidad de tweets: " + ((Usuario)usuarios.get(j)).getCantidad();
	    		datos.add(dato);
			}
	    	return datos;
		}
		
		
}
