/**
 * 
 */

package Simulator;

import Test.TestHeadD20;

/**
 * @author Steven
 *
 */
public class Simulation {

	/**
	 * 
	 */
	public Simulation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestHeadD20 test = new TestHeadD20();
		System.out.println("Testing Highest roll on 1 die");
		test.ExcuteD20Adavantage(100000, 1);
		System.out.println("\nTesting Highest roll on 2 die");
		test.ExcuteD20Adavantage(2);
		System.out.println("\nTesting Highest roll on 3 die");
		test.ExcuteD20Adavantage(3);
		System.out.println("\nTesting Highest roll on 4 die");
		test.ExcuteD20Adavantage(4);
		System.out.println("\nTesting Lowest roll on 2 die");
		test.ExcuteD20Disadavantage(2);
	}

}
