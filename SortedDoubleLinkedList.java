import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T> {

	public Comparator<T> listComparator;
	
	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * @param compareableObject
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		//super();
		listComparator=compareableObject;
	}
	
	/**
	 * Inserts the specified element at the correct position in the sort
	 * @param data the data to be added to the list
	 */
	public void add(T data) {
		Node current = new Node(data); //node that holds the data element to be added
		
		if (size == 0) //if list is empty, set head and tail to current
		{
			head = tail = current;	
		}
		else if (listComparator.compare((T)head.data, data) > 0) //if data is to be added at the beginning of the list
		{
			head.prev = current;
			current.next = head;
			head = current;
		} 
		else if (listComparator.compare((T)tail.data, data) < 0) //if data is to be added at the end of the list
		{
			tail.next = current;
			current.prev = tail;
			tail = current;
		} 
		else //data is to be added is in between two nodes
		{
			Node tempNode = head;
			
			while (tempNode != null) //iterate through the list
			{
				if (listComparator.compare((T) tempNode.data, data) < 0 && listComparator.compare((T) tempNode.next.data, data)>0) //data placement was found
				{
					Node temp = tempNode.next;
					tempNode.next.prev = tempNode.next = current;
					current.next = temp;
					current.prev = tempNode;
				}
				tempNode = tempNode.next; //move to the next node to check
			} //end iteration
		}
		
        size++;
				
	}
	
	/**
	 * This operation is invalid for a sorted list. 
	 * An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 */
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This operation is invalid for a sorted list.
	 */
	public void addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Implements the iterator by calling the super class iterator method.
	 */
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/**
	 * Implements the remove operation by calling the super class remove method.
	 */
	public BasicDoubleLinkedList.Node remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}
}