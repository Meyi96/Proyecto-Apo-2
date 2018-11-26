package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class TrendsLinksController implements Initializable{

	@FXML
    private ListView<String> hashatagListView;

    @FXML
    void back(MouseEvent event) {

    }

    @FXML
    void linkSelected(MouseEvent event) {
    	try {
			Desktop.getDesktop().browse(new URI(hashatagListView.getSelectionModel().getSelectedItem()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void ordenOriginal(ActionEvent event) {

    }

    @FXML
    void ordenarAparicionLexi(ActionEvent event) {

    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
