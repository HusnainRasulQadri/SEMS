package frontendLayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import buisnessLayer.Controller;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class createAccount {
	@FXML
	TextField fx_username;
	@FXML
	PasswordField fx_password;
	@FXML
	TextField fx_name;
	@FXML
	private DatePicker fx_DOB;
	@FXML
	private ImageView closeButton;
	@FXML
	private ImageView minimizeButton;
	@FXML
	Label fx_message;

	Stage stage;
	LocalDate dobDate;
	String name;
	String password;
	String rollNumber;

	@FXML
	protected void close() {

	}

	@FXML
	void dateClicked() {
		dobDate = fx_DOB.getValue();
	}

	@FXML
	void nameClicked() {
		//name = fx_name.getText();
		System.out.println("Name: " + name);
	}

	@FXML
	void passwordClicked() {
		//password = fx_password.getText();
		System.out.println("Password: " + password);
	}

	@FXML
	void rollNumberClicked() {
		//rollNumber = fx_username.getText();
		System.out.println("Roll Number: " + rollNumber);
	}

	void getStage(Stage stage){
		this.stage = stage;
		closeButton.setOnMouseClicked(mouseEvent -> stage.close());
		minimizeButton.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
	}

	@FXML
	protected void createAccount( ) throws IOException {
		name = fx_name.getText();
		password = fx_password.getText();
		rollNumber = fx_username.getText();

		if (rollNumber.length()!=8 || rollNumber.charAt(3)!='-' || rollNumber.charAt(2)!='I' ) {
			fx_message.setText("Invalid Input. Please try again.");
		}
		else if (name != null && dobDate != null && rollNumber != null && password != null) {
			Controller.createAccount(name,dobDate.toString(),rollNumber,password);

			//CALLING LOGIN SCREEN
			FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 509, 552);
			stage.setScene(scene);
			((login)fxmlLoader.getController()).getStage(stage);
			stage.show();
		}
		else {
			fx_message.setText("Invalid Input. Please try again.");
		}
	}
}
