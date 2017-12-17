package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionResult;

/**
 * Tester class for BookSplitter, WatsonAnalyzer, and WatsonParser 
 * @author Alice
 *
 */
public class WatsonTester {

	public static void main(String[] args) {

//////	TEST: WatsonAnalyzer pre-segmented .txt files
/*
		WatsonAnalyzer wa1 = new WatsonAnalyzer("pride-prejudice-1.txt");
		String ch1 = wa1.getWatsonResponse().getEmotion().toString();
		
		System.out.println(ch1);
		WatsonAnalyzer wa2 = new WatsonAnalyzer("pride-prejudice-2.txt");
		System.out.println(wa2.getWatsonResponse());
		WatsonAnalyzer wa3 = new WatsonAnalyzer("pride-prejudice-3.txt");
		System.out.println(wa3.getWatsonResponse());
*/
		
//////	TEST: WatsonParser document emotion
/*
		String emotionTester = "{\n" + 
				"  \"document\": {\n" + 
				"    \"emotion\": {\n" + 
				"      \"anger\": 0.144436,\n" + 
				"      \"disgust\": 0.074289,\n" + 
				"      \"fear\": 0.130087,\n" + 
				"      \"joy\": 0.599415,\n" + 
				"      \"sadness\": 0.519859\n" + 
				"    }\n" + 
				"  }\n" + 
				"}";
		
		WatsonParser wp = new WatsonParser();
		wp.parseDocEmotion(emotionTester);
		System.out.println(wp.getAngerScore());
		System.out.println(wp.getDisgustScore());
		System.out.println(wp.getFearScore());
		System.out.println(wp.getJoyScore());
		System.out.println(wp.getSadnessScore());
*/
		
/////	TEST: BookSplitter -> WatsonAnalyzer -> WatsonParser
/*		
		String url = "http://www.loyalbooks.com/download/text/King-Solomons-Mines-by-Haggard.txt";
		BookSplitter split = new BookSplitter(url);
		String[] bookSegments = split.getBookSegments();
		
		double[] anger = new double[5];
		double[] disgust = new double[5];
		double[] fear = new double[5];
		double[] joy = new double[5];
		double[] sadness = new double[5];
		
		int index = 0;
		
		for (String s : bookSegments) {
			WatsonAnalyzer wa = new WatsonAnalyzer(s);
			String emotion = wa.getWatsonDocEmotion();

			WatsonParser wp = new WatsonParser();
			wp.parseDocEmotion(emotion);
			anger[index] = wp.getAngerScore();
			disgust[index] = wp.getDisgustScore();
			fear[index] = wp.getFearScore();
			joy[index] = wp.getJoyScore();
			sadness[index] = wp.getSadnessScore();

			index++;
		}
		
		System.out.println("anger" + Arrays.toString(anger));
		System.out.println("disgust" + Arrays.toString(disgust));
		System.out.println("fear" + Arrays.toString(fear));
		System.out.println("joy" + Arrays.toString(joy));
		System.out.println("sadness" + Arrays.toString(sadness));
*/

/////	TEST: BookEmotionData = (BookSplitter -> WatsonAnalyzer -> WatsonParser)	
	/*
//		String url = "http://www.loyalbooks.com/download/text/King-Solomons-Mines-by-Haggard.txt";
//		String url = "http://www.loyalbooks.com/download/text/Hedda-Gabler.txt";
//		String url = "http://www.loyalbooks.com/download/text/King-Lear-by-William-Shakespeare.txt";
//		String url = "http://www.loyalbooks.com/download/text/Mrs-Peter-Rabbit-by-Thornton-W-Burgess.txt";
		
		
		BookEmotionData bookData = new BookEmotionData(url);
		System.out.println("anger: " + bookData.getAnger());
		System.out.println("disgust: " + bookData.getDisgust());
		System.out.println("fear: " + bookData.getFear());
		System.out.println("joy: " + bookData.getJoy());
		System.out.println("sadness: " + bookData.getSadness());
		
		//response:
		//anger: [0.111292, 0.131232, 0.115586, 0.141064, 0.11936]
		//disgust: [0.075014, 0.12144, 0.519952, 0.141819, 0.143354]
		//fear: [0.09306, 0.122896, 0.145105, 0.171134, 0.444784]
		//joy: [0.603973, 0.576895, 0.586473, 0.539747, 0.57395]
		//sadness: [0.547961, 0.581861, 0.535293, 0.577122, 0.490002]
*/
		BookDataPrinter bdp = new BookDataPrinter(); 
		bdp.printBooks();
		
////// 	TEST: WatsonAnalyzer -> WatsonParser for Entities/Characters 
/*
		String textName = "pride-prejudice.txt";
		WatsonAnalyzer wa = new WatsonAnalyzer(textName);
		
		WatsonParser wp = new WatsonParser(); 
		String textToParse = wa.getWatsonEntities().get(0).toString();
		wp.parsePersonEntity(textToParse);
		System.out.println(wa.getWatsonResponse());
*/
		

//////  TEST: WatsonParser for Entities/Characters (in isolation)
/*
		String sampleEntityNonPerson = "{\n" + 
				"  \"type\": \"Location\",\n" + 
				"  \"relevance\": 0.880592,\n" + 
				"  \"count\": 70,\n" + 
				"  \"text\": \"Elizabeth Bennet\",\n" + 
				"  \"emotion\": {\n" + 
				"    \"anger\": 0.078232,\n" + 
				"    \"disgust\": 0.026196,\n" + 
				"    \"fear\": 0.116198,\n" + 
				"    \"joy\": 0.244804,\n" + 
				"    \"sadness\": 0.112424\n" + 
				"  },\n" + 
				"  \"sentiment\": {\n" + 
				"    \"score\": 0.0\n" + 
				"  }\n" + 
				"}";
		
		String sampleEntityPerson = "{\n" + 
				"  \"type\": \"Person\",\n" + 
				"  \"relevance\": 0.880592,\n" + 
				"  \"count\": 70,\n" + 
				"  \"text\": \"Elizabeth Bennet\",\n" + 
				"  \"emotion\": {\n" + 
				"    \"anger\": 0.078232,\n" + 
				"    \"disgust\": 0.026196,\n" + 
				"    \"fear\": 0.116198,\n" + 
				"    \"joy\": 0.244804,\n" + 
				"    \"sadness\": 0.112424\n" + 
				"  },\n" + 
				"  \"sentiment\": {\n" + 
				"    \"score\": 0.0\n" + 
				"  }\n" + 
				"}";
		
		List<String> sampleList = new ArrayList<>();
				sampleList.add(sampleEntityPerson);
				sampleList.add(sampleEntityNonPerson);
		
		JsonObject entityResponse = new JsonParser().parse(sampleEntityNonPerson)
				.getAsJsonObject();
		
		// check if type is "Person" otherwise do nothing
		String type = entityResponse.get("type").getAsString();
		if (type.equals("Person")) {
			System.out.println("person");
		} else {
			System.out.println("not a person");
		}
		
		wp.removeNonPerson(sampleList);
		System.out.println(sampleList);
		wp.parsePersonEntity(sampleList.get(0));
		System.out.println(wp.getName());
		System.out.println(wp.getCount());
		System.out.println(wp.getRelevance());
		System.out.println(wp.getAngerScore());
		System.out.println(wp.getDisgustScore());
*/		
		
	}

}
