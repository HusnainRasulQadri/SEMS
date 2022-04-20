package frontendLayer;

import buisnessLayer.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class login {
	@FXML
	TextField fx_username;
	@FXML
	PasswordField fx_password;
	@FXML
	Label fx_invalidCredentials;
	@FXML
	private ImageView closeButton;
	@FXML
	private ImageView minimizeButton;


	Stage stage;

	@FXML
	protected void onLoginClick() throws IOException {
		//String input = fx_username.getText();
		//System.out.println(input);
		if (Controller.verifyLogin(fx_username.getText(),fx_password.getText())) {
			FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 509, 552);
			stage.setScene(scene);
			((menu)fxmlLoader.getController()).getStage(stage);
			stage.show();
		}
		else {
			fx_invalidCredentials.setText("Invalid Credentials! Please Re-Enter");
			fx_username.setText("");
			fx_password.setText("");
		}
	}

	@FXML
	protected void close() {
		return;
	}

	void getStage(Stage stage){
		this.stage = stage;
		closeButton.setOnMouseClicked(mouseEvent -> stage.close());
		minimizeButton.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
	}

	@FXML
	protected void createAccount( ) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createAccount.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 509, 552);
		stage.setScene(scene);
		((createAccount)fxmlLoader.getController()).getStage(stage);
		stage.show();
	}
}