package com.example.dad.workouttracker.feature;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    NumberPicker npSetRep1 = null;
    NumberPicker npSetRep2 = null;
    NumberPicker npSetWeight1 = null;
    NumberPicker npSetWeight2 = null;

    private static final String myEquipmentPreferences= "MyWeightPrefs";
    private static final String keyEQUIPMENT_LIST = "EquipmentList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity:OnCreate","Did this actually work?");

        // TODO: add the ability to pull Excercises from stored memory
        // TODO: add the ability to store Excercises to memory
        // TODO: remove hard coding of excercises
        // Temporary Initialize Weight Equipment
        TempWriteEquipmentList();


       // WeightChanger(50, 100, 5,70,R.id.npSet1Weight);
        // Initialize the components
        npSetRep1 =findViewById(R.id.npSet1Reps);
        npSetRep2 =findViewById(R.id.npSet2Reps);
        npSetWeight1 = findViewById(R.id.npSet1Weight);
        npSetWeight2 = findViewById(R.id.npSet2Weight);


        // Initialize the Number Pickes
        SetupRepitions(15, npSetRep1);
        SetupRepitions(15, npSetRep2);
        WeightChanger(50, 100,5,75, npSetWeight1);
        WeightChanger(50, 100,5,95, npSetWeight2);

        addListenerOnSpinnerItemSelection();

    }

    /**
     * This function creates the different Weight values for an excercise.  It creates
     * strings for all of the values and then selects the startvalue
     * @param minValue - Lowest weight
     * @param maxValue - Maximum weight on the equipment
     * @param step - Step for each weight
     * @param startValue - Value that the Number Picker should start at
     * @param np - Number Picker that the values are for
     */
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
        npSet.setValue(startvalue);
        npSet.setWrapSelectorWheel(false);

    }

    public void addListenerOnSpinnerItemSelection() {
        Spinner spinner1 = findViewById(R.id.spnrExcercises);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,GetListOfEquipment());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(aa);

        spinner1.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        String SelectedWeight = parent.getItemAtPosition(pos).toString();
        Log.d("WeightChangeListener:onItemSelected","On Item Selected:" + SelectedWeight);
        Log.d("WeightChangeListener::OnItemSelected","Id:" + R.id.npSet1Weight);

        npSetWeight1 = findViewById(R.id.npSet1Weight);
        npSetWeight2 = findViewById(R.id.npSet2Weight);

        if(SelectedWeight.equalsIgnoreCase("Chest Press")){
            WeightChanger(50,200,5,190,npSetWeight1);
            WeightChanger( 50,200,5,50,npSetWeight2);
            Log.d("**** WeightChangeListener:OnSelected", "Changed Weight Values");
        }else if(SelectedWeight == "Row") {
            // TODO: Set the appropriate Weights and Repititions for Chest Press
        }else if (SelectedWeight == "Twister") {
            // TODO: Set the appropriate Weights and Repititions for Twister
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * This function reads the List of Equipment that are being used for excercise.
     * @return - ArrayList of equipment
     */
    protected List<String> GetListOfEquipment(){
        SharedPreferences EquipmentInfo = getSharedPreferences(myEquipmentPreferences, Context.MODE_PRIVATE);
        String EquipmentStringList[] = EquipmentInfo.getString(keyEQUIPMENT_LIST, "None").split(",");
        List<String> EquipmentList = new ArrayList<>();

        for (String Equipment : EquipmentStringList) EquipmentList.add(Equipment);

        return EquipmentList;
    }

    protected void TempWriteEquipmentList(){
        SharedPreferences EquipmentInfo = getSharedPreferences(myEquipmentPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = EquipmentInfo.edit();
        editor.putString(keyEQUIPMENT_LIST,"Chest Press,Shoulder Press,Abdominal,Row-Rear Deltoid,Pulldown,Fly,Triceps Press,Torso Rotation,Hip Adduction,Hip Abduction,Back Extension");
        editor.commit();
    }
}
