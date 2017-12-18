package application;

import java.util.ArrayList;

/**
 * Reads in a line from Book-Data.csv and organizes data
 * @author Drawjk705
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
	
	
	/**
	 * Constructor class to parse array of Strings from .csv line
	 * @param array
	 */
	public BookObj(String [] array) {
		
		try {
			title = array[0];
			author = array[1];
			try {
				pubDate = Integer.parseInt(array[2]);
			} catch (Exception e) {
				pubDate = 0;
			}
			location = array[3];
			URL = array[4];
			anger = new ArrayList<Double>();
			disgust = new ArrayList<Double>();
			fear = new ArrayList<Double>();
			joy = new ArrayList<Double>();
			sadness = new ArrayList<Double>();
			
			for (int i = 5; i < 10; i++) {
				try {
					anger.add(Double.parseDouble(array[i]));
				} catch (NumberFormatException nfe) {
					anger.add(0.0);
				}
			}
			for (int i = 10; i < 15; i++) {
				try {
					disgust.add(Double.parseDouble(array[i]));
				} catch (NumberFormatException nfe) {
					disgust.add(0.0);
				}
			}
			for (int i = 15; i < 20; i++) {
				try {
					fear.add(Double.parseDouble(array[i]));
				} catch (NumberFormatException nfe) {
					fear.add(0.0);
				}
			}
			for (int i = 20; i < 25; i++) {
				try {
					joy.add(Double.parseDouble(array[i]));
				} catch (NumberFormatException nfe) {
					joy.add(0.0);
				}
			}
			for (int i = 25; i < 30; i++) {
				try {
					sadness.add(Double.parseDouble(array[i]));
				} catch (NumberFormatException nfe) {
					sadness.add(0.0);
				}
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("Check Book-Data.csv file for missing values\n");
//			aioobe.printStackTrace();
//			throw aioobe;
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
