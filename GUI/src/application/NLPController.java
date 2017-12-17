package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;

public class NLPController implements Initializable {
	
	// mapping between book title to the book object itself
	private HashMap<String, BookDummy> titleToBook = new HashMap<String, BookDummy>();	

	// list of books to display
	private ObservableList<String> list;
	
	// "switch" to determine if should plot book comparisons or not
	private Boolean booksCompared;
	
	// tabs for current
	@FXML
    private Tab currentTitleTab;

    @FXML
    private Tab currentAuthorTab;

    @FXML
    private Tab currentYearTab;

    // tabs for compare
	@FXML
    private Tab compareTitleTab;

    @FXML
    private Tab compareAuthorTab;

    @FXML
    private Tab compareYearTab;

	// dynamic text for the application
	@FXML
    private Text currentBook;
	@FXML
	private Text comparedBook;
	
	// lists to display
    @FXML
    private ListView<String> bookList;
    @FXML
    private ListView<String> comparisonList;
    
    // tree view of sort by author
    @FXML
    private TreeView<String> currentAuthorTree;
    @FXML
    private TreeView<String> compareAuthorTree;
    
    // tree view of sort by year
    @FXML
    private TreeView<String> currentYearTree;
    @FXML
    private TreeView<String> compareYearTree;
    
    // buttons
    @FXML
    private Button selectBook;
    @FXML
    private Button compareWith;
    @FXML
    private Button updateGraph;
    @FXML
    
    // the line chart
    private LineChart<String, Double> lineChart;

    // checkboxes    
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
    
    /**
     * method to set up initial features
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// set to false
		booksCompared = false;
		
		// set currentBook text
		currentBook.setText("No book chosen. Yet...");
		// set comparedBook text
		comparedBook.setText("None");
		
		// dummy books to load into the bookList
				
		BookDummy b1 = new BookDummy("Help!", "The Beatles", 1965);
		BookDummy b2 = new BookDummy("Abbey Road", "The Beatles", 1969);
		BookDummy b3 = new BookDummy("Let it Bleed", "The Rolling Stones", 1969);
		BookDummy b4 = new BookDummy("Purple Rain", "Prince", 1984);
		
		titleToBook.put(b1.getTitle(), b1);
		titleToBook.put(b2.getTitle(), b2);
		titleToBook.put(b3.getTitle(), b3);
		titleToBook.put(b4.getTitle(), b4);
		
		
		// create tree view for authors
		ArrayList<BookDummy> bds = new ArrayList<BookDummy>(titleToBook.values());
		
		HashMap<String, ArrayList<String>> authorList = new HashMap<String, ArrayList<String>>();
		
		// create new HashMap of authors to list of their ouvre if
		// doesn't already exist
		// if there is already a mapping, add book to list of author's
		// ouvre
		for (BookDummy bd : bds) {
			if (!authorList.containsKey(bd.getAuthor())) {
				ArrayList<String> authorOuvre = new ArrayList<String>();
				authorOuvre.add(bd.getTitle());
				authorList.put(bd.getAuthor(), authorOuvre);
			} else {
				authorList.get(bd.getAuthor()).add(bd.getTitle());
			}
		}

		TreeItem<String> dummyAuthorRoot = new TreeItem<String>("dummy");
		
		// iterate through each author in the list of authors
		for (String author : authorList.keySet()) {
			// make a new root for that author
			TreeItem<String> authorRoot = new TreeItem<String>(author);
			// set default so author's list isn't expanded
			authorRoot.setExpanded(false);
			// go through each title in the author's list
			for (String title : authorList.get(author)) {
				// make a new tree item for each title
				authorRoot.getChildren().add(new TreeItem<String>(title));	
			}
			// add authorRoot to dummyRoot
			dummyAuthorRoot.getChildren().add(authorRoot);
		}
		// set dummyRoot as the main root
		currentAuthorTree.setRoot(dummyAuthorRoot);
		// set dummyRoot to be hidden
		currentAuthorTree.setShowRoot(false);
		
		// set for compare tree as well
		compareAuthorTree.setRoot(dummyAuthorRoot);
		compareAuthorTree.setShowRoot(false);

		
		HashMap<Integer, ArrayList<String>> yearList = new HashMap<Integer, ArrayList<String>>();
		
		// create new HashMap of years to list of their ouvre if
		// doesn't already exist
		// if there is already a mapping, add book to list of year's
		// ouvre
		for (BookDummy bd : bds) {
			if (!yearList.containsKey(bd.getYear())) {
				ArrayList<String> yearOuvre = new ArrayList<String>();
				yearOuvre.add(bd.getTitle());
				yearList.put(bd.getYear(), yearOuvre);
			} else {
				yearList.get(bd.getYear()).add(bd.getTitle());
			}
		}

		TreeItem<String> dummyYearRoot = new TreeItem<String>("dummy");
		
		// iterate through each year in the list of years
		for (Integer year : yearList.keySet()) {
			// make a new root for that year
			TreeItem<String> yearRoot = new TreeItem<String>(year.toString());
			// set default so year's list isn't expanded
			yearRoot.setExpanded(false);
			// go through each title in the year's list
			for (String title : yearList.get(year)) {
				// make a new tree item for each title
				yearRoot.getChildren().add(new TreeItem<String>(title));	
			}
			// add authorRoot to dummyYearRoot
			dummyYearRoot.getChildren().add(yearRoot);
		}
		// set dummyRoot as the main root
		currentYearTree.setRoot(dummyYearRoot);
		// set dummyRoot to be hidden
		currentYearTree.setShowRoot(false);
		
		// set for compare tree as well
		compareYearTree.setRoot(dummyYearRoot);
		compareYearTree.setShowRoot(false);

		
		// have all books loaded into the list
		list = FXCollections.observableArrayList(titleToBook.keySet());
		bookList.setItems(list);

		
		// have check-boxes preset to be checked
		sadCheck.fire();
		joyCheck.fire();
		fearCheck.fire();
		disgustCheck.fire();
		angerCheck.fire();
	}
    
    /**
     * method to generate the graph
     * @param event when a button has been clicked
     */
    @FXML
    void generateGraph(ActionEvent event) {
    	// clear the graph
    	lineChart.getData().clear();
    	
    	// make a second list for books to compare
    	// will display all books except for the one that is currently selected
    	ObservableList<String> list2 = FXCollections.observableArrayList((list));
    	
    	String bd = null;
    	
    	// if are selecting a book without comparing
    	if (event.getSource().equals(selectBook) || event.getSource().equals(updateGraph) ||
    			event.getSource().equals(compareWith)) {
    		// get the selected book
    		if (currentTitleTab.isSelected()) {
    			bd = bookList.getSelectionModel().getSelectedItem();
    		} else if (currentAuthorTab.isSelected()) {
    			bd = currentAuthorTree.getSelectionModel().getSelectedItem().getValue();
    		} else if (currentYearTab.isSelected())  {
    			bd = currentYearTree.getSelectionModel().getSelectedItem().getValue();
    		}
    		
    		// set the currentBook text
    		currentBook.setText(bd);
    		// remove this book from the comparison list
    		list2.remove(bd);
    		// set the comparison list
        	comparisonList.setItems(list2);
        	
        	// book dummy object
    	    BookDummy book = titleToBook.get(bd);

    	    // declare series
    	    XYChart.Series<String, Double> anger = new XYChart.Series<>();
    		XYChart.Series<String, Double> fear = new XYChart.Series<>();
    		XYChart.Series<String, Double> sadness = new XYChart.Series<>();
    		XYChart.Series<String, Double> disgust = new XYChart.Series<>();
    		XYChart.Series<String, Double> joy = new XYChart.Series<>();

    		// plot series data
    		
    		//anger
    		anger.getData().add(new XYChart.Data<String, Double>("1", book.getAnger(0)));
    		anger.getData().add(new XYChart.Data<String, Double>("2", book.getAnger(1)));
    		anger.getData().add(new XYChart.Data<String, Double>("3", book.getAnger(2)));
    		anger.setName(bd + ": Anger");
    		if (angerCheck.isSelected()) {
    			lineChart.getData().add(anger);
    		}
    		
    		// joy
    		joy.getData().add(new XYChart.Data<String, Double>("1", book.getJoy(0)));
    		joy.getData().add(new XYChart.Data<String, Double>("2", book.getJoy(1)));
    		joy.getData().add(new XYChart.Data<String, Double>("3", book.getJoy(2)));
    		joy.setName(bd + ": Joy");
    		if (joyCheck.isSelected()) {
    			lineChart.getData().add(joy);
    		}		
    		
    		// disgust
    		disgust.getData().add(new XYChart.Data<String, Double>("1", book.getDisgust(0)));
    		disgust.getData().add(new XYChart.Data<String, Double>("2", book.getDisgust(1)));
    		disgust.getData().add(new XYChart.Data<String, Double>("3", book.getDisgust(2)));
    		disgust.setName(bd + ": Disgust");
    		if (disgustCheck.isSelected()) {
    			lineChart.getData().add(disgust);
    		}
    		
    		// sadness
    		sadness.getData().add(new XYChart.Data<String, Double>("1", book.getSadness(0)));
    		sadness.getData().add(new XYChart.Data<String, Double>("2", book.getSadness(1)));
    		sadness.getData().add(new XYChart.Data<String, Double>("3", book.getSadness(2)));
    		sadness.setName(bd + ": Sadness");
    		if (sadCheck.isSelected()) {
    			lineChart.getData().add(sadness);
    		}
    		
    		fear.getData().add(new XYChart.Data<String, Double>("1", book.getFear(0)));
    		fear.getData().add(new XYChart.Data<String, Double>("2", book.getFear(1)));
    		fear.getData().add(new XYChart.Data<String, Double>("3", book.getFear(2)));
    		fear.setName(bd + ": Fear");
    		if (fearCheck.isSelected()) {
    			lineChart.getData().add(fear);
    		}
    	}
    	// if are comparing two books
    	if (event.getSource().equals(compareWith) || (event.getSource().equals(updateGraph) &&
    			booksCompared)) {
    		// get the selected book
    		if (compareTitleTab.isSelected()) {
    			bd = comparisonList.getSelectionModel().getSelectedItem();
    		} else if (compareAuthorTab.isSelected()) {
    			bd = compareAuthorTree.getSelectionModel().getSelectedItem().getValue();
    		} else if (compareYearTab.isSelected())  {
    			bd = compareYearTree.getSelectionModel().getSelectedItem().getValue();
    		}
    		
    		// set comparedBook text
    		comparedBook.setText(bd);
        	// set "switch" to true
    		booksCompared = true;
        	
    		// new book dummy
    	    BookDummy book = titleToBook.get(bd);

    	    // declare series
    	    XYChart.Series<String, Double> anger = new XYChart.Series<>();
    		XYChart.Series<String, Double> fear = new XYChart.Series<>();
    		XYChart.Series<String, Double> sadness = new XYChart.Series<>();
    		XYChart.Series<String, Double> disgust = new XYChart.Series<>();
    		XYChart.Series<String, Double> joy = new XYChart.Series<>();

    		// plot series
    		anger.getData().add(new XYChart.Data<String, Double>("1", book.getAnger(0)));
    		anger.getData().add(new XYChart.Data<String, Double>("2", book.getAnger(1)));
    		anger.getData().add(new XYChart.Data<String, Double>("3", book.getAnger(2)));
    		anger.setName(bd + ": Anger");
    		if (angerCheck.isSelected()) {
    			lineChart.getData().add(anger);
    		}
    		
    		joy.getData().add(new XYChart.Data<String, Double>("1", book.getJoy(0)));
    		joy.getData().add(new XYChart.Data<String, Double>("2", book.getJoy(1)));
    		joy.getData().add(new XYChart.Data<String, Double>("3", book.getJoy(2)));
    		joy.setName(bd + ": Joy");
    		if (joyCheck.isSelected()) {
    			lineChart.getData().add(joy);
    		}		
    		
    		disgust.getData().add(new XYChart.Data<String, Double>("1", book.getDisgust(0)));
    		disgust.getData().add(new XYChart.Data<String, Double>("2", book.getDisgust(1)));
    		disgust.getData().add(new XYChart.Data<String, Double>("3", book.getDisgust(2)));
    		disgust.setName(bd + ": Disgust");
    		if (disgustCheck.isSelected()) {
    			lineChart.getData().add(disgust);
    		}
    		
    		sadness.getData().add(new XYChart.Data<String, Double>("1", book.getSadness(0)));
    		sadness.getData().add(new XYChart.Data<String, Double>("2", book.getSadness(1)));
    		sadness.getData().add(new XYChart.Data<String, Double>("3", book.getSadness(2)));
    		sadness.setName(bd + ": Sadness");
    		if (sadCheck.isSelected()) {
    			lineChart.getData().add(sadness);
    		}
    		
    		fear.getData().add(new XYChart.Data<String, Double>("1", book.getFear(0)));
    		fear.getData().add(new XYChart.Data<String, Double>("2", book.getFear(1)));
    		fear.getData().add(new XYChart.Data<String, Double>("3", book.getFear(2)));
    		fear.setName(bd + ": Fear");
    		if (fearCheck.isSelected()) {
    			lineChart.getData().add(fear);
    		}
    	}
    }
     
    /**
     * method for Undo Comparison button
     * @param event when the undo comparison button is clicked
     */
    @FXML
    public void undoCompare(ActionEvent event) {
    	// turns "switch" off
    	booksCompared = false;
    	
    	// resets comparedBook text
    	comparedBook.setText("None");
    }
}
