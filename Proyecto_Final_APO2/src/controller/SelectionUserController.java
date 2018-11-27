package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import MyException.PosicionNoExistenteException;
import View.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
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
	    	Main.getN().setUsuarioActual(null);
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("/View/Start.fxml"));
	    	Parent parent = loader.load();
	    	Scene scene = new Scene(parent);
	    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	stage.setScene(scene);
	    	stage.show();
	    }

	    @FXML
	    void orderNTweets(ActionEvent event) {
	    	usersListView.getItems().addAll(inicioOrden('t'));
	    	Main.getN().setUsuarioActual(null);
	    }

	    @FXML
	    void orderName(ActionEvent event) {
	    	usersListView.getItems().addAll(inicioOrden('n'));
	    	Main.getN().setUsuarioActual(null);
	    }

	    @FXML
	    void seletionUser(ActionEvent event) throws IOException {
	    	System.out.println(usersListView.getSelectionModel().getSelectedIndex());
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("/View/ViewTweets.fxml"));
	    	Parent parent = loader.load();
	    	ViewTweetsController controller = (ViewTweetsController)loader.getController();
	    	controller.usuarioSeleccionado(Main.getN().getUsuarioActual());
	    	Scene scene = new Scene(parent);
	    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    	stage.setScene(scene);
	    	stage.show();
	    }

	    @FXML
	    void userSelectionList(MouseEvent event) {
	    	if(usersListView.getSelectionModel().getSelectedIndex() != -1) {
	    		Main.getN().setUsuarioActual((Usuario)usuarios.get(usersListView.getSelectionModel().getSelectedIndex()));
	    		selection.setVisible(true);
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
