/**
 * Tester class for WatsonAnalyzer and WatsonParser
 * @author Alice
 *
 */
public class WatsonTester {

	public static void main(String[] args) {

//		String textName = "pride-prejudice.txt";
//		WatsonAnalyzer wa = new WatsonAnalyzer(textName);
//		WatsonParser wp = new WatsonParser(); 
//		String textToParse = wa.getWatsonEntities().get(0).toString();
//		wp.parseEntity(textToParse);
//
//		System.out.println(wa.getWatsonResponse());
		
		
		//Running results if we break up book into sections
		//TODO: abstract into method and make into an arraylist 
		//(user prescribed number of files)
		String text1 = "pride-prejudice-1.txt"; 
		String text2 = "pride-prejudice-2.txt";
		String text3 = "pride-prejudice-3.txt";
		WatsonAnalyzer wa1 = new WatsonAnalyzer(text1);
		System.out.println("part 1");
		System.out.println(wa1.getWatsonResponse());

		WatsonAnalyzer wa2 = new WatsonAnalyzer(text2);
		System.out.println("part 2");
		System.out.println(wa2.getWatsonResponse());

		WatsonAnalyzer wa3 = new WatsonAnalyzer(text3);
		System.out.println("part 3");
		System.out.println(wa3.getWatsonResponse());



		
		
	}

}
