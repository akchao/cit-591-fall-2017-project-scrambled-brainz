import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
	
	
	public Book(String url) {
		this.url = url;
		extractHtml(url);
	}
	
	
	/**
	 * get title from URL page
	 * @param url
	 */
	public void extractHtml(String url){		
		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println("test: " + Jsoup.parse(url));
			String title = doc.title();
			System.out.println(title);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public String extractTitle() {
		String title = "";
		
		return title;
	}
	
	public static void main(String[] args) {
		System.out.println("test!");
	}

}
