/* Class BSTNode
 * 	this class represents objects in a dictionary. A BSTNode can be a leaf which
 * 	has no children, or it can have two children. A leaf can have a record or it may not.
 * 	The root is a BSTNode with no parent.
 * 
 * @author Sabrina Lee 251297577
 * CS2210A 001
 * Prof. Solis Oba
 * November 19, 2023
 */

public class BSTNode {
	// INSTANCE VARIABLES
	private Record theRecord;
	private BSTNode left;
	private BSTNode right;
	private BSTNode parent;
	
	/**
	 * Constructor initializes instance variables
	 * @param item
	 */
	public BSTNode(Record item) {
		this.theRecord = item;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	/**
	 * Accessor method getRecord returns record of the node object
	 * @return theRecord
	 */
	public Record getRecord() {
		return this.theRecord;
	}
	/**
	 * Mutator method setRecord sets the record of the node object with the given parameter
	 * @param d
	 */
	public void setRecord (Record d) {
		this.theRecord = d;
	}
	/**
	 * Accessor method getLeftChild returns the left child node of a node or null if 
	 * 	no such child exists
	 * @return left
	 */
	public BSTNode getLeftChild() {
		return this.left;
	}
	/**
	 * Accessor method getRightChild returns the right child node of a node or null if 
	 * 	no such child exists
	 * @return right
	 */
	public BSTNode getRightChild() {
		return this.right;
	}
	/**
	 * Accessor method getParent returns the parent node of a node or null if 
	 * 	the node is the root
	 * @return parent
	 */
	public BSTNode getParent() {
		return this.parent;
	}
	/**
	 * Mutator method setLeftChild sets the left child of a node as the given parameter.
	 * @param u
	 */
	public void setLeftChild(BSTNode u) {
		this.left = u;
	}
	/**
	 * Mutator method setRightChild sets the right child of a node as the given parameter.
	 * @param u
	 */
	public void setRightChild(BSTNode u) {
		this.right = u;
	}
	/**
	 * Mutator method setParent sets the parent of a node as the given parameter.
	 * @param u
	 */
	public void setParent(BSTNode u) {
		this.parent = u;
	}
	/**
	 * method isLeaf checks if a node has two children
	 * @return true if both left and right children of a node exist
	 * @return false if either child does not exist
	 */
	public boolean isLeaf() {
		if (this.left == null && this.right == null) {
			return true;
		}
		return false;
	}
}
