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
 *   12.06.2018 - Added default values and direct type
 *   			  reading. Renamed ReadDataString(...)
 *   			  to ProcessDataString(...). Also added
 * 				  a few convenience methods.
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

	/** default values for the supported types*/
	DefaultTypeValues default_values;
	
	
	/////////////////////////////////////////////////////////////////
	
	public DataString(String delimiter) {
		this.delimiter = delimiter;
		data = new LinkedList<>();
		default_values = new DefaultTypeValues();
	}
	
	/////////////////////////////////////////////////////////////////
	
	/**
	 * Reads in a datastring. This clears any currently hold data.
	 * @param datastring - string containing data of correct format
	 */
	public void ProcessDataString(String datastring) {
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
	 * Adds new key - value  pair to the stored data.
	 * @param key
	 * @param data
	 */
	public void Add(String key, short data) {
		Add(key, "" + data);
	}
	
	/**
	 * Adds new key - value  pair to the stored data.
	 * @param key
	 * @param data
	 */
	public void Add(String key, int data) {
		Add(key, "" + data);
	}
	
	/**
	 * Adds new key - value  pair to the stored data.
	 * @param key
	 * @param data
	 */
	public void Add(String key, long data) {
		Add(key, "" + data);
	}
	
	/**
	 * Adds new key - value  pair to the stored data.
	 * @param key
	 * @param data
	 */
	public void Add(String key, boolean data) {
		Add(key, "" + data);
	}
	
	/////////////////////////////////////////////////////////////////
	
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
	 * Clears all stored data.
	 */
	public void Clear() {
		data.clear();
	}
	
	/////////////////////////////////////////////////////////////////
	
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
		Log.WARN("TaletellerSDK::DataString:: Read key does not exist"
				+ " in this data string. Returning null!"
				+ " (key: " + key + ")");
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
	
	//////////////////////////////
	
	/**
	 * Reads data field connected to given key.
	 * Note that the value must be written as
	 * "true" or "false". "1" or "0" wont work!
	 * @param key
	 * @return
	 */
	public boolean ReadBoolean(String key) {
		for (String piece : data) {
			String[] parts = piece.split(":", 2);
			String pre = parts[0];
			String suf = parts[1];
			if(pre.equalsIgnoreCase(key)) {
				try {
					return Boolean.parseBoolean(suf);
				} catch (NumberFormatException e) {
					Log.ERROR("TaletellerCore::DataString:: ReadShort "
							+ "on incorrect type! "
							+ "(key: " + key + ", value: " + suf + ")");
					return default_values.getDefault_value_boolean();
				}
			}	
		}
		Log.WARN("TaletellerCore::DataString:: Read key does not exist"
				+ " in this data string. Returning default!"
				+ " (key: " + key + ")");
		return default_values.getDefault_value_boolean();
	}
	
	//////////////////////////////
	
	/**
	 * Reads data field connected to given key.
	 * @param key
	 * @return
	 */
	public short ReadShort(String key) {
		for (String piece : data) {
			String[] parts = piece.split(":", 2);
			String pre = parts[0];
			String suf = parts[1];
			if(pre.equalsIgnoreCase(key)) {
				try {
					return Short.parseShort(suf);
				} catch (NumberFormatException e) {
					Log.ERROR("TaletellerCore::DataString:: ReadShort "
							+ "on incorrect type! "
							+ "(key: " + key + ", value: " + suf + ")");
					return default_values.getDefault_value_short();
				}
			}	
		}
		Log.WARN("TaletellerCore::DataString:: Read key does not exist"
				+ " in this data string. Returning default!"
				+ " (key: " + key + ")");
		return default_values.getDefault_value_short();
	}
	
	/**
	 * Reads data field connected to given key.
	 * @param key
	 * @return
	 */
	public int ReadInt(String key) {
		for (String piece : data) {
			String[] parts = piece.split(":", 2);
			String pre = parts[0];
			String suf = parts[1];
			if(pre.equalsIgnoreCase(key)) {
				try {
					return Integer.parseInt(suf);
				} catch (NumberFormatException e) {
					Log.ERROR("TaletellerCore::DataString:: ReadInt "
							+ "on incorrect type! "
							+ "(key: " + key + ", value: " + suf + ")");
					return default_values.getDefault_value_int();
				}
			}	
		}
		Log.WARN("TaletellerCore::DataString:: Read key does not exist"
				+ " in this data string. Returning default!"
				+ " (key: " + key + ")");
		return default_values.getDefault_value_int();
	}
	
	/**
	 * Reads data field connected to given key.
	 * @param key
	 * @return
	 */
	public long ReadLong(String key) {
		for (String piece : data) {
			String[] parts = piece.split(":", 2);
			String pre = parts[0];
			String suf = parts[1];
			if(pre.equalsIgnoreCase(key)) {
				try {
					return Long.parseLong(suf);
				} catch (NumberFormatException e) {
					Log.ERROR("TaletellerCore::DataString:: ReadLong "
							+ "on incorrect type! "
							+ "(key: " + key + ", value: " + suf + ")");
					return default_values.getDefault_value_long();
				}
			}	
		}
		Log.WARN("TaletellerCore::DataString:: Read key does not exist"
				+ " in this data string. Returning default!"
				+ " (key: " + key + ")");
		return default_values.getDefault_value_long();
	}
	
	//////////////////////////////
	
	/**
	 * Reads data field connected to given key.
	 * @param key
	 * @return
	 */
	public float ReadFloat(String key) {
		for (String piece : data) {
			String[] parts = piece.split(":", 2);
			String pre = parts[0];
			String suf = parts[1];
			if(pre.equalsIgnoreCase(key)) {
				try {
					return Float.parseFloat(suf);
				} catch (NumberFormatException e) {
					Log.ERROR("TaletellerCore::DataString:: ReadLong "
							+ "on incorrect type! "
							+ "(key: " + key + ", value: " + suf + ")");
					return default_values.getDefault_value_float();
				}
			}	
		}
		Log.WARN("TaletellerCore::DataString:: Read key does not exist"
				+ " in this data string. Returning default!"
				+ " (key: " + key + ")");
		return default_values.getDefault_value_float();
	}
	
	/**
	 * Reads data field connected to given key.
	 * @param key
	 * @return
	 */
	public double ReadDouble(String key) {
		for (String piece : data) {
			String[] parts = piece.split(":", 2);
			String pre = parts[0];
			String suf = parts[1];
			if(pre.equalsIgnoreCase(key)) {
				try {
					return Double.parseDouble(suf);
				} catch (NumberFormatException e) {
					Log.ERROR("TaletellerCore::DataString:: ReadLong "
							+ "on incorrect type! "
							+ "(key: " + key + ", value: " + suf + ")");
					return default_values.getDefault_value_double();
				}
			}	
		}
		Log.WARN("TaletellerCore::DataString:: Read key does not exist"
				+ " in this data string. Returning default!"
				+ " (key: " + key + ")");
		return default_values.getDefault_value_float();
	}

	/////////////////////////////////////////////////////
	
	public void setDefault_values(DefaultTypeValues default_values) {
		this.default_values = default_values;
	}
	
}
