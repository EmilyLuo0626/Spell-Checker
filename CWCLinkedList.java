/*
 * This is the sub-class of WCLinkedList, and use count sorting method
 */
public class CWCLinkedList extends WCLinkedList {
    /*
     * This method overrides the contains() method in WCLinkedList
     * It adds one if the word is found in the list
     * If the count of the word is larger than the one in front of it, swap it with the previous one
     *
     * @param String that you want to search for
     * @return true if the word is contained in the list, false otherwise
     */
    @Override
    public boolean contains(String data){
        Node tmp = head;
        while(tmp != null){
            if(tmp.element.getWord().equals(data)) {
                tmp.element.addCount();
                if(tmp.prev!=null && tmp.element.compareTo(tmp.prev.element)>0)
                    swap(tmp);
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }
}
