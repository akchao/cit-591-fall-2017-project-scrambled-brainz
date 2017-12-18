package application;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * this is a class to represent a single book's meta-data
 * before it has been process for its emotion data 
 *
 * @author Drawjk705
 *
 */

public class BookMetaDataHolder {

	private String url;
	private String author;
	private String title;
	private String pubDate;
	private String location;
	
	// indicator to assess whether book has
	// Wikipedia data or not
	private Boolean hasData;
	
	/**
	 * constructor
	 * @param url the book's .txt URL
	 */
	public BookMetaDataHolder (String url) {
		this.url = url;
		hasData = false;
	}
	
	/**
	 * store data into Book object's instance variables
	 * only if the book has data to store
	 */
	public void storeData() {
		ArrayList<String> bookData = new ArrayList<String>();
		try { 
			 bookData = new ArrayList<String>(extractWikipediaData());
		} catch (Exception NullPointerExeption) {
			// exit method if has no data
			return;
		}
		// if the book has data, populate its instance variables
		if (hasData) {
			for (String datum : bookData) {
				String s = datum;
				if (s.contains("Author")) {
					author = s.replaceAll("Author ", "");
					author = author.replaceAll(",", "");
				} else if (s.contains("Country")) {
					location = s.replaceAll("Country ", "");
					location = location.replaceAll(",", "");
				} else if (s.contains("Publi")) {
					// extract year from publication date
					Pattern p = Pattern.compile("([0-9]{4})");
					Matcher m = p.matcher(s);
					if (m.find()) {
						pubDate = m.group(0);
					}
				} else if (s.contains("Title")) {
					title = s.replace("Title ", "");
					title = title.replaceAll(",", "");
				}
			}
		}
	}

	/**
	 * class to extract data from Wikipedia
	 * on the book in question
	 * @param url the book's loyalbooks.com URL
	 */
	public ArrayList<String> extractWikipediaData() {
		// get the URL for the book's Wikipedia page
		String wikipediaUrl = searchForBookWikipediaPage();
		
		// ArrayList to store the book's meta-data
		ArrayList<String> bookData = new ArrayList<String>();
		
		Document doc = null;
		try {
			// connect to the book's Wikipedia page
			doc = Jsoup.connect(wikipediaUrl).get();
			
			// find the book's "infobox"
			
			// get all tables from the Wikipedia page
			Elements tables = doc.select("table");
			
			Element infobox = null;
			
			// find the table of class "infobox"
			for (Element table : tables) {
				if (table.hasClass("infobox")) {
					infobox = table;
					break;
				}
			}
			
			// go through each row of the infobox 
			Elements tableTags = null; 
			
			// scrape the infobox for meta-data
			try {
				tableTags = infobox.select("tr");
				// add each row's text to array
				for (Element row : tableTags) {				
					bookData.add(row.text());
				}
				hasData = true;
			} catch (Exception NullPointerException) {
				// if no infobox exists, set hasData to false,
				// and exit the method
				hasData = false;
				return null;
			}
			// get book's title
			String title = null; 		
			try {
				title = infobox.selectFirst("caption").text();
				// append label to title
				title = "Title " + title;
				
				// add title to ArrayList
				bookData.add(title);
				hasData = true;
			} catch (Exception NullPointerException) {
				hasData = false;
				return null;
			}
		} catch (Exception IllegalArgumentException) {
			// if cannot access Wikipedia page 
			IllegalArgumentException.printStackTrace();
		}
		return bookData;
	}
	
	/**
	 * method to find the Wikipedia page for the book
	 * in question
	 * 
	 * Will first search google for Wikipedia page.
	 * Once Google starts rejecting requests due to
	 * bot behavior, go to another search engine
	 * 
	 * @param url the book's LoyalBooks url
	 * @return Wikipedia URL with data on the book
	 */
	public String searchForBookWikipediaPage() {
		// get the text to use to search Google (or whatever engine)
		String searchParam = getBookSearchParam();
		
		// String to begin searching Google
		String google = "https://www.google.com/search?q=";
		
		// create the appropriate URL to submit
		String url = encodeUrl(google, searchParam);
		
		// get Google search page
		Document doc = null;
		try {
			// connect to Google
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// should Google fail, go to Yahoo
			String yahoo = "https://search.yahoo.com/search?p=";
			url = encodeUrl(yahoo, searchParam);
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e1) {
				// should Yahoo fail, go to Bing
				String bing = "https://www.bing.com/search?q=";
				url = encodeUrl(bing, searchParam);
				try {
					doc = Jsoup.connect(url).get();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		// go the the Wikipedia page -- the first link from 
		// the search engine
		Element wikiPage = doc.getElementsByTag("cite").get(0);
		
		// get and return the Wikipedia URL
		String wikipediaUrl = wikiPage.text();

		return wikipediaUrl;
	}
	
	/**
	 * method to encode a search parameter to be URL-friendly
	 * @param url the search engine URL
	 * to encode the search parameter with
	 * @param searchParam the query for the search engine
	 * @return the encoded search engine URL with the query
	 */
	public String encodeUrl(String url, String searchParam) {
		String encodedSearchText = null;
		// String to append to eng of searchParam
		String wikipedia = " Wikipedia";
		try {
			encodedSearchText = URLEncoder.encode(searchParam + wikipedia,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// append the encoded search text to the search engine URL
		url += encodedSearchText;
		
		return url;
	}
	
	
	/**
	 * method to get data on a given book
	 * based on its URL
	 * @param url the corresponding URL to the book
	 */
	public String getBookSearchParam() {
		// the book's title will be in its URL
		String title = url;
		
		// get rid of all unnecessary pieces of the URL:
		title = title.replaceAll("http://www.loyalbooks.com/download/text/", "");
		title = title.replaceAll(".txt", "");
		title = title.replaceAll("-", " ");
		
		return title;
	}
	
	/**
	 * method to print all data on a book
	 * to the console
	 */
	public void printBookData() {
		System.out.println("Url: " + url);
		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		System.out.println("PubDate: " + pubDate);
		System.out.println("Location: " + location);
		System.out.println("\n ############ \n");
	}

	
	/**
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @return pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * 
	 * @return location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * assess if book has data
	 * @return
	 */
	public Boolean hasData() {
		return hasData;
	}
}