package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class to read the Book-Data.csv file (file with *all* book data:
 * meta-data, title, URL, emotions...) and to create an ArrayList
 * of Book objects from the resulting data 
 * @author Drawjk705
 *
 */
public class BookObjDataReader {

	// ArrayList to store all books
	private ArrayList<BookObj> books = new ArrayList<BookObj>();
	
	/**
	 * constructor
	 * will read in the Book-Data.csv file
	 */
	public BookObjDataReader() {
		String file = "Book-Data.csv";
		readCSV(file);
	}
	
	/**
	 * method to read in the file
	 * @param file the file to read
	 */
	public void readCSV(String file) {
		Scanner in = null;
		try {
			File reader = new File(file);
			in = new Scanner(reader);
			
			// read over headers
			in.nextLine();
			
			while (in.hasNextLine()) {
				// split each cell (by commas) into an array
				String [] line = in.nextLine().split(",");
				// store that array in a book object (which will
				// parse that array)
				BookObj book = new BookObj(line);
				// add the book object to the books ArrayList
				books.add(book);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
	
	/**
	 * getter method
	 * @return ArrayList of books
	 */
	public ArrayList<BookObj> getBooks() {
		return books;
	}
	
}
