/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.StringToHashMap.StringToHashMap;

/**
 * Unit tests for the {@link com.StringToHashMap.StringToHashMap} class.
 * 
 * @author JPhillips
 * @version 9/20/15
 */
public class StringToHashMapTests {

	private static final String input1 = "Steve = Engineer\nJake = Steve\nMatt = QA\nJon = CTO";
	private static final String input2 = "Steve = Engineer\nJake = {{Steve}}\n    Matt = QA\nJon = CTO";
	private static final String input3 = "Andrew = Engineer\nJake = {{Steve}}\nMatt = QA\nJon = CTO";
	private static final String input4 = "Steve = Engineer\nJake = {{St{{ev}}e}}\nMatt = QA\nJon = CTO";
	private static final String input5 = "Steve = Engineer    \r    Jake = Steve,Matt = QA\rJon = CTO";
	private static final String input6 = "Steve = Engineer  \r\n  Jake = Steve\r\nMatt = QA\r\nJon = CTO";
	private static final String input7 = "Steve:Engineer,Jake:Steve,Matt:QA/Jon:CTO";
	private static final String input8 = "Steve:Engineer,Jake:Steve,Matt:QA,Jon-CTO";
	private HashMap<String, String> hashMap1;
	private HashMap<String, String> hashMap2;
	private HashMap<String, String> hashMap3;
	private HashMap<String, String> hashMap4;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		hashMap1 = new HashMap<String, String>();
		hashMap1.put("Steve", "Engineer"); 
		hashMap1.put("Jake", "Steve");
		hashMap1.put("Matt", "QA"); 
		hashMap1.put("Jon", "CTO");
		
		hashMap2 = new HashMap<String, String>();
		hashMap2.put("Steve", "Engineer"); 
		hashMap2.put("Jake", "{{Steve}}");
		hashMap2.put("Matt", "QA"); 
		hashMap2.put("Jon", "CTO");
		
		hashMap3 = new HashMap<String, String>();
		hashMap3.put("Steve", "Engineer"); 
		hashMap3.put("Jake", "Engineer");
		hashMap3.put("Matt", "QA"); 
		hashMap3.put("Jon", "CTO");
		
		hashMap4 = new HashMap<String, String>();
		hashMap4.put("Andrew", "Engineer"); 
		hashMap4.put("Jake", "{{Steve}}");
		hashMap4.put("Matt", "QA"); 
		hashMap4.put("Jon", "CTO");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		hashMap1 = null;
		hashMap2 = null;
		hashMap3 = null;
		hashMap4 = null;
	}

	/**
	 * Test method for {@link com.StringToHashMap.StringToHashMap#MyHashMap(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testStringToHashMap() {
		assertEquals(hashMap1, new StringToHashMap(input1));
		assertEquals(hashMap2, new StringToHashMap(input2));
		assertEquals(hashMap4, new StringToHashMap(input3));
		assertEquals(hashMap1, new StringToHashMap(input5));
		assertEquals(hashMap1, new StringToHashMap(input6));
		
		try {
			new StringToHashMap(input7);
		}
		catch(Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
			assertEquals("Error parsing key/value pairs. Check key/value pairs delimiter.", e.getMessage());
		}
		
		try {
			new StringToHashMap(input8);
		}
		catch(Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
			assertEquals("Error parsing value in key/value pair. Check key/value pair delimiter in input String.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link com.StringToHashMap.StringToHashMap#findReferences()}.
	 */
	@Test
	public void testFindReferences() {
		assertEquals(hashMap3, new StringToHashMap(input2).findReferences());
		assertEquals(hashMap4, new StringToHashMap(input3).findReferences());
		assertEquals(hashMap3, new StringToHashMap(input4).findReferences());
		assertEquals(hashMap1, new StringToHashMap(input5).findReferences());
	}

}
