import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * class to print books and their data
 * @author Drawjk705
 *
 */
public class BookPrinter {

	int index = 0;
	int bookCount = 0;
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
		
		PrintWriter out = null;
		
		try {
			out = new PrintWriter("Book-Data.csv");
			
			// write header
			out.println("Title,Author,Publication Date,Location,URL");
			
			// write each line
			for (Book book : books) {
				System.out.println(++index);
				book.storeData();
				if (book.hasData()) {
					out.print(book.getTitle() + ",");
					out.print(book.getAuthor() + ",");
					out.print(book.getPubDate() + ",");
					out.print(book.getLocation() + ",");
					out.println(book.getUrl() + ",");
					out.flush();
					System.out.println("\t" + ++bookCount);
				}
			}
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	
	
}
