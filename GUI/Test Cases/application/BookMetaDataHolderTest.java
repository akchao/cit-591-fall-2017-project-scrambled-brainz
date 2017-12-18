package application;

import static org.junit.Assert.*;

import java.awt.print.Book;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class BookMetaDataHolderTest {

	/**
	 * make sure are getting a Wikipedia URL
	 */
	@Test
	public void testSearchForBookWikepediaPage() {
		BookMetaDataHolder bmdh = new BookMetaDataHolder("The Beatles");
		
		String wikipediaURL = bmdh.searchForBookWikipediaPage();
		
		Pattern p = Pattern.compile("(https://en.wikipedia.org/wiki/)(.*)");
		
		Matcher m = p.matcher(wikipediaURL);
		
		String match = null;
		
		if (m.find()) {
			match = m.group(1);
		}
		assertEquals("URL is not correct", "https://en.wikipedia.org/wiki/", match);
	}
	
	/**
	 * make sure that will extract search parameters appropriately
	 */
	@Test
	public void testGetBookSearchParam() {
		String text = "The Beatles";
		BookMetaDataHolder bmdh = new BookMetaDataHolder(text);
		
		String searchParam = bmdh.getBookSearchParam();
		
		assertEquals("Did not extract search parameter", text, searchParam);
	}
	
	/**
	 * make sure that hasData will be false under appropriate circumstances
	 */
	@Test
	public void testExtractWikipediaData() {
		BookMetaDataHolder bmdh = new BookMetaDataHolder("King Lear");
		
		bmdh.extractWikipediaData();
		
		boolean hasData = bmdh.hasData();
		
		assertFalse("Book should not have data, since its Wikipedia page"
				+ " lacks an infobox", hasData);
	}

}
