/*<listing chapter="6" section="6">*/

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * The KWPriorityQueue implements the Queue interface by building a heap in an
 * ArrayList. The heap is structured so that the &quot;smallest&quot; item is at the top.
 *
 * @param <E> The element type
 * @author Koffman and Wolfgang
 */
public class KWPriorityQueue<E> extends AbstractQueue<E>
        implements Queue<E> {

    // Data Fields
    /**
     * The ArrayList to hold the data.
     */
    private final ArrayList<E> theData;
    /**
     * A reference to a Comparator object.
     */
    private final Comparator<E> comp;

    // Methods
    // Constructor
    @SuppressWarnings("unchecked")
    public KWPriorityQueue() {
        theData = new ArrayList<>(10);
        comp = (left, right) -> ((Comparable<E>) left).compareTo(right);
    }

    /**
     * Creates a heap-based priority queue with the specified initial capacity
     * that orders its elements according to the specified comp.
     *
     * @param comp The comp used to order this priority queue
     * @throws IllegalArgumentException if cap is less than 1
     */
    public KWPriorityQueue(Comparator<E> comp) {
        theData = new ArrayList<>(10);
        this.comp = comp;
    }

    /**
     * Insert an item into the priority queue.
     *
     * pre: The ArrayList theData is in heap order.
     * post: The item is in the priority queue and theData is in heap order.
     * @param item The item to be inserted
     * @throws NullPointerException if the item to be inserted is null.
     */
    @Override
    public boolean offer(E item) {
        // Add the item to the heap.
        theData.add(item);
        // child is newly inserted item.
        int child = theData.size() - 1;
        int parent = (child - 1) / 2; // Find child�s parent.
        // Reheap
        while (parent >= 0 && 
                comp.compare(theData.get(parent), theData.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return true;
    }

    /**
     * Remove an item from the priority queue
     *
     * pre: The ArrayList theData is in heap order.
     * post: Removed smallest item, theData is in heap order.
     * @return The item with the smallest priority value or null if empty.
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // Save the top of the heap.
        E result = theData.get(0);
        // If only one item then remove it.
        if (theData.size() == 1) {
            theData.remove(0);
            return result;
        }
        // Remove the last item from the ArrayList and place it into
        // the first position.
        theData.set(0, theData.remove(theData.size() - 1));
        // The parent starts at the top.
        int parent = 0;
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= theData.size()) {
                break; // Out of heap.
            }
            int rightChild = leftChild + 1;
            int minChild = leftChild; // Assume leftChild is smaller.
            // See whether rightChild is smaller.
            if (rightChild < theData.size()
                    && comp.compare(theData.get(leftChild), theData.get(rightChild)) > 0) {
                minChild = rightChild;
            }
            // assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (comp.compare(theData.get(parent), theData.get(minChild)) > 0) {
                swap(parent, minChild);
                parent = minChild;
            } else { // Heap property is restored.
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return theData.iterator();
    }

    /*<exercise chapter="6" section="6" type="programming" number="1">*/
    public void swap(int a, int b) {
        E temp = theData.get(a);
        theData.set(a, theData.get(b));
        theData.set(b, temp);
    }

    @Override
    public E peek() {
        return theData.get(0);
    }

    @Override
    // Code is in AbstractQueue
    public E remove() {
        E item = poll();
        if (item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
        
    @Override
    public int size() {
        return theData.size();
    }
    /*</exercise>*/
}
/*</listing>*/
