package application;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import preliminary_work.BookEmotionData;
import preliminary_work.BookSplitter;

public class BookEmotionDataTest {

	String goodUrl; 
	String incompleteUrl;
	String noUrl;
	String notAUrl;
	String notABookUrl;

	
	@Before
	public void setupBookEmotionDataTest() {
		goodUrl = "http://www.loyalbooks.com/download/text/Gullivers-Travels-by-Jonathan-Swift.txt";
		incompleteUrl = "http://www.loyalbooks.com/download/text/Gullivers-Travels-by-Jonathan-Swift";
		noUrl = "";  
		notAUrl = "Gullivers.txt";
		notABookUrl = "http://www.nytimes.com";
		
	}
	

	@Test
	public void testIncompleteUrl() {
		BookEmotionData bed = new BookEmotionData(incompleteUrl);
		assertFalse("BookEmotionData failed to acquire book emotion data", bed.doesHaveEmotionData());
	}
	
	
	@Test
	public void testNoUrl() {
		BookEmotionData bed = new BookEmotionData(noUrl);
		assertFalse("BookEmotionData failed to acquire book emotion data", bed.doesHaveEmotionData());
	}
	
	
	@Test
	public void testNotABookUrl() {
		BookEmotionData bed = new BookEmotionData(notABookUrl);
		assertFalse("BookEmotionData failed to acquire book emotion data", bed.doesHaveEmotionData());
	}
	
	
	@Test
	public void testNotAUrl() {
		BookEmotionData bed = new BookEmotionData(notAUrl);
		assertFalse("BookEmotionData failed to acquire book emotion data", bed.doesHaveEmotionData());
	}
	
	
	// TEST UNABLE TO RUN DUE TO REQUIRING WATSON API KEY
	/*	
		@Test
		public void testGoodUrl() {
			
			BookEmotionData bed = new BookEmotionData(goodUrl);
			assertTrue("BookEmotionData failed to acquire book emotion data", bed.doesHaveEmotionData());
			
		}
	*/

}
