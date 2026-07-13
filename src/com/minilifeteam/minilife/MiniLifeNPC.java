//MiniLife NPC Module
//Version 1.1-InDev2
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: 
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam.minilife;

public class MiniLifeNPC {

	private String nameNPC;
	
	public void createNPC (String name) {
		this.nameNPC = name;
	}
	
	public String nameGet () {
		return nameNPC;
	}
}
