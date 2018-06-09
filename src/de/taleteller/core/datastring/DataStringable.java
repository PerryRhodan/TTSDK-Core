/**
 * www.taleteller.de
 * 
 * TaletellerCore
 *   DataStringable
 * 
 * Summary:
 *   
 * 
 * History:
 *   25.11.2017 - Cleaning of code
 *   
 * 
 * Ideas:
 *   
 * 
 * Stephan Hogrefe, Edinburgh, 2017
 */
package de.taleteller.core.datastring;

/**
 * 
 */
public interface DataStringable {

	/** Returns the data of this instance as string. */
	public String GetDataString();
	
	/** Loads the data given as string and updates this instance.
	 *  Returns whether or not this was successful. */
	public boolean LoadDataFromString(String data);
	
}
