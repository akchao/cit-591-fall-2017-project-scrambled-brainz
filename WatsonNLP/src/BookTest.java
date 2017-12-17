import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class BookTest {
		
	public ArrayList<String> readBookUrls() {
		ArrayList<String> urls = new ArrayList<String>();
		try {
			File inputFile = new File("Book URLs.csv");
			Scanner in = new Scanner(inputFile);
			
			// skip the column header
			in.nextLine();
			
			while (in.hasNextLine()) {
				urls.add(in.nextLine());
			}
			
			// close the document
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urls;
	}
	
	@Test
	public void testGetBookSearchParam() {
		ArrayList<String> urls = new ArrayList<String>(readBookUrls());
		
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
		ArrayList<String> urls = new ArrayList<String>(readBookUrls());
		
		int i = 0;
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
	
//	@Test (expected = Exception.class)
//	public void testExtractWikipediaData() {
//		ArrayList<String> urls = new ArrayList<String>(readBookUrls());
//		
//		for (String url : urls) {
//			Book b = new Book(url);
//		}
//		
//	}
}
