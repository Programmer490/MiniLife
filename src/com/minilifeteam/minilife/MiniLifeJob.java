//MiniLife Job Module
//Version 1.0-InDev1 
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: 
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam.minilife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MiniLifeJob {

	private String name;
	private double salary;
	private int yearsWorked;
	private int promotions;
	private int promotionRate;

	public Job (String name, double jobSalary, int promotionRate) {
		this.name = name;
		this.salary = jobSalary;
		this.promotions = 0;
		this.yearsWorked = 0;
		this.promotionRate = promotionRate;
	}


	//get the salary
	public double getSalary() {
  		return salary;
	}
	
	//method for promotion increase
	private Random random = new Random();

	public void promotionChance() {
		if(random.nextInt(100) < promotionRate) {
			promotions++;
			salary += 5000;
			System.out.println ( "Congratulations! You've been promoted!");
		}
	}

	//advance the year and have things happen
	public void advanceYear() {
		yearsWorked++;
		promotionChance();
	}

}