/*  Charlotte Fanning
	CSC 201 Fall 2020
	Programming Assignment 4
	24 November 2020  */

/*  This program reads input from a file called "knapsack.txt" which contains a weight capacity, number of items, list of weights, and list of values for those
	items. The main method uses the KnapsackFiller class to determine ways of packing these items into a knapsack in a way that does not exceed the weight
	capacity. The greedyFill algorithm places the highest valued items into the knapsack until there is no room for the next highest valued item, while the
	optimalFill algorithm considers all possible combinations of the items that does not exceed the weight capacity and chooses the most valuable one.  */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Knapsack
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String fileName = "knapsack.txt";
		File file = new File(fileName);
		Scanner fileScnr = new Scanner(file);
		
		final int CAPACITY = fileScnr.nextInt();
		final int NUM_THINGS = fileScnr.nextInt();
		
		int[] weights = new int[NUM_THINGS];
		for (int i = 0; i < NUM_THINGS; i++)
		{
			weights[i] = fileScnr.nextInt();
		}
		
		int[] values = new int[NUM_THINGS];
		for (int i = 0; i < NUM_THINGS; i++)
		{
			values[i] = fileScnr.nextInt();
		}
		
		if (NUM_THINGS <= 15)     // print contents to choose from
		{
			System.out.println("OPTIONS");
			System.out.printf("%-6s %10s %12s %n", "item", "weight", "value");
			System.out.println("----------------------------------------");
			for (int i = 0; i < NUM_THINGS; i++)
			{
				System.out.printf("%-6d %10d %11d %n", (i + 1), weights[i], values[i]);
			}
		}
		
		KnapsackFiller filler = new KnapsackFiller(NUM_THINGS, CAPACITY);
		
		for (int i = 0; i < NUM_THINGS; i++)        // construct content objects
		{
			Thing thing = new Thing(weights[i], values[i]);
			filler.options[i] = thing;
		}
		System.out.println();
		
		filler.greedyFill();
		filler.optimalFill();
		
	}
}
