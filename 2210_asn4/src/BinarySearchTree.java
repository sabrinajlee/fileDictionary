/* Class BinarySearchTree
 * 	this class represents a data structure that stores objects based on their keys. 
 * 	a left child of any given node has a smaller key and a right child
 * 	of any given node has a larger key than the parent node.
 * 
 * @author Sabrina Lee
 * November 19, 2023
 */

public class BinarySearchTree {
	// INSTANCE VARIABLE
	private BSTNode root;
	
	/**
	 * Constructor initializes instance variables
	 */
	public BinarySearchTree() {
		this.root = new BSTNode(null);
	}
	/**
	 * Accessor method getRoot returns the root of the BinarySearchTree object
	 * @return root
	 */
	public BSTNode getRoot() {
		return this.root;
	}
	/**
	 * Accessor method get traverses the tree starting at a given root r, comparing the key of each 
	 * 	node to the given key k. it returns the key of the node with the same key or returns the null leaf
	 * 	node where k should be found but is not.
	 * @param r
	 * @param k
	 * @return BSTNode object with a record containing the key k, if it exists
	 * @return BSTNode leaf object with no record where the key k could be inserted
	 */
	public BSTNode get(BSTNode r, Key k) {
		if (r.isLeaf()) {
			return r;
		}
		else {
			Key rKey = r.getRecord().getKey();
			switch (rKey.compareTo(k)) {
				case 0:
					return r;
				case -1:
					return get(r.getRightChild(), k);
				default:
					return get(r.getLeftChild(), k);
			}
		}
	}
	/**
	 * method inserts the given record d into a leaf node of a BinarySearchTree 
	 * 	or throws a DictionaryException if a BSTNode object with the same key
	 * 	as d already exists. if a node has its record set to d, then its children
	 * 	are set as new null leaf nodes.
	 */
	public void insert (BSTNode r, Record d) throws DictionaryException{
		BSTNode p = get(r,d.getKey());
		BSTNode leftLeaf = new BSTNode(null);
		BSTNode rightLeaf = new BSTNode(null);
		
		// if p is not a leaf then it is a BSTNode with the same key as d
		if (!p.isLeaf()) {
				throw new DictionaryException("Error: Key already exists");
		}
		// in the first call of insert to a BinarySearchTree object, the root is
		//	null so it must be set to the new record
		else if (this.root == null){
			this.root.setRecord(d);
			this.root.setLeftChild(leftLeaf);
			leftLeaf.setParent(this.root);
			this.root.setRightChild(rightLeaf);
			rightLeaf.setParent(this.root);
		}
		// p is a null leaf so its record is set to d
		else {
			p.setRecord(d);
			p.setLeftChild(leftLeaf);
			leftLeaf.setParent(p);
			p.setRightChild(rightLeaf);
			rightLeaf.setParent(p);
			
		}
	}
	/**
	 * method remove deletes the record with key k from a node in the BinarySearchTree
	 * 	if it exists, or throws a DictionaryException if such a node object does not exist.
	 * 	if a node is removed then it is replaced by a null leaf node or one of its subtrees
	 * 	is promoted to take its place or the successor of the removed node takes its place.
	 * @param r
	 * @param k
	 * @throws DictionaryException
	 */
	public void remove (BSTNode r, Key k) throws DictionaryException {
		BSTNode p = get(r, k);
		Key rootKey = getRoot().getRecord().getKey();
		BSTNode leaf = new BSTNode(null);
		
		// if p does not have a record then it is a null leaf and no node with the key
		//	k was found. if the key of p is not the same as k then the node with key does not exist.
		if (p.getRecord() == null || p.getRecord().getKey().compareTo(k) != 0 ) {
			throw new DictionaryException("Error: Key does not exist");
		}
		else {
			BSTNode parent = p.getParent();
			BSTNode rightChild = p.getRightChild();
			BSTNode leftChild = p.getLeftChild();
			Key pKey = p.getRecord().getKey();
			
			// in this branch p is a leaf with a record so its record is removed and it becomes a null leaf
			if (p.isLeaf()) {
				p.setRecord(null);
			}
			// in all following branches p is internal.
			// in this branch both children of p are leafs with no record so the record of p is removed and p
			//	becomes a null leaf
			else if (leftChild.isLeaf() && leftChild.getRecord() == null 
					&& rightChild.isLeaf() && rightChild.getRecord() == null) {
				p.setRecord(null);
				p.setLeftChild(null);
				p.setRightChild(null);
			}
			// in this branch the right child of p is a leaf (and left child may or may not be a leaf)
			else if (rightChild.isLeaf()) {
				// in this branch the right child is a leaf with no record so the left child is promoted
				if (rightChild.getRecord() == null) {
					// in this branch p is the root, it has no parent node
					if (pKey.compareTo(rootKey) == 0) {
						setRoot(leftChild);	// update the root variable of the BinarySearchTree so the previous root is actually removed
						leftChild.setParent(null);
						return;
					}
					// promote the left subtree of p to replace p
					else {
						Key parentKey = parent.getRecord().getKey();
						leftChild.setParent(parent);
						// check if p is the left or right child of the parent and update the corresponding child of the parent
						if (parentKey.compareTo(pKey) == -1) {
							parent.setRightChild(leftChild);
						}
						else {
							parent.setLeftChild(leftChild);
						}
					}
				}
				// in this branch the right child is a leaf with a record
				else {
					// in this branch p is the root, it has no parent
					if (pKey.compareTo(rootKey) == 0) {
						setRoot(rightChild);
						rightChild.setParent(null);
						rightChild.setLeftChild(leftChild);
						rightChild.setRightChild(leaf);
						leftChild.setParent(rightChild);
						return;
					}
					// promote the right subtree of p to replace p
					else {
						Key parentKey = parent.getRecord().getKey();
						// check if p is the left or right child of the parent and update the corresponding child of the parent
						if (parentKey.compareTo(pKey) == -1) {
							parent.setRightChild(rightChild);
						}
						else {
							parent.setLeftChild(rightChild);
						}
						rightChild.setParent(parent);
						rightChild.setLeftChild(leftChild);
						rightChild.setRightChild(leaf);
						leftChild.setParent(rightChild);
					}
				}
			}
			// in this branch the right child of p is not a leaf and the left is
			else if (leftChild.isLeaf()) {
				// in this branch the left child is a null leaf node
				if (leftChild.getRecord() == null) {
					// in this branch p is the root
					if (pKey.compareTo(rootKey) == 0) {
						setRoot(rightChild);
						rightChild.setParent(null);
						return;
					}
					// promote the right subtree of p to replace p
					else {
						Key parentKey = parent.getRecord().getKey();
						rightChild.setParent(parent);
						// check if p is the left or right child of the parent and update the corresponding child of the parent
						if (parentKey.compareTo(pKey) == -1) {
							parent.setRightChild(rightChild);
						}
						else {
							parent.setLeftChild(rightChild);
						}
					}
				}
				// in this branch the left child is a leaf with a record
				else {
					// in this branch p is the root
					if (pKey.compareTo(rootKey) == 0) {
						setRoot(leftChild);
						leftChild.setParent(null);
						leftChild.setLeftChild(leaf);
						leftChild.setRightChild(rightChild);
						rightChild.setParent(leftChild);
						return;
					}
					// promote the left child of p to replace p
					else {
						Key parentKey = parent.getRecord().getKey();
						// check if p is the left or right child of the parent and update the corresponding child of the parent
						if (parentKey.compareTo(pKey) == -1) {
							parent.setRightChild(leftChild);
						}
						else {
							parent.setLeftChild(leftChild);
						}
						leftChild.setParent(parent);
						leftChild.setLeftChild(leaf);
						leftChild.setRightChild(rightChild);
						rightChild.setParent(leftChild);
					}
				}
			}
			// in this branch both children of p are internal
			else {
				BSTNode s = smallest(rightChild);	// retrieve the smallest node of the right child of p
				p.setRecord(s.getRecord());			// copy the smallest node's record into p, effectively turning p into the smallest node
				remove(s,s.getRecord().getKey());	// remove the smallest node from the BinarySearchTree recursively
			}
		}
	}
	/**
	 * method successor returns the successor BSTNode in inorder of the node with the key k of the tree with root r
	 * @param r
	 * @param k
	 * @return BSTNode with the succeeding key of BSTNode with key k if it exists
	 * @return null if the given key k is the largest in the tree
	 */
	public BSTNode successor(BSTNode r, Key k){
		BSTNode p = get(r, k);
		BSTNode l = largest(r);
		// if the largest key in the tree is the same as k then there is no successor
		if (l.getRecord().getKey().compareTo(k) != 1) {
			return null;
		}
		else {
			// if node p with key k is not a leaf and its record is not null then its successor is the node with the smallest key in the right subtree
			if (!p.isLeaf() && p.getRightChild().getRecord() != null) {
				return smallest(p.getRightChild());
			}
			// in this branch p is a leaf so has no children
			else {
				p = p.getParent();
				// if p is the root then this loop will never execute, otherwise it will loop until the root is reached or the successor is found
				while (p != null && (p.getRecord().getKey().compareTo(k) == -1)){
					p = p.getParent();
				}
				return p;
			}
		}
	}
	/**
	 * method predecessor returns the predecessor BSTNode in inorder of the node with the key k of the tree with root r
	 * @param r
	 * @param k
	 * @return BSTNode with the preceding key of BSTNode with key k if it exists
	 * @return null if the given key k is the smallest in the tree
	 */
	public BSTNode predecessor(BSTNode r, Key k) {
		BSTNode p = get(r, k);
		BSTNode s = smallest(r);
		// if the smallest key in the tree is the same as k then there is no predecessor
		if (s.getRecord().getKey().compareTo(k) != -1) {
			return null;
		}
		else {
			// if node p with key k is not a leaf and its record is not null then its predecessor is the node with the largest key in the left subtree
			if (!p.isLeaf() && p.getLeftChild().getRecord() != null) {
				return largest(p.getLeftChild());
			}
			// in this branch p is a leaf
			else {
				p = p.getParent();
				// if p is the root then this loop will never execute, otherwise it will loop until the root is reached or the predecessor is found
				while (p != null && (p.getRecord().getKey().compareTo(k) == 1)){
					p = p.getParent();
				}
				return p;
			}
		}
	}
	/**
	 * method smallest finds the BSTNode object with the smallest key in the tree with root r. the node is a leaf or the parent of a leaf
	 * @param r
	 * @return BSTNode with smallest key in the tree
	 * @return null if smallest is called on a null leaf
	 */
	public BSTNode smallest(BSTNode r) {
		// this branch is taken if the given node r is a leaf
		if (r.isLeaf()) {
			// if r has no record then there are no keys in the tree so no node is smallest
			if (r.getRecord() == null) {
				return null;
			}
			// if r has a record but no children then it is the only node
			return r;
		}
		// in this branch r is an internal node
		else {
			BSTNode p = r;
			// find the leftmost node in the tree
			while (!p.isLeaf()) {
				p = p.getLeftChild();
			}
			// if the current node has no record then its parent node has the smallest key in the tree
			if (p.getRecord() == null) {
				return p.getParent();
			}
			// otherwise the current node is the smallest key
			else {
				return p;
			}
		}
	}
	/**
	 * method largest finds the BSTNode object with the smallest key in the tree with root r. the node is a leaf or the parent of a leaf
	 * @param r
	 * @return BSTNode with largest key in the tree
	 * @return null is largest is called on a null leaf
	 */
	public BSTNode largest (BSTNode r) {
		// this branch is taken if the given node r is a leaf
		if (r.isLeaf()) {
			// if r has no record then there are no keys in the tree so no node is smallest
			if (r.getRecord() == null) {
				return null;
			}
			// if r has a record but no children then it is the only node
			return r;
		}
		// in this branch r is an internal node
		else {
			BSTNode p = r;
			// find the rightmost node in the tree
			while (!p.isLeaf()) {
				p = p.getRightChild();
			}
			// if the current node has no record then its parent node has the largest key in the tree
			if (p.getRecord() == null) {
				return p.getParent();
			}
			// otherwise the current node is the largest key
			return p;
		}
	}
	/**
	 * Mutator method setRoot sets the root of the tree as the given BSTNode r
	 * @param r
	 */
	private void setRoot(BSTNode r) {
		this.root = r;
	}
}
