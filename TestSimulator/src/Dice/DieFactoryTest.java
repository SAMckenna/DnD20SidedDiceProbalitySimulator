package Dice;

import static org.junit.Assert.*;

import org.junit.Test;

public class DieFactoryTest {

	@Test
	public void testFourSidedDie() {
		Die die = DieFactory.FourSidedDie();
		assert(die instanceof Die);
		assert(die.get_maxValue() == 4.0);
		assert(die.get_minValue() == 1.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	
	@Test
	public void testSixSidedDie() {
		Die die = DieFactory.SixSidedDie();
		assert(die instanceof Die);
		assert(die.get_maxValue() == 6.0);
		assert(die.get_minValue() == 1.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	@Test
	public void testEightSidedDie() {
		Die die = DieFactory.EightSidedDie();
		assert(die instanceof Die);
		assert(die.get_maxValue() == 8.0);
		assert(die.get_minValue() == 1.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	
	@Test
	public void testTenSidedDie() {
		Die die = DieFactory.TenSidedDie();
		assert(die instanceof Die);
		assert(die.get_maxValue() == 10.0);
		assert(die.get_minValue() == 1.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	@Test
	public void testTwelveSidedDie() {
		Die die = DieFactory.TwelveSidedDie();
		assert(die instanceof Die);
		assert(die.get_maxValue() == 12.0);
		assert(die.get_minValue() == 1.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	
	@Test
	public void testTwentySidedDie() {
		Die die = DieFactory.TwentySidedDie();
		assert(die instanceof Die);
		assert(die.get_maxValue() == 20.0);
		assert(die.get_minValue() == 1.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	
	@Test
	public void testHundredSidedDie() {
		Die die = DieFactory.HundredSidedDie();
		assert(die instanceof Die);
		assert(die.get_maxValue() == 100.0);
		assert(die.get_minValue() == 1.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	
	@Test
	public void testCustomSidedDie() {
		Die die = DieFactory.CustomDie(1, 0);
		assert(die instanceof Die);
		assert(die.get_maxValue() == 1.0);
		assert(die.get_minValue() == 0.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
		
		die = DieFactory.CustomDie(6, 3);
		assert(die instanceof Die);
		assert(die.get_maxValue() == 6.0);
		assert(die.get_minValue() == 3.0);
		roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
}
