/*
 * Created on Jul 1, 2003
 * Some resources I found useful in understanding
 * Binary Search Trees and making this class
 * http://www.autoobjects.com/Home/Teaching/CmpE_126/CmpE_126_Lectures/Binary_Search_Tree/binary_search_tree.html
 * http://web.mit.edu/1.00/www/Lectures/Lecture28/Lecture28print.pdf
 * http://www.nist.gov/dads/HTML/binarySearchTree.html
 */
import java.lang.StringBuffer;
import java.lang.Comparable;

/**
 * @author Fabian Jones AD Summer Intern 2003
 *
 */
public class BSTree {
	// root node of this tree
	private BSTNode root;
	
	/*
	 * Default constructor, initializes an empty tree
	 * with root == null
	 */
	public BSTree(){
		this(null);
	}
	
	/*
	 * Constructor, initializes the tree with rootNode = node
	 */
	public BSTree(BSTNode node){
		root = node;
	}
	
	/*
	 * @return the root of this tree
	 */
	public BSTNode getRoot(){
		return root;
	}
	
	/*
	 * @return BSTNode with value equal to val, or null
	 * if BSTNode with that value is not found
	 */
	public BSTNode find(Comparable val){
		if(root != null){
			return root.findNode(val);
		}
		return null;
	}
	
	/*
	 * @return the node removed that has the same value
	 * as the val argument
	 */
	public BSTNode removeNode(Comparable val){
		return removeNode(new BSTNode(val));
	}
	
	/*
	 * @return removes the node from the tree
	 */
	public BSTNode removeNode(BSTNode node){
		return removeNodeHelper(root, node);
	}
	
	/*
	 * helper function to remove the node
	 * To remove a node, there are three possibilities
	 * 1.  The node to be deleted is a leaf, delete it and re-adjust the
	 *     parent of the leaf
	 * 2.  The node has one subtree, reassign the subtree to the parent node
	 * 3.  The node is a tree itself, so then, 2 options exist
	 *     all of which center around trying to find the replacement node
	 *     for the node that will be deleted
	 *     a.  If the left subtree is taller than the right, the replacement node is the 
	 *         max node from the left subtree
	 *     b.  Otherwise (the right subtree height is >= left subtree height)
	 *         the replacement node is the minimum node of the right subtree 
	 */
	private BSTNode removeNodeHelper(BSTNode start, BSTNode node){
		if(start == null){
			return null;
		}
		else{
			int delta = node.getValue().compareTo(start.getValue());
			
			if(delta < 0){
				// traverse the left child to find the node
				return removeNodeHelper(start.getLeftChild(), node);
			}
			else if(delta > 0){
				// traverse the right child to find the node
				return removeNodeHelper(start.getRightChild(), node);
			}
			else{
				// node found!
				if(start.isLeaf()){
					// remove a leaf
					return this.removeLeaf(start);
				}
				else if(start.isTree()){
					// remove a node that is a tree
					// meaning it has 2 subtrees
					return this.removeTree(start);
				}
				else{
					// remove a node with one subtree
					return this.removeOneSubTree(start);
				}
			}
		}
	}
	
	private BSTNode removeLeaf(BSTNode leaf){
		if(leaf.hasParent()){
			// has Parent
			BSTNode leafParent = leaf.getParent();
			
			// no references to this leaf object now
			leaf.setParent(null);
			
			if(leafParent.hasLeftChild() && leafParent.getLeftChild().equals(leaf)){
				// if the leaf is the left child of this parent
				// set that child to null
				leafParent.setLeftChild(null);
			}
			else{
				// if the leaf is the right child of the parent
				// set the right child of the parent to null
				leafParent.setRightChild(null);
			}
		}
		else{
			// is a Leaf, does not have Parent, then it is the root
			root = null;
		}
		return leaf;
	}
	
	private BSTNode removeTree(BSTNode tree){
		//System.out.println("2 subtrees");
		BSTNode replacementNode;
		if(heightHelper(tree.getLeftChild()) > heightHelper(tree.getRightChild())){
			// if left tree is taller, replacement node 
			// is the max of the left tree
			replacementNode = getMaxHelper(tree.getLeftChild());
		}
		else{
			// replacement node is the min of the right tree
			replacementNode = getMinHelper(tree.getRightChild());
		}
					
		if(tree.hasParent()){
			// if the tree has a parent, then
			// set the child of the replacement node to 
			// the corresponding tree child 
						
			if(tree.hasRightChild() && !tree.getRightChild().equals(replacementNode)){
				replacementNode.setRightChild(tree.getRightChild());
			}
						
			if(tree.hasLeftChild() && !tree.getLeftChild().equals(replacementNode)){
				replacementNode.setLeftChild(tree.getLeftChild());
			}
			
			// set the parent of the tree to the replacement node
			BSTNode treeParent = tree.getParent();
						
			if(treeParent.hasLeftChild() && treeParent.getLeftChild().equals(tree)){
				treeParent.setLeftChild(replacementNode);
			}
			else{
				treeParent.setRightChild(replacementNode);
			}
			
			// set the parent of the replacement node to null
			BSTNode replaceNodeParent = replacementNode.getParent();
						
			if(replaceNodeParent.hasLeftChild() 
					&& replaceNodeParent.getLeftChild().equals(replacementNode)){
				replaceNodeParent.setLeftChild(null);
			}
			else{
				replaceNodeParent.setRightChild(null);
			}
			
			replacementNode.setParent(treeParent);
		}
		else{
			// remove root node
			BSTNode replaceNodeParent = replacementNode.getParent();
						
			if(replaceNodeParent.hasLeftChild() 
					&& replaceNodeParent.getLeftChild().equals(replacementNode)){
				// if the replacement node is the left child
				// of it's parent, set that child to null
				replaceNodeParent.setLeftChild(null);
			}
			else{
				// if the replacement node is the right child
				// of it's parent, set that child to null
				replaceNodeParent.setRightChild(null);
			}
			
			// replacementNode is now the root			
			replacementNode.setParent(null);
			replacementNode.setRightChild(tree.getRightChild());
			replacementNode.setLeftChild(tree.getLeftChild());
			tree.getRightChild().setParent(replacementNode);
			tree.getLeftChild().setParent(replacementNode);
			root = replacementNode;
		}
		// remove references to other nodes
		tree.setParent(null);
		tree.setRightChild(null);
		tree.setLeftChild(null);
		return tree;
	}
	
	private BSTNode removeOneSubTree(BSTNode start){
		if(start.hasParent()){
			BSTNode startParent = start.getParent();
			
			// if the
			if(startParent.hasLeftChild() && startParent.getLeftChild().equals(start)){
				// if start has left child, get the left child, else get the right child
				BSTNode n = start.hasLeftChild()? start.getLeftChild(): start.getRightChild();
				// re-adjust the parents and children
				n.setParent(startParent);
				startParent.setLeftChild(n);							
			}
			else{
				// if start has left child, get the left child, else get the right child
				BSTNode n = start.hasLeftChild()? start.getLeftChild(): start.getRightChild();
				// re-adjust the parents and children
				n.setParent(startParent);
				startParent.setRightChild(n);
			}
		}
		else{
			// no parent, then root
			if(start.hasLeftChild()){
				root = start.getLeftChild();
				start.getLeftChild().setParent(null);
			}
			else{
				root = start.getRightChild();
				start.getRightChild().setParent(null);
			}
		}
		// remove references to other nodes
		start.setLeftChild(null);
		start.setRightChild(null);
		start.setParent(null);
		return start;
	}
		
	/*
	 * inserts the node in the appropriate position
	 * in the tree
	 */
	public void insertNode(BSTNode node){
		if(root != null){
			root.insertNode(node);
		}
	}
	
	/*
	 * @returns the maximum BSTNode in this tree
	 */
	public BSTNode getMaximum(){
		if(root != null){
			return getMaxHelper(root);
		}
		return null;
	}
	
	/*
	 * Helper function for the finding the maximum
	 * The basic strategy is to traverse down the right
	 * children of the tree, until we come to a node
	 * that does not have a right child.  When we come
	 * to that node, that node is the maximum, so return it
	 */
	private BSTNode getMaxHelper(BSTNode node){
		// if this node has a right child, it is not the maximum
		// return the helper function executed on the right child 
		if(node.hasRightChild()){
			return getMaxHelper(node.getRightChild());
		}
		return node;
	}
	
	/*
	 * return the BSTNode that is the minimum of this tree
	 */
	public BSTNode getMinimum(){
		if(root != null){
			return getMinHelper(root);
		}
		return null;
	}
	
	/*
	 * helper function the returns the minimum node in this tree
	 * basic strategy is to traverse down the left children in the tree
	 * until we come to a node that doesn't have a left child, that node
	 * is the minimum, then return it
	 */
	private BSTNode getMinHelper(BSTNode node){
		if(node.hasLeftChild()){
			return getMinHelper(node.getLeftChild());
		}
		return node;
	}
	
	/*
	 * @return the minimum BSTNode in this tree
	 * and removes it
	 */
	public BSTNode removeMinimum(){
		return removeMinHelper(root);
	}
	
	/*
	 * helper function to remove minimum node
	 * same basic strategy as get minimum, however
	 * to remove the node, has to re-adjust the parent
	 * of the minimum node   
	 */
	private BSTNode removeMinHelper(BSTNode start){
		if(start == null){
			// if null, return null
			return null;
		}
		else if(start.hasLeftChild()){
			// if has left child, not the minimum
			// traverse down the left child
			return removeMinHelper(start.getLeftChild());
		}
		else{
			// found the minimum
			
			if(start.hasParent()){
				// has parent
				start.getParent().setLeftChild(start.getRightChild());
			}
			else{
				// the root node is the minimum
				// set root to the right child of the
				// current root,if not null, set the parent
				// of the right child to null
				root = start.getRightChild();
			}
			
			if(start.hasRightChild()){
				start.getRightChild().setParent(start.getParent());
			}
			return start;
		}
	}
	
	/*
	 * removes the node with the maximum value in this tree
	 * @return the node with the maximum value
	 */
	public BSTNode removeMaximum(){
		return removeMaxHelper(root);
	}
	
	/*
	 * helper function to remove the maximum value node
	 * same basic strategy as findMaximum, i.e. traverse 
	 * down the right children until we find the max,
	 * then remove it by re-adjusting the parent
	 * node and any children that the max might have
	 */
	private BSTNode removeMaxHelper(BSTNode start){
		if(start == null){
			// if null, return null
			return null;
		}
		else if(start.hasRightChild()){
			// if has right child, then not maximum
			// traverse down the right child to find max
			return removeMaxHelper(start.getRightChild());
		}
		else{
			if(start.hasParent()){
				start.getParent().setRightChild(start.getLeftChild());	
			}
			else{
				// doesn't have parents, then it is the root
				root = start.getLeftChild();
			}
			
			if(start.hasLeftChild()){
				start.getLeftChild().setParent(start.getParent());
			}
			return start;
		}
	}
	
	/*
	 * @return true if the tree is empty
	 */
	public boolean isEmpty(){
		return root == null;
	}
	
	/*
	 * Creates an empty tree
	 */
	public void makeEmpty(){
		// note: I am not sure if more is necessary
		// but Java gc should take care of the rest...I hope
		root = null;
	}
	
	/*
	 * @return the number of nodes in the tree, including the root
	 */
	public int getSize(){
		return getSizeHelper(root);
	}
	
	/*
	 * helper function that counts each node starting from node start
	 */
	private int getSizeHelper(BSTNode start){
		if(start == null)
			return 0;
		else{
			// returns 1 + size of left child + size of right child 
			return getSizeHelper(start.getLeftChild())
				+ getSizeHelper(start.getRightChild()) + 1;
		}
	}
	
	/*
	 * @return the number of Nodes in this tree 
	 * that satisfies node.isLeaf() == true
	 */
	public int getLeavesCount(){
		return leavesCountHelper(root);
	}
	
	/*
	 * helper function that counts the number of leaves
	 * tree beginning at the node start
	 */
	private int leavesCountHelper(BSTNode start){
		if(start == null){
			return 0;
		}
		if(start.isLeaf()){
			return 1;
		}
		else{
			// return num leaves in left child + num leaves in right child
			return leavesCountHelper(start.getLeftChild())
					+ leavesCountHelper(start.getRightChild());
		}
	}
	
	/*
	 * @return the height of the tree 
	 */
	public int getHeigth(){
		return heightHelper(root);
	}
	
	/*
	 * helper function that returns height from a start node 
	 */
	private int heightHelper(BSTNode start){
		if  (start == null){
			return 0;
		}
		else{
			return Math.max(heightHelper(start.getLeftChild()), heightHelper(start.getRightChild())) + 1;
		}
	}
	
	/*
	 * Returns a String of the Nodes in preorder
	 * preorder is defined recursively as root, left subtree, right subtree
	 */
	public String preorder(){
		if(root != null){
			return root.preorder();
		}
		return null;
	}
	
	/*
	 * Returns String of Nodes in inorder
	 * inorder is defined recursively as left subtree, root, right subtree
	 */
	public String inorder(){
		if(root != null){
			return root.inorder();
		}
		return null;
	}
	
	/*
	 * Returns String of Nodes in postorder
	 * postorder is defined recursively as left subtree, right subtree, root
	 */
	public String postorder(){
		if(root != null){
			return root.postorder();
		}
		return null;
	}
	
	/***Overloaded Objects Method***/
	public int hashCode(){
		return root.hashCode();
	}
	
	public String toString(){
		if(root == null)
			return "{EmptyTree}\n";
		else
			return "\n" + showSub(root, 1) + "\n";
	}
	
	private String showSub(BSTNode node, int level){
		// toString method, returns an on-the-side
		// flipped over version of the tree
		StringBuffer sb = new StringBuffer();
		
		if(node != null){
			sb.append(showSub(node.getRightChild(), level+1));
			for(int j = 0;  j < level; ++ j){
				sb.append("\t\t");
			}
			sb.append(" " + node.toString());
				
			if(node.isTree()){
				sb.append("<");
			}
			else if(node.hasRightChild()){
				sb.append("/");
			}
			else if (node.hasLeftChild()){
				sb.append("\\");
			}
			sb.append("\n" + showSub(node.getLeftChild(), level+1));
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		//BSTree tree = new BSTree(new BSTNode(new Integer(37)));
		//tree.insertNode(new BSTNode(new Integer(24)));
		//tree.insertNode(new BSTNode(new Integer(42)));
		//tree.insertNode(new BSTNode(new Integer(7)));
		//tree.insertNode(new BSTNode(new Integer(2)));
		//tree.insertNode(new BSTNode(new Integer(40)));
		//tree.insertNode(new BSTNode(new Integer(32)));
		//tree.insertNode(new BSTNode(new Integer(120)));
		//tree.insertNode(new BSTNode(new Integer(100)));
		
		BSTree tree = new BSTree(new BSTNode("H"));
		tree.insertNode(new BSTNode("A"));
		tree.insertNode(new BSTNode("F"));
		tree.insertNode(new BSTNode("B"));
		tree.insertNode(new BSTNode("G"));
		tree.insertNode(new BSTNode("D"));
		tree.insertNode(new BSTNode("C"));
		tree.insertNode(new BSTNode("E"));
		
		System.out.println(tree);
		System.out.println("preorder: " + tree.preorder());
		System.out.println("inorder: " + tree.inorder());
		System.out.println("postorder: " + tree.postorder());
		
		//System.out.println(tree);
		//System.out.println("remove: " + tree.removeNode(new Integer(37)));
		//System.out.println(tree);
		//System.out.println("remove: " + tree.removeNode(new Integer(7)));
		//System.out.println(tree);
		
		//tree.insertNode(new BSTNode(new Integer(900)));
		//System.out.println(tree);
		
		//for(int i = 0; i < 10; ++i){
		//	System.out.println(tree);
		//	System.out.println(tree.removeMaximum());	
		//}
		
		//for(int i = 0; i < 10; ++i){
		//	System.out.println(tree);
		//	System.out.println("remove min: " + tree.removeMinimum());	
		//}
		
		
		//tree.removeNode(new Integer(24));		
		//System.out.println(tree);
		
		//tree.removeNode(new Integer(37));
		//System.out.println(tree);
		
		//tree.removeNode(new Integer(7));		
		//System.out.println(tree);
	}
}