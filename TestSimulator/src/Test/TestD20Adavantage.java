/**
 * 
 */
package Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Steven
 *
 */
public class TestD20Adavantage extends Thread{
	Random _die;
	TestHeadD20 _head;
	int _diceToRoll;

	/**
	 * creates a runnable d20 roll test for standard advantage
	 */
	public TestD20Adavantage(TestHeadD20 head) {
		this._die = new Random();
		this._head = head;
		this._diceToRoll = 2;
	}
	
	/**
	 * creates a runnable d20 roll test for standard advantage
	 */
	public TestD20Adavantage(TestHeadD20 head, int dice) {
		this._die = new Random();
		this._head = head;
		this._diceToRoll = dice;
	}
	
	public void run(){
		int highest = 0;
		for(int i = 0; i < _diceToRoll; i++){
			int number = (_die.nextInt(20) +1);
			if(number > highest){
				highest = number;
			}
		}
		try {
			if(this._head._accessResults.tryAcquire(100, TimeUnit.MILLISECONDS)){
				this._head._result.add(highest);
				this._head._accessResults.release();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
