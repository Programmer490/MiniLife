//MiniLife - WordGame minigame program (based on Wordle by the New York Times)
//version 0.2-InDev2 (Jul 13, 2026)
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Celeste Manguso
//Secondary Developer(s) on this file: 

package com.minilifeteam;

//import scanner, stuff for debugging
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.concurrent.ThreadLocalRandom;



public class MiniLife_WordGame {
    public static Boolean doRunMinigameMenu = true;

    public static Boolean launchWordGame(Scanner input, MiniLifeDialog dialogModule, Logger logger, Boolean isMinigameExitable, Boolean isDebug){
        logger.info("##DEBUG## - Minigame1 Launched - WordGame");

        //introduce the minigame
        System.out.println("Welcome to Word Game");

        doRunMinigameMenu = true;

        //launch the minigame menu
        while (MiniLife_WordGame.doRunMinigameMenu == true){
            String getWGMenuChoice = wordGameMenu(input, dialogModule, logger, isMinigameExitable, isDebug);

            if (getWGMenuChoice.contentEquals("LaunchMinigame")){
                logger.info("##DEBUG## - user chose to launch the Word Game minigame.");
                MiniLife_WordGame.doRunMinigameMenu = false;
                //playMinigame(input, dialogModule);
            }
            else if (getWGMenuChoice.contentEquals("RulesMenu")){
                //loads the rules screen
                logger.info("##DEBUG## - user chose to load the rules menu.");
                MiniLife_WordGame.doRunMinigameMenu = false;
                //displayRulesMenu(input, dialogModule);
            }
            else if (getWGMenuChoice.contentEquals("ExitMinigame")){
                if (isMinigameExitable){
                    //exits the minigame
                    logger.info("##DEBUG## - user chose to exit program. exiting...");
                    return false;
                }
                else {
                    //the minigame cannot be exited, runs the menu again.
                    System.out.println("This minigame is mandatory! you cannot exit now.");
                    MiniLife_WordGame.doRunMinigameMenu = true;
                    continue;
                }
            }
            else if (getWGMenuChoice.contentEquals("LaunchDebug")){
                logger.info("##DEBUG## - WordGame Debug Menu Launching...");
                //wgDebugMenu(input, dialogModule, logger, isMinigameExitable)
            }
            else if (getWGMenuChoice.contentEquals("ExitedLoop")){
                //something weird happened. this is a backup case.
                logger.info("##DEBUG## - Error Detected! Word Game Menu loop exited incorrectly. restarting loop.");
                MiniLife_WordGame.doRunMinigameMenu = true;
                continue;
            }
        }

        //for now
        return true;

    }

    public static String wordGameMenu(Scanner input, MiniLifeDialog dialogModule, Logger logger, Boolean isMinigameExitable, Boolean isDebug){
            String wgMenuChoice = "-1";
            System.out.println("Minigame Menu: ");
            System.out.println("1: Start Minigame");
            System.out.println("2: Minigame Rules");
            if (isMinigameExitable){System.out.println("0: Forefit Benefits (Exits Minigame)");}
            if (isDebug){System.out.println("##DEBUG## - 99: Launch Debug Menu for WordGame Minigame");}
            while (MiniLife_WordGame.doRunMinigameMenu == true){
                try{
                    wgMenuChoice = input.next().trim().toLowerCase();} catch (InputMismatchException e){
                    logger.info("##DEBUG## - InputMismatchException caught. Non-integer entered in mainMenuChoice. fixing mistake and looping");
                    System.out.println("Error! Letter or special character entered. Please enter an integer.");
                    wgMenuChoice = "-1";
                    input.next();
                    continue;
                }
                if (Character.isDigit(wgMenuChoice.charAt(0))){
                    if (wgMenuChoice.charAt(0) == '1'){
                        //returns "LaunchMinigame", indicating the minigame should be launched.
                        MiniLife_WordGame.doRunMinigameMenu = false;
                        return "LaunchMinigame";
                    }
                    else if (wgMenuChoice.charAt(0) == '2'){
                        //returns "RulesMenu", indicating the rules for this minigame should be displayed.
                        MiniLife_WordGame.doRunMinigameMenu = false;
                        return "RulesMenu";
                    }
                    else if (wgMenuChoice.charAt(0) == '0'){
                        //returns "ExitMinigame", indicating the minigame should be exited.
                        MiniLife_WordGame.doRunMinigameMenu = false;
                        return "ExitMinigame";
                    }
                    else if (wgMenuChoice.charAt(0) == '9' && wgMenuChoice.charAt(1) == '9'){
                        //##DEBUG## - returns "LaunchDebug", indicating the debug menu should be launched.
                        MiniLife_WordGame.doRunMinigameMenu = false;
                        return "LaunchDebug";
                    }
                    else {
                        //the input was invalid, restarts menu loop
                        System.out.println("Error! Invalid input. Please select a correct choice.");
                        continue;
                    }
                }
            }
            //something weird happened. this is a backup case.
            MiniLife_WordGame.doRunMinigameMenu = false;
            return "ExitedLoop";
    }
}
