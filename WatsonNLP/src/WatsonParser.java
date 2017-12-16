import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.*; 
import javax.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;


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
//		removeNonPerson(watsonEntities);
	}
	
	
/*	
	{
		  "language": "en",
		  "usage": {
		    "features": 1
		  },
		  "emotion": {
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
	}
*/
	
	
	
	
	public void removeNonPerson(List<String> watsonResponse) {
		
		for (int i=0; i<watsonResponse.size(); i++) {
		
			JsonObject entityResponse = new JsonParser()
					.parse(watsonResponse.get(i).toString())
					.getAsJsonObject();
			
			type = entityResponse.get("type").getAsString();
			if (type.equals("Person")) {
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
		type = entityResponse.get("type").getAsString().trim();
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
	
	public javax.json.JsonObject buildCharacterProfile() {
		Map<String, Object> config = new HashMap<String, Object>();
        
		 JsonBuilderFactory factory = Json.createBuilderFactory(config);
		 javax.json.JsonObject value = factory.createObjectBuilder()
			 .add(name, factory.createObjectBuilder())	 
		     .add("emotions", factory.createArrayBuilder()
		    		 .add(factory.createObjectBuilder()
		    		     .add("anger", angerScore)
		    		     .add("disgust", disgustScore)
		    		     .add("fear", fearScore)
		    		     .add("joy", joyScore)
		    		     .add("sadness", sadnessScore)))
		     .build();
		
		 // testing out formatting
//		config.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));

//		JsonWriterFactory factory2 = Json.createWriterFactory(value);
//
//		int spacesToIndentEachLevel = 2;
//		try {
//			new JSONObject(value).toString(spacesToIndentEachLevel);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		 System.out.println(factory);
		 return value;
	}
	
	/*
	book {
	{ characters [
		character 1  {
			emotions [
				emotion1,
				emotion2,
			]
		}
		character 2 {
			emotions [
				emotion1,
				emotion2,
			]
		}
	]
	}
	}
	
	"{\n" + 
		"  \"type\": \"Person\",\n" + 
		"  \"relevance\": 0.880592,\n" + 
		"  \"count\": 70,\n" + 
		"  \"text\": \"Elizabeth Bennet\",\n" + 
		"  \"emotion\": {\n" + 
		"    \"anger\": 0.078232,\n" + 
		"    \"disgust\": 0.026196,\n" + 
		"    \"fear\": 0.116198,\n" + 
		"    \"joy\": 0.244804,\n" + 
		"    \"sadness\": 0.112424\n" + 
		"  },\n" + 
		"  \"sentiment\": {\n" + 
		"    \"score\": 0.0\n" + 
		"  }\n" + 
		"}";
		
	*/
	
	/*
	
	{
	     "firstName": "John", "lastName": "Smith", "age": 25,
	     "address" : {
	         "streetAddress": "21 2nd Street",
	         "city": "New York",
	         "state": "NY",
	         "postalCode": "10021"
	     },
	     "phoneNumber": [
	         { "type": "home", "number": "212 555-1234" },
	         { "type": "fax", "number": "646 555-4567" }
	     ]
	 }
	 
	 
	The code to create the object shown above is the following:

	 
	 JsonBuilderFactory factory = Json.createBuilderFactory(config);
	 JsonObject value = factory.createObjectBuilder()
	     .add("firstName", "John")
	     .add("lastName", "Smith")
	     .add("age", 25)
	     .add("address", factory.createObjectBuilder()
	         .add("streetAddress", "21 2nd Street")
	         .add("city", "New York")
	         .add("state", "NY")
	         .add("postalCode", "10021"))
	     .add("phoneNumber", factory.createArrayBuilder()
	         .add(factory.createObjectBuilder()
	             .add("type", "home")
	             .add("number", "212 555-1234"))
	         .add(factory.createObjectBuilder()
	             .add("type", "fax")
	             .add("number", "646 555-4567")))
	     .build();
	 
	 
	This class does not allow null to be used as a name or value while building the JSON object
	
	*/
	
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
