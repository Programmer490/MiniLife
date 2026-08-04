//MiniLife - Coin Flip minigame program 
//version 1.0-InDev3
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Monse Olvera
//Secondary Developer(s) on this file: 

package com.minilifeteam;

import java.util.Random;
import java.util.Scanner;

public class coinflip {
	
		// Create scanner and random object
		public static boolean play(Scanner input) {
		Random random = new Random();
		
		String choice;
		int coin;
		
		//get players choice
		System.out.println(" Heads or Tails ");
		System.out.print("Enter Heads or Tails: ");
		choice = input.next();
		choice = choice.toLowerCase();
		
		//Coin flip
		coin = random.nextInt(2);
		
		//Results
		if (coin == 0) {
			System.out.println("The coin landed on Heads.");
			if (choice.equals("heads")) {
				System.out.println("You guessed correct!");
				return true;
			}
			else {
				System.out.println("You guessed wrong!");
				return false;
			}
		}
		else {
			System.out.println("The coin landed on Tails. ");
			if (choice.equals("tails")) {
				System.out.println("You guessed correct!");
				return true;
			}
			else {
				System.out.println("You guessed wrong!");
				return false;
		}
		
		}
		
	}

}
