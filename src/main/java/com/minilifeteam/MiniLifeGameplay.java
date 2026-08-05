//MiniLife gameplay program file
//version 1.1-rc2 (aug 4, 2026)
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//this project uses some code licensed under the Apache License version 2.0. This code includes the Apache Commons Lang library. see the "apache-LICENSE.txt" file for license terms.
//This project uses some code licensed under the BSD 3-clause license. This code includes the Jline3 library. see "jline-license.txt" for license terms.
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
import java.io.IOException;
import java.lang.UnsupportedOperationException;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;
import org.jline.terminal.*;
import org.jline.utils.*;
import org.jline.style.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MiniLifeGameplay{

    private MiniLifePlayer playerCharacter;
    private MiniLifeDialog dialogModule;
    private Boolean isDebug;
    private List<Boolean> debugFlagsLocal;
    private Scanner input;
    private Logger logger;

    //debug flags stuff
    Boolean gameIsDemo = false;
    Boolean minigameDebugMode = false;
    Boolean minigameForceEnable = false;
    Boolean playerLotteryForce = false;
    Boolean forcePlayerInjury = false;
    Boolean forcePlayerCancer = false;
    Boolean forceHeirloom = false;
    Boolean forcePlayerFriend = false;
    Boolean forceTriviaGame = false;

    public void initGameModule(MiniLifePlayer playerChar, MiniLifeDialog dialog, Scanner input, Logger logger, Boolean isDebug, List<Boolean> DebugFlags){
        this.playerCharacter = playerChar;
        this.dialogModule = dialog;
        this.input = input;
        this.logger = logger;
        this.isDebug = isDebug;
        this.debugFlagsLocal = DebugFlags;

        if (isDebug){
            this.gameIsDemo = debugFlagsLocal.get(0);
            this.minigameDebugMode = debugFlagsLocal.get(1);
            this.minigameForceEnable = debugFlagsLocal.get(2);
            this.playerLotteryForce = debugFlagsLocal.get(3);
            this.forcePlayerInjury = debugFlagsLocal.get(4);
            this.forcePlayerCancer = debugFlagsLocal.get(5);
            this.forceHeirloom = debugFlagsLocal.get(6);
            this.forcePlayerFriend = debugFlagsLocal.get(7);
            this.forceTriviaGame = debugFlagsLocal.get(8);
        }
    }

    //debug flags stuff


    //code to call a certain game

    /**
     * this allows you to call a certain game using an ID. the boolean allows you to indicate whether the game should be reset to it's default variable values before being run.
     * @param gameID - see list below to see the identity of the games
     * @param gameID_1 - gameplayLoop1: toddler
     * @param gameID_2 - gameplayLoop2: elementary school
     * @param gameID_3 - gameplayLoop3: middle school
     * @param gameID_4 - gameplayLoop4: high school
     * @param gameID_5 - gameplayLoop5: young adult
     * @param gameID_6 - gameplayLoop6: adult
     * @param gameID_7 - gameplayLoop7: senior
     * @param gameID_99 - gameplayLoopDemoEnd - end of demo loop
     * @param resetGame - Boolean - set to true to reset the specified game being called
     */
    public void callGameWithID(int gameID, Boolean resetGame, Terminal sysTerm) throws Exception{
        switch(gameID){
            case 1:
                gameplayLoop1(resetGame, sysTerm);
                break;
            case 2:
                gameplayLoop2(resetGame, sysTerm);
                break;
            case 3:
                gameplayLoop3(resetGame, sysTerm);
                break;
            case 4:
                gameplayLoop4(resetGame, sysTerm);
                break; 
            case 5:
                gameplayLoop5(resetGame, sysTerm);
                break;
            case 6:
                gameplayLoop6(resetGame, sysTerm);
                break;
            case 7:
                gameplayLoop7(resetGame, sysTerm);
                break;
            case 99:
                gameplayLoopDemoEnd(resetGame, sysTerm);
                break;
            default:
                logger.info("##DEBUG## - Error - logic error in callGameWithID, unable to load game.");
                break;

        }
    }


    /**
     * Gameplay Loop 1 - Young Childhood
     * Ages: 0-5
     * @apiNote character is a kid, so the possibilities are limited. the character may be asked to do a minigame to recieve an award (added to the character inv awards list),
     * @apiNote the player can also possibly get a bit of pocket money from their family, they may also be gifted an heirloom during this time.
     * @apiNote there is a very small chance that the character's family wins the lottery, and the character is gifted a ton of money.
     * @apiNote the character may lose some health due to an injury, and there is a very small chance that the character develops cancer during this time.
     */
    private void gameplayLoop1(Boolean doReset, Terminal sysTerm) throws Exception{
        if (doReset){
            gameplayLoop1_reset();
        }

        logger.info("##DEBUG## - gameplayLoop1");
        //ask to clear the console (if in debug mode)
        if (isDebug){
            logger.info("##DEBUG## - Clear the console?: ");
            String doClearConsole = input.next().trim().toLowerCase();
            if (doClearConsole.charAt(0) == '1' || doClearConsole.charAt(0) == 'y' || doClearConsole.charAt(0) == 't'){
                MiniLifeMain.clearConsole();
            }
        }

        //setup menu stuff
        int menu_width = 120;
        MiniLifeMenu gameMenuHeader = new MiniLifeMenu();
        gameMenuHeader.createMenu("*", "*", "~", "!", menu_width, false, sysTerm);

        MiniLifeMenu gameMenu = new MiniLifeMenu();
        gameMenu.createMenu("\u25CF", "\u25CF", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu errorMenu = new MiniLifeMenu();
        errorMenu.createMenu("\u2613", "\u2613", "-", "\u258B", menu_width, false, sysTerm);
        

        
        
        //-------MINIGAME--------

        //check if a minigame should be run, also selects which minigame to run
        int minigameRunPotential = ThreadLocalRandom.current().nextInt(0, 100);
        Boolean minigameShouldBeRun = false;
        Boolean minigameShouldBeRunConclusive = false;
        Boolean minigameWasWon = false;
        int minigameToRun = ThreadLocalRandom.current().nextInt(1, 4);
        String minigameName;

        //tie minigame logic to the only currently working minigame (for debug)
        if (isDebug){
            minigameToRun = 0;
        }

        //odds: 35/100
        if (minigameRunPotential <= 35 || minigameForceEnable){
            minigameShouldBeRun = true;
        }else {
            minigameShouldBeRun = false;
        }

        //ask the player if they would like to play a minigame to recieve a prize
        if (minigameShouldBeRun){
            gameMenuHeader.displaySeperator(1);
            gameMenuHeader.menuElement("---Minigame---", "", 2);
            gameMenuHeader.displaySeperator(1);

            gameMenu.menuElement("Would you like to play a minigame to recieve an award?", "", 2);
            if (minigameToRun == 0){
                 minigameName = "Word Game (By Celeste)";
            }else if (minigameToRun == 1){
                 minigameName = "Rock Paper Scissors (By Dal)";
            }else if (minigameToRun == 2){
                 minigameName = "Coin Flip (By Monse)";
            }
            else if (minigameToRun == 3){
                minigameName = "Math Game (By Monse)";
            }else{
                 minigameName = "Error! Unknown Minigame";
            }

            gameMenu.menuElement("Minigame Name: ", minigameName, 2);
            gameMenu.displaySeperator(1);

            System.out.print("Play the minigame?: ");
            String userInput = input.next().trim().toLowerCase();
            char[] inputChar = userInput.toCharArray();
            if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                minigameShouldBeRunConclusive = true;
            }else{
                logger.info("##DEBUG## - user chose not to run minigame.");
                minigameShouldBeRunConclusive = false;
            }

            //actually run the minigame, if the result of asking the user was a yes.
            if (minigameShouldBeRunConclusive){
                if (minigameToRun == 0){
                    minigameWasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                }else if (minigameToRun == 1){
                    minigameWasWon = MiniLife_rpsGame.playGame(input);
                }else if (minigameToRun == 2){
                    minigameWasWon = coinflip.play(input);
                }else if (minigameToRun == 3){
                    minigameWasWon = mathgame.play(input);
                }
            }else{
                //do nothing
            }

            int randomPrize = ThreadLocalRandom.current().nextInt(0, 5);
            if(minigameWasWon){
                switch(randomPrize){
                    case 1:
                        //give the character a bicycle (value: $150) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Bicycle", 150.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Bicycle ($150 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 2:
                        //give the character a game child (value: $200) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Game Child", 200.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Game Child ($200 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 3:
                        //give the character a Lintendo Super Entertainer (value: $300) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Lintendo Super Entertainer", 300.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Lintendo Super Entertainer ($300 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 4:
                        //give the character a rusty can (value: $1.50) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Rusty Can", 1.50);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Rusty Can ($1.50 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    default:
                        //an error has occured. the player should be given an ErrorItem (value: $0).
                        logger.info("##DEBUG## - error in gameplayLoop1_minigame: irrational response from RNG. adding the \"error\" item to the player inventory");
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.getInventory().appendToHeirloomsList("ErrorItem", 0.0);
                        break;
                }
            }
        }

            //----LOTTERY AND POCKET MONEY----

            //now, run some checks to determine if the character gets pocket money from family
            int characterGetsPocketMoneyOdds = ThreadLocalRandom.current().nextInt(0, 500);
            Boolean characterGetsPocketMoney = false;

            //odds: 50/500 (5/100)
            if (characterGetsPocketMoneyOdds >= 250 && characterGetsPocketMoneyOdds <= 300){
                characterGetsPocketMoney = true;
            }else {
                characterGetsPocketMoney = false;
            }

            //award $50 if the character got lucky
            if (characterGetsPocketMoney){
                playerCharacter.addMoney(50);
                gameMenu.displaySeperator(1);
                gameMenu.menuElement("You got some pocket money!", "", 2);
                gameMenu.menuElement("$50 added to wallet.", "", 2);
                gameMenu.displaySeperator(1);

            }else{
                //do nothing
            }

            //determine if the character's family wins the lottery
            int playerWinsLotteryOdds = ThreadLocalRandom.current().nextInt(0, 100000);
            Boolean playerDoesWinLottery = false;

            //odds: 1 in 100,000
            if (playerWinsLotteryOdds == 15){
                playerDoesWinLottery = true;
            }

            //player has won the lottery!!! add $5,000,000 to their balance and display a nice message
            if (!playerCharacter.getPlayerBooleanInfo(12) && playerDoesWinLottery || playerLotteryForce){
                playerCharacter.setPlayerBooleanInfo(12, true);
                playerCharacter.addMoney(5000000);
                playerCharacter.getInventory().appendToAwardsList("wonLottery");
                playerCharacter.setPlayerBooleanInfo(18, true);

                gameMenu.displaySeperator(1);
                gameMenu.menuElement("Congratulations!!! your family won the lottery!!!", "", 2);
                gameMenu.menuElement("$5,000,000 has been gifted to you by your parents!", "", 2);
                gameMenu.displaySeperator(1);
            }

            //----INJURY CHECK----
            int playerDoesGetInjuredOdds = ThreadLocalRandom.current().nextInt(0, 1000);
            Boolean playerDoesGetInjured = false;

            //odds 100/1000
            if (playerDoesGetInjuredOdds <= 100 || forcePlayerInjury){
                playerDoesGetInjured = true;
            }

            if (playerDoesGetInjured){
                playerCharacter.takeHealth(10);
                playerCharacter.getInventory().appendToAwardsList("gotInjured");
                playerCharacter.setPlayerBooleanInfo(18, true);

                errorMenu.displaySeperator(1);
                errorMenu.menuElement("You have been injured! You broke a leg.", "", 2);
                errorMenu.menuElement("You lost 10 health.", "", 2);
                errorMenu.displaySeperator(1);
            }


            //----CANCER CHECK----
            int playerDoesGetCancerOdds = ThreadLocalRandom.current().nextInt(0, 500000);
            Boolean playerDoesGetCancer = false;

            //odds: 100/500,000
            if (playerDoesGetCancerOdds <= 100 || playerCharacter.getPlayerBooleanInfo(7) || forcePlayerCancer){
                playerDoesGetCancer = true;
                playerCharacter.getInventory().appendToAwardsList("gotCancer");
                playerCharacter.setPlayerBooleanInfo(18, true);
            }

            if (playerDoesGetCancer){
                playerCharacter.setPlayerBooleanInfo(7, true);
                playerCharacter.takeHealth(20);

                errorMenu.displaySeperator(1);
                errorMenu.menuElement("Oh No! You have cancer :(", "", 2);
                errorMenu.menuElement("You lost 20 health. You will lose 20 health each year unless healed.", "", 2);
                errorMenu.displaySeperator(1);
            }

            //run advanceYear functions in the various modules
            playerCharacter.advanceYear(); //age up player

            //age up player's friends, if they exist
            if (playerCharacter.getPlayerBooleanInfo(4)){
                for (int n = 0;n >= playerCharacter.getFriendsList().size();n++){
                    playerCharacter.getFriendsList().get(n).advanceYear();
                }
            }

            gameMenu.displaySeperator(1);
            gameMenu.menuElement("The year has been advanced.", "", 2);
            gameMenu.menuElement("Years played: ", playerCharacter.getYearsPlayed(), 2);
            gameMenu.displaySeperator(1);


            

        }


    private void gameplayLoop1_reset(){

    }

    /**
     * Gameplay Loop 2 - Elementary School
     * Ages: 6-11
     * @apiNote the character is older now, and is in elementary school. functions to age up the character within school will now be added (the grade will be advanced).
     * @apiNote the character is now going to potentially face minigames for school, may make new friends (which will be generated in this loop, if the option so arises),
     * @apiNote the character may gain an heirloom, their family may win the lottery, they may find a wallet on the ground, 
     * @apiNote they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     */

    private void gameplayLoop2(Boolean doReset, Terminal sysTerm) throws Exception{
        if (doReset){
            gameplayLoop2_reset();
        }
        logger.info("##DEBUG## - gameplayLoop2");
        //ask to clear the console (if in debug mode)
        if (isDebug){
            logger.info("##DEBUG## - Clear the console?: ");
            String doClearConsole = input.next().trim().toLowerCase();
            if (doClearConsole.charAt(0) == '1' || doClearConsole.charAt(0) == 'y' || doClearConsole.charAt(0) == 't'){
                MiniLifeMain.clearConsole();
            }
        }

        //setup menu stuff
        int menu_width = 120;
        MiniLifeMenu gameMenuHeader = new MiniLifeMenu();
        gameMenuHeader.createMenu("*", "*", "~", "!", menu_width, false, sysTerm);

        MiniLifeMenu gameMenu = new MiniLifeMenu();
        gameMenu.createMenu("\u25CF", "\u25CF", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu errorMenu = new MiniLifeMenu();
        errorMenu.createMenu("\u26CC", "\u26CC", "-", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu loveMenu = new MiniLifeMenu();
        loveMenu.createMenu("\u2765", "\u2765", "~", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu friendMenu = new MiniLifeMenu();
        friendMenu.createMenu(":3", ":3", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu transMenu = new MiniLifeMenu();
        transMenu.createMenu("\u26A7", "\u26A7", "\u25AC", "\u258B", menu_width, false, sysTerm);

        
        
        //-------MINIGAME--------

        //check if a minigame should be run, also selects which minigame to run
        int minigame1RunPotential = ThreadLocalRandom.current().nextInt(0, 100);
        Boolean minigame1ShouldBeRun = false;
        Boolean minigame1ShouldBeRunConclusive = false;
        Boolean minigame1WasWon = false;
        int minigameToRun = ThreadLocalRandom.current().nextInt(1, 4);
        String minigameName;

        //tie minigame logic to the only currently working minigame (for debug)
        if (isDebug){
            minigameToRun = 0;
        }

        //odds: 35/100
        if (minigame1RunPotential <= 35 || minigameForceEnable){
            minigame1ShouldBeRun = true;
        }else {
            minigame1ShouldBeRun = false;
        }

        //ask the player if they would like to play a minigame to recieve a prize
        if (minigame1ShouldBeRun){
            gameMenuHeader.displaySeperator(1);
            gameMenuHeader.menuElement("---Minigame---", "", 2);
            gameMenuHeader.displaySeperator(1);

            gameMenu.menuElement("Would you like to play a minigame to recieve an award?", "", 2);
            if (minigameToRun == 0){
                 minigameName = "Word Game (By Celeste)";
            }else if (minigameToRun == 1){
                 minigameName = "Rock Paper Scissors (By Dal)";
            }else if (minigameToRun == 2){
                 minigameName = "Coin Flip (By Monse)";
            }else if (minigameToRun == 3){
                 minigameName = "Math Game (By Monse)";
            }
            else{
                 minigameName = "Error! Unknown Minigame";
            }

            gameMenu.menuElement("Minigame Name: ", minigameName, 2);
            gameMenu.displaySeperator(1);

            System.out.println("Play the minigame?: ");
            String userInput = input.next().trim().toLowerCase();
            char[] inputChar = userInput.toCharArray();

            if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                minigame1ShouldBeRunConclusive = true;
            }else{
                logger.info("##DEBUG## - user chose not to run minigame.");
                minigame1ShouldBeRunConclusive = false;
            }

            //actually run the minigame, if the result of asking the user was a yes.
            if (minigame1ShouldBeRunConclusive){
                if (minigameToRun == 0){
                    minigame1WasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                }else if (minigameToRun == 1){
                    minigame1WasWon = MiniLife_rpsGame.playGame(input);
                }else if (minigameToRun == 2){
                    minigame1WasWon = coinflip.play(input);
                }else if (minigameToRun == 3){
                    minigame1WasWon = mathgame.play(input);
                }
            }else{
                //do nothing
            }

            int randomPrize = ThreadLocalRandom.current().nextInt(0, 5);
            if(minigame1WasWon){
                switch(randomPrize){
                    case 1:
                        //give the character a lintendo dualscreen (value: $200) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Lintendo DualScreen", 200.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Lintendo DualScreen ($150 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 2:
                        //give the character a game child super (value: $200) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Game Child Super", 200.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Game Child Super ($200 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 3:
                        //give the character a Saga Neptune (value: $300) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Saga Neptune", 300.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Saga Neptune ($300 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 4:
                        //give the character an old boot (value: $0.50) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Old Boot", 0.50);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Old Boot ($0.50 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    default:
                        //an error has occured. the player should be given an ErrorItem (value: $0).
                        logger.info("##DEBUG## - error in gameplayLoop1_minigame: irrational response from RNG. adding the \"error\" item to the player inventory");
                        playerCharacter.getInventory().appendToHeirloomsList("ErrorItem", 0.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        break;
                }
            }
        }

            //----LOTTERY AND POCKET MONEY----

            //now, run some checks to determine if the character gets pocket money from family
            int characterGetsPocketMoneyOdds = ThreadLocalRandom.current().nextInt(0, 500);
            Boolean characterGetsPocketMoney = false;

            //odds: 50/500 (5/100)
            if (characterGetsPocketMoneyOdds >= 250 && characterGetsPocketMoneyOdds <= 300){
                characterGetsPocketMoney = true;
            }else {
                characterGetsPocketMoney = false;
            }

            //award $50 if the character got lucky
            if (characterGetsPocketMoney){
                playerCharacter.addMoney(50);
                gameMenu.displaySeperator(1);
                gameMenu.menuElement("You got some pocket money!", "", 2);
                gameMenu.menuElement("$50 added to wallet.", "", 2);
                gameMenu.displaySeperator(1);

            }else{
                //do nothing
            }

            //determine if the character's family wins the lottery
            int playerWinsLotteryOdds = ThreadLocalRandom.current().nextInt(0, 100000);
            Boolean playerDoesWinLottery = false;

            //odds: 1 in 100,000
            if (playerWinsLotteryOdds == 15){
                playerDoesWinLottery = true;
                playerCharacter.getInventory().appendToAwardsList("wonLottery");
                playerCharacter.setPlayerBooleanInfo(18, true);
            }

            //player has won the lottery!!! add $5,000,000 to their balance and display a nice message
            if (!playerCharacter.getPlayerBooleanInfo(12) && playerDoesWinLottery || playerLotteryForce){
                playerCharacter.setPlayerBooleanInfo(12, true);
                playerCharacter.addMoney(5000000);

                gameMenu.displaySeperator(1);
                gameMenu.menuElement("Congratulations!!! your family won the lottery!!!", "", 2);
                gameMenu.menuElement("$5,000,000 has been gifted to you by your parents!", "", 2);
                gameMenu.displaySeperator(1);
            }


            //---HEIRLOOMS----
            //give the character an heirloom if they pass an RNG check. the heirloom is based on the current age of the character. heirlooms are worth a lot of money.

            int heirloomRNGCheck = ThreadLocalRandom.current().nextInt(0, 10000);

            //odds: 1000/10000
            if (heirloomRNGCheck <= 5000 && heirloomRNGCheck >= 4000 || forceHeirloom){
                switch (playerCharacter.getAge()){
                    case 6:
                        //item - old lamp - value: $650
                        playerCharacter.getInventory().appendToHeirloomsList("Old Lamp", 650.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Old Lamp", 2);
                        gameMenu.menuElement("Value: ", "$650", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 7:
                        //item - 80's crystal pepso soda - value: $250
                        playerCharacter.getInventory().appendToHeirloomsList("80's Crystal Pepso Soda", 250.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "80's Crystal Pepso Soda", 2);
                        gameMenu.menuElement("Value: ", "$250", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 8:
                        //item - Family Man Season 3 DVD box set - value: $50
                        playerCharacter.getInventory().appendToHeirloomsList("Family Man Season 3 Box Set", 50.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Family Man Season 3 Box Set", 2);
                        gameMenu.menuElement("Value: ", "$50", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 9:
                        //item - Golden Egg - value: $1,000
                        playerCharacter.getInventory().appendToHeirloomsList("Golden Egg", 1000.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Golden Egg", 2);
                        gameMenu.menuElement("Value: ", "$1,000", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 10:
                        //item - Violet Horse CD - value: $500
                        playerCharacter.getInventory().appendToHeirloomsList("Violet Horse CD", 500.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Violet Horse CD", 2);
                        gameMenu.menuElement("Value: ", "$500", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 11:
                        //item - Hatsune Moko: Operation Diva DX Arcade Cabinet - value: $3,000
                        playerCharacter.getInventory().appendToHeirloomsList("Hatsune Moko: Operation Diva DX Arcade Cabinet", 500.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Hatsune Moko: Operation Diva DX Arcade Cabinet", 2);
                        gameMenu.menuElement("Value: ", "$3,000", 2);
                        gameMenu.displaySeperator(2);
                        break;
                }

            }

            //----FRIENDS----
            //first, we run an RNG check to see if the player will be offered a new friendship (50/50 chance). if they are, we generate a new friend and offer it up to the player. 
            //if they accept, we append the new friend to the friend's index, set playerDoesHaveFriends to true, and move along.

            int playerGainsFriendRNG = ThreadLocalRandom.current().nextInt(0, 100);

            if (playerGainsFriendRNG <= 50 || forcePlayerFriend){
               //generate a friend
               MiniLifeFriend friendCandidate = new MiniLifeFriend();
               int friendGender = ThreadLocalRandom.current().nextInt(0, 3);
               String fcFirstName = "";
               String fcLastName = dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
               int fcAge = (ThreadLocalRandom.current().nextInt((playerCharacter.getAge() - 3), (playerCharacter.getAge() + 3)));
               switch(friendGender){
                    case 0:
                        fcFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    case 1:
                        fcFirstName = dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1));
                    case 2:
                        fcFirstName = dialogModule.getNBNameWithID(ThreadLocalRandom.current().nextInt(0, 78 + 1));    
               }
               
               friendCandidate.createFriend(fcFirstName, fcLastName, fcAge);

               friendMenu.displaySeperator(2);
               friendMenu.menuElement("You met someone new, and you hit it off!", "", 2);
               friendMenu.menuElement("Would you like to be friends?", "", 2);
               friendMenu.menuElement("Name: ", (friendCandidate.getFriendName() + " " + friendCandidate.getLastName()), 2);
               friendMenu.menuElement("Age: ", friendCandidate.getAge(), 2);
               friendMenu.displaySeperator(2);

                System.out.print("Make a new friend?: ");
                String userInput = input.next().trim().toLowerCase();
                char[] inputChar = userInput.toCharArray();

                if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                    playerCharacter.getFriendsList().add(friendCandidate);
                    playerCharacter.setPlayerBooleanInfo(4, true);
                }else{
                    logger.info("##DEBUG## - user chose to not make friend.");
                    errorMenu.displaySeperator(1);
                    errorMenu.menuElement("You did not become friends.", "", 2);
                    errorMenu.displaySeperator(1);
            }


            }

            //now, check if there are any friendships with low relationship scores which should be ended.
            for (int n = 0;n < playerCharacter.getFriendsList().size(); n++){
                if (playerCharacter.getFriendsList().get(n).getRelationship() < 15){
                    errorMenu.displaySeperator(1);
                    errorMenu.menuElement("Oh No :( you had a falling out with one of your friends.", "", 2);
                    errorMenu.menuElement("Name: ", (playerCharacter.getFriendsList().get(n).getFriendName() + " " + playerCharacter.getFriendsList().get(n).getLastName()), 2);
                    errorMenu.menuElement("You are no longer friends.", "", 2);
                    errorMenu.displaySeperator(1);
                    playerCharacter.getFriendsList().remove(n);
                }
            }


            //----INJURY CHECK----
            int playerDoesGetInjuredOdds = ThreadLocalRandom.current().nextInt(0, 1000);
            Boolean playerDoesGetInjured = false;

            //odds 100/1000
            if (playerDoesGetInjuredOdds <= 100 || forcePlayerInjury){
                playerDoesGetInjured = true;
            }

            if (playerDoesGetInjured){
                playerCharacter.takeHealth(10);
                playerCharacter.getInventory().appendToAwardsList("gotInjured");
                playerCharacter.setPlayerBooleanInfo(18, true);

                errorMenu.displaySeperator(1);
                errorMenu.menuElement("You have been injured! You broke a leg.", "", 2);
                errorMenu.menuElement("You lost 10 health.", "", 2);
                errorMenu.displaySeperator(1);
            }


            //----CANCER CHECK----
            int playerDoesGetCancerOdds = ThreadLocalRandom.current().nextInt(0, 500000);
            Boolean playerDoesGetCancer = false;

            //odds: 100/500,000
            if (playerDoesGetCancerOdds <= 100 || playerCharacter.getPlayerBooleanInfo(7) || forcePlayerCancer){
                playerDoesGetCancer = true;
            }

            if (playerDoesGetCancer){
                playerCharacter.setPlayerBooleanInfo(7, true);
                playerCharacter.takeHealth(20);
                playerCharacter.getInventory().appendToAwardsList("gotCancer");
                playerCharacter.setPlayerBooleanInfo(18, true);

                errorMenu.displaySeperator(1);
                errorMenu.menuElement("Oh No! You have cancer :(", "", 2);
                errorMenu.menuElement("You lost 20 health. You will lose 20 health each year unless healed.", "", 2);
                errorMenu.displaySeperator(1);
            }


            //----SCHOOL----

            Boolean minigame2WasWon = false;
            //enable school
            if (!playerCharacter.getPlayerBooleanInfo(2)){
                playerCharacter.setPlayerBooleanInfo(2, true);
            }

            //do another minigame check to offer the player to try and increase their GPA, only offered if the previous check failed/was rejected, and the player has a GPA below 3.0
            if (!minigame1ShouldBeRunConclusive && playerCharacter.getSchool().gpaGet() < 3.0 || minigameForceEnable){
                //check if a minigame should be run, also selects which minigame to run
                Boolean minigame2ShouldBeRun = true;
                Boolean minigame2ShouldBeRunConclusive = true;
                int minigameToRun_2 = ThreadLocalRandom.current().nextInt(1, 4);
                String minigameName_2;

                //tie minigame logic to the only currently working minigame (for debug)
                if (isDebug){
                    minigameToRun_2 = 0;
                }

                //ask the player if they would like to play a minigame to recieve a prize
                if (minigame2ShouldBeRun){
                    gameMenuHeader.displaySeperator(1);
                    gameMenuHeader.menuElement("---Minigame---", "", 2);
                    gameMenuHeader.displaySeperator(1);

                    gameMenu.menuElement("Would you like to play a minigame to try harder at school?", "", 2);
                    if (minigameToRun_2 == 0){
                        minigameName_2 = "Word Game (By Celeste)";
                    }else if (minigameToRun_2 == 1){
                        minigameName_2 = "Rock Paper Scissors (By Dal)";
                    }else if (minigameToRun_2 == 2){
                        minigameName_2 = "Coin Flip (By Monse)";
                    }else if (minigameToRun_2 == 3){
                        minigameName_2 = "Math Game (By Monse)";
                    }else{
                        minigameName_2 = "Error! Unknown Minigame";
                        minigame2WasWon = false;
                    }

                    gameMenu.menuElement("Minigame Name: ", minigameName_2, 2);
                    gameMenu.displaySeperator(1);

                    System.out.println("Play the minigame?: ");
                    String userInput = input.next().trim().toLowerCase();
                    char[] inputChar = userInput.toCharArray();

                    if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                        minigame2ShouldBeRunConclusive = true;
                    }else{
                        logger.info("##DEBUG## - user chose not to run minigame.");
                        minigame2ShouldBeRunConclusive = false;
                    }

                    //actually run the minigame, if the result of asking the user was a yes.
                    if (minigame2ShouldBeRunConclusive){
                        if (minigameToRun_2 == 0){
                            minigame2WasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                        }else if (minigameToRun_2 == 1){
                            minigame2WasWon = MiniLife_rpsGame.playGame(input);
                        }else if (minigameToRun_2 == 2){
                            minigame2WasWon = coinflip.play(input);
                        }else if (minigameToRun_2 == 3){
                            minigame2WasWon = mathgame.play(input);
                        }
                    }else{
                        //do nothing
                    }

                    if(minigame2WasWon){
                        //increase school GPA
                        playerCharacter.getSchool().gpaUp();
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! your GPA went up!", "", 2);
                        gameMenu.displaySeperator(1);
                    }
                    else {
                        playerCharacter.getSchool().gpaDown();
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Oh No! you failed! your GPA went down.", "", 2);
                        gameMenu.displaySeperator(1);
                    }
                }
            }

            //--RELATIONSHIP--
            //this section is about offering a chance to improve the relationship with your friends through a minigame, this section offers a chance to improve your relationships with all of
            //your friends by playing a small micro-game embedded within this module itself. it's a basic multiple-choice music trivia question which gives +1 relationship points to all player friendships.
            //the twist is that all of these questions are hyper-relavant to my specific music tastes, making it difficult for anyone who isn't into this sort of music.

            int questionPicked = ThreadLocalRandom.current().nextInt(0, 5);

            String questionOne =  "When did the album Brat by Charli XCX come out?";
            String q1AnsOne = "1: June 7, 2024"; //correct
            String q1AnsTwo = "2: July 13, 2023";
            String q1AnsThree = "3: August 17, 2025";
            String q1AnsFour = "4: November 12, 2024";

            String questionTwo =  "What year did the Fender Stratocaster first release?";
            String q2AnsOne = "1: 1954"; //correct
            String q2AnsTwo = "2: 1962";
            String q2AnsThree = "3: 1946";
            String q2AnsFour = "4: 1998";

            String questionThree =  "When did Chappell Roan's \"Pink Pony Club\" release?";
            String q3AnsOne = "1: August 13, 2022";
            String q3AnsTwo = "2: May 12, 2019";
            String q3AnsThree = "3: April 3, 2020"; //correct
            String q3AnsFour = "4: December 13, 2021";

            String questionFour =  "What is the name of Vylet Pony's 21'st Album?";
            String q4AnsOne = "1: I Am the Drifter of Sunshine Valley";
            String q4AnsTwo = "3: Carousel";
            String q4AnsThree = "4: Monarch of Monsters";
            String q4AnsFour = "4: I Was the Loner of Paradise Valley"; //correct

            String questionFive =  "What is the name of the third song on Jane Remover's \"Census Designated\"";
            String q5AnsOne = "1: Holding a Leech";
            String q5AnsTwo = "2: Fling"; //correct
            String q5AnsThree = "3: Backseat Girl";
            String q5AnsFour = "4: Census Designated";

            int triviaGameOffered = ThreadLocalRandom.current().nextInt(0, 10);
            Boolean runTriviaGame = false;
            Boolean playerWonTriviaGame = false;
            String triviaInput = "";
            char[] triviaChar;

            if (playerCharacter.getPlayerBooleanInfo(4) && triviaGameOffered <= 5){
                //ask the user if they would like to play the trivia game
                gameMenuHeader.displaySeperator(1);
                gameMenu.menuElement("~~~Friendship Game~~~", "", 2);
                gameMenu.menuElement("Would you like to play a trivia game to improve your friendships?", "", 2);
                gameMenuHeader.displaySeperator(1);

                System.out.print("Play the trivia game?: ");
                String userInput = input.next().trim().toLowerCase();
                char[] inputChar = userInput.toCharArray();

                if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                     runTriviaGame = true;
                }else{
                    logger.info("##DEBUG## - user chose not to run trivia game.");
                    runTriviaGame = false;
                 }

                 if (runTriviaGame || forceTriviaGame){
                    switch(questionPicked){
                        case 0:
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionOne, "", 2);
                            gameMenu.menuElement(q1AnsOne, "", 2);
                            gameMenu.menuElement(q1AnsTwo, "", 2);
                            gameMenu.menuElement(q1AnsThree, "", 2);
                            gameMenu.menuElement(q1AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '1' || triviaChar [0] == 'A' || triviaChar [0] == 'a'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q1AnsOne, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 1:
                            //question 2
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionTwo, "", 2);
                            gameMenu.menuElement(q2AnsOne, "", 2);
                            gameMenu.menuElement(q2AnsTwo, "", 2);
                            gameMenu.menuElement(q2AnsThree, "", 2);
                            gameMenu.menuElement(q2AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '1' || triviaChar [0] == 'A' || triviaChar [0] == 'a'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q2AnsOne, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 2:
                            //question 3
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionThree, "", 2);
                            gameMenu.menuElement(q3AnsOne, "", 2);
                            gameMenu.menuElement(q3AnsTwo, "", 2);
                            gameMenu.menuElement(q3AnsThree, "", 2);
                            gameMenu.menuElement(q3AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '3' || triviaChar [0] == 'C' || triviaChar [0] == 'C'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q3AnsThree, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 3:
                            //question 4
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionFour, "", 2);
                            gameMenu.menuElement(q4AnsOne, "", 2);
                            gameMenu.menuElement(q4AnsTwo, "", 2);
                            gameMenu.menuElement(q4AnsThree, "", 2);
                            gameMenu.menuElement(q4AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '4' || triviaChar [0] == 'D' || triviaChar [0] == 'd'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q4AnsFour, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 4:
                            //question 5
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionFive, "", 2);
                            gameMenu.menuElement(q5AnsOne, "", 2);
                            gameMenu.menuElement(q5AnsTwo, "", 2);
                            gameMenu.menuElement(q5AnsThree, "", 2);
                            gameMenu.menuElement(q5AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '2' || triviaChar [0] == 'B' || triviaChar [0] == 'b'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q5AnsTwo, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        default:
                            errorMenu.displaySeperator(1);
                            errorMenu.menuElement("###-ERROR-###: ", "switch statement overflow in trivia game (gpl2)", 2);
                            errorMenu.displaySeperator(1);
                            break;
                    }
                }
            }





            //---AGE UP---

            //run advanceYear functions in the various modules
            playerCharacter.advanceYear(); //age up player
            playerCharacter.getSchool().advanceYear(); //advance school year

            //lower friendship by 5 on odd years
            for (int n=0;n < playerCharacter.getFriendsList().size();n++){
                if ((playerCharacter.getAge() & 1) == 0){
                    playerCharacter.getFriendsList().get(n).friendRelationshipDecline(5);
                }
            }


            //lower GPA by 0.5 on even years
            switch (playerCharacter.getAge()){
                case 6:
                    if (!minigame2WasWon && playerCharacter.getSchool().gpaGet() > 1.0){
                        playerCharacter.getSchool().gpaDown();
                    }
                    break;
                case 8:
                    if (!minigame2WasWon && playerCharacter.getSchool().gpaGet() > 1.0){
                        playerCharacter.getSchool().gpaDown();
                    }
                    break;
                case 10:
                    if (!minigame2WasWon && playerCharacter.getSchool().gpaGet() > 1.0){
                        playerCharacter.getSchool().gpaDown();
                    }
                    break;
            }

            //age up player's friends, if they exist
            if (playerCharacter.getPlayerBooleanInfo(4)){
                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                    playerCharacter.getFriendsList().get(n).advanceYear();
                }
            }

            gameMenu.displaySeperator(1);
            gameMenu.menuElement("The year has been advanced.", "", 2);
            gameMenu.menuElement("Years played: ", playerCharacter.getYearsPlayed(), 2);
            gameMenu.menuElement("Current school year: ", playerCharacter.getSchool().gradeGet(), 2);
            gameMenu.displaySeperator(1);


            
    }

    private void gameplayLoop2_reset(){
    
    }
    
    /**
     * Gameplay Loop 3 - Middle School
     * Ages: 12-14
     * @apiNote the character is older now, and is in middle school. functions to age up the character within school will continue (the grade will be advanced).
     * @apiNote the character is going to potentially face minigames for school, may make new friends (which will be generated in this loop, if the option so arises),
     * @apiNote the character may gain an heirloom, their family may win the lottery, they may find a wallet on the ground, 
     * @apiNote they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     * @apiNote the character may begin to form romantic feelings for a friend, but won't be able to advance those feelings much yet.
     * @apiNote the character will recieve a personality type during this block. this will change in the next block, but then it will be set in stone. some things are effected by personality type.
     * @apiNote the player may get cancer in this era, if they are particularly unlucky. they may lose some health due to an injury or illness. (and may lose some school performance if these things happen)
     * @apiNote the player may choose to make the character petty shoplift, which can result in arrest, or the character taking on the "delinquent" or "criminal" personality types, 
     * greatly increasing the odds of injury, arrest, and criminal... opportunities in the future.
     */

    private void gameplayLoop3(Boolean doReset, Terminal sysTerm) throws Exception{
        if (doReset){
            gameplayLoop3_reset();
        }
        logger.info("##DEBUG## - gameplayLoop3");

        //ask to clear the console (if in debug mode)
        if (isDebug){
            logger.info("##DEBUG## - Clear the console?: ");
            String doClearConsole = input.next().trim().toLowerCase();
            if (doClearConsole.charAt(0) == '1' || doClearConsole.charAt(0) == 'y' || doClearConsole.charAt(0) == 't'){
                MiniLifeMain.clearConsole();
            }
        }

        //setup menu stuff
        int menu_width = 120;
        MiniLifeMenu gameMenuHeader = new MiniLifeMenu();
        gameMenuHeader.createMenu("*", "*", "~", "!", menu_width, false, sysTerm);

        MiniLifeMenu gameMenu = new MiniLifeMenu();
        gameMenu.createMenu("\u25CF", "\u25CF", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu errorMenu = new MiniLifeMenu();
        errorMenu.createMenu("\u26CC", "\u26CC", "-", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu loveMenu = new MiniLifeMenu();
        loveMenu.createMenu("\u2765", "\u2765", "~", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu friendMenu = new MiniLifeMenu();
        friendMenu.createMenu(":3", ":3", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu transMenu = new MiniLifeMenu();
        transMenu.createMenu("\u26A7", "\u26A7", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu moneyMenu = new MiniLifeMenu();
        moneyMenu.createMenu("$", "$", "\u25AC", "\u258B", menu_width, false, sysTerm);
        
        
        //-------MINIGAME--------

        //check if a minigame should be run, also selects which minigame to run
        int minigame1RunPotential = ThreadLocalRandom.current().nextInt(0, 100);
        Boolean minigame1ShouldBeRun = false;
        Boolean minigame1ShouldBeRunConclusive = false;
        Boolean minigame1WasWon = false;
        int minigameToRun = ThreadLocalRandom.current().nextInt(1, 4);
        String minigameName;

        //odds: 35/100
        if (minigame1RunPotential <= 35 || minigameForceEnable){
            minigame1ShouldBeRun = true;
        }else {
            minigame1ShouldBeRun = false;
        }

        //ask the player if they would like to play a minigame to recieve a prize
        if (minigame1ShouldBeRun){
            gameMenuHeader.displaySeperator(1);
            gameMenuHeader.menuElement("---Minigame---", "", 2);
            gameMenuHeader.displaySeperator(1);

            gameMenu.menuElement("Would you like to play a minigame to recieve an award?", "", 2);
            if (minigameToRun == 0){
                 minigameName = "Word Game (By Celeste)";
            }else if (minigameToRun == 1){
                 minigameName = "Rock Paper Scissors (By Dal)";
            }else if (minigameToRun == 2){
                 minigameName = "Coin Flip (By Monse)";
            }else if (minigameToRun == 3){
                 minigameName = "Math Game (By Monse)";
            }else{
                 minigameName = "Error! Unknown Minigame";
            }

            gameMenu.menuElement("Minigame Name: ", minigameName, 2);
            gameMenu.displaySeperator(1);

            System.out.println("Play the minigame?: ");
            String userInput = input.next().trim().toLowerCase();
            char[] inputChar = userInput.toCharArray();

            if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                minigame1ShouldBeRunConclusive = true;
            }else{
                logger.info("##DEBUG## - user chose not to run minigame.");
                minigame1ShouldBeRunConclusive = false;
            }

            //actually run the minigame, if the result of asking the user was a yes.
            if (minigame1ShouldBeRunConclusive){
                if (minigameToRun == 0){
                    minigame1WasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                }else if (minigameToRun == 1){
                    minigame1WasWon = MiniLife_rpsGame.playGame(input);
                }else if (minigameToRun == 2){
                    minigame1WasWon = coinflip.play(input);
                }else if (minigameToRun == 3){
                    minigame1WasWon = mathgame.play(input);
                }
            }else{
                //do nothing
            }

            int randomPrize = ThreadLocalRandom.current().nextInt(0, 5);
            if(minigame1WasWon){
                switch(randomPrize){
                    case 1:
                        //give the character a lintendo dualscreen (value: $200) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Lintendo DualScreen", 200.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Lintendo DualScreen ($150 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 2:
                        //give the character a game child super (value: $200) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Game Child Super", 200.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Game Child Super ($200 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 3:
                        //give the character a Saga Neptune (value: $300) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Saga Neptune", 300.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Saga Neptune ($300 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 4:
                        //give the character an old boot (value: $0.50) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Old Boot", 0.50);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Old Boot ($0.50 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    default:
                        //an error has occured. the player should be given an ErrorItem (value: $0).
                        logger.info("##DEBUG## - error in gameplayLoop1_minigame: irrational response from RNG. adding the \"error\" item to the player inventory");
                        playerCharacter.getInventory().appendToHeirloomsList("ErrorItem", 0.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        break;
                }
            }
        }

            //----LOTTERY AND POCKET MONEY----

            //now, run some checks to determine if the character gets pocket money from family
            int characterGetsPocketMoneyOdds = ThreadLocalRandom.current().nextInt(0, 500);
            Boolean characterGetsPocketMoney = false;

            //odds: 50/500 (5/100)
            if (characterGetsPocketMoneyOdds >= 250 && characterGetsPocketMoneyOdds <= 300){
                characterGetsPocketMoney = true;
            }else {
                characterGetsPocketMoney = false;
            }

            //award $50 if the character got lucky
            if (characterGetsPocketMoney){
                playerCharacter.addMoney(50);
                gameMenu.displaySeperator(1);
                gameMenu.menuElement("You got some pocket money!", "", 2);
                gameMenu.menuElement("$50 added to wallet.", "", 2);
                gameMenu.displaySeperator(1);

            }else{
                //do nothing
            }

            //determine if the character's family wins the lottery
            int playerWinsLotteryOdds = ThreadLocalRandom.current().nextInt(0, 100000);
            Boolean playerDoesWinLottery = false;

            //odds: 1 in 100,000
            if (playerWinsLotteryOdds == 15){
                playerDoesWinLottery = true;
                playerCharacter.getInventory().appendToAwardsList("wonLottery");
                playerCharacter.setPlayerBooleanInfo(18, true);
            }

            //player has won the lottery!!! add $5,000,000 to their balance and display a nice message
            if (!playerCharacter.getPlayerBooleanInfo(12) && playerDoesWinLottery || playerLotteryForce){
                playerCharacter.setPlayerBooleanInfo(12, true);
                playerCharacter.addMoney(5000000);

                moneyMenu.displaySeperator(1);
                moneyMenu.menuElement("Congratulations!!! your family won the lottery!!!", "", 2);
                moneyMenu.menuElement("$5,000,000 has been gifted to you by your parents!", "", 2);
                moneyMenu.displaySeperator(1);
            }


            //---HEIRLOOMS----
            //give the character an heirloom if they pass an RNG check. the heirloom is based on the current age of the character. heirlooms are worth a lot of money.

            int heirloomRNGCheck = ThreadLocalRandom.current().nextInt(0, 10000);
            int heirloomToPick = ThreadLocalRandom.current().nextInt(0, 6);

            //odds: 1000/10000
            if (heirloomRNGCheck <= 5000 && heirloomRNGCheck >= 4000 || forceHeirloom){
                switch (heirloomToPick){
                    case 0:
                        //item - Moldy Bread (Penicillin) - value: $5
                        playerCharacter.getInventory().appendToHeirloomsList("Moldy Bread (Penicillin)", 5.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Moldy Bread (Penicillin)", 2);
                        gameMenu.menuElement("Value: ", "$5", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 1:
                        //item - Golden Tweezers - value: $300
                        playerCharacter.getInventory().appendToHeirloomsList("Golden Tweezers", 300.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Golden Tweezers", 2);
                        gameMenu.menuElement("Value: ", "$300", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 2:
                        //item - American Father Season 5 Red-Ray Box Set - value: $50
                        playerCharacter.getInventory().appendToHeirloomsList("American Father Season 5 Red-Ray Box Set", 50.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "American Father Season 5 Red-Ray Box Set", 2);
                        gameMenu.menuElement("Value: ", "$50", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 3:
                        //item - Emerald & Ruby Ring - value: $750
                        playerCharacter.getInventory().appendToHeirloomsList("Emerald & Ruby Ring", 750.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Emerald & Ruby Ring", 2);
                        gameMenu.menuElement("Value: ", "$750", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 4:
                        //item - PearBook Oxygen - value: $1,500
                        playerCharacter.getInventory().appendToHeirloomsList("PearBook Oxygen", 1500.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "PearBook Oxygen", 2);
                        gameMenu.menuElement("Value: ", "$1,500", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 5:
                        //item - Lintendo Witch Game Console - value: $350.0
                        playerCharacter.getInventory().appendToHeirloomsList("Lintendo Witch Game Console", 350.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Lintendo Witch Game Console", 2);
                        gameMenu.menuElement("Value: ", "$350.0", 2);
                        gameMenu.displaySeperator(2);
                        break;
                }

            }

            //----FRIENDS----
            //first, we run an RNG check to see if the player will be offered a new friendship (50/50 chance). if they are, we generate a new friend and offer it up to the player. 
            //if they accept, we append the new friend to the friend's index, set playerDoesHaveFriends to true, and move along.

            int playerGainsFriendRNG = ThreadLocalRandom.current().nextInt(0, 100);

            if (playerGainsFriendRNG <= 50 || forcePlayerFriend){
               //generate a friend
               MiniLifeFriend friendCandidate = new MiniLifeFriend();
               int friendGender = ThreadLocalRandom.current().nextInt(0, 3);
               String fcFirstName = "";
               String fcLastName = dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
               int fcAge = (ThreadLocalRandom.current().nextInt((playerCharacter.getAge() - 3), (playerCharacter.getAge() + 3)));
               switch(friendGender){
                    case 0:
                        fcFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    case 1:
                        fcFirstName = dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1));
                    case 2:
                        fcFirstName = dialogModule.getNBNameWithID(ThreadLocalRandom.current().nextInt(0, 78 + 1));    
               }
               
               friendCandidate.createFriend(fcFirstName, fcLastName, fcAge);

               friendMenu.displaySeperator(2);
               friendMenu.menuElement("You met someone new, and you hit it off!", "", 2);
               friendMenu.menuElement("Would you like to be friends?", "", 2);
               friendMenu.menuElement("Name: ", (friendCandidate.getFriendName() + " " + friendCandidate.getLastName()), 2);
               friendMenu.menuElement("Age: ", friendCandidate.getAge(), 2);
               friendMenu.displaySeperator(2);

                System.out.print("Make a new friend?: ");
                String userInput = input.next().trim().toLowerCase();
                char[] inputChar = userInput.toCharArray();

                if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                    playerCharacter.getFriendsList().add(friendCandidate);
                    playerCharacter.setPlayerBooleanInfo(4, true);
                }else{
                    logger.info("##DEBUG## - user chose to not make friend.");
                    errorMenu.displaySeperator(1);
                    errorMenu.menuElement("You did not become friends.", "", 2);
                    errorMenu.displaySeperator(1);
            }


            }

            //now, check if there are any friendships with low relationship scores which should be ended.
            for (int n = 0;n < playerCharacter.getFriendsList().size(); n++){
                if (playerCharacter.getFriendsList().get(n).getRelationship() < 15){
                    errorMenu.displaySeperator(1);
                    errorMenu.menuElement("Oh No :( you had a falling out with one of your friends.", "", 2);
                    errorMenu.menuElement("Name: ", (playerCharacter.getFriendsList().get(n).getFriendName() + " " + playerCharacter.getFriendsList().get(n).getLastName()), 2);
                    errorMenu.menuElement("You are no longer friends.", "", 2);
                    errorMenu.displaySeperator(1);
                    playerCharacter.getFriendsList().remove(n);
                    playerCharacter.getInventory().appendToAwardsList("lostFriend");
                    playerCharacter.setPlayerBooleanInfo(18, true);
                }
            }

            //check if there are any high-friendship friends who the character may fall in love with. only runs if it wins an RNG check, player has a valid high-level friendship, 
            //and player does not already have a romantic interest or spouse.
            int playerFallsInLoveRNG = ThreadLocalRandom.current().nextInt(0, 10);
            int playerLoveSuccess = ThreadLocalRandom.current().nextInt(0, 100);
            for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                if (playerCharacter.getFriendsList().get(n).getRelationship() > 90 && playerFallsInLoveRNG <= 4 && !(playerCharacter.getPlayerBooleanInfo(10) || playerCharacter.getPlayerBooleanInfo(11)) || forcePlayerFriend){
                    loveMenu.displaySeperator(1);
                    loveMenu.menuElement("You gaze deep into your friend's eyes... and notice that you suddenly feel nervous.", "", 2);
                    loveMenu.menuElement("Name: ", (playerCharacter.getFriendsList().get(n).getFriendName() + " " + playerCharacter.getFriendsList().get(n).getLastName()), 2);
                    loveMenu.menuElement("Would you like to ask this friend out?","",2);
                    loveMenu.displaySeperator(1);
                    System.out.print("Ask them out?: ");
                    String userInput = input.next().trim().toLowerCase();
                    char[] inputChar = userInput.toCharArray();

                    if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                        if (playerLoveSuccess <= 65){
                            List<MiniLifeFriend> playerRomanceList = new ArrayList<MiniLifeFriend>();
                            playerCharacter.setRomanceList(playerRomanceList);
                            playerCharacter.getRomanceList().add(playerCharacter.getFriendsList().get(n));
                            playerCharacter.getFriendsList().remove(n);

                        }else{
                            errorMenu.displaySeperator(1);
                            errorMenu.menuElement(":( You were rejected.", "", 2);
                            errorMenu.displaySeperator(1);
                            playerCharacter.getFriendsList().get(n).friendRelationshipDecline(15);
                        }
                    }else{
                        logger.info("##DEBUG## - user chose to not ask out crush.");
                        errorMenu.displaySeperator(1);
                        errorMenu.menuElement("You let the flame die out...", "", 2);
                        errorMenu.displaySeperator(1);

                }
            }
        }

            //----INJURY CHECK----
            int playerDoesGetInjuredOdds = ThreadLocalRandom.current().nextInt(0, 1000);
            Boolean playerDoesGetInjured = false;

            //odds 100/1000
            if (playerDoesGetInjuredOdds <= 100 || forcePlayerInjury){
                playerDoesGetInjured = true;
            }

            if (playerDoesGetInjured){
                playerCharacter.takeHealth(10);
                playerCharacter.getInventory().appendToAwardsList("gotInjured");
                playerCharacter.setPlayerBooleanInfo(18, true);

                errorMenu.displaySeperator(1);
                errorMenu.menuElement("You have been injured! You broke a leg.", "", 2);
                errorMenu.menuElement("You lost 10 health.", "", 2);
                errorMenu.displaySeperator(1);
            }


            //----CANCER CHECK----
            int playerDoesGetCancerOdds = ThreadLocalRandom.current().nextInt(0, 500000);
            Boolean playerDoesGetCancer = false;

            //odds: 100/500,000
            if (playerDoesGetCancerOdds <= 100 || playerCharacter.getPlayerBooleanInfo(7) || forcePlayerCancer){
                playerDoesGetCancer = true;
            }

            if (playerDoesGetCancer){
                playerCharacter.setPlayerBooleanInfo(7, true);
                playerCharacter.takeHealth(20);
                playerCharacter.getInventory().appendToAwardsList("gotCancer");
                playerCharacter.setPlayerBooleanInfo(18, true);

                errorMenu.displaySeperator(1);
                errorMenu.menuElement("Oh No! You have cancer :(", "", 2);
                errorMenu.menuElement("You lost 20 health. You will lose 20 health each year unless healed.", "", 2);
                errorMenu.displaySeperator(1);
            }


            //----SCHOOL----

            Boolean minigame2WasWon = false;
            Boolean minigame2ShouldBeRunConclusive = true;
            //enable school
            if (!playerCharacter.getPlayerBooleanInfo(2)){
                playerCharacter.setPlayerBooleanInfo(2, true);
            }

            //do another minigame check to offer the player to try and increase their GPA, only offered if the previous check failed/was rejected, and the player has a GPA below 3.0
            if (!minigame1ShouldBeRunConclusive && playerCharacter.getSchool().gpaGet() < 3.0 || minigameForceEnable){
                //check if a minigame should be run, also selects which minigame to run
                Boolean minigame2ShouldBeRun = true;
                minigame2ShouldBeRunConclusive = true;
                int minigameToRun_2 = ThreadLocalRandom.current().nextInt(1, 4);
                String minigameName_2;

                //tie minigame logic to the only currently working minigame (for debug)
                if (isDebug){
                    minigameToRun_2 = 0;
                }

                //ask the player if they would like to play a minigame to recieve a prize
                if (minigame2ShouldBeRun){
                    gameMenuHeader.displaySeperator(1);
                    gameMenuHeader.menuElement("---Minigame---", "", 2);
                    gameMenuHeader.displaySeperator(1);

                    gameMenu.menuElement("Would you like to play a minigame to try harder at school?", "", 2);
                    if (minigameToRun_2 == 0){
                        minigameName_2 = "Word Game (By Celeste)";
                    }else if (minigameToRun_2 == 1){
                        minigameName_2 = "Rock Paper Scissors (By Dal)";
                    }else if (minigameToRun_2 == 2){
                        minigameName_2 = "Coin Flip (By Monse)";
                    }else if (minigameToRun_2 == 3){
                        minigameName_2 = "Math Game (By Monse)";
                    }else{
                        minigameName_2 = "Error! Unknown Minigame";
                        minigame2WasWon = false;
                    }

                    gameMenu.menuElement("Minigame Name: ", minigameName_2, 2);
                    gameMenu.displaySeperator(1);

                    System.out.println("Play the minigame?: ");
                    String userInput = input.next().trim().toLowerCase();
                    char[] inputChar = userInput.toCharArray();

                    if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                        minigame2ShouldBeRunConclusive = true;
                    }else{
                        logger.info("##DEBUG## - user chose not to run minigame.");
                        minigame2ShouldBeRunConclusive = false;
                    }

                    //actually run the minigame, if the result of asking the user was a yes.
                    if (minigame2ShouldBeRunConclusive){
                        if (minigameToRun_2 == 0){
                            minigame2WasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                        }else if (minigameToRun_2 == 1){
                            minigame2WasWon = MiniLife_rpsGame.playGame(input);
                        }else if (minigameToRun_2 == 2){
                            minigame2WasWon = coinflip.play(input);
                        }else if (minigameToRun_2 == 3){
                            minigame2WasWon = mathgame.play(input);
                        }
                    }else{
                        //do nothing
                    }

                    if(minigame2WasWon){
                        //increase school GPA
                        playerCharacter.getSchool().gpaUp();
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! your GPA went up!", "", 2);
                        gameMenu.displaySeperator(1);
                    }
                    else {
                        playerCharacter.getSchool().gpaDown();
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Oh No! you failed! your GPA went down.", "", 2);
                        gameMenu.displaySeperator(1);
                    }
                }
            }

            //force a minigame to save the school career if the character has a GPA below 1.5
            if (playerCharacter.getSchool().gpaGet() < 1.5 || minigameForceEnable){
                Boolean minigame3WasWon = false;

                //do another minigame check to offer the player to try and increase their GPA, only offered if the previous check failed/was rejected, and the player has a GPA below 3.0
                if (!minigame1ShouldBeRunConclusive && !minigame2ShouldBeRunConclusive|| minigameForceEnable){
                    //check if a minigame should be run, also selects which minigame to run
                    Boolean minigame3ShouldBeRun = true;
                    Boolean minigame3ShouldBeRunConclusive = true;
                    int minigameToRun_3 = ThreadLocalRandom.current().nextInt(1, 4);
                    String minigameName_3;

                    //ask the player if they would like to play a minigame to recieve a prize
                    if (minigame3ShouldBeRun){
                        gameMenuHeader.displaySeperator(1);
                        gameMenuHeader.menuElement("---Minigame---", "", 2);
                        gameMenuHeader.displaySeperator(1);

                        gameMenu.menuElement("You are failing at school. You must play a minigame.", "", 2);
                        if (minigameToRun_3 == 0){
                            minigameName_3 = "Word Game (By Celeste)";
                        }else if (minigameToRun_3 == 1){
                            minigameName_3 = "Rock Paper Scissors (By Dal)";
                        }else if (minigameToRun_3 == 2){
                            minigameName_3 = "Coin Flip (By Monse)";
                        }else if (minigameToRun_3 == 3){
                            minigameName_3 = "Math Game (By Monse)";
                        }else{
                            minigameName_3 = "Error! Unknown Minigame";
                            minigame2WasWon = false;
                        }

                        gameMenu.menuElement("Minigame Name: ", minigameName_3, 2);
                        gameMenu.displaySeperator(1);

                        minigame3ShouldBeRunConclusive = true;


                        //actually run the minigame, if the result of asking the user was a yes.
                        if (minigame3ShouldBeRunConclusive){
                            if (minigameToRun_3 == 0){
                                minigame3WasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, false, isDebug);
                            }else if (minigameToRun_3 == 1){
                                minigame3WasWon = MiniLife_rpsGame.playGame(input);
                            }else if (minigameToRun_3 == 2){
                                minigame3WasWon = coinflip.play(input);
                            }else if (minigameToRun_3 == 2){
                                minigame3WasWon = mathgame.play(input);
                            }
                        }else{
                            //do nothing
                            if (isDebug){
                                minigame3WasWon = false;
                            }
                        }

                        if(minigame3WasWon){
                            //increase school GPA
                            playerCharacter.getSchool().gpaSet(playerCharacter.getSchool().gpaGet() + 1.5);
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement("Congrats! You saved your school career!", "", 2);
                            gameMenu.displaySeperator(1);
                        }
                        else {
                            playerCharacter.getSchool().gpaDown();
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement("Oh No! you failed! You are being sent to a remedial school.", "", 2);
                            gameMenu.displaySeperator(1);

                            playerCharacter.getInventory().appendToAwardsList("remedialSchool");
                            playerCharacter.setPlayerBooleanInfo(18, true);
                            playerCharacter.getSchool().setSchoolName(playerCharacter.getPlayerCity() + " Remedial Middle School");
                            playerCharacter.getSchool().gpaSet(2.0);
                        }
                    }
                }
            }

            //pick a new name for the school, reset GPA, etc. if the school is still an elementary school
            if (playerCharacter.getSchool().getSchoolName().contains("Elementary")){
                gameMenu.displaySeperator(1);
                gameMenu.menuElement("Congratulations!", "", 2);
                gameMenu.menuElement("You graduated from ", playerCharacter.getSchool().getSchoolName(), 2);
                gameMenu.displaySeperator(1);

                String newSchoolName = (dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)) + " " +
                dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1))+
                " Middle School");
                playerCharacter.getSchool().setSchoolName(newSchoolName);
                playerCharacter.getSchool().gpaSet(playerCharacter.getSchool().gpaGet() + 1.0);
            }

            

            //--RELATIONSHIP--
            //this section is about offering a chance to improve the relationship with your friends through a minigame, this section offers a chance to improve your relationships with all of
            //your friends by playing a small micro-game embedded within this module itself. this time around, it's a tech-focused quiz.

            int questionPicked = ThreadLocalRandom.current().nextInt(0, 5);

            String questionOne =  "When did Apple release the iPhone 6s?";
            String q1AnsOne = "1: September 25, 2015"; //correct
            String q1AnsTwo = "2: August 10, 2014";
            String q1AnsThree = "3: January 10, 2015";
            String q1AnsFour = "4: December 13, 2016";

            String questionTwo =  "What year did Windows XP come out?";
            String q2AnsOne = "1: 2001"; //correct
            String q2AnsTwo = "2: 2000";
            String q2AnsThree = "3: 2003";
            String q2AnsFour = "4: 2002";

            String questionThree =  "What is the kernel which runs under Windows 11?";
            String q3AnsOne = "1: BSD";
            String q3AnsTwo = "2: Linux";
            String q3AnsThree = "3: Windows NT"; //correct
            String q3AnsFour = "4: Windows Zulu";

            String questionFour =  "What year did the original iPod release?";
            String q4AnsOne = "1: 2000";
            String q4AnsTwo = "3: 2004";
            String q4AnsThree = "4: 1999";
            String q4AnsFour = "4: 2001"; //correct

            String questionFive =  "What processor company invented the x86-64 processor architecture?";
            String q5AnsOne = "1: Intel";
            String q5AnsTwo = "2: AMD"; //correct
            String q5AnsThree = "3: Cyrix";
            String q5AnsFour = "4: IBM";

            int triviaGameOffered = ThreadLocalRandom.current().nextInt(0, 10);
            Boolean runTriviaGame = false;
            Boolean playerWonTriviaGame = false;
            String triviaInput = "";
            char[] triviaChar;

            if (playerCharacter.getPlayerBooleanInfo(4) && triviaGameOffered <= 5){
                //ask the user if they would like to play the trivia game
                gameMenuHeader.displaySeperator(1);
                gameMenu.menuElement("~~~Friendship Game~~~", "", 2);
                gameMenu.menuElement("Would you like to play a trivia game to improve your friendships?", "", 2);
                gameMenuHeader.displaySeperator(1);

                System.out.print("Play the trivia game?: ");
                String userInput = input.next().trim().toLowerCase();
                char[] inputChar = userInput.toCharArray();

                if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                     runTriviaGame = true;
                }else{
                    logger.info("##DEBUG## - user chose not to run trivia game.");
                    runTriviaGame = false;
                 }

                 if (runTriviaGame || forceTriviaGame){
                    switch(questionPicked){
                        case 0:
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionOne, "", 2);
                            gameMenu.menuElement(q1AnsOne, "", 2);
                            gameMenu.menuElement(q1AnsTwo, "", 2);
                            gameMenu.menuElement(q1AnsThree, "", 2);
                            gameMenu.menuElement(q1AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '1' || triviaChar [0] == 'A' || triviaChar [0] == 'a'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q1AnsOne, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 1:
                            //question 2
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionTwo, "", 2);
                            gameMenu.menuElement(q2AnsOne, "", 2);
                            gameMenu.menuElement(q2AnsTwo, "", 2);
                            gameMenu.menuElement(q2AnsThree, "", 2);
                            gameMenu.menuElement(q2AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '1' || triviaChar [0] == 'A' || triviaChar [0] == 'a'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q2AnsOne, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 2:
                            //question 3
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionThree, "", 2);
                            gameMenu.menuElement(q3AnsOne, "", 2);
                            gameMenu.menuElement(q3AnsTwo, "", 2);
                            gameMenu.menuElement(q3AnsThree, "", 2);
                            gameMenu.menuElement(q3AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '3' || triviaChar [0] == 'C' || triviaChar [0] == 'C'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q3AnsThree, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 3:
                            //question 4
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionFour, "", 2);
                            gameMenu.menuElement(q4AnsOne, "", 2);
                            gameMenu.menuElement(q4AnsTwo, "", 2);
                            gameMenu.menuElement(q4AnsThree, "", 2);
                            gameMenu.menuElement(q4AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '4' || triviaChar [0] == 'D' || triviaChar [0] == 'd'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q4AnsFour, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 4:
                            //question 5
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionFive, "", 2);
                            gameMenu.menuElement(q5AnsOne, "", 2);
                            gameMenu.menuElement(q5AnsTwo, "", 2);
                            gameMenu.menuElement(q5AnsThree, "", 2);
                            gameMenu.menuElement(q5AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '2' || triviaChar [0] == 'B' || triviaChar [0] == 'b'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q5AnsTwo, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        default:
                            errorMenu.displaySeperator(1);
                            errorMenu.menuElement("###-ERROR-###: ", "switch statement overflow in trivia game (gpl2)", 2);
                            errorMenu.displaySeperator(1);
                            break;
                    }
                }
            }


            //----LOVE----
            //ask the player to answer a single, difficult question to save the relationship with their lover if it is doing poorly. if they fail, they will be dumped
            // String loveSaverInput = "";
            // char[] loveSaverChar = triviaInput.toCharArray();
            // char correctAnswer = '0.01';
            // if (playerCharacter.getPlayerBooleanInfo(10)){
            //     if (playerCharacter.getRomanceList().get(0).getRelationship() < 45){
            //         loveMenu.displaySeperator(1);
            //         loveMenu.menuElement("You arer at risk of breaking up!", "", 2);
            //         loveMenu.menuElement("You must answer the following question to save your relationship!", "", 2);
            //         loveMenu.displaySeperator(1);
            //         loveMenu.menuElement("1.23 divided by 8 times the square root of 234.5 is?", "", 2);
            //         loveSaverInput = input.next().trim().toLowerCase();
            //         loveSaverChar = loveSaverInput.toCharArray();

            //         if (loveSaverChar)){

            //         }

            //     }
            // }
            


            //ask the player at random to pick from a couple options for a gift to give to their lover, if they lose the RNG check then their lover will not like it and they will lose some
            //relationship value.
            int checkToRunDateRNG = ThreadLocalRandom.current().nextInt(0, 1000);
            int correctAnswer1 = ThreadLocalRandom.current().nextInt(1, 5);
            int correctAnswer2 = ThreadLocalRandom.current().nextInt(1, 5);
            int correctAnswer3 = ThreadLocalRandom.current().nextInt(1, 5);
            int correctAnswer4 = ThreadLocalRandom.current().nextInt(1, 5);
            String correctAnswer1Str = "" + correctAnswer1;
            String correctAnswer2Str = "" + correctAnswer2;
            String correctAnswer3Str = "" + correctAnswer3;
            String correctAnswer4Str = "" + correctAnswer4;
            String dateInput = "";
            int numAnsweredCorrectly = 0;
            String[] questionBank1 = {"Pick a flower: ", "1: Rose", "2: Daisy", "3: Azalea", "4: Sunflower"};
            String[] questionBank2 = {"Pick a gift: ", "1: Box of Chocolate", "2: Bottle of Soda", "3: Old Sock", "4: Super Mary Brothers Plushie"};
            String[] questionBank3 = {"Pick a location: ", "1: Cheese Factory", "2: McRonald's", "3: InFront Steakhouse", "4: Wall-Mart"};
            String[] questionBank4 = {"Pick a season: ", "1: Spring", "2: Summer", "3: Winter", "4: Fall"};
            if (checkToRunDateRNG < 350 && playerCharacter.getPlayerBooleanInfo(10) || forcePlayerFriend){
                loveMenu.displaySeperator(1);
                loveMenu.menuElement("Date Night!", "", 2);
                loveMenu.menuElement("Take your special someone out for a date, but pick wisely...", "", 2);
                loveMenu.displaySeperator(1);

                logger.info("##DEBUG## - correct answer: " + Arrays.asList(questionBank1).get(correctAnswer1));
                System.out.println(questionBank1[1]);
                System.out.println(questionBank1[2]);
                System.out.println(questionBank1[3]);
                System.out.println(questionBank1[4]);
                System.out.print(questionBank1[0]);
                dateInput = input.next().trim().toLowerCase();
                input.nextLine();
                if (dateInput.charAt(0) == correctAnswer1Str.charAt(0)){
                    dateInput = "";
                    numAnsweredCorrectly++;
                }

                loveMenu.displaySeperator(1);

                logger.info("##DEBUG## - correct answer: " + Arrays.asList(questionBank2).get(correctAnswer2));
                System.out.println(questionBank2[1]);
                System.out.println(questionBank2[2]);
                System.out.println(questionBank2[3]);
                System.out.println(questionBank2[4]);
                System.out.print(questionBank2[0]);
                dateInput = input.next().trim().toLowerCase();
                input.nextLine();
                if (dateInput.charAt(0) == correctAnswer2Str.charAt(0)){
                    dateInput = "";
                    numAnsweredCorrectly++;
                }

                loveMenu.displaySeperator(1);

                logger.info("##DEBUG## - correct answer: " + Arrays.asList(questionBank3).get(correctAnswer3));
                System.out.println(questionBank3[1]);
                System.out.println(questionBank3[2]);
                System.out.println(questionBank3[3]);
                System.out.println(questionBank3[4]);
                System.out.print(questionBank3[0]);
                dateInput = input.next().trim().toLowerCase();
                input.nextLine();
                if (dateInput.charAt(0) == correctAnswer3Str.charAt(0)){
                    dateInput = "";
                    numAnsweredCorrectly++;
                }

                loveMenu.displaySeperator(1);

                logger.info("##DEBUG## - correct answer: " + Arrays.asList(questionBank4).get(correctAnswer4));
                System.out.println(questionBank4[1]);
                System.out.println(questionBank4[2]);
                System.out.println(questionBank4[3]);
                System.out.println(questionBank4[4]);
                System.out.print(questionBank4[0]);
                dateInput = input.next().trim().toLowerCase();
                input.nextLine();
                if (dateInput.charAt(0) == correctAnswer4Str.charAt(0)){
                    dateInput = "";
                    numAnsweredCorrectly++;
                }

                if (isDebug && !playerCharacter.getPlayerBooleanInfo(10)){
                    List<MiniLifeFriend> debugRomanceList = new ArrayList<MiniLifeFriend>();
                    debugRomanceList.add(playerCharacter.getFriendsList().get(0));
                    playerCharacter.setRomanceList(debugRomanceList);
                }

                logger.info("##DEBUG## - numAnsweredCorrectly - " + numAnsweredCorrectly);

                switch(numAnsweredCorrectly){
                    case 0:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You Failed :(", "", 2);
                        loveMenu.menuElement("Your date hated all of your choices!", "", 2);
                        loveMenu.menuElement("You lost -10 relationship points", "", 2);
                        loveMenu.displaySeperator(1);
                        playerCharacter.getRomanceList().get(0).friendRelationshipDecline(10);
                        playerCharacter.getInventory().appendToAwardsList("badDate");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        break;
                    case 1:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You did horribly!", "", 2);
                        loveMenu.menuElement("Your date hated all but one of your choices!", "", 2);
                        loveMenu.menuElement("You lost -5 relationship points", "", 2);
                        loveMenu.displaySeperator(1);
                        playerCharacter.getRomanceList().get(0).friendRelationshipDecline(5);
                        break;
                    case 2:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You did ok.", "", 2);
                        loveMenu.menuElement("Your date was mildly entertained.", "", 2);
                        loveMenu.menuElement("You did not gain any relationship points.", "", 2);
                        loveMenu.displaySeperator(1);
                        break;
                    case 3:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You did good!", "", 2);
                        loveMenu.menuElement("Your date had a good time!", "", 2);
                        loveMenu.menuElement("You gained +5 relationship points!", "", 2);
                        loveMenu.displaySeperator(1);
                        playerCharacter.getRomanceList().get(0).friendRelationshipImprove(5);
                        break;
                    case 4:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You did amazingly!", "", 2);
                        loveMenu.menuElement("Your date had an amazing time!", "", 2);
                        loveMenu.menuElement("You gained +10 relationship points!", "", 2);
                        loveMenu.displaySeperator(1);
                        playerCharacter.getRomanceList().get(0).friendRelationshipImprove(10);
                        playerCharacter.getInventory().appendToAwardsList("greatDate");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        break;
                }
                
            }



            //---AGE UP---

            //assign the player a personality type at random, if they don't already have one.
            if (!playerCharacter.getPlayerBooleanInfo(13)){
                int randomPersonalityType = ThreadLocalRandom.current().nextInt(1, 9);
                playerCharacter.setPersonalityType(randomPersonalityType);
                gameMenu.displaySeperator(2);
                gameMenu.menuElement("You picked up the ", (playerCharacter.getPlayerPersonality() + " personality type!"), 2);
                gameMenu.displaySeperator(2);
            }

            //if the character has lost friends before, broken up, or fails an an rng check, make them have the "Depressed" personality type
            if (playerCharacter.getInventory().getAwardsList().contains("lostFriend") || playerCharacter.getInventory().getAwardsList().contains("brokeUp")){
                playerCharacter.setPersonalityType(7);
                playerCharacter.setPlayerBooleanInfo(14, true);
                playerCharacter.getInventory().appendToAwardsList("wasDepressed");
                playerCharacter.setPlayerBooleanInfo(18, true);
            }
            

            //run advanceYear functions in the various modules
            playerCharacter.advanceYear(); //age up player
            playerCharacter.getSchool().advanceYear(); //advance school year

            //lower friendship by 5 on odd years
            for (int n=0;n < playerCharacter.getFriendsList().size();n++){
                if ((playerCharacter.getAge() & 1) == 0){
                    playerCharacter.getFriendsList().get(n).friendRelationshipDecline(5);
                }
            }

            //lower relationship by 1 on odd years
            if (playerCharacter.getPlayerBooleanInfo(10)){
                for (int n=0;n < playerCharacter.getRomanceList().size();n++){
                    if ((playerCharacter.getAge() & 1) == 0){
                        playerCharacter.getRomanceList().get(n).friendRelationshipDecline(1);
                    }
                }
            }

            //lower GPA by 0.5 on even years
            if (!((playerCharacter.getAge() & 1) == 0)){
                if (!minigame2WasWon && playerCharacter.getSchool().gpaGet() > 1.0){
                     playerCharacter.getSchool().gpaDown();
                }
              }

            //age up player's friends, if they exist
            if (playerCharacter.getPlayerBooleanInfo(4)){
                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                    playerCharacter.getFriendsList().get(n).advanceYear();
                }
            }

            //age up player's romantic interest, if they exist
            if (playerCharacter.getPlayerBooleanInfo(10)){
                playerCharacter.getRomanceList().get(0).advanceYear();
            }

            gameMenu.displaySeperator(1);
            gameMenu.menuElement("The year has been advanced.", "", 2);
            gameMenu.menuElement("Years played: ", playerCharacter.getYearsPlayed(), 2);
            gameMenu.menuElement("Current school year: ", playerCharacter.getSchool().gradeGet(), 2);
            gameMenu.displaySeperator(1);

            if (playerCharacter.getPlayerBooleanInfo(18)){
                if (playerCharacter.getInventory().getAwardsList().contains("finishedDemo")){
                    playerCharacter.addMoney(playerCharacter.getJob().getSalary());
                    playerCharacter.getJob().advanceYear();

                    errorMenu.displaySeperator(1);
                    errorMenu.menuElement("You have finished the demo. You may continue playing.", "", 2);
                    errorMenu.menuElement("Your salary has been awarded.", "", 2);
                    errorMenu.menuElement("Years worked at job: ", playerCharacter.getJob().yearsworkedGet(), 2);
                    errorMenu.displaySeperator(1);
                }
            }
    }
    

    private void gameplayLoop3_reset(){
    
    }

    /**
     * Gameplay Loop 4 - High Schooler
     * Ages: 15 - 18
     * @apiNote the character is older now, and is in high school. functions to age up the character within school will continue (the grade will be advanced).
     * 
     * @apiNote the character is going to potentially face minigames for school, may make new friends (which will be generated in this loop, if the option so arises),
     * 
     * @apiNote the character may gain an heirloom, their family may win the lottery, they may find a wallet on the ground, 
     * 
     * @apiNote they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     * 
     * @apiNote the character may begin to form romantic feelings for a friend, and can now make some more advancement (though marriage remains locked)
     * 
     * @apiNote the character will recieve a new personality type during this block. it will be locked in (unless a new personality type is forced by an event). more things are affected by personality type.
     * 
     * @apiNote the player may get cancer in this era, if they are particularly unlucky. they may lose some health due to an injury or illness. (and may lose some school performance if these things happen)
     * 
     * @apiNote the player may choose to make the character petty shoplift, which can result in arrest, or the character taking on the "delinquent" or "criminal" personality types, 
     * greatly increasing the odds of injury, arrest, and criminal... opportunities in the future.
     * the player may now also choose to go full delinquent. they may make the character try and steal a car, or rob a house. these crimes can get the character in big trouble, but can be lucrative...
     * 
     * @apiNote the character can drive now, and if they are lucky they might be gifted a car by their family. if they previously won the lottery (or do now), they WILL be given a luxury car for free.
     * 
     * @apiNote the character can get a part time job now.
     */

    private void gameplayLoop4(Boolean doReset, Terminal sysTerm){
        if (doReset){
            gameplayLoop4_reset();
        }
        logger.info("##DEBUG## - gameplayLoop4");
    }

    private void gameplayLoop4_reset(){
    
    }



    /**
     * Gameplay Loop 5 - Young Adult
     * Ages: 19-29
     * @apiNote the character is older now, and is finally an adult. the character may or may not be in college at this time, depending on the player choice. if they are, 
     * @apiNote school-related checks will continue to happen
     * 
     * @apiNote the character is going to potentially face minigames for school, may make new friends (which will be generated in this loop, if the option so arises),
     * 
     * @apiNote the character may gain an heirloom, they may win the lottery, or they may find a wallet on the ground
     * 
     * @apiNote If in college, they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     * 
     * @apiNote the character may begin to form romantic feelings for a friend, and can now make some more advancement. they can get married.
     * 
     * @apiNote the character's personality is locked in (unless a new personality type is forced by an event). even more things are affected by personality type.
     * 
     * @apiNote the player may get cancer in this era, if they are particularly unlucky. they may lose some health due to an injury or illness. (and may lose some school/work performance if these things happen)
     * 
     * @apiNote the player may choose to make the character petty shoplift, which can result in arrest, or the character taking on the "delinquent" or "criminal" personality types, 
     * greatly increasing the odds of injury, arrest, and criminal... opportunities in the future.
     * the player may now also choose to go full delinquent. they may make the character try and steal a car, or rob a house. these crimes can get the character in big trouble, but can be lucrative...
     * the character may get a job working as a criminal if they have the "criminal" personality type. this will allow them to formalize their criminal behavior, and make money!!! 
     * 
     * @apiNote the player may be asked if the character should start smoking. this will greatly increase the chances of lung cancer, remove an amount from the player's money each year, and cause a slight debuff to the player's relationships.
     * 
     * @apiNote the character may finally get a full-time job in a degreeless field. once they graduate, they can finally get a degree-required job and enter the workforce.
     */

    private void gameplayLoop5(Boolean doReset, Terminal sysTerm){
        if (doReset){
            gameplayLoop5_reset();
        }
        logger.info("##DEBUG## - gameplayLoop5");
    }

    private void gameplayLoop5_reset(){
    
    }

    /**
     * Gameplay Loop 6 - Adult, Middle-aged, Young Senior
     * Ages: 30-69
     * @apiNote the character is a working adult. the character will have graduated from college (or failed out from it), will likely own a car, will probably have friends and romantic interests now...
     * they may even be married.
     * @apiNote school-related checks will no longer happen, unless the player chooses to go to college later/go back to college. 
     * 
     * @apiNote the character is going to potentially face minigames for school, may make new friends (which will be generated in this loop, if the option so arises),
     * 
     * @apiNote the character may gain an heirloom, they may win the lottery, or they may find a wallet on the ground
     * 
     * @apiNote if in college, they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     * 
     * @apiNote the character may begin to form romantic feelings for a friend, and can now make some more advancement. they can get married.
     * 
     * @apiNote the character's personality type is locked in (unless a new personality type is forced by an event). most things are affected by personality type.
     * 
     * @apiNote the player may get cancer in this era, if they are particularly unlucky. they may lose some health due to an injury or illness. (and may lose some school performance if these things happen)
     * 
     * @apiNote the player may choose to make the character petty shoplift, which can result in arrest, or the character taking on the "delinquent" or "criminal" personality types, 
     * greatly increasing the odds of injury, arrest, and criminal... opportunities in the future.
     * the player may now also choose to go full delinquent. they may make the character try and steal a car, or rob a house. these crimes can get the character in big trouble, but can be lucrative...
     * the character may get a job working as a criminal if they have the "criminal" personality type. this will allow them to formalize their criminal behavior, and make money!!! 
     * however, if the player is a criminal, they may end up in prison by now... or perhaps they are the leader of a criminal organization... 
     * 
     * @apiNote the player may be asked if the character should start smoking. this will greatly increase the chances of lung cancer, remove an amount from the player's money each year, and cause a slight debuff to the player's relationships.
     * 
     * @apiNote the character may finally get a full-time job in a degreeless field. once they graduate, they can finally get a degree-required job and enter the workforce.
     * 
     * @apiNote if the player is a professional furry, they will be making insane money, but it is incredibly rare that this job comes up. secretely, this is the career path to becoming the ruler of the world...
     */

    private void gameplayLoop6(Boolean doReset, Terminal sysTerm){
        if (doReset){
            gameplayLoop6_reset();
        }
        logger.info("##DEBUG## - gameplayLoop6");
    }

    private void gameplayLoop6_reset(){
    
    }

    /**
     * Gameplay Loop 7 - Senior-Death
     * Ages: 70-end
     * @apiNote the character is a senior. they will be forced to retire at this point, unless they are poor, in which case the player may have to apply for a minimum wage job (considered a loss condition)
     *
     * @apiNote school-related checks will no longer happen, and jobs are greatly limited. 
     * 
     * @apiNote the character may make new friends (which will be generated in this loop, if the option so arises),
     * 
     * @apiNote the character may gain an heirloom, they may win the lottery, or they may find a wallet on the ground
     * 
     * @apiNote if in college, they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     * 
     * @apiNote the character may begin to form romantic feelings for a friend, and can now make some more advancement. they can get married.
     * 
     * @apiNote the character's personality type is locked in (unless a new personality type is forced by an event). most things are affected by personality type.
     * 
     * @apiNote the player may get cancer in this era, if they are particularly unlucky. they may lose some health due to an injury or illness. (and may lose some school performance if these things happen)
     * 
     * @apiNote the player may choose to make the character petty shoplift, which can result in arrest, or the character taking on the "delinquent" or "criminal" personality types, 
     * greatly increasing the odds of injury, arrest, and criminal... opportunities in the future.
     * the player may now also choose to go full delinquent. they may make the character try and steal a car, or rob a house. these crimes can get the character in big trouble, but can be lucrative...
     * the character may get a job working as a criminal if they have the "criminal" personality type. this will allow them to formalize their criminal behavior, and make money!!! 
     * however, if the player is a criminal, they may end up in prison by now... or perhaps they are the leader of a criminal organization... 
     * 
     * @apiNote the player may be asked if the character should start smoking. this will greatly increase the chances of lung cancer, remove an amount from the player's money each year, and cause a slight debuff to the player's relationships.
     * 
     * @apiNote the character may finally get a full-time job in a degreeless field. once they graduate, they can finally get a degree-required job and enter the workforce.
     * 
     * @apiNote if the player is a professional furry, they will be making insane money, but it is incredibly rare that this job comes up. secretely, this is the career path to becoming the ruler of the world...
     */

    private void gameplayLoop7(Boolean doReset, Terminal sysTerm){
        if (doReset){
            gameplayLoop7_reset();
        }
        logger.info("##DEBUG## - gameplayLoop7");
    }

    private void gameplayLoop7_reset(){
    
    }

    private void gameplayLoopDemoEnd(Boolean doReset, Terminal sysTerm) throws Exception{
        if (doReset){
            gameplayLoop3_reset();
        }
        logger.info("##DEBUG## - gameplayLoopDemoEnd");

        //ask to clear the console (if in debug mode)
        if (isDebug){
            logger.info("##DEBUG## - Clear the console?: ");
            String doClearConsole = input.next().trim().toLowerCase();
            if (doClearConsole.charAt(0) == '1' || doClearConsole.charAt(0) == 'y' || doClearConsole.charAt(0) == 't'){
                MiniLifeMain.clearConsole();
            }
        }

        //setup menu stuff
        int menu_width = 120;
        MiniLifeMenu gameMenuHeader = new MiniLifeMenu();
        gameMenuHeader.createMenu("*", "*", "~", "!", menu_width, false, sysTerm);

        MiniLifeMenu gameMenu = new MiniLifeMenu();
        gameMenu.createMenu("\u25CF", "\u25CF", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu errorMenu = new MiniLifeMenu();
        errorMenu.createMenu("\u26CC", "\u26CC", "-", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu loveMenu = new MiniLifeMenu();
        loveMenu.createMenu("\u2765", "\u2765", "~", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu friendMenu = new MiniLifeMenu();
        friendMenu.createMenu(":3", ":3", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu transMenu = new MiniLifeMenu();
        transMenu.createMenu("\u26A7", "\u26A7", "\u25AC", "\u258B", menu_width, false, sysTerm);

        MiniLifeMenu moneyMenu = new MiniLifeMenu();
        moneyMenu.createMenu("$", "$", "\u25AC", "\u258B", menu_width, false, sysTerm);
        
        //----DEMO END STUFF----
        
        //first, inform the user that the demo has ended.
        errorMenu.displaySeperator(1);
        errorMenu.menuElement("Alert! The demo has ended.", "", 2);
        errorMenu.menuElement("You will be assigned a random job so that you may continue playing.", "", 2);
        errorMenu.menuElement("You will be unable to continue to the later school or life stages.", "", 2);
        errorMenu.menuElement("You will be unable to get married or have children.", "", 2);
        errorMenu.menuElement("You will die at the age of 75.", "", 2);
        errorMenu.menuElement("-----END OF DEMO-----", "", 2);
        errorMenu.displaySeperator(1);


        //assign the player a random job, set all the flags for having a job (and for finishing the demo)
        MiniLifeJob demoJob = new MiniLifeJob();
        String demoJobName = dialogModule.getLowJobNameWithID(ThreadLocalRandom.current().nextInt(0, 73 + 1));
        String demoCompanyName = dialogModule.getCompanyNameWithID(ThreadLocalRandom.current().nextInt(0, 55 + 1));
        Double demoSalary = ThreadLocalRandom.current().nextDouble(50000.0, 150000.0);
        demoJob.createJob(demoJobName, demoCompanyName, demoSalary, 0);

        playerCharacter.setPlayerBooleanInfo(18, true);
        playerCharacter.setPlayerBooleanInfo(1, true);
        playerCharacter.getInventory().appendToAwardsList("gotJob");
        playerCharacter.getInventory().appendToAwardsList("finishedDemo");

        playerCharacter.setJob(demoJob);











        //-------MINIGAME--------

        //check if a minigame should be run, also selects which minigame to run
        int minigame1RunPotential = ThreadLocalRandom.current().nextInt(0, 100);
        Boolean minigame1ShouldBeRun = false;
        Boolean minigame1ShouldBeRunConclusive = false;
        Boolean minigame1WasWon = false;
        int minigameToRun = ThreadLocalRandom.current().nextInt(1, 4);
        String minigameName;

        //odds: 35/100
        if (minigame1RunPotential <= 35 || minigameForceEnable){
            minigame1ShouldBeRun = true;
        }else {
            minigame1ShouldBeRun = false;
        }

        //ask the player if they would like to play a minigame to recieve a prize
        if (minigame1ShouldBeRun){
            gameMenuHeader.displaySeperator(1);
            gameMenuHeader.menuElement("---Minigame---", "", 2);
            gameMenuHeader.displaySeperator(1);

            gameMenu.menuElement("Would you like to play a minigame to recieve an award?", "", 2);
            if (minigameToRun == 0){
                 minigameName = "Word Game (By Celeste)";
            }else if (minigameToRun == 1){
                 minigameName = "Rock Paper Scissors (By Dal)";
            }else if (minigameToRun == 2){
                 minigameName = "Coin Flip (By Monse)";
            }else if (minigameToRun == 3){
                 minigameName = "Math Game (By Monse)";
            }else{
                 minigameName = "Error! Unknown Minigame";
            }

            gameMenu.menuElement("Minigame Name: ", minigameName, 2);
            gameMenu.displaySeperator(1);

            System.out.println("Play the minigame?: ");
            String userInput = input.next().trim().toLowerCase();
            char[] inputChar = userInput.toCharArray();

            if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                minigame1ShouldBeRunConclusive = true;
            }else{
                logger.info("##DEBUG## - user chose not to run minigame.");
                minigame1ShouldBeRunConclusive = false;
            }

            //actually run the minigame, if the result of asking the user was a yes.
            if (minigame1ShouldBeRunConclusive){
                if (minigameToRun == 0){
                    minigame1WasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                }else if (minigameToRun == 1){
                    minigame1WasWon = MiniLife_rpsGame.playGame(input);
                }else if (minigameToRun == 2){
                    minigame1WasWon = coinflip.play(input);
                }else if (minigameToRun == 3){
                    minigame1WasWon = mathgame.play(input);
                }
            }else{
                //do nothing
            }

            int randomPrize = ThreadLocalRandom.current().nextInt(0, 5);
            if(minigame1WasWon){
                switch(randomPrize){
                    case 1:
                        //give the character a lintendo dualscreen (value: $200) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Lintendo DualScreen", 200.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Lintendo DualScreen ($150 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 2:
                        //give the character a game child super (value: $200) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Game Child Super", 200.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Game Child Super ($200 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 3:
                        //give the character a Saga Neptune (value: $300) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Saga Neptune", 300.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Saga Neptune ($300 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    case 4:
                        //give the character an old boot (value: $0.50) if the player wins the minigame
                        playerCharacter.getInventory().appendToHeirloomsList("Old Boot", 0.50);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! you won! you got: ", "Old Boot ($0.50 Value)", 2);
                        gameMenu.displaySeperator(1);
                        break;
                    default:
                        //an error has occured. the player should be given an ErrorItem (value: $0).
                        logger.info("##DEBUG## - error in gameplayLoop1_minigame: irrational response from RNG. adding the \"error\" item to the player inventory");
                        playerCharacter.getInventory().appendToHeirloomsList("ErrorItem", 0.0);
                        playerCharacter.getInventory().appendToAwardsList("wonMinigame");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        break;
                }
            }
        }

            //----LOTTERY AND POCKET MONEY----

            //now, run some checks to determine if the character gets pocket money from family
            int characterGetsPocketMoneyOdds = ThreadLocalRandom.current().nextInt(0, 500);
            Boolean characterGetsPocketMoney = false;

            //odds: 50/500 (5/100)
            if (characterGetsPocketMoneyOdds >= 250 && characterGetsPocketMoneyOdds <= 300){
                characterGetsPocketMoney = true;
            }else {
                characterGetsPocketMoney = false;
            }

            //award $50 if the character got lucky
            if (characterGetsPocketMoney){
                playerCharacter.addMoney(50);
                gameMenu.displaySeperator(1);
                gameMenu.menuElement("You got some pocket money!", "", 2);
                gameMenu.menuElement("$50 added to wallet.", "", 2);
                gameMenu.displaySeperator(1);

            }else{
                //do nothing
            }

            //determine if the character's family wins the lottery
            int playerWinsLotteryOdds = ThreadLocalRandom.current().nextInt(0, 100000);
            Boolean playerDoesWinLottery = false;

            //odds: 1 in 100,000
            if (playerWinsLotteryOdds == 15){
                playerDoesWinLottery = true;
                playerCharacter.getInventory().appendToAwardsList("wonLottery");
                playerCharacter.setPlayerBooleanInfo(18, true);
            }

            //player has won the lottery!!! add $5,000,000 to their balance and display a nice message
            if (!playerCharacter.getPlayerBooleanInfo(12) && playerDoesWinLottery || playerLotteryForce){
                playerCharacter.setPlayerBooleanInfo(12, true);
                playerCharacter.addMoney(5000000);

                moneyMenu.displaySeperator(1);
                moneyMenu.menuElement("Congratulations!!! your family won the lottery!!!", "", 2);
                moneyMenu.menuElement("$5,000,000 has been gifted to you by your parents!", "", 2);
                moneyMenu.displaySeperator(1);
            }


            //---HEIRLOOMS----
            //give the character an heirloom if they pass an RNG check. the heirloom is based on the current age of the character. heirlooms are worth a lot of money.

            int heirloomRNGCheck = ThreadLocalRandom.current().nextInt(0, 10000);
            int heirloomToPick = ThreadLocalRandom.current().nextInt(0, 6);

            //odds: 1000/10000
            if (heirloomRNGCheck <= 5000 && heirloomRNGCheck >= 4000 || forceHeirloom){
                switch (heirloomToPick){
                    case 0:
                        //item - Moldy Bread (Penicillin) - value: $5
                        playerCharacter.getInventory().appendToHeirloomsList("Moldy Bread (Penicillin)", 5.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Moldy Bread (Penicillin)", 2);
                        gameMenu.menuElement("Value: ", "$5", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 1:
                        //item - Golden Tweezers - value: $300
                        playerCharacter.getInventory().appendToHeirloomsList("Golden Tweezers", 300.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Golden Tweezers", 2);
                        gameMenu.menuElement("Value: ", "$300", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 2:
                        //item - American Father Season 5 Red-Ray Box Set - value: $50
                        playerCharacter.getInventory().appendToHeirloomsList("American Father Season 5 Red-Ray Box Set", 50.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "American Father Season 5 Red-Ray Box Set", 2);
                        gameMenu.menuElement("Value: ", "$50", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 3:
                        //item - Emerald & Ruby Ring - value: $750
                        playerCharacter.getInventory().appendToHeirloomsList("Emerald & Ruby Ring", 750.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Emerald & Ruby Ring", 2);
                        gameMenu.menuElement("Value: ", "$750", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 4:
                        //item - PearBook Oxygen - value: $1,500
                        playerCharacter.getInventory().appendToHeirloomsList("PearBook Oxygen", 1500.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "PearBook Oxygen", 2);
                        gameMenu.menuElement("Value: ", "$1,500", 2);
                        gameMenu.displaySeperator(2);
                        break;
                    case 5:
                        //item - Lintendo Witch Game Console - value: $350.0
                        playerCharacter.getInventory().appendToHeirloomsList("Lintendo Witch Game Console", 350.0);
                        playerCharacter.setPlayerBooleanInfo(17, true);
                        gameMenu.displaySeperator(2);
                        gameMenu.menuElement("Congrats! You got an heirloom: ", "Lintendo Witch Game Console", 2);
                        gameMenu.menuElement("Value: ", "$350.0", 2);
                        gameMenu.displaySeperator(2);
                        break;
                }

            }

            //----FRIENDS----
            //first, we run an RNG check to see if the player will be offered a new friendship (50/50 chance). if they are, we generate a new friend and offer it up to the player. 
            //if they accept, we append the new friend to the friend's index, set playerDoesHaveFriends to true, and move along.

            int playerGainsFriendRNG = ThreadLocalRandom.current().nextInt(0, 100);

            if (playerGainsFriendRNG <= 50 || forcePlayerFriend){
               //generate a friend
               MiniLifeFriend friendCandidate = new MiniLifeFriend();
               int friendGender = ThreadLocalRandom.current().nextInt(0, 3);
               String fcFirstName = "";
               String fcLastName = dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1));
               int fcAge = (ThreadLocalRandom.current().nextInt((playerCharacter.getAge() - 3), (playerCharacter.getAge() + 3)));
               switch(friendGender){
                    case 0:
                        fcFirstName = dialogModule.getFemaleNameWithID(ThreadLocalRandom.current().nextInt(0, 193 + 1));
                    case 1:
                        fcFirstName = dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1));
                    case 2:
                        fcFirstName = dialogModule.getNBNameWithID(ThreadLocalRandom.current().nextInt(0, 78 + 1));    
               }
               
               friendCandidate.createFriend(fcFirstName, fcLastName, fcAge);

               friendMenu.displaySeperator(2);
               friendMenu.menuElement("You met someone new, and you hit it off!", "", 2);
               friendMenu.menuElement("Would you like to be friends?", "", 2);
               friendMenu.menuElement("Name: ", (friendCandidate.getFriendName() + " " + friendCandidate.getLastName()), 2);
               friendMenu.menuElement("Age: ", friendCandidate.getAge(), 2);
               friendMenu.displaySeperator(2);

                System.out.print("Make a new friend?: ");
                String userInput = input.next().trim().toLowerCase();
                char[] inputChar = userInput.toCharArray();

                if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                    playerCharacter.getFriendsList().add(friendCandidate);
                    playerCharacter.setPlayerBooleanInfo(4, true);
                }else{
                    logger.info("##DEBUG## - user chose to not make friend.");
                    errorMenu.displaySeperator(1);
                    errorMenu.menuElement("You did not become friends.", "", 2);
                    errorMenu.displaySeperator(1);
            }


            }

            //now, check if there are any friendships with low relationship scores which should be ended.
            for (int n = 0;n < playerCharacter.getFriendsList().size(); n++){
                if (playerCharacter.getFriendsList().get(n).getRelationship() < 15){
                    errorMenu.displaySeperator(1);
                    errorMenu.menuElement("Oh No :( you had a falling out with one of your friends.", "", 2);
                    errorMenu.menuElement("Name: ", (playerCharacter.getFriendsList().get(n).getFriendName() + " " + playerCharacter.getFriendsList().get(n).getLastName()), 2);
                    errorMenu.menuElement("You are no longer friends.", "", 2);
                    errorMenu.displaySeperator(1);
                    playerCharacter.getFriendsList().remove(n);
                    playerCharacter.getInventory().appendToAwardsList("lostFriend");
                    playerCharacter.setPlayerBooleanInfo(18, true);
                }
            }

            //check if there are any high-friendship friends who the character may fall in love with. only runs if it wins an RNG check, player has a valid high-level friendship, 
            //and player does not already have a romantic interest or spouse.
            int playerFallsInLoveRNG = ThreadLocalRandom.current().nextInt(0, 10);
            int playerLoveSuccess = ThreadLocalRandom.current().nextInt(0, 100);
            for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                if (playerCharacter.getFriendsList().get(n).getRelationship() > 90 && playerFallsInLoveRNG <= 4 && !(playerCharacter.getPlayerBooleanInfo(10) || playerCharacter.getPlayerBooleanInfo(11)) || forcePlayerFriend){
                    loveMenu.displaySeperator(1);
                    loveMenu.menuElement("You gaze deep into your friend's eyes... and notice that you suddenly feel nervous.", "", 2);
                    loveMenu.menuElement("Name: ", (playerCharacter.getFriendsList().get(n).getFriendName() + " " + playerCharacter.getFriendsList().get(n).getLastName()), 2);
                    loveMenu.menuElement("Would you like to ask this friend out?","",2);
                    loveMenu.displaySeperator(1);
                    System.out.print("Ask them out?: ");
                    String userInput = input.next().trim().toLowerCase();
                    char[] inputChar = userInput.toCharArray();

                    if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                        if (playerLoveSuccess <= 65){
                            List<MiniLifeFriend> playerRomanceList = new ArrayList<MiniLifeFriend>();
                            playerCharacter.setRomanceList(playerRomanceList);
                            playerCharacter.getRomanceList().add(playerCharacter.getFriendsList().get(n));
                            playerCharacter.getFriendsList().remove(n);

                        }else{
                            errorMenu.displaySeperator(1);
                            errorMenu.menuElement(":( You were rejected.", "", 2);
                            errorMenu.displaySeperator(1);
                            playerCharacter.getFriendsList().get(n).friendRelationshipDecline(15);
                        }
                    }else{
                        logger.info("##DEBUG## - user chose to not ask out crush.");
                        errorMenu.displaySeperator(1);
                        errorMenu.menuElement("You let the flame die out...", "", 2);
                        errorMenu.displaySeperator(1);

                }
            }
        }

            //----INJURY CHECK----
            int playerDoesGetInjuredOdds = ThreadLocalRandom.current().nextInt(0, 1000);
            Boolean playerDoesGetInjured = false;

            //odds 100/1000
            if (playerDoesGetInjuredOdds <= 100 || forcePlayerInjury){
                playerDoesGetInjured = true;
            }

            if (playerDoesGetInjured){
                playerCharacter.takeHealth(10);
                playerCharacter.getInventory().appendToAwardsList("gotInjured");
                playerCharacter.setPlayerBooleanInfo(18, true);

                errorMenu.displaySeperator(1);
                errorMenu.menuElement("You have been injured! You broke a leg.", "", 2);
                errorMenu.menuElement("You lost 10 health.", "", 2);
                errorMenu.displaySeperator(1);
            }


            //----CANCER CHECK----
            int playerDoesGetCancerOdds = ThreadLocalRandom.current().nextInt(0, 500000);
            Boolean playerDoesGetCancer = false;

            //odds: 100/500,000
            if (playerDoesGetCancerOdds <= 100 || playerCharacter.getPlayerBooleanInfo(7) || forcePlayerCancer){
                playerDoesGetCancer = true;
            }

            if (playerDoesGetCancer){
                playerCharacter.setPlayerBooleanInfo(7, true);
                playerCharacter.takeHealth(20);
                playerCharacter.getInventory().appendToAwardsList("gotCancer");
                playerCharacter.setPlayerBooleanInfo(18, true);

                errorMenu.displaySeperator(1);
                errorMenu.menuElement("Oh No! You have cancer :(", "", 2);
                errorMenu.menuElement("You lost 20 health. You will lose 20 health each year unless healed.", "", 2);
                errorMenu.displaySeperator(1);
            }


            //----SCHOOL----

            Boolean minigame2WasWon = false;
            Boolean minigame2ShouldBeRunConclusive = true;
            //enable school
            if (!playerCharacter.getPlayerBooleanInfo(2)){
                playerCharacter.setPlayerBooleanInfo(2, true);
            }

            //do another minigame check to offer the player to try and increase their GPA, only offered if the previous check failed/was rejected, and the player has a GPA below 3.0
            if (!minigame1ShouldBeRunConclusive && playerCharacter.getSchool().gpaGet() < 3.0 || minigameForceEnable){
                //check if a minigame should be run, also selects which minigame to run
                Boolean minigame2ShouldBeRun = true;
                minigame2ShouldBeRunConclusive = true;
                int minigameToRun_2 = ThreadLocalRandom.current().nextInt(1, 4);
                String minigameName_2;

                //tie minigame logic to the only currently working minigame (for debug)
                if (isDebug){
                    minigameToRun_2 = 0;
                }

                //ask the player if they would like to play a minigame to recieve a prize
                if (minigame2ShouldBeRun){
                    gameMenuHeader.displaySeperator(1);
                    gameMenuHeader.menuElement("---Minigame---", "", 2);
                    gameMenuHeader.displaySeperator(1);

                    gameMenu.menuElement("Would you like to play a minigame to try harder at school?", "", 2);
                    if (minigameToRun_2 == 0){
                        minigameName_2 = "Word Game (By Celeste)";
                    }else if (minigameToRun_2 == 1){
                        minigameName_2 = "Rock Paper Scissors (By Dal)";
                    }else if (minigameToRun_2 == 2){
                        minigameName_2 = "Coin Flip (By Monse)";
                    }else if (minigameToRun_2 == 3){
                        minigameName_2 = "Math Game (By Monse)";
                    }else{
                        minigameName_2 = "Error! Unknown Minigame";
                        minigame2WasWon = false;
                    }

                    gameMenu.menuElement("Minigame Name: ", minigameName_2, 2);
                    gameMenu.displaySeperator(1);

                    System.out.println("Play the minigame?: ");
                    String userInput = input.next().trim().toLowerCase();
                    char[] inputChar = userInput.toCharArray();

                    if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                        minigame2ShouldBeRunConclusive = true;
                    }else{
                        logger.info("##DEBUG## - user chose not to run minigame.");
                        minigame2ShouldBeRunConclusive = false;
                    }

                    //actually run the minigame, if the result of asking the user was a yes.
                    if (minigame2ShouldBeRunConclusive){
                        if (minigameToRun_2 == 0){
                            minigame2WasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, true, isDebug);
                        }else if (minigameToRun_2 == 1){
                            minigame2WasWon = MiniLife_rpsGame.playGame(input);
                        }else if (minigameToRun_2 == 2){
                            minigame2WasWon = coinflip.play(input);
                        }else if (minigameToRun_2 == 3){
                            minigame2WasWon = mathgame.play(input);
                        }
                    }else{
                        //do nothing
                    }

                    if(minigame2WasWon){
                        //increase school GPA
                        playerCharacter.getSchool().gpaUp();
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Congrats! your GPA went up!", "", 2);
                        gameMenu.displaySeperator(1);
                    }
                    else {
                        playerCharacter.getSchool().gpaDown();
                        gameMenu.displaySeperator(1);
                        gameMenu.menuElement("Oh No! you failed! your GPA went down.", "", 2);
                        gameMenu.displaySeperator(1);
                    }
                }
            }

            //force a minigame to save the school career if the character has a GPA below 1.5
            if (playerCharacter.getSchool().gpaGet() < 1.5 || minigameForceEnable){
                Boolean minigame3WasWon = false;

                //do another minigame check to offer the player to try and increase their GPA, only offered if the previous check failed/was rejected, and the player has a GPA below 3.0
                if (!minigame1ShouldBeRunConclusive && !minigame2ShouldBeRunConclusive|| minigameForceEnable){
                    //check if a minigame should be run, also selects which minigame to run
                    Boolean minigame3ShouldBeRun = true;
                    Boolean minigame3ShouldBeRunConclusive = true;
                    int minigameToRun_3 = ThreadLocalRandom.current().nextInt(1, 4);
                    String minigameName_3;

                    //ask the player if they would like to play a minigame to recieve a prize
                    if (minigame3ShouldBeRun){
                        gameMenuHeader.displaySeperator(1);
                        gameMenuHeader.menuElement("---Minigame---", "", 2);
                        gameMenuHeader.displaySeperator(1);

                        gameMenu.menuElement("You are failing at school. You must play a minigame.", "", 2);
                        if (minigameToRun_3 == 0){
                            minigameName_3 = "Word Game (By Celeste)";
                        }else if (minigameToRun_3 == 1){
                            minigameName_3 = "Rock Paper Scissors (By Dal)";
                        }else if (minigameToRun_3 == 2){
                            minigameName_3 = "Coin Flip (By Monse)";
                        }else if (minigameToRun_3 == 3){
                            minigameName_3 = "Math Game (By Monse)";
                        }else{
                            minigameName_3 = "Error! Unknown Minigame";
                            minigame2WasWon = false;
                        }

                        gameMenu.menuElement("Minigame Name: ", minigameName_3, 2);
                        gameMenu.displaySeperator(1);

                        minigame3ShouldBeRunConclusive = true;


                        //actually run the minigame, if the result of asking the user was a yes.
                        if (minigame3ShouldBeRunConclusive){
                            if (minigameToRun_3 == 0){
                                minigame3WasWon = MiniLife_WordGame.launchWordGame(input, dialogModule, logger, false, isDebug);
                            }else if (minigameToRun_3 == 1){
                                minigame3WasWon = MiniLife_rpsGame.playGame(input);
                            }else if (minigameToRun_3 == 2){
                                minigame3WasWon = coinflip.play(input);
                            }else if (minigameToRun_3 == 2){
                                minigame3WasWon = mathgame.play(input);
                            }
                        }else{
                            //do nothing
                            if (isDebug){
                                minigame3WasWon = false;
                            }
                        }

                        if(minigame3WasWon){
                            //increase school GPA
                            playerCharacter.getSchool().gpaSet(playerCharacter.getSchool().gpaGet() + 1.5);
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement("Congrats! You saved your school career!", "", 2);
                            gameMenu.displaySeperator(1);
                        }
                        else {
                            playerCharacter.getSchool().gpaDown();
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement("Oh No! you failed! You are being sent to a remedial school.", "", 2);
                            gameMenu.displaySeperator(1);

                            playerCharacter.getInventory().appendToAwardsList("remedialSchool");
                            playerCharacter.setPlayerBooleanInfo(18, true);
                            playerCharacter.getSchool().setSchoolName(playerCharacter.getPlayerCity() + " Remedial Middle School");
                            playerCharacter.getSchool().gpaSet(2.0);
                        }
                    }
                }
            }

            //pick a new name for the school, reset GPA, etc. if the school is still an elementary school
            if (playerCharacter.getSchool().getSchoolName().contains("Elementary")){
                gameMenu.displaySeperator(1);
                gameMenu.menuElement("Congratulations!", "", 2);
                gameMenu.menuElement("You graduated from ", playerCharacter.getSchool().getSchoolName(), 2);
                gameMenu.displaySeperator(1);

                String newSchoolName = (dialogModule.getMaleNameWithID(ThreadLocalRandom.current().nextInt(0, 227 + 1)) + " " +
                dialogModule.getLastNameWithID(ThreadLocalRandom.current().nextInt(0, 161 + 1))+
                " Middle School");
                playerCharacter.getSchool().setSchoolName(newSchoolName);
                playerCharacter.getSchool().gpaSet(playerCharacter.getSchool().gpaGet() + 1.0);
            }

            

            //--RELATIONSHIP--
            //this section is about offering a chance to improve the relationship with your friends through a minigame, this section offers a chance to improve your relationships with all of
            //your friends by playing a small micro-game embedded within this module itself. this time around, it's a tech-focused quiz.

            int questionPicked = ThreadLocalRandom.current().nextInt(0, 5);

            String questionOne =  "When did Apple release the iPhone 6s?";
            String q1AnsOne = "1: September 25, 2015"; //correct
            String q1AnsTwo = "2: August 10, 2014";
            String q1AnsThree = "3: January 10, 2015";
            String q1AnsFour = "4: December 13, 2016";

            String questionTwo =  "What year did Windows XP come out?";
            String q2AnsOne = "1: 2001"; //correct
            String q2AnsTwo = "2: 2000";
            String q2AnsThree = "3: 2003";
            String q2AnsFour = "4: 2002";

            String questionThree =  "What is the kernel which runs under Windows 11?";
            String q3AnsOne = "1: BSD";
            String q3AnsTwo = "2: Linux";
            String q3AnsThree = "3: Windows NT"; //correct
            String q3AnsFour = "4: Windows Zulu";

            String questionFour =  "What year did the original iPod release?";
            String q4AnsOne = "1: 2000";
            String q4AnsTwo = "3: 2004";
            String q4AnsThree = "4: 1999";
            String q4AnsFour = "4: 2001"; //correct

            String questionFive =  "What processor company invented the x86-64 processor architecture?";
            String q5AnsOne = "1: Intel";
            String q5AnsTwo = "2: AMD"; //correct
            String q5AnsThree = "3: Cyrix";
            String q5AnsFour = "4: IBM";

            int triviaGameOffered = ThreadLocalRandom.current().nextInt(0, 10);
            Boolean runTriviaGame = false;
            Boolean playerWonTriviaGame = false;
            String triviaInput = "";
            char[] triviaChar;

            if (playerCharacter.getPlayerBooleanInfo(4) && triviaGameOffered <= 5){
                //ask the user if they would like to play the trivia game
                gameMenuHeader.displaySeperator(1);
                gameMenu.menuElement("~~~Friendship Game~~~", "", 2);
                gameMenu.menuElement("Would you like to play a trivia game to improve your friendships?", "", 2);
                gameMenuHeader.displaySeperator(1);

                System.out.print("Play the trivia game?: ");
                String userInput = input.next().trim().toLowerCase();
                char[] inputChar = userInput.toCharArray();

                if (inputChar[0] == 'y' || inputChar [0] == 'Y'){
                     runTriviaGame = true;
                }else{
                    logger.info("##DEBUG## - user chose not to run trivia game.");
                    runTriviaGame = false;
                 }

                 if (runTriviaGame || forceTriviaGame){
                    switch(questionPicked){
                        case 0:
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionOne, "", 2);
                            gameMenu.menuElement(q1AnsOne, "", 2);
                            gameMenu.menuElement(q1AnsTwo, "", 2);
                            gameMenu.menuElement(q1AnsThree, "", 2);
                            gameMenu.menuElement(q1AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '1' || triviaChar [0] == 'A' || triviaChar [0] == 'a'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q1AnsOne, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 1:
                            //question 2
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionTwo, "", 2);
                            gameMenu.menuElement(q2AnsOne, "", 2);
                            gameMenu.menuElement(q2AnsTwo, "", 2);
                            gameMenu.menuElement(q2AnsThree, "", 2);
                            gameMenu.menuElement(q2AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '1' || triviaChar [0] == 'A' || triviaChar [0] == 'a'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q2AnsOne, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 2:
                            //question 3
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionThree, "", 2);
                            gameMenu.menuElement(q3AnsOne, "", 2);
                            gameMenu.menuElement(q3AnsTwo, "", 2);
                            gameMenu.menuElement(q3AnsThree, "", 2);
                            gameMenu.menuElement(q3AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '3' || triviaChar [0] == 'C' || triviaChar [0] == 'C'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q3AnsThree, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 3:
                            //question 4
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionFour, "", 2);
                            gameMenu.menuElement(q4AnsOne, "", 2);
                            gameMenu.menuElement(q4AnsTwo, "", 2);
                            gameMenu.menuElement(q4AnsThree, "", 2);
                            gameMenu.menuElement(q4AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '4' || triviaChar [0] == 'D' || triviaChar [0] == 'd'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q4AnsFour, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        case 4:
                            //question 5
                            gameMenu.displaySeperator(1);
                            gameMenu.menuElement(questionFive, "", 2);
                            gameMenu.menuElement(q5AnsOne, "", 2);
                            gameMenu.menuElement(q5AnsTwo, "", 2);
                            gameMenu.menuElement(q5AnsThree, "", 2);
                            gameMenu.menuElement(q5AnsFour, "", 2);
                            gameMenu.displaySeperator(1);

                            System.out.print("Answer: ");
                            triviaInput = input.next().trim().toLowerCase();
                            triviaChar = triviaInput.toCharArray();

                            if (triviaChar[0] == '2' || triviaChar [0] == 'B' || triviaChar [0] == 'b'){
                                gameMenuHeader.displaySeperator(1);
                                gameMenuHeader.menuElement("Congrats! You got it right!", "", 2);
                                gameMenuHeader.displaySeperator(1);
                                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                                    playerCharacter.getFriendsList().get(n).friendRelationshipImprove(10);
                                }
                            }else{
                                errorMenu.displaySeperator(1);
                                errorMenu.menuElement("That's not correct.", "", 2);
                                errorMenu.menuElement("Correct Answer: ", q5AnsTwo, 2);
                                errorMenu.displaySeperator(1);
                            }
                            break;
                        default:
                            errorMenu.displaySeperator(1);
                            errorMenu.menuElement("###-ERROR-###: ", "switch statement overflow in trivia game (gpl2)", 2);
                            errorMenu.displaySeperator(1);
                            break;
                    }
                }
            }


            //----LOVE----
            //ask the player to answer a single, difficult question to save the relationship with their lover if it is doing poorly. if they fail, they will be dumped
            // String loveSaverInput = "";
            // char[] loveSaverChar = triviaInput.toCharArray();
            // char correctAnswer = '0.01';
            // if (playerCharacter.getPlayerBooleanInfo(10)){
            //     if (playerCharacter.getRomanceList().get(0).getRelationship() < 45){
            //         loveMenu.displaySeperator(1);
            //         loveMenu.menuElement("You arer at risk of breaking up!", "", 2);
            //         loveMenu.menuElement("You must answer the following question to save your relationship!", "", 2);
            //         loveMenu.displaySeperator(1);
            //         loveMenu.menuElement("1.23 divided by 8 times the square root of 234.5 is?", "", 2);
            //         loveSaverInput = input.next().trim().toLowerCase();
            //         loveSaverChar = loveSaverInput.toCharArray();

            //         if (loveSaverChar)){

            //         }

            //     }
            // }
            


            //ask the player at random to pick from a couple options for a gift to give to their lover, if they lose the RNG check then their lover will not like it and they will lose some
            //relationship value.
            int checkToRunDateRNG = ThreadLocalRandom.current().nextInt(0, 1000);
            int correctAnswer1 = ThreadLocalRandom.current().nextInt(1, 5);
            int correctAnswer2 = ThreadLocalRandom.current().nextInt(1, 5);
            int correctAnswer3 = ThreadLocalRandom.current().nextInt(1, 5);
            int correctAnswer4 = ThreadLocalRandom.current().nextInt(1, 5);
            String correctAnswer1Str = "" + correctAnswer1;
            String correctAnswer2Str = "" + correctAnswer2;
            String correctAnswer3Str = "" + correctAnswer3;
            String correctAnswer4Str = "" + correctAnswer4;
            String dateInput = "";
            int numAnsweredCorrectly = 0;
            String[] questionBank1 = {"Pick a flower: ", "1: Rose", "2: Daisy", "3: Azalea", "4: Sunflower"};
            String[] questionBank2 = {"Pick a gift: ", "1: Box of Chocolate", "2: Bottle of Soda", "3: Old Sock", "4: Super Mary Brothers Plushie"};
            String[] questionBank3 = {"Pick a location: ", "1: Cheese Factory", "2: McRonald's", "3: InFront Steakhouse", "4: Wall-Mart"};
            String[] questionBank4 = {"Pick a season: ", "1: Spring", "2: Summer", "3: Winter", "4: Fall"};
            if (checkToRunDateRNG < 350 && playerCharacter.getPlayerBooleanInfo(10) || forcePlayerFriend){
                loveMenu.displaySeperator(1);
                loveMenu.menuElement("Date Night!", "", 2);
                loveMenu.menuElement("Take your special someone out for a date, but pick wisely...", "", 2);
                loveMenu.displaySeperator(1);

                logger.info("##DEBUG## - correct answer: " + Arrays.asList(questionBank1).get(correctAnswer1));
                System.out.println(questionBank1[1]);
                System.out.println(questionBank1[2]);
                System.out.println(questionBank1[3]);
                System.out.println(questionBank1[4]);
                System.out.print(questionBank1[0]);
                dateInput = input.next().trim().toLowerCase();
                input.nextLine();
                if (dateInput.charAt(0) == correctAnswer1Str.charAt(0)){
                    dateInput = "";
                    numAnsweredCorrectly++;
                }

                loveMenu.displaySeperator(1);

                logger.info("##DEBUG## - correct answer: " + Arrays.asList(questionBank2).get(correctAnswer2));
                System.out.println(questionBank2[1]);
                System.out.println(questionBank2[2]);
                System.out.println(questionBank2[3]);
                System.out.println(questionBank2[4]);
                System.out.print(questionBank2[0]);
                dateInput = input.next().trim().toLowerCase();
                input.nextLine();
                if (dateInput.charAt(0) == correctAnswer2Str.charAt(0)){
                    dateInput = "";
                    numAnsweredCorrectly++;
                }

                loveMenu.displaySeperator(1);

                logger.info("##DEBUG## - correct answer: " + Arrays.asList(questionBank3).get(correctAnswer3));
                System.out.println(questionBank3[1]);
                System.out.println(questionBank3[2]);
                System.out.println(questionBank3[3]);
                System.out.println(questionBank3[4]);
                System.out.print(questionBank3[0]);
                dateInput = input.next().trim().toLowerCase();
                input.nextLine();
                if (dateInput.charAt(0) == correctAnswer3Str.charAt(0)){
                    dateInput = "";
                    numAnsweredCorrectly++;
                }

                loveMenu.displaySeperator(1);

                logger.info("##DEBUG## - correct answer: " + Arrays.asList(questionBank4).get(correctAnswer4));
                System.out.println(questionBank4[1]);
                System.out.println(questionBank4[2]);
                System.out.println(questionBank4[3]);
                System.out.println(questionBank4[4]);
                System.out.print(questionBank4[0]);
                dateInput = input.next().trim().toLowerCase();
                input.nextLine();
                if (dateInput.charAt(0) == correctAnswer4Str.charAt(0)){
                    dateInput = "";
                    numAnsweredCorrectly++;
                }

                if (isDebug && !playerCharacter.getPlayerBooleanInfo(10)){
                    List<MiniLifeFriend> debugRomanceList = new ArrayList<MiniLifeFriend>();
                    debugRomanceList.add(playerCharacter.getFriendsList().get(0));
                    playerCharacter.setRomanceList(debugRomanceList);
                }

                logger.info("##DEBUG## - numAnsweredCorrectly - " + numAnsweredCorrectly);

                switch(numAnsweredCorrectly){
                    case 0:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You Failed :(", "", 2);
                        loveMenu.menuElement("Your date hated all of your choices!", "", 2);
                        loveMenu.menuElement("You lost -10 relationship points", "", 2);
                        loveMenu.displaySeperator(1);
                        playerCharacter.getRomanceList().get(0).friendRelationshipDecline(10);
                        playerCharacter.getInventory().appendToAwardsList("badDate");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        break;
                    case 1:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You did horribly!", "", 2);
                        loveMenu.menuElement("Your date hated all but one of your choices!", "", 2);
                        loveMenu.menuElement("You lost -5 relationship points", "", 2);
                        loveMenu.displaySeperator(1);
                        playerCharacter.getRomanceList().get(0).friendRelationshipDecline(5);
                        break;
                    case 2:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You did ok.", "", 2);
                        loveMenu.menuElement("Your date was mildly entertained.", "", 2);
                        loveMenu.menuElement("You did not gain any relationship points.", "", 2);
                        loveMenu.displaySeperator(1);
                        break;
                    case 3:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You did good!", "", 2);
                        loveMenu.menuElement("Your date had a good time!", "", 2);
                        loveMenu.menuElement("You gained +5 relationship points!", "", 2);
                        loveMenu.displaySeperator(1);
                        playerCharacter.getRomanceList().get(0).friendRelationshipImprove(5);
                        break;
                    case 4:
                        loveMenu.displaySeperator(1);
                        loveMenu.menuElement("You did amazingly!", "", 2);
                        loveMenu.menuElement("Your date had an amazing time!", "", 2);
                        loveMenu.menuElement("You gained +10 relationship points!", "", 2);
                        loveMenu.displaySeperator(1);
                        playerCharacter.getRomanceList().get(0).friendRelationshipImprove(10);
                        playerCharacter.getInventory().appendToAwardsList("greatDate");
                        playerCharacter.setPlayerBooleanInfo(18, true);
                        break;
                }
                
            }



            //---AGE UP---

            //assign the player a personality type at random, if they don't already have one.
            if (!playerCharacter.getPlayerBooleanInfo(13)){
                int randomPersonalityType = ThreadLocalRandom.current().nextInt(1, 9);
                playerCharacter.setPersonalityType(randomPersonalityType);
                gameMenu.displaySeperator(2);
                gameMenu.menuElement("You picked up the ", (playerCharacter.getPlayerPersonality() + " personality type!"), 2);
                gameMenu.displaySeperator(2);
            }

            //if the character has lost friends before, broken up, or fails an an rng check, make them have the "Depressed" personality type
            if (playerCharacter.getInventory().getAwardsList().contains("lostFriend") || playerCharacter.getInventory().getAwardsList().contains("brokeUp")){
                playerCharacter.setPersonalityType(7);
                playerCharacter.setPlayerBooleanInfo(14, true);
                playerCharacter.getInventory().appendToAwardsList("wasDepressed");
                playerCharacter.setPlayerBooleanInfo(18, true);
            }
            

            //run advanceYear functions in the various modules
            playerCharacter.advanceYear(); //age up player
            playerCharacter.getSchool().advanceYear(); //advance school year

            //lower friendship by 5 on odd years
            for (int n=0;n < playerCharacter.getFriendsList().size();n++){
                if ((playerCharacter.getAge() & 1) == 0){
                    playerCharacter.getFriendsList().get(n).friendRelationshipDecline(5);
                }
            }

            //lower relationship by 1 on odd years
            if (playerCharacter.getPlayerBooleanInfo(10)){
                for (int n=0;n < playerCharacter.getRomanceList().size();n++){
                    if ((playerCharacter.getAge() & 1) == 0){
                        playerCharacter.getRomanceList().get(n).friendRelationshipDecline(1);
                    }
                }
            }

            //lower GPA by 0.5 on even years
            if (!((playerCharacter.getAge() & 1) == 0)){
                if (!minigame2WasWon && playerCharacter.getSchool().gpaGet() > 1.0){
                     playerCharacter.getSchool().gpaDown();
                }
              }

            //age up player's friends, if they exist
            if (playerCharacter.getPlayerBooleanInfo(4)){
                for (int n = 0;n < playerCharacter.getFriendsList().size();n++){
                    playerCharacter.getFriendsList().get(n).advanceYear();
                }
            }

            //age up player's romantic interest, if they exist
            if (playerCharacter.getPlayerBooleanInfo(10)){
                playerCharacter.getRomanceList().get(0).advanceYear();
            }

            gameMenu.displaySeperator(1);
            gameMenu.menuElement("The year has been advanced.", "", 2);
            gameMenu.menuElement("Years played: ", playerCharacter.getYearsPlayed(), 2);
            gameMenu.menuElement("Current school year: ", playerCharacter.getSchool().gradeGet(), 2);
            gameMenu.displaySeperator(1);
    }

}
