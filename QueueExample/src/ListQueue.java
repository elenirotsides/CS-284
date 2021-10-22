import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;


public class ListQueue<E> extends AbstractQueue<E> implements Queue<E>{
	private static class Node<E>{
		
		private E data;
		private Node<E> next;
		
		private Node(E dataItem) {
			data  = dataItem;
			next = null;
		}
		private Node(E dataItem, Node<E> nodeRef) {
			data = dataItem;
			next = nodeRef;
		} 
	}//Node Class Ends
	
	private Node<E> front;
	private Node<E> rear;
	private int size;
	
	@Override
	public boolean offer(E item) {
		if(item ==null)
			throw new IllegalArgumentException();
		if(front==null) {
			rear = new Node<>(item);
			front = rear;
		}else {
			rear.next = new Node<>(item);
			rear = rear.next;
		}
		size++;
		return true;
	}
	
	@Override
	public E peek() {
		if(size==0)
			return null;
		else
			return front.data;
	}
	
	@Override
	public E poll() {
		E item = peek();
		if(item == null)
			return null;
		if(size == 1) {
			front = null;
			rear = null;
		}else {
			front = front.next;
		}
		size--;
		return item;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
