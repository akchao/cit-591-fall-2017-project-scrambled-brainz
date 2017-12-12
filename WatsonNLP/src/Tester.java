/**
 * tester class for book functions
 *
 * @author Drawjk705
 *
 */

public class Tester {

	public static void main(String[] args) {

		URLGetter ug = new URLGetter();
		
		ug.findBookUrls("http://www.loyalbooks.com/genre/Adventure");
		
		System.out.println(ug.getTxtLinks());
		
		ug.writeLinksToCsv();
		
	}
}
