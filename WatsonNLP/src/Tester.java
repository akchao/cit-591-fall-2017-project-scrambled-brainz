import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * tester class for book functions
 *
 * @author Drawjk705
 *
 */

public class Tester {

	public static void main(String[] args) throws IOException {

//		BookReader br = new BookReader();
		
//		br.URLReader();
		
		// String to begin searching Google
		String google = "https://www.google.com/search?q=";
		
		// String to query Google for Wikipedia
		String wikipedia = " dbpedia";
		
		
//		URL url = new URL("https://www.gutenberg.org/files/56126/56126-h/56126-h.htm");
//		
//		Scanner s = new Scanner(url.openStream());
		
		// String with our search term (book title)
		String st = "To Kill a Mockingbird";
		
		// encode search text to make it URL-friendly
		String urlEnc = URLEncoder.encode(st + wikipedia, "UTF-8");
		
		// concatenate with our Google search link
		google += urlEnc;
		
		// get that Google search page
		Document doc = Jsoup.connect(google).get();

		// go the the wikipedia page -- the first link from Google
		Element wikiPage = doc.getElementsByTag("cite").get(0);
		
		// to to the table within the Wikipedia page
		Elements table = wikiPage.select("table.description");
		
		System.out.println(table.size());
		
		Elements rows = table.select("tr");
		
		System.out.println(rows.size());
		
		
		
		// TODO: iterate through table rows/columns to get name etc.
		
		
		
//		String author = table.select("author").text();
		
//		System.out.println(author);
		
		
		
		System.out.println(google);
		
		
		
	}
}
