package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class NLPController {
	
	@FXML
	private Button selectBook;
	@FXML
	private Text currentBook;
	
	@FXML LineChart<String, Number> lineChart;
	
	/**
	 * method to display list of books from which
	 * user can select to analyze 
	 * @param event
	 */
	@FXML
	public void displayBookList(ActionEvent event) {
		
	}
}
