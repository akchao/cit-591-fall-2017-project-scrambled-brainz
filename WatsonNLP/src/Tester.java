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
		
		String google = "https://www.google.com/search?q=";
		
		String wikipedia = " wikipedia";
		
		
		URL url = new URL("https://www.gutenberg.org/files/56126/56126-h/56126-h.htm");
		
		Scanner s = new Scanner(url.openStream());
		
		String st = "To Kill a Mockingbird";
		
		String urlEnc = URLEncoder.encode(st + wikipedia, "UTF-8");
		
		google += urlEnc;
		
		Document doc = Jsoup.connect(google).get();

		
		// go the the wikipedia page
		Element wikiPage = doc.getElementsByTag("cite").get(0);
		
		// to to the table within the Wikipedia page
		Elements table = wikiPage.select("table.infobox");
		
		// TODO: iterate through table rows/columns to get name etc.
		
		
		
//		String author = table.select("author").text();
		
//		System.out.println(author);
		
		
		
		System.out.println(google + urlEnc);
		
		
		
	}
}
