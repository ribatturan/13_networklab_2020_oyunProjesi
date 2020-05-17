package Pong_proje;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Integer;

//Mouse'un yerine göre paddle'in yerini günceller

public class clientReciever implements Runnable
{
	private Socket connectionSock = null;
	private Paddle paddleY; 
	public clientReciever(Socket connectionSock, Paddle paddleY)
	{
		this.connectionSock = connectionSock;
		this.paddleY = paddleY;
	}
	public void run()
	{
		try
		{
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			while(true)
			{
				String clientText = clientInput.readLine();
				if(clientText != null)
				{

					paddleY.setValue(Integer.parseInt(clientText) ); 
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}			
}