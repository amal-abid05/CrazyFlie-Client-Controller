package fenetre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class KeyPanel extends JPanel
{
	private int pitch = 0;
	private int roll = 0;
	private int yaw = 0;
	public KeyPanel() 
	{
		setBounds(10,10,240,240);
		addKeyListener(new KeyListener() 
		{
			
			@Override
			public void keyTyped(KeyEvent arg0) 
			{
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				int key = arg0.getKeyCode();
				if(key==KeyEvent.VK_T)
				{
					roll=0;
				}
				if(key==KeyEvent.VK_R)
				{
					roll=0;
				}
				if(key==KeyEvent.VK_G)
				{
					pitch=0;
				}
				if(key==KeyEvent.VK_F)
				{
					pitch=0;
				}
				if(key==KeyEvent.VK_V)
				{
					yaw=0;
				}
				if(key==KeyEvent.VK_C)
				{
					yaw=0;
				}
				repaint();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				int key = arg0.getKeyCode();
				if(key==KeyEvent.VK_T)
				{
					if(roll<95)
						roll+=5;
					else
						roll=100;
				}
				if(key==KeyEvent.VK_R)
				{
					if(roll>-95)
						roll-=5;
					else
						roll=-100;
				}
				if(key==KeyEvent.VK_G)
				{
					if(pitch<95)
						pitch+=5;
					else
						pitch=100;
				}
				if(key==KeyEvent.VK_F)
				{
					if(pitch>-95)
						pitch-=5;
					else
						pitch=-100;
				}
				if(key==KeyEvent.VK_V)
				{
					if(yaw<95)
						yaw+=5;
					else
						yaw=100;
				}
				if(key==KeyEvent.VK_C)
				{
					if(yaw>-95)
						yaw-=5;
					else
						yaw=-100;
				}
				repaint();
				
			}
			
		});
		setFocusable(true);
		requestFocus();
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 240, 40);
		g.setColor(Color.blue);
		g.fillRect(0, 100, 240, 40);
		g.setColor(Color.magenta);
		g.fillRect(0, 200, 240, 40);
		g.setColor(Color.black);
		g.fillOval(100+roll,0,  40, 40);
		g.fillOval(100+pitch,100,  40, 40);
		g.fillOval(100+yaw,200,  40, 40);
	}
	public int getPitch() {
		return pitch;
	}
	public void setPitch(int pitch) {
		this.pitch = pitch;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public int getYaw() {
		return yaw;
	}
	public void setYaw(int yaw) {
		this.yaw = yaw;
	}
	
}
