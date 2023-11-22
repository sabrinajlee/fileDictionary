/* Class Interface
 * 	this class represents the interface of the database. objects of BSTNode are created with data from a .txt file
 * 	and inserted into a BSTDictionary object. the program continually prompts the user to input a command to perform
 * 	some action by retrieving or removing data from the dictionary. 
 * 
 * @author Sabrina Lee 251297577
 * CS2210A 001
 * Prof. Solis Oba
 * November 19, 2023
 */

import java.io.*;
public class Interface {
	public static void main(String[] args) {
		// initialize the dictionary
	    BSTDictionary dict = new BSTDictionary();
	    // try opening a file and reading in lines
		try {
		    BufferedReader in = new BufferedReader(new FileReader(args[0]));
		    String line = in.readLine().toLowerCase();
		    String label;
		    String data;
		    int type = 0;
		    char first;
		    Key k;
		    Record r;
		    // read lines until end of file
		    while (line != null) {
		    	label = line.toLowerCase();	// set label as the first line read
		    	line = in.readLine();
		    	data = line;				// set data as the second line read
		    	first = data.charAt(0);
		    	// set type based on the first character of the data
		    	switch (first) {
		    	case '-':
		    		type = 3;	//  In this case data is the name of a sound file
		    		break;
		    	case '+':
		    		type = 4;	//  In this case data is the name of a music file
		    		break;
		    	case '*':
		    		type = 5;	//  In this case data is the name of a voice file
		    		break;
		    	case '/':
		    		type = 2;	//  In this case data is a translation of label to French
		    		break;
		    	default:
		    		if (data.endsWith(".gif")) {
		    			type = 7;	// In this case data is the name of an animated image file
		    		}
		    		else if (data.endsWith(".jpg")) {
		    			type = 6;	// In this case data is the name of an image file
		    		}
		    		else if (data.endsWith(".html")) {
		    			type = 8;	//  In this case data is the URL of a webpage
		    		}
		    		else {
		    			type = 1;	//  In this case data is a string containing a definition of label
		    		}
		    	}
		    	// initialize the attributes of the record of the new entry of the dictionary
		    	k = new Key(label, type);
		    	r = new Record(k, data);
		    	// try adding a new entry into the dictionary
		    	try {
		    		dict.put(r);
		    	}
		    	catch (DictionaryException e) {
		    		System.out.println("Error: Failed to enter record to dictionary.");
		    	}
		    	line = in.readLine();	// move to next line before continuing to next loop
		    }
		}
		catch (IOException e) {
		    System.out.println("Cannot open file");
		}
		// infinite loop to continually prompt user for input
		while (true) {
			StringReader keyboard = new StringReader();
			String cmd = keyboard.read("Enter next command: ");
			String[] command = cmd.split(" ");
			// print the definition of a word if it exists in the dictionary
			if (command[0].equals("define") && command.length == 2) {
				String lbl = command[1];
				Key k = new Key(lbl, 1);
				Record d = dict.get(k);
				if (d != null) {
					System.out.println(d.getDataItem());
				}
				else {
					System.out.println("The word " + lbl + " is not in the ordered dictionary");
				}
			}
			// print the translation of a word is it exists in the dictionary
			else if (command[0].equals("translate") && command.length == 2) {
				String lbl = command[1];
				Key k = new Key(lbl, 2);
				Record d = dict.get(k);
				if (d != null) {
					System.out.println(d.getDataItem().substring(1));
				}
				else {
					System.out.println("There is no definition for the word " + lbl);
				}
			}
			// play a sound file of some sound if it exists in the dictionary
			else if (command[0].equals("sound") && command.length == 2) {
				String lbl = command[1];
				Key k = new Key(lbl, 3);
				Record d = dict.get(k);
				if (d != null) {
					try {
						String fileName = d.getDataItem().substring(1);
						SoundPlayer sound = new SoundPlayer();
						sound.play(fileName);
					} catch (MultimediaException e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.println("There is no sound file for " + lbl);
				}
			}
			// play a sound file of music if it exists in the dictionary
			else if (command[0].equals("play") && command.length == 2) {
				String lbl = command[1];
				Key k = new Key(lbl, 4);
				Record d = dict.get(k);
				if (d != null) {
					try {
						String fileName = d.getDataItem().substring(1);
						SoundPlayer sound = new SoundPlayer();
						sound.play(fileName);
					} catch (MultimediaException e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.println("There is no music file for " + lbl);
				}
			}
			// play a sound file of a voice if it exists in the dictionary
			else if (command[0].equals("say") && command.length == 2) {
				String lbl = command[1];
				Key k = new Key(lbl, 5);
				Record d = dict.get(k);
				if (d != null) {
					try {
						String fileName = d.getDataItem().substring(1);
						SoundPlayer sound = new SoundPlayer();
						sound.play(fileName);
					} catch (MultimediaException e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.println("There is no voice file for " + lbl);
				}
			}
			// show a still image if it is found in the dictionary
			else if (command[0].equals("show") && command.length == 2) {
				String lbl = command[1];
				Key k = new Key(lbl, 6);
				Record d = dict.get(k);
				if (d != null) {
					try {
						String fileName = d.getDataItem();
						PictureViewer picture = new PictureViewer();
					    picture.show(fileName);
					} catch (MultimediaException e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.println("There is no image file for " + lbl);
				}
			}
			// show a gif if it is found in the dictionary
			else if (command[0].equals("animate") && command.length == 2) {
				String lbl = command[1];
				Key k = new Key(lbl, 7);
				Record d = dict.get(k);
				if (d != null) {
					try {
						String fileName = d.getDataItem();
						PictureViewer picture = new PictureViewer();
					    picture.show(fileName);
					} catch (MultimediaException e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.println("There is no animated image file for " + lbl);
				}
			}
			// show a webpage if it is found in the dictionary
			else if (command[0].equals("browse") && command.length == 2) {
				String lbl = command[1];
				Key k = new Key(lbl, 8);
				Record d = dict.get(k);
				if (d != null) {
					try {
						String url = d.getDataItem();
						ShowHTML html = new ShowHTML();
					    html.show(url);
					} catch (MultimediaException e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.println("There is no webpage called " + lbl);
				}
			}
			// remove an entry from the dictionary if it exists
			else if (command[0].equals("delete") && command.length == 3) {
				String lbl = command[1];
				int t = Integer.parseInt(command[2]);
				Key k = new Key(lbl, t);
				try {
					dict.remove(k);
				}
				catch (DictionaryException e) {
					System.out.println("No record in the ordered dictionary has key (" + lbl + "," + t + ").");
				}
			}
			// add an entry into the dictionary if it does not exist
			else if (command[0].equals("add") && command.length == 4) {
				String lbl = command[1];
				int t = Integer.parseInt(command[2]);
				Key k = new Key(lbl, t);
				String data = command[3];
				Record d = new Record(k, data);
				try {
					dict.put(d);
				}
				catch (DictionaryException e) {
					System.out.println("A record with the given key (" + lbl + "," + t + ") is already in the ordered dictionary.");
		
				}
			}
			// print the labels of the items in the dictionary that start with the given string or have the same name as that string
			else if (command[0].equals("list") && command.length == 2) {
				String prefix = command[1];
				Key k = new Key(prefix, 1);	
				Record p = dict.get(k);		// find the first instance of a record with the label prefix
				boolean found = false;
				if (p != null && p.getKey().getLabel().equals(prefix)) {
					System.out.print(prefix);
					found = true;
				}
				Record succ = dict.successor(k);
				// continue down the right subtree until there is no successor or all matching keys have been found
				while (succ != null) {
					if (succ.getKey().getLabel().startsWith(prefix)) {
						if (found == true) {
							System.out.print(", ");
						}
						System.out.print(succ.getKey().getLabel());
						succ = dict.successor(succ.getKey());
						found = true;
					}
					else {
						break;
					}
				}
				if (found == false) {
					System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
				}
				else {
					System.out.println();
				}
			}
			// print the attributes of the item in the dictionary with the smallest key
			else if (command[0].equals("first") && command.length == 1) {
				Record s = dict.smallest();
				String lbl = s.getKey().getLabel();
				int t = s.getKey().getType();
				String d = s.getDataItem();
				System.out.println(lbl + ", " + t + ", " + d);
			}
			// print the attributes of the item in the dictionary with the largest key
			else if (command[0].equals("last") && command.length == 1) {
				Record l = dict.largest();
				String lbl = l.getKey().getLabel();
				int t = l.getKey().getType();
				String d = l.getDataItem();
				System.out.println(lbl + ", " + t + ", " + d);
			}
			// exit the program
			else if (command[0].equals("exit") && command.length == 1) {
				return;
			}
			// wrong or non existent command
			else {
				System.out.println("Invalid command.");
			}
			
			
		}
		
	}
}
