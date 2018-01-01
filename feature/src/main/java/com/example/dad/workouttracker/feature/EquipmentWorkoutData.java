package com.example.dad.workouttracker.feature;


import android.util.Log;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dad on 12/31/2017.
 */

public class EquipmentWorkoutData {
    private int WorkoutSet1 = 50;
    private int WorkoutSet2 = 50;
    private int RepSet1 = 15;
    private int RepSet2 = 15;

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
        String WorkoutInfo = Integer.toString(WorkoutSet1);
        WorkoutInfo += "," + Integer.toString(WorkoutSet2);
        WorkoutInfo += "," + Integer.toString(RepSet1);
        WorkoutInfo += "," + Integer.toString(RepSet2);

        return WorkoutInfo;
    }

    public void putStringTokenized(String delimitedWorkoutInfo){
        String WorkoutInfo[] = delimitedWorkoutInfo.split(",");
        setWorkoutSet1(Integer.parseInt(WorkoutInfo[0]));
        setWorkoutSet2(Integer.parseInt(WorkoutInfo[1]));
        setRepSet1(Integer.parseInt(WorkoutInfo[2]));
        setRepSet2(Integer.parseInt(WorkoutInfo[3]));

        return;
    }
}
