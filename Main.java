package cafe;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
This class launches the JavaFX application
Places user interface controls in a scene and displays it in a stage.
@author Dharma Wijesinghe, Min Sun You
*/
public class Main extends Application {
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	
	/**
	Default constructor for Main class.
	*/
	public Main() {
	    
	}
	
    /**
    Creates a scene and displays it in a stage.
    @param primaryStage the stage.
    */
    @Override
	public void start(Stage primaryStage) {
		try {
		    //When the FXLLoader loaded the .fxml, the associated controller is also instantiated
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CafeMain.fxml"));
			Scene scene = new Scene(root, WIDTH, HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
    Launches the JavaFX application.
    @param args the command line arguments
    */
	public static void main(String[] args) {
		launch(args);
	}
}