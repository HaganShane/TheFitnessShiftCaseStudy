/**
 * @author Shane Hagan
 * Date: 1/24/2023
 * Project: The Fitness Shift - Health and Wellness Program
 * Class: User model class - our POJO with variables, table creation, constructors, setters / getters,
 * hashCode & equals methods, and toString method.
 * Also mapping the OneToMany to each of our other models for joined tables to pull our data
 * from.
 */

package com.shanehagan.fitnessshift.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "uId", nullable = false, unique = true)
    private int uId;

    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phoneNumber", length = 12)
    private String phoneNumber;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private int height;

    /**
     * Changed from ManyToOne to OneToOne, pointing back to the workout class
     * Assisted in putting the userId in the model table to track which user inputs a given record.
     * Useful when grabbing all records by a userId
     * Mapped by the user
     */
    @OneToMany(mappedBy = "user", targetEntity = Workout.class, cascade = CascadeType.ALL)
    private List<Workout> userWorkouts;

    /**
     * Changed from ManyToOne to OneToOne, pointing back to the food class
     * Assisted in putting the userId in the model table to track which user inputs a given record.
     * Useful when grabbing all records by a userId
     * Mapped by the user
     */
    @OneToMany(mappedBy = "user", targetEntity = Food.class, cascade = CascadeType.ALL)
    private List<Food> userFood;

    /**
     * Changed from ManyToOne to OneToOne, pointing back to the sleep class
     * Assisted in putting the userId in the model table to track which user inputs a given record.
     * Useful when grabbing all records by a userId
     * Mapped by the user
     */
    @OneToMany(mappedBy = "user", targetEntity = Sleep.class, cascade = CascadeType.ALL)
    private List<Sleep> userSleep;

    /**
     * Changed from ManyToOne to OneToOne, pointing back to the water class
     * Assisted in putting the userId in the model table to track which user inputs a given record.
     * Useful when grabbing all records by a userId
     * Mapped by the user
     */
    @OneToMany(mappedBy = "user", targetEntity = Water.class, cascade = CascadeType.ALL)
    private List<Water> userWater;

    public User() {}

    public User(String firstName, String lastName, String email, String phoneNumber, String dateOfBirth, double weight,
                int height, List<Workout> userWorkouts, List<Food> userFood, List<Sleep> userSleep, List<Water> userWater) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.userWorkouts = userWorkouts;
        this.userFood = userFood;
        this.userSleep = userSleep;
        this.userWater = userWater;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Workout> getUserWorkouts() {
        return userWorkouts;
    }

    public void setUserWorkouts(List<Workout> userWorkouts) {
        this.userWorkouts = userWorkouts;
    }

    public List<Food> getUserFood() {
        return userFood;
    }

    public void setUserFood(List<Food> userFood) {
        this.userFood = userFood;
    }

    public List<Sleep> getUserSleep() {
        return userSleep;
    }

    public void setUserSleep(List<Sleep> userSleep) {
        this.userSleep = userSleep;
    }

    public List<Water> getUserWater() {
        return userWater;
    }

    public void setUserWater(List<Water> userWater) {
        this.userWater = userWater;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth, email, firstName, height, lastName, password, phoneNumber, uId, userFood,
                userSleep, userWater, userWorkouts, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName) && height == other.height
                && Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
                && Objects.equals(phoneNumber, other.phoneNumber) && uId == other.uId
                && Objects.equals(userFood, other.userFood) && Objects.equals(userSleep, other.userSleep)
                && Objects.equals(userWater, other.userWater) && Objects.equals(userWorkouts, other.userWorkouts)
                && Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
    }

    @Override
    public String toString() {
        return "User [uId=" + uId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth
                + ", weight=" + weight + ", height=" + height + ", userWorkouts=" + userWorkouts + ", userFood="
                + userFood + ", userSleep=" + userSleep + ", userWater=" + userWater + "]";
    }
}
