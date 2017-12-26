package com.example.dad.workouttracker.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    protected void  WeightChanger(int minValue, int maxValue, int step, int startValue, int viewId) {

        int idxStartValue = 0;
        String[] valueSet = new String[(maxValue - minValue) / step +1];
        for (int i = minValue; i <=maxValue; i+= step){
            valueSet[(i / step)-1] = String.valueOf(i);
            if (startValue == i){
                idxStartValue = i;
            }
        }

        NumberPicker np = findViewById(viewId);
        np.setMinValue(0);
        np.setMaxValue(valueSet.length - 1);

        np.setDisplayedValues(valueSet); // Added line to get a commit
        np.setWrapSelectorWheel(false);  // Do not want the selector wheel to wrap around

        np.setValue(idxStartValue);  // Set to the last known good value or the lowest
    }

}
