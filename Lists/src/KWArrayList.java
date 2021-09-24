/*<listing chapter="2" section="4" sequence="1">*/

import java.util.Arrays;
import java.util.AbstractList;
import java.util.Collection;

/**
 *  This class implements some of the methods of the Java
 *  ArrayList class.
 * @param <E> The element type
 *  @author Koffman and Wolfgang
 */
public class KWArrayList<E>
        /*<exercise chapter="2" section="10" type="programming" number="1">*/
        extends AbstractList<E>
        /*</exercise>*/
{
    // Data Fields

    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 10;
    /** The underlying data array */
    private E[] theData;
    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;

    /**
     * Construct an empty KWArrayList with the default
     * initial capacity
     */
    @SuppressWarnings({"unchecked"})
    public KWArrayList() {
        capacity = INITIAL_CAPACITY;
        theData = (E[]) new Object[capacity];
    }

    /*<exercise chapter="2" section="10" type="programming" number="2">*/
    /**
     * Construct a KWLinked list that contains the elements of the specified collection.
     * @param c The Collection
     */
    public KWArrayList(Collection<? extends E> c) {
        this.addAll(c);
    }
    /*</exercise>*/

    /*<exercise chapter="2" section="4" type="programming" number="2"*>*/
    /**
     * Construct an empty KWArrayList with a specified initial capacity
     * @param capacity The initial capacity
     */
    @SuppressWarnings("unchecked")
    public KWArrayList(int capacity) {
        this.capacity = capacity;
        theData = (E[]) new Object[capacity];
    }
    /*</exercise>*/

    /**
     * Add an entry to the data inserting it at the end of the list.
     * @param anEntry The value to be added to the list.
     * @return true since the add always succeeds
     */
    @Override
    public boolean add(E anEntry) {
        if (size == capacity) {
            reallocate();
        }
        theData[size] = anEntry;
        size++;
        return true;
    }

    /** Adds a new element at the specified index to this list.
     *  Later elements, if any, are moved to the right.
     *  @param index Where to add this element; must be >= 0 and <= size
     *  @param anEntry The new value to add
     *  @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void add(int index, E anEntry)
    {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("" + index);
        }
        if(size == capacity){
            reallocate();
        }
        // Shift data in elements from index to size - 1
        for(int i = size; i > index; i--){
            theData[i] = theData[i - 1];
        }
        // Insert the new item
        theData[index] = anEntry;
        size++;
    }

    /**
     * Get a value in the array based on its index.
     * @param index - The index of the item desired
     * @return The contents of the array at that index
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return theData[index];
    }

    /**
     * Set the value in the array based on its index.
     * @param index - The index of the item desired
     * @param newValue - The new value to store at this position
     * @return The old value at this position
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    @Override
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldValue = theData[index];
        theData[index] = newValue;
        return oldValue;
    }

    /**
     * Remove an entry based on its index
     * @param index - The index of the entry to be removed
     * @return The value removed
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E returnValue = theData[index];
        for (int i = index + 1; i < size; i++) {
            theData[i - 1] = theData[i];
        }
        size--;
        return returnValue;
    }

    /**
     * Allocate a new array to hold the directory
     */
    private void reallocate() {
        capacity = 2 * capacity;
        theData = Arrays.copyOf(theData, capacity);
    }

    /**
     * Get the current size of the array
     * @return The current size of the array
     */
    @Override
    public int size() {
        return size;
    }

    /*<exercise chapter="2" section="4" type="programming" number="1">*/
    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element
     * @param item The object to search for
     * @return The index of the first occurrence of the specified item
     *         or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (theData[i] == null && item == null) {
                return i;
            }
            if (theData[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    /*</exercise>*/
}
/*</listing>*/
