/*
 * This is a class for binary tree nodes
 */
public class LinkedBinaryTreeNode<T> implements BinaryTreeNode<T>{
    protected T data;                                           //stores the data
    protected LinkedBinaryTreeNode<T> parent;                   //parent of the node
    protected LinkedBinaryTreeNode<T> left;                     //left child
    protected LinkedBinaryTreeNode<T> right;                    //right child

    /**
     * Constructs a node as root of its own one-element tree. */
    public LinkedBinaryTreeNode(T data){
        this.data = data;
    }

    /**
     * Returns the data stored in this node. */
    public T getData(){ return data; }

    /**
     * Modifies the data stored in this node. */
    public void setData(T data){ this.data = data; }

    /**
     * Returns the parent of this node, or null if root. */
    public BinaryTreeNode<T> getParent(){ return parent; }

    /**
     * Returns the left child of this node, or null if none. */
    public BinaryTreeNode<T> getLeft(){ return left; }

    /**
     * Returns the right child of this node, or null if none. */
    public BinaryTreeNode<T> getRight(){ return right; }

    /**
     * Removes child from its current parent and inserts it * as the left child of this node. If this node already * has a left child then it is removed.
     * @exception IllegalArgumentException if the child is * an ancestor of this node
     */
    public void setLeft(BinaryTreeNode<T> child){
        // Ensure the child is not an ancestor.
        for(LinkedBinaryTreeNode<T> n = this; n != null; n = n.parent){
            if(n == child)
                throw new IllegalArgumentException();
        }

        // Ensure child is instance of LinkedBinaryTreeNode.
        LinkedBinaryTreeNode<T> childNode = (LinkedBinaryTreeNode<T>) child;

        // Break old links, then reconnect properly.
        if(this.left != null) left.parent = null;
        if(childNode != null){
            childNode.removeFromParent();
            childNode.parent = this;
        }
        this.left = childNode;
    }

    /**
     * Removes child from its current parent and inserts it
     * as the left child of this node. If this node already
     * has a left child then it is removed.
     * @exception IllegalArgumentException if the child is * an ancestor of this node
     */

    public void setRight(BinaryTreeNode<T> child){
        // Ensure the child is not an ancestor.
        for(LinkedBinaryTreeNode<T> n = this; n != null; n = n.parent){
            if(n == child)
                throw new IllegalArgumentException();
        }

        // Ensure child is instance of LinkedBinaryTreeNode.
        LinkedBinaryTreeNode<T> childNode = (LinkedBinaryTreeNode<T>) child;

        // Break old links, then reconnect properly.
        if(this.right != null)
            right.parent = null;
        if(childNode != null){
            childNode.removeFromParent();
            childNode.parent = this;
        }
        this.right = childNode;
    }


    /**
     * Removes this node, and all its descendants, from * whatever tree it’s in. Nothing if node is a root. */
    public void removeFromParent(){
        if(parent != null){
            if(parent.left == this)
                parent.left = null;
            else if(parent.right == this)
                parent.right = null;
            this.parent = null;
        }
    }


    /*
     * Visits the nodes in this tree in preorder
     */
    public void traversePreorder(BinaryTreeNode.Visitor<T> visitor){
        visitor.visit(this);
        if(left!=null)
            left.traversePreorder(visitor);
        if(right!=null)
            right.traversePreorder(visitor);
    }

    /*
     * Visits the nodes in this tree in Postorder
     */
    public void traversePostorder(BinaryTreeNode.Visitor<T> visitor){
        if(left!=null)
            left.traversePreorder(visitor);
        if(right!=null)
            right.traversePreorder(visitor);
        visitor.visit(this);
    }

    /*
     * Visits the nodes in this tree in Inorder
     */
    public void traverseInorder(BinaryTreeNode.Visitor<T> visitor){
        if(left!=null)
            left.traversePreorder(visitor);
        visitor.visit(this);
        if(right!=null)
            right.traversePreorder(visitor);
    }

    @Override
    public String toString(){
        return data.toString();
    }


}
