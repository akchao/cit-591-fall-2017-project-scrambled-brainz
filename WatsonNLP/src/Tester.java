import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * tester class for book functions
 *
 * @author Drawjk705
 *
 */

public class Tester {

	public static void main(String[] args) {

		BookPrinter bp = new BookPrinter();
		
		bp.printBooks();
		
//		BookReader br = new BookReader();
//		
//		ArrayList<Book> books = new ArrayList<Book>(br.getBooks());
//		
//		for (Book book : books) {
//			book.extractWikipediaData();
//		}
//		
		
		
//		Book b = new Book("http://www.loyalbooks.com/download/text/Crime-and-Punishment-by-Fyodor-Dostoyevsky.txt");
//		
//		b.storeData();
//		
//		b.printBookData();
		
		
		
	}
}
