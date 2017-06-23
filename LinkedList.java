package project5;


import list.ListInterface;

public class LinkedList<T extends Comparable<T>> implements ListInterface<T>
{
    private static class Node<E>
    {
        private Node<E> next;
        private Node<E> previous;
        private E data;
        /**
         * creat new node with given data
         */
        public Node(E d)
        {
            data = d;
        }
        /**
         * set the node after this node
         */
        public void setNext(Node<E> n)
        {
            next = n;
        }
        /**
         * set the node before this
         */
        public void setPrevious(Node<E> n)
        {
            previous = n;
        }
        /**
         * set data 
         */
        public void setData(E d)
        {
            data = d;
        }
        /**
         * Get the next ndoe
         */
        public Node<E> next()
        {
            return next;
        }
        /**
         * get the node before this
         */
        public Node<E> previous()
        {
            return previous;
        }
        /**
         * get the data in the Node
         */
        public E getData()
        {
            return data;
        }
    }//end of Node class

    //fields
    private Node<T> head;
    private Node<T> tail;
    private int length;

    /**
     * creat new linkedlist object
     */
    public LinkedList()
    {
        init();
    }
    /**
     * initializes the object to have the head and tail nodes
     */
    private void init()
    {
        head = new LinkedList.Node<T>(null);
        tail = new LinkedList.Node<T>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        length = 0;
    }
    /**
     * add a elements to the end of the list
     * @param newEntry the element to add to end
     * @throws IllegalArgumentException if the entry is null
     */
    @Override
    public void add(T newEntry)
    {
        add(getLength(), newEntry);
    }
    /**
     * adds the object to the position in the list
     * @param index
     * @param obj
     * @throw IndexOutOfBoundException
     *              if index if less than zero or greater than length
     * @throw IllegalArgumentException
     *              if obj is null
     */
    @Override
    public void add(int index, T obj)
    {
        if (index < 0 || length < index)
        {
            throw new IndexOutOfBoundsException();
        }
        if (obj == null)
        {
            throw new IllegalArgumentException("Cannot add null "
                    + "objects to a list");
        }

        Node<T> nodeAfter;
        if(index == length)
        {
            nodeAfter = tail;
        }
        else 
        {
            nodeAfter = getNodeAtIndex(index);
        }

        Node<T> addition = new Node<T>(obj);
        addition.setPrevious(nodeAfter.previous());
        addition.setNext(nodeAfter);
        nodeAfter.previous().setNext(addition);
        nodeAfter.setPrevious(addition);
        length++;
    }

    /**
     * remove all element from the list
     */
    @Override
    public void clear()
    {
        init();
    }

    /**
     * check if the list contains the given object
     * @param obj
     * @return true if contains the object
     */
    @Override
    public boolean contains(T obj)
    {
        return lastIndexOf(obj) != -1;
    }

    /**
     * get the object at the given position
     * @param index where the object is located
     * @return the object at the given position
     * @throws indexOutOfBoundsException if there is no node 
     */
    @Override
    public T getEntry(int index)
    {
        return getNodeAtIndex(index).getData();
    }

    /**
     * get the number of elements in the list
     * @return the number of elements
     */
    @Override
    public int getLength()
    {
        return length;
    }

    /**
     * check if the array is empty
     * @return true if the array is empty
     */
    @Override
    public boolean isEmpty()
    {
        return length == 0;
    }
    /**
     * get the node at the index
     * @param index
     * @return node at index
     */
    private Node<T> getNodeAtIndex(int index)
    {
        if (index < 0 || getLength() <= index)
        {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head.next();
        for(int i = 0; i < index; i++)
        {
            current = current.next();
        }
        return current;
    }
    /**
     * get the last time the given object is in the list
     * @param obj
     * @return the last position of it. -1 if it is not in the list
     */
    public int lastIndexOf(T obj)
    {
        Node<T> current = tail.previous();
        for (int i = getLength()-1; i >= 0; i--)
        {
            if(current.getData().equals(obj))
            {
                return i;
            }
            current = current.previous();
        }
        return -1;
    }
    /**
     * remove the element at the specificed index from the list
     * @param index
     * @throw IndexOutofBoundsException 
     *          if there is not an element at the index
     * @return true if successful
     */
    @Override
    public T remove(int index)
    {
        Node<T> nodeToBeRemoved = getNodeAtIndex(index);
        nodeToBeRemoved.previous().setNext(nodeToBeRemoved.next());
        nodeToBeRemoved.next().setPrevious(nodeToBeRemoved.previous());
        length--;
        return nodeToBeRemoved.getData();
    }
    /**
     * removes the first object in the list that.equals(obj)
     * @param obj the object to remove
     * @return true if object is found and removed
     */
    public boolean remove(T obj)
    {
        Node<T> current = head.next();
        while (!current.equals(tail)) {
            if (current.getData().equals(obj)) {
                current.previous().setNext(current.next());
                current.next().setPrevious(current.previous());
                length--;
                return true;
            }
            current = current.next();
        }
        return false;
    }
    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            Node<T> currNode = head.next();
            while (currNode != tail) {
                T element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next != tail) {
                    builder.append(", ");
                }  
                currNode = currNode.next();
            }
        }
        builder.append("}");
        return builder.toString();
    }
    /**
     * replace the specific index with new entry
     * @throws IndexOutOfBoundsException if index is less than 0 or 
     *                  greater than length -1
     * @throws IllegalArgumentException if newEntry is null
     * @return the replaced entry
     * @param index index need to be replaced
     * @param newEntry entry will replace the index's entry
     */
    @Override
    public T replace(int index, T newEntry)
    {
        if (index < 0 || index > length -1 )
        {
            throw new IndexOutOfBoundsException();
        }
        if (newEntry == null)
        {
            throw new IllegalArgumentException();
        }
        Node<T> temp = this.getNodeAtIndex(index);
        T replacedEntry = temp.getData();
        temp.setData(newEntry);
        return replacedEntry;
    }
    /**
     * convert list to object array
     * @return object array
     */
    @Override
    public Object[] toArray()
    {
        Object[] array = new Object[length];
        for (int i = 0; i < length; i++)
        {
            array[i] = this.getEntry(i);
        }
        return array;
    }
    /**
     * swap the two node by index 
     */
    public void swap(int index1, int index2)
    {
        T entry1 = this.getEntry(index1);
        T entry2 = this.getEntry(index2);
        this.replace(index1, entry2);
        this.replace(index2, entry1);
    }
    /**
     * insetionSort
     */
    public void insertSort()
    {
        if (length > 1)
        {
            for (int i = 1; i < length; i++)
            {
                for(int j = i ; j > 0 ; j--)
                {
                    if (this.getEntry(j).compareTo(this.getEntry(j-1)) < 0)
                    {
                        swap(j, j-1);
                    }
                }
            }
        }
    }

}
