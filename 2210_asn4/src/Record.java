/* Class Record 
 * 	this class stores the record of a BSTNode. A record can be null or
 * 	store data and a key.
 * 
 * @author Sabrina Lee 251297577
 * CS2210A 001
 * Prof. Solis Oba
 * November 19, 2023
 */
public class Record {
	// INSTANCE VARIABLES
	private Key theKey;
	private String data;
	
	/**
	 * Constructor initializes instance variables
	 * @param k
	 * @param theData
	 */
	public Record(Key k, String theData) {
		this.theKey = k;
		this.data = theData;
	}
	/**
	 * Accessor method getKey returns key variable
	 * @return theKey
	 */
	public Key getKey() {
		 return this.theKey;
	}
	 /**
	  * Accessor method getDataItem returns data variable
	  * @return data
	  */
	public String getDataItem() {
		 return this.data;
	}
}
