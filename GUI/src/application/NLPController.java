package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.omg.CORBA.INITIALIZE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NLPController implements Initializable {
		
	// to open the dialog for selecting a book
	
	@FXML
	private Button selectBook;
	// to list the title of the current book
	@FXML
	private Text currentBook;
	// to select a new book
	
	@FXML LineChart<Integer, Double> lineChart;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		set currentBook text
		currentBook.setText("No book chosen. Yet...");
	}
	
	/**
	 * method to display list of books from which
	 * user can select to analyze 
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void displayBookList(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("BookSelect.fxml"));
		stage.setScene(new Scene(root));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(selectBook.getScene().getWindow());
		stage.showAndWait();
	}
	
	public void populateGraph(BookDummy book) {
		XYChart.Series<Integer, Double> series = new XYChart.Series<>();
		
		series.getData().add(new XYChart.Data<Integer, Double>(1, book.getAnger(0)));
		series.getData().add(new XYChart.Data<Integer, Double>(2, book.getAnger(1)));
		series.getData().add(new XYChart.Data<Integer, Double>(3, book.getAnger(2)));
		
		lineChart.getData().add(series);
	}
}
