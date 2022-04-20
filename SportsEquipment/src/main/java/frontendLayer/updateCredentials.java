package frontendLayer;

import buisnessLayer.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class updateCredentials {
	@FXML
	TextField fx_password;
	@FXML
	TextField fx_username;
	@FXML
	private ImageView closeButton;
	@FXML
	private ImageView minimizeButton;
	@FXML
	Label fx_message;

	Stage stage;
	LocalDate dobDate;
	String name;

	void getStage(Stage stage){
		this.stage = stage;
		closeButton.setOnMouseClicked(mouseEvent -> stage.close());
		minimizeButton.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
	}

	@FXML
	protected void createAccount( ) throws IOException {

		String rollNumber = fx_username.getText();

		if (rollNumber.length()!=8 || rollNumber.charAt(3)!='-' || rollNumber.charAt(2)!='I' ) {
			fx_message.setText("Invalid Input. Please try again.");
		}
		else if (fx_username != null && fx_password != null) {
			Controller.updateCredentials(fx_username.getText(), fx_password.getText());

			//CALLING LOGIN SCREEN
			FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 509, 552);
			stage.setScene(scene);
			((menu) fxmlLoader.getController()).getStage(stage);
			stage.show();
		}
		else {
			fx_message.setText("Invalid Input. Please try again.");
		}

	}
}
