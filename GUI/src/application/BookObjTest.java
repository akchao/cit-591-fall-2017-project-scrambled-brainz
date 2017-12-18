package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BookObjTest {

	/**
	 * make sure that parse double exception handling is working
	 */
	@Test
	public void testParseDoubleExceptionHandling() {
		String[] array = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z", "aa", "bb", "cc", "dd" };
		
		BookObj book = new BookObj(array);
		
		ArrayList<Double> disgust = new ArrayList<Double>(book.getDisgust());
		ArrayList<Double> joy = new ArrayList<Double>(book.getJoy());
		ArrayList<Double> fear = new ArrayList<Double>(book.getFear());
		ArrayList<Double> sadness = new ArrayList<Double>(book.getSadness());
		ArrayList<Double> anger = new ArrayList<Double>(book.getAnger());
		
		ArrayList<ArrayList<Double>> emotions = new ArrayList<ArrayList<Double>>();
		emotions.add(disgust);
		emotions.add(joy);
		emotions.add(fear);
		emotions.add(sadness);
		emotions.add(anger);
		
		double sum = 0;
		
		// since all values should be 0, sum should be 0
		for (ArrayList<Double> emotion : emotions) {
			for (Double d : emotion) {
				sum += d;
			}
		}	
		
		assertEquals("disgust_1 should be 0.0"
				+ " as a result of exception handling", 0.0, sum, 0);			
	}

	/**
	 * make sure that arrays that are too small are
	 * handled correctly
	 */
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testIndexOutOfBoundsExceptionHandling() {
		String[] arrayTooSmall = { "a" };
		BookObj book = new BookObj(arrayTooSmall);
	}

}
