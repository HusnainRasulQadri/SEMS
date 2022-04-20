package frontendLayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class HelloApplication extends Application {
	public Stage stage;
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 509, 552);
		this.stage = stage;
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("balls-sports.png")));//setting icon
		stage.setScene(scene);
		((login)fxmlLoader.getController()).getStage(stage);
		stage.show();
	}


	public static void main(String[] args) {
		launch();
	}
}