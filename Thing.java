/*  Charlotte Fanning
	CSC 201 Fall 2020
	Programming Assignment 4
	24 November 2020  */

public class Thing
{
	private int weight;
	private int value;
	private boolean packed;
	
	public Thing(int w, int v)
	{
		weight = w;
		value = v;
		packed = false;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public boolean isPacked()
	{
		return packed;
	}
	
	public void pack()      // mark items as part of the solution in greedy fill algorithm
	{
		packed = true;
	}
}
