package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BookObjDataReaderTest {

	int index;
	
	/**
	 * test case to check if the Book-Data.csv has any null values
	 */
	@Test
	public void testForNullValues() {
		// to skip the first row
		index = 2;
		
		BookObjDataReader bodr = new BookObjDataReader();
		
		ArrayList<BookObj> books = new ArrayList<BookObj>(bodr.getBooks());
		
		boolean hasNullValue = false;
		
		for (BookObj book : books) {
			if (book.getTitle().equals(null) || book.getAuthor().equals(null) ||
					book.getLocation().equals(null) || book.getPubDate() == 0) {
				hasNullValue = true;
				break;
			}
			index++;
		}
		assertFalse("There is a null value for book at row " + index, hasNullValue);
	}

}
