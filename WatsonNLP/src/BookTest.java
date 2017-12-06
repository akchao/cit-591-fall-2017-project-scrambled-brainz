import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BookTest {


	@Test
	public void testGetBookSearchParam() {
		BookReader br = new BookReader();
		
		ArrayList<String> urls = new ArrayList<String>(br.getUrls());
		
		for (String url : urls) {
			Book b = new Book(url);
			String searchParam = b.getBookSearchParam();
			if (searchParam == null) {
				assertEquals("Search Parameter should return a String, "
						+ "not null", "String s", null);
			}
		}
	}
	
	@Test
	public void testSearchForBookWikipediaPage() {
		BookReader br = new BookReader();
		
		ArrayList<String> urls = new ArrayList<String>();
		
		for (String url : urls) {
			Book b = new Book(url);
			String wikipediaUrl = b.searchForBookWikipediaPage();
			if (wikipediaUrl.equals("https://www.wikipedia.org/")) {
				assertEquals("Wikipedia URL should not be homepage", 
						"https://en.wikipedia.org/wiki/SEARCH_OBJECT", 
						wikipediaUrl);
			}
		}
	}
}
