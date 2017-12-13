import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI_MainWindow extends Application {
	
	Stage window;
	Scene s1;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		window.show();
	}
	
	
	
}
