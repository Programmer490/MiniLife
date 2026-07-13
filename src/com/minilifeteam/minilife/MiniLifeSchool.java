//MiniLife School Module
//Version 2.0-InDev1 
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: 
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam.minilife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MiniLifeSchool {

	private double gpa;
	private int grade;
	private boolean college;
	private boolean schoolGraduated;
	
	public School(){
		gpa = 1.0;
		grade = 1;
		college = false;
		schoolGraduated = false;
	}

	//increases/decreases gpa
	public void gpaUp() {
		if (gpa < 5.0) {
			gpa += 0.5;
		} else { System.out.println ("Your GPA remains the same."); }
	}
	public void gpaDown() {
		if (gpa > 1.0) {
			gpa -= 0.5;
		} else { System.out.println ("Your GPA remains the same."); }
	}

	public double gpaGet(){
		return gpa;
	}

	public int gradeGet() {
		return grade;
	}

	public boolean collegeGet() { 
		return college;
	}
	
	public boolean schoolgraduatedGet() {
		return schoolGraduated;
	}

	//increase year method
	public void advanceYear() {
		//increase grade
		grade++;
		//check if graduated
		if ( grade > 12 ) {
			schoolGraduated = true;
		} else { schoolGraduated = false; }
	}

}
