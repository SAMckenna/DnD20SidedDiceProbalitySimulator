/**
 * 
 */
package Dice;

/**
 * @author Steven
 * A class for making dice, with methods for making common die types.
 */
public class DieFactory {

	/**
	 * Creates a four sided die
	 * @return a die
	 */
	static public Die FourSidedDie(){
		return new Die(4, 1);
	}
	
	/**
	 * Creates a six sided die
	 * @return a die
	 */
	static public Die SixSidedDie(){
		return new Die(6, 1);
	}
	
	/**
	 * Creates a eight sided die
	 * @return a die
	 */
	static public Die EightSidedDie(){
		return new Die(8, 1);
	}
	
	/**
	 * Creates a ten sided die
	 * @return a die
	 */
	static public Die TenSidedDie(){
		return new Die(10, 1);
	}
	
	/**
	 * Creates a twelve sided die
	 * @return a die
	 */
	static public Die TwelveSidedDie(){
		return new Die(12, 1);
	}
	
	/**
	 * Creates a twenty sided die
	 * @return a die
	 */
	static public Die TwentySidedDie(){
		return new Die(20, 1);
	}
	
	/**
	 * Creates a hundred sided die
	 * @return a die
	 */
	static public Die HundredSidedDie(){
		return new Die(100, 1);
	}
	
	/**
	 * Creates a die with the given max value and min value
	 * @return a die
	 */
	static public Die CustomDie(double inMax, double inMin){
		return new Die(inMax, inMin);
	}

}
