import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DbpediaExperiment {

	
	public static void main(String[] args) {
		
		try {
			Document doc = Jsoup.connect("http://en.wikipedia.org/w/index.php?title=Special:Search&search=To+Kill+a+Mockingbird").get();
			
			Elements tables = doc.select("table");
			
			// get the infobox
			Element infobox = null;
			
			for (Element table : tables) {
				if (table.hasClass("infobox")) {
					infobox = table;
					System.out.println(infobox.outerHtml());
					break;
				}
			}
			
			Elements tableTags = infobox.select("tr");
			
			for (Element row : tableTags) {
				System.out.println(row.text());
			}
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
