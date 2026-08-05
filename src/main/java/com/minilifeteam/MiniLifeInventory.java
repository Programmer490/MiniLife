//MiniLife inventory program file
//version 1.0-indev4 (Aug 4, 2026)
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//this project uses some code licensed under the Apache License version 2.0. This code includes the Apache Commons Lang library. This license is compatible with GPLv3.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Celeste Manguso
//Secondary Developer(s) on this file: 

package com.minilifeteam;

//imports
import java.util.List;
import java.util.ArrayList;

public class MiniLifeInventory {

    private List<String> houseList = new ArrayList<String>();
    private List<Double> houseValue = new ArrayList<Double>();
    private List<String> carsList = new ArrayList<String>();
    private List<Double> carValue = new ArrayList<Double>();
    private List<String> heirlooms = new ArrayList<String>();
    private List<Double> heirloomValue = new ArrayList<Double>();
    private List<String> awardsList = new ArrayList<String>();
    private List<String> criminalRecord = new ArrayList<String>();

    //HOUSE
        public List<String> getHouseList() {
        return houseList;
	}

        public List<Double> getHomeValueList() {
        return houseValue;
        }

	public void appendToHouseList(String house, Double value) {
        houseList.add(house);
        houseValue.add(value);
	}

    //awards
	public List<String> getAwardsList() {
        return awardsList;
	}

	public void appendToAwardsList(String award) {
        awardsList.add(award);
	}


    //CAR
	public List<String> getCarsList() {
        return carsList;
	}

        public List<Double> getCarValueList() {
        return carValue;
        }

	public void appendToCarsList(String car, Double value) {
        carsList.add(car);
        carValue.add(value);
	}

    //heirlooms
	public List<String> getHeirloomsList() {
        return heirlooms;
	}

        public List<Double> getHeirloomValueList() {
        return heirloomValue;
        }

	public void appendToHeirloomsList(String heirloom, Double value) {
        heirlooms.add(heirloom);
        heirloomValue.add(value);
	}

    //criminal record
	public List<String> getCriminalRecord() {
        return criminalRecord;
	}

	public void appendNewCriminalRecord(String crime) {
        criminalRecord.add(crime);
	}
}
