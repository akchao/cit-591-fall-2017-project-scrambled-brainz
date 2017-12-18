package preliminary_work;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	private static int index; 

	// keep track of all links
	private HashMap<String, Integer> txtLinks = new HashMap<String, Integer>(); 
	private ArrayList<String> genres;
	/**
	 * constructor
	 */
	public URLGetter() {
		index = 0;
		genres = new ArrayList<String>(findGenres());
	}
	
	/**
	 * write all links out to a .csv file
	 */
	public void writeLinksToCsv() {
		PrintWriter out = null;
		try {
			out = new PrintWriter("Book URLs.csv");
			
			for (String link : txtLinks.keySet()) {
				out.print(link + "\n");
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		
	}
	
	/**
	 * method to get the .txt link for a book, given its loyalbooks.com URL
	 * @param bookUrl the generic loyalbooks.com URL for the book
	 */
	public void getTxt(String bookUrl) {
		Document doc = null;
		try {
			// connect to book's page
			doc = Jsoup.connect(bookUrl).get();
			
			// get the link to the book's .txt file 
			Element box = doc.select("td.book2:contains(Text File eBook)").first();
	
			// exit function for books that don't have a proper .txt link
			if (box == null) {
				return;
			}
			
			Element link = box.select("a").first();
			
			// store URL in string
			String url = link.attr("abs:href");
			
			// store URL in HashMap (to avoid repeat URLs)
			txtLinks.put(url, 1);
			
		} catch (IOException e) {
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
				// use index % 3 because each book has
				// three copies of its page link
				if (index % 3 == 0) {
					Elements l = link.select("a[href]");
					
					// store the link in a string
					String bookUrl = l.attr("abs:href");
					// the the .txt URL for the given book's page
					getTxt(bookUrl);
				}
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * get links to all genres
	 * @return ArrayList of links to each genre
	 */
	public ArrayList<String> findGenres() {
		String url = "http://www.loyalbooks.com/genre-menu";
		
		// string to append to end of genre url
		String append = "?type=ebook&results=100";
		
		// keep track of genre links
		ArrayList<String> genreLinks = new ArrayList<String>();
		
		Document doc = null;
		
		try {
			// connect to url
			doc = Jsoup.connect(url).get();
			
			// go to the table with all genres
			Element genreTable = doc.selectFirst("table:contains(All Genres)");
			
			// extract all links from the genre table
			Elements genres = genreTable.select("td > a");
			
			// add each link to the ArrayList
			for (Element g : genres) {
				String link = g.attr("abs:href");
				link = link + append;			
				genreLinks.add(link);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return genreLinks;
	}
	
	/**
	 * accessor method
	 * @return all links to txt files
	 */
	public HashMap<String, Integer> getTxtLinks() {
		return txtLinks;
	}
	
	/**
	 * accessor method
	 * @return links to each genre
	 */
	public ArrayList<String> getGenres() {
		return genres;
	}
	
}
