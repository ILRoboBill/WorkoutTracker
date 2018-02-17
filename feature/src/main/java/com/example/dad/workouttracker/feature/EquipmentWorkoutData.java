package com.example.dad.workouttracker.feature;


import android.util.Log;

/**
 * Created by Dad on 12/31/2017.
 */

public class EquipmentWorkoutData {
    private int WorkoutSet1 = 50;
    private int WorkoutSet2 = 50;
    private int RepSet1 = 15;
    private int RepSet2 = 15;
    private int initialWorkoutSet1 = 0;
    private int initialWorkoutSet2 = 0;
    private int initialRepSet1 = 0;
    private int initialRepSet2 = 0;
    private int repeatedSets = 1;


    private static final String myEquipmentPreferences= "MyWeightPrefs";
    private static final String keyEQUIPMENT_LIST = "EquipmentList";

    public void setRepSet1(int repSet1) {
        RepSet1 = repSet1;
    }

    public void setRepSet2(int repSet2) {
        RepSet2 = repSet2;
    }

    public void setWorkoutSet1(int workoutSet1) {
        WorkoutSet1 = workoutSet1;
    }

    public void setWorkoutSet2(int workoutSet2) {
        WorkoutSet2 = workoutSet2;
    }

    public int getRepSet1() {
        return RepSet1;
    }

    public int getRepSet2() {
        return RepSet2;
    }

    public int getWorkoutSet1() {
        return WorkoutSet1;
    }

    public int getWorkoutSet2() {
        return WorkoutSet2;
    }


    public String getTokenizedString() {

        //To encourage increasing the weight of workouts.  Count the number of times that a workout is the same
        Log.d("**** EquipmentWorkoutData::getTokenizedString","Rep 1 = " + Integer.toString(initialRepSet1) + "!!" + Integer.toString(RepSet1) );
        Log.d("**** EquipmentWorkoutData::getTokenizedString","Rep 1 = " + Integer.toString(initialRepSet2) + "!!" + Integer.toString(RepSet2) );
        Log.d("**** EquipmentWorkoutData::getTokenizedString","Rep 1 = " + Integer.toString(initialWorkoutSet1) + "!!" + Integer.toString(WorkoutSet1) );
        Log.d("**** EquipmentWorkoutData::getTokenizedString","Rep 1 = " + Integer.toString(initialWorkoutSet2) + "!!" + Integer.toString(WorkoutSet2) );
        if ( (initialRepSet1 == RepSet1) &&
                (initialRepSet2 == RepSet2) &&
                (initialWorkoutSet1 == WorkoutSet1) &&
                (initialWorkoutSet2 == WorkoutSet2)){

            repeatedSets++;
            Log.d("**** EquipmentWorkoutData::getTokenizedString","repeated sets increase = " + Integer.toString(repeatedSets) );
        }
        else {
            repeatedSets = 1;
            Log.d("**** EquipmentWorkoutData::getTokenizedString","No Change" );
        }
        String WorkoutInfo = Integer.toString(WorkoutSet1);
        WorkoutInfo += "," + Integer.toString(WorkoutSet2);
        WorkoutInfo += "," + Integer.toString(RepSet1);
        WorkoutInfo += "," + Integer.toString(RepSet2);
        WorkoutInfo += "," + Integer.toString(repeatedSets);

        return WorkoutInfo;
    }

    public void putStringTokenized(String delimitedWorkoutInfo){
        Log.d("**** EquipmentWorkoutData::putStringTokenized", "delimitedWorkout Info[" + delimitedWorkoutInfo + "]");

        if ( (delimitedWorkoutInfo != null) &&
                !delimitedWorkoutInfo.isEmpty() ) {

            String WorkoutInfo[] = delimitedWorkoutInfo.split(",");
            initialWorkoutSet1 = WorkoutSet1 = Integer.parseInt(WorkoutInfo[0]);
            initialWorkoutSet2 = WorkoutSet2 = Integer.parseInt(WorkoutInfo[1]);
            initialRepSet1 = RepSet1 = Integer.parseInt(WorkoutInfo[2]);
            initialRepSet2 = RepSet2 = Integer.parseInt(WorkoutInfo[3]);

            Log.d("**** EquipmentWorkoutData::putStringTokenized", "length of data =" + Integer.toString(WorkoutInfo.length));
            //  Added to support the addition of the RepeatedSets
            if (WorkoutInfo.length > 4) {
                repeatedSets = Integer.parseInt(WorkoutInfo[4]);

            }
            if (repeatedSets < 1) {
                repeatedSets = 1;
            }
        }
        else {
            initialWorkoutSet1 = WorkoutSet1 = 50;
            initialWorkoutSet2 = WorkoutSet2 = 50;
            initialRepSet1 = RepSet1 = 15;
            initialRepSet2 = RepSet2 = 15;
            repeatedSets = 1;
        }
        Log.d("**** EquipmentWorkoutData::putStringTokenized", "repeatedSets" + repeatedSets);
        return;
    }

    public String GetMotivationalMessage(){
        String motivationalMessage;
        Log.d("**** EquipmentWorkoutData::GetMotivationalMessage", "repeatedSets" + repeatedSets);
        motivationalMessage = "You have done this " + repeatedSets;
        if (repeatedSets == 1){
            motivationalMessage += " time before";
        }
        else{
            motivationalMessage += " times before";
        }


        return motivationalMessage;

    }
}
