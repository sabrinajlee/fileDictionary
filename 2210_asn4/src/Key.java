/* Class Key
 * 	this class stores the key of a record. A key stores a label describing the type
 * 	of the object.
 * 
 * @author Sabrina Lee 251297577
 * CS2210A 001
 * Prof. Solis Oba
 * November 19, 2023
 */

public class Key {
	// INSTANCE VARIABLES
	private String label;
	private int type;

	/**
	 * Constructor initializes instance variables
	 * @param theLabel
	 * @param theType
	 */
	public Key(String theLabel, int theType) {
		this.label = theLabel.toLowerCase();
		this.type = theType;
	}
	/**
	 * Accessor method getLabel returns label variable
	 * @return label
	 */
	public String getLabel() {
		return this.label;
	}
	/**
	 * Accessor method getType returns type variable
	 * @return
	 */
	public int getType() {
		return this.type;
	}
	/**
	 * method compareTo compares two keys by first lexicographically comparing their labels
	 * 	and then comparing their types.
	 * @param k
	 * @return 0 if the keys are the same
	 * @return -1 if the first key is less than the second
	 * @return 1 otherwise
	 */
	public int compareTo(Key k) {
		int cmpLabel = this.label.compareTo(k.label);

		if (cmpLabel == 0) {
			if (this.type == k.type) {
				return 0;
			}
			else if (this.type < k.type) {
				return -1;
			}
			else return 1;
		}
		else if (cmpLabel < 0) {
			return -1;
		}
		return 1;
	}
}
