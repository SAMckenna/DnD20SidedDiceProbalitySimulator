/**
 * 
 */
package Test;

import Dice.Die;
import Dice.DieFactory;

/**
 * @author Steven
 * A class for concurrently generating a random die roll, in which the lowest value rolled is the result, and sending it back to TestHeadD20.
 */
public class TestWorkerD20Disadavantage extends Thread{
	private Die _die; /** the die that will be rolled by this worker */
	private TestHeadD20 _head; /** the TestHeadD20 which this object will send it's roll back to*/
	private int _diceToRoll; /** how many times to generate a random number before choosing the highest as the result */

	/**
	 * creates a runnable d20 roll test for standard disadvantage
	 * @param head, the TestHeadD20 which this class should send it's roll back to
	 */
	public TestWorkerD20Disadavantage(TestHeadD20 head) {
		this._die = DieFactory.TwentySidedDie();
		this._head = head;
		this._diceToRoll = 2;
	}
	
	/**
	 * creates a runnable d20 roll test for non standard disadvantage
	 * @param head, the TestHeadD20 which this class should send it's roll back to
	 * @param dice, how many times the die should be rolled before taking the lowest value achieved
	 */
	public TestWorkerD20Disadavantage(TestHeadD20 head, int dice) {
		this._die = DieFactory.TwentySidedDie();
		this._head = head;
		this._diceToRoll = dice;
	}
	
	/**
	 * Generate the dice roll and send it to the TestHeadD20
	 */
	public void run(){
		int lowest = 21;
		for(int i = 0; i < _diceToRoll; i++){
			int number = (this._die.RollInt());
			if(number < lowest){
				lowest = number;
			}
		}
		this._head.AddSample(lowest);
	}
	

}
