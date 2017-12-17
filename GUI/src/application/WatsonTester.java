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
		
	}

}
