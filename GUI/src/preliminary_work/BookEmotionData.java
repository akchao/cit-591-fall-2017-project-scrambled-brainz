package preliminary_work;
import java.util.ArrayList;

/**
 * Class that takes integrates BookSplitter, WatsonAnalyzer, and WatsonParser 
 * in order to read in a single book from a URL, split the book into 5 sections,
 * send each section to Watson NLU API for processing, and stores the responses.
 * @author Alice
 *
 */
public class BookEmotionData {
	
	private ArrayList<Double> anger;
	private ArrayList<Double> disgust;
	private ArrayList<Double> fear;
	private ArrayList<Double> joy;
	private ArrayList<Double> sadness;
	
	/**
	 * Constructs a BookEmotionData object that will 
	 * perform the task for one book at a time
	 * @param url the book url that should contain only the book text (no tables)
	 */
	public BookEmotionData(String url) {

		// initialize ArrayLists
		anger = new ArrayList<>();
		disgust = new ArrayList<>();
		fear = new ArrayList<>();
		joy = new ArrayList<>();
		sadness = new ArrayList<>();
		
		generateEmotionDataForBook(url);
	}
	
	
	/**
	 * Method that takes in a book url and generates the emotion 
	 * data for the book and stores it in an arraylist
	 * @param url the book url 
	 */
	private void generateEmotionDataForBook(String url) {
		
		BookSplitter split = new BookSplitter(url);
		String[] bookSegments = split.getBookSegments();
		
		
		for (String s : bookSegments) {
			// Watson API analyze for emotion in one book segment
			WatsonAnalyzer wa = new WatsonAnalyzer(s);
			String emotion = wa.getWatsonDocEmotion();

			// parse Watson JSON response
			WatsonParser wp = new WatsonParser();
			wp.parseDocEmotion(emotion);			
			
			// store specific emotion scores in arraylist
			anger.add(wp.getAngerScore());
			disgust.add(wp.getDisgustScore());
			fear.add(wp.getFearScore());
			joy.add(wp.getJoyScore());
			sadness.add(wp.getSadnessScore());

		}
		
	}

	/**
	 * @return the anger scores for each book segment as ArrayList
	 */
	public ArrayList<Double> getAnger() {
		return anger;
	}

	/**
	 * @return the disgust scores for each book segment as ArrayList
	 */
	public ArrayList<Double> getDisgust() {
		return disgust;
	}

	/**
	 * @return the fear scores for each book segment as ArrayList
	 */
	public ArrayList<Double> getFear() {
		return fear;
	}

	/**
	 * @return the joy scores for each book segment as ArrayList
	 */
	public ArrayList<Double> getJoy() {
		return joy;
	}

	/**
	 * @return the sadness scores for each book segment as ArrayList
	 */
	public ArrayList<Double> getSadness() {
		return sadness;
	}

	

	
}
