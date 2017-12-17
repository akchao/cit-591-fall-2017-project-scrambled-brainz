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
	private static int index;
	private static int booksWithData;
	
//	private String url;
	
	
	public BookReader() {
		index = 0;
		booksWithData = 0;
		String file = "Book URLS.csv";
		readBookUrls(file);
		
//		urls.add("http://www.loyalbooks.com/download/text/Oliver-Twist-by-Charles-Dickens.txt");
//		urls.add("http://www.loyalbooks.com/download/text/Duchess-of-Malfi.txt");
//		urls.add("http://www.loyalbooks.com/download/text/Master-Builder-Henrik-Ibsen.txt");
//		urls.add("http://www.loyalbooks.com/download/text/Gullivers-Travels-by-Jonathan-Swift.txt");
//		urls.add("http://www.loyalbooks.com/download/text/This-Side-of-Paradise-by-F-Scott-Fitzgerald.txt");
		
		for (String url : urls) {
			Book book = new Book(url);
			books.add(book);
//			book.storeData();
//			System.out.println(++index);
//			if (book.hasData()) {
//				System.out.println("\t" + ++booksWithData);
//				books.add(book);
//			}
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

	public ArrayList<String> getUrls() {
		return urls;
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	
}
