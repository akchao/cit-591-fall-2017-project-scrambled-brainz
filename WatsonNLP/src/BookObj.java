import java.util.ArrayList;

/**
 * Reads in Book-Data.csv and organizes data
 * @author Alice
 *
 */
public class BookObj {
	private String title;
	private String author;
	private int pubDate;
	private String location;
	private String URL;
	private ArrayList<Double> anger;
	private ArrayList<Double> disgust;
	private ArrayList<Double> fear;
	private ArrayList<Double> joy;
	private ArrayList<Double> sadness;
	
	
	
	public BookObj(String [] array) {
		title = array[0];
		author = array[1];
		pubDate = Integer.parseInt(array[2]);
		location = array[3];
		URL = array[4];
		anger = new ArrayList<Double>();
		for (int i = 5; i < 10; i++) {
			anger.add(Double.parseDouble(array[i]));
		}
		for (int i = 10; i < 15; i++) {
			disgust.add(Double.parseDouble(array[i]));
		}
		for (int i = 15; i < 20; i++) {
			fear.add(Double.parseDouble(array[i]));
		}
		for (int i = 20; i < 25; i++) {
			joy.add(Double.parseDouble(array[i]));
		}
		for (int i = 25; i < 30; i++) {
			sadness.add(Double.parseDouble(array[i]));
		}
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}



	/**
	 * @return the pubDate
	 */
	public int getPubDate() {
		return pubDate;
	}



	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}



	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}



	/**
	 * @return the anger
	 */
	public ArrayList<Double> getAnger() {
		return anger;
	}



	/**
	 * @return the disgust
	 */
	public ArrayList<Double> getDisgust() {
		return disgust;
	}



	/**
	 * @return the fear
	 */
	public ArrayList<Double> getFear() {
		return fear;
	}



	/**
	 * @return the joy
	 */
	public ArrayList<Double> getJoy() {
		return joy;
	}



	/**
	 * @return the sadness
	 */
	public ArrayList<Double> getSadness() {
		return sadness;
	}

	

	
}
