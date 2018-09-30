/*
 * This is an generic interface for binary trees
 */
public interface BinaryTreeNode<T> {

    /*
     * Returns the data stored in this node
     */
    T getData();

    /*
     * Modifies the data stored in this node
     */
    void setData(T data);

    /*
     * Returns the parent of node, or null if node is root
     */
    BinaryTreeNode<T> getParent();

    /**
     * Returns the left child of this node, or null if none */
    BinaryTreeNode<T> getLeft();

    /**
     * Returns the right child of this node, or null if none */
    BinaryTreeNode <T> getRight();

    /**
     * Removes child from its current parent and inserts it
     * as the left child of this node. If this node already
     * has a left child it is removed.
     *
     * @exception IllegalArgumentException if the child is
     * an ancestor (that would make a cycle).
     */
    void setLeft(BinaryTreeNode<T> child);

    /**
     * Removes child from its current parent and inserts it
     * at the right child of node, remove existing left child
     *
     * @exception IllegalArgumentException if child ancestor
     */
    void setRight(BinaryTreeNode <T> child);


    /**
     * Removes this node and all its descendants from tree
     **/
    void removeFromParent ();

    /**
     * Visits the nodes in this tree in preorder. */
    void traversePreorder(Visitor<T> visitor);

    /**
     * Visits the nodes in this tree in postorder. */
    void traversePostorder(Visitor<T> visitor);

    /**
     * Visits the nodes in this tree in inorder. */
    void traverseInorder(Visitor<T> visitor);

    /**
     * Visitor pattern used by traversals to process
     */
    public interface Visitor<T>{
        void visit(BinaryTreeNode<T> node);
    }
}





