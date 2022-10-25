import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
 
public class BasicDoubleLinkedList<T>  implements Iterable<T> {

	protected int size;
	protected Node<T> head;
	protected Node<T> tail;
	
	/**
	 * Constructor to set to initialize head, tail and size to null, null and 0
	 */
	BasicDoubleLinkedList(){
		size=0;
		head=null;
		tail=null;
	}
	
	//A generic inner class Node
	class Node<T>{
		T data;
		Node<T> prev; 
		Node<T> next;
		
		public Node(T dataNode) {
			data=dataNode;
		}
	}

	/**
	 * 
	 * @Iburahima Barry
	 
	 *A generic inner class named DoubleLinkedListIterator that implements java’s ListIterator interface 
	 *(for the iterator method).This class only implements the next(), hasNext(), previous() and hasPrevious() 
	 *methods of the ListIterator interface.
	 *The rest of the methods should throw the UnsupportedOperationException
	 */
	public class DoubleLinkedListIterator implements ListIterator<T>{

		private Node currentNext, currentPrevious;
		
		/**
		 * Constructor to initialize the current pointer to the head of the BasicDoubleLinkedList.
		 */
		public DoubleLinkedListIterator()
		{
			currentNext = currentPrevious = new Node(null);
			
			currentNext.next = head;
		}
		
		@Override
		public boolean hasNext() {
			return currentNext.next != null;
		}

		@Override
		public T next() throws NoSuchElementException{
			if (!hasNext())
				throw new NoSuchElementException();
			else {
				currentNext = currentNext.next;
				currentPrevious.prev = currentNext;
				return (T) currentNext.data;
			}
			
		}

		@Override
		public boolean hasPrevious() {
			return currentPrevious.prev != null;
		}

		@Override
		public T previous() throws NoSuchElementException {
			if (!hasPrevious())
				throw new NoSuchElementException();
			else {
				currentPrevious = currentPrevious.prev;
				currentNext.next = currentPrevious;
				return (T) currentPrevious.data;
			}
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) throws UnsupportedOperationException {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) throws UnsupportedOperationException {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
	}

	/**
	 * 
	 * @return the number of nodes in the list.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Adds an element to the end of the list and updated the size of the list
	 * @param data
	 */
	public void addToEnd(T data) {
		Node<T> current = new Node<T>(data);
		if(size==0) {
			head=tail=current;
		}
		else {
			tail.next=current;
			current.prev=tail;
			tail=current;
		}
		size++;
	}
	
	/**
	 * Adds element to the front of the list and updated the size of the list
	 * @param data
	 */
	public void addToFront(T data) {
		Node<T> current= new Node<T>(data);
		if(size==0) {
			head=tail=current;
		}
		else {
			head.prev=current;
			current.next=head;
			head=current;
		}
		size++;
	}
	
	/**
	 * Returns but does not remove the first element from the list.
	 * @return first data element or null if no elements in the list.
	 */
	public T getFirst() {
		if(head==null||tail==null) {
			return null;
		}
		else
			return head.data;
	}
	
	/**
	 * Returns but does not remove the last element from the list.
	 * @return last data element or null if no elements in the list.
	 */
	public T getLast() {
		if(head==null || tail==null) {
			return null;
		}
		else
			return tail.data;
	}
	
	/**
	 * This method returns an object of the DoubleLinkedListIterator. 
	 * (the inner class that implements java's ListIterator interface)
	 */
	@Override
	public ListIterator<T> iterator() {
		// TODO Auto-generated method stub
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * Removes the first instance of the targetData from the list.
	 * @param targetData
	 * @param comparator
	 * @return a node containing the targetData or null
	 */
	public BasicDoubleLinkedList<T>.Node<T> remove(T targetData, Comparator<T> comparator){
		Node<T>current = head;
		Node<T> temp= new Node(null); //node that would hold the removed element data
		
		while (current != null) //iterate through the list
		{
			if (comparator.compare(targetData, current.data) == 0) // to determine the equality of data elements
			{
				temp=new Node(targetData);
				
				if (current == head) //if head contains the element to be removed, remove head
				{
					
					head = head.next;
					head.prev=null;
					size--;
				} 
				else if (current == tail) //if tail contains the element to be removed, remove tail
				{
					
					tail = tail.prev;
					tail.next=null;
					size--;
				} 
				else //remove the node that contains the data element to be removed
				{
					
					current.prev.next = current.next;
					current.next.prev=current.prev;
					size--;
				}
			}
			current = current.next;

		} //end iteration
		return temp;
	}

	/**
	 * Removes and returns the first element from the list
	 * @return the first data element or null if list is empty
	 */
	public T retrieveFirstElement() {
		
		if (head==null)
			return null;
		else {
			T currentData=head.data;
			head=head.next;
			size--;
			return currentData;
		}
	}
	
	/**
	 * Removes and returns the last element from the list
	 * @return the last data element or null if list is empty
	 */
	public T retrieveLastElement() {
		if(head==null && tail==null)
			return null;
	
		else {
			T currentData=tail.data;
			
			tail=tail.prev;
			tail.next=null;
			size--;
			return currentData;
		}
	}
	
	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>(getSize());
		Node<T> current = head;

		while (current != null) {
			list.add(current.data);
			current = current.next;
		}

		return list;
	}

}