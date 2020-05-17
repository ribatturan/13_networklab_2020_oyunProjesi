package Pong_proje;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class scene extends JPanel implements ActionListener, KeyListener
{
	private int lRectY = 0; 
	private int rRectY = 0; 
	private int ballX = 450, ballY = 250; 
	private int yOffset = 0;
	
	private JLabel scoreBoard = new JLabel("0 - 0");
	
	Timer reDisplay = new Timer(5, this);
	public scene()
	{
		super();
		add(scoreBoard);		
		setBackground(Color.RED);
		reDisplay.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	public scene(int lRectY, int rRectY, int ballX, int ballY)
	{
		super();
		this.lRectY = lRectY;
		this.rRectY = rRectY;
		this.ballX = ballX;
		this.ballY = ballY;
		add(scoreBoard);
		setBackground(Color.RED);
		reDisplay.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE); 
		g.fillRect(0, lRectY, 20, 150);  
		g.fillRect(876, rRectY, 20, 150);
		g.fillOval(ballX, ballY, 20, 20); 
	}
	public void actionPerformed(ActionEvent event)
	{
		repaint();
	}
	public void keyPressed(KeyEvent event)
	{
		int code = event.getKeyCode();
		if(code == KeyEvent.VK_UP)
		{
			yOffset = -2;
		}
		if(code == KeyEvent.VK_DOWN)
		{
			yOffset = 2;
		}
	}
	public void keyTyped(KeyEvent event)
	{
	}
	public void keyReleased(KeyEvent event)
	{
		yOffset = 0;
	}
	public void moveLeftPaddle(int lRectY)
	{
		this.lRectY = lRectY;
	}
	public void moveRightPaddle(int rRectY)
	{
		this.rRectY = rRectY;
	}
	public void changeBallPosition(int ballX, int ballY)
	{
		this.ballX = ballX;
		this.ballY = ballY;
	}
	public void modifyScore(String score)
	{
		scoreBoard.setText(score);
	}	
	public int getYOffset()
	{
		return yOffset;
	}
}