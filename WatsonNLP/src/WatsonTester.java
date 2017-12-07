/**
 * Tester class for WatsonAnalyzer and WatsonParser
 * @author Alice
 *
 */
public class WatsonTester {

	public static void main(String[] args) {

		String textName = "pride-prejudice.txt";
		WatsonAnalyzer wa = new WatsonAnalyzer(textName);
		WatsonParser wp = new WatsonParser(); 
		String textToParse = wa.getWatsonEntities().get(0).toString();
		wp.parseEntity(textToParse);
		
		System.out.println(wa.getWatsonResponse());

	}

}
