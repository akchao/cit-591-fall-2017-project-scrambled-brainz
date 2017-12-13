import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBoxes {

	public static void display(String title, String message) {
		Stage window = new Stage();
		
		// won't allow selecting another window
		// until this one is taken care of
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Alert!");
		window.setMinWidth(100);
		
		Label label1 = new Label();
		label1.setText(message);
		Button closeButton = new Button("Close this window");
		closeButton.setOnAction(e -> window.close());
		
		// take care of layout
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label1, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene s = new Scene(layout);
		window.setScene(s);
		window.showAndWait();
	}
}
