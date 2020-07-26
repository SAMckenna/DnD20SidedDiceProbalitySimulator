/**
 * 
 */

package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Steven
 * This class controls and maintains the results for testing intended to show the probability of achieving a particular value on a
 * twenty sided die
 */
public class TestHeadD20{
	private ArrayList<Integer> _samples = new ArrayList<>(); /** holds the samples  used for calculating the test results */
	private Semaphore _accessSamples = new Semaphore(1); /** must be acquired to modify the samples table */
	private int _sampleSize; /**sample size */
	private double _mean = 0.0; /** the average value of the roll achieved in the last test*/
	private double _mode = 0.0; /** the most common value rolled during the last test */
	private double _median = 0.0;/** the middle most value rolled during the last test, after the samples were sorted numerically*/
	private double[] _occurancesOfNumbers = new double[20]; /** holds the number of times each value occurred in the last test */
	private int _sumOfNumbersOccurred = 0; /** holds the sum of the values used in the last test */

	/**
	 * creates a default test, with a sample size of 1;
	 */
	public TestHeadD20() {
		this._sampleSize = 1;
	}

	/**
	 * Executes a 20 sided dice roll simulation, using the current set sample size, where the die is rolled twice and the higher value is added to the results. Then attempts to display the results.
	 * @param diceToRoll, the number of dice to roll before picking the highest number rolled
	 */
	public void ExcuteD20Adavantage(int diceToRoll){
		this.ClearResults();
		do{
			Thread test = new TestWorkerD20Adavantage(this, diceToRoll);
			test.start();
		}
		while(_samples.size() < this._sampleSize);
		try {
			this._accessSamples.acquire();
			this.CalculateResults();
			this.DisplayResults();
			this._accessSamples.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Executes a 20 sided dice roll simulation, where the die is rolled a number of times equal to diceToRoll and the higher value is added to the results. Then attempts to display the results.
	 * @param sampleSize the number of independent dice rolls required in results for this test
	 * @param diceToRoll the number of dice to roll before taking the highest
	 */
	public void ExcuteD20Adavantage(int sampleSize, int diceToRoll){
		this.ClearResults();
		this._sampleSize = sampleSize;
		do{
			Thread test = new TestWorkerD20Adavantage(this, diceToRoll);
			test.start();
		}
		while(_samples.size() < this._sampleSize);
		try {
			this._accessSamples.acquire();
			this.CalculateResults();
			this.DisplayResults();
			this._accessSamples.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Executes a 20 sided dice roll simulation, using the current setting for sample size ,where the die is rolled a number of times\
	 *  equal to diceToRoll and the lowest value is added to the samples. It will then attempt to display the results.
	 * @param diceToRoll the number of dice to roll before taking the highest
	 */
	public void ExcuteD20Disadavantage(int diceToRoll){
		this.ClearResults();
		do{
			Thread test = new TestWorkerD20Disadavantage(this, diceToRoll);
			test.start();
		}
		while(_samples.size() < this._sampleSize);
		try {
			this._accessSamples.acquire();
			this.CalculateResults();
			this.DisplayResults();
			this._accessSamples.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Executes a 20 sided dice roll simulation, where the die is rolled a number of times equal to diceToRoll and the lowest value is added to the results. Then attempts to display the results.
	 * @param sampleSize the number of independent dice rolls required in results for this test
	 * @param diceToRoll the number of dice to roll before taking the highest
	 */
	public void ExcuteD20Disadavantage(int sampleSize, int diceToRoll){
		this.ClearResults();
		this._sampleSize = sampleSize;
		do{
			Thread test = new TestWorkerD20Disadavantage(this, diceToRoll);
			test.start();
		}
		while(_samples.size() < this._sampleSize);
		try {
			this._accessSamples.acquire();
			this.CalculateResults();
			this.DisplayResults();
			this._accessSamples.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Displays to the console how often a particular value appeared in the test, along with the mean, median and mode.
	 */
	public void DisplayResults(){		
		int sampleSize = this._samples.size();
		System.out.println("Testing results\n Frequancy of number rolled in " + sampleSize + " rolls in percent");
		for(int num = 0; num < this._occurancesOfNumbers.length; num++){
			System.out.println((num +1) + ": " + (100*(this._occurancesOfNumbers[num]/sampleSize)) + "%");			
		}
		System.out.println("mean: " + (this._mean));
		System.out.println("median: " + this._median);
		System.out.println("mode: " + (this._mode + 1));
	}

	/**
	 * @return the _sampleSize
	 */
	public int get_sampleSize() {
		return _sampleSize;
	}

	/**
	 * @return the _samples
	 */
	public ArrayList<Integer> get_result() {
		return _samples;
	}

	/**
	 * Tries to add the given sample to the samples, in thread safe manor.
	 * @param sample, the sample to add to the samples list
	 */
	protected void AddSample(int sample){
		try {
			if(this._accessSamples.tryAcquire(100, TimeUnit.MILLISECONDS)){
				this._samples.add(sample);
				this._accessSamples.release();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Calculates the results of the test by determining how often each value occurred, finding the mean, mode and median; 
	 */
	private void CalculateResults(){
		Collections.sort(this._samples);
		int sampleSize = this._samples.size();
		int mode = 0;
		for(int num : this._samples){
			if(num > 0){
				this._occurancesOfNumbers[(num-1)] += 1;
				this._sumOfNumbersOccurred += num;
			}
		}
		for(int num = 0; num < this._occurancesOfNumbers.length; num++){
			if(this._occurancesOfNumbers[num] > this._occurancesOfNumbers[mode]){
				this._mode = num;
			}
		}
		if(sampleSize > 1){
			this._median = (_samples.get(sampleSize/2) + _samples.get(((sampleSize/2) -1)))/2; 		
		}
		if(sampleSize > 0){
			this._mean = this._sumOfNumbersOccurred/sampleSize;
		}
	}

	/**
	 * Empties the samples list and calculated results, such that a new set of samples can be added to it and new results calculated.
	 */
	private void ClearResults(){
		try {
			this._accessSamples.acquire();
			this._samples.clear();
			this._mean = 0;
			this._median = 0;
			this._mode = 0;
			this._occurancesOfNumbers = new double[20];
			this._sumOfNumbersOccurred = 0;
			this._accessSamples.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
