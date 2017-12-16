import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Tester class for WatsonAnalyzer and WatsonParser
 * @author Alice
 *
 */
public class WatsonTester {

	public static void main(String[] args) {

		String textName = "pride-prejudice.txt";
		WatsonAnalyzer wa = new WatsonAnalyzer(textName);
		
//		WatsonParser wp = new WatsonParser(); 
//		String textToParse = wa.getWatsonEntities().get(0).toString();
//		wp.parsePersonEntity(textToParse);
		System.out.println(wa.getWatsonResponse());
		
		/*
		String url = "http://www.loyalbooks.com/download/text/King-Solomons-Mines-by-Haggard.txt";
		BookSplitter bs = new BookSplitter(url);
		
		String[] bookSegments = bs.getBookSegments();
		for (String s : bookSegments) {
			WatsonAnalyzer wa = new WatsonAnalyzer(s);
			List<String> entities = wa.getWatsonEntitiesString();
			
			WatsonParser wp = new WatsonParser();
			wp.removeNonPerson(entities);
			wp.parsePersonEntity(entities.get(0));
		}
		*/
		
//		System.out.println(bs.getBook());
//		System.out.println(bs.getBookLines());
		
//		ArrayList<String> book = bs.getBookLines();
//		System.out.println(book.size());
		
		//Running results if we break up book into sections
		//TODO: abstract into method and make into an arraylist 
		//(user prescribed number of files)
//		String text1 = "pride-prejudice-1.txt"; 
//		String text2 = "pride-prejudice-2.txt";
//		String text3 = "pride-prejudice-3.txt";
//		WatsonAnalyzer wa1 = new WatsonAnalyzer(text1);
//		System.out.println("part 1");
//		System.out.println(wa1.getWatsonResponse());
//
//		WatsonAnalyzer wa2 = new WatsonAnalyzer(text2);
//		System.out.println("part 2");
//		System.out.println(wa2.getWatsonResponse());
//
//		WatsonAnalyzer wa3 = new WatsonAnalyzer(text3);
//		System.out.println("part 3");
//		System.out.println(wa3.getWatsonResponse());

		
		
//		wp.buildCharacterProfile();
		
		
//		String sampleEntityNonPerson = "{\n" + 
//				"  \"type\": \"Location\",\n" + 
//				"  \"relevance\": 0.880592,\n" + 
//				"  \"count\": 70,\n" + 
//				"  \"text\": \"Elizabeth Bennet\",\n" + 
//				"  \"emotion\": {\n" + 
//				"    \"anger\": 0.078232,\n" + 
//				"    \"disgust\": 0.026196,\n" + 
//				"    \"fear\": 0.116198,\n" + 
//				"    \"joy\": 0.244804,\n" + 
//				"    \"sadness\": 0.112424\n" + 
//				"  },\n" + 
//				"  \"sentiment\": {\n" + 
//				"    \"score\": 0.0\n" + 
//				"  }\n" + 
//				"}";
//		
//		String sampleEntityPerson = "{\n" + 
//				"  \"type\": \"Person\",\n" + 
//				"  \"relevance\": 0.880592,\n" + 
//				"  \"count\": 70,\n" + 
//				"  \"text\": \"Elizabeth Bennet\",\n" + 
//				"  \"emotion\": {\n" + 
//				"    \"anger\": 0.078232,\n" + 
//				"    \"disgust\": 0.026196,\n" + 
//				"    \"fear\": 0.116198,\n" + 
//				"    \"joy\": 0.244804,\n" + 
//				"    \"sadness\": 0.112424\n" + 
//				"  },\n" + 
//				"  \"sentiment\": {\n" + 
//				"    \"score\": 0.0\n" + 
//				"  }\n" + 
//				"}";
		
//		List<String> sampleList = new ArrayList<>();
//				sampleList.add(sampleEntityPerson);
//				sampleList.add(sampleEntityNonPerson);
		
//		JsonObject entityResponse = new JsonParser().parse(sampleEntityNonPerson)
//				.getAsJsonObject();
//		
//		// check if type is "Person" otherwise do nothing
//		String type = entityResponse.get("type").getAsString();
//		if (type.equals("Person")) {
//			System.out.println("person");
//		} else {
//			System.out.println("not a person");
//		}
//		
//		wp.removeNonPerson(sampleList);
//		System.out.println(sampleList);
//		wp.parsePersonEntity(sampleList.get(0));
//		System.out.println(wp.getName());
//		System.out.println(wp.getCount());
//		System.out.println(wp.getRelevance());
//		System.out.println(wp.getAngerScore());
//		System.out.println(wp.getDisgustScore());
//
//		wp.buildCharacterProfile().toString();
		
		
	}

}
