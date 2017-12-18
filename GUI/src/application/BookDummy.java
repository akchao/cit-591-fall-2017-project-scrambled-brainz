package application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.print.attribute.HashAttributeSet;

/**
 * Dummy class for preliminary testing of GUI
 * @author drawj
 *
 */
public class BookDummy {
	private String title;
	private String author;
	private int year;
	private double joy;
	private double disgust;
	private double anger;
	private double sadness;
	private double fear;
	
	
	private ArrayList<HashMap<String, Double>> emotions = new ArrayList<HashMap<String, Double>>();
	
	/**
	 * dummy constructor
	 * @param title
	 * @param author
	 * @param year
	 */
	public BookDummy(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
		
		String emotions[] = { "joy", "disgust", "anger", "sadness", "fear" };
		
		HashMap<String, Double> e1 = new HashMap<String, Double>();
		HashMap<String, Double> e2 = new HashMap<String, Double>();
		HashMap<String, Double> e3 = new HashMap<String, Double>();
		
		this.emotions.add(e1);
		this.emotions.add(e2);
		this.emotions.add(e3);
		
		Random r = new Random();
		
		// randomly generate emotion data
		for (HashMap<String, Double> e : this.emotions) {
			for (String emotion : emotions) {
				e.put(emotion, r.nextDouble());
			}
		}
		
	}
	
	public String getTitle() {
		return title;
	}
	public double getJoy(int i) {
		try {
			return emotions.get(i).get("joy");
		} catch (Exception IndexOutOfBoundsException) {
			return 0;
		}
	}
	public double getDisgust(int i) {
		try {
			return emotions.get(i).get("disgust");
		} catch (Exception IndexOutOfBoundsException) {
			return 0;
		}
	}
	public double getAnger(int i) {
		try {
			return emotions.get(i).get("anger");
		} catch (Exception IndexOutOfBoundsException) {
			return 0;
		}
	}
	public double getSadness(int i) {
		try {
			return emotions.get(i).get("sadness");
		} catch (Exception IndexOutOfBoundsException) {
			return 0;
		}
	}
	public double getFear(int i) {
		try {
			return emotions.get(i).get("fear");
		} catch (Exception IndexOutOfBoundsException) {
			return 0;
		}
	}
	public String getAuthor() {
		return author;
	}
	public int getYear() {
		return year;
	}
}
