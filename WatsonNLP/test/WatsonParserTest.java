import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
/**
 * Test methods from WatsonParserTest
 * @author Alice
 *
 */
public class WatsonParserTest {

	WatsonParser wp;
	String sampleEntityPerson;
	String sampleEntityNonPerson;
	List<String> sample;
	
	@Before
	public void setupWatsonTest() {
		wp = new WatsonParser();
		
		sampleEntityPerson = "{\n" + 
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
		
		sampleEntityNonPerson = "{\n" + 
				"  \"type\": \"Location\",\n" + 
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
		
		//List<EntitiesResult> sample = new ArrayList<>();
		sample = new ArrayList<>();
		sample.add(sampleEntityPerson);
		sample.add(sampleEntityNonPerson);
		
	}

	@Test
	public void testParseEntityIsAPerson() {
		
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals("Person",wp.getType());	
		fail("WatsonParser could not identify a Person entity type");
	}
	 
	
	@Test 
	public void testParseEntityNonPersonIsNotAPerson() {
		
		wp.parsePersonEntity(sampleEntityNonPerson);
		assertNotSame("WatsonParser incorrectly identified a non-person "
				+ "entity as a person","Person",wp.getType());
		fail("WatsonParser incorrectly identified a non-person entity as a person");
	} 
	
	
	@Test
	public void testParseRemoveNonPerson( ) {
		wp.removeNonPerson(sample);
		assertEquals(sampleEntityPerson,sample.get(0));
	}
	 
	
	@Test 
	public void testNoBlankResponses() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertNotNull(wp.getName());
		assertNotNull(wp.getRelevance());
		assertNotNull(wp.getCount());
		assertNotNull(wp.getAngerScore());
		assertNotNull(wp.getDisgustScore());
		assertNotNull(wp.getFearScore());
		assertNotNull(wp.getJoyScore());
		assertNotNull(wp.getSadnessScore());
		assertNotNull(wp.getSentimentScore());	
		fail("Watson response had blanks");
	}
	
	
	@Test
	public void testParseEntityForName() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals("Elizabeth Bennet",wp.getName());
	}
	
	
	@Test
	public void testParseEntityForRelevance() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertSame(0.880592,wp.getRelevance());
	}

	
	@Test
	public void testParseEntityForCount() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertSame(70,wp.getCount());
	}
	
	
	@Test
	public void testParseEntityForAngerScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertSame(0.078232,wp.getAngerScore());
	}
	

	@Test
	public void testParseEntityForDisgustScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertSame(0.026196,wp.getDisgustScore());	
	}

	
	@Test
	public void testParseEntityForFearScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertSame(0.116198,wp.getFearScore());
	}
	
	
	@Test
	public void testParseEntityForJoyScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertSame(0.244804,wp.getJoyScore());
		
	}
	
	
	@Test
	public void testParseEntityForSadnessScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertSame(0.112424,wp.getSadnessScore());
	}
	
	
	@Test
	public void testParseEntityForSentimentScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertSame(0.0,wp.getSentimentScore());
	}
	
}
