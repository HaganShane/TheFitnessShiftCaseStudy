/**
 * @author Shane Hagan
 * Date: 1/24/2023
 * Project: The Fitness Shift - Health and Wellness Program
 * Class: Sleep model class - our POJO with variables, table creation, constructors, setters / getters,
 * hashCode & equals methods, and toString method.
 */

package com.shanehagan.fitnessshift.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table
public class Sleep {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "sId", nullable = false, unique = true)
    private int sId;

    @Column (name = "hours", nullable = false)
    private double hours;

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

    public Sleep() {}

    public Sleep(double hours, LocalDate date, LocalTime time, User user) {
        this.hours = hours;
        this.date = date;
        this.time = time;
        this.user = user;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
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
        Sleep sleep = (Sleep) o;
        return sId == sleep.sId && Double.compare(sleep.hours, hours) == 0 && date.equals(sleep.date) && time.equals(sleep.time) && user.equals(sleep.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sId, hours, date, time, user);
    }

    @Override
    public String toString() {
        return "Sleep{" +
                "sId=" + sId +
                ", hours=" + hours +
                ", date=" + date +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}
