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
	private HashMap<String, BookObj> titleToBook = new HashMap<String, BookObj>();	

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
		
		// get all books
		BookObjDataReader bodr = new BookObjDataReader();
		
		ArrayList<BookObj> books = new ArrayList<BookObj>(bodr.getBooks());

		for (BookObj book: books) {
			titleToBook.put(book.getTitle(), book);
		}
				
		HashMap<String, ArrayList<String>> authorList = new HashMap<String, ArrayList<String>>();
		
		// create new HashMap of authors to list of their ouvre if
		// doesn't already exist
		// if there is already a mapping, add book to list of author's
		// ouvre
		for (BookObj book : books) {
			if (!authorList.containsKey(book.getAuthor())) {
				ArrayList<String> authorOuvre = new ArrayList<String>();
				authorOuvre.add(book.getTitle());
				authorList.put(book.getAuthor(), authorOuvre);
			} else {
				authorList.get(book.getAuthor()).add(book.getTitle());
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
		for (BookObj book : books) {
			if (!yearList.containsKey(book.getPubDate())) {
				ArrayList<String> yearOuvre = new ArrayList<String>();
				yearOuvre.add(book.getTitle());
				yearList.put(book.getPubDate(), yearOuvre);
			} else {
				yearList.get(book.getPubDate()).add(book.getTitle());
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
    	
    	String title = null;
    	
    	// if are selecting a book without comparing
    	if (event.getSource().equals(selectBook) || event.getSource().equals(updateGraph) ||
    			event.getSource().equals(compareWith)) {
    		// get the selected book
    		if (currentTitleTab.isSelected()) {
    			title = bookList.getSelectionModel().getSelectedItem();
    		} else if (currentAuthorTab.isSelected()) {
    			title = currentAuthorTree.getSelectionModel().getSelectedItem().getValue();
    		} else if (currentYearTab.isSelected())  {
    			title = currentYearTree.getSelectionModel().getSelectedItem().getValue();
    		}
    		
    		// set the currentBook text
    		currentBook.setText(title);
    		// remove this book from the comparison list
    		list2.remove(title);
    		// set the comparison list
        	comparisonList.setItems(list2);
        	
        	// book object
    	    BookObj book = titleToBook.get(title);

    	    // declare series
    	    XYChart.Series<String, Double> anger = new XYChart.Series<>();
    		XYChart.Series<String, Double> fear = new XYChart.Series<>();
    		XYChart.Series<String, Double> sadness = new XYChart.Series<>();
    		XYChart.Series<String, Double> disgust = new XYChart.Series<>();
    		XYChart.Series<String, Double> joy = new XYChart.Series<>();

    		// plot series data
    		
    		//anger
    		for (int i = 0; i < 5; i ++) {
    			anger.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getAnger().get(i)));
    		}
    		anger.setName(title + ": Anger");
    		if (angerCheck.isSelected()) {
    			lineChart.getData().add(anger);
    		}
    		
    		// joy
    		for (int i = 0; i < 5; i++) {
    			joy.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getJoy().get(i)));	
    		}
    		joy.setName(title + ": Joy");
    		if (joyCheck.isSelected()) {
    			lineChart.getData().add(joy);
    		}
    		
       		// disgust
    		for (int i = 0; i < 5; i++) {
    			disgust.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getDisgust().get(i)));
    		}
    		disgust.setName(title + ": Disgust");
    		if (disgustCheck.isSelected()) {
    			lineChart.getData().add(disgust);
    		}
    		
    		// sadness
    		for (int i = 0; i < 5; i++) {
    			sadness.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getSadness().get(i)));
    		}
    		sadness.setName(title + ": Sadness");
    		if (sadCheck.isSelected()) {
    			lineChart.getData().add(sadness);
    		}
    		
    		// fear
    		for (int i = 0; i < 5; i++) {
    			fear.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getFear().get(i)));
    		}
    		fear.setName(title + ": Fear");
    		if (fearCheck.isSelected()) {
    			lineChart.getData().add(fear);
    		}
    	}
    	// if are comparing two books
    	if (event.getSource().equals(compareWith) || (event.getSource().equals(updateGraph) &&
    			booksCompared)) {
    		// get the selected book
    		if (compareTitleTab.isSelected()) {
    			title = comparisonList.getSelectionModel().getSelectedItem();
    		} else if (compareAuthorTab.isSelected()) {
    			title = compareAuthorTree.getSelectionModel().getSelectedItem().getValue();
    		} else if (compareYearTab.isSelected())  {
    			title = compareYearTree.getSelectionModel().getSelectedItem().getValue();
    		}
    		
    		// set comparedBook text
    		comparedBook.setText(title);
        	// set "switch" to true
    		booksCompared = true;
        	
    		// new book dummy
    	    BookObj book = titleToBook.get(title);

    	    // declare series
    	    XYChart.Series<String, Double> anger = new XYChart.Series<>();
    		XYChart.Series<String, Double> fear = new XYChart.Series<>();
    		XYChart.Series<String, Double> sadness = new XYChart.Series<>();
    		XYChart.Series<String, Double> disgust = new XYChart.Series<>();
    		XYChart.Series<String, Double> joy = new XYChart.Series<>();

    		// plot series
    		//anger
    		for (int i = 0; i < 5; i ++) {
    			anger.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getAnger().get(i)));
    		}
    		anger.setName(title + ": Anger");
    		if (angerCheck.isSelected()) {
    			lineChart.getData().add(anger);
    		}
    		
    		// joy
    		for (int i = 0; i < 5; i++) {
    			joy.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getJoy().get(i)));	
    		}
    		joy.setName(title + ": Joy");
    		if (joyCheck.isSelected()) {
    			lineChart.getData().add(joy);
    		}
    		
       		// disgust
    		for (int i = 0; i < 5; i++) {
    			disgust.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getDisgust().get(i)));
    		}
    		disgust.setName(title + ": Disgust");
    		if (disgustCheck.isSelected()) {
    			lineChart.getData().add(disgust);
    		}
    		
    		// sadness
    		for (int i = 0; i < 5; i++) {
    			sadness.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getSadness().get(i)));
    		}
    		sadness.setName(title + ": Sadness");
    		if (sadCheck.isSelected()) {
    			lineChart.getData().add(sadness);
    		}
    		
    		// fear
    		for (int i = 0; i < 5; i++) {
    			fear.getData().add(new XYChart.Data<String, Double>(Integer.toString(i + 1), book.getFear().get(i)));
    		}
    		fear.setName(title + ": Fear");
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
