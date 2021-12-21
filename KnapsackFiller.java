/*  Charlotte Fanning
	CSC 201 Fall 2020
	Programming Assignment 4
	24 November 2020  */

public class KnapsackFiller
{
	public Thing[] options;    // list of things that can be selected to pack
	private int capacity;
	
	public KnapsackFiller(int numThings, int capacity)
	{
		options = new Thing[numThings];
		this.capacity = capacity;
	}
	
	public void greedyFill()
	{
		Combination greedy = new Combination();
		int maxValue;
		int maxIndex = 0;
		
		while (greedy.getWeight() < capacity)
		{
			maxValue = 0;       // next highest valued item not packed
			for (int i = 0; i < options.length; i++)
			{
				Thing thing = options[i];
				if (!thing.isPacked() && thing.getWeight() <= capacity - greedy.getWeight())
				{
					if (thing.getValue() > maxValue)
					{
						maxValue = thing.getValue();
						maxIndex = i;
					}
					
				}
			}
			if (maxValue == 0)
			{
				break;      // all remaining items are too large to place in the knapsack
			}
			
			options[maxIndex].pack();
			greedy.addItem(options[maxIndex].getWeight(), options[maxIndex].getValue(), maxIndex);
		}
		
		System.out.println("GREEDY FILL");
		System.out.printf("%-6s %10s %11s %n", "item", "weight", "value");
		System.out.println("----------------------------------------");
		if (greedy.items.size() <= 15)
		{
			for (Integer i : greedy.items)
			{
				Thing thing = options[i];
				System.out.printf("%-6d %10d %12d %n", i + 1, thing.getWeight(), thing.getValue());
			}
			System.out.println("----------------------------------------");
		}
		System.out.printf("%-6s %10d %12d %n", "total", greedy.getWeight(), greedy.getValue());
		System.out.println();
	}
	
	private Combination optimalFillHelp(int item, Combination itemsSoFar)       // current item, representative of the items in the current partial solution
	{
		if (item >= options.length)     // no more items to consider
		{
			return itemsSoFar;
		}
		
		else
		{
			Combination take = new Combination(itemsSoFar, options[item].getWeight(), options[item].getValue(), item);      // add current item to new path
			
			if (take.getWeight() > capacity)     // backtrack, capacity exceeded by current item
			{
				take.removeItem(options[item].getWeight(), options[item].getValue(), item);
			}
			else
			{
				take = optimalFillHelp(item + 1, take);     // call to next item with current item added
			}
			
			Combination notTake = optimalFillHelp(item + 1, itemsSoFar);        // call to next item without current item, does not create new combination
			
			if (take.greaterThan(notTake))
			{
				return take;
			}
			else
			{
				return notTake;
			}
		}
	}
	
	public void optimalFill()
	{
		Combination optimal = optimalFillHelp(0, new Combination());        // recursive algorithm
		
		if (optimal.items.size() <= 15)
		{
			System.out.println("OPTIMAL FILL");
			System.out.printf("%-6s %10s %11s %n", "item", "weight", "value");
			System.out.println("----------------------------------------");
			if (optimal.items.size() <= 15)
			{
				for (Integer i : optimal.items)
				{
					Thing thing = options[i];
					System.out.printf("%-6d %10d %12d %n", i + 1, thing.getWeight(), thing.getValue());
				}
				System.out.println("----------------------------------------");
			}
			System.out.printf("%-6s %10d %12d %n", "total", optimal.getWeight(), optimal.getValue());
		}
	}
}
