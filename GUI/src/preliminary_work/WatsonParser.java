package preliminary_work;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;


/**
 * Class that contains methods to parse Watson NLU JSON responses 
 * using a Google GSON API.  
 * Currently has a method for parsing the emotion response for a 
 * whole document, as well as methods for cleaning and parsing 
 * responses for an entity/character's emotions.
 * Methods are public so they can be called appropriately and 
 * named to indicate how it should be used.
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
	 * Constructs a Watson JSON response parser object
	 */
	public WatsonParser() {

	}
	
	
	/**
	 * Method to parse a document's emotion, Json response must be formatted as a string
	 * and only takes in one document emotion at a time, not entire Watson response.
	 * @param text the Watson Json response for document emotion
	 */
	public void parseDocEmotion(String text) {
		try {
			JsonObject emotionResponse = new JsonParser().parse(text)
					.getAsJsonObject();

			JsonObject docResponse = emotionResponse.get("document").getAsJsonObject();

			parseEmotion(docResponse);	
		} catch (JsonParseException e) {
			System.out.println("Provided document emotion was formatted incorrectly ");
		}
		
	}
	
	/**
	 * Method that parses just the emotion Json object for 5 emotions. 
	 * @param response a JsonObject of just the Watson emotion data
	 */
	public void parseEmotion(JsonObject response) {
		try {
			JsonObject emotion = response.get("emotion").getAsJsonObject();
			angerScore = emotion.get("anger").getAsDouble();
			disgustScore = emotion.get("disgust").getAsDouble();
			fearScore = emotion.get("fear").getAsDouble();
			joyScore = emotion.get("joy").getAsDouble();
			sadnessScore = emotion.get("sadness").getAsDouble();
		} catch (JsonParseException e) {
			System.out.println("Provided emotion was formatted incorrectly ");
		}

	}
	
	
	/**
	 * @return the angerScore Watson's anger score for the document or an entity
	 */
	public double getAngerScore() {
		return angerScore;
	}

	/**
	 * @return the disgustScore Watson's disgust score for the document or an entity 
	 */
	public double getDisgustScore() {
		return disgustScore;
	}

	/**
	 * @return the fearScore Watson's fear score for the document or an entity
	 */
	public double getFearScore() {
		return fearScore;
	}

	/**
	 * @return the joyScore Watson's joy score for the document or an entity
	 */
	public double getJoyScore() {
		return joyScore;
	}

	/**
	 * @return the sadnessScore Watson's sadness score for the document or an entity
	 */
	public double getSadnessScore() {
		return sadnessScore;
	}
	
	
//// ADDITIONAL FUNCTIONALITIES NOT USED IN 1.0 VERSION OF THE GUI /////////////////////////////////
	
	/**
	 * Removes all entities from an arraylist that are not of "Person" type 
	 * @param watsonResponse
	 */
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
	 * Parses a single Person entity response for emotion scores
	 * @param text a single Person entity response as a string
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
	 * @return the type the Watson entity type (e.g. "Person" or "Location")
	 */
	public String getEntityType() {
		return entityType;
	}
	
	/**
	 * @return the name the name of the entity (e.g. character)
	 */
	public String getEntityName() {
		return entityName;
	}
	
	/**
	 * @return the relevance the entity's importance in the book 
	 */
	public double getEntityRelevance() {
		return entityRelevance;
	}

	/**
	 * @return the count the number of times the entity appears in the book
	 */
	public int getEntityCount() {
		return entityCount;
	}

	/**
	 * @return the entitySentimentScore Watson's sentiment score for only an entity
	 */
	public double getEntitySentimentScore() {
		return entitySentimentScore;
	}
	
}
