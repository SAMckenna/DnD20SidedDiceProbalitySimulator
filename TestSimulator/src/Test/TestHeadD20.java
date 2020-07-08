/**
 * 
 */

package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;

/**
 * @author Steven
 *
 */
public class TestHeadD20{
	ArrayList<Integer> _result = new ArrayList<>(); //holds the results of any testing
	Semaphore _accessResults = new Semaphore(1); //must be acquired to modify the results table
	private int _sampleSize; //sample size

	/**
	 * creates a default test
	 */
	public TestHeadD20() {
		this._sampleSize = 1;
	}
	
	/**
	 * Executes a 20 sided dice roll simulation, where the die is rolled twice and the higher value is added to the results. Then attempts to display the results.
	 * @param sampleSize
	 */
	public void ExcuteD20Adavantage(int sampleSize){
		this._sampleSize = sampleSize;
		int i = 0;
		for(;i < this._sampleSize; i++){
			Thread test = new TestD20Adavantage(this);
			test.start();
		}
		do{
			Thread test = new TestD20Adavantage(this, 2);
			test.start();
		}
		while(_result.size() < this._sampleSize);
		try {
			this._accessResults.acquire();
			DisplayResults();
			this._accessResults.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Executes a 20 sided dice roll simulation, where the die is rolled a number of times equal to diceToRoll and the higher value is added to the results. Then attempts to display the results.
	 * @param sampleSize the number of independent dice rolls required in results for this test
	 * @param diceToRoll the number of dice to roll before taking the highest
	 */
	public void ExcuteD20Adavantage(int sampleSize, int diceToRoll){
		this._sampleSize = sampleSize;
		do{
			Thread test = new TestD20Adavantage(this, diceToRoll);
			test.start();
		}
		while(_result.size() < this._sampleSize);
		try {
			this._accessResults.acquire();
			DisplayResults();
			this._accessResults.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Executes a 20 sided dice roll simulation, where the die is rolled a number of times equal to diceToRoll and the lowest value is added to the results. Then attempts to display the results.
	 * @param sampleSize the number of independent dice rolls required in results for this test
	 * @param diceToRoll the number of dice to roll before taking the highest
	 */
	public void ExcuteD20Disadavantage(int sampleSize, int diceToRoll){
		this._sampleSize = sampleSize;
		do{
			Thread test = new TestD20Disadavantage(this, diceToRoll);
			test.start();
		}
		while(_result.size() < this._sampleSize);
		try {
			this._accessResults.acquire();
			DisplayResults();
			this._accessResults.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Calculates how often a particular value appeared in the test, along with median and mode. It then displays all this to console.
	 */
	public void DisplayResults(){
		Collections.sort(this._result);
		int sampleSize = this._result.size();
		double[] occurances = new double[20];
		int median = 0;
		int mode = 0;
		int sumOfOccurances = 0;		
		for(int num : _result){
			occurances[(num-1)] += 1;
			sumOfOccurances += num;
		}
		System.out.println("Testing results\n Frequancy of number rolled in " + sampleSize + " rolls in percent");
		for(int num = 0; num < occurances.length; num++){
			System.out.println((num +1) + ": " + (100*(occurances[num]/sampleSize)) + "%");
			if(occurances[num] > occurances[mode]){
				mode = num;
			}
		}
		if(sampleSize > 1){
			median = (_result.get(sampleSize/2) + _result.get(((sampleSize/2) -1)))/2; 		
		}
		System.out.println("mean: " + (sumOfOccurances/sampleSize));
		System.out.println("median: " + median);
		System.out.println("mode: " + (mode + 1));
	}
		
}
