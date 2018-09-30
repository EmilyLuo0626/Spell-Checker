import java.util.ArrayList;

/*
 * This is the AVLHashSet class
 * It is a hash algorithm that uses separate chaining and AVL tree to deal with the collision
 */
public class AVLHashSet<T> {
    //ArrayList<AVLTree<T>> table;                                    //ArrayList to store the AVL trees
    AVLTree<T>[] table;
    static int size = 11;

    /*
     * Null constructor that creates an empty hash table that has 11 entries
     */
    public AVLHashSet(){
        //table = new ArrayList<>(11);
        table = new AVLTree[11];
    }

    /*
     * Constructor that accepts the table size as an positive non-zero integer
     */
    public AVLHashSet(int tableSize){
        if(tableSize<=0)
            throw new RuntimeException("Invalid table size");
        //table = new ArrayList<>(tableSize);
        table = new AVLTree[tableSize];
        size = tableSize;
    }

    /*
     * boolean add(T data) adds data to the hash table.
     * Return true is the add was successful, false otherwise (data was already in the table)
     */
    public boolean add(T data){
        //if the data is already in the list, fail to add
        if(contains(data))
            return false;
        //get the index of the data in the table list
        int index = hashCode(data);
        if(index!=-1){ //assert data is a string
            if(table[index]==null){ //there's no AVLTree at the location
                AVLTree<T> newTree = new AVLTree<>();
                newTree.add(data);
                table[index] = newTree;
            }
            else{
                table[index].add(data);
            }
        }
        return true;

    }

    /*
     * boolean contains(E data) returns true if the hash table contains data, return false otherwise
     */
    public boolean contains(T data){
        boolean found = false;                                  //whether found the data
        for(int i = 0;i<table.length;i++){
            if(table[i]!=null)
                if(table[i].contains(data)) {
                    found = true;
                    break;
                }
        }
//        int index = hashCode(data);
//        if(table[index]!=null)
//            found = table[index].contains(data);
        return found;
    }

    /*
     * int getSize() returns the number of data items stored in the hash table.
     */
    public int getSize(){
        int size = 0;                                           //the total number of elements
        for(int i = 0;i<table.length;i++){
            if(table[i]!=null)
                size += table[i].getSize();
        }
        return size;
    }

    /*
     * E[] toArray(E[] list) returns an array containing the data items in the hash table.
     * this method will need a visitor to store data items in the array as it traverses the AVL trees.
     */
    public T[] toArray(T[] list){
        T[] tempList;
        ToArray<T> a = new ToArray<>(list);                     //a visitor class
        for(int i=0;i<table.length;i++){
            if(table[i]!=null)
                a = (ToArray<T>) table[i].traverseFromRoot(a);
        }
        return a.getList();
    }

    /*
     * hashCode(String data) returns the hash code for the given data
     */
    public static <T> int hashCode(T data){
        if(data instanceof String) {
            String s = (String)data;
            int max = (int) Math.ceil(26.0 / size);             //the maximum number of characters stored in a hash
            int index = s.charAt(0) - 97;                       //the hash code of the data
            index = index/max;
            return index;
        }
        else
            return -1;
    }

}
