package Pong_proje;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Integer;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Client
{
	//private static int mouseY;
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Pong");
		window.setSize(900, 500);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		scene game = new scene();
		window.add(game);
		try
		{
			String hostname = "localhost"; 
			Socket connectionSock = new Socket(hostname, 5555);
			DataOutputStream out = new DataOutputStream(connectionSock.getOutputStream());
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			
			clientInput.readLine();
			
			
			ServerReciever sceneUpdater = new ServerReciever(game, connectionSock); 
			Thread recieving = new Thread(sceneUpdater);
			recieving.start();
                        
			while(true)
			{
				int mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY()) - (int)(game.getLocationOnScreen().getY());
				out.writeBytes(mouseY + "\n");
			}	
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
}
