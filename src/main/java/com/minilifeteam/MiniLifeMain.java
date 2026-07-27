//MiniLife main program file
//version 0.20-InDev3 (Jul 22, 2026)
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//this project uses some code licensed under the Apache License version 2.0. This code includes the Apache Commons Lang library. see the "apache-LICENSE.txt" file for license terms.
//This project uses some code licensed under the BSD 3-clause license. This code includes the Jline3 library. see "jline-license.txt" for license terms.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Celeste Manguso
//Secondary Developer(s) on this file: 



//define package
package com.minilifeteam;

//import scanner, stuff for debugging
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;
import java.util.InputMismatchException;
import java.lang.UnsupportedOperationException;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;
import org.jline.terminal.*;
import org.jline.utils.*;
import org.jline.style.*;
import java.util.List;
import java.util.ArrayList;

public class MiniLifeMain {
    //create debug logger
    private static final Logger logger = Logger.getLogger(MiniLifeMain.class.getName());

    public static Boolean gameIsDemo = true;

    public static Boolean doRunMainMenu = true;

    public static Boolean doRunGameMenu = true;
    


        public static void main(String[] args) throws Exception{
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

        Terminal sysTerm = TerminalBuilder.builder().system(true).ffm(true).streams(System.in, System.out).build();



        //create a dialog module instance
        MiniLifeDialog dialogModule = new MiniLifeDialog();

        //create an instance of all minigames
        MiniLife_WordGame wordGame = new MiniLife_WordGame();

        //##DEBUG## if mode is DEBUG, ask user if they need to access special functionality:
        if (isDebug){
         runDebugMenu(input, dialogModule, wordGame, isDebug, sysTerm);
        }

        //introduce the program
        int width = sysTerm.getWidth() - 5;
        System.out.println(StringUtils.rightPad("*", width - 1, "-") + "*");
        System.out.println(StringUtils.center(StringUtils.center("MiniLife (Demo Version)", width - 2), width, "|"));
        System.out.println(StringUtils.center( StringUtils.center(dialogModule.getVersionString(), width - 2), width, "|" ));
        System.out.println(StringUtils.rightPad("*", width - 1, "-") + "*");
        Date currentDate = new Date();
        logger.info("##DEBUG## - Debug Logging Enabled. Current date and time is " + currentDate);




        //display the main menu
        while (MiniLifeMain.doRunMainMenu == true){
        String getMenuChoice = displayMainMenu(input, dialogModule);

        if (getMenuChoice.contentEquals("NewGame")){
            logger.info("##DEBUG## - user chose to launch a new game.");
            MiniLifeMain.doRunMainMenu = false;
            playNewGame(input, dialogModule, isDebug, sysTerm);
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
            exitGame(input, sysTerm);
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

        public static void playNewGame(Scanner input, MiniLifeDialog dialogModule, Boolean isDebug, Terminal sysTerm /*MiniLifeCharacter characterModule, MiniLifeMinigame, minigame1, etc... */) throws Exception{

            //clear the console (if not in debug mode, ask if in debug mode)
            if (!isDebug){
                clearConsole();
            }
            else if (isDebug){
                logger.info("##DEBUG## - Clear the console?: ");
                String doClearConsole = input.next().trim().toLowerCase();
                if (doClearConsole.charAt(0) == '1' || doClearConsole.charAt(0) == 'y' || doClearConsole.charAt(0) == 't'){
                    clearConsole();
                }
            }
            

            int menu_width = sysTerm.getWidth() - 5;
            System.out.println(StringUtils.rightPad("*", menu_width - 1, "~") + "*");
            System.out.println(StringUtils.center(StringUtils.center("New Game!", menu_width - 2), menu_width, "!"));
            System.out.println(StringUtils.rightPad("*", menu_width - 1, "~") + "*");

            //initialize the player character, create a school for them, etc.
            MiniLifePlayer playerCharacter = new MiniLifePlayer();
            MiniLifeSchool playerSchool = new MiniLifeSchool();
            
            //gender stuff
            int initialPlayerGender = ThreadLocalRandom.current().nextInt(0, 2);
            logger.info("Initial Gender: " + initialPlayerGender);

            //city stuff
            String playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
            logger.info("##DEBUG## - city name set. city name: " + playerCity);
            String schoolName = playerCity + " - " + dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)) + " Elementary School";
            logger.info("##DEBUG## - school name set. school name: " + schoolName);

            //family stuff
            List<MiniLifeNPC> playerSiblingsList = new ArrayList<MiniLifeNPC>();
            MiniLifeNPC playerMother = new MiniLifeNPC();
            String playerMotherName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
            int playerMotherAge = ThreadLocalRandom.current().nextInt(23, 56);
            MiniLifeNPC playerFather = new MiniLifeNPC();
            String playerFatherName = dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1));
            int playerFatherAge = ThreadLocalRandom.current().nextInt((playerMotherAge - 3), (playerMotherAge + 4));

            //friends stuff
            List<MiniLifeFriend> playerFriendsList = new ArrayList<MiniLifeFriend>();
            Boolean playerHasFriends = false;

            //job/school stuff
            Boolean playerIsInSchool = false;
            Boolean playerDidGraduateCollege = false;

            //name stuff
            String playerFirstName = "";
            String playerLastName = dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));

            if (initialPlayerGender == 0){
                playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
            }
            else if (initialPlayerGender == 1){
                playerFirstName = dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1));
            }
            else{
                logger.info("##DEBUG## - Irrational Response recieved in inital gender module. Setting gender to non-binary");
                initialPlayerGender = 3;
                playerFirstName = dialogModule.getNBNameWithID(ThreadLocalRandom.current().nextInt(0, 78 + 1));
            }

            //fully initialize the school
            playerSchool.createSchool(schoolName);

            //decide if the player will have a sibling, and if so, create one for them.
            int randomSiblingsCheck = ThreadLocalRandom.current().nextInt(0, 11);
            Boolean playerWillHaveSiblings;
            if (randomSiblingsCheck > 5){
                playerWillHaveSiblings = true;
            }
            else{
                playerWillHaveSiblings = false;
            }

            if (playerWillHaveSiblings){
                MiniLifeNPC playerSibling = new MiniLifeNPC();
                String playerSiblingName = "";
                int ageOfSibling = ThreadLocalRandom.current().nextInt(2, 12);
                int playerSiblingGender = ThreadLocalRandom.current().nextInt(0, 2);

                if (playerSiblingGender == 0){
                    playerSiblingName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                }
                else if (playerSiblingGender == 1){
                    playerSiblingName = dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1));
                }
                else{
                    playerSiblingGender = 3;
                    playerSiblingName = dialogModule.getNBNameWithID(ThreadLocalRandom.current().nextInt(0, 78 + 1));
                }

                //create the sibling
                playerSibling.createNPC(playerSiblingName, playerLastName, ageOfSibling);

                //add the sibling to the list
                playerSiblingsList.add(playerSibling);
            }

            //initialize the player's parents
            playerMother.createNPC(playerMotherName, playerLastName, playerMotherAge);
            playerFather.createNPC(playerFatherName, playerLastName, playerFatherAge);

            //finally, initialize the player object with all of it's data.
            playerCharacter.createPlayer(playerFirstName, playerLastName, initialPlayerGender, playerMother, playerFather, playerFriendsList, playerCity);
            if (playerWillHaveSiblings){
                playerCharacter.updateSiblingsList(playerSiblingsList);
            }   
            playerCharacter.setSchool(playerSchool);

            

            //display the player info for the first time
            List<Boolean> DebugFlags = new ArrayList<Boolean>();
            if (isDebug){
                DebugFlags.add(false); //job field
                DebugFlags.add(true); //school field
                DebugFlags.add(true); //college field
                DebugFlags.add(false); //siblings field
                DebugFlags.add(false); //friends field
                DebugFlags.add(gameIsDemo);
            }
            displayPlayerInfo(playerCharacter, dialogModule, isDebug, DebugFlags, menu_width, sysTerm);

            //display the game menu
            List<Boolean> menuDebugFlags = new ArrayList<Boolean>();
            if (isDebug){
                menuDebugFlags.add(true); //show debug menu
                menuDebugFlags.add(gameIsDemo); //game is demo - true if game is the demo version, false otherwise
            }

            doRunGameMenu = true;
            while (doRunGameMenu){
                displayGameMenu(input, dialogModule, playerCharacter, isDebug, menuDebugFlags, menu_width, sysTerm);
            }


        }

        public static void runDebugMenu (Scanner input, MiniLifeDialog dialogModule, MiniLife_WordGame wordGame, Boolean isDebug, Terminal sysTerm) throws Exception{
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
                int width = 85;
                System.out.println(StringUtils.rightPad("##DEBUG##", width - 9, "\u25AC") + "##DEBUG##");
                System.out.println(StringUtils.center( StringUtils.center("--Debugger's Paradise--", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("Please enter the function you would like to run", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.rightPad("##DEBUG##", width - 9, "\u25AC") + "##DEBUG##");

                System.out.println(StringUtils.center( StringUtils.center("1: Search Dialog Module", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("2: Test Dialog Module", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("3: Launch Minigame 1 - WordGame", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("4: Test Jobs Module", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("5: Test Player Module", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("6: Test NPC Module", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("7: Test School Module", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("8: Clear Console", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("9: Test GameplayLoop1 (toddler)", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("10: Test GameplayLoop2 (child)", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("11: Test GameplayLoop3 (middle school)", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("12: Test GameplayLoop4 (high school)", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("13: Test GameplayLoop5 (young adult)", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("14: Test GameplayLoop6 (adult)", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("15: Test GameplayLoop7 (senior citizen)", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.center( StringUtils.center("0: Exit Debug Module", width - 2), width , "\u258B" ));
                System.out.println(StringUtils.rightPad("\u25CF", width - 1, "\u25AC") + "\u25CF");
                debugInput = input.next().trim().toLowerCase();

                if (debugInput.charAt(0) == '1' && debugInput.length() == 1){
                    //debug menu - search dialog module functionality
                    int arrayID = 0;
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
                    //tests the dialog module by using each of it's getter functions to get a random result.
                    logger.info("##DEBUG## Testing Dialog Module. Dialog Module will print a random item from each array");
                    System.out.println(dialogModule.getDialogWithID(ThreadLocalRandom.current().nextInt(0, 53 + 1)));
                    System.out.println(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)));
                    System.out.println(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)));
                    System.out.println(dialogModule.getNBNameWithID(ThreadLocalRandom.current().nextInt(0, 78 + 1)));
                    System.out.println(dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)));
                    System.out.println(dialogModule.getLowJobNameWithID(ThreadLocalRandom.current().nextInt(0, 72 + 1)));
                    System.out.println(dialogModule.getHighJobNameWithID(ThreadLocalRandom.current().nextInt(0, 61 + 1)));
                    System.out.println(dialogModule.getHouseWithID(ThreadLocalRandom.current().nextInt(0, 16 + 1)));
                    System.out.println(dialogModule.getCarsWithID(ThreadLocalRandom.current().nextInt(0, 82 + 1)));
                    System.out.println(dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1)));
                    System.out.println(dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1)));
                    System.out.println(dialogModule.getWGWordWithID(ThreadLocalRandom.current().nextInt(0, 456 + 1)));
                }
                else if (debugInput.charAt(0) == '3'){
                    //launches the WordGame minigame with debugging features enabled, in exitable mode.
                    MiniLife_WordGame.doRunMinigameMenu = true;
                    wordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                }
                else if (debugInput.charAt(0) == '4'){
                    System.out.println("##DEBUG## - Job Module Tester");
                    //this tests the job module by creating a job object, and then calling it's getter functions and advancing the year to test the promotion logic.
                    String debug_jobName = dialogModule.getLowJobNameWithID(ThreadLocalRandom.current().nextInt(0, 72 + 1));
                    String debug_employerName = dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1));
                    double debug_jobSalary = 45000.0;
                    int debug_jobPromotionRate = 15;
                    MiniLifeJob debug_job = new MiniLifeJob();
                    debug_job.createJob(debug_jobName, debug_employerName, debug_jobSalary, debug_jobPromotionRate);
                    System.out.println("Job Title: " + debug_job.getJobTitle());
                    System.out.println("Employer: " + debug_job.getEmployerName());
                    System.out.println("Salary: " + debug_job.getSalary());
                    System.out.println("Promotion Count: " + debug_job.promotionsGet());
                    System.out.println("Advancing Year 3 times...");
                    debug_job.advanceYear();
                    debug_job.advanceYear();
                    debug_job.advanceYear();
                    System.out.println("Salary: " + debug_job.getSalary());
                    System.out.println("Promotion Count: " + debug_job.promotionsGet());
                }
                else if (debugInput.charAt(0) == '5'){
                    //test player module

                    //initialize 
                    String debug_jobName = dialogModule.getLowJobNameWithID(ThreadLocalRandom.current().nextInt(0, 72 + 1));
                    String debug_employerName = dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1));
                    double debug_jobSalary = 45000.0;
                    int debug_jobPromotionRate = 5;
                    MiniLifeJob debugJob = new MiniLifeJob();
                    debugJob.createJob(debug_jobName, debug_employerName, debug_jobSalary, debug_jobPromotionRate);
                    String debug_playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    String debug_playerLastName =  dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
                    String debug_playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    MiniLifePlayer debugPlayer = new MiniLifePlayer();
                    MiniLifeNPC debug_mother = new MiniLifeNPC();
                    MiniLifeNPC debug_father = new MiniLifeNPC();
                    MiniLifeFriend debug_friend = new MiniLifeFriend();
                    MiniLifeNPC debug_sister = new MiniLifeNPC();
                    List<MiniLifeFriend> debug_friendslist = new ArrayList<MiniLifeFriend>();
                    List<MiniLifeNPC> debug_siblingslist = new ArrayList<MiniLifeNPC>();

                    //setup the friends and parents with names
                    debug_mother.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 47);
                    debug_father.createNPC(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)), debug_playerLastName, 53);
                    debug_friend.createFriend(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)),
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)),
                        2
                    );
                    debug_sister.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 12);

                    debug_friendslist.add(debug_friend);
                    debug_siblingslist.add(debug_sister);

                    debugPlayer.createPlayer(debug_playerFirstName, debug_playerLastName, 0, debug_mother, debug_father, debug_friendslist, debug_playerCity);
                    debugPlayer.setJob(debugJob);
                    debugPlayer.updateSiblingsList(debug_siblingslist);

                    //print initial player information
                    System.out.println("##DEBUG## - Player Module Tester");
                    System.out.println("--Player Info--");
                    System.out.println("First Name: " + debugPlayer.getFirstName());
                    System.out.println("Last Name: " + debugPlayer.getLastName());
                    System.out.println("Age: " + debugPlayer.getAge());
                    System.out.println("Money: " + debugPlayer.getMoney());
                    System.out.println("Health: " + debugPlayer.getHealth());
                    System.out.println("Gender: " + debugPlayer.getPlayerGender());
                    System.out.println("City: " + debugPlayer.getPlayerCity());
                    System.out.println("--Job Info--");
                    System.out.println("Job Title: " + debugPlayer.getJob().getJobTitle());
                    System.out.println("Employer: " + debugPlayer.getJob().getEmployerName());
                    System.out.println("Salary: " + debugPlayer.getJob().getSalary());
                    System.out.println("--Friends and Family Info--");
                    System.out.println("Mother's Name: " + debugPlayer.getPlayerMother().nameGet());
                    System.out.println("Father's Name: " + debugPlayer.getPlayerFather().nameGet());
                    System.out.println("Friend Names: " + debugPlayer.getFriendsList().get(0).getFriendName());
                    System.out.println("Sibling Name: " + debugPlayer.getPlayerSiblings().get(0).nameGet());
                    System.out.println("Sibling Age: " + debugPlayer.getPlayerSiblings().get(0).getAge());

                    
                    //make some stuff happen to the player and then display all the info again
                    System.out.println("Making some changes happen...");
                    debugPlayer.addHealth(5);
                    debugPlayer.addMoney(25000);
                    debugPlayer.removeMoney(10000);
                    debugPlayer.takeHealth(15);
                    debugPlayer.getJob().advanceYear();
                    debugPlayer.getJob().advanceYear();
                    debugPlayer.getJob().advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.getPlayerSiblings().get(0).advanceYear();
                    debugPlayer.getPlayerSiblings().get(0).advanceYear();
                    debugPlayer.getPlayerSiblings().get(0).advanceYear();
                    debugPlayer.changeFirstName(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)));
                    debugPlayer.changeLastName(dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)));
                    debugPlayer.getPlayerSiblings().get(0).changeFirstName(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1))
                    );

                    //display the info again after all the changes
                    System.out.println("--Player Info--");
                    System.out.println("First Name: " + debugPlayer.getFirstName());
                    System.out.println("Last Name: " + debugPlayer.getLastName());
                    System.out.println("Age: " + debugPlayer.getAge());
                    System.out.println("Money: " + debugPlayer.getMoney());
                    System.out.println("Health: " + debugPlayer.getHealth());
                    System.out.println("Gender: " + debugPlayer.getPlayerGender());
                    System.out.println("--Job Info--");
                    System.out.println("Job Title: " + debugPlayer.getJob().getJobTitle());
                    System.out.println("Employer: " + debugPlayer.getJob().getEmployerName());
                    System.out.println("Salary: " + debugPlayer.getJob().getSalary());
                    System.out.println("--Friends and Family Info--");
                    System.out.println("Mother's Name: " + debugPlayer.getPlayerMother().nameGet());
                    System.out.println("Father's Name: " + debugPlayer.getPlayerFather().nameGet());
                    System.out.println("Friend Names: " + debugPlayer.getFriendsList().get(0).getFriendName());
                    System.out.println("Sibling Name: " + debugPlayer.getPlayerSiblings().get(0).nameGet());
                    System.out.println("Sibling Age: " + debugPlayer.getPlayerSiblings().get(0).getAge());

                }
                else if (debugInput.charAt(0) == '6'){
                    //test NPC module
                    System.out.println("##DEBUG## - NPC Module Tester");

                    //create NPC with random first and last name
                    MiniLifeNPC debug_NPC = new MiniLifeNPC();

                    //set the first and last names of the NPC to random names from the Dialog module.
                    debug_NPC.createNPC(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), 
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)),
                        ThreadLocalRandom.current().nextInt(18, 89) 
                    );

                    //print the first and last names of the NPC.
                    System.out.println("---NPC Info---");
                    System.out.println("First Name: " + debug_NPC.nameGet());
                    System.out.println("Last Name: " + debug_NPC.getLastName());
                    System.out.println("Age: " + debug_NPC.getAge());
                }
                else if (debugInput.charAt(0) == '7'){
                    //test school module
                    System.out.println("##DEBUG## - School Module Tester");

                    //create a school object
                    MiniLifeSchool debug_school = new MiniLifeSchool();
                    String cityName = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));

                    //call the initializer
                    debug_school.createSchool(cityName + " - Elementary School");

                    //display school info (first time)
                    System.out.println("---School Info---");
                    System.out.println("School Name: " + debug_school.getSchoolName());
                    System.out.println("Grade: " + debug_school.gradeGet());
                    System.out.println("GPA: " + debug_school.gpaGet());
                    System.out.println("Graduated School: " + debug_school.schoolgraduatedGet());
                    System.out.println("Graduated College: " + debug_school.collegeGraduatedGet());
                    System.out.println("Current Degree: " + debug_school.getDegreeName());

                    //change some stuff (increase to middle school, increase the gpa a bit, change school name, etc.)
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.gpaUp();
                    debug_school.gpaUp();
                    debug_school.gpaUp();
                    debug_school.setSchoolName(cityName + " - Middle School");

                    //display school info (second time)
                    System.out.println("---School Info---");
                    System.out.println("School Name: " + debug_school.getSchoolName());
                    System.out.println("Grade: " + debug_school.gradeGet());
                    System.out.println("GPA: " + debug_school.gpaGet());
                    System.out.println("Graduated School: " + debug_school.schoolgraduatedGet());
                    System.out.println("Graduated College: " + debug_school.collegeGraduatedGet());
                    System.out.println("Current Degree: " + debug_school.getDegreeName());


                    //change some stuff (graduate from high school, begin college, set name accordingly, etc.)
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.gpaUp();
                    debug_school.gpaUp();
                    debug_school.attendCollege("Univeristy of " + cityName);

                    //display school info (third time)
                    System.out.println("---School Info---");
                    System.out.println("School Name: " + debug_school.getSchoolName());
                    System.out.println("Grade: " + debug_school.gradeGet());
                    System.out.println("GPA: " + debug_school.gpaGet());
                    System.out.println("Graduated School: " + debug_school.schoolgraduatedGet());
                    System.out.println("Graduated College: " + debug_school.collegeGraduatedGet());
                    System.out.println("Current Degree: " + debug_school.getDegreeName());

                     //change some stuff (graduate from college with a bachelor's degree)
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.advanceYear();
                    debug_school.gpaUp();
                    debug_school.gpaUp();
                    debug_school.graduateCollege();

                    //display school info (fourth time)
                    System.out.println("---School Info---");
                    System.out.println("School Name: " + debug_school.getSchoolName());
                    System.out.println("Grade: " + debug_school.gradeGet());
                    System.out.println("GPA: " + debug_school.gpaGet());
                    System.out.println("Graduated School: " + debug_school.schoolgraduatedGet());
                    System.out.println("Graduated College: " + debug_school.collegeGraduatedGet());
                    System.out.println("Current Degree: " + debug_school.getDegreeName());


                }
                else if (debugInput.charAt(0) == '8'){
                    clearConsole();
                }
                else if (debugInput.charAt(0) == '9'){
                    logger.info("##DEBUG## - debugMenu - test gameplay loop 1");

                    //initialize player
                    String debug_playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    String debug_playerLastName =  dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
                    String debug_playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    MiniLifePlayer debugPlayer = new MiniLifePlayer();
                    MiniLifeNPC debug_mother = new MiniLifeNPC();
                    MiniLifeNPC debug_father = new MiniLifeNPC();
                    MiniLifeFriend debug_friend = new MiniLifeFriend();
                    MiniLifeNPC debug_sister = new MiniLifeNPC();
                    List<MiniLifeFriend> debug_friendslist = new ArrayList<MiniLifeFriend>();
                    List<MiniLifeNPC> debug_siblingslist = new ArrayList<MiniLifeNPC>();

                    //setup the friends and parents with names
                    debug_mother.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 47);
                    debug_father.createNPC(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)), debug_playerLastName, 53);
                    debug_friend.createFriend(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)),
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)),
                        2
                    );
                    debug_sister.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 12);

                    debug_friendslist.add(debug_friend);
                    debug_siblingslist.add(debug_sister);

                    debugPlayer.createPlayer(debug_playerFirstName, debug_playerLastName, 0, debug_mother, debug_father, debug_friendslist, debug_playerCity);
                    debugPlayer.updateSiblingsList(debug_siblingslist);

                    //create a new game instance
                    List<Boolean> debugFlagsGPL1 = new ArrayList<Boolean>();

                    debugFlagsGPL1.add(gameIsDemo); //demo indicator
                    debugFlagsGPL1.add(true);//minigame debug mode
                    debugFlagsGPL1.add(true);//minigame force enable
                    debugFlagsGPL1.add(true); //lottery win force enable
                    debugFlagsGPL1.add(true); //force player injury
                    debugFlagsGPL1.add(true); //force player cancer
                    debugFlagsGPL1.add(false); //unused in this section
                    debugFlagsGPL1.add(false); //unused in this section
                    debugFlagsGPL1.add(false); //unused in this section

                    MiniLifeGameplay gameplayLoop1 = new MiniLifeGameplay();
                    gameplayLoop1.initGameModule(debugPlayer, dialogModule, input, logger, isDebug, debugFlagsGPL1);

                    //call the gameplay loop 1
                    gameplayLoop1.callGameWithID(1, isDebug, sysTerm);

                    System.out.println("playerAge: " + debugPlayer.getAge());


                    
                }
                else if (debugInput.charAt(0) == '1' && debugInput.charAt(1) == '0'){
                    logger.info("##DEBUG## - debugMenu - test gameplay loop 2");

                    //initialize player
                    String debug_playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    String debug_playerLastName =  dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
                    String debug_playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    MiniLifePlayer debugPlayer = new MiniLifePlayer();
                    MiniLifeNPC debug_mother = new MiniLifeNPC();
                    MiniLifeNPC debug_father = new MiniLifeNPC();
                    MiniLifeFriend debug_friend = new MiniLifeFriend();
                    MiniLifeFriend debug_friend2 = new MiniLifeFriend();
                    MiniLifeNPC debug_sister = new MiniLifeNPC();
                    List<MiniLifeFriend> debug_friendslist = new ArrayList<MiniLifeFriend>();
                    List<MiniLifeNPC> debug_siblingslist = new ArrayList<MiniLifeNPC>();

                    MiniLifeSchool debug_school = new MiniLifeSchool();
                    String cityName = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    debug_school.createSchool(cityName + " - Elementary School");

                    //setup the friends and parents with names
                    debug_mother.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 47);
                    debug_father.createNPC(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)), debug_playerLastName, 53);
                    debug_friend.createFriend(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)),
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)),
                        2
                    );
                    debug_sister.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 12);

                    debug_friendslist.add(debug_friend);
                    debug_friendslist.add(debug_friend2);
                    debug_siblingslist.add(debug_sister);

                    debugPlayer.createPlayer(debug_playerFirstName, debug_playerLastName, 0, debug_mother, debug_father, debug_friendslist, debug_playerCity);
                    debugPlayer.updateSiblingsList(debug_siblingslist);
                    debugPlayer.setSchool(debug_school);

                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();



                    //create a new game instance
                    //create a new game instance
                    List<Boolean> debugFlagsGPL2 = new ArrayList<Boolean>();

                    debugFlagsGPL2.add(gameIsDemo); //demo indicator
                    debugFlagsGPL2.add(true);//minigame debug mode
                    debugFlagsGPL2.add(true);//minigame force enable
                    debugFlagsGPL2.add(true); //lottery win force enable
                    debugFlagsGPL2.add(true); //force player injury
                    debugFlagsGPL2.add(true); //force player cancer
                    debugFlagsGPL2.add(true); //force heirloom check
                    debugFlagsGPL2.add(true); //force friend gain
                    debugFlagsGPL2.add(true); //force trivia game

                    MiniLifeGameplay gameplayLoop2 = new MiniLifeGameplay();
                    gameplayLoop2.initGameModule(debugPlayer, dialogModule, input, logger, isDebug, debugFlagsGPL2);

                    //call the gameplay loop 2
                    gameplayLoop2.callGameWithID(2, isDebug, sysTerm);


                }
                else if (debugInput.charAt(0) == '1' && debugInput.charAt(1) == '1'){
                    logger.info("##DEBUG## - debugMenu - test gameplay loop 3");

                    //initialize player
                    String debug_playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    String debug_playerLastName =  dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
                    String debug_playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    MiniLifePlayer debugPlayer = new MiniLifePlayer();
                    MiniLifeNPC debug_mother = new MiniLifeNPC();
                    MiniLifeNPC debug_father = new MiniLifeNPC();
                    MiniLifeFriend debug_friend = new MiniLifeFriend();
                    MiniLifeFriend debug_friend2 = new MiniLifeFriend();
                    MiniLifeNPC debug_sister = new MiniLifeNPC();
                    List<MiniLifeFriend> debug_friendslist = new ArrayList<MiniLifeFriend>();
                    List<MiniLifeNPC> debug_siblingslist = new ArrayList<MiniLifeNPC>();

                    MiniLifeSchool debug_school = new MiniLifeSchool();
                    String cityName = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    debug_school.createSchool(cityName + " - Elementary School");

                    //setup the friends and parents with names
                    debug_mother.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 47);
                    debug_father.createNPC(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)), debug_playerLastName, 53);
                    debug_friend.createFriend(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)),
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)),
                        2
                    );
                    debug_sister.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 12);

                    debug_friendslist.add(debug_friend);
                    debug_friendslist.add(debug_friend2);
                    debug_siblingslist.add(debug_sister);

                    debugPlayer.createPlayer(debug_playerFirstName, debug_playerLastName, 0, debug_mother, debug_father, debug_friendslist, debug_playerCity);
                    debugPlayer.updateSiblingsList(debug_siblingslist);
                    debugPlayer.setSchool(debug_school);

                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();

                    debugPlayer.getSchool().setSchoolName("Debug" + " Middle School");
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();



                    //create a new game instance
                    List<Boolean> debugFlagsGPL3 = new ArrayList<Boolean>();

                    debugFlagsGPL3.add(gameIsDemo); //demo indicator
                    debugFlagsGPL3.add(true);//minigame debug mode
                    debugFlagsGPL3.add(true);//minigame force enable
                    debugFlagsGPL3.add(true); //lottery win force enable
                    debugFlagsGPL3.add(true); //force player injury
                    debugFlagsGPL3.add(true); //force player cancer
                    debugFlagsGPL3.add(true); //force heirloom check
                    debugFlagsGPL3.add(true); //force friend gain
                    debugFlagsGPL3.add(true); //force trivia game

                    MiniLifeGameplay gameplayLoop3 = new MiniLifeGameplay();
                    gameplayLoop3.initGameModule(debugPlayer, dialogModule, input, logger, isDebug, debugFlagsGPL3);

                    //call the gameplay loop 3
                    gameplayLoop3.callGameWithID(3, isDebug, sysTerm);
                }
                else if (debugInput.charAt(0) == '1' && debugInput.charAt(1) == '2'){
                    logger.info("##DEBUG## - debugMenu - test gameplay loop 4");

                    //initialize player
                    String debug_jobName = dialogModule.getLowJobNameWithID(12);
                    String debug_employerName = dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1));
                    double debug_jobSalary = 45000.0;
                    int debug_jobPromotionRate = 5;
                    MiniLifeJob debugJob = new MiniLifeJob();
                    debugJob.createJob(debug_jobName, debug_employerName, debug_jobSalary, debug_jobPromotionRate);
                    String debug_playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    String debug_playerLastName =  dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
                    String debug_playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    MiniLifePlayer debugPlayer = new MiniLifePlayer();
                    MiniLifeNPC debug_mother = new MiniLifeNPC();
                    MiniLifeNPC debug_father = new MiniLifeNPC();
                    MiniLifeFriend debug_friend = new MiniLifeFriend();
                    MiniLifeFriend debug_friend2 = new MiniLifeFriend();
                    MiniLifeNPC debug_sister = new MiniLifeNPC();
                    List<MiniLifeFriend> debug_friendslist = new ArrayList<MiniLifeFriend>();
                    List<MiniLifeNPC> debug_siblingslist = new ArrayList<MiniLifeNPC>();

                    MiniLifeSchool debug_school = new MiniLifeSchool();
                    String cityName = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    debug_school.createSchool(cityName + " - Elementary School");

                    //setup the friends and parents with names
                    debug_mother.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 47);
                    debug_father.createNPC(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)), debug_playerLastName, 53);
                    debug_friend.createFriend(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)),
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)),
                        2
                    );
                    debug_sister.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 12);

                    debug_friendslist.add(debug_friend);
                    debug_friendslist.add(debug_friend2);
                    debug_siblingslist.add(debug_sister);

                    debugPlayer.createPlayer(debug_playerFirstName, debug_playerLastName, 0, debug_mother, debug_father, debug_friendslist, debug_playerCity);
                    debugPlayer.updateSiblingsList(debug_siblingslist);
                    debugPlayer.setSchool(debug_school);

                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();

                    debugPlayer.getSchool().setSchoolName("Debug" + " High School");
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();



                    //create a new game instance
                    List<Boolean> debugFlags = new ArrayList<Boolean>();

                    debugFlags.add(gameIsDemo); //demo indicator

                    MiniLifeGameplay gameplayLoop4 = new MiniLifeGameplay();
                    gameplayLoop4.initGameModule(debugPlayer, dialogModule, input, logger, isDebug, debugFlags);

                    //call the gameplay loop 4
                    gameplayLoop4.callGameWithID(4, isDebug, sysTerm);
                }
                else if (debugInput.charAt(0) == '1' && debugInput.charAt(1) == '3'){
                    logger.info("##DEBUG## - debugMenu - test gameplay loop 5");
                    //initialize player
                    String debug_jobName = dialogModule.getLowJobNameWithID(18);
                    String debug_employerName = dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1));
                    double debug_jobSalary = 20000.0;
                    int debug_jobPromotionRate = 5;
                    MiniLifeJob debugJob = new MiniLifeJob();
                    debugJob.createJob(debug_jobName, debug_employerName, debug_jobSalary, debug_jobPromotionRate);
                    String debug_playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    String debug_playerLastName =  dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
                    String debug_playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    MiniLifePlayer debugPlayer = new MiniLifePlayer();
                    MiniLifeNPC debug_mother = new MiniLifeNPC();
                    MiniLifeNPC debug_father = new MiniLifeNPC();
                    MiniLifeFriend debug_friend = new MiniLifeFriend();
                    MiniLifeFriend debug_friend2 = new MiniLifeFriend();
                    MiniLifeNPC debug_sister = new MiniLifeNPC();
                    List<MiniLifeFriend> debug_friendslist = new ArrayList<MiniLifeFriend>();
                    List<MiniLifeNPC> debug_siblingslist = new ArrayList<MiniLifeNPC>();

                    MiniLifeSchool debug_school = new MiniLifeSchool();
                    String cityName = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    debug_school.createSchool(cityName + " - Elementary School");

                    //setup the friends and parents with names
                    debug_mother.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 47);
                    debug_father.createNPC(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)), debug_playerLastName, 53);
                    debug_friend.createFriend(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)),
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)),
                        2
                    );
                    debug_sister.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 12);

                    debug_friendslist.add(debug_friend);
                    debug_friendslist.add(debug_friend2);
                    debug_siblingslist.add(debug_sister);

                    debugPlayer.createPlayer(debug_playerFirstName, debug_playerLastName, 0, debug_mother, debug_father, debug_friendslist, debug_playerCity);
                    debugPlayer.updateSiblingsList(debug_siblingslist);
                    debugPlayer.setSchool(debug_school);

                    //20
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();

                    debugPlayer.getSchool().setSchoolName("Debug" + " College");
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();

                    debugPlayer.getSchool().attendCollege("Debug" + "College");

                    debugPlayer.setJob(debugJob);



                    //create a new game instance
                    List<Boolean> debugFlags = new ArrayList<Boolean>();

                    debugFlags.add(gameIsDemo); //demo indicator

                    MiniLifeGameplay gameplayLoop5 = new MiniLifeGameplay();
                    gameplayLoop5.initGameModule(debugPlayer, dialogModule, input, logger, isDebug, debugFlags);

                    //call the gameplay loop 4
                    gameplayLoop5.callGameWithID(5, isDebug, sysTerm);
                }
                else if (debugInput.charAt(0) == '1' && debugInput.charAt(1) == '4'){
                    logger.info("##DEBUG## - debugMenu - test gameplay loop 6");

                    //initialize player
                    String debug_jobName = dialogModule.getHighJobNameWithID(0);
                    String debug_employerName = dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1));
                    double debug_jobSalary = 110000.0;
                    int debug_jobPromotionRate = 5;
                    MiniLifeJob debugJob = new MiniLifeJob();
                    debugJob.createJob(debug_jobName, debug_employerName, debug_jobSalary, debug_jobPromotionRate);
                    String debug_playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    String debug_playerLastName =  dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
                    String debug_playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    MiniLifePlayer debugPlayer = new MiniLifePlayer();
                    MiniLifeNPC debug_mother = new MiniLifeNPC();
                    MiniLifeNPC debug_father = new MiniLifeNPC();
                    MiniLifeFriend debug_friend = new MiniLifeFriend();
                    MiniLifeFriend debug_friend2 = new MiniLifeFriend();
                    MiniLifeNPC debug_sister = new MiniLifeNPC();
                    List<MiniLifeFriend> debug_friendslist = new ArrayList<MiniLifeFriend>();
                    List<MiniLifeNPC> debug_siblingslist = new ArrayList<MiniLifeNPC>();

                    MiniLifeSchool debug_school = new MiniLifeSchool();
                    String cityName = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    debug_school.createSchool(cityName + " - Elementary School");

                    //setup the friends and parents with names
                    debug_mother.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 47);
                    debug_father.createNPC(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)), debug_playerLastName, 53);
                    debug_friend.createFriend(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)),
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)),
                        2
                    );
                    debug_sister.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 12);

                    debug_friendslist.add(debug_friend);
                    debug_friendslist.add(debug_friend2);
                    debug_siblingslist.add(debug_sister);

                    debugPlayer.createPlayer(debug_playerFirstName, debug_playerLastName, 0, debug_mother, debug_father, debug_friendslist, debug_playerCity);
                    debugPlayer.updateSiblingsList(debug_siblingslist);
                    debugPlayer.setSchool(debug_school);

                    //32
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();
                    debugPlayer.advanceYear();

                    debugPlayer.getSchool().setSchoolName("Debug" + " College");
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().attendCollege("Debug" + "College");
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().graduateCollege();

                    debugPlayer.setJob(debugJob);



                    //create a new game instance
                    List<Boolean> debugFlags = new ArrayList<Boolean>();

                    debugFlags.add(gameIsDemo); //demo indicator

                    MiniLifeGameplay gameplayLoop6 = new MiniLifeGameplay();
                    gameplayLoop6.initGameModule(debugPlayer, dialogModule, input, logger, isDebug, debugFlags);

                    //call the gameplay loop 6
                    gameplayLoop6.callGameWithID(6, isDebug, sysTerm);


                }
                else if (debugInput.charAt(0) == '1' && debugInput.charAt(1) == '5'){
                    logger.info("##DEBUG## - debugMenu - test gameplay loop 7");


                    //initialize player
                    String debug_jobName = dialogModule.getHighJobNameWithID(0);
                    String debug_employerName = dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1));
                    double debug_jobSalary = 110000.0;
                    int debug_jobPromotionRate = 5;
                    MiniLifeJob debugJob = new MiniLifeJob();
                    debugJob.createJob(debug_jobName, debug_employerName, debug_jobSalary, debug_jobPromotionRate);
                    String debug_playerFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    String debug_playerLastName =  dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
                    String debug_playerCity = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    MiniLifePlayer debugPlayer = new MiniLifePlayer();
                    MiniLifeNPC debug_mother = new MiniLifeNPC();
                    MiniLifeNPC debug_father = new MiniLifeNPC();
                    MiniLifeFriend debug_friend = new MiniLifeFriend();
                    MiniLifeFriend debug_friend2 = new MiniLifeFriend();
                    MiniLifeNPC debug_sister = new MiniLifeNPC();
                    List<MiniLifeFriend> debug_friendslist = new ArrayList<MiniLifeFriend>();
                    List<MiniLifeNPC> debug_siblingslist = new ArrayList<MiniLifeNPC>();

                    MiniLifeSchool debug_school = new MiniLifeSchool();
                    String cityName = dialogModule.getCityNameWithID(ThreadLocalRandom.current().nextInt(0, 85 + 1));
                    debug_school.createSchool(cityName + " - Elementary School");

                    //setup the friends and parents with names
                    debug_mother.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 47);
                    debug_father.createNPC(dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)), debug_playerLastName, 53);
                    debug_friend.createFriend(
                        dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)),
                        dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1)),
                        2
                    );
                    debug_sister.createNPC(dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1)), debug_playerLastName, 12);

                    debug_friendslist.add(debug_friend);
                    debug_friendslist.add(debug_friend2);
                    debug_siblingslist.add(debug_sister);

                    debugPlayer.createPlayer(debug_playerFirstName, debug_playerLastName, 0, debug_mother, debug_father, debug_friendslist, debug_playerCity);
                    debugPlayer.updateSiblingsList(debug_siblingslist);
                    debugPlayer.setSchool(debug_school);

                    //32

                    int runCount = 90;

                    for (int n = 0; n == runCount; n++){
                        debugPlayer.advanceYear();
                    }

                    debugPlayer.getSchool().setSchoolName("Debug" + " College");
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().advanceYear();
                    debugPlayer.getSchool().attendCollege("Debug" + "College");
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().gpaUp();
                    debugPlayer.getSchool().graduateCollege();

                    debugPlayer.setJob(debugJob);



                    //create a new game instance
                    List<Boolean> debugFlags = new ArrayList<Boolean>();

                    debugFlags.add(gameIsDemo); //demo indicator

                    MiniLifeGameplay gameplayLoop7 = new MiniLifeGameplay();
                    gameplayLoop7.initGameModule(debugPlayer, dialogModule, input, logger, isDebug, debugFlags);

                    //call the gameplay loop 6
                    gameplayLoop7.callGameWithID(7, isDebug, sysTerm);
                }
                else if (debugInput.charAt(0) == '0'){
                    //exits the debug menu
                    doRunDebugMenu = 0;
                    runDebugConsole = 0;
                    break;
                }
            }
        }while(runDebugConsole == 1);
        }

        public static void displayGameMenu(Scanner input, MiniLifeDialog dialogModule, MiniLifePlayer playerCharacter, Boolean isDebug, List<Boolean>DebugEnabledFlags, int menu_width, Terminal sysTerm) throws Exception{
                //debug stuff
                Boolean showDebugSettings = false;
                Boolean gameIsDemo = true;

                if (isDebug){
                    showDebugSettings = DebugEnabledFlags.get(0);
                    gameIsDemo = DebugEnabledFlags.get(1);
                    logger.info("##DEBUG## - Special displayGameMenu Debug Flags: " + "showDebugSettings - " + showDebugSettings + ", gameIsDemo - " + gameIsDemo);
                }

                //create the menus
                MiniLifeMenu gameMenuHeader = new MiniLifeMenu();
                gameMenuHeader.createMenu("*", "*", "~", "!", menu_width, false, sysTerm);

                MiniLifeMenu gameMenu = new MiniLifeMenu();
                gameMenu.createMenu("\u25CF", "\u25CF", "\u25AC", "\u258B", menu_width, false, sysTerm);

                //header
                gameMenuHeader.displaySeperator(1);
                gameMenuHeader.menuElement("---Game Menu---", "", 2);
                gameMenuHeader.displaySeperator(1);

                //game menu
                gameMenu.menuElement("What would you like to do?", "", 2);
                gameMenu.menuElement("1: Check Stats", "", 2);
                gameMenu.menuElement("2: Check Inventory", "", 2);
                gameMenu.menuElement("3: Purchase...", "", 2);
                gameMenu.menuElement("4: Increase Age", "", 2);
                gameMenu.menuElement("5: Get a Job...", "", 2);
                gameMenu.menuElement("6: See the Doctor...", "", 2);
                gameMenu.menuElement("7: Settings Menu", "", 2);
                gameMenu.menuElement("0: Exit game (without saving)", "", 2);
                
                if (isDebug && showDebugSettings){
                    gameMenu.displaySeperator(1);
                    gameMenu.menuElement("##DEBUG## - 99: Debug Menu", "", 2);
                }

                //stuff to setup displayPlayerInfo
                List<Boolean> playerInfoDebugFlags = new ArrayList<Boolean>();
                if (isDebug){
                    playerInfoDebugFlags.add(false); //job field
                    playerInfoDebugFlags.add(true); //school field
                    playerInfoDebugFlags.add(true); //college field
                    playerInfoDebugFlags.add(false); //siblings field
                    playerInfoDebugFlags.add(false); //friends field
                    playerInfoDebugFlags.add(gameIsDemo);
                }

                gameMenu.displaySeperator(1);

                String menuChoice;
                char[] menuChoiceChar = {};
                Boolean doRunMenuChooser = true;
                //begin input section

                while (doRunMenuChooser){
                    //menu input
                    try{
                        System.out.println("Please make a selection: ");
                        menuChoice = input.next().trim().toLowerCase();

                        //make sure the input is valid, loop if not
                       if (Character.isDigit(menuChoice.charAt(0))){
                            menuChoiceChar = menuChoice.toCharArray();
                            doRunMenuChooser = false;
                            break;
                       }
                        else {
                            logger.info("##DEBUG## - game menu input - invalid input recieved. clearing variable and looping input.");
                            menuChoice = "";
                            doRunMenuChooser = true;
                            continue;
                        }


                    }catch (InputMismatchException error){
                        menuChoice = "";
                        input.next();
                        doRunMenuChooser = true;
                        continue;
                    }
                }



                switch(menuChoiceChar[0]) {
                    case '1':
                        //choice 1 code here
                        logger.info("##DEBUG## - game menu - choice 1 selected - check stats");
                        displayPlayerInfo(playerCharacter, dialogModule, isDebug, playerInfoDebugFlags, menu_width, sysTerm);
                        break;
                    case '2':
                        //choice 2 code here
                        logger.info("##DEBUG## - game menu - choice 2 selected - inv check");
                        break;
                    case '3':
                        //choice 3 code here
                        logger.info("##DEBUG## - game menu - choice 3 selected - purchase");
                        break;
                    case '4':
                        //choice 4 code here
                        logger.info("##DEBUG## - game menu - choice 4 selected - advance year");

                        switch(playerCharacter.getAge()){
                            //call gameplay loop 1 for ages 0-5 (de facto 1-5)
                            case 0, 1, 2, 3, 4:
                                //create a new game instance
                                List<Boolean> debugFlagsGPL1 = new ArrayList<Boolean>();

                                debugFlagsGPL1.add(gameIsDemo); //demo indicator
                                debugFlagsGPL1.add(false);//minigame debug mode
                                debugFlagsGPL1.add(false);//minigame force enable
                                debugFlagsGPL1.add(false); //lottery win force enable
                                debugFlagsGPL1.add(false); //force player injury
                                debugFlagsGPL1.add(false); //force player cancer
                                debugFlagsGPL1.add(false); //unused in this section
                                debugFlagsGPL1.add(false); //unused in this section
                                debugFlagsGPL1.add(false); //unused in this section

                                MiniLifeGameplay gameplayLoop1 = new MiniLifeGameplay();
                                gameplayLoop1.initGameModule(playerCharacter, dialogModule, input, logger, isDebug, debugFlagsGPL1);

                                //call the gameplay loop 1
                                gameplayLoop1.callGameWithID(1, false, sysTerm);
                                break;

                            case 5, 6, 7, 8, 9, 10:
                                //create a new game instance
                                List<Boolean> debugFlagsGPL2 = new ArrayList<Boolean>();

                                debugFlagsGPL2.add(gameIsDemo); //demo indicator
                                debugFlagsGPL2.add(false);//minigame debug mode
                                debugFlagsGPL2.add(false);//minigame force enable
                                debugFlagsGPL2.add(false); //lottery win force enable
                                debugFlagsGPL2.add(false); //force player injury
                                debugFlagsGPL2.add(false); //force player cancer
                                debugFlagsGPL2.add(false); //heirloom force enable
                                debugFlagsGPL2.add(false); //new friend force enable
                                debugFlagsGPL2.add(false); //trivia game force enable

                                MiniLifeGameplay gameplayLoop2 = new MiniLifeGameplay();
                                gameplayLoop2.initGameModule(playerCharacter, dialogModule, input, logger, isDebug, debugFlagsGPL2);

                                //call the gameplay loop 2
                                gameplayLoop2.callGameWithID(2, false, sysTerm);
                                break;

                            case 11, 12, 13:
                                //create a new game instance
                                List<Boolean> debugFlagsGPL3 = new ArrayList<Boolean>();

                                debugFlagsGPL3.add(gameIsDemo); //demo indicator
                                debugFlagsGPL3.add(false);//minigame debug mode
                                debugFlagsGPL3.add(false);//minigame force enable
                                debugFlagsGPL3.add(false); //lottery win force enable
                                debugFlagsGPL3.add(false); //force player injury
                                debugFlagsGPL3.add(false); //force player cancer
                                debugFlagsGPL3.add(false); //heirloom force enable
                                debugFlagsGPL3.add(false); //new friend force enable
                                debugFlagsGPL3.add(false); //trivia game force enable

                                MiniLifeGameplay gameplayLoop3 = new MiniLifeGameplay();
                                gameplayLoop3.initGameModule(playerCharacter, dialogModule, input, logger, isDebug, debugFlagsGPL3);

                                //call the gameplay loop 3
                                gameplayLoop3.callGameWithID(3, false, sysTerm);
                                break;

                        }





                        break;
                    case '5':
                        //choice 5 code here
                        logger.info("##DEBUG## - game menu - choice 5 selected - Look for Jobs");
                        break;
                    case '6':
                        //choice 6 code here
                        logger.info("##DEBUG## - game menu - choice 6 selected - doctor's office menu");
                        break;
                    case '7':
                        logger.info("##DEBUG## - game menu - choice 7 selected - settings menu");
                        displaySettingsMenu(input, dialogModule);
                        break;
                    case '0':
                        //choice 0 code here
                        logger.info("##DEBUG## - game menu - choice 0 selected - exit game");
                        exitGame(input, sysTerm);
                        break;
                    case '9':
                        if (isDebug){
                            if (menuChoiceChar[1] == '9'){
                                //debug choice code here
                                logger.info("##DEBUG## - game menu - choice 99 selected - debug");
                                System.out.println("Meow!!!");
                                break;
                            }
                        }
                    default:
                        logger.info("##DEBUG## - exception - unknown program state. switch block in game menu resulted in unhandled choice. closing program with error to prevent unintended behavior");
                        throw new UnsupportedOperationException("Invalid Program State in Game Menu. Switch-Block reported impossible result.");
                }


                //temporary code until input mechanism is implemented so the menu doesn't repeat itself 500 times with no way to end it
                try{Thread.sleep(1500);}
                catch (InterruptedException error){
                    System.out.println("InterruptedException Caught");
                 }
        }

        public static void clearConsole(){
            try{
                //clear console using OS-specific console clear commands
                String currentOS = System.getProperty("os.name").toLowerCase();

                if (currentOS.contains("win")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
                else {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }
            }catch (Exception error){
                //fallback by printing a bunch of blank lines
                logger.info("##DEBUG## - clearConsole - fallback triggered, printing blank lines");
                for (int n = 0; n < 50; n++){
                    System.out.println();
                }
            }
        }

        public static void displayPlayerInfo(MiniLifePlayer playerCharacter, MiniLifeDialog dialogModule, Boolean isDebug, List<Boolean>DebugEnabledFlags, int menu_width, Terminal sysTerm) throws Exception{

                    Boolean jobFieldEnabled = false;
                    Boolean schoolFieldEnabled = false;
                    Boolean collegeFieldEnabled = false;
                    Boolean siblingsFieldEnabled = false;
                    Boolean friendsFieldEnabled = false;
                    Boolean gameIsDemo = true;

                if (isDebug){
                    jobFieldEnabled = DebugEnabledFlags.get(0);
                    schoolFieldEnabled = DebugEnabledFlags.get(1);
                    collegeFieldEnabled = DebugEnabledFlags.get(2);
                    siblingsFieldEnabled = DebugEnabledFlags.get(3);
                    friendsFieldEnabled = DebugEnabledFlags.get(4);
                    gameIsDemo = DebugEnabledFlags.get(5);
                    logger.info("##DEBUG## - Special DisplayPlayerInfo Debug Flags: " + "jobFieldEnabled - " + jobFieldEnabled + ", schoolFieldEnabled - " + schoolFieldEnabled + ", collegeFieldEnabled - " + collegeFieldEnabled +
                    ", siblingsFieldEnabled - " + siblingsFieldEnabled + ", friendsFieldEnabled - " + friendsFieldEnabled + ", gameIsDemo - " + gameIsDemo);
                }
                
                //create the menu
                MiniLifeMenu playerInfoScreen = new MiniLifeMenu();
                playerInfoScreen.createMenu("\u25CF", "\u25CF", "\u25AC", "\u258B", menu_width, false, sysTerm);


                //begin player info screen
                playerInfoScreen.displaySeperator(1);
                //player info
                playerInfoScreen.menuElement("----Player Info----", "", 2);
                playerInfoScreen.menuElement("First Name: ", playerCharacter.getFirstName(), 2);
                playerInfoScreen.menuElement("Last Name: ", playerCharacter.getLastName(), 2);
                playerInfoScreen.menuElement("Age: ", playerCharacter.getAge(), 2);
                playerInfoScreen.menuElement("Money: ", playerCharacter.getMoney(), 2);
                playerInfoScreen.menuElement("Health: : ", playerCharacter.getHealth(), 2);
                playerInfoScreen.menuElement("Gender: : ", playerCharacter.getPlayerGender(), 2);
                playerInfoScreen.menuElement("City: : ", playerCharacter.getPlayerCity(), 2);
                playerInfoScreen.displaySeperator(1);
                //job
                if (playerCharacter.doesPlayerHaveJob() || jobFieldEnabled){
                    playerInfoScreen.menuElement("----Job Info----", "", 2);
                    playerInfoScreen.menuElement("Salary: ", playerCharacter.getJob().getSalary(), 2);
                    playerInfoScreen.menuElement("Employer: ", playerCharacter.getJob().getEmployerName(), 2);
                    playerInfoScreen.displaySeperator(1);
                }
                //friends and family
                playerInfoScreen.menuElement("----Friends and Family Info----", "", 2);
                //displays mother's and father's first and last names
                playerInfoScreen.menuElement("Mother's Name: ", (playerCharacter.getPlayerMother().nameGet() + " " + playerCharacter.getLastName()), 2);
                playerInfoScreen.menuElement("Father's Name: ", (playerCharacter.getPlayerFather().nameGet() + " " + playerCharacter.getLastName()), 2);
                if (playerCharacter.getPlayerBooleanInfo(3) || siblingsFieldEnabled){
                    //displays the name of the player's sibling (only one is supported in the demo version)
                    playerInfoScreen.menuElement("Sibling's Name: ", (playerCharacter.getPlayerSiblings().get(0).nameGet() + " " + playerCharacter.getLastName()), 2);
                }
                if (playerCharacter.getPlayerBooleanInfo(4) || friendsFieldEnabled){
                    for (int n = 0; n < playerCharacter.getFriendsList().size(); n++){
                        //displays friend names for each friend in the friendslist.
                        playerInfoScreen.menuElement(
                            "Friend's Name: ", 
                            (playerCharacter.getFriendsList().get(n).getFriendName() + 
                            " " + playerCharacter.getFriendsList().get(n).getLastName() + 
                            " - Age: " + playerCharacter.getFriendsList().get(n).getAge() + 
                            " - Relationship: " + playerCharacter.getFriendsList().get(n).getRelationship()), 
                            2
                        );
                    }
                }
                playerInfoScreen.displaySeperator(1);
                if (playerCharacter.getPlayerBooleanInfo(10) || friendsFieldEnabled){
                    for (int n = 0; n < playerCharacter.getRomanceList().size(); n++){
                        //displays friend names for each friend in the friendslist.
                        playerInfoScreen.menuElement(
                            "Lover's Name: ", 
                            (playerCharacter.getRomanceList().get(n).getFriendName() + 
                            " " + playerCharacter.getRomanceList().get(n).getLastName() + 
                            " - Age: " + playerCharacter.getRomanceList().get(n).getAge() + 
                            " - Relationship: " + playerCharacter.getRomanceList().get(n).getRelationship()), 
                            2
                        );
                    }
                }
                playerInfoScreen.displaySeperator(1);
                //school info
                if (playerCharacter.isPlayerInSchool() || schoolFieldEnabled){
                    playerInfoScreen.menuElement("----School Info----", "", 2);
                    playerInfoScreen.menuElement("School Name: ", playerCharacter.getSchool().getSchoolName(), 2);
                    playerInfoScreen.menuElement("Grade: ", playerCharacter.getSchool().gradeGet(), 2);
                    playerInfoScreen.menuElement("GPA: ", playerCharacter.getSchool().gpaGet(), 2);
                    if ((playerCharacter.getSchool().gradeGet() > 12 && playerCharacter.getSchool().schoolgraduatedGet()) || schoolFieldEnabled){
                        //for if the player graduated high school
                        playerInfoScreen.menuElement("Degree: ", playerCharacter.getSchool().getDegreeName(), 2);
                    };
                    playerInfoScreen.displaySeperator(1);
                }
                else if (playerCharacter.getSchool().collegeGraduatedGet() || collegeFieldEnabled){
                    //for the if the player already graduated college
                    playerInfoScreen.menuElement("----School Info----", "", 2);
                    playerInfoScreen.menuElement("Degree: ", playerCharacter.getSchool().getDegreeName(), 2);
                    playerInfoScreen.displaySeperator(1);
                };

                if (isDebug){
                    //debugging stuff
                    playerInfoScreen.menuElement("####DEBUG Info####", "", 2);
                    playerInfoScreen.menuElement("Demo Version: ", gameIsDemo, 2);
                    playerInfoScreen.menuElement("Version: ", dialogModule.getVersionString(), 2);
                    playerInfoScreen.menuElement("Development Milestone: ", dialogModule.getDevelopmentMilestoneString(), 2);
                    playerInfoScreen.menuElement("Current Operating System: ", System.getProperty("os.name"), 2);
                    playerInfoScreen.displaySeperator(1);
                }

        }

        public static void exitGame (Scanner input, Terminal sysTerm) throws Exception{
            //close the scanner
            input.close();
            logger.info("##DEBUG## - Scanner successfully closed, exiting program...");

            sysTerm.flush();
            sysTerm.close();
            
            //exit the game
            System.exit(0);
        }
}
