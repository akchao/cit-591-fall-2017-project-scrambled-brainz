import java.util.Arrays;

public class BookEmotionData {

	private double[] anger;
	private double[] disgust;
	private double[] fear;
	private double[] joy;
	private double[] sadness;
	
	public BookEmotionData(String url) {
		anger = new double[5];
		disgust = new double[5];
		fear = new double[5];
		joy = new double[5];
		sadness = new double[5];
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
			anger[index] = wp.getAngerScore();
			disgust[index] = wp.getDisgustScore();
			fear[index] = wp.getFearScore();
			joy[index] = wp.getJoyScore();
			sadness[index] = wp.getSadnessScore();

			index++;
		}
		
	}

	/**
	 * @return the anger array of anger scores for each book segment
	 */
	public double[] getAnger() {
		return anger;
	}

	/**
	 * @return the disgust array of disgust scores for each book segment
	 */
	public double[] getDisgust() {
		return disgust;
	}

	/**
	 * @return the fear array of fear scores for each book segment
	 */
	public double[] getFear() {
		return fear;
	}

	/**
	 * @return the joy array of joy scores for each book segment
	 */
	public double[] getJoy() {
		return joy;
	}

	/**
	 * @return the sadness array of sadness scores for each book segment
	 */
	public double[] getSadness() {
		return sadness;
	}
	
	
	
}
