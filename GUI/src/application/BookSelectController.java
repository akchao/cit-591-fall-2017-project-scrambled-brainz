package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class BookSelectController implements Initializable {
	
	private ObservableList<BookDummy> list;
	
	@FXML
	private Button selectBook2;
	// list view of all books
	@FXML
	private ListView<BookDummy> bookList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BookDummy b1 = new BookDummy("Help");
		BookDummy b2 = new BookDummy("Abbey Road");	
		
		list = FXCollections.observableArrayList(b1, b2);
	
		bookList.setItems(list);
	}
	
}
