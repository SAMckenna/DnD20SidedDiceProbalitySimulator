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
		TestHeadD20 test2 = new TestHeadD20();
		TestHeadD20 test3 = new TestHeadD20();
		TestHeadD20 test4 = new TestHeadD20();
		TestHeadD20 test5 = new TestHeadD20();
		System.out.println("Testing Highest roll on 1 die");
		test.ExcuteD20Adavantage(100000, 1);
		System.out.println("\nTesting Highest roll on 2 die");
		test2.ExcuteD20Adavantage(100000, 2);
		System.out.println("\nTesting Highest roll on 3 die");
		test3.ExcuteD20Adavantage(100000, 3);
		System.out.println("\nTesting Highest roll on 4 die");
		test4.ExcuteD20Adavantage(100000, 4);
		System.out.println("\nTesting Lowest roll on 2 die");
		test5.ExcuteD20Disadavantage(100000, 2);
	}

}
