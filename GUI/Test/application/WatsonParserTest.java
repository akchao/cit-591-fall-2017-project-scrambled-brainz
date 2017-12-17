package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test methods from WatsonParserTest
 * @author Alice
 *
 */
public class WatsonParserTest {

	WatsonParser wp;
	String sampleEntityPerson;
	String sampleEntityNonPerson;
	String sampleDocumentEmotion;
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
		
		sampleDocumentEmotion = "{\n" + 
				"  \"document\": {\n" + 
				"    \"emotion\": {\n" + 
				"      \"anger\": 0.144436,\n" + 
				"      \"disgust\": 0.074289,\n" + 
				"      \"fear\": 0.130087,\n" + 
				"      \"joy\": 0.599415,\n" + 
				"      \"sadness\": 0.519859\n" + 
				"    }\n" + 
				"  }\n" + 
				"}";
		
		
		//List<EntitiesResult> sample = new ArrayList<>();
		sample = new ArrayList<>();
		sample.add(sampleEntityPerson);
		sample.add(sampleEntityNonPerson);
		
	}

	
	
	
	
	
	
	
	@Test
	public void testParseDocForDisgustScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.026196,wp.getDisgustScore(),0.000000);	
	}

	
	@Test
	public void testParseDocForFearScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.116198,wp.getFearScore(),0.000000);
	}
	
	
	@Test
	public void testParseDocForJoyScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.244804,wp.getJoyScore(),0.000000);
		
	}
	
	
	@Test
	public void testParseDocForSadnessScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.112424,wp.getSadnessScore(),0.000000);
	}
	
	
	
	@Test
	public void testParseEntityIsAPerson() {
		
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals("Person",wp.getEntityType());	
		fail("WatsonParser could not identify a Person entity type");
	}
	 
	
	@Test
	public void testParseRemoveNonPerson( ) {
		wp.removeNonPerson(sample);
		assertEquals(sampleEntityPerson,sample.get(0));
	}
	 
	
	@Test
	public void testParseEntityForName() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals("Elizabeth Bennet",wp.getEntityName());
	}
	
	
	@Test
	public void testParseEntityForRelevance() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.880592,wp.getEntityRelevance(),0.000000);
	}

	
	@Test
	public void testParseEntityForCount() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(70,wp.getEntityCount());
	}
	
	
	@Test
	public void testParseEntityForAngerScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.078232,wp.getAngerScore(),0.000000);
	}
	

	@Test
	public void testParseEntityForDisgustScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.026196,wp.getDisgustScore(),0.000000);	
	}

	
	@Test
	public void testParseEntityForFearScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.116198,wp.getFearScore(),0.000000);
	}
	
	
	@Test
	public void testParseEntityForJoyScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.244804,wp.getJoyScore(),0.000000);
		
	}
	
	
	@Test
	public void testParseEntityForSadnessScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.112424,wp.getSadnessScore(),0.000000);
	}
	
	
	@Test
	public void testParseEntityForSentimentScore() {
		wp.parsePersonEntity(sampleEntityPerson);
		assertEquals(0.0,wp.getEntitySentimentScore(),0.0);
	}
	
}