package com.example.juano.accelerometertest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorGyroscope;
    private Sensor mSensorMagnetometer;

    private TextView txt_message_accelerometer;
    private TextView txt_a_x;
    private TextView txt_a_y;
    private TextView txt_a_z;

    private TextView txt_message_gyroscope;
    private TextView txt_g_x;
    private TextView txt_g_y;
    private TextView txt_g_z;

    private TextView txt_message_magnetometer;
    private TextView txt_m_x;
    private TextView txt_m_y;
    private TextView txt_m_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        txt_message_accelerometer = (TextView) findViewById(R.id.txt_message_accelerometer);
        txt_a_x = (TextView) findViewById(R.id.txt_a_x);
        txt_a_y = (TextView) findViewById(R.id.txt_a_y);
        txt_a_z = (TextView) findViewById(R.id.txt_a_z);

        mSensorAccelerometer  = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mSensorAccelerometer == null){
            txt_message_accelerometer.setText("Lo sentimos pero no se detecta un accelerometro");
        }else{
            txt_message_accelerometer.setText("ya se detecto el accelerometro");
        }

        txt_message_gyroscope = (TextView) findViewById(R.id.txt_message_gyroscope);
        txt_g_x = (TextView) findViewById(R.id.txt_g_x);
        txt_g_y = (TextView) findViewById(R.id.txt_g_y);
        txt_g_z = (TextView) findViewById(R.id.txt_g_z);

        mSensorGyroscope  = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mSensorGyroscope == null){
            txt_message_gyroscope.setText("Lo sentimos pero no se detecta un accelerometro");
        }else{
            txt_message_gyroscope.setText("ya se detecto el giroscopio");
        }

        txt_message_magnetometer = (TextView) findViewById(R.id.txt_message_magnetometer);
        txt_m_x = (TextView) findViewById(R.id.txt_m_x);
        txt_m_y = (TextView) findViewById(R.id.txt_m_y);
        txt_m_z = (TextView) findViewById(R.id.txt_m_z);

        mSensorMagnetometer  = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mSensorMagnetometer == null){
            txt_message_magnetometer.setText("Lo sentimos pero no se detecta un accelerometro");
        }else{
            txt_message_magnetometer.setText("ya se detecto el magnetometro");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mSensorGyroscope != null) {
            mSensorManager.registerListener(this, mSensorGyroscope,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mSensorMagnetometer != null) {
            mSensorManager.registerListener(this, mSensorMagnetometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                if (sensorEvent.values.length >= 3){
                    txt_a_x.setText("x = "+this.round(sensorEvent.values[0], 4));
                    txt_a_y.setText("y = "+this.round(sensorEvent.values[1], 4));
                    txt_a_z.setText("z = "+this.round(sensorEvent.values[2], 4));
                }
                break;
            case Sensor.TYPE_GYROSCOPE:
                if (sensorEvent.values.length >= 3){
                    txt_g_x.setText("x = "+this.round(sensorEvent.values[0], 4));
                    txt_g_y.setText("y = "+this.round(sensorEvent.values[1], 4));
                    txt_g_z.setText("z = "+this.round(sensorEvent.values[2], 4));
                }
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                if (sensorEvent.values.length >= 3){
                    txt_m_x.setText("x = "+this.round(sensorEvent.values[0], 4));
                    txt_m_y.setText("y = "+this.round(sensorEvent.values[1], 4));
                    txt_m_z.setText("z = "+this.round(sensorEvent.values[2], 4));
                }
                break;
            default:
                // do nothing
        }
    }

    public static float round(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.i("AccuracyChanged i:"+i, sensor.toString());
    }
}
