package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class SortedListTest {

	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains("apple"));
		assertTrue(list.add("apple1"));
		list.add("apple2");
		list.add("apple3");
		list.add("apple4");
		list.add("apple5");
		list.add("apple6");
		list.add("apple7");
		list.add("apple8");
		list.add("apple9");
		assertTrue(list.add("apple10"));
		list.add("apple 11");
		assertEquals(11, list.size());
	}

	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		list.add("apple");
		assertEquals("banana", list.get(1));
		assertEquals("apple", list.get(0));
		list.add("orange");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("orange", list.get(2));
		assertThrows(NullPointerException.class, () -> list.add(null));
		assertThrows(IllegalArgumentException.class, () -> list.add("apple"));
	}
	
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		list.add("apple");
		list.add("banana");
		list.add("cranberry");
		list.add("durian");
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-100000));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
	}
	
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		
		//TODO Add some elements to the list - at least 4
		
		//TODO Test removing an element at an index < 0
		
		//TODO Test removing an element at size
		
		//TODO Test removing a middle element
		
		//TODO Test removing the last element
		
		//TODO Test removing the first element
		
		//TODO Test removing the last element
	}
	
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test indexOf on an empty list
		
		//TODO Add some elements
		
		//TODO Test various calls to indexOf for elements in the list
		//and not in the list
		
		//TODO Test checking the index of null
		
	}
	
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		
		//TODO Clear the list
		
		//TODO Test that the list is empty
	}

	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		
		//TODO Add at least one element
		
		//TODO Check that the list is no longer empty
	}

	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		
		//TODO Add some elements
		
		//TODO Test some true and false cases
	}
	
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		
		//TODO Test for equality and non-equality
	}
	
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		
		//TODO Test for the same and different hashCodes
	}

}
 