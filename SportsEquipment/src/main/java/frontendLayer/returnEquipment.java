package frontendLayer;

import buisnessLayer.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import serviceLayer.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class returnEquipment implements Initializable {
	@FXML private Label ref_id_val;
	@FXML private Label name_val;
	@FXML private Label comments_val;

	@FXML private ImageView closeButton;
	@FXML private ImageView minimizeButton;

	private Stage stage;
	void getStage(Stage stage){
		this.stage = stage;
		closeButton.setOnMouseClicked(mouseEvent -> stage.close());
		minimizeButton.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		String data = Controller.getIssue();
		if (data != null) {
			ArrayList<String> values = new ArrayList<String>(Arrays.asList(data.split(",")));
			ref_id_val.setText(values.get(0));
			name_val.setText(values.get(1));
			comments_val.setText(values.get(2));
		}
	}

	@FXML
	void returnEquipment() throws IOException {
		Controller.returnIssue();
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 509, 552);
		stage.setScene(scene);
		((menu) fxmlLoader.getController()).getStage(stage);
		stage.show();
	}
}
