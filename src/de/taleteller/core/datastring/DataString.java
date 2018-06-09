/**
 * www.taleteller.de
 * 
 * TaletellerCore
 *   DataString
 * 
 * Summary:
 *   
 * 
 * History:
 *   25.11.2017 - Cleaning of code
 *   
 * 
 * Ideas:
 *   - Default value when loading
 * 
 * Stephan Hogrefe, Edinburgh, 2017
 */
package de.taleteller.core.datastring;

import java.util.LinkedList;

import de.taleteller.core.logging.Log;

/**
 * 
 */
public class DataString {

	
	/** delimiter to split the key-value pairs */
	String delimiter;
	
	/** data in its key-value-pairs, without the delimiter yet */
	LinkedList<String> data;

	
	public DataString(String delimiter) {
		this.delimiter = delimiter;
		data = new LinkedList<>();
	}
	
	/**
	 * Reads in a datastring. This clears any currently hold data.
	 * @param datastring
	 */
	public void ReadDataString(String datastring) {
		String[] pieces = datastring.split(delimiter);
		data.clear();
		for (String piece : pieces) {
			data.add(piece);
		}
	}
	
	/**
	 * Adds new key - value  pair to the stored data.
	 * @param key
	 * @param data
	 */
	public void Add(String key, String data) {
		if(key == null || key.length() < 1)
			Log.ERROR("TaletellerSDK::DataString:: Added key is null or zero length!");
		if(data == null || data.length() < 1)
			Log.ERROR("TaletellerSDK::DataString:: Added data is null or zero length!");
		if(key.contains(":"))
			Log.ERROR("TaletellerSDK::DataString:: Added key contains ':'! This wont work!");
		
		this.data.add(key + ":" + data);
	}
	
	/**
	 * Returns all stored data as one data string.
	 * @return
	 */
	public String GetDataString() {
		String fulldatastring = "";
		int k = 0;
		for (String piece : data) {
			fulldatastring += piece;
			if(k < data.size() - 1)
				fulldatastring += delimiter;
			k++;
		}
		return fulldatastring;
	}
	
	/**
	 * Reads data field connected to given key.
	 * @param key
	 * @return
	 */
	public String Read(String key) {
		for (String piece : data) {
			String[] parts = piece.split(":", 2);
			String pre = parts[0];
			String suf = parts[1];
			if(pre.equalsIgnoreCase(key))
				return suf;
		}
		Log.WARN("TaletellerSDK::DataString:: Read key does not exist in this data string. Returning null!");
		return null;
	}
	
	/**
	 * Reads all data fields connected to given key.
	 * @param key
	 * @return
	 */
	public String[] ReadAll(String key) {
		LinkedList<String> sufs = new LinkedList<>();
		for (String piece : data) {
			String[] parts = piece.split(":", 2);
			String pre = parts[0];
			String suf = parts[1];
			if(pre.equalsIgnoreCase(key))
				sufs.add(suf);
		}
		
		return sufs.toArray(new String[0]);
	}
	
	/**
	 * Clears all stored data.
	 */
	public void Clear() {
		data.clear();
	}
	
}
