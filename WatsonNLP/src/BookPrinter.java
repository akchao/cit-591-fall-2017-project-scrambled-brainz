import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * class to print books and their data
 * @author Drawjk705
 *
 */
public class BookPrinter {

	public BookPrinter() {

	}
	
	public void printBooks() {
		BookReader br = new BookReader();
		
		ArrayList<Book> books = new ArrayList<Book>(br.getBooks());
		
		try {
			PrintWriter out = new PrintWriter("Book-Data.csv");
			
			out.println("Title,Author,Publication Date,Location,Genre,URL");
			
			for (Book book : books) {
				System.out.println(book.getTitle());
				out.print(book.getTitle() + ",");
				out.print(book.getAuthor() + ",");
				out.print(book.getPubDate() + ",");
				out.print(book.getLocation() + ",");
				out.print(book.getGenre() + ",");
				out.println(book.getUrl() + "\n");				
			}
			
			out.close();		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
