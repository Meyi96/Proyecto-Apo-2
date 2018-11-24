package controller;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import View.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewUserController {
	
	@FXML
    private TextArea dataTextArea;

    @FXML
    void add(ActionEvent event) throws IOException {
    	String data = dataTextArea.getText();
    	Main.getN().RegistrarUsuario(data);
    	System.out.println();
    }
    
    @FXML
    private Hyperlink hyper;
    
    public void goLink(String link) throws IOException, URISyntaxException {
    	Desktop.getDesktop().browse(new URI(link));
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
    	goLink("https://www.youtube.com/watch?v=MhQec4a26gU&feature=youtu.be");
    }

	
}
