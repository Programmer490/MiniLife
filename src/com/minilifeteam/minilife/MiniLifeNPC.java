//MiniLife NPC Module
//Version 1.2-InDev3
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: 
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam.minilife;

public class MiniLifeNPC {

	private String nameNPC;
	private String lastNameNPC;
	private int npcAge;
	
	public void createNPC (String name, String lastName, int ageOfNPC) {
		this.nameNPC = name;
		this.lastNameNPC = lastName;
		this.npcAge = ageOfNPC;
	}
	
	public String nameGet () {
		return nameNPC;
	}

	public String getLastName(){
		return lastNameNPC;
	}

	public int getAge(){
		return npcAge;
	}

	public void changeFirstName(String newName){
		this.nameNPC = newName;
	}
	public void changeLastName(String newLastName){
		this.lastNameNPC = newLastName;
	}

	public void advanceYear(){
		this.npcAge++;
	}

}
