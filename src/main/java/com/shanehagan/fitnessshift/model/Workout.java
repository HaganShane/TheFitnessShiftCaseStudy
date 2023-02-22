/**
 * @author Shane Hagan
 * Date: 1/24/2023
 * Project: The Fitness Shift - Health and Wellness Program
 * Class: Workout model class - our POJO with variables, table creation, constructors, setters / getters,
 * hashCode & equals methods, and toString method.
 */

package com.shanehagan.fitnessshift.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table
public class Workout {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "wrkId", nullable = false, unique = true)
    private int wrkId;

    @Column(name = "workoutName", length = 50, nullable = false)
    private String workoutName;

    @Column(name = "workoutCategory", length = 50)
    private String workoutCategory;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "duration")
    private int duration;

    @Column(name = "equipment", length = 100)
    private String equipment;

    /**
     * Changed from ManyToOne to OneToOne, pointing back to the user class
     * Assisted in putting the userId in the model table to track which user inputs a given record.
     * Useful when grabbing all records by a userId
     */
    @ManyToOne(targetEntity = User.class)
    private User user;

    public Workout() {}

    public Workout(String workoutName, String workoutCategory, LocalDate date, LocalTime time, int duration,
                   String equipment, User user) {
        this.workoutName = workoutName;
        this.workoutCategory = workoutCategory;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.equipment = equipment;
        this.user = user;
    }

    public int getWrkId() {
        return wrkId;
    }

    public void setWrkId(int wrkId) {
        this.wrkId = wrkId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutCategory() {
        return workoutCategory;
    }

    public void setWorkoutCategory(String workoutCategory) {
        this.workoutCategory = workoutCategory;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return wrkId == workout.wrkId && duration == workout.duration && workoutName.equals(workout.workoutName) && workoutCategory.equals(workout.workoutCategory) && date.equals(workout.date) && time.equals(workout.time) && equipment.equals(workout.equipment) && user.equals(workout.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wrkId, workoutName, workoutCategory, date, time, duration, equipment, user);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "wrkId=" + wrkId +
                ", workoutName='" + workoutName + '\'' +
                ", workoutCategory='" + workoutCategory + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", equipment='" + equipment + '\'' +
                ", user=" + user +
                '}';
    }
}
