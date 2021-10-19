/**
 * 
 * @author Eleni Rotsides
 * @section A
 * @pledge I pledge my honor that I have abided by the Stevens honor system.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest<E> {

	/**
	 * Testing operations on empty DLL
	 */
	@Test
	void createEmptyDLL() {
		IDLList<E> dll = new IDLList<E>();
		
		assertThrows(IllegalStateException.class, () -> { dll.getHead(); });
		assertThrows(IllegalStateException.class, () -> { dll.getLast(); });
		assertEquals(dll.size(), 0);
		assertThrows(IllegalStateException.class, () -> { dll.get(0); });
		assertThrows(IllegalStateException.class, () -> { dll.toString(); });
	}
	
	/**
	 * Tests inserting elements at head using add(elem)
	 * Checking to make sure insertion works by using the other methods as well (size, getHead, getLast, toString, etc)
	 */
	@Test
	void addElemAtHead() {
		IDLList<Integer> dll = new IDLList<Integer>();
		
		// add elem that is null 
		assertThrows(IllegalArgumentException.class, () -> { dll.add(null); });
		
		// add 5 to head
		assertEquals(dll.add(5), true);
		assertEquals(dll.size(), 1);
		assertEquals(dll.getHead(), 5);
		assertEquals(dll.getLast(), 5);
		assertEquals(dll.toString(), "[5]");
		
		// add 2 to head
		assertEquals(dll.add(2), true);
		assertEquals(dll.size(), 2);
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 5);
		assertEquals(dll.toString(), "[2, 5]");
		
		//add 7 to head
		assertEquals(dll.add(7), true);
		assertEquals(dll.size(), 3);
		assertEquals(dll.getHead(), 7);
		assertEquals(dll.getLast(), 5);
		assertEquals(dll.toString(), "[7, 2, 5]");
	}
	
	/**
	 * Tests inserting elements at index using add(index, elem)
	 * Checking to make sure insertion works by using the other methods as well (size, getHead, getLast, toString, etc)
	 */
	@Test
	void addElemAtIndex() {
		IDLList<Integer> dll = new IDLList<Integer>();
		
		// add elem that is null 
		assertThrows(IllegalArgumentException.class, () -> { dll.add(null); });
		
		//insert at negative index
		assertThrows(IllegalStateException.class, () -> { dll.add(-5, 7); });
		
		//insert at nonexistent index
		assertThrows(IllegalStateException.class, () -> { dll.add(9, 7); });
				
		// create a list with some elements
		assertEquals(dll.add(5), true);
		assertEquals(dll.add(2), true);
		assertEquals(dll.add(7), true);
		assertEquals(dll.toString(), "[7, 2, 5]");
		
		// add 8 at index 1
		assertEquals(dll.add(1, 8), true);
		assertEquals(dll.getHead(), 7);
		assertEquals(dll.getLast(), 5);
		assertEquals(dll.size(), 4);
		assertEquals(dll.toString(), "[7, 8, 2, 5]");
		
		// add 9 at head
		assertEquals(dll.add(0, 9), true);
		assertEquals(dll.getHead(), 9);
		assertEquals(dll.getLast(), 5);
		assertEquals(dll.size(), 5);
		assertEquals(dll.toString(), "[9, 7, 8, 2, 5]");
		
		// create new dll instance to test insertion when head is null
		IDLList<Integer> dll2 = new IDLList<Integer>();
		
		assertEquals(dll2.add(0, 5), true);
		assertEquals(dll2.size(), 1);
		assertEquals(dll2.getHead(), 5);
		assertEquals(dll2.getLast(), 5);
		assertEquals(dll2.toString(), "[5]");
		
		// new dll instance to test insertion when tail is null (same thing as when head is null)
		IDLList<Integer> dll3 = new IDLList<Integer>();
		
		assertEquals(dll3.add(dll3.size(), 5), true);
		assertEquals(dll3.size(), 1);
		assertEquals(dll3.getHead(), 5);
		assertEquals(dll3.getLast(), 5);
		assertEquals(dll3.toString(), "[5]");
		
		// insert at the last element
		assertEquals(dll3.add(dll3.size() - 1, 4), true);
		assertEquals(dll3.size(), 2);
		assertEquals(dll3.getHead(), 4);
		assertEquals(dll3.getLast(), 5);
		assertEquals(dll3.toString(), "[4, 5]");
		
		assertEquals(dll3.add(dll3.size() - 1, 3), true);
		assertEquals(dll3.size(), 3);
		assertEquals(dll3.getHead(), 4);
		assertEquals(dll3.getLast(), 5);
		assertEquals(dll3.toString(), "[4, 3, 5]");
		
		// now test insert at head
		assertEquals(dll3.add(0, 27), true);
		assertEquals(dll3.size(), 4);
		assertEquals(dll3.getHead(), 27);
		assertEquals(dll3.getLast(), 5);
		assertEquals(dll3.toString(), "[27, 4, 3, 5]");
	}
	
	/**
	 * Tests inserting elements at tail using append(elem)
	 * Checking to make sure insertion works by using the other methods as well (size, getHead, getLast, toString, etc)
	 */
	@Test
	void append() {
		IDLList<Integer> dll = new IDLList<Integer>();
		
		// add elem that is null 
		assertThrows(IllegalArgumentException.class, () -> { dll.add(null); });
		
		// create a list with some elements
		assertEquals(dll.add(5), true);
		assertEquals(dll.add(2), true);
		assertEquals(dll.add(7), true);
		assertEquals(dll.toString(), "[7, 2, 5]");
		
		//append elements to list
		assertEquals(dll.append(0), true);
		assertEquals(dll.size(), 4);
		assertEquals(dll.getHead(), 7);
		assertEquals(dll.getLast(), 0);
		assertEquals(dll.toString(), "[7, 2, 5, 0]");
		
		assertEquals(dll.append(3), true);
		assertEquals(dll.size(), 5);
		assertEquals(dll.getHead(), 7);
		assertEquals(dll.getLast(), 3);
		assertEquals(dll.toString(), "[7, 2, 5, 0, 3]");
		
		//create new dll instance to test insertion when tail is null
		IDLList<Integer> dll2 = new IDLList<Integer>();
		
		//start adding elements at tail
		assertEquals(dll2.append(4), true);
		assertEquals(dll2.size(), 1);
		assertEquals(dll2.getHead(), 4);
		assertEquals(dll2.getLast(), 4);
		assertEquals(dll2.toString(), "[4]");
		
		assertEquals(dll2.append(5), true);
		assertEquals(dll2.size(), 2);
		assertEquals(dll2.getHead(), 4);
		assertEquals(dll2.getLast(), 5);
		assertEquals(dll2.toString(), "[4, 5]");
	}
	
	/**
	 * Tests getting an element at index using get(index)
	 */
	@Test
	void get() {
		IDLList<Integer> dll = new IDLList<Integer>();
		
		//get when index does not exist
		assertThrows(IllegalStateException.class, () -> { dll.get(4); });
		
		//add some elements to the dll
		assertEquals(dll.add(5), true);
		assertEquals(dll.add(2), true);
		
		//get element
		assertEquals(dll.get(0), 2);
		assertEquals(dll.get(1), 5);
		
		//get when index does not exist
		assertThrows(IllegalStateException.class, () -> { dll.get(3); });
		
		//get at a negative index
		assertThrows(IllegalStateException.class, () -> { dll.get(-3); });
	}
	
	/**
	 * Tests removing element at head using remove(elem)
	 * Checking to make sure removal works by using the other methods as well (size, getHead, getLast, toString, etc)
	 */
	@Test
	void removeElemAtHead() {
		IDLList<Integer> dll = new IDLList<Integer>();
		
		//remove when head does not exist
		assertThrows(IllegalStateException.class, () -> { dll.remove(); });
		
		// create a list with some elements
		assertEquals(dll.add(5), true);
		assertEquals(dll.add(2), true);
		assertEquals(dll.add(7), true);
		assertEquals(dll.toString(), "[7, 2, 5]");
		
		// remove elements
		assertEquals(dll.remove(), 7);
		assertEquals(dll.size(), 2);
		assertEquals(dll.toString(), "[2, 5]");
		assertEquals(dll.remove(), 2);
		assertEquals(dll.size(), 1);
		assertEquals(dll.toString(), "[5]");
		assertEquals(dll.remove(), 5);
		assertEquals(dll.size(), 0);
		assertThrows(IllegalStateException.class, () -> { dll.toString(); });
		
		//remove when head does not exist
		assertThrows(IllegalStateException.class, () -> { dll.remove(); });
	}
	
	/**
	 * Tests removing element at tail using removeLast(elem)
	 * Checking to make sure removal works by using the other methods as well (size, getHead, getLast, toString, etc)
	 */
	@Test
	void removeLast() {
		IDLList<Integer> dll = new IDLList<Integer>();
		
		//remove when dll does not exist
		assertThrows(IllegalStateException.class, () -> { dll.removeLast(); });
		
		// create a list with some elements
		assertEquals(dll.add(5), true);
		assertEquals(dll.add(2), true);
		assertEquals(dll.add(7), true);
		assertEquals(dll.toString(), "[7, 2, 5]");
		
		// remove elements at tail
		assertEquals(dll.removeLast(), 5);
		assertEquals(dll.size(), 2);
		assertEquals(dll.toString(), "[7, 2]");
		assertEquals(dll.removeLast(), 2);
		assertEquals(dll.size(), 1);
		assertEquals(dll.toString(), "[7]");
		assertEquals(dll.removeLast(), 7);
		assertEquals(dll.size(), 0);
		
		//remove when head does not exist
		assertThrows(IllegalStateException.class, () -> { dll.remove(); });
	}
	
	/**
	 * Tests removing element at index using removeAt(index)
	 * Checking to make sure removal works by using the other methods as well (size, getHead, getLast, toString, etc)
	 */
	@Test
	void removeAt() {
		IDLList<Integer> dll = new IDLList<Integer>();
		
		//remove when dll does not exist
		assertThrows(IllegalStateException.class, () -> { dll.removeAt(3); });
		
		//remove at negative index
		assertThrows(IllegalStateException.class, () -> { dll.removeAt(-3); });
		
		//remove at head
		assertThrows(IllegalStateException.class, () -> { dll.removeAt(0); });
		
		// create a list with some elements
		assertEquals(dll.add(5), true);
		assertEquals(dll.add(2), true);
		assertEquals(dll.add(7), true);
		assertEquals(dll.add(4), true);
		assertEquals(dll.add(8), true);
		assertEquals(dll.toString(), "[8, 4, 7, 2, 5]");
		
		// remove elements at certain index
		assertEquals(dll.removeAt(2), 7);
		assertEquals(dll.size(), 4);
		assertEquals(dll.toString(), "[8, 4, 2, 5]");
		assertEquals(dll.removeAt(0), 8);
		assertEquals(dll.size(), 3);
		assertEquals(dll.toString(), "[4, 2, 5]");
		assertEquals(dll.removeAt(dll.size() - 1), 5);
		assertEquals(dll.size(), 2);
		assertEquals(dll.toString(), "[4, 2]");
	}
	
	/**
	 * Tests removing first occurrence at index using remove(elem)
	 * Checking to make sure removal works by using the other methods as well (size, getHead, getLast, toString, etc)
	 */
	@Test
	void removeFirstOccurrence() {
		IDLList<Integer> dll = new IDLList<Integer>();
		
		//remove null element that doesn't exist
		assertEquals(dll.remove(null), false);
		
		//remove nonexistent element
		assertEquals(dll.remove(5), false);
		
		// create a list with some elements
		assertEquals(dll.add(5), true);
		assertEquals(dll.add(2), true);
		assertEquals(dll.add(7), true);
		assertEquals(dll.add(4), true);
		assertEquals(dll.add(2), true);
		assertEquals(dll.toString(), "[2, 4, 7, 2, 5]");
		
		//remove the first occurrence of 2
		assertEquals(dll.remove(2), true);
		assertEquals(dll.size(), 4);
		assertEquals(dll.toString(), "[4, 7, 2, 5]");
		
		// new dll instance to test a different scenario
		IDLList<Integer> dll2 = new IDLList<Integer>();
		
		// create a list with some elements
		assertEquals(dll2.add(6), true);
		assertEquals(dll2.add(4), true);
		assertEquals(dll2.add(4), true);
		assertEquals(dll2.add(2), true);
		assertEquals(dll2.add(1), true);
		assertEquals(dll2.toString(), "[1, 2, 4, 4, 6]");
		
		//remove the first occurrence of 4
		assertEquals(dll2.remove(4), true);
		assertEquals(dll2.size(), 4);
		assertEquals(dll2.getHead(), 1);
		assertEquals(dll2.getLast(), 6);
		assertEquals(dll2.toString(), "[1, 2, 4, 6]");
		
		//remove the first occurrence of 6
		assertEquals(dll2.remove(6), true);
		assertEquals(dll2.size(), 3);
		assertEquals(dll2.getHead(), 1);
		assertEquals(dll2.getLast(), 4);
		assertEquals(dll2.toString(), "[1, 2, 4]");
		
		//remove nonexistent element
		assertEquals(dll2.remove(3), false);
		assertEquals(dll2.size(), 3);
		assertEquals(dll2.getHead(), 1);
		assertEquals(dll2.getLast(), 4);
		assertEquals(dll2.toString(), "[1, 2, 4]");
	}
	
	/**
	 * Testing all the methods sequentially to ensure all data fields are being updated appropriately in between
	 * different methods. This is like a makeshift integration test.
	 */
	@Test
	void testEverythingTogether() {
		IDLList<Integer> dll = new IDLList<Integer>();

		// make sure these throw errors on an empty list, and size is 0
		assertThrows(IllegalStateException.class, () -> { dll.getHead(); });
		assertThrows(IllegalStateException.class, () -> { dll.getLast(); });
		assertEquals(dll.size(), 0);
		assertThrows(IllegalStateException.class, () -> { dll.get(0); });
		assertThrows(IllegalStateException.class, () -> { dll.toString(); });
		
		// test all the functions I've written sequentially, to make sure they work together
		assertEquals(dll.add(3), true);
		assertEquals(dll.add(0, 2), true);
		assertEquals(dll.toString(), "[2, 3]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 3);
		assertEquals(dll.size(), 2);
		assertEquals(dll.get(1), 3);
		
		assertEquals(dll.add(dll.size() - 1, 4), true);
		assertEquals(dll.toString(), "[2, 4, 3]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 3);
		assertEquals(dll.size(), 3);
		
		assertEquals(dll.append(1), true);
		assertEquals(dll.toString(), "[2, 4, 3, 1]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 1);
		assertEquals(dll.size(), 4);
		
		assertEquals(dll.add(2, 2), true);
		assertEquals(dll.toString(), "[2, 4, 2, 3, 1]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 1);
		assertEquals(dll.size(), 5);
		
		assertEquals(dll.add(3), true);
		assertEquals(dll.toString(), "[3, 2, 4, 2, 3, 1]");
		assertEquals(dll.getHead(), 3);
		assertEquals(dll.getLast(), 1);
		assertEquals(dll.size(), 6);
		
		assertEquals(dll.remove(), 3);
		assertEquals(dll.toString(), "[2, 4, 2, 3, 1]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 1);
		assertEquals(dll.size(), 5);
		
		assertEquals(dll.removeLast(), 1);
		assertEquals(dll.toString(), "[2, 4, 2, 3]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 3);
		assertEquals(dll.size(), 4);
		
		assertEquals(dll.get(0), 2);
		assertEquals(dll.get(dll.size() - 1), 3);
		assertEquals(dll.get(1), 4);
		
		assertEquals(dll.add(5), true);
		assertEquals(dll.toString(), "[5, 2, 4, 2, 3]");
		assertEquals(dll.getHead(), 5);
		assertEquals(dll.getLast(), 3);
		assertEquals(dll.size(), 5);
		
		assertEquals(dll.remove(5), true);
		assertEquals(dll.toString(), "[2, 4, 2, 3]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 3);
		assertEquals(dll.size(), 4);
		
		assertEquals(dll.remove(3), true);
		assertEquals(dll.toString(), "[2, 4, 2]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 2);
		assertEquals(dll.size(), 3);
		
		assertEquals(dll.remove(4), true);
		assertEquals(dll.toString(), "[2, 2]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 2);
		assertEquals(dll.size(), 2);
		
		assertEquals(dll.remove(2), true);
		assertEquals(dll.toString(), "[2]");
		assertEquals(dll.getHead(), 2);
		assertEquals(dll.getLast(), 2);
		assertEquals(dll.size(), 1);
		
		assertEquals(dll.remove(2), true);
		assertThrows(IllegalStateException.class, () -> { dll.getHead(); });
		assertThrows(IllegalStateException.class, () -> { dll.getLast(); });
		assertEquals(dll.size(), 0);
		assertThrows(IllegalStateException.class, () -> { dll.get(0); });
		assertThrows(IllegalStateException.class, () -> { dll.toString(); });
	}
}
