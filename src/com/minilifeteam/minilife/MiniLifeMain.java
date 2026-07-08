//MiniLife main program file
//version 0.3-InDev1 (Jul 7, 2026)
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Celeste Manguso
//Secondary Developer(s) on this file: 



//define package
package com.minilifeteam.minilife;

//import scanner, stuff for debugging
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.concurrent.ThreadLocalRandom;

public class MiniLifeMain {
    //create debug logger
    private static final Logger logger = Logger.getLogger(MiniLifeMain.class.getName());

   public static Boolean doRunMainMenu = true;

        public static void main(String[] args){
        //enable debug messages if they were enabled on the command line (for debugging builds, comment this out for production builds before compiling)
        int argsLength = args.length;
        Boolean isDebug = false;
        if (argsLength != 0){
            isDebug = Boolean.parseBoolean(args[0]);
        }
        else if (argsLength == 0) {
            isDebug = false;
        }
        
        if (isDebug){
            logger.setLevel(Level.INFO);//for debug
        }
        else {
            logger.setLevel(Level.SEVERE); //for production
        }

        //create a scanner
        Scanner input = new Scanner(System.in);
        logger.info("##DEBUG## - Scanner Created");

        //create a dialog module instance
        MiniLifeDialog dialogModule = new MiniLifeDialog();

        //create an instance of all minigames
        MiniLife_WordGame wordGame = new MiniLife_WordGame();

        //##DEBUG## if mode is DEBUG, ask user if they need to access special functionality:
        if (isDebug){
         runDebugMenu(input, dialogModule, wordGame, isDebug);
        }

        //introduce the program
        System.out.println("MiniLife (Demo Version)");
        System.out.println(dialogModule.getVersionString());
        System.out.println("By: The MiniLife Team");
        System.out.println("---------------------------------");
        Date currentDate = new Date();
        logger.info("##DEBUG## - Debug Logging Enabled. Current date and time is " + currentDate);


        //display the main menu
        while (MiniLifeMain.doRunMainMenu == true){
        String getMenuChoice = displayMainMenu(input, dialogModule);

        if (getMenuChoice.contentEquals("NewGame")){
            logger.info("##DEBUG## - user chose to launch a new game.");
            MiniLifeMain.doRunMainMenu = false;
            playNewGame(input, dialogModule);
        }
        else if (getMenuChoice.contentEquals("loadSaveGame")){
            logger.info("##DEBUG## - user chose to load save game");
            System.out.println("Error! Save Game functionality is not currently available in this version!");
            MiniLifeMain.doRunMainMenu = true;
            continue;
        }
        else if (getMenuChoice.contentEquals("SettingsMenu")){
            logger.info("##DEBUG## - user chose to load the settings menu.");
            MiniLifeMain.doRunMainMenu = false;
            displaySettingsMenu(input, dialogModule);
        }
        else if (getMenuChoice.contentEquals("ExitProgram")){
            logger.info("##DEBUG## - user chose to exit program. exiting...");
            MiniLifeMain.doRunMainMenu = false;
            exitGame(input);
        }
        else if (getMenuChoice.contentEquals("ExitedLoop")){
            logger.info("##DEBUG## - Error Detected! Main Menu loop exited incorrectly. restarting loop.");
            MiniLifeMain.doRunMainMenu = true;
            continue;
        }
    }
            
        //close the input scanner
        input.close();
        logger.info("##DEBUG## - Scanner Closed");
        }


        public static String displayMainMenu (Scanner input, MiniLifeDialog dialogModule){
            String mainMenuChoice = "-1";
            System.out.println(dialogModule.getDialogWithID(0) + "!");
            System.out.println("Main Menu: ");
            System.out.println("1: New Game");
            System.out.println("2: Settings Menu");
            System.out.println("0: Exit Game");
            while (MiniLifeMain.doRunMainMenu == true){
                try{
                    mainMenuChoice = input.next().trim().toLowerCase();} catch (InputMismatchException e){
                    logger.info("##DEBUG## - InputMismatchException caught. Non-integer entered in mainMenuChoice. fixing mistake and looping");
                    System.out.println("Error! Letter or special character entered. Please enter an integer.");
                    mainMenuChoice = "-1";
                    input.next();
                    continue;
                }
                if (Character.isDigit(mainMenuChoice.charAt(0))){
                    if (mainMenuChoice.charAt(0) == '1'){
                        //returns "NewGame", indicating a new game should be started.
                        MiniLifeMain.doRunMainMenu = false;
                        return "NewGame";
                    }
                    else if (mainMenuChoice.charAt(0) == '2'){
                        //returns "SettingsMenu", indicating the settings menu should be displayed.
                        MiniLifeMain.doRunMainMenu = false;
                        return "SettingsMenu";
                    }
                    else if (mainMenuChoice.charAt(0) == '3'){
                        //returns "loadSaveGame", indicating a saved game should be loaded. not currently available.
                        MiniLifeMain.doRunMainMenu = false;
                        return "loadSaveGame";
                    }
                    else if (mainMenuChoice.charAt(0) == '0'){
                        //returns "ExitProgram", indicating the program should be exited.
                        MiniLifeMain.doRunMainMenu = false;
                        return "ExitProgram";
                    }
                    else {
                        System.out.println("Error! Invalid input. Please select a correct choice.");
                        continue;
                    }
                }
            }

            MiniLifeMain.doRunMainMenu = false;
            return "ExitedLoop";
        }

        public static void displaySettingsMenu(Scanner input, MiniLifeDialog dialogModule){
            System.out.println("SettingsMenu");
        }

        public static void playNewGame(Scanner input, MiniLifeDialog dialogModule /*MiniLifeCharacter characterModule, MiniLifeMinigame, minigame1, etc... */){
            System.out.println("NewGame");
        }

        public static void runDebugMenu (Scanner input, MiniLifeDialog dialogModule, MiniLife_WordGame wordGame, Boolean isDebug){
            int runDebugConsole = 1;
            logger.info("##DEBUG## Debug mode is enabled, launching debug console.");

            do{
            String debugInput = "";
            int doRunDebugMenu = 0;
            System.out.println("##DEBUG## Debug Console: Do you need to run a special function?");
            debugInput = input.next().trim().toLowerCase();
            if (debugInput.charAt(0) == 'y'){
                doRunDebugMenu = 1;
            }
            else if (debugInput.charAt(0) == 'n'){
                runDebugConsole = 0;
                break;
            }
            else {
                continue;
            }

            while (doRunDebugMenu == 1){
                System.out.println("##DEBUG##--Please enter the function you would like to run--##DEBUG##");
                System.out.println("1: Search Dialog Module");
                System.out.println("2: Test Dialog Module");
                System.out.println("3: Launch Minigame 1 - WordGame");
                System.out.println("0: Exit Debug Menu");
                debugInput = input.next().trim().toLowerCase();

                if (debugInput.charAt(0) == '1'){
                    //debug menu - search dialog module functionality
                    int arrayID = 0;
                    //String searchTerm = "";

                    System.out.println("Please enter the array ID");
                    System.out.println("1: Search Dialog Array");
                    System.out.println("2: Search Male Name Array");
                    System.out.println("3: Search Female Name Array");
                    System.out.println("4: Search NB Name Array");
                    System.out.println("5: Search Last Name Array");
                    System.out.println("6: Search Jobs (Without Degree) Array");
                    System.out.println("7: Search Jobs (With Degree) Array");
                    System.out.println("8: Search Houses Array");
                    System.out.println("9: Search Cars Array");
                    System.out.println("10: Search Cities Array");                   
                    System.out.println("11: Search Companies Array");
                    System.out.println("12: Search WordGame Minigame Array");
                    System.out.println("0:  Exit this menu");

                    try{
                    arrayID = input.nextInt();} catch (InputMismatchException e){
                    logger.info("##DEBUG## - InputMismatchException caught. Non-integer entered in userChoice. fixing mistake and looping");
                    System.out.println("Error! Letter or special character entered. Please enter an integer.");
                    arrayID = 0;
                    input.next();
                    continue;
                    }   

                    System.out.println("Please enter the search term");
                    input.nextLine(); //absorb leftover newline character
                    String searchTerm = input.nextLine();

                    System.out.println(dialogModule.getIndexInArrays(arrayID, searchTerm));
                    //System.out.println(dialogModule.getIndexInArrays(0, "Welcome to MiniLife"));

                }
                else if (debugInput.charAt(0) == '2'){
                    logger.info("##DEBUG## Testing Dialog Module. Dialog Module will print a random item from each array");
                    System.out.println(dialogModule.getDialogWithID(ThreadLocalRandom.current().nextInt(0, 53 + 1)));
                    System.out.println(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)));
                    System.out.println(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)));
                    System.out.println(dialogModule.getNBNameWithID(ThreadLocalRandom.current().nextInt(0, 78 + 1)));
                    System.out.println(dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)));
                    System.out.println(dialogModule.getLowJobNameWithID(ThreadLocalRandom.current().nextInt(0, 73 + 1)));
                    System.out.println(dialogModule.getHighJobNameWithID(ThreadLocalRandom.current().nextInt(0, 61 + 1)));
                    System.out.println(dialogModule.getHouseWithID(ThreadLocalRandom.current().nextInt(0, 16 + 1)));
                    System.out.println(dialogModule.getCarsWithID(ThreadLocalRandom.current().nextInt(0, 82 + 1)));
                    System.out.println(dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1)));
                    System.out.println(dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1)));
                    System.out.println(dialogModule.getWGWordWithID(ThreadLocalRandom.current().nextInt(0, 456 + 1)));
                }
                else if (debugInput.charAt(0) == '3'){
                    wordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                }
                else if (debugInput.charAt(0) == '0'){
                    doRunDebugMenu = 0;
                    runDebugConsole = 0;
                    break;
                }
            }
        }while(runDebugConsole == 1);
        }

        public static void exitGame (Scanner input){
            //close the scanner
            input.close();
            logger.info("##DEBUG## - Scanner successfully closed, exiting program...");
            
            //exit the game
            System.exit(0);
        }
}
