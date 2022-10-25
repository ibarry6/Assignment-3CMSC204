import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortedDoubleLinkedList_Ibrahima_Test {

	SortedDoubleLinkedList<Phone> sortedlinkedPhone;
	PhoneComparator Phonecomparator;
	
	public Phone itel=new Phone("Itel", 2010);
	public Phone samsumg=new Phone("Samsumg", 2010);
	public Phone huawei=new Phone("Huawei", 2010);
	public Phone pixel=new Phone("Pixel", 2010);
	
	public ArrayList<Phone> fill = new ArrayList<Phone>();
	
	@BeforeEach
	void setUp() throws Exception {
		Phonecomparator= new PhoneComparator();
		sortedlinkedPhone= new SortedDoubleLinkedList<Phone>(Phonecomparator);
	}

	@AfterEach
	void tearDown() throws Exception {
		sortedlinkedPhone = null;
		Phonecomparator=null;
	}

	@Test
	void testAddToEnd() {
//		fail("Not yet implemented");
		try {
			sortedlinkedPhone.addToEnd(itel);
		}catch(UnsupportedOperationException e){
			assertTrue("Invalid operation for sorted list", true);
		}
	}

	@Test
	void testAddToFront() {
//		fail("Not yet implemented");
		try {
			sortedlinkedPhone.addToFront(itel);
		}catch(UnsupportedOperationException e){
			assertTrue(e.getMessage(), true);
		}
	}

	@Test
	void testAddPhone() {
//		fail("Not yet implemented");
		//alphabetical order: i m s z
		sortedlinkedPhone.add(samsumg);
		sortedlinkedPhone.add(itel);
		assertEquals(itel, sortedlinkedPhone.getFirst());
		assertEquals(samsumg, sortedlinkedPhone.getLast());
		sortedlinkedPhone.add(pixel);
		assertEquals(samsumg, sortedlinkedPhone.getLast());
	}

	@Test
	void testRemoveFirstPhone() {
//		fail("Not yet implemented");
		
		sortedlinkedPhone.add(samsumg);
		sortedlinkedPhone.add(itel);
		sortedlinkedPhone.add(huawei);
		assertEquals(huawei, sortedlinkedPhone.getFirst());
		sortedlinkedPhone.remove(huawei, Phonecomparator);
		assertEquals(itel, sortedlinkedPhone.getFirst());
		assertEquals(2, sortedlinkedPhone.getSize());
	}


	@Test
	void testGetFirst() {
//		fail("Not yet implemented");
		
		sortedlinkedPhone.add(samsumg);
		assertEquals(samsumg, sortedlinkedPhone.getFirst());
		sortedlinkedPhone.add(itel);
		sortedlinkedPhone.add(pixel);
		assertEquals(itel, sortedlinkedPhone.getFirst());
	}

	@Test
	void testGetLast() {
//		fail("Not yet implemented");
		//alphabetical order: i m s z
		sortedlinkedPhone.add(samsumg);
		sortedlinkedPhone.add(itel);
		assertEquals(itel, sortedlinkedPhone.getFirst());
		sortedlinkedPhone.add(pixel);
		assertEquals(itel, sortedlinkedPhone.getFirst());
	
	}

	@Test
	void testRemoveLastElement() {
//		fail("Not yet implemented");
		
		sortedlinkedPhone.add(samsumg);
		sortedlinkedPhone.add(itel);
		sortedlinkedPhone.add(huawei);
		assertEquals(samsumg, sortedlinkedPhone.getLast());
		sortedlinkedPhone.remove(samsumg, Phonecomparator);
		assertEquals(itel, sortedlinkedPhone.getLast());
		assertEquals(2, sortedlinkedPhone.getSize());
	}
	
	@Test
	void testUnsupportedOperationException() {
		try {
			sortedlinkedPhone.addToFront(itel);
			sortedlinkedPhone.addToEnd(pixel);
		}catch(UnsupportedOperationException e){
			assertTrue(e.getMessage(), true);
		}
	}

	@Test
	public void testIteratorNoSuchElementExceptionPreviousAndNext() {
		sortedlinkedPhone.add(samsumg);
		sortedlinkedPhone.add(itel);
		sortedlinkedPhone.add(huawei);
		ListIterator<Phone> iterator = sortedlinkedPhone.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(huawei, iterator.next() );
		assertEquals(itel, iterator.next());
		assertEquals(samsumg, iterator.next());
		
		assertEquals(false, iterator.hasNext());
		
		try {
			iterator.next();
			
		}catch(NoSuchElementException e){
			assertTrue(e.getMessage(), true);
		}
		
		assertEquals(true, iterator.hasPrevious());
		
		assertEquals(samsumg, iterator.previous());
		assertEquals(itel, iterator.previous());
		assertEquals(huawei, iterator.previous());
		assertEquals(false, iterator.hasPrevious());
		
		try {
			iterator.previous();
			
		}catch(NoSuchElementException e){
			assertTrue(e.getMessage(), true);
		}
	}
	
	
	@Test
	void testRetrieveLastElement() {
//		fail("Not yet implemented")
		sortedlinkedPhone.add(samsumg);
		sortedlinkedPhone.add(itel);
		sortedlinkedPhone.add(huawei);
		sortedlinkedPhone.retrieveFirstElement();
		assertEquals(itel, sortedlinkedPhone.getFirst());
		assertEquals(2, sortedlinkedPhone.getSize());
	}
//
	@Test
	void testToArrayList() {
//		fail("Not yet implemented");
		ArrayList<Phone> list;
		sortedlinkedPhone.add(samsumg);
		sortedlinkedPhone.add(itel);
		sortedlinkedPhone.add(huawei);
		list=sortedlinkedPhone.toArrayList();
		assertEquals(huawei, list.get(0));
		assertEquals(itel, list.get(1));
		assertEquals(samsumg, list.get(2));
	}
//	
	private class PhoneComparator implements Comparator<Phone>
	{

		@Override
		public int compare(Phone arg0, Phone arg1) {
			// Phone's brands by alphabetical order
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Phone{
		String brand;
		int year;
		
		public Phone(String brand, int year){
			this.brand = brand;
			this.year = year;
		}
		
		public String getBrand(){
			return brand;
		}
		
		public int getYear(){
			return year;
		}
		
		public String toString() {
			return (getBrand()+" "+getYear());
		}
	}

}