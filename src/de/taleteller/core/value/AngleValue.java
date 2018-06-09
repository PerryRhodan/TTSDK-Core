/**
 * www.taleteller.de
 * 
 * TaletellerCore
 *   AngleValue
 * 
 * Summary:
 *   
 * 
 * History:
 *   25.11.2017 - Cleaning of code
 *   09.06.2018 - Adding add and subtract functions
 * 
 * Ideas:
 *   
 * 
 * Stephan Hogrefe, Edinburgh, 2018
 */
package de.taleteller.core.value;

/**
 * Holds angle values from [0, 360], where 0 is the same as 360.
 * 
 */
public class AngleValue {
	public static final double toDEG = 180.0/Math.PI;
	public static final double toRAD = Math.PI/180.0;
	
	/** current value in degrees */
	double degrees;
	
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	public AngleValue() {
		degrees = 0.0;
	}
	
	public AngleValue(double _degrees) {
		this.degrees = _degrees;
		limitTo360();
	}
	
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	public void setDegrees(double value) {
		this.degrees = value;
		limitTo360();
	}
	
	public void setRads(double value) {
		this.degrees = value * toDEG;
		limitTo360();
	}
	
	public void add(AngleValue other) {
		this.degrees += other.degrees;
		limitTo360();
	}
	
	public void subtract(AngleValue other) {
		this.degrees -= other.degrees;
		limitTo360();
	}
	
	/**
	 * Returns the smaller angle between this angle
	 * and the given. Always returns a positive value!
	 * @param other
	 * @return
	 */
	public AngleValue getSmallerAngleTo(AngleValue other) {
		double difference = other.degrees - this.degrees;
		difference += 180.0;
		if(difference >= 360.0)
			difference -= 360.0;
		difference -= 180.0;

		// return absolute value for the angle,
		// as I here do not consider the direction
		// of the angle or anything of that sort
		return new AngleValue(Math.abs(difference));
	}
	
	/**
	 * Returns the larger angle between this angle
	 * and the given. Always returns a positive value!
	 * @param other
	 * @return
	 */
	public AngleValue getLargerAngleTo(AngleValue other) {
		AngleValue diff_angle = getSmallerAngleTo(other);
		diff_angle.setDegrees(360.0 - diff_angle.getDegrees());
		
		return diff_angle;
	}
	
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	private double limitTo360() {
		while(this.degrees < 0)
			this.degrees += 360.0;
		while(this.degrees > 360.0)
			this.degrees -= 360.0;
		return this.degrees;
	}
	
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	public double getDegrees() {
		return degrees;
	}
	
	public double getRads() {
		return degrees * toRAD;
	}
	
	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(degrees);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		AngleValue other = (AngleValue) obj;
		if (Double.doubleToLongBits(degrees) != Double.doubleToLongBits(other.degrees))
			return false;
		return true;
	}

}
