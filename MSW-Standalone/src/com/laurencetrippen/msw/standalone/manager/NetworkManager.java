package com.laurencetrippen.msw.standalone.manager;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkManager {
	
	public static String getLocalhost() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Deprecated
	public static int getFreePort() {return 0;}

}
