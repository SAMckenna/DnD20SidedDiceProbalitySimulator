package Dice;

import static org.junit.Assert.*;

import org.junit.Test;

public class DieTest {

	@Test
	public void testDefualtDie() {
		Die die = new Die();
		assert(die instanceof Die);
		assert(die.get_maxValue() == 1.0);
		assert(die.get_minValue() == 0.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	
	@Test
	public void testDieInt() {
		Die die = new Die(10, 0);
		assert(die instanceof Die);
		assert(die.get_maxValue() == 10.0);
		assert(die.get_minValue() == 0.0);
		double roll = die.RollDouble();
		assert(roll >= (int)die.get_minValue());
		assert(roll <= (int)die.get_maxValue());
		
		die = new Die(0, 5);
		assert(die instanceof Die);
		assert(die.get_maxValue() == 5.0);
		assert(die.get_minValue() == 0.0);
		roll = die.RollInt();
		assert(roll >= (int)die.get_minValue());
		assert(roll <= (int)die.get_maxValue());
		
		die = new Die(6, 6);
		assert(die instanceof Die);
		assert(die.get_maxValue() == 7.0);
		assert(die.get_minValue() == 6.0);
		roll = die.RollInt();
		assert(roll >= (int)die.get_minValue());
		System.out.println(roll + " " + die.get_maxValue());
		assert(roll <= (int)die.get_maxValue());
		
		die = new Die(-6, -6);
		assert(die instanceof Die);
		assert(die.get_maxValue() == -5);
		assert(die.get_minValue() == -6);
		roll = die.RollInt();
		assert(roll >= (int)die.get_minValue());
		assert(roll <= (int)die.get_maxValue());
	}
	
	@Test
	public void testDieDouble() {
		Die die = new Die(6.5, 0.6);
		assert(die instanceof Die);
		assert(die.get_maxValue() == 6.5);
		assert(die.get_minValue() == 0.6);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
		
		die = new Die(0.2, 7.3);
		assert(die instanceof Die);
		assert(die.get_maxValue() == 7.3);
		assert(die.get_minValue() == 0.2);
		roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
		
		die = new Die(7.3, 7.3);
		assert(die instanceof Die);
		assert(die.get_maxValue() == 8.3);
		assert(die.get_minValue() == 7.3);
		roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
		
		die = new Die(-6, -6);
		assert(die instanceof Die);
		assert(die.get_maxValue() == -5.0);
		assert(die.get_minValue() == -6.0);
		roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
	}
	
	@Test
	public void testDieCopy() {
		Die die, die2;
		die = new Die(10, 1);		
		die2 = new Die(die); 
		
		assert(die instanceof Die);
		assert(die.get_maxValue() == 10.0);
		assert(die.get_minValue() == 1.0);
		double roll = die.RollDouble();
		assert(roll >= die.get_minValue());
		assert(roll <= die.get_maxValue());
		
		assert(die2 instanceof Die);
		assert(die2.get_maxValue() == 10.0);
		assert(die2.get_minValue() == 1.0);
		roll = die2.RollDouble();
		assert(roll >= die2.get_minValue());
		assert(roll <= die2.get_maxValue());
		
		die2.set_maxValue(2);
		die.set_minValue(5);
		
		assert(die instanceof Die);
		assert(die.get_maxValue() == 10.0);
		assert(die.get_minValue() == 5.0);
		
		assert(die2 instanceof Die);
		assert(die2.get_maxValue() == 2.0);
		assert(die2.get_minValue() == 1.0);
	}
	
	@Test
	public void testSettersGetters() {
		Die die = new Die();		
		assert(die instanceof Die);
		assert(die.get_maxValue() == 1.0);
		assert(die.get_minValue() == 0.0);
		
		die.set_maxValue(5);
		die.set_minValue(4);
		assert(die.get_maxValue() == 5.0);
		assert(die.get_minValue() == 4.0);
		
		//max can't be less then min
		die.set_maxValue(2);
		die.set_minValue(8);
		assert(die.get_maxValue() == 5.0);
		assert(die.get_minValue() == 4.0);
		
		
	}

}
