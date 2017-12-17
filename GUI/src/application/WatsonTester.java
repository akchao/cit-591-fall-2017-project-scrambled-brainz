package application;


/**
 * Tester class for two under-the-hood functionalities related to Watson API:
 * BookDataPrinter (depends on BookEmotionData, BookMetaDataReader -> BookMetaData)
 * BookEmotionData (depends on BookSplitter, WatsonAnalyzer, and WatsonParser) 
 * Both of these will require a Watson key stored in a class called "Secret" with 
 * public static instance variables called "username" and "password."  
 * See SecretTemplate for format.
 * @author Alice
 *
 */
public class WatsonTester {

	public static void main(String[] args) {

    //  Test BookDataPrinter will automatically test BookEmotionData and run it for multiple books
	//  and print results in a CSV file called "Book-Data.csv"
		// BookDataPrinter bdp = new BookDataPrinter(); 
		// bdp.printBooks();
	
		
	//  Test just BookEmotionData for a single book and print results to console	
		String url = "http://www.loyalbooks.com/download/text/Gullivers-Travels-by-Jonathan-Swift.txt";
		BookEmotionData bed = new BookEmotionData(url);
		
		System.out.println("Emotion scores of book from beginning to end:");
		System.out.println("(URL: " + url + " )\n");
		System.out.println("Emotion: [section 1, section 2, section 3, section 4, section 5]");
		System.out.println("Anger: " + bed.getAnger());
		System.out.println("Disgust: " + bed.getDisgust());
		System.out.println("Fear: " + bed.getFear());
		System.out.println("Joy: " + bed.getJoy());
		System.out.println("Sadness: " + bed.getSadness());
		
	}

}
