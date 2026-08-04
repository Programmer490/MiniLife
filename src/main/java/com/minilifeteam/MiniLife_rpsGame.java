//MiniLife - RockPaperScissors minigame program 
//version 1.0-InDev3
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: 

package com.minilifeteam;

import java.util.Scanner;

public class MiniLife_rpsGame {

	public static Boolean playGame (Scanner input) {
		//range for computer
		int computer = (int)(Math.random() * 3);
	
		System.out.println("Input 0 for Rock, 1 for Paper, or 2 for Scissors:");
		int user = input.nextInt();

		String[] choices = { "Rock", "Paper", "Scissors"};
		
		System.out.println ("You chose " + choices[user]);
		System.out.println ("They chose " + choices[computer]);
	
		//declare winner
		if (user == computer) {
			System.out.println("Draw");
			return false;
		} else if ((user == 0 && computer == 2) || (user == 1 && computer == 0) || (user == 2 && computer == 1)) {
			System.out.println("You win!");
			return true;
		} else {
			System.out.println("You lose!");
			return false;
		}
	}
}