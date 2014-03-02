package com.example.gravitytoast;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SensorManager mManager;
	private Sensor mGravSensor;
	private static final int sType = Sensor.TYPE_ACCELEROMETER;
	int dir=4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
		    Thread.sleep(1000);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		mManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mGravSensor = mManager.getDefaultSensor(sType);
		
	    if (mGravSensor != null) {
	    	Toast.makeText(this, "Accelerometer Sensor found",Toast.LENGTH_LONG).show();
	      } else {
	        
	        Toast.makeText(this, "Gravity Sensor not found",Toast.LENGTH_LONG).show();
	        finish();
	      }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	Context context = this;
	
	private SensorEventListener mSensorListener = new SensorEventListener() {
		
		@Override
	    public void onSensorChanged(SensorEvent event) {
			dir = Direction.getDirection(event);
			//Direction.printDirection(context, dir);
			//Toast.makeText(context, "End toast",Toast.LENGTH_SHORT).show();

		}
	    @Override
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }
	  };

		public void printGrav(View v) {
			Direction.printDirection(context, dir);
		}
	  
	  @Override
	  protected void onResume() {
		  super.onResume();
		  mManager.registerListener(mSensorListener, mManager.getDefaultSensor(sType), SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onPause() {
	    mManager.unregisterListener(mSensorListener);
	    super.onPause();
	  }
	  
	  @Override
	  protected void onDestroy() {
	    super.onDestroy();
	    if (mGravSensor != null) {
	      mManager.unregisterListener(mSensorListener);
	    }
	  }
}
