import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Test methods from WatsonParserTest
 * @author Alice
 *
 */
public class WatsonParserTest {

	@Test
	public void testParseEntity() {
		WatsonParser wp = new WatsonParser();
		
		String sample = "{\n" + 
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
		
		wp.parseEntity(sample);
//		assertEquals();
		System.out.println("Name: " + wp.getName());
		System.out.println("Relevance: " + wp.getRelevance());
		System.out.println("Count: " + wp.getCount());
		System.out.println("Anger score: " + wp.getAngerScore());
		System.out.println("Disgust score: " + wp.getDisgustScore());
		System.out.println("Fear score: " + wp.getFearScore());
		System.out.println("Joy score: " + wp.getJoyScore());
		System.out.println("Sadness score: " + wp.getSadnessScore());
		System.out.println("Sentiment score: " + wp.getSentimentScore());	
		
		fail("Not yet implemented");
	}

}
