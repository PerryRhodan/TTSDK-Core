/**
 * www.taleteller.de
 * 
 * TaletellerCore
 *   DefaultTypeValues
 * 
 * Summary:
 *   
 * 
 * History:
 *   12.06.2018 - Initial implementation
 *   
 * 
 * Ideas:
 * 
 * Stephan Hogrefe, Edinburgh, 2017
 */
package de.taleteller.core.datastring;

public class DefaultTypeValues {

	private boolean default_value_boolean;
	private short default_value_short;
	private int default_value_int;
	private long default_value_long;
	private float default_value_float;
	private double default_value_double;
	
	///////////////////////////////////////////////
	
	public DefaultTypeValues() {
		default_value_boolean = false;
		default_value_short = 0;
		default_value_int = 0;
		default_value_long = 0;
		default_value_float = 0.0f;
		default_value_double = 0.0;
	}
	
	///////////////////////////////////////////////
	
	public boolean getDefault_value_boolean() {
		return default_value_boolean;
	}

	public short getDefault_value_short() {
		return default_value_short;
	}

	public int getDefault_value_int() {
		return default_value_int;
	}

	public long getDefault_value_long() {
		return default_value_long;
	}

	public float getDefault_value_float() {
		return default_value_float;
	}

	public double getDefault_value_double() {
		return default_value_double;
	}

	public void setDefault_value_boolean(boolean default_value_boolean) {
		this.default_value_boolean = default_value_boolean;
	}

	public void setDefault_value_short(short default_value_short) {
		this.default_value_short = default_value_short;
	}

	public void setDefault_value_int(int default_value_int) {
		this.default_value_int = default_value_int;
	}

	public void setDefault_value_long(long default_value_long) {
		this.default_value_long = default_value_long;
	}

	public void setDefault_value_float(float default_value_float) {
		this.default_value_float = default_value_float;
	}

	public void setDefault_value_double(double default_value_double) {
		this.default_value_double = default_value_double;
	}

}
