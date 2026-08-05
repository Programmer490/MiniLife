//MiniLife Player Module
//Version 2.5-InDev3
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: Celeste Manguso
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam;

import java.util.List;

public class MiniLifePlayer {

	//player variables
	private String firstName;
	private String lastName;
	private int age;
	private double money;
	private int health;
	private MiniLifeJob currentJob;
	private MiniLifeSchool currentSchool;
	private int playerGender;
	private MiniLifeNPC playerMother;
	private MiniLifeNPC playerFather;
	private List<MiniLifeFriend> playerFriends;
	private List<MiniLifeNPC> playerSiblings;
	private List<MiniLifeFriend> playerRomanticInterests;
	private String playerCity;
	MiniLifeInventory playerInventory = new MiniLifeInventory();
	private String playerPersonalityType;
	private int yearsProgressed = 0;



	//boolean block - flags
	private Boolean playerHasJob = false;
	private Boolean playerIsInSchool = false;
	private Boolean playerHasSiblings = false;
	private Boolean playerHasFriends = false;
	private Boolean playerIsInPrision = false;
	private Boolean playerIsASmoker = false;
	private Boolean playerDoesHaveCancer = false;
	private Boolean playerDidFailCollege = false;
	private Boolean playerHasArrestRecord = false;
	private Boolean playerHasRomanticInterest = false;
	private Boolean playerIsMarried = false;
	private Boolean playerFamilyDidWinLottery = false;
	private Boolean playerDoesHavePersonalityAssigned = false;
	private Boolean playerHasSpecialPersonality = false;
	private Boolean playerOwnsHouse = false;
	private Boolean playerOwnsCar = false;
	private Boolean playerHasHeirloom = false;
	private Boolean playerHasAchievement = false;
	private Boolean playerIsDead = false;

	

	//initialize Player variables for use
	public void createPlayer(String firstName, String lastName, int genderCode, MiniLifeNPC mother, MiniLifeNPC father, List<MiniLifeFriend> friends, String cityName) {
		this.firstName = firstName;
   		this.lastName = lastName;
		this.age = 1;
		this.money = 0.0;
		this.health = 100;
		this.playerGender = genderCode;
		this.playerMother = mother;
		this.playerFather = father;
		this.playerFriends = friends;
		this.playerCity = cityName;
	}

	/**
	 * this function returns the status of a specified Boolean value attached to the player. 
	 * @param boolID_1 - playerHasJob
	 * @param boolID_2 - playerIsInSchool
	 * @param boolID_3 - playerHasSiblings
	 * @param boolID_4 - playerHasFriends
	 * @param boolID_5 - playerIsInPrison
	 * @param boolID_6 - playerIsASmoker
	 * @param boolID_7 - playerDoesHaveCancer
	 * @param boolID_8 - playerDidFailCollege
	 * @param boolID_9 - playerHasArrestRecord
	 * @param boolID_10 - playerHasRomanticInterest
	 * @param boolID_11 - playerIsMarried
	 * @param boolID_12 - playerFamilyDidWinLottery
	 * @param boolID_13 - playerDoesHavePersonalityAssigned
	 * @param boolID_14 - playerHasSpecialPersonality
	 * @param boolID_15 - playerOwnsHouse
	 * @param boolID_16 - playerOwnsCar	 
	 * @param boolID_17 - playerHasHeirloom
	 * @param boolID_18 - playerHasAchievement
	 * @param boolID_19 - playerIsDead
	 * @param boolID - pass one of the above numbers as the argument for this function to get the status of the named boolean.
	 * @return Boolean status of the specified boolID
	 */

	public Boolean getPlayerBooleanInfo(int boolID){
		switch(boolID){
			case 1:
				return playerHasJob;
			case 2: 
				return playerIsInSchool;
			case 3:
				return playerHasSiblings;
			case 4:
				return playerHasFriends;
			case 5: 
				return playerIsInPrision;
			case 6:
				return playerIsASmoker;
			case 7:
				return playerDoesHaveCancer;
			case 8:
				return playerDidFailCollege;
			case 9:
				return playerHasArrestRecord;
			case 10:
				return playerHasRomanticInterest;
			case 11:
				return playerIsMarried;
			case 12:
				return playerFamilyDidWinLottery;
			case 13:
				return playerDoesHavePersonalityAssigned;
			case 14:
				return playerHasSpecialPersonality;
			case 15:
				return playerOwnsHouse;
			case 16:
				return playerOwnsCar;
			case 17:
				return playerHasHeirloom;
			case 18:
				return playerHasAchievement;
			case 19:
				return playerIsDead;
			default:
				System.out.println("###Error! Logic Error occured in getPlayerBooleanInfo. Result returned false as failsafe.###");
				return false;
		}
	}

	/**
	 * this function sets a new status for a specified boolean in the player boolean block.
	 * @param boolID - pass one of the below numbers as the argument for this function to set the named boolean.
	 * @param boolID_1 - playerHasJob
	 * @param boolID_2 - playerIsInSchool
	 * @param boolID_3 - playerHasSiblings
	 * @param boolID_4 - playerHasFriends
	 * @param boolID_5 - playerIsInPrison
	 * @param boolID_6 - playerIsASmoker
	 * @param boolID_7 - playerDoesHaveCancer
	 * @param boolID_8 - playerDidFailCollege
	 * @param boolID_9 - playerHasArrestRecord
	 * @param boolID_10 - playerHasRomanticInterest
	 * @param boolID_11 - playerIsMarried
	 * @param boolID_12 - playerFamilyDidWinLottery
	 * @param boolID_13 - playerDoesHavePersonalityAssigned
	 * @param boolID_14 - playerHasSpecialPersonality
	 * @param boolID_15 - playerOwnsHouse
	 * @param boolID_16 - playerOwnsCar
	 * @param boolID_17 - playerHasHeirloom	 
	 * @param boolID_18 - playerHasAchievement
	 * @param boolID_19 - playerIsDead
	 * @param newStatus - Boolean to set as the new status for the specified boolID
	 */

	public void setPlayerBooleanInfo(int boolID, Boolean newStatus){
		switch(boolID){
			case 1:
				this.playerHasJob = newStatus;
				break;
			case 2: 
				this.playerIsInSchool = newStatus;
				break;
			case 3:
				this.playerHasSiblings = newStatus;
				break;
			case 4:
				this.playerHasFriends = newStatus;
				break;
			case 5: 
				 this.playerIsInPrision = newStatus;
				 break;
			case 6:
				 this.playerIsASmoker = newStatus;
				 break;
			case 7:
				 this.playerDoesHaveCancer = newStatus;
				 break;
			case 8:
				 this.playerDidFailCollege = newStatus;
				 break;
			case 9:
				 this.playerHasArrestRecord = newStatus;
				 break;
			case 10:
				this.playerHasRomanticInterest = newStatus;
				break;
			case 11:
				this.playerIsMarried = newStatus;
				break;
			case 12:
				this.playerFamilyDidWinLottery = newStatus;
				break;
			case 13:
				this.playerDoesHavePersonalityAssigned = newStatus;
				break;
			case 14:
				this.playerHasSpecialPersonality = newStatus;
				break;
			case 15:
				this.playerOwnsHouse = newStatus;
				break;
			case 16:
				this.playerOwnsCar = newStatus;
				break;
			case 17:
				this.playerHasHeirloom = newStatus;
				break;
			case 18:
				this.playerHasAchievement = newStatus;
				break;
			case 19:
				this.playerIsDead = newStatus;
				break;
			default:
				System.out.println("###Error! Logic Error occured in setPlayerBooleanInfo. Unable to set specified boolID.###");
				break;
		}
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

	//personality
	/**
	 * sets the player's personality type according to the following list:
	 * @param personalityCode - see below list for available options to pass to this argument.
	 * @param personalityCode_1 - Friendly-Cheerful
	 * @param personalityCode_2 - Friendly-Shy
	 * @param personalityCode_3 - Emotional-Shy
	 * @param personalityCode_4 - Emotional-Withdrawn
	 * @param personalityCode_5 - Angry-Withdrawn
	 * @param personalityCode_6 - Angry-Outward
	 * @param personalityCode_7 - Depressed
	 * @param personalityCode_8 - Delinquent
	 * @param personalityCode_9 - Criminal
	 */
	public void setPersonalityType(int personalityCode){
		switch(personalityCode){
			case 1:
				playerPersonalityType = "Friendly-Cheerful";
				this.playerDoesHavePersonalityAssigned = true;
				break;
			case 2:
				playerPersonalityType = "Friendly-Shy";
				this.playerDoesHavePersonalityAssigned = true;
				break;
			case 3:
				playerPersonalityType = "Emotional-Shy";
				this.playerDoesHavePersonalityAssigned = true;
				break;
			case 4:
				playerPersonalityType = "Emotional-Withdrawn";
				this.playerDoesHavePersonalityAssigned = true;
				break;
			case 5:
				playerPersonalityType = "Angry-Withdrawn";
				this.playerDoesHavePersonalityAssigned = true;
				break;
			case 6:
				playerPersonalityType = "Angry-Outward";
				this.playerDoesHavePersonalityAssigned = true;
				break;
			case 7:
				playerPersonalityType = "Depressed";
				this.playerDoesHavePersonalityAssigned = true;
				break;
			case 8:
				playerPersonalityType = "Delinquent";
				this.playerDoesHavePersonalityAssigned = true;
				this.playerHasSpecialPersonality = true;
				break;
			case 9:
				playerPersonalityType = "Criminal";
				this.playerDoesHavePersonalityAssigned = true;
				this.playerHasSpecialPersonality = true;
				break;
		}
	}

	public String getPlayerPersonality(){
		return playerPersonalityType;
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

	public void setFriendsList(List<MiniLifeFriend> newFriendsList){
		this.playerFriends = newFriendsList;
		playerHasFriends = true;
	}

	public List<MiniLifeFriend> getRomanceList(){
		return playerRomanticInterests;
	}

	public void setRomanceList(List<MiniLifeFriend> newRomanceList){
		this.playerRomanticInterests = newRomanceList;
		setPlayerBooleanInfo(10, true);
	}


	// public Boolean doesPlayerHaveSiblings(){
	// 	return playerHasSiblings;
	// }

	// public Boolean doesPlayerHaveFriends(){
	// 	return playerHasFriends;
	// }

	// public void setSiblingsStatus(Boolean doesPlayerHaveSiblings){
	// 	this.playerHasSiblings = false;
	// }

	// public void setFriendsStatus(Boolean doesPlayerHaveFriends){
	// 	this.playerHasFriends = false;
	// }

	//city
	public String getPlayerCity() {
		return playerCity;
	}
	

	public void updatePlayerCity(String newCityName){
		this.playerCity = newCityName;
	}

	//inventory
	public void setPlayerInventory(MiniLifeInventory inv) {
		this.playerInventory = inv;
	}

	public MiniLifeInventory getInventory(){
		return playerInventory;
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
		yearsProgressed++;
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

	public int getYearsPlayed(){
		return yearsProgressed;
	}

//last bracket
}
