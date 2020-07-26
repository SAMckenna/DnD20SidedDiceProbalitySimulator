/**
 * 
 */
package Dice;

import java.util.Random;

/**
 * @author Steven
 * A class to define the functions and characteristics of a die
 */
public class Die {
	
	private double _maxValue; /** The highest value the die can roll*/
	private double _minValue; /** The lowest value the die can roll*/
	private Random _die = new Random();
	/**
	 * Creates a default die with the highest value of 1 and lowest value of 0; 
	 */
	public Die() {
		this._maxValue = 1;
		this._minValue = 0;
	}

	/**
	 * Creates a die from the given values, such that the highest value of the die is the highest given value and the lowest value of the die
	 * is the lowest value given. If the given values are equal, the highest value will be 1 greater than the given value.
	 * @param maxValue, the highest value the die should be able to output
	 * @param minValue, the lowest value the die should be able to output
	 */
	public Die(int inMaxValue,  int inMinValue){
		double[] validMinMax = this.ValidateMaxMinValue(inMaxValue, inMinValue);
		this._maxValue = validMinMax[1];
		this._minValue = validMinMax[0];
	}
	
	/**
	 * Creates a die from the given values, such that the highest value of the die is the highest given value and the lowest value of the die
	 * is the lowest value given. If the given values are equal, the highest value will be 1 greater than the given value.
	 * @param maxValue, the highest value the die should be able to output
	 * @param minValue, the lowest value the die should be able to output
	 */
	public Die(double inMaxValue,  double inMinValue){
		double[] validMinMax = this.ValidateMaxMinValue(inMaxValue, inMinValue);
		this._maxValue = validMinMax[1];
		this._minValue = validMinMax[0];
	}
	
	/**
	 * Create a deep copy of another die, such that alterations to either will not affect the other.
	 * @param die, die to be copied
	 */
	public Die(Die die){
		this._maxValue = die.get_maxValue();
		this._minValue = die.get_minValue();
	}
	
	/**
	 * Gives the highest value the die can currently roll;	
	 * @return _maxValue for this die
	 */
	public double get_maxValue() {
		return _maxValue;
	}

	/**
	 * Changes the highest value the die can roll to the given one, if it's higher than the current lowest value the die can roll.
	 * @param inMaxValue, the value to change the highest value of the die to
	 */
	public void set_maxValue(double inMaxValue) {
		if(this.ValidMax(inMaxValue)){
			this._maxValue = inMaxValue;
		}
	}

	/**
	 * Gives the lowest value the die can currently roll;	
	 * @return _minValue for this die
	 */
	public double get_minValue() {
		return _minValue;
	}

	/**
	 * Changes the lowest value the die can roll to the given one, if it's lower than the current highest value the die can roll.
	 * @param inMaxValue, the value to change the lowest value of the die to
	 */
	public void set_minValue(double inMinValue) {
		if(this.ValidMin(inMinValue)){
			this._minValue = inMinValue;
		}
	}
	
	/**
	 * Rolls the die and returns an integer within the dies limits.
	 * @return a random integer within the dies limits
	 */
	public int RollInt(){
		int negativeMaxOffset;
		if(this._maxValue < 0){
			negativeMaxOffset = (int)((-1 * this._minValue) - (-1 * this._maxValue));
			return (_die.nextInt(negativeMaxOffset +1) + (int)this._minValue);
		}
		else{
			return ((_die.nextInt((int)(this._maxValue - this._minValue) + 1)) + (int)this._minValue);
		}
	}
	
	/**
	 * Rolls the die and returns an double within the dies limits.
	 * @return a random double within the dies limits
	 */
	public double RollDouble(){
		return (((this._maxValue - this._minValue) * _die.nextDouble()) + this._minValue);
	}

	/**
	 * Checks that the given max value is greater than the given min value, but if this isn't so it will attempt to correct this
	 * @param maxValue, the highest value the die can roll
	 * @param minValue, the lowest value the die can roll
	 * @return an array with the lowest value in position 0 and the highest value in position 1;
	 */
	private double[] ValidateMaxMinValue(double maxValue,  double minValue){
		double[] result = {minValue, maxValue};
		if(maxValue < minValue){
			result[0] = maxValue;
			result[1] = minValue;
		}
		//if the given values are equal, raise make by 1;
		else if(maxValue == minValue){
			result[0] = minValue;
			result[1] = maxValue + 1;
		}
		return result;
	}
	
	/**
	 * Checks if the given value is greater than the current min value of the die and returns true if it is.
	 * @param value, the value to compared against the current min value
	 * @return true if the given value is greater than the current min value;
	 */
	private boolean ValidMax(double value){
		return value > this._minValue;
	}
	
	/**
	 * Checks if the given value is less than the current max value of the die and returns true if it is.
	 * @param value, the value to compared against the current max value
	 * @return true if the given value is greater than the current max value;
	 */
	private boolean ValidMin(double value){
		return value < this._maxValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(_maxValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(_minValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Die)) {
			return false;
		}
		Die other = (Die) obj;
		if (Double.doubleToLongBits(_maxValue) != Double.doubleToLongBits(other._maxValue)) {
			return false;
		}
		if (Double.doubleToLongBits(_minValue) != Double.doubleToLongBits(other._minValue)) {
			return false;
		}
		return true;
	}
	
	
	
}
