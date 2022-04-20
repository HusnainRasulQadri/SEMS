package frontendLayer;

import buisnessLayer.Controller;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import serviceLayer.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class issueEquipment implements Initializable {
	@FXML
	public ListView<String> list_view;

	ObservableList<String> list;

	@FXML
	private Label message;

	@FXML
	private ImageView closeButton;
	@FXML
	private ImageView minimizeButton;
	private Stage stage;
	void getStage(Stage stage){
		this.stage = stage;
		closeButton.setOnMouseClicked(mouseEvent -> stage.close());
		minimizeButton.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
	}

	@FXML
	void issueEquipment() throws IOException, InterruptedException {
		String item = list_view.getSelectionModel().getSelectedItem();
		if (item != null) {
			String ref_id = item.substring(0,7);
			if (!Controller.issueEquipment(ref_id)){
				message.setText("Item already issued. Returning to back in 5s");
				sleep(5000);
			}
			//CALLING LOGIN SCREEN
			FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 509, 552);
			stage.setScene(scene);
			((menu) fxmlLoader.getController()).getStage(stage);
			stage.show();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<String> items = DBHandler.getIssuableItems();
		list = FXCollections.observableArrayList(items);
		list_view.setItems(list);
		list_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
}
