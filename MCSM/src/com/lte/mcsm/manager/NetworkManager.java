package com.lte.mcsm.manager;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkManager {
	
	public static String getMyIPAdress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

}
