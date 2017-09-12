/*
 * Implementation of a Binary Search Tree Node
 * A BSTNode is a Mutable Object.
 */

import java.lang.Comparable;
import java.lang.StringBuffer;

/*
 * Created on Jun 30, 2003
 */

/**
 * @author Fabian Jones AD Summer Intern 2003
 */
public class BSTNode {
	// private member variables
	
	// the value of the node
	private Comparable value;
	// the left child of this node
	private BSTNode left;
	// the right child
	private BSTNode right;
	// the parent of this node
	private BSTNode parent;
	// cache the hash code
	private int hash = 0;
	
	// cache the Stringbuffer used for traverse String
	// to cut down on Object creation, i.e. creating
	// new StringBuffer objects everytime the method is called
	private StringBuffer preorderSB;
	private StringBuffer inorderSB;
	private StringBuffer postorderSB;
	
	/*
	 * Default Constructor for BSTNode, sets the value of this
	 * BSTNode to elem.
	 * @param elem 
	 */
	public BSTNode(Comparable elem){
		this(elem, null, null);
	}
	
	/*
	 * Contructor for BSTNode
	 * @param elem the value of the BSTNode
	 * @param lf the left child of this BSTNode
	 * @param rt the right child of this BSTNode
	 */
	public BSTNode(Comparable elem, BSTNode lf, BSTNode rt){
		value = elem;
		left = lf;
		right = rt;
	}
	
	/*
	 * @return the value of this BSTNode
	 */
	public Comparable getValue(){
		return value;
	}
	
	/*
	 * @return the left child of this BSTNode
	 */
	public BSTNode getLeftChild(){
		return left;
	}
	
	/*
	 * @return the right child of this BSTNode
	 */
	public BSTNode getRightChild(){
		return right;
	}
	
	/*
	 * @return the parent of this BSTNode
	 */
	public BSTNode getParent(){
		return parent;
	}
	
	/*
	 * @param node sets the left child of this BSTNode to node
	 * also, sets the parent of the left child to this
	 */
	public void setLeftChild(BSTNode node){
		left = node;
	}
	
	/*
	 * @param node sets the right child of this BSTNode to node
	 * also, sets the parent of the right child to this
	 */
	public void setRightChild(BSTNode node){
		right = node;
	}
	
	/*
	 * @param node sets the parent of this BSTnode to node
	 */
	public void setParent(BSTNode node){
		parent = node;
	}
	
	/*
	 * @return true if this BSTNode has a left child,
	 * which is defined as the left child not being null
	 */
	public boolean hasLeftChild(){
		return left != null;
	}
	
	/*
	 * @return true if this BSTNode has a right child,
	 * which is defined as the right child not being null
	 */
	public boolean hasRightChild(){
		return right != null;
	}
	
	/*
	 * @return true if this BSTNode has a parent
	 */
	 public boolean hasParent(){
		 return parent != null;
	 }
	
	/*
	 * @return true if this BSTNode has a left or a right child
	 */ 
	public boolean hasChildren(){
		return hasRightChild() || hasLeftChild();
	}
	
	/*
	 * @return true if this BSTNode has both left and right child 
	 */
	public boolean hasBothChildren(){
		return hasRightChild() && hasLeftChild();
	}
	
	/*
	 * @return true if this has no children
	 * note: this is a convinience method, because it is
	 * equivalent to !hasBothChildren()
	 */
	public boolean isLeaf(){
		return (left == null) && (right == null);
	}
	
	/*
	 * @return true if this has both children
	 * note: this is a convinience method, equivalent
	 * to hasBothChildren()
	 */
	public boolean isTree(){
		return (left != null) && (right != null);
	}
	
	/*
	 * @param val insert node of value val
	 */
	public void insertNode(Comparable val){
		insertNode(new BSTNode(val));
	}
	
	/*
	 * @param node insert node into this BSTNode
	 */
	public void insertNode(BSTNode node){
		int delta = node.getValue().compareTo(value);
		
		// if the node is less than the this node
		if(delta < 0){
			if(left == null){
				// if doesn't have a left child, set
				// the left child to the node
				// also set the parent of the node
				left = node;
				left.setParent(this);
			}
			else{
				// has left child, so insert in the child node
				left.insertNode(node);
			}
		}
		// else if the node is greater than this node
		else if(delta > 0){
			if(right == null){
				// has no right child, set right child
				// to the node, and set the parent to this
				right = node;
				right.setParent(this);
			}
			else{
				// has right child, so insert in the right child
				right.insertNode(node);
			}
		}
	}
	
	/*
	 * @return BSTNode with the value val, or null if it is not found
	 * @param val the value of the BSTNode to search for
	 */
	public BSTNode findNode(Comparable val){
		int delta = val.compareTo(value);
		// the value is less than this.value
		if(delta < 0){
			// if there is a leftChild, return left.findNode(val)
			// there is no leftChild, so the val does not exist
			// in the node, so return null
			return (left!= null)? left.findNode(val): null;
		}
		// else if the value is greater than this.value
		else if (delta > 0){
			// if there is a rightChild, then return right.findNode(val)
			// else, there is no rightChild, return null
			return (right != null)? right.findNode(val): null;
		}
		// else, dela == 0, so we have found the node with that
		// val, return the node
		return this;
	}
	
	/*
	 * @return BSTNode with the value of node.getValue() or null if not found 
	 */ 
	public BSTNode findNode(BSTNode node){
		return findNode(node.getValue());
	}

	/*
	 * Returns a String of the Nodes in preorder
	 * preorder is defined recursively as root, left subtree, right subtree
	 */
	public String preorder(){
		if(preorderSB == null){
			preorderSB = new StringBuffer();
		}
		else{
			preorderSB.delete(0, preorderSB.length());
		}
		
		preorderSB.append(toString());
		preorderSB.append(hasLeftChild()? left.preorder():"");
		preorderSB.append(hasRightChild()? right.preorder():"");
		
		return preorderSB.toString();
	}

	/*
	 * Returns String of Nodes in inorder
	 * inorder is defined recursively as left subtree, root, right subtree
	 */
	public String inorder(){
		if(inorderSB == null){
			inorderSB = new StringBuffer();
		}
		else{
			inorderSB.delete(0, inorderSB.length());
		}
		
		inorderSB.append( hasLeftChild()? left.inorder():"" );
		inorderSB.append(toString());
		inorderSB.append( hasRightChild()? right.inorder():"");
		
		return inorderSB.toString();
	}

	/*
	 * Returns String of Nodes in postorder
	 * postorder is defined recursively as left subtree, right subtree, root
	 */
	public String postorder(){
		if(postorderSB == null){
			postorderSB = new StringBuffer();
		}
		else{
			postorderSB.delete(0, postorderSB.length());
		}
		
		postorderSB.append(hasLeftChild()? left.postorder():"");
		postorderSB.append(hasRightChild()? right.postorder():"");
		postorderSB.append(toString());
		
		return postorderSB.toString();
	}

	/***Overloaded Object Methods***/
	/*
	 * @see java.lang.Object#equals()
	 * Two BSTNodes are equal if their values
	 */
	public boolean equals(Object o){
		if(o instanceof BSTNode){
			BSTNode b = (BSTNode) o;
			return b.value.equals(value);		
		}
		return false;
	}
	/*
	 *  (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		// hashCode has not been initialized, so initialize it
		if(hash == 0){
			hash = value.hashCode();
		}
		return hash;
	}
	/*
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		// string representation of this string
		// of the form: "(BSTNode.value)"
		return "{" + value.toString() + "}";				
	}
	
	public static void main(String[] args){
		BSTNode node = new BSTNode("B");
		BSTNode node1 = new BSTNode("A");
		
		node.insertNode(node1);
		node.insertNode("D");
		
		System.out.println(node.hasLeftChild());
		System.out.println(node1.getParent());
		
	}
}