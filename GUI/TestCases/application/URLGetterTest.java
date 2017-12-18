package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import preliminary_work.URLGetter;

public class URLGetterTest {

	/**
	 * make sure that links are all for .txt
	 */
	@Test
	public void testLinksAreTxt() {
		URLGetter urlg = new URLGetter();
		
		// an arbitrary loyalbooks.com link
		String url = "http://www.loyalbooks.com/book/the-divine-comedy-by-dante-alighieri";
		
		urlg.getTxt(url);
		
		HashMap<String, Integer> links = new HashMap<>(urlg.getTxtLinks());
		
		// pattern to recognize .txt link
		Pattern p = Pattern.compile("((^.txt))*.txt");
		
		boolean isTxt = true;
		
		for (String link : links.keySet()) {
			Matcher m = p.matcher(link);
			if (m.find()) {
				if (!m.group(0).equals(".txt")) {
					isTxt = false;
					break;
				}
			}
		}
		assertTrue("Failed to get .txt link", isTxt);
	}
	
	/**
	 * test to see if get all .txt links from a loyalbooks.com
	 * genre page
	 * 
	 * if fail test, it just means that not all books have .txt links
	 */
	@Test
	public void testFindBookURLs() {
		URLGetter urlg = new URLGetter();
		
		// a genre link for loyalbooks.com
		String link = "http://www.loyalbooks.com/genre/Poetry?results=100";
		
		urlg.findBookUrls(link);
		
		int numberOfLinks = urlg.getTxtLinks().keySet().size();
		
		assertEquals("Did not get all .txt links from the genre page", 100, numberOfLinks); 
	}
	
	/**
	 * Make sure that all genre links are formatter properly
	 */
	@Test
	public void testGenreLinksAreCorrect() {
		URLGetter urlg = new URLGetter();
		
		Pattern p = Pattern.compile("http://www.loyalbooks.com/genre/(.*)?results=100");
		
		ArrayList<String> genres = new ArrayList<String>(urlg.getGenres());
		
		boolean correctLink = true;
		
		String link = null;
		
		for (String genre : genres) {
			Matcher m = p.matcher(genre);
			if (!m.matches()) {
				link = genre;
				correctLink = false;
				break;
			}
		}
		assertTrue("Not all links have been fomatted properly, e.g.: " + link, correctLink);
	}

}
