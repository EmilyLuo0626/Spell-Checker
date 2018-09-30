import java.util.Comparator;

/**
 * A binary search tree class with insertion, removal and
 * lookup. All tree items must be distinct according to the * comparator. If no comparator is supplied the "natural
 * order" of tree elements is used.
 */
public class BinarySearchTree<E> {

    protected BinaryTreeNode<E> root = null;                        //root of the tree
    private Comparator<E> comparator;                               //Comparator used to order the items in the tree
    protected int size;                                               //number of nodes in the tree

    /*
     * Constructs an empty BST that can only accept comparable items
     */
    public BinarySearchTree(){
        this(null);
    }

    /*
     * Constructs a BST that orders its items according to the given comparator
     */
    public BinarySearchTree(Comparator<E> c){
        comparator = c;
        size = 0;
    }

    /*
     * Returns whether or not the tree contains an object with the given value
     */
    public boolean contains(E data){
        return nodeContaining(data) != null;
    }

    /*
     * Adds a single data item to the tree
     * Can be overwritten
     */
    public void add(E data){
        //increase size
        size++;
        if(root == null) {
            root = new LinkedBinaryTreeNode<E>(data);
            return;
        }
        BinaryTreeNode<E> n = root;
        while(true){
            int comparisonResult = compare(data,n.getData());
            if(comparisonResult==0) { //already in the tree
                n.setData(data);
                size--; //since it's a duplicate, reduce the size by 1
                return;
            }
            else if(comparisonResult<0){
                if(n.getLeft() == null){
                    n.setLeft(new LinkedBinaryTreeNode<E>(data));
                    return;
                }
                n=n.getLeft();
            }
            else{
                if(n.getRight() == null){
                    n.setRight(new LinkedBinaryTreeNode<E>(data));
                    return;
                }
                n=n.getRight();
            }

        }
    }

    /*
     * Remove the node corresponding to the data
     * The tree remains a BinarySearchTree
     */
    public void remove(E data){
        //decrease size
        size--;
        //find the node
        BinaryTreeNode<E> node = nodeContaining(data);
        if(node == null) { //data is not in the tree
            size++; //since the removal is not successful, add 1 back to size
            return;
        }
        else if(node.getLeft()!=null && node.getRight()!=null) { //have two children
            //use the method with predecessor
            BinaryTreeNode<E> predecessor = predecessor(node);
            node.setData(predecessor.getData());
            node = predecessor;
        }
        //delete node from the tree
        //node has zero or one child
        BinaryTreeNode<E> pullup;
        if(node.getLeft()==null)
            pullup = node.getRight();
        else
            pullup = node.getLeft();

        //node is the root
        if(node == root)
            setRoot(pullup);
            //node is the left child of parent
        else if(node.getParent().getLeft() == node)
            node.getParent().setLeft(pullup);
            //node is the right child of parent
        else
            node.getParent().setRight(pullup);


    }

    /*
     * Returns the root of the tree
     */
    protected BinaryTreeNode<E> getRoot(){
        return root;
    }

    /*
     * Returns the size of the tree
     */
    public int getSize(){ return size; }

    /*
     * Returns the height of the tree
     */
    public int reHeight(){ return getHeight(root)-1;}

    /*
     * Makes the given node the new root of the tree
     */
    public void setRoot(BinaryTreeNode<E> r){
        if(r!=null)
            r.removeFromParent();
        root = r;
    }

    /*
     * Returns the rightmost node in the left substree
     */
    protected BinaryTreeNode<E> predecessor(BinaryTreeNode<E> node){
        BinaryTreeNode<E> n = node.getLeft();
        if(n!= null)
            while(n.getRight()!=null)
                n = n.getRight();
        return n;
    }

    /*
     * Helper method that returns node containing data
     * This is used in both contains and remove
     */
    protected BinaryTreeNode<E> nodeContaining(E data){
        for(BinaryTreeNode<E> n = root;n != null;){
            int compareResult = compare(data, n.getData());
            if(compareResult == 0)
                return n;
            else if(compareResult<0)
                n = n.getLeft();
            else
                n = n.getRight();
        }
        return null;
    }

    /*
     * Compare the given datas with comparator or default compareTo
     */
    protected int compare(E x, E y){
        if(comparator == null)
            return ((Comparable<E>)x).compareTo(y);
        else
            return comparator.compare(x,y);
    }

    /*
     * Rotate left around the given node
     */
    protected void rotateLeft(BinaryTreeNode<E> n){
        //make sure it has a right child
        if(n.getRight() == null)
            return;
        BinaryTreeNode<E> oldRight = n.getRight();

        //set right child of n to left child of R
        n.setRight(oldRight.getLeft());

        //if rotate around root
        if(n.getParent() == null)
            root = oldRight;
        else if(n.getParent().getLeft() == n)
            n.getParent().setLeft(oldRight);
        else
            n.getParent().setRight(oldRight);

        //set left child of R to n
        oldRight.setLeft(n);
    }

    /*
     * Rotate right around the given node
     */
    protected void rotateRight(BinaryTreeNode<E> n){
        //make sure it has a right child
        if(n.getLeft() == null)
            return;
        BinaryTreeNode<E> oldLeft = n.getLeft();

        //set left child of n to right child of R
        n.setLeft(oldLeft.getRight());

        //if rotate around root
        if(n.getParent() == null)
            root = oldLeft;
        else if(n.getParent().getRight() == n)
            n.getParent().setRight(oldLeft);
        else
            n.getParent().setLeft(oldLeft);

        //set left child of R to n
        oldLeft.setRight(n);
    }

    /*
     * returns the height from the given node
     */
    protected int getHeight(BinaryTreeNode<E> node){
        if(node==null)                                         //node doesn't exist
            return 0;
        else if(node.getLeft()==null && node.getRight()==null) //leaf
            return 1;
        else if(node.getRight()==null && node.getLeft()!=null) //only has left child
            return 1+getHeight(node.getLeft());
        else if(node.getRight()!=null && node.getLeft()==null) //only has right child
            return 1+getHeight(node.getRight());
        else {                                                 //has two children
            int right = getHeight(node.getRight());                     //height of right child
            int left = getHeight(node.getLeft());                       //height of left child
            if (right > left)
                return 1 + right;
            else {
                return 1 + left;
            }
        }
    }

    /*
     *
     */
    public BinaryTreeNode.Visitor<E> traverseFromRoot(BinaryTreeNode.Visitor<E> visitor){
        root.traverseInorder(visitor);
        return visitor;
    }

    @Override
    public String toString(){
        return print(root);
    }

    private String print(BinaryTreeNode<E> node){
        if(node.getLeft()==null && node.getRight()==null)
            return "("+node.getData()+")";
        else if(node.getRight()==null && node.getLeft()!=null)
            return "("+print(node.getLeft())+node.getData()+")";
        else if (node.getRight()!=null && node.getLeft()==null)
            return "("+node.getData()+print(node.getRight())+")";
        else
            return "("+print(node.getLeft())+node.getData()+print(node.getRight())+")";

    }
}
