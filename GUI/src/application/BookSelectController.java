package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BookSelectController implements Initializable {
	
	private ObservableList<String> list;
	
	private HashMap<String, BookDummy> titleToBook = new HashMap<String, BookDummy>();	
	
	@FXML
	private Button selectBook2;
	// list view of all books
	@FXML
	private ListView<String> bookList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BookDummy b1 = new BookDummy("Help!");
		BookDummy b2 = new BookDummy("Abbey Road");	
		
		titleToBook.put(b1.getTitle(), b1);
		titleToBook.put(b2.getTitle(), b2);
		
		list = FXCollections.observableArrayList(b1.getTitle(), b2.getTitle());
	
		bookList.setItems(list);
	}
	
	
	@FXML
	public BookDummy selectBook(ActionEvent event) {
		String bd;
		bd = bookList.getSelectionModel().getSelectedItem();
		
		Stage stage = (Stage) selectBook2.getScene().getWindow();
	    stage.close();
	    System.out.println(bd);
	    NLPController nlp = new NLPController();
	    nlp.populateGraph(titleToBook.get(bd));

		return titleToBook.get(bd);
	}
	
	
}
