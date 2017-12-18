package application;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import preliminary_work.BookSplitter;

public class BookSplitterTest {

	String goodUrl; 
	String incompleteUrl;
	String noUrl;
	String notAUrl;
	String notABookUrl;

	
	
	@Before
	public void setupBookSplitterTest() {
		goodUrl = "http://www.loyalbooks.com/download/text/Gullivers-Travels-by-Jonathan-Swift.txt";
		incompleteUrl = "http://www.loyalbooks.com/download/text/Gullivers-Travels-by-Jonathan-Swift";
		noUrl = "";  
		notAUrl = "Gullivers.txt";
		notABookUrl = "http://www.nytimes.com";
		
	}
	
	
	@Test
	public void testGoodUrlHasBookLines() {
		
		BookSplitter bs = new BookSplitter(goodUrl);
		assertTrue("BookSplitter failed to read in the book", bs.doesHaveBookLines());
		
	}
 
	
	@Test
	public void testGoodUrlHasBookSegments() {
		
		BookSplitter bs = new BookSplitter(goodUrl);
		assertTrue("BookSplitter failed to split the book into segments", bs.doesHaveBookSegments());
		
	}
 

	@Test
	public void testIncompleteUrl() {
		BookSplitter bs = new BookSplitter(incompleteUrl);
		assertFalse("BookSplitter took in an incomplete URL", bs.doesHaveGoodUrl());
	}
	
	
	@Test
	public void testNoUrl() {
		BookSplitter bs = new BookSplitter(noUrl);
		assertFalse(bs.doesHaveGoodUrl());
	}
	
	
	@Test
	public void testNotABookUrl() {
		BookSplitter bs = new BookSplitter(notABookUrl);
		assertFalse(bs.doesHaveGoodUrl());
	}
	
	
	@Test
	public void testNotAUrl() {
		BookSplitter bs = new BookSplitter(notAUrl);
		assertFalse(bs.doesHaveGoodUrl());
	}
	
}
