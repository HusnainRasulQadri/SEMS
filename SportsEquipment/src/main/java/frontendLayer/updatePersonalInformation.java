package frontendLayer;

import buisnessLayer.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class updatePersonalInformation {
	@FXML
	TextField fx_name;
	@FXML
	private DatePicker fx_DOB;
	@FXML
	private ImageView closeButton;
	@FXML
	private ImageView minimizeButton;

	Stage stage;
	LocalDate dobDate;
	String name;

	@FXML
	void dateClicked() {
		dobDate = fx_DOB.getValue();
	}

	void getStage(Stage stage){
		this.stage = stage;
		closeButton.setOnMouseClicked(mouseEvent -> stage.close());
		minimizeButton.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
	}

	@FXML
	protected void createAccount( ) throws IOException {
		name = fx_name.getText();

		if (name != null && dobDate != null) {
			Controller.updatePersonalInfo(name, dobDate.toString());

			//CALLING LOGIN SCREEN
			FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 509, 552);
			stage.setScene(scene);
			((menu) fxmlLoader.getController()).getStage(stage);
			stage.show();
		}
		System.out.println("Name: " + name);
		System.out.println("DOB: " + dobDate.toString());
	}
}
