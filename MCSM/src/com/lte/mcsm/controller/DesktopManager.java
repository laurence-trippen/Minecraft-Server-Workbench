package com.lte.mcsm.view.components;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Desktop {
	
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

}
