import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * this is a class to represent a single book
 *
 * @author Drawjk705
 *
 */

public class Book {

	private String url;
	private ArrayList<String> keywords;
	private String tone;
	private String mood;
	private String author;
	private String title;
	private String pubDate;
	private String location;
	private ArrayList<String> genre = new ArrayList<String>();
	
	
	public Book(String url) {
		this.url = url;
		
		ArrayList<String> bookData = new ArrayList<String>(extractWikipediaData());
		
		for (String datum : bookData) {
			String s = datum;
			if (s.contains("Author")) {
				author = s.replaceAll("Author ", "");
			} else if (s.contains("Country")) {
				location = s.replaceAll("Country ", "");
			} else if (s.contains("Genre")) {
				String genreTemp = s.replaceAll("Genre ", "");
				if (genreTemp.contains(",")) {
					String[] gt = genreTemp.split(", ");
					for (String g : gt) {
						genre.add(g);
					}
				}				
			} else if (s.contains("Publi")) {
				pubDate = s.replace("Publication Date ", "");
				pubDate = s.replace("Published ", "");
			} else if (s.contains("Title")) {
				title = s.replace("Title ", "");
			}
		}
	}


	/**
	 * class to extract data from Wikipedia
	 * on the book in question
	 * @param url the book's Gutenberg URL
	 */
	public ArrayList<String> extractWikipediaData() {
		String wikipediaUrl = searchForBookWikipediaPage();

		ArrayList<String> bookData = new ArrayList<String>();

		
		Document doc = null;
		try {
			// connect to the book's Wikipedia page
			doc = Jsoup.connect(wikipediaUrl).get();
			
			// find the book's "infobox"
			Elements tables = doc.select("table");
			
			Element infobox = null;
			
			for (Element table : tables) {
				if (table.hasClass("infobox")) {
					infobox = table;
					break;
				}
			}
			
			// go through each row of the infobox 
			Elements tableTags = infobox.select("tr");			
	
			// add each row's text to array
			for (Element row : tableTags) {				
				bookData.add(row.text());
			}
			
			// get book's title
			String title = infobox.selectFirst("caption").text();
			
			// append label to title
			title = "Title " + title;
			
			// add title to ArrayList
			bookData.add(title);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookData;
	}
	
	
	/**
	 * method to find the Wikipedia page for the book
	 * in question
	 * @param url the book's LoyalBooks url
	 * @return Wikipedia URL with data on the book
	 */
	public String searchForBookWikipediaPage() {
		String searchParam = getBookSearchParam();
		
		// String to begin searching Google
		String google = "https://www.google.com/search?q=";
		
		// String to query Google for Wikipedia
		String wikipedia = " Wikipedia";
		
		// encode search text to make it URL-friendly
		String encodedSearchText = null;
		try {
			encodedSearchText = URLEncoder.encode(searchParam + wikipedia,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// concatenate with the Google search link
		google += encodedSearchText;
		
		// get Google search page
		Document doc = null;
		try {
			doc = Jsoup.connect(google).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// go the the Wikipedia page -- the first link from Google
		Element wikiPage = doc.getElementsByTag("cite").get(0);
		
		String wikipediaUrl = wikiPage.text();

		return wikipediaUrl;
	}
	
	
	/**
	 * method to get data on a given book
	 * based on its URL
	 * @param url the corresponding URL to the book
	 */
	public String getBookSearchParam() {
		String title = null;
		
		try {
			// get the book title
			Document doc = Jsoup.connect(url).get();
			title = doc.title();
			
			// get ride of the " - Free Ebook" in title
			// so that can search Google more easily
			title = title.toLowerCase();
			title = title.replaceAll(" - free ebook", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return title;
	}
	
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
	 * @return keywords
	 */
	public ArrayList<String> getKeywords() {
		return keywords;
	}

	/**
	 * 
	 * @return tone
	 */
	public String getTone() {
		return tone;
	}

	/**
	 * 
	 * @return mood
	 */
	public String getMood() {
		return mood;
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
	 * 
	 * @return genre
	 */
	public ArrayList<String> getGenre() {
		return genre;
	}
}
