/*
 * This is the sub-class of WCLinkedList, and use transpose sorting method
 */
public class TWCLinkedList extends WCLinkedList {
    /*
     * This method overrides the contains() method in WCLinkedList
     * It adds one if the word is found in the list, and swap it with the word in front of it
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
                swap(tmp);
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }
}
