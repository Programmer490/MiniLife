//MiniLife Friend Module
//Version 1.1-InDev3 
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: 
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//allows more indepth friend and relationship status
public class MiniLifeFriend  {

		private String name;
		private String lastName;
		private int friendAge;
		private int relationship;

		public void createFriend(String nameFriend, String lastNameFriend, int age) {
			this.name = nameFriend;
			this.lastName = lastNameFriend;
			this.friendAge = age;
			this.relationship = 75;
		}
		public String getFriendName() {
			return name;
		}
		public String getLastName() {
			return lastName;
		}
		public void updateFirstName(String newName){
			this.name = newName;
		}
		public void updateLastName(String newLast){
			this.lastName = newLast;
		}

		public int getAge(){
			return friendAge;
		}



		public int getRelationship() {
			return relationship;
		}
		public void friendRelationshipImprove(int amount) {
			relationship += amount;
		}
		public void friendRelationshipDecline(int amount) {
			relationship -= amount;
		}

		public void advanceYear(){
			this.friendAge++;
			//this.relationship -= 5;
		}
	
		// //list for names for friends/etc 
		// private String[] Names = {"James", "Amanda", "Jessica", "Lily", "Arnold", "John", "Claire", "Shawnda", "Leroy", "Lamar", "Alex", "Chuck", "Lenny", "Lynn", "Dennis", "Reggie", "Ronaldo", "Carlos", "Carla"};

		// private Random random = new Random();

		// //Return a random name 
		// public String GetRandomName() {
		// int nameindex = random.nextInt(Names.length);
		// return Names[nameindex]; }

}