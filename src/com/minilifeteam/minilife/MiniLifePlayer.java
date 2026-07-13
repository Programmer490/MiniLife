//MiniLife Player Module
//Version 1.0-InDev1 
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: 
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam.minilife;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MiniLifePlayer {

	//player variables
	private String playerName;
	private int age;
	private double money;
	private int health;
	private String house;
	private String car;
	private Job currentJob;

	//initialize Player variables for use
	public Player(String firstName, String lastName) {
		this.firstName = firstName;
   		this.lastName = lastName;
		car = "None";
		house = "None";
		this.age = 0;
		this.money = 0.0;
		this.health = 100;
	}

	//HOUSE
	public String getHouse() {
    return house;
	}

	public void setHouse(String house) {
    this.house = house;
	}

	//CAR
	public String getCar() {
    return car;
	}

	public void setCar(String car) {
    this.car = car;
	}

	//NAME
	public String getFirstName() {
    		return firstName;
	}

	public String getLastName() {
    		return lastName;
	}

	public String getFullName() {
   		 return firstName + " " + lastName;
	}

	//used to age up player and progress year
	public void advanceYear() {
		age += 1;
	}
	
	public int ageGet() {
		return age;
	}
	
	//shows money
	public double getMoney() {
		return money;
	}
	//used to add money to player
	public void addMoney(double amount) {
		money += amount;
	}

	//used to take away money from player
	public void removeMoney(double amount) {
		if (amount < money) {
			money -= amount;
		}
	}
	
	//take away health
	public void takeHealth( int number ) {
		health -= number;
		if (health <= 0) {
			System.out.println ("You lost all your health. You died.");
		}
	}
	
	//add health
	public void addHealth( int number ) {
		if (health <= 100) {
			health += number;
			
			if (health > 100) {
				health = 100;
			}
		}
	}

	public int getHealth() {
		return health;
	}


	//gets the current player Job
	public Job getJob() {
		return currentJob;
	}
	//draws from the Job class to set up a job
	public void setJob(Job job) {
   		currentJob = job;
	}

//last bracket
}
