//MiniLife Player Module
//Version 2.0-InDev3
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: Celeste Manguso
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam.minilife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

public class MiniLifePlayer {

	//player variables
	private String firstName;
	private String lastName;
	private int age;
	private double money;
	private int health;
	private String house;
	private String car;
	private MiniLifeJob currentJob;
	private MiniLifeSchool currentSchool;
	private int playerGender;
	private MiniLifeNPC playerMother;
	private MiniLifeNPC playerFather;
	private List<MiniLifeFriend> playerFriends;
	private List<MiniLifeNPC> playerSiblings;
	private String playerCity;
	private Boolean playerHasJob = false;
	private Boolean playerIsInSchool = false;
	private Boolean playerHasSiblings = false;
	private Boolean playerHasFriends = false;

	//initialize Player variables for use
	public void createPlayer(String firstName, String lastName, int genderCode, MiniLifeNPC mother, MiniLifeNPC father, List<MiniLifeFriend> friends, String cityName) {
		this.firstName = firstName;
   		this.lastName = lastName;
		car = "None";
		house = "None";
		this.age = 1;
		this.money = 0.0;
		this.health = 100;
		this.playerGender = genderCode;
		this.playerMother = mother;
		this.playerFather = father;
		this.playerFriends = friends;
		this.playerCity = cityName;
		
	}

	//HOUSE
	public String getHouse() {
    return house;
	}

	public void setHouse(String house) {
    this.house = house;
	}

	//gender
	public void setGender(int genderCode){
		this.playerGender = genderCode;
	}

	public String getPlayerGender(){
		if (this.playerGender == 0){
			return "Female";
		}
		else if (this.playerGender == 1){
			return "Male";
		}
		else if (this.playerGender == 2){
			return "Non-Binary";
		}
		else {
			return "UnknownGender";
		}
	}

	//friends and relatives
	public MiniLifeNPC getPlayerMother(){
		return playerMother;
	}

	public MiniLifeNPC getPlayerFather(){
		return playerFather;
	}

	public void updateSiblingsList(List<MiniLifeNPC> newSiblingsList){
		this.playerSiblings = newSiblingsList;
		playerHasSiblings = true;
	}

	public List<MiniLifeNPC> getPlayerSiblings(){
		return playerSiblings;
	}

	public List<MiniLifeFriend> getFriendsList(){
		return playerFriends;
	}

	public void updateFriendsList(List<MiniLifeFriend> newFriendsList){
		this.playerFriends = newFriendsList;
		playerHasFriends = true;
	}

	public Boolean doesPlayerHaveSiblings(){
		return playerHasSiblings;
	}

	public Boolean doesPlayerHaveFriends(){
		return playerHasFriends;
	}

	public void setSiblingsStatus(Boolean doesPlayerHaveSiblings){
		this.playerHasSiblings = false;
	}

	public void setFriendsStatus(Boolean doesPlayerHaveFriends){
		this.playerHasFriends = false;
	}

	//city
	public String getPlayerCity() {
		return playerCity;
	}

	public void updatePlayerCity(String newCityName){
		this.playerCity = newCityName;
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

	public void changeFirstName(String newName) {
		this.firstName = newName;
	}

	public void changeLastName(String newName) {
		this.lastName = newName;
	}

	//used to age up player and progress year
	public void advanceYear() {
		age += 1;
	}
	
	public int getAge() {
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
	public MiniLifeJob getJob() {
		return currentJob;
	}

	//returns true if the player has a job
	public Boolean doesPlayerHaveJob(){
		return playerHasJob;
	}

	//draws from the Job class to set up a job
	public void setJob(MiniLifeJob job) {
   		currentJob = job;
		playerHasJob = true;
	}

	public void setJobStatus(Boolean playerHasJob){
		this.playerHasJob = playerHasJob;
	}

	//gets the current player school
	public MiniLifeSchool getSchool() {
		return currentSchool;
	}

	//returns true if the player is in school.
	public Boolean isPlayerInSchool(){
		return playerIsInSchool;
	}

	public void setInSchoolStatus(Boolean playerIsInSchool){
		this.playerIsInSchool = playerIsInSchool;
	}


	//draws from the school class to attach a school object
	public void setSchool(MiniLifeSchool school) {
   		currentSchool = school;
		if (this.age > 5){
			this.playerIsInSchool = true;
		}
	}

//last bracket
}
