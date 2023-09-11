package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
<<<<<<< HEAD
=======

>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR1-2.git
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertThrows;


=======
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR1-2.git
import org.junit.Test;
<<<<<<< HEAD


=======
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR1-2.git
import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Student;


public class SortedListTest {

	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		//TODO Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		
	}

	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//TODO Test adding to the front, middle and back of the list
		
		//TODO Test adding a null element
		
		//TODO Test adding a duplicate element
	}
	
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		
		//TODO Add some elements to the list
		
		//TODO Test getting an element at an index < 0
		
		//TODO Test getting an element at size
		
	}
	/**
	 * This test method checks the assert errors are thrown correctly for the remove class
	 * as well as testing proper function on boundary values.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		//Test removing from an empty list
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		
		//Add some elements to the list - at least 4
		
		list.add("banana");
		list.add("cantaloupe");
		list.add("pear");
		list.add("apple");
		
		//Test removing an element at an index < 0
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		
		//Test removing an element at size
		
		assertThrows(IndexOutOfBoundsException.class,
				() ->  list.remove(list.size()));
		
		//Test removing a middle element
		
		assertEquals("banana", list.remove(1));
		
		//Test removing the last element
		
		assertEquals("pear", list.remove(2));
		
		//Test removing the first element
		
		assertEquals("apple", list.remove(0));
		
		//Test removing the last element
	}
	/**
	 * This test method validates the functionality of the indexOf method, 
	 * specifically that it properly returns -1 on an item not in the list,
	 * appropriately throws an error for a null input, 
	 * and appropriately reports the index of items in the sorted list.
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("pear"));
		
		//Add some elements
		
		list.add("banana");
		list.add("cantaloupe");
		list.add("pear");
		list.add("apple");
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("cantaloupe"));
		assertEquals(3, list.indexOf("pear"));
		
		//Test checking the index of null
		
		assertThrows(NullPointerException.class,
				() -> list.indexOf(null));
		
	}
	/**
	 * This test method evaluates that the clear method properly clears a list.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		
		list.add("banana");
		list.add("cantaloupe");
		list.add("pear");
		list.add("apple");
		
		//Clear the list
		
		list.clear();
		
		//Test that the list is empty
		
		assertEquals(0, list.size());
	}

	/**
	 * Tests a list to see if the size of the list is equal to 0, in other words, tests to see if
	 * the list is empty.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertEquals(0, list.size());
		
		//Add at least one element
		list.add("element");
		
		//Check that the list is no longer empty
		assertNotEquals(0, list.size());
	}

	@Test
	/**
	 * Tests a list to see if it contains a specific element.
	 */
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertEquals(0, list.size());
		
		//Add some elements
		list.add("1");
		list.add("2");
		list.add("ice cream");
		
		//Test some true and false cases
		assertTrue(list.contains("1"));
		assertFalse(list.contains("pizza"));
		assertTrue(list.contains("ice cream"));
		assertFalse(list.contains("5"));
	}
	
	/**
	 * Tests two lists to see if they are equal to each other.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list2 = list1;
		list3.add("x");
		list3.add("y");
		list3.add("z");
		
		
		//Test for equality and non-equality
		assertEquals(list1, list2);
		assertNotEquals(list1, list3);
		assertNotEquals(list2, list3);
	}
	
	/**
	 * Tests two lists to see if their hash codes are equal.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list2 = list1;
		list3.add("x");
		list3.add("y");
		list3.add("z");
		
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}

}
 