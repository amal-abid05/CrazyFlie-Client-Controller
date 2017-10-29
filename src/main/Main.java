package main;

import javax.swing.JFrame;

import fenetre.CommandPanel;
import fenetre.ConnectPanel;
import fenetre.RepeatingReleasedEventsFixer;

public class Main {
	public static JFrame frame;
	public static ConnectPanel conn;
	public static CommandPanel comm;
	public static void main(String[] args) 
	{
		//for linux user
		//new RepeatingReleasedEventsFixer().install();
		frame=new JFrame();
		conn=new ConnectPanel();
		frame.setVisible(true);
		frame.setLayout(null);
		frame.add(conn);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
