package application;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import preliminary_work.BookMetaDataGetter;
import preliminary_work.URLGetter;

public class BookMetaDataGetterTest {

	/**
	 * make sure that links are all for .txt
	 */
	@Test
	public void testLinksAreTxt() {
		BookMetaDataGetter bmdg = new BookMetaDataGetter();
		
		bmdg.readBookUrls("BookURLs.csv");
		
		ArrayList<String> urls = new ArrayList<String>(bmdg.getUrls());		
		
		Pattern p = Pattern.compile("((^.txt))*.txt");
		
		boolean isTxt = true;
		
		for (String url : urls) {
			Matcher m = p.matcher(url);
			if (m.find()) {
				if (!m.group(0).equals(".txt")) {
					isTxt = false;
				}
			}
		}
		assertTrue("URL list has a non-.txt link", isTxt);
	}

}
