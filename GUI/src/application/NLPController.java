package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NLPController implements Initializable {
	
	private HashMap<String, BookDummy> titleToBook = new HashMap<String, BookDummy>();	

	private ObservableList<String> list;

	@FXML
    private Text currentBook;
    @FXML
    private ListView<String> bookList;
    @FXML
    private Button selectBook;
    @FXML
    private Button compareWith;
    @FXML
    private LineChart<String, Double> lineChart;

    
    @FXML
    private CheckBox sadCheck;
    @FXML
    private CheckBox joyCheck;
    @FXML
    private CheckBox fearCheck;
    @FXML
    private CheckBox disgustCheck;
    @FXML
    private CheckBox angerCheck;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		set currentBook text
		currentBook.setText("No book chosen. Yet...");
		
		BookDummy b1 = new BookDummy("Help!");
		BookDummy b2 = new BookDummy("Abbey Road");	
		
		titleToBook.put(b1.getTitle(), b1);
		titleToBook.put(b2.getTitle(), b2);
		
		list = FXCollections.observableArrayList(b1.getTitle(), b2.getTitle());
	
		bookList.setItems(list);
	}
    
    
    @FXML
    void generateGraph(ActionEvent event) {
    	String bd;
		bd = bookList.getSelectionModel().getSelectedItem();
		currentBook.setText(bd);
		
	    BookDummy book = titleToBook.get(bd);

    	
    	lineChart.getData().clear();
		
		XYChart.Series<String, Double> anger = new XYChart.Series<>();
		
		anger.getData().add(new XYChart.Data<String, Double>("1", book.getAnger(0)));
		anger.getData().add(new XYChart.Data<String, Double>("2", book.getAnger(1)));
		anger.getData().add(new XYChart.Data<String, Double>("3", book.getAnger(2)));
		anger.setName("Anger");
		lineChart.getData().add(anger);
		
		XYChart.Series<String, Double> joy = new XYChart.Series<>();
		
		joy.getData().add(new XYChart.Data<String, Double>("1", book.getJoy(0)));
		joy.getData().add(new XYChart.Data<String, Double>("2", book.getJoy(1)));
		joy.getData().add(new XYChart.Data<String, Double>("3", book.getJoy(2)));
		joy.setName("Joy");
		lineChart.getData().add(joy);
		
		XYChart.Series<String, Double> disgust = new XYChart.Series<>();
		
		disgust.getData().add(new XYChart.Data<String, Double>("1", book.getDisgust(0)));
		disgust.getData().add(new XYChart.Data<String, Double>("2", book.getDisgust(1)));
		disgust.getData().add(new XYChart.Data<String, Double>("3", book.getDisgust(2)));
		disgust.setName("Disgust");
		lineChart.getData().add(disgust);
		
		XYChart.Series<String, Double> sadness = new XYChart.Series<>();
		
		sadness.getData().add(new XYChart.Data<String, Double>("1", book.getSadness(0)));
		sadness.getData().add(new XYChart.Data<String, Double>("2", book.getSadness(1)));
		sadness.getData().add(new XYChart.Data<String, Double>("3", book.getSadness(2)));
		sadness.setName("Sadness");
		lineChart.getData().add(sadness);
		
		XYChart.Series<String, Double> fear = new XYChart.Series<>();
		
		fear.getData().add(new XYChart.Data<String, Double>("1", book.getFear(0)));
		fear.getData().add(new XYChart.Data<String, Double>("2", book.getFear(1)));
		fear.getData().add(new XYChart.Data<String, Double>("3", book.getFear(2)));
		fear.setName("Joy");
		lineChart.getData().add(fear);
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@FXML
//	private Button selectBook;
//	// to list the title of the current book
//	@FXML
//	private Text currentBook;
//	@FXML
//	private Button compareWith;
//	
//	@FXML LineChart<String, Double> lineChart;
//	
//	private BookDummy book;
//	
//	
//
//	
//	/**
//	 * method to display list of books from which
//	 * user can select to analyze 
//	 * @param event
//	 * @throws IOException 
//	 */
//	@FXML
//	public void displayBookList(ActionEvent event) throws IOException {
//		Stage stage;
//		Parent root;
//		stage = new Stage();
//		root = FXMLLoader.load(getClass().getResource("BookSelect.fxml"));
//		stage.setScene(new Scene(root));
//		stage.initModality(Modality.APPLICATION_MODAL);
//		stage.initOwner(selectBook.getScene().getWindow());
//		stage.showAndWait();
//	}
//	
//	@FXML
//	public void populateGraph(ActionEvent event) {
//		BookSelectController bsc = new BookSelectController();
//		
//		BookDummy book = bsc.returnSelectedBook();
//		
//		
//		
////		lineChart.getData().clear();
//		
//		System.out.println(book.getAnger(1));
//		
////		BookDummy book = bsc.returnSelectedBook();
//		
//		XYChart.Series<String, Double> anger = new XYChart.Series<>();
//		
//		anger.getData().add(new XYChart.Data<String, Double>("1", book.getAnger(0)));
//		anger.getData().add(new XYChart.Data<String, Double>("2", book.getAnger(1)));
//		anger.getData().add(new XYChart.Data<String, Double>("3", book.getAnger(2)));
//		anger.setName("Anger");
////		lineChart.getData().add(anger);
//		
//		XYChart.Series<String, Double> joy = new XYChart.Series<>();
//		
//		joy.getData().add(new XYChart.Data<String, Double>("1", book.getJoy(0)));
//		joy.getData().add(new XYChart.Data<String, Double>("2", book.getJoy(1)));
//		joy.getData().add(new XYChart.Data<String, Double>("3", book.getJoy(2)));
//		joy.setName("Joy");
//		
////		lineChart.getData().add(joy);
//		
//		
//		
//	}
//	
//	public void setBook(BookDummy book) {
//		this.book = book;
//	}
}
