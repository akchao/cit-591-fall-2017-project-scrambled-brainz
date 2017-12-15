import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		
//		Pattern p = Pattern.compile("([0-9]{4})");
//		Matcher m = p.matcher("Publication date 28 October 1726 (291 years ago) (1726-10-28)");
//		
//		m.find();
//		
//		System.out.println(m.group(0));
//		
		
		
		
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
