/**
 * 
 */
package project5;

import student.TestCase;

/**
 * @author Yiyang Fan
 * @version Jun 22, 2017
 */
public class LinkedListTest extends TestCase
{
    LinkedList<String> list;

    /**
     * test setup
     */
    public void setUp()
    {
        list = new LinkedList<String>();
        list.add("1");
        list.add("2");
    }
    /**
     * test add newEntry method
     */
    public void testAdd()
    {
        list.add("new");
        assertEquals("{1, 2, new}", list.toString());
        //test exception
        Exception e = null;
        try {
            list.add(null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }
    /**
     * test add to index
     */
    public void testAddIndex()
    {
        list.add(2, "new");
        assertEquals("{1, 2, new}", list.toString());
        //test IllegalArgumentException
        Exception e = null;
        try {
            list.add(3, null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
        e = null;
        try {
            list.add(50, "new");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    /**
     * test clear method
     */
    public void testClear()
    {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }
    /**
     * test contains method
     */
    public void testContains()
    {
        assertTrue(list.contains("1"));
        assertFalse(list.contains("new"));
    }
    /**
     * test getEntry with index
     */
    public void testGetEntry()
    {
        assertEquals("1", list.getEntry(0));
        //test exception
        Exception e = null;
        try {
            list.getEntry(3);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    /**
     * testt getLength
     */
    public void testGetLength()
    {
        assertEquals(2, list.getLength());
    }
    /**
     * test isempty method
     */
    public void testIsEmpty()
    {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }
    /**
     * test getLastIndexof
     */
    public void testLastIndexOf()
    {
        list.add("2");
        list.add("1");
        assertEquals(3, list.lastIndexOf("1"));
        assertEquals(-1, list.lastIndexOf("new"));
    }
    /**
     * test remove index method
     */
    public void testRemoveIndex()
    {
        assertEquals("2", list.remove(1));
      //test exception
        Exception e = null;
        try {
            list.remove(50);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    /**
     * test remove first object in the list that equals.object
     */
    public void testRemoveObj()
    {
        assertTrue(list.remove("1"));
        assertFalse(list.remove("new"));
    }
    /**
     * test toString method
     */
    public void testToString()
    {
        assertEquals("{1, 2}", list.toString());
    }
    /**
     * test replace method
     */
    public void testReplace()
    {
        assertEquals("1", list.replace(0, "0"));
       //test IllegalArgumentException
        Exception e = null;
        try {
            list.replace(0, null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
        //test IndexOutOfBoundsException
        e = null;
        try {
            list.replace(50, "new");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }
    /**
     * test swap method
     */
    public void testSwap()
    {
        list.add("new");
        list.swap(0, 2);
        assertEquals("{new, 2, 1}", list.toString());
    }
    /**
     * test toarray
     */
    public void testToArray()
    {
        list.clear();
        list.add("A");
        list.add("B");
        Object[] array = list.toArray();
        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
    }
    /**
     * test insertion sort
     */
    public void testInsertSort()
    {
        list.clear();
        list.add("B");
        list.add("A");
        list.add("C");
        list.insertSort();
        assertEquals("{A, B, C}", list.toString());
    }
}
