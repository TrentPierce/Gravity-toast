package com.example.gravitytoast;

import android.content.Context;
import android.hardware.SensorEvent;
import android.widget.Toast;

public class Direction {
	
	public static void printDirection(Context c, int direction) {

		int duration = Toast.LENGTH_SHORT;
		CharSequence text1 = "Top";
		CharSequence text2 = "Right";
		CharSequence text3 = "Bottom";
		CharSequence text4 = "Left";
		Toast toast1 = Toast.makeText(c, text1, duration);
		Toast toast2 = Toast.makeText(c, text2, duration);
		Toast toast3 = Toast.makeText(c, text3, duration);
		Toast toast4 = Toast.makeText(c, text4, duration);
		
		switch (direction) {
			case 1:	toast1.show();
					break;
			case 2:	toast2.show();
					break;
			case 3:	toast3.show();
					break;
			case 4:	toast4.show();
					break;
		}
	}
	
	public static int getDirection(SensorEvent event){
		int dir;
		if (Math.abs(event.values[0]) > Math.abs(event.values[1])){
			if (event.values[0]>0)
				dir = 4;
			else
				dir = 2;
		}
		else {
			if (event.values[1]>0)
				dir = 3;
			else
				dir = 1;	
		}
		return dir;
	}

}
