import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Parses the Watson JSON responses for analysis
 * @author Alice
 *
 */
public class WatsonParser {
	
	private String type;
	private String name;
	private double relevance;
	private int count;	
	private double angerScore;
	private double disgustScore;
	private double fearScore;
	private double joyScore;
	private double sadnessScore;
	private double sentimentScore;

	/**
	 * Constructs Watson response parser
	 */
	public WatsonParser() {
//		WatsonAnalyzer wa = new WatsonAnalyzer();
//		AnalysisResults watsonResponse = wa.getWatsonResponse();
//		List<EntitiesResult> watsonEntities = wa.getWatsonEntities();
	}
	
	/**
	 * Parses a single Person entity response
	 * @param text
	 */
	public void parsePersonEntity(String text) {
		JsonObject entityResponse = new JsonParser().parse(text)
				.getAsJsonObject();
		
		// check if type is "Person" otherwise do nothing
		type = entityResponse.get("type").getAsString();
		if (type.equals("Person")) {
			
			name = entityResponse.get("text").getAsString();
			relevance = entityResponse.get("relevance").getAsDouble();
			count = entityResponse.get("count").getAsInt();
				
			JsonObject emotion = entityResponse.get("emotion").getAsJsonObject();
			angerScore = emotion.get("anger").getAsDouble();
			disgustScore = emotion.get("disgust").getAsDouble();
			fearScore = emotion.get("fear").getAsDouble();
			joyScore = emotion.get("joy").getAsDouble();
			sadnessScore = emotion.get("sadness").getAsDouble();

			JsonObject sentiment = entityResponse.get("sentiment").getAsJsonObject();
			sentimentScore = sentiment.get("score").getAsDouble();
		} 
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the relevance
	 */
	public double getRelevance() {
		return relevance;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the angerScore
	 */
	public double getAngerScore() {
		return angerScore;
	}

	/**
	 * @return the disgustScore
	 */
	public double getDisgustScore() {
		return disgustScore;
	}

	/**
	 * @return the fearScore
	 */
	public double getFearScore() {
		return fearScore;
	}

	/**
	 * @return the joyScore
	 */
	public double getJoyScore() {
		return joyScore;
	}

	/**
	 * @return the sadnessScore
	 */
	public double getSadnessScore() {
		return sadnessScore;
	}

	/**
	 * @return the sentimentScore
	 */
	public double getSentimentScore() {
		return sentimentScore;
	}
	
}
