package application;
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

	
	/**
	 * Constructs the book splitter, which divides up an online book into sections
	 * currently, number of sections is 5, but this could be changed
	 * @param url the book's url
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
	public void getBookFromUrl(String link) {
		URL url;
		String inputLine;
		try {
			
			url = new URL(link);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(url.openStream()));
			while ((inputLine = in.readLine()) != null) {
				Pattern p = Pattern.compile(".+");
				Matcher m = p.matcher(inputLine);
				if (m.find()) {								// to obtain only sentences with letters
					bookLines.add(inputLine.trim());
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
			e.printStackTrace();
		}
	}

	public void segmentBook(int numberOfSegments) {
		bookSegments = new String[numberOfSegments];
		if (bookLines.size() > numberOfSegments) {
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
				} else {
					segment = segment + bookLines.get(k) + " ";
					count++;
				}
			}		
		} 
	}

	/**
	 * @return the bookLines
	 */
	public ArrayList<String> getBookLines() {
		return bookLines;
	}

	
	/**
	 * @return the bookSegments
	 */
	public String[] getBookSegments() {
		return bookSegments;
	}



	
	

}
