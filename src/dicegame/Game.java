package dicegame;

import java.util.*;
/**
 * Handle the gameplay data and logic
 * @author Matt Lyons, David Lukacs, David McClure, Daniel Kercheski
 * 
 * 	UPDATE: New methods completed - randomRoll(), secondRoll(), distanceLeft()
 * 
 * 			Other needs:
 * 			
 * 						~Need to continuously deduct yardage from a TOTAL until
 * 						remaining distance is 0 (hole is complete).
 * 
 * 						~Need to store number of turns (strokes). Not sure if should be
 * 						implemented in Game class, or Statistics.
 *
 */
public class Game
{
	static int initialDistance = 36;	//distance from each hole
	static int upperBound = 7;			//7; because random goes up to n-1
	static int lowerBound = 1;			//1; because lowest die value
	
	
	//method for calculating first roll value
	public static int randomRoll()
	{
		Random random = new Random();
		int value = random.nextInt(upperBound - lowerBound) + (lowerBound);
		
		return value;
	}
	
	
	//method for calculating total yardage from second roll
	public static int secondRoll(int value)
	{
		int randomDiceRoll1;
		int randomDiceRoll2;
		int randomDiceRoll3;
		int randomDiceRoll4;
		int randomDiceRoll5;
		int randomDiceRoll6;
		
		int rollTotal;
		
		Random random = new Random();
		
		if (value == 1)
		{
			randomDiceRoll1 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			rollTotal = randomDiceRoll1;
			
		}
		else if(value == 2)
		{
			randomDiceRoll1 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll2 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			rollTotal = randomDiceRoll1 + randomDiceRoll2;
			
		}
		else if(value == 3)
		{
			randomDiceRoll1 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll2 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll3 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			rollTotal = randomDiceRoll1 + randomDiceRoll2 + randomDiceRoll3;
			
		}
		else if(value == 4)
		{
			randomDiceRoll1 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll2 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll3 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll4 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			rollTotal = randomDiceRoll1 + randomDiceRoll2 + randomDiceRoll3
						+ randomDiceRoll4;
			
		}
		else if(value == 5)
		{
			randomDiceRoll1 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll2 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll3 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll4 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll5 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			rollTotal = randomDiceRoll1 + randomDiceRoll2 + randomDiceRoll3
					+ randomDiceRoll4 + randomDiceRoll5;
			
		}
		else
		{
			randomDiceRoll1 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll2 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll3 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll4 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll5 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			randomDiceRoll6 = random.nextInt(upperBound - lowerBound) + (lowerBound);
			rollTotal = randomDiceRoll1 + randomDiceRoll2 + randomDiceRoll3
					+ randomDiceRoll4 + randomDiceRoll5 + randomDiceRoll6;
			
		}
		
		return rollTotal;
	}
	
	
	//method for calculating remaining distance
	public static int distanceLeft(int value)
	{
		int remainingDistance;
		
		remainingDistance = initialDistance - value;
		
		return remainingDistance;
	}
	
	
	//Testing the current methods
	public static void main(String[] args)
	{
		System.out.println("Testing the created methods: ");
		System.out.println();
		
		int firstRoll = randomRoll();
		System.out.println("Here is the number of dice allowed for second roll: " + 
		firstRoll);
		System.out.println();
		
		int secondRoll = secondRoll(firstRoll);
		System.out.println("Here is the total yardage value of first stroke: " + 
		secondRoll);
		System.out.println();
		
		int remainder = distanceLeft(secondRoll);
		System.out.println("Here is the yardage remaining until the hole is reached: " + 
		remainder);
	}
	
}
