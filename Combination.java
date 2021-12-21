/*  Charlotte Fanning
	CSC 201 Fall 2020
	Programming Assignment 4
	24 November 2020  */

/*  This class represents some combination of items that could go into the knapsack. If the solution space for filling the knapsack is of a tree structure, with
	each level of the tree representing a particular item that could be packed or not, the combination object is like a particular path in the tree from root to
	end (when there are no more items to consider or the weight capacity is met.  */

import java.util.ArrayList;

public class Combination
{
	private int weight;
	private int value;
	public ArrayList<Integer> items;        // indices of items chosen
	
	public Combination ()
	{
		weight = 0;
		value = 0;
		items = new ArrayList<>();
	}
	
	public Combination (Combination copied, int w, int v, int i)        // create new object when adding item to a partial solution
	{
		weight = copied.weight + w;
		value = copied.value + v;
		items = new ArrayList<>();
		
		for (Integer item : copied.items)
		{
			items.add(Integer.valueOf(item));
		}
		items.add(Integer.valueOf(i));
	}
	
	public void addItem(int w, int v, int i)
	{
		weight += w;
		value += v;
		items.add(Integer.valueOf(i));
	}
	
	public Combination removeItem(int w, int v, int i)
	{
		weight -= w;
		value -= v;
		items.remove(Integer.valueOf(i));
		
		return this;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public boolean greaterThan(Combination rhs)
	{
		return (value > rhs.value);
	}
}
