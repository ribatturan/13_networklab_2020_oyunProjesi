package Pong_proje;


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
