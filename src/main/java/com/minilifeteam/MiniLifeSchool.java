//MiniLife School Module
//Version 2.2-InDev3
//Primary Developer(s) on this file: Chelsea Dal Parsons
//Secondary Developer(s) on this file: Celeste Manguso
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file

package com.minilifeteam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MiniLifeSchool {

	private double gpa;
	private int grade;
	private boolean college;
	private boolean schoolGraduated;
	private String schoolName;
	private boolean collegeGraduated;
	private String currentDegree;
	
	public void createSchool(String nameOfSchool){
		gpa = 4.0;
		grade = 1;
		college = false;
		schoolGraduated = false;
		collegeGraduated = false;
		this.schoolName = nameOfSchool;
		this.currentDegree = "N/A";
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

	public void gpaSet(double gpa){
		this.gpa = gpa;
	}

	public int gradeGet() {
		return grade;
	}

	public void attendCollege(String nameOfCollege){
		this.college = true;
		this.gpa = 1.0;
		this.schoolName = nameOfCollege;
	}

	public void graduateCollege(){
		if (gpa >= 1.5 && grade >= 14){
			this.collegeGraduated = true;
				if (grade == 14 || grade == 15){
					this.currentDegree = "Associates Degree";
				}
				else if (grade == 16){
					this.currentDegree =  "Bachelor's Degree";
				}
				else if (grade == 18){
					this.currentDegree =  "Master's Degree";
				}
				else if (grade == 20){
					this.currentDegree =  "Doctorate";
				}
				else {
					this.currentDegree =  "unknownDegree";
				}
		}
		else {
			System.out.println("Unfortunately, you failed to graduate college. Your GPA is too low., or you have not done it for long enough.");
			System.out.println("Your GPA: " + gpaGet());
			System.out.println("Your current grade: " + gradeGet());
		}
	}

	public String getSchoolName(){
		return schoolName;
	}

	public void setSchoolName(String nameOfSchool){
		this.schoolName = nameOfSchool;
	}

	public String getDegreeName(){
		return currentDegree;
	}

	public boolean collegeGet() { 
		return college;
	}
	
	public boolean schoolgraduatedGet() {
		return schoolGraduated;
	}

	public boolean collegeGraduatedGet() {
		return collegeGraduated;
	}

	//increase year method
	public void advanceYear() {
		//increase grade
		grade++;
		//check if graduated
		if ( grade > 12 ) {
			schoolGraduated = true;
			currentDegree = "High School Diploma";
		} else { schoolGraduated = false; }
	}

}
