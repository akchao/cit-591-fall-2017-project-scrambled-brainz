import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookObjDataReader {

	private ArrayList<BookObj> books;
	

	public BookObjDataReader() {
		String file = "Book-Data.csv";
		readCSV(file);
	}
	
	public void readCSV(String file) {
		Scanner in = null;
		try {
			File reader = new File(file);
			in = new Scanner(reader);
			
			// read over headers
			in.nextLine();
			
			while (in.hasNextLine()) {
				String [] line = in.nextLine().split(",");
				BookObj book = new BookObj(line);
				books.add(book);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
	
	public ArrayList<BookObj> getBooks() {
		return books;
	}
	
}
