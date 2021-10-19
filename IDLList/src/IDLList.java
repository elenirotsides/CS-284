/**
 * 
 * @author Eleni Rotsides
 * @section A
 * @pledge I pledge my honor that I have abided by the Stevens honor system.
 */

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Indexed Double Linked List Class
 * @author Eleni Rotsides
 *
 * @param <E>
 */
public class IDLList<E> {
	
	@SuppressWarnings("hiding")
	/**
	 * Inner Node Class
	 * @author Eleni Rotsides
	 *
	 * @param <E>
	 */
	private class Node<E>{
		
		// Data fields
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		// Constructors
		private Node(E elem) {
			data = elem;
			next = null;
			prev = null;
		}
		
		private Node(E elem, Node<E> prev, Node<E> next) {
			data = elem;
			this.prev = prev;
			this.next = next;
			
		}
	}
	
	// Data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	// Constructor
	public IDLList() {
		//require index maintenance
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	
	// Methods start below this point 
	
	/**
	 * Adds elem at the head
	 * @param elem
	 * @throws IllegalStateException if there is no such element
	 * @return true
	 */
	public boolean add(E elem) {
		//require index maintenance
		if(elem == null) {
			throw new IllegalArgumentException("elem cannot be null");
		}
		
		if(head == null) {
			// if there isn't a head, create a new node and add elem
			head = new Node<E>(elem, null, null);
			tail = head;
			indices.add(0, head);
			size++;
		} else if(size() == 1){
			// if there's only one element in the list
			Node<E> newHead = new Node<E>(elem, null, head);
			head = newHead;
			head.next = tail;
			tail.prev = head;
			indices.add(0, newHead);
			size++;
		} else {
			// all other cases
			Node<E> newHead = new Node<E>(elem);
			Node<E> nodeAtOldHead = indices.get(0);
			newHead.prev = null;
			nodeAtOldHead.prev = newHead;
			newHead.next = nodeAtOldHead;
			indices.add(0, newHead);
			head = newHead;
			size++;
		}
		return true;
	}
	
	/**
	 * Adds elem at position index 
	 * @param index
	 * @param elem
	 * @throws IllegalStateException if there is no such element
	 * @return true
	 */
	public boolean add(int index, E elem) {
		//require index maintenance
		if(elem == null) {
			throw new IllegalArgumentException("elem cannot be null");
		}

		if(index == 0 && head == null) {
			// if index is 0 but there is no list
			head = new Node<E>(elem, null, null);
			tail = head;
			indices.add(index, head);
			size++;
		} else if(index < 0) {
			// if the index is negative
			throw new IllegalStateException("Cannot add at a negative index");
		} else if(index == 0 && size() == 1) {
			// if you want to insert at head when there's only 1 element in list
			Node<E> newHead = new Node<E>(elem, null, indices.get(index));
			tail = head;
			head = newHead;
			head.next = tail;
			tail.prev = head;
			indices.add(0, newHead);
			size++;
		} else if(index == size() - 1 && size() > 1) {
			// if you want to insert at index where tail is
			Node<E> elemToInsert = new Node<E>(elem, indices.get(index).prev, indices.get(index));
			indices.add(index, elemToInsert);
			size++;
			tail = indices.get(index).next;
		} else if(index >= size()) {
			// if you want to insert at an index greater than or equal to size
			throw new IllegalStateException("Cannot add at a nonexistent index");
		} else if(index == 0) {
			// if you want to insert at head
			Node<E> newHead = new Node<E>(elem, null, indices.get(index));
			indices.add(0, newHead);
			head = newHead;
			size++;
		}else {
			Node<E> elemToInsert = new Node<E>(elem, indices.get(index).prev, indices.get(index));
			indices.add(index, elemToInsert);
			size++;
		}
		return true;
	}
	
	/**
	 * Adds elem as the new last element of the list
	 * @param elem
	 * @throws IllegalStateException if there is no such element
	 * @return true
	 */
	public boolean append(E elem) {
		//require index maintenance
		if(elem == null) {
			throw new IllegalArgumentException("elem cannot be null");
		}
		
		if(tail == null) {
			// if tail is null, create node and then add elem to tail
			head = new Node<E>(elem, null, null);
			tail = head;
			indices.add(0, head);
			size++;
		} else {
			// other cases
			Node<E> newTail = new Node<E>(elem, indices.get(size() - 1), null);
			tail = newTail;
			indices.add(newTail);
			size++;
		}
		return true;
	}
	
	/**
	 * Returns the object at position index from the head
	 * @throws IllegalStateException if there is no such element
	 * @param index
	 * @return E
	 */
	public E get(int index) {
		if(index < 0) {
			throw new IllegalStateException("Cannot get data at a negative index.");
		}
		if(index > size() - 1) {
			throw new IllegalStateException("Cannot get data at a nonexistent index.");
		}
		return indices.get(index).data;
	}
	
	/**
	 * Returns the object at the head
	 * @throws IllegalStateException if there is no such element
	 * @return E
	 */
	public E getHead() {
		if(head == null) {
			throw new IllegalStateException("Head does not exist.");
		}
		return head.data;
	}
	
	/**
	 * Returns the object at the tail
	 * @throws IllegalStateException if there is no such element
	 * @return E
	 */
	public E getLast() {
		if(tail == null) {
			throw new IllegalStateException("Tail does not exist.");
		}
		return tail.data;
	}
	
	/**
	 * Returns the list size
	 * @return int
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes and returns the element at the head.
	 * @throws IllegalStateException if there is no such element
	 * @return E
	 */
	public E remove() {
		//require index maintenance
		if(head == null) {
			throw new IllegalStateException("Element does not exist.");
		} else if(size() == 2) {
			// if only head and tail exist in list
			E copyHead = head.data;
			head = tail;
			tail = head;
			indices.remove(0);
			size--;
			return copyHead;
		} else if (size() == 1) {
			// if only head exists in list
			E copyHead = head.data;
			head = null;
			tail = null;
			indices.remove(0);
			size--;
			return copyHead;
		} else {
			// other cases
			E copyHead = head.data;
			head = head.next;
			indices.remove(0);
			size--;
			return copyHead;
		}
	}
	
	/**
	 * Removes and returns the element at the tail 
	 * @throws IllegalStateException if there is no such element
	 * @return E
	 */
	public E removeLast() {
		//require index maintenance
		if(tail == null) {
			throw new IllegalStateException("Element you are trying to remove does not exist.");
		} 
		
		E copyTail = tail.data;
		
		if(size() > 2) {
			// if list contains more than 2 elements
			tail = tail.prev;
			indices.remove(size() - 1);
			size--;
		} else if(size() == 2) {
			// if list only contains head and tail
			tail = head;
			head = tail;
			indices.remove(size() - 1);
			size--;
		} else {
			// if list only has one element
			head = null;
			tail = null;
			indices.remove(size() - 1);
			size--;
		}
		
		return copyTail;
	}
	
	/**
	 * Removes and returns the element at the index
	 * @throws IllegalStateException if there is no such element
	 * @param index
	 * @return E
	 */
	public E removeAt(int index) {
		//require index maintenance
		if(index < 0) {
			throw new IllegalStateException("Cannot remove at a negative index");
		}
		if(index >= size()) {
			throw new IllegalStateException("Cannot remove at a non existent index");
		}
		
		if(index == 0) {
			// removing at head
			return remove();
		}
		else if (index == size() - 1) {
			// removing at tail
			return removeLast();
		}
		else {
			// all other cases
			E elemToRemove = indices.get(index).data;
			Node<E> nodeBeforeIndex = indices.get(index).prev;
			Node<E> nodeAfterIndex = indices.get(index).next;
			nodeBeforeIndex.next = nodeAfterIndex;
			nodeAfterIndex.prev = nodeBeforeIndex;
			indices.remove(index);
			size--;
			return elemToRemove;
		}
	}
	
	/**
	 * Removes the first occurrence of elem in the list and returns true. Returns false if elem is not in the list.
	 * @param elem
	 * @return boolean
	 */
	public boolean remove(E elem) {
		// iterate over entire list until elem is found, otherwise return false
		for(int i = 0; i < size(); i++) {
			if(indices.get(i).data == elem) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the string representation of the double linked list.
	 * @throws IllegalStateException if size is 0
	 * @return String
	 */
	@Override
	public String toString() {
		if(size == 0) {
			throw new IllegalStateException("Cannot make a string since the element does not exist.");
		}
		StringJoiner dllToString = new StringJoiner(", ", "[", "]");
		// iterate over entire list and stringify each element
		for(int i = 0; i < size(); i++) {
			dllToString.add(indices.get(i).data.toString());
		}
		return dllToString.toString();
	}
}
