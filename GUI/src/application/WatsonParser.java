package application;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Parses the Watson JSON responses for analysis
 * @author Alice
 *
 */
public class WatsonParser {
	
	private String entityType;
	private String entityName;
	private double entityRelevance;
	private int entityCount;	
	private double angerScore;
	private double disgustScore;
	private double fearScore;
	private double joyScore;
	private double sadnessScore;
	private double entitySentimentScore;

	
	/**
	 * Constructs Watson response parser
	 */
	public WatsonParser() {
//		WatsonAnalyzer wa = new WatsonAnalyzer();
//		AnalysisResults watsonResponse = wa.getWatsonResponse();
//		List<EntitiesResult> watsonEntities = wa.getWatsonEntities();
//		removeNonPerson(watsonEntities);
	}
	
	
/*	
{
  "document": {
    "emotion": {
      "anger": 0.144436,
      "disgust": 0.074289,
      "fear": 0.130087,
      "joy": 0.599415,
      "sadness": 0.519859
    }
  }
}
*/

	public void parseDocEmotion(String text) {
		
		JsonObject emotionResponse = new JsonParser().parse(text)
				.getAsJsonObject();
		
		JsonObject docResponse = emotionResponse.get("document").getAsJsonObject();
		
		parseEmotion(docResponse);		
		
	}
	
	public void parseEmotion(JsonObject response) {

		JsonObject emotion = response.get("emotion").getAsJsonObject();
		angerScore = emotion.get("anger").getAsDouble();
		disgustScore = emotion.get("disgust").getAsDouble();
		fearScore = emotion.get("fear").getAsDouble();
		joyScore = emotion.get("joy").getAsDouble();
		sadnessScore = emotion.get("sadness").getAsDouble();

	}
	
	
	public void removeNonPerson(List<String> watsonResponse) {
		
		for (int i=0; i<watsonResponse.size(); i++) {
		
			JsonObject entityResponse = new JsonParser()
					.parse(watsonResponse.get(i).toString())
					.getAsJsonObject();
			
			entityType = entityResponse.get("type").getAsString();
			if (entityType.equals("Person")) {
				continue;
			} else {
				watsonResponse.remove(i);
			}
		}
			
	} 
	
	/**
	 * Parses a single Person entity response
	 * @param text
	 */
	public void parsePersonEntity(String text) {
		JsonObject entityResponse = new JsonParser().parse(text)
				.getAsJsonObject();
		
		// check if type is "Person" otherwise do nothing
		entityType = entityResponse.get("type").getAsString().trim();
		if (entityType.equals("Person")) {
			
			entityName = entityResponse.get("text").getAsString();
			entityRelevance = entityResponse.get("relevance").getAsDouble();
			entityCount = entityResponse.get("count").getAsInt();
			
			parseEmotion(entityResponse);

			JsonObject sentiment = entityResponse.get("sentiment").getAsJsonObject();
			entitySentimentScore = sentiment.get("score").getAsDouble();
		} 
	}
	

	/**
	 * @return the type only for 
	 */
	public String getEntityType() {
		return entityType;
	}
	
	/**
	 * @return the name
	 */
	public String getEntityName() {
		return entityName;
	}
	
	/**
	 * @return the relevance
	 */
	public double getEntityRelevance() {
		return entityRelevance;
	}

	/**
	 * @return the count
	 */
	public int getEntityCount() {
		return entityCount;
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
	public double getEntitySentimentScore() {
		return entitySentimentScore;
	}
	
}
