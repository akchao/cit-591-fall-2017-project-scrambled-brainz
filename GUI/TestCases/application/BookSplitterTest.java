package application;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import preliminary_work.BookSplitter;

public class BookSplitterTest {

	String goodUrl; 
	String badUrl;
	String noUrl;
	String notAUrl;
	String notABookUrl;
	String sampleGoodText;
	String sampleTooShortText;
	
	
	@Before
	public void setupBookSplitterTest() {
		goodUrl = "http://www.loyalbooks.com/download/text/Gullivers-Travels-by-Jonathan-Swift.txt";
		badUrl = "http://www.loyalbooks.com/download/text/Gullivers-Travels-by-Jonathan-Swift";
		noUrl = "";  // same as NULL?
		notAUrl = "Gullivers.txt";
		notABookUrl = "";
//		sampleGoodText = "I hope you will be ready to own publicly, whenever you shall be called to\n" + 
//				"it, that by your great and frequent urgency you prevailed on me to\n" + 
//				"publish a very loose and uncorrect account of my travels, with directions\n" + 
//				"to hire some young gentleman of either university to put them in order,\n" + 
//				"and correct the style, as my cousin Dampier did, by my advice, in his\n" + 
//				"book called â€œA Voyage round the world.â€� ";
//		
//		sampleTooShortText = "I hope you will be ready to own publicly, whenever you shall be called to\n" + 
//				"it, that by your great and frequent urgency you prevailed on me to\n" + 
//				"publish a very loose and uncorrect account of my travels.";
		
	}
	
	
	@Test
	public void testGoodUrl() {
		BookSplitter bs = new BookSplitter(goodUrl);
		String[] segments = bs.getBookSegments();
//		System.out.println(!segments[0].equals(""));
		String checkContent = segments[0];		
		boolean checkIfContent = !checkContent.equals("");
		
//		assertTrue(checkIfContent);
		assertEquals(true,checkIfContent);
		fail("BookSplitter failed to split the book");
	}

	
	@Test
	public void testSplitText() {
		
	}
	
}
