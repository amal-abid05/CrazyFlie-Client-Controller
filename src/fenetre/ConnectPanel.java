package fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;
import se.bitcraze.crazyflie.lib.crazyflie.Crazyflie;
import se.bitcraze.crazyflie.lib.crazyradio.ConnectionData;
import se.bitcraze.crazyflie.lib.crazyradio.Crazyradio;
import se.bitcraze.crazyflie.lib.crazyradio.RadioDriver;
import se.bitcraze.crazyflie.lib.usb.UsbLinkJava;

public class ConnectPanel extends JPanel
{
	
	public ConnectPanel() 
	{
		setLayout(null);
		//connexion port usb pc
		UsbLinkJava usb = new UsbLinkJava();
		//connexion radioradiver premier detecté
		final RadioDriver dr = new RadioDriver(usb);
		//detection des connections (scan)
		List<ConnectionData> list = dr.scanInterface();
		for (int i=0;i<list.size();i++)
		{
			//connectionData c'est l'id
			final ConnectionData connectionData=list.get(i);
			//chauqu connection Ã  un id rate et channel 
			JButton button = new JButton(connectionData.getDataRate()+":"+connectionData.getChannel());
			button.setBounds(20, 20+i*45, 200, 40);
			add(button);
			button.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Main.frame.setVisible(false);
					Crazyflie crazy = new Crazyflie(dr);
					Main.frame.remove(Main.conn);
					Main.comm=new CommandPanel(crazy,connectionData);
					Main.frame.add(Main.comm);
					Main.frame.setVisible(true);
				}
			});
		}
		setBounds(0,0,600,600);
	}
}
