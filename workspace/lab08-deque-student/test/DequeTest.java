import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {
	
	Deque<String> deque;
	
	@Before
	public void setup() {
		deque = new LinkedDeque<String>();
	}

	@Test
	public void testFrontMethods() {
		assertTrue(deque.isEmpty()); // originally the deque should be empty
		deque.addToFront("Bob"); // add Bob to the front of the deque
		assertFalse(deque.isEmpty()); // deque should no longer be empty
		assertEquals("Bob", deque.first());
		deque.addToFront("VIP Alice"); // add VIP Alice to the front of the deque
		assertEquals("VIP Alice", deque.first());
		assertEquals("VIP Alice", deque.removeFront());
		assertFalse(deque.isEmpty());
		assertEquals("Bob", deque.first()); // Bob should still be in the deque
		deque.removeFront();
		assertTrue(deque.isEmpty());
	}
	
	@Test
	public void testRearMethods() {
		// TODO
		// YOUR CODE HERE
		assertTrue(deque.isEmpty());
		deque.addToRear("Mike");
		assertEquals("Mike",deque.last());
		deque.addToRear("Max");
		assertEquals("Mike",deque.first());
		assertEquals("Max",deque.last());
		assertEquals("Max",deque.removeRear());
		assertFalse(deque.isEmpty());
		assertEquals("Mike",deque.first());
		assertEquals("Mike",deque.last());
		deque.removeFront();
		assertTrue(deque.isEmpty());
	}
}
