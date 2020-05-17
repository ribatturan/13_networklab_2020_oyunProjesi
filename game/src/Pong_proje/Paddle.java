package Pong_proje;

/*
This is a simple wrapper class for integers.
*/

public class Paddle
{
	private int value = 0;
	public Paddle()
	{
	}
	public Paddle(int value)
	{
		this.value = value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	public int getValue()
	{
		return value;
	}
	public String toString()
	{
		return "" + value;
	}
}