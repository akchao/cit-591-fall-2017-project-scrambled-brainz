import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class to get all URLs for the .txt files.
 * Will use http://www.loyalbooks.com
 * @author Drawjk705
 *
 */
public class URLGetter {

	// keep track of all links
	private HashMap<String, Integer> txtLinks = new HashMap<String, Integer>(); 

	
	public URLGetter() {
		
	}
	
	public void getTxt(String bookUrl) {
		Document doc = null;
		try {
			// connect to book's page
			doc = Jsoup.connect(bookUrl).get();
			
			// get the link to the book's .txt file 
			Element link = doc.select("td.book2:contains(Text File eBook)").first();
			
			// skip books that don't have a proper .txt link
			if (link == null) {
				return;
			}
			
			link = link.selectFirst("a[href]");
			
			String url = link.text();
			
			txtLinks.put(url, 1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * method to get book urls
	 * using the site http://www.loyalbooks.com/
	 * @param url -> the specific URL to specify book category
	 */
	public void findBookUrls(String url) {
		Document doc = null;
		try {
			// connect to url
			doc = Jsoup.connect(url).get();
			
			// find table that contains books
			Element table = doc.selectFirst("table.layout2-blue");
			
			// get all book links from the table
			Elements links = table.select("td > a");
			
			// go through each cell, and pull out
			// the link to the book
			int index = 0;
			for (Element link : links) {				
				if (index % 3 == 0) {
					Elements l = link.select("a[href]");
					
					// store the link in a string
					String bookUrl = l.attr("abs:href");
					
					getTxt(bookUrl);
				}
				index++;
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void findGenres() {
		String url = "http://www.loyalbooks.com/genre-menu";
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
			
			Elements genre = doc.select("table:contains(All Genres)");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
