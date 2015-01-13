import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class HashingTest {

	Set<String> setA;
	Set<String> setB;
	
	@Before
	public void setUp() {
		setA = new HashSet<String>();
		setA.add("Mark");
		setA.add("Tom");
		
		setB = new HashSet<String>();
		setB.add("Tom");
		setB.add("Mark");
	}
	
	@Test
	public void test() {
//		fail("Not yet implemented");
		assertTrue(true);
		
		if(setA.equals(setB))
			System.out.println("Yes .... it is equals.");
		
		System.out.println("setA hashed: " + setA.hashCode());
		System.out.println("setB hashed: " + setB.hashCode());
		
	}

}
