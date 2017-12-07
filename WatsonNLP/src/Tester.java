import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

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

	public static void main(String[] args) throws IOException {
		
		BookReader br = new BookReader();
		
		ArrayList<Book> books = new ArrayList<Book>(br.getBooks());
		
		for (Book book: books) {
			book.printBookData();
		}
		
//		Book book = new Book("https://www.gutenberg.org/files/2701/2701-h/2701-h.htm");
//		
//		book.printBookData();
		
	}
}
