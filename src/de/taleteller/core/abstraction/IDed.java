/**
 * www.taleteller.de
 * 
 * TaletellerCore
 *   IDed
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
package de.taleteller.core.abstraction;

/**
 *
 */
public abstract class IDed {

	/** current highest id for all IDed instances */
	private static int HIGHEST_ID = -1;
	
	/** Id of this instance */
	private int id;
	
	/** Constructor, sets the current
	 *  highest id + 1 as id. */
	public IDed() {
		HIGHEST_ID += 1;
		this.id = HIGHEST_ID;
	}
	
	/**
	 * Constructor, sets given id.
	 * If given id is > then the current highest id,
	 * the highest id is updated accordingly.
	 * If the given id is >= 0, but smaller than the 
	 * current highest id, a warning will be output,
	 * but the id is set anyway. 
	 * @param in_id
	 */
	public IDed(int in_id) {
		if(in_id > HIGHEST_ID) {
			HIGHEST_ID = in_id;
			this.id = HIGHEST_ID;
		} else if(in_id < 0) {
			// just a dummy object, which has only an invalid id
			this.id = -1;
		} else {
			this.id = in_id;
			System.out.println("IDed:: WARNING! GIVEN ID MIGHT ALREADY BE IN USE! ("
									+ this.getClass().getName() + ")");
		}
	}
	
	/**
	 * Sets given id.
	 * If given id is > then the current highest id,
	 * the highest id is updated accordingly.
	 * If the given id is >= 0, but smaller than the 
	 * current highest id, a warning will be output,
	 * but the id is set anyway. 
	 * @param in_id
	 */
	public void SetId(int in_id) {
		if(in_id > HIGHEST_ID) {
			HIGHEST_ID = in_id;
			this.id = HIGHEST_ID;
		} else if(in_id < 0) {
			// just a dummy object, which has only an invalid id
			this.id = -1;
		} else {
			this.id = in_id;
			System.out.println("IDed:: WARNING! GIVEN ID MIGHT ALREADY BE IN USE! ("
									+ this.getClass().getName() + ")");
		}
	}

	/** Returns id of this instance. */
	public int getId() {
		return id;
	}
	
	/** ######################################################## */
	
	@Override
	public String toString() {
		return "" + id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDed other = (IDed) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
