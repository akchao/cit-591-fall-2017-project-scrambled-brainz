import java.util.ArrayList;
import java.util.Arrays;

public class BookEmotionData {

//	private double[] anger;
//	private double[] disgust;
//	private double[] fear;
//	private double[] joy;
//	private double[] sadness;
	
	private ArrayList<Double> anger;
	private ArrayList<Double> disgust;
	private ArrayList<Double> fear;
	private ArrayList<Double> joy;
	private ArrayList<Double> sadness;
	
	public BookEmotionData(String url) {
//		anger = new double[5];
//		disgust = new double[5];
//		fear = new double[5];
//		joy = new double[5];
//		sadness = new double[5];
		
		anger = new ArrayList<>();
		disgust = new ArrayList<>();
		fear = new ArrayList<>();
		joy = new ArrayList<>();
		sadness = new ArrayList<>();
		
		generateEmotionDataForBook(url);
	}
	
	public void generateEmotionDataForBook(String url) {
		
//		String url = "http://www.loyalbooks.com/download/text/King-Solomons-Mines-by-Haggard.txt";
		BookSplitter split = new BookSplitter(url);
		String[] bookSegments = split.getBookSegments();
		
		int index = 0;
		
		for (String s : bookSegments) {
			WatsonAnalyzer wa = new WatsonAnalyzer(s);
			String emotion = wa.getWatsonDocEmotion();

			WatsonParser wp = new WatsonParser();
			wp.parseDocEmotion(emotion);
//			anger[index] = wp.getAngerScore();
//			disgust[index] = wp.getDisgustScore();
//			fear[index] = wp.getFearScore();
//			joy[index] = wp.getJoyScore();
//			sadness[index] = wp.getSadnessScore();
			
			
			anger.add(wp.getAngerScore());
			disgust.add(wp.getDisgustScore());
			fear.add(wp.getFearScore());
			joy.add(wp.getJoyScore());
			sadness.add(wp.getSadnessScore());

			index++;
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

	
	
//	/**
//	 * @return the anger array of anger scores for each book segment
//	 */
//	public double[] getAnger() {
//		return anger;
//	}
//
//	/**
//	 * @return the disgust array of disgust scores for each book segment
//	 */
//	public double[] getDisgust() {
//		return disgust;
//	}
//
//	/**
//	 * @return the fear array of fear scores for each book segment
//	 */
//	public double[] getFear() {
//		return fear;
//	}
//
//	/**
//	 * @return the joy array of joy scores for each book segment
//	 */
//	public double[] getJoy() {
//		return joy;
//	}
//
//	/**
//	 * @return the sadness array of sadness scores for each book segment
//	 */
//	public double[] getSadness() {
//		return sadness;
//	}
	
	
	
	
}
