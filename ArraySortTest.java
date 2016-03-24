import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;

import java.util.Arrays;
import java.util.Random;

public class ArraySortTest {
	
	private static int[][] controlArrays = null;
	private static int[][] sortedArrays = null;
	private static int[][] doubleSortedArrays = null;
	private static int numArrays = 500;		// number of arrays to generate
	private static int upperSize = 10000;	// upper limit of the size of each array

	/*
	 * Generates and sorts the arrays
	 */
	@BeforeClass
	public static void makeAndSortArrays()
	{
		controlArrays = new int[numArrays][];
		sortedArrays = new int[numArrays][];
		doubleSortedArrays = new int[numArrays][];
		Random rand = new Random(System.nanoTime());
		
		// generate the random arrays
		for (int i = 0; i < numArrays; i++)
		{
			int currSize = rand.nextInt(upperSize) + 1;
			controlArrays[i] = new int[currSize];
			sortedArrays[i] = new int[currSize];
			doubleSortedArrays[i] = new int[currSize];
			
			for (int j = 0; j < currSize; j++)
			{
				int randNum = rand.nextInt();
				controlArrays[i][j] = randNum;
				sortedArrays[i][j] = randNum;
				doubleSortedArrays[i][j] = randNum;
			}
		}
		
		// sort the test arrays
		for (int i = 0; i < sortedArrays.length; i++)
		{
			Arrays.sort(sortedArrays[i]);
			Arrays.sort(doubleSortedArrays[i]);
			Arrays.sort(doubleSortedArrays[i]);
		}
	}
	
	/*
	 * The length of each sorted array should be the same as before the sort
	 */
	@Test
	public void testLength()
	{
		boolean notEqual = false;
		for (int i = 0; i < sortedArrays.length; i++)
		{
			if (sortedArrays[i].length != controlArrays[i].length)
			{
				notEqual = true;
				break;
			}
		}
		
		assertFalse(notEqual);
	}
	
	/*
	 * Each array should be in ascending order
	 */
	@Test
	public void testAscending()
	{
		boolean notAscending = false;
		for (int i = 0; i < sortedArrays.length; i++)
		{
			for (int j = 0; j < sortedArrays[i].length - 1; j++)
			{
				if (sortedArrays[i][j] > sortedArrays[i][j + 1])
				{
					notAscending = true;
					break;
				}
			}
		}
		
		assertFalse(notAscending);
	}
	
	/*
	 * Each sorted array should be of type int[]
	 */
	@Test
	public void testArray()
	{
		boolean notIntArray = false;
		for (int i = 0; i < sortedArrays.length; i++)
		{
			Object tempClass = sortedArrays[i];
			if (!(tempClass instanceof int[]))
			{
				notIntArray = true;
				break;
			}
		}
		
		assertFalse(notIntArray);
	}
	
	/*
	 * Each sorted array should contain the same values as the original array
	 */
	@Test
	public void testValues()
	{
		boolean notSameValue = false;
		for (int i = 0; i < controlArrays.length; i++)
		{
			for (int j = 0; j < controlArrays[i].length; j++)
			{
				if (Arrays.binarySearch(sortedArrays[i], controlArrays[i][j]) < 0)
				{
					notSameValue = true;
					break;
				}
			}
		}
		
		assertFalse(notSameValue);
	}
	
	/*
	 * Sorting the array twice should produce an identical array
	 */
	@Test
	public void idempotent()
	{
		boolean theyreDifferent = false;
		for (int i = 0; i < sortedArrays.length; i++)
		{
			for (int j = 0; j < sortedArrays[i].length; j++)
			{
				if (doubleSortedArrays[i][j] != sortedArrays[i][j])
				{
					theyreDifferent = true;
					break;
				}
			}
		}
		
		assertFalse(theyreDifferent);
	}
	
	

}
