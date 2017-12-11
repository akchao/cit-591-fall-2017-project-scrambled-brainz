import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * tester class for book functions
 *
 * @author Drawjk705
 *
 */

public class Tester {

	public static void main(String[] args) {
		
//		BookReader br = new BookReader();
//		
//		ArrayList<Book> books = new ArrayList<Book>(br.getBooks());
//		
//		for (Book book: books) {
//			book.printBookData();
//		}
		
//		String url = "https://www.gutenberg.org/";
		String url = "http://www.loyalbooks.com/genre/Adventure";
	
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements tables = doc.select("table");
			
			Element table = null;
			
			for (Element t: tables) {
				if (t.hasClass("layout2-blue")) {
					table = t;
					break;
				}
			}
			
			Elements rows = table.select("td");
			
			String s = null;
			
			for (Element row : rows) {				
				Element link = row.selectFirst("a[href]");
				
				s = link.attr("abs:href");
				
//				System.out.println(link.attr("abs:href"));
				
				break;
			}
			
			doc = Jsoup.connect(s).get();
			
			Element link = doc.select("td.book2:contains(Text File eBook)").first();
			System.out.println(link.text());
			
			link = link.selectFirst("a[href]");
			
			
			System.out.println(link.attr("abs:href"));
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
