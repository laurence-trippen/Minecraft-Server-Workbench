package com.lte.mcsm.manager;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DesktopManager {
	
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

}