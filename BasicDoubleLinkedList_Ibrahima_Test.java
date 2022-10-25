import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BasicDoubleLinkedList_Ibrahima_Test {

	BasicDoubleLinkedList<Phone> linkedPhone;
	PhoneComparator Phonecomparator;
	
	public Phone itel=new Phone("Itel", 2010);
	public Phone samsumg=new Phone("Samsumg", 2010);
	public Phone huawei=new Phone("Huawei", 2010);
	public Phone pixel=new Phone("Pixel", 2010);
	
	public ArrayList<Phone> fill = new ArrayList<Phone>();
	
	@BeforeEach
	void setUp() throws Exception {
		linkedPhone= new BasicDoubleLinkedList<Phone>();
		linkedPhone.addToEnd(itel);
		linkedPhone.addToEnd(samsumg);
		Phonecomparator= new PhoneComparator();
	}

	@AfterEach
	void tearDown() throws Exception {
		linkedPhone = null;
	}

	@Test
	void testGetSize() {
//		fail("Not yet implemented");
		assertEquals(2,linkedPhone.getSize());
	}

	@Test
	void testAddToEnd() {
//		fail("Not yet implemented");
		assertEquals(samsumg,linkedPhone.getLast());
		linkedPhone.addToEnd(pixel);
		assertEquals(pixel,linkedPhone.getLast());
	}

	@Test
	void testAddToFront() {
//		fail("Not yet implemented");
		assertEquals(itel,linkedPhone.getFirst());
		linkedPhone.addToFront(huawei);
		assertEquals(huawei,linkedPhone.getFirst());
	}

	@Test
	void testGetFirst() {
//		fail("Not yet implemented");
		assertEquals(itel,linkedPhone.getFirst());
	}

	@Test
	void testGetLast() {
//		fail("Not yet implemented");
		assertEquals(samsumg,linkedPhone.getLast());
	}

	@Test
	void testIteratorSuccessfulNext() {
		linkedPhone.addToEnd(huawei);
		linkedPhone.addToFront(pixel);
		ListIterator<Phone> iterator = linkedPhone.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(pixel, iterator.next());
		assertEquals(itel, iterator.next());
		assertEquals(samsumg, iterator.next());
		assertEquals(huawei, iterator.next());
		assertEquals(false, iterator.hasNext());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedPhone.addToEnd(huawei);
		linkedPhone.addToFront(pixel);
		ListIterator<Phone> iterator = linkedPhone.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(pixel, iterator.next());
		assertEquals(itel, iterator.next());
		assertEquals(samsumg, iterator.next());
		assertEquals(huawei, iterator.next());
		assertEquals(false, iterator.hasNext());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(huawei, iterator.previous());
		assertEquals(samsumg, iterator.previous());
		assertEquals(itel, iterator.previous());
		assertEquals(pixel, iterator.previous());
		assertEquals(false, iterator.hasPrevious());
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		ListIterator<Phone> iterator = linkedPhone.iterator();
		assertEquals(itel, iterator.next());
		assertEquals(samsumg, iterator.next());
		assertEquals(false, iterator.hasNext());
		
		try {
			iterator.next();
		}catch(NoSuchElementException e){
			assertTrue(e.getMessage(), true);
		}
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		ListIterator<Phone> iterator = linkedPhone.iterator();
		assertEquals(itel, iterator.next());
		assertEquals(samsumg, iterator.next());
		assertEquals(false, iterator.hasNext());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(samsumg, iterator.previous());
		assertEquals(itel, iterator.previous());
		assertEquals(false, iterator.hasPrevious());
		
		try {
			iterator.previous();
		}catch(NoSuchElementException e){
			assertTrue(e.getMessage(), true);
		}
	}

	@Test
	void testRemove() {
//		fail("Not yet implemented");
		assertEquals(itel, linkedPhone.getFirst());
		assertEquals(samsumg, linkedPhone.getLast());
		assertEquals(2, linkedPhone.getSize());
		linkedPhone.remove(itel, Phonecomparator);
		assertEquals(samsumg, linkedPhone.getFirst());
		assertEquals(1, linkedPhone.getSize());
		
		linkedPhone.addToEnd(huawei);
		assertEquals(huawei, linkedPhone.getLast());
	}

	@Test
	void testRetrieveFirstElement() {
//		fail("Not yet implemented");
		assertEquals(itel, linkedPhone.getFirst());
		assertEquals(samsumg, linkedPhone.getLast());
		assertEquals(2, linkedPhone.getSize());
		linkedPhone.addToEnd(huawei);
		assertEquals(3, linkedPhone.getSize());
		linkedPhone.retrieveFirstElement();
		assertEquals(samsumg, linkedPhone.getFirst());
		assertEquals(huawei, linkedPhone.getLast());
		assertEquals(2, linkedPhone.getSize());
	}

	@Test
	void testRetrieveLastElement() {
//		fail("Not yet implemented");
		assertEquals(itel, linkedPhone.getFirst());
		assertEquals(samsumg, linkedPhone.getLast());
		assertEquals(2, linkedPhone.getSize());
		linkedPhone.addToEnd(huawei);
		assertEquals(3, linkedPhone.getSize());
		linkedPhone.retrieveLastElement();
		assertEquals(itel, linkedPhone.getFirst());
		assertEquals(samsumg, linkedPhone.getLast());
		assertEquals(2, linkedPhone.getSize());
		
	}

	@Test
	void testToArrayList() {
//		fail("Not yet implemented");
		ArrayList<Phone> list;
		linkedPhone.addToFront(pixel);
		//linkedCar.addToEnd(d);
		list = linkedPhone.toArrayList();
		assertEquals(pixel,list.get(0));
		assertEquals(itel,list.get(1));
		assertEquals(samsumg,list.get(2));
	}

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