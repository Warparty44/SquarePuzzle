package com.badlogic.androidgames.ch04androidbasics;

import android.app.Activity;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * Created by pippijr0 on 6/7/2017.
 */

public class AccelerometerTest extends AppCompatActivity implements SensorEventListener{
    StringBuilder builder = new StringBuilder();
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        setContentView(textView);

        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
            textView.setText("No accelerometer installed.");
        } else {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            if (!manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME)) {
                textView.setText("Couldn't register sensor listener");
            }
        }
    }

    public void onSensorChanged(SensorEvent event) {
        //final int[] as = ACCELEROMETER_AXIS_SWAP[screenRotation];
        //float screenX = (float) as[0]*event.values[as[2]];
        //float screenY = (float) as[1]*event.values[as[3]];
        //float screenZ = event.values[2];

        builder.setLength(0);
        builder.append("x: ");
        //builder.append(screenX);
        builder.append(event.values[0]);
        builder.append(", y: ");
        //builder.append(screenY);
        builder.append(event.values[1]);
        builder.append(", z: ");
        //builder.append(screenZ);
        builder.append(event.values[2]);
        textView.setText(builder.toString());
    }

    //int screenRotation;
    //public void onResume(Activity activity) {
    //    super.onResume();
    //    WindowManager windowMgr = (WindowManager) activity.getSystemService(Activity.WINDOW_SERVICE);
    //    screenRotation = windowMgr.getDefaultDisplay().getRotation();
    //}

    //static final int ACCELEROMETER_AXIS_SWAP[][] = {
    //        {1,-1,0,1},
    //        {-1,-1,1,0},
    //        {-1,1,0,1},
    //        {1,1,1,0}
    //};

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //nothing to do here
    }

}
