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
public class TestD20Disadavantage extends Thread{
	Random _die;
	TestHeadD20 _head;
	int _diceToRoll;

	/**
	 * creates a runnable d20 roll test for standard advantage
	 */
	public TestD20Disadavantage(TestHeadD20 head) {
		this._die = new Random();
		this._head = head;
		this._diceToRoll = 2;
	}
	
	/**
	 * creates a runnable d20 roll test for standard advantage
	 */
	public TestD20Disadavantage(TestHeadD20 head, int dice) {
		this._die = new Random();
		this._head = head;
		this._diceToRoll = dice;
	}
	
	public void run(){
		int lowest = 21;
		for(int i = 0; i < _diceToRoll; i++){
			int number = (_die.nextInt(20) +1);
			if(number < lowest){
				lowest = number;
			}
		}
		try {
			if(this._head._accessResults.tryAcquire(100, TimeUnit.MILLISECONDS)){
				this._head._result.add(lowest);
				this._head._accessResults.release();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
