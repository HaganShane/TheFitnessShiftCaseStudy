/**
 * @author Shane Hagan
 * Date: 1/24/2023
 * Project: The Fitness Shift - Health and Wellness Program
 * Class: Food model class - our POJO with variables, table creation, constructors, setters / getters,
 * hashCode & equals methods, and toString method.
 */

package com.shanehagan.fitnessshift.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table
public class Food {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "fId", nullable = false, unique = true)
    private int fId;

    @Column(name = "mealName", nullable = false)
    private String mealName;

    @Column(name = "mealCategory")
    private String mealCategory;

    @Column(name = "calories")
    private int calories;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    /**
     * Changed from ManyToOne to OneToOne, pointing back to the user class
     * Assisted in putting the userId in the model table to track which user inputs a given record.
     * Useful when grabbing all records by a userId
     */
    @ManyToOne(targetEntity = User.class)
    private User user;

    public Food() {}

    public Food(String mealName, String mealCategory, int calories, LocalDate date, LocalTime time, User user) {
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.calories = calories;
        this.date = date;
        this.time = time;
        this.user = user;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
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
        Food food = (Food) o;
        return fId == food.fId && calories == food.calories && mealName.equals(food.mealName) && mealCategory.equals(food.mealCategory) && date.equals(food.date) && time.equals(food.time) && user.equals(food.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fId, mealName, mealCategory, calories, date, time, user);
    }

    @Override
    public String toString() {
        return "Food{" +
                "fId=" + fId +
                ", mealName='" + mealName + '\'' +
                ", mealCategory='" + mealCategory + '\'' +
                ", calories=" + calories +
                ", date=" + date +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}
