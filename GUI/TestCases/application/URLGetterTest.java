package application;

import static org.junit.Assert.*;

import java.io.File;
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
		
		File input = new File("BookURLs.csv");
		
		HashMap<String, Integer> links = new HashMap<String, Integer>(urlg.getTxtLinks());
		
		boolean isTxt = true;
		
		Pattern p = Pattern.compile("(^(.txt)*).txt");
				
		for (String link : links.keySet()) {
			Matcher m = p.matcher(link);
			String txt = m.replaceFirst("");
			if (!txt.equals(".txt")) {
				isTxt = false;
			}
		}
		assertTrue("URL list has a non-.txt link", isTxt);
	}

}
