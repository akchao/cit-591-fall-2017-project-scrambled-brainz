package preliminary_work;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;

/**
 * Class reads in a book from a URL and splits the book into 5 sections. 
 * @author Alice
 *
 */
public class BookSplitter {

	private ArrayList<String> bookLines;
	private String[] bookSegments;
	private boolean hasGoodUrl = false;
	private boolean hasBookLines = false;
	private boolean hasBookSegments = false;
	
	
	/**
	 * Constructs the book splitter, which divides up an online book into sections
	 * currently, number of sections is 5, but this could be changed
	 * @param url the book's .txt url
	 */
	public BookSplitter(String url) {
		bookLines = new ArrayList<>();
		
		if (!url.equals(null)) {
			getBookFromUrl(url);
			segmentBook(5);
		}

	}

	
	/**
	 * Method reads in a URL and stores the book lines into an arraylist
	 */
	private void getBookFromUrl(String link) {
		URL url;
		String inputLine;
		try {
			url = new URL(link);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(url.openStream()));
			while ((inputLine = in.readLine()) != null) {
				hasGoodUrl = true;
				
				Pattern p = Pattern.compile(".+");
				Matcher m = p.matcher(inputLine);
				if (m.find()) {								// to obtain only sentences with letters
					bookLines.add(inputLine.trim());
					hasBookLines = true;

				} else {
					continue;
				} 
			}
			in.close();
		} catch (MalformedURLException mue) {
			System.out.println(mue.getMessage());
		} catch (IOException ioe) {			
			System.out.println(ioe.getMessage());	
		} catch (Exception e) {
			System.out.println(e.getMessage());;
		}
	}
	/**
	 * method to split the book into segments
	 * @param numberOfSegments the number of segments into which
	 * to split the book
	 */		
	private void segmentBook(int numberOfSegments) {
		bookSegments = new String[numberOfSegments];
		if (hasBookLines && bookLines.size() > numberOfSegments) {
			int counter = (bookLines.size() / numberOfSegments);
			int count = 0;

			String segment = "";
			int i = 0;
			for (int k=0; k<bookLines.size(); k++) {
				if (count == (counter-1)) {
					bookSegments[i] = segment;
					segment = "";
					count = 0;
					i++;
					hasBookSegments = true;
				} else {
					segment = segment + bookLines.get(k) + " ";
					count++;
				}
			}		
		} 
	}

	/**
	 * @return the bookLines the book lines stored as an array
	 */
	public ArrayList<String> getBookLines() {
		return bookLines;
	}

	
	/**
	 * @return the bookSegments the book segments stored as an array
	 */
	public String[] getBookSegments() {
		return bookSegments;
	}


	/**
	 * @return the hasBookLines true if BookSplitter read in the book, false otherwise
	 */
	public boolean doesHaveBookLines() {
		return hasBookLines;
	}


	/**
	 * @return the hasBookSegments true if BookSplitter split the book into segments, false otherwise
	 */
	public boolean doesHaveBookSegments() {
		return hasBookSegments;
	}


	/**
	 * @return the hasGoodUrl true if URL is correct, false if incomplete or missing
	 */
	public boolean doesHaveGoodUrl() {
		return hasGoodUrl;
	}


	
}
