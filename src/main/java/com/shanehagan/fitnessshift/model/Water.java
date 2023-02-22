/**
 * @author Shane Hagan
 * Date: 1/24/2023
 * Project: The Fitness Shift - Health and Wellness Program
 * Class: Water model class - our POJO with variables, table creation, constructors, setters / getters,
 * hashCode & equals methods, and toString method.
 */

package com.shanehagan.fitnessshift.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table
public class Water {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "wtrId", nullable = false, unique = true)
    private int wtrId;

    @Column(name = "amount")
    private double amount;

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

    public Water() {}

    public Water(double amount, LocalDate date, LocalTime time, User user) {
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.user = user;
    }

    public int getWtrId() {
        return wtrId;
    }

    public void setWtrId(int wtrId) {
        this.wtrId = wtrId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        Water water = (Water) o;
        return wtrId == water.wtrId && Double.compare(water.amount, amount) == 0 && date.equals(water.date) && time.equals(water.time) && user.equals(water.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wtrId, amount, date, time, user);
    }

    @Override
    public String toString() {
        return "Water{" +
                "wtrId=" + wtrId +
                ", amount=" + amount +
                ", date=" + date +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}
