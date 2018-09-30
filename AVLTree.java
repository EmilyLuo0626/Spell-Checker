import java.util.Comparator;

/*
 * A AVL tree class
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    /*
     * Constructs an empty AVL that only accept comparable items
     */
    public AVLTree(){
        this(null);
    }

    /*
     * Constructs an AVL that orders its items according to given comparator
     */
    public AVLTree(Comparator<E> c){
        super(c);
    }

    /*
     * Adds a single data item to the AVL tree, can overwrite
     */
    @Override
    public void add(E data){
        // let’s go ahead and add the new node, thanks BST
        super.add(data);
        // find the new node containing the data
        BinaryTreeNode<E> n = nodeContaining(data);
        // if there is a new node, then rebalance
        if(n != null) rebalance(n);
    }

    /**
     * Removes a data item from the AVL tree */
    @Override
    public void remove(E data){
        // find the node containing the data
        BinaryTreeNode<E> n = nodeContaining(data);
        if(n != null){
            // code here to determine the node n where the
            // rebalance should start (done before removal)
            if(n.getLeft()==null && n.getRight()==null) //if the node is a leaf
                n = n.getParent();
            else if( n.getLeft()!=null && n.getRight()!=null) //if the node has two children
                n = predecessor(n).getParent();
            else if(n.getRight()==null) //if the node has only left child
                n = n.getLeft();
            else //if the node has only right child
                n = n.getRight();


            // let’s remove node containing the data
            super.remove(data);
            // Rebalance starting at n
            rebalance(n);
        }
    }

    /*
     * Rebalance the tree to maintain it as an AVL tree
     */
    protected void rebalance(BinaryTreeNode<E> node) {
        assert (node != null);
        E data = node.getData();
        // let’s check every node until we reach root
        while (node != null) {
            int heightDiff = getHeight(node.getLeft()) - getHeight(node.getRight());
            if (heightDiff == 2) {                    // left rebalance
                //int comparisonResult = compare(data, node.getLeft().getData());
                int comparisonResult = getHeight(node.getLeft().getLeft()) - getHeight(node.getLeft().getRight());
                if (comparisonResult >= 0)            // left-left
                    rotateRight(node);
                else {                               // left-right
                    rotateLeft(node.getLeft());
                    rotateRight(node);
                }
            }
            else if (heightDiff == -2) {              // right rebalance
                //int comparisonResult = compare(data, node.getRight().getData());
                int comparisonResult = getHeight(node.getRight().getRight()) - getHeight(node.getRight().getLeft());
                if(comparisonResult >= 0)            //right-right
                    rotateLeft(node);
                else{                               //right-left
                    rotateRight(node.getRight());
                    rotateLeft(node);
                }
            }
            node = node.getParent();
        }
    }

}
