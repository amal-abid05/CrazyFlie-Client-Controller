package main;

import java.io.IOException;
import java.util.List;

import javax.usb.UsbDevice;

import se.bitcraze.crazyflie.lib.usb.UsbLinkJava;

public class Test {


	public static void main(String[] args) 
	{
		UsbLinkJava usb = new UsbLinkJava();
		try {
			usb.initDevice(0x1915, 0x7777);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
