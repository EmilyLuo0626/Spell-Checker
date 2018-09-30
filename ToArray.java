/*
 * ToArray class is a Visitor class that implements BinaryTreeNode.Visitor
 * It stores an array of all the nodes in the tree
 */
public class ToArray<T> implements BinaryTreeNode.Visitor<T> {
    T[] list;                                                       //the array that stores all the data
    int index = 0;                                                  //the current index at the list

    /*
     * Constructor takes in an array of T to store the items
     */
    public ToArray (T[] list){
        this.list = list;
    }

    @Override
    public void visit(BinaryTreeNode<T> node) {
        list[index] = node.getData();
        index++;
    }

    public T[] getList() {
        return list;
    }
}
