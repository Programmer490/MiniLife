//MiniLife Friend Module
//Version 1.0-InDev1 
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: 
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam.minilife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//allows more indepth friend and relationship status
public class MiniLifeFriend  {

		private String name;
		private int relationship;

		public MiniLifeFriend(String nameFriend) {
			this.name = nameFriend;
			this.relationship = 50;
		}
		public String getFriendName() {
			return name;
		}
		public int getRelationship() {
			return relationship;
		}
		public void characterplusRelationship(int amount) {
			relationship += amount;
		}
		public void characternegativeRelationship(int amount) {
			relationship -= amount;
		}
	
		//list for names for friends/etc 
		private String[] Names = {"James", "Amanda", "Jessica", "Lily", "Arnold", "John", "Claire", "Shawnda", "Leroy", "Lamar", "Alex", "Chuck", "Lenny", "Lynn", "Dennis", "Reggie", "Ronaldo", "Carlos", "Carla"};

		private Random random = new Random();

		//Return a random name 
		public String GetRandomName() {
		int nameindex = random.nextInt(Names.length);
		return Names[nameindex]; }

}