package com.example.dad.workouttracker.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    NumberPicker npSetRep1 = null;
    NumberPicker npSetRep2 = null;
    NumberPicker npSetWeight1 = null;
    NumberPicker npSetWeight2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Add calling the setup up for weight changer
       // WeightChanger(50, 100, 5,70,R.id.npSet1Weight);
        // Initialize the components
        npSetRep1 =(NumberPicker)findViewById(R.id.npSet1Reps);
        npSetRep2 =(NumberPicker)findViewById(R.id.npSet2Reps);
        npSetWeight1 = (NumberPicker)findViewById(R.id.npSet1Weight);
        npSetWeight2 = (NumberPicker)findViewById(R.id.npSet2Weight);

        // Initialize the Number Pickes
        SetupRepitions(15, npSetRep1);
        SetupRepitions(15, npSetRep2);
        WeightChanger(50, 100,5,75, npSetWeight1);
        WeightChanger(50, 100,5,95, npSetWeight2);



    }


    protected void  WeightChanger(int minValue, int maxValue, int step, int startValue, NumberPicker np) {

        int idxStartValue = 0;
        String[] valueSet = new String[(maxValue - minValue) / step +1];
        int idxPtr =0;
        for (int i = minValue; i <=maxValue; i+= step, idxPtr++){
            valueSet[idxPtr] = String.valueOf(i);
            if (startValue == i){
               idxStartValue = idxPtr;
            }
        }

        np.setMinValue(0);
        np.setMaxValue(valueSet.length - 1);

        np.setDisplayedValues(valueSet); // Added line to get a commit
        np.setWrapSelectorWheel(false);  // Do not want the selector wheel to wrap around

        np.setValue(idxStartValue);  // Set to the last known good value or the lowest
    }

    protected void SetupRepitions(int startvalue, NumberPicker npSet) {

        npSet.setMinValue(1);
        npSet.setMaxValue(25);
        npSet.setValue(15);
        npSet.setWrapSelectorWheel(false);

    }
}
