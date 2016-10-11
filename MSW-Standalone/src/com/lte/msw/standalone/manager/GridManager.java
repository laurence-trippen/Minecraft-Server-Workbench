package com.lte.msw.manager;

public class GridManager {
	
	public static int getX(int x) {
		if (x < 4) {
			++x;
		}
		if (x == 3) {
			x = 0;
		}
		return x;
	}

	public static int getY(int y, int x) {
		if (x == 0) {
			++y;
		}
		return y;
	}
	
}