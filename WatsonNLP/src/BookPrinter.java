import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * class to print books and their data
 * @author Drawjk705
 *
 */
public class BookPrinter {

	/**
	 * constructor
	 */
	public BookPrinter() {
	}
	
	/**
	 * method to print books to .csv file
	 */
	public void printBooks() {
		BookReader br = new BookReader();
		
		// store books in ArrayList
		ArrayList<Book> books = new ArrayList<Book>(br.getBooks());
		
		// get data for each book
		for (Book book : books) {
			book.storeData();
		}
		
		try {
			PrintWriter out = new PrintWriter("Book-Data.csv");
			
			// write header
			out.println("Title,Author,Publication Date,Location,URL");
			
			// write each line
			for (Book book : books) {
				out.print(book.getTitle() + ",");
				out.print(book.getAuthor() + ",");
				out.print(book.getPubDate() + ",");
				out.print(book.getLocation() + ",");
				out.println(book.getUrl() + ",");				
			}
			
			// close the buffer
			out.close();		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
