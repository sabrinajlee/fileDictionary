/* Class BSTDictionary
 * 	this class represents a dictionary that uses the BinarySearchClass as a data structure
 * 	to represent its data in a binary search tree
 * 
 * @author Sabrina Lee 251297577
 * CS2210A 001
 * Prof. Solis Oba
 * November 19, 2023
 */

public class BSTDictionary implements BSTDictionaryADT {
	private BinarySearchTree dict;
	private BSTNode root;

	public BSTDictionary() {
		this.dict = new BinarySearchTree();
		this.root = dict.getRoot();
	}
	
	@Override
	public Record get(Key k) {
		return dict.get(root, k).getRecord();
	}

	@Override
	public void put(Record d) throws DictionaryException {
		dict.insert(root, d);
	}

	@Override
	public void remove(Key k) throws DictionaryException {
		dict.remove(root, k);
	}

	@Override
	public Record successor(Key k) {
		BSTNode succ = dict.successor(root, k);
		if (succ == null) {
			return null;
		}
		else {
			return succ.getRecord();
		}
	}

	@Override
	public Record predecessor(Key k) {
		BSTNode pred = dict.predecessor(root, k);
		if (pred == null) {
			return null;
		}
		else {
			return pred.getRecord();
		}
	}

	@Override
	public Record smallest() {
		return dict.smallest(root).getRecord();
	}

	@Override
	public Record largest() {
		return dict.largest(root).getRecord();
	}

}
