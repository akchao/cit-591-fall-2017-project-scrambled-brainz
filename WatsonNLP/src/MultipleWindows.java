import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultipleWindows extends Application{

	Stage window;
	Scene s1, s2;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		Label label1 = new Label("Welcome to the first scene");
		Button button1 = new Button("Go to scene 2");
		button1.setOnAction(e -> window.setScene(s2));
		
		// Layout 1 - childern are in a vertical column
		// VBox --> stacks all objects in a columns
		VBox layout1 = new VBox(20);
		
		layout1.getChildren().addAll(label1, button1);
		
		s1 = new Scene(layout1, 200, 200);
		
		// Button 2
		Button button2 = new Button("Go to scene 1");
		button2.setOnAction(e -> window.setScene(s1));
		Button button3 = new Button("Alert!");
		button3.setOnAction(e-> AlertBoxes.display("Alert!", "Warning!"));
		
		VBox layout2 = new VBox(12);
		layout2.getChildren().addAll(button2, button3);
		
		s2 = new Scene(layout2, 300, 300);
		
		window.setTitle("Yo");
		window.setScene(s1);
		window.show();
	}

}
