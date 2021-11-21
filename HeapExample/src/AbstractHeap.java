import java.util.ArrayList;

public abstract class AbstractHeap<E> {
	
	private final ArrayList<E> theData;
	protected abstract int compare(E e1, E e2);
	
	public AbstractHeap() {
		theData = new ArrayList<>(10);
	}
	
	public boolean offer(E item) {
		theData.add(item);
		
		int child = theData.size() - 1;
		int parent = (child - 1) / 2;
		
		while(parent >= 0 && compare(theData.get(parent), theData.get(child)) > 0) {
			swap(parent, child);
			child = parent;
			parent = (child- 1) / 2;
		}
		
		return true;
	}
	
	public E poll() {
		if(isEmpty()) {
			return null;
		}
		
		E result = theData.get(0);
		if(theData.size() == 1) {
			theData.remove(0);
			return result;
		}
		
		theData.set(0, theData.remove(theData.size() - 1));
		
		int parent = 0;
		while(true) {
			int leftChild = 2 * parent + 1;
			
			if(leftChild >= theData.size()) {
				// every heap is a complete binary tree, so if there is no left child, there is no right child
				break;
			}
			int rightChild = leftChild + 1;
			int minChild = leftChild;
			
			if(rightChild < theData.size() 
					&& compare(theData.get(leftChild), theData.get(rightChild)) > 0) {
				minChild = rightChild;
			} 
			
			if(compare(theData.get(parent), theData.get(minChild)) > 0) {
				swap(parent, minChild);
				parent = minChild;
			} else {
				break;
			}
		}
		return result;
	}
	
	private void swap(int a, int b) {
		E temp = theData.get(a);
		theData.set(a, theData.get(b));
		theData.set(b, temp);
	}
	
	private boolean isEmpty() {
		return theData.size() == 0;
	}
	
	public int size() {
        return theData.size();
    }
}
