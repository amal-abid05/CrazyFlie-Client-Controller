package fenetre;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;
import se.bitcraze.crazyflie.lib.crazyflie.ConnectionListener;
import se.bitcraze.crazyflie.lib.crazyflie.Crazyflie;
import se.bitcraze.crazyflie.lib.crazyflie.DataListener;
import se.bitcraze.crazyflie.lib.crazyradio.ConnectionData;
import se.bitcraze.crazyflie.lib.crtp.CommanderPacket;
import se.bitcraze.crazyflie.lib.crtp.CrtpDriver;
import se.bitcraze.crazyflie.lib.crtp.CrtpPacket;

public class CommandPanel extends JPanel
{
	private Crazyflie crazyflie;
	private int state;
	private String message;
	private KeyPanel keyPanel;
	private MousePanel mousePanel;
	private JButton disconnect;
	private Thread sendThread;
	private ConnectionData connectionData;
	public CommandPanel(Crazyflie crazyflie,ConnectionData connectionData) 
	{
		this.crazyflie=crazyflie;
		this.connectionData=connectionData;
		
		this.crazyflie.getDriver().addConnectionListener(new ConnectionListener() {
			
			@Override
			public void connectionRequested(String arg0) 
			{
				state=1;
				repaint();
			}
			@Override
			public void connected(String arg0) 
			{
				state=2;
				repaint();
			}
			@Override
			public void setupFinished(String arg0) 
			{
				state=3;
				//envoie les param des vol
				start();
				repaint();
			}
			
			@Override
			public void linkQualityUpdated(int arg0) 
			{
				
			}
			
			@Override
			public void disconnected(String arg0) 
			{
				state=0;
				repaint();
			}
			
			
			
			@Override
			public void connectionLost(String arg0, String arg1) 
			{
				state=4;
				message=arg1;
				repaint();
			}
			
			@Override
			public void connectionFailed(String arg0, String arg1) 
			{
				state=4;
				message=arg1;
				repaint();
			}
			
			
		});
		
		this.crazyflie.connect(this.connectionData);
		
		disconnect=new JButton("disconnect");
		disconnect.setBounds(20, 300, 250,40);
		disconnect.setFocusable(false);
		disconnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				disconnect();
			}
		});
		mousePanel=new MousePanel();
		keyPanel=new KeyPanel();
		setLayout(null);
		add(mousePanel);
		add(keyPanel);
		add(disconnect);
		setBounds(0,0,600,600);		
	}
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		switch(state)
		{
			case 0 : g.drawString("Not Connected", 10, 390); break;
			case 1 : g.drawString("Connecting...", 10, 390); break;
			case 2 : g.drawString("Setup...", 10, 390); break;
			case 3 : g.drawString("Connected", 10, 390); break;
			case 4 : g.drawString("Can't Connect : "+message, 10, 390); break;
		}
		
	}
	public void disconnect() 
	{

        if (crazyflie != null) {
        	crazyflie.disconnect();
        	crazyflie = null;
        }
        if (sendThread != null) 
        {
        	sendThread.interrupt();
        	sendThread = null;
        }
        Main.frame.setVisible(false);
		Main.frame.remove(Main.comm);
		Main.conn=new ConnectPanel();
		Main.frame.add(Main.conn);
		Main.frame.setVisible(true);
    }
	private void start() 
	{
		sendThread = new Thread(new Runnable() 
		{
            @Override
            public void run() 
            {
                while (crazyflie != null) 
                {
                	if(state==3)
                	{
                		crazyflie.sendPacket(new CommanderPacket(keyPanel.getRoll(), keyPanel.getPitch(), keyPanel.getYaw(), (char)(mousePanel.getThrust()*6553)));
                        System.out.println("Sending :"+mousePanel.getThrust());
                		try 
                        {
                            Thread.sleep(5);
                        } 
                        catch (InterruptedException e) 
                        {
                            
                            break;
                        }
                	}
            		
                }
           }
        });
		sendThread.start();
	}
}
