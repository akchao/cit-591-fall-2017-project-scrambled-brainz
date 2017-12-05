import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class BookReaderTest {

	/**
	 * test to make sure that BookReader is actually
	 * reading in the books URLs,
	 * and that they are .txt
	 */
	@Test
	public void testReadBooks() {
		BookReader br = new BookReader();
		
		ArrayList<String> urls = new ArrayList<String>();

		Pattern p = Pattern.compile("[^(.txt)]*.txt");
		
		urls = br.getUrls();		
		
		for (String url : urls) {
			Matcher m = p.matcher(url);
			if (!m.find()) {
				assertEquals("URL should have a .txt format", "[^(.txt)]*.txt", url);
			}

		}
	}


}
