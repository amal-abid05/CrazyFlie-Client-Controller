package fenetre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class MousePanel extends JPanel
{
	private int thrust = 0;
	private boolean pressed;
	private int lastX = 0;
	public MousePanel() 
	{
		setBounds(510,10,40,240);
		setBackground(Color.red);
		setOpaque(true);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				if(pressed)
					pressed=false;
				thrust = 0;
				lastX = 0;
				repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int y=e.getY();
				if(y<240&&y>200)
				{
					pressed=true;
					thrust = 0;
					lastX = y;
				}
				repaint();	
			}
			
			@Override
			public void mouseExited(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
			}
		});
		addMouseMotionListener(new MouseMotionListener() 
		{
			
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				if(pressed)
				{
					int x=e.getY();
					int ya=thrust-(x-lastX);
					if(ya>0)
					{
						if(ya<200)
						{
							thrust=ya;
							lastX = x;
						}
						else
						{
							thrust=200;
						}
					}
					else
					{
						thrust=0;
					}
				}
				repaint();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.fillOval(0,200-thrust,  40, 40);
	}
	public int getThrust() {return thrust/2;}
	public void setThrust(int thrust) {this.thrust = thrust;}
}
