package com.example.nutrifit.data.model;

public class Workout {
    private int id;
    private int user_id;
    private String exercise_name;
    private int duration;
    private String plan_date;

    public Workout(int id, int user_id, String exercise_name, int duration, String plan_date) {
        this.id = id;
        this.user_id = user_id;
        this.exercise_name = exercise_name;
        this.duration = duration;
        this.plan_date = plan_date;
    }

    public int getId() { return id; }
    public int getUserId() { return user_id; }
    public String getExerciseName() { return exercise_name; }
    public int getDuration() { return duration; }
    public String getPlanDate() { return plan_date; }
}
