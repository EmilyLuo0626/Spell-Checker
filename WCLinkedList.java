/*
 * This class stores all the methods in LinkedList, use the simple sort method
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WCLinkedList implements Iterable<WordCount>{
    protected Node head;                                    //the start of the list
    private Node tail;                                      //the end of the list
    private int size;                                       //the size of the list


    /*
     * default constructor
     */
    public WCLinkedList(){
        size=0;
        head = null;
        tail = null;
    }

    /*
     * This is the inner class of WCLinkedList
     */
    protected static class Node{
        WordCount element;
        Node next;
        Node prev;

        //default constructor
        public Node(){
            element = null;
            next = null;
            prev = null;
        }

        //This constructor set the next, previous Node and the String element
        public Node(String s, Node n, Node p){
            element = new WordCount(s);
            next = n;
            prev = p;
        }
    }

    /*
     * This method checks if the list is empty
     *
     * @return true if the size is 0, false otherwise
     */
    public boolean isEmpty(){
        return size==0;
    }

    /*
     * This method checks if the given String is contained in the LinkedList
     * If the String is found, add one to the count
     *
     * @param String that wanted to search
     * @return true if the String is contained, false otherwise
     */
    public boolean contains(String data){
        Node tmp = head;
        while(tmp != null){
            if(tmp.element.getWord().equals(data)) {
                tmp.element.addCount();
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    /*
     * This method returns the size of the list
     *
     * @return the size of the list
     */
    public int size(){
        return size;
    }

    /*
     * This method returns a string of all the information in the list
     *
     * @return the string of all data
     */
    public String toString(){
        String re = null;
        Node tmp = head;
        while(tmp != null) {
            re = re + " " + tmp.element.toString();
        }
        return re;
    }

    /*
     * This method add the string to the end of the list
     *
     * @param the String that wanted to be added
     */
    public void addLast(String data){
        tail = new Node(data, null, tail);
        Node second = tail.prev;
        if(second!=null)
            second.next = tail;
        else
            head = tail;
        size++;
    }

    /*
     * This method swap the WordCount in the Node with the one in front of it
     *
     * @param the Node that want to be swapped with the previous one
     */
    protected void swap(Node n){
        WordCount tmp = n.element;
        if(n.prev!=null) {
            n.element = n.prev.element;
            n.prev.element = tmp;
        }
    }


    /*
     * This is the inner class of LinkedList that makes it iterable
     * so that we can we enhanced for loop
     */
    private class WCListIterator implements Iterator<WordCount> {
        private Node nextNode = head;

        /*
         * This method checks if the Node is the end of the list
         *
         * @return true if there is something follows, false otherwise
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /*
         * gets the WordCount in the next Node
         *
         * @return the WordCount
         */
        @Override
        public WordCount next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator exceed");
            }
            WordCount data = nextNode.element;
            nextNode = nextNode.next;
            return data;
        }
    }

    public Iterator iterator(){
        return new WCListIterator();
    }




}
