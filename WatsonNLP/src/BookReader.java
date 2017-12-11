import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.annotations.JsonAdapter;

/**
 * class to get all of the book data
 * will read a .csv file with links to books
 * on Project Gutenberg
 * @author Drawjk705
 *
 */
public class BookReader {

	private ArrayList<String> urls = new ArrayList<String>();
	ArrayList<Book> books = new ArrayList<Book>();	
	private String json;
	
//	private String url;
	
	
	public BookReader() {
		String file = "Book URLS.csv";
		readBookUrls(file);
		
		for (String url : urls) {
			Book book = new Book(url);
			books.add(book);
		}
	}
	
	public void findBookUrls(String url) {
				
	}
	
	
	
	
		
	/**
	 * read the URL of each book, and add it
	 * to an ArrayList
	 * @param file the .csv file containing
	 * all of the URLs
	 */
	public void readBookUrls(String file) {
		try {
			File inputFile = new File(file);
			Scanner in = new Scanner(inputFile);
			
			// skip the column header
			in.nextLine();
			
			while (in.hasNextLine()) {
				urls.add(in.nextLine());
			}
			
			// close the document
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getUrls() {
		return urls;
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	
}
