package application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * class to get all of the book meta-data
 * will read a .csv file with links to books
 * on loyalbooks.com
 * 
 * @author Drawjk705
 *
 */
public class BookMetaDataGetter {

	private ArrayList<String> urls = new ArrayList<String>();
	ArrayList<BookMetaDataHolder> books = new ArrayList<BookMetaDataHolder>();	
	private String json;
	private static int index;
	private static int booksWithData;
	
	/**
	 * constructor class
	 * will automatically populate an ArrayList of BookMetaData objects
	 */
	public BookMetaDataGetter() {
		index = 0;
		booksWithData = 0;
		String file = "BookURLs.csv";
		readBookUrls(file);

		for (String url : urls) {
			BookMetaDataHolder book = new BookMetaDataHolder(url);
			books.add(book);
		}
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

	/**
	 * getter method
	 * @return get all book URLs
	 */
	public ArrayList<String> getUrls() {
		return urls;
	}
	
	/**
	 * getter method
	 * @return all BookMetaData objects
	 */
	public ArrayList<BookMetaDataHolder> getBooks() {
		return books;
	}
	
}