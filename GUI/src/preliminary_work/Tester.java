package preliminary_work;
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

		BookDataPrinter bdp = new BookDataPrinter();
		bdp.printBooks();
		
	}
}
