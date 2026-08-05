//MiniLife - Math minigame program 
//version 1.0-InDev3
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Monse Olvera
//Secondary Developer(s) on this file: 

package com.minilifeteam;

import java.util.Scanner; 
import java.util.InputMismatchException;

public class mathgame {
	
		public static boolean play(Scanner input) {
		
		//generate random numbers
		int num1 = (int)(Math.random() * 10) + 1;
		int num2 = (int)(Math.random() * 10) + 1;
		
		//calculate the final answer
		int answer = num1 + num2;
		
		//System asks the question
		System.out.println("Welcome to Math quiz! ");
		System.out.print("What is " + num1 + " + " + num2 + " ? ");
		
		//Gets players answer
		int playerAnswer = 0;
		Boolean runAnswer = true;
		while (runAnswer){
			try {
				playerAnswer = input.nextInt();
				runAnswer = false;
			} catch(InputMismatchException e){
				System.out.println("Error! Unknown input!");
				input.next();
				playerAnswer = 0;
				runAnswer = true;
			}
		}
		
		if (playerAnswer == answer) {
			System.out.println("You are correct!");
			return true;
		}
		else { 
			System.out.println("You are wrong");
			System.out.println("The answer was " + answer);
			return false;
		}
		
	}
}
