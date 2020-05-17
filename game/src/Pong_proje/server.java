package Pong_proje;

/*
This is the server program that acts as the model for the pong game.
*/
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.*;

public class server
{
	private static int leftWins = 0, rightWins = 0;  //skor tablosu
	private static int ballX = 450, ballY = 250;
	private static double ballVelX = (Math.random()*2) - 1; //top x ivmesi
	private static double ballVelY = (Math.random()*2) - 1; //top y ivmesi
	private static Paddle lRectY = new Paddle(0);
	private static Paddle rRectY = new Paddle(0);
	private static String score = leftWins + " - " + rightWins;
	
	
	public static void main(String[] args)
	{
		if(ballVelX == 0)
		{
			ballVelX = 0.1;
		}
		if(ballVelY == 0)
		{
			ballVelY = 0.1;
		}
		try
		{
			/* first the server waits for connections from two clients and then stops listening 
				the first connection is assigned the left player and the the second is assigned as the right*/
			System.out.println("Clientlar bekleniyor...");
			ServerSocket serverSock = new ServerSocket(5555);
			Socket connectionOne = serverSock.accept();
			Socket connectionTwo = serverSock.accept();
			DataOutputStream clientOutput1 = new DataOutputStream(connectionOne.getOutputStream());
			DataOutputStream clientOutput2 = new DataOutputStream(connectionTwo.getOutputStream());
			
			clientReciever left = new clientReciever(connectionOne, lRectY);
			clientReciever right = new clientReciever(connectionTwo, rRectY);
			Thread solRecieve = new Thread(left);
			Thread sagRecieve = new Thread(right);
			
			clientOutput1.writeBytes("start\n"); //clientların data göndermesini başlatır
			clientOutput2.writeBytes("start\n");
			
			BufferedReader clientInput1 = new BufferedReader(new InputStreamReader(connectionOne.getInputStream()));
			BufferedReader clientInput2 = new BufferedReader(new InputStreamReader(connectionTwo.getInputStream()));
			
			clientInput1.readLine();
			solRecieve.start();
			clientInput2.readLine(); 
			sagRecieve.start();
			
			boolean newGame = false; 
			while(true)
			{
				if(ballX == 0)
				{
					if(ballY < lRectY.getValue() || ballY > lRectY.getValue() + 150)
					{
						rightWins++;
						resetValues();
						newGame = true;
					}
					else
					{
						ballVelX = -ballVelX;
					}
				}
				else if(ballX == 876)
				{
					if(ballY < rRectY.getValue() || ballY > rRectY.getValue() + 150)
					{
						leftWins++;
						resetValues();
						newGame = true;
					}
					else
					{
						ballVelX = -ballVelX;
					}
				}
				
				if(ballY == 0 || ballY == 450)
				{
					ballVelY = -ballVelY;
				}
				
				if(newGame)
				{
					newGame = false;
				}
				else
				{
					if(ballVelX >= 1)
					{
						ballX++;
						ballVelX = 0.1;
					}
					else if(ballVelX <= -1)
					{
						ballX--;
						ballVelX = -0.1;
					}
					else if(ballVelX > 0)
					{
						ballVelX += 0.1;
					}
					else if(ballVelX < 0)
					{
						ballVelX -= 0.1;
					}
					
					if(ballVelY >= 1)
					{
						ballY++;
						ballVelY = 0.1;
					}
					else if(ballVelY <= -1)
					{
						ballY--;
						ballVelY = -0.1;
					}
					else if(ballVelY > 0)
					{
						ballVelY += 0.1;
					}
					else if(ballVelY < 0)
					{
						ballVelY -= 0.1;
					}
				}
				if(connectionOne == null || connectionTwo == null) //clientlar hala oyunda mı?
				{
					break;
				}
                                
				
				if(lRectY.getValue() < 0)
				{
					lRectY.setValue(0);
				}
				else if(lRectY.getValue() > 350)
				{
					lRectY.setValue(350);
				}

				if(rRectY.getValue() < 0)
				{
					rRectY.setValue(0);
				}
				else if(rRectY.getValue() > 350)
				{
					rRectY.setValue(350);
				}
				clientOutput1.writeBytes(ballX + "\n");
				clientOutput2.writeBytes(ballX + "\n");
				clientOutput1.writeBytes(ballY + "\n");
				clientOutput2.writeBytes(ballY + "\n");
				clientOutput1.writeBytes(lRectY.getValue() + "\n");
				clientOutput2.writeBytes(lRectY.getValue() + "\n");
				clientOutput1.writeBytes(rRectY.getValue() + "\n");
				clientOutput2.writeBytes(rRectY.getValue() + "\n");
				clientOutput1.writeBytes(score + "\n");
				clientOutput2.writeBytes(score + "\n");
                                if(leftWins==10)break;
                                else  if(rightWins==10)break;
			}	
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
	public static void resetValues()
	{
		ballX = 450;
		ballY = 250;
		ballVelX = (Math.random()*2) - 1; //ball's x axis velocity
		ballVelY = (Math.random()*2) - 1; //ball's y axis velocity
		Paddle lRectY = new Paddle(0);
		Paddle rRectY = new Paddle(0);
		score = leftWins + " - " + rightWins;
		if(ballVelX == 0)
		{
			ballVelX = 0.1;
		}
		if(ballVelY == 0)
		{
			ballVelY = 0.1;
		}	
	}	
}