//MiniLife main program file
//version 0.1-InDev3 (Jul 19, 2026)
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//this project uses some code licensed under the Apache License version 2.0. This code includes the Apache Commons Lang library. This license is compatible with GPLv3.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Celeste Manguso
//Secondary Developer(s) on this file: 

package com.minilifeteam;

//import scanner, stuff for debugging
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.ArrayList;

public class MiniLifeMenu {

    private String sepPadLeft;
    private String sepPadRight;
    private String sepLine;
    private String elementPad;
    private int menuWidth;

    /**
     * this function creates a menu object, and assigns the shared design elements for the menu system to use later.
     * @param sepPadLeft - String - the padding character for the left end of the seperator - recommended: u25CF
     * @param sepPadRight - String - the padding character for the right end of the seperator - recommended: u25CF
     * @param sepLine - String - the line character for the seperator - recommended: u25AC
     * @param elementPad - String - the padding character for both ends of the elements - recommended: 
     * @param menuWidth - Int - the width of the menu
     */
    public void createMenu(String sepPadLeft, String sepPadRight, String sepLine, String elementPad, int menuWidth){
        this.sepPadLeft = sepPadLeft;
        this.sepPadRight = sepPadRight;
        this.sepLine = sepLine;
        this.elementPad = elementPad;
        this.menuWidth = menuWidth;
    }

    /**
     * this function displays the sepeator with the designated menu's settings.
     * @param menuWidthMinus - this is the amount to remove from the menuWidth for this specific menu element. set to 0 if you wish to have it to the full length (as initialized).
     */
    public void displaySeperator(int menuWidthMinus){
        System.out.println(StringUtils.rightPad(sepPadLeft, menuWidth - menuWidthMinus, sepLine) + sepPadRight);
    }


    //menu element block

    /**
     * This function displays a menu element in the standard format.
     * @param elementText - String - the text to be displayed as the element. this is the main body text
     * @param specialString - String - any special string to be concatenated to the end. pass a pre-concatenated string to add more.
     * @param menuWidthMinus - int - the amount to remove from the menuWidth for this specific menu element
     */
    public void menuElement(String elementText, String specialString, int menuWidthMinus){
        System.out.println(StringUtils.center( StringUtils.center((elementText + specialString), menuWidth - menuWidthMinus), menuWidth, elementPad));
    }
    
    /**
     * This function displays a menu element in the standard format.
     * @param elementText - String - the text to be displayed as the element. this is the main body text
     * @param specialInt - int - any special int to be concatenated to the end. pass a pre-concatenated int to add more.
     * @param menuWidthMinus - int - the amount to remove from the menuWidth for this specific menu element
     */
    public void menuElement(String elementText, int specialInt, int menuWidthMinus){
        System.out.println(StringUtils.center( StringUtils.center((elementText + specialInt), menuWidth - menuWidthMinus), menuWidth, elementPad));
    }

    /**
     * This function displays a menu element in the standard format.
     * @param elementText - String - the text to be displayed as the element. this is the main body text
     * @param specialBool - Boolean - any special Boolean to be concatenated to the end. pass a pre-concatenated Boolean to add more.
     * @param menuWidthMinus - int - the amount to remove from the menuWidth for this specific menu element
     */
    public void menuElement(String elementText, Boolean specialBool, int menuWidthMinus){
        System.out.println(StringUtils.center( StringUtils.center((elementText + specialBool), menuWidth - menuWidthMinus), menuWidth, elementPad));
    }

    /**
     * This function displays a menu element in the standard format.
     * @param elementText - String - the text to be displayed as the element. this is the main body text
     * @param specialDouble - double - any special double to be concatenated to the end. pass a pre-concatenated double to add more.
     * @param menuWidthMinus - int - the amount to remove from the menuWidth for this specific menu element
     */
    public void menuElement(String elementText, double specialDouble, int menuWidthMinus){
        System.out.println(StringUtils.center( StringUtils.center((elementText + specialDouble), menuWidth - menuWidthMinus), menuWidth, elementPad));
    }

}
