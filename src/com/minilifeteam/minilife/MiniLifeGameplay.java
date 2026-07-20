//MiniLife gameplay program file
//version 0.1-InDev3 (Jul 20, 2026)
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//this project uses some code licensed under the Apache License version 2.0. This code includes the Apache Commons Lang library. This license is compatible with GPLv3.
//No Artificial Intelligence tools were used in the creation of this source code file.
//Primary Developer(s) on this file: Celeste Manguso
//Secondary Developer(s) on this file: 

package com.minilifeteam.minilife;

//import scanner, stuff for debugging
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;
import java.util.InputMismatchException;
import java.lang.UnsupportedOperationException;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.ArrayList;

public class MiniLifeGameplay {

    private MiniLifePlayer playerCharacter;
    private MiniLifeDialog dialogModule;
    private Boolean isDebug;
    private List<Boolean> debugFlagsLocal;
    private Scanner input;
    private Logger logger;

    public void initGameModule(MiniLifePlayer playerChar, MiniLifeDialog dialog, Scanner input, Logger logger, Boolean isDebug, List<Boolean> DebugFlags){
        this.playerCharacter = playerChar;
        this.dialogModule = dialog;
        this.input = input;
        this.logger = logger;
        this.isDebug = isDebug;
        this.debugFlagsLocal = DebugFlags;
    }

    //debug flags stuff


    //code to call a certain game

    public void callGameWithID(int gameID, Boolean resetGame){
        switch(gameID){
            case 1:
                gameplayLoop1(resetGame);
                break;
            case 2:
                gameplayLoop2(resetGame);
                break;
            case 3:
                gameplayLoop3(resetGame);
                break;
            case 4:
                gameplayLoop4(resetGame);
                break;
        }
    }


    /**
     * Gameplay Loop 1 - Young Childhood
     * Ages: 0-5
     * character is a kid, so the possibilities are limited. the character may be asked to do a minigame to recieve an award (added to the character inv awards list),
     * the player can also possibly get a bit of pocket money from their family, they may also be gifted an heirloom during this time.
     * there is a very small chance that the character's family wins the lottery, and the character is gifted a ton of money.
     * the character may lose some health due to an injury, and there is a very small chance that the character develops cancer during this time.
     */
    private void gameplayLoop1(Boolean doReset){
        if (doReset){
            gameplayLoop1_reset();
        }
    }

    private void gameplayLoop1_reset(){

    }

    /**
     * Gameplay Loop 2 - Elementary School
     * Ages: 6-11
     * the character is older now, and is in elementary school. functions to age up the character within school will now be added (the grade will be advanced).
     * the character is now going to potentially face minigames for school, may make new friends (which will be generated in this loop, if the option so arises),
     * the character may gain an heirloom, their family may win the lottery, they may find a wallet on the ground, 
     * they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     */

    private void gameplayLoop2(Boolean doReset){
        if (doReset){
            gameplayLoop2_reset();
        }
    }

    private void gameplayLoop2_reset(){
    
    }
    
    /**
     * Gameplay Loop 3 - Middle School
     * Ages: 12-14
     * the character is older now, and is in middle school. functions to age up the character within school will continue (the grade will be advanced).
     * the character is going to potentially face minigames for school, may make new friends (which will be generated in this loop, if the option so arises),
     * the character may gain an heirloom, their family may win the lottery, they may find a wallet on the ground, 
     * they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     * the character may begin to form romantic feelings for a friend, but won't be able to advance those feelings much yet.
     * the character will recieve a personality type during this block. this will change in the next block, but then it will be set in stone. some things are effected by personality type.
     * the player may get cancer in this era, if they are particularly unlucky. they may lose some health due to an injury or illness. (and may lose some school performance if these things happen)
     * the player may choose to make the character petty shoplift, which can result in arrest, or the character taking on the "delinquent" or "criminal" personality types, 
     * greatly increasing the odds of injury, arrest, and criminal... opportunities in the future.
     */

    private void gameplayLoop3(Boolean doReset){
        if (doReset){
            gameplayLoop3_reset();
        }
    }

    private void gameplayLoop3_reset(){
    
    }

    /**
     * Gameplay Loop 4 - High Schooler
     * Ages: 15 - 18
     * the character is older now, and is in high school. functions to age up the character within school will continue (the grade will be advanced).
     * 
     * the character is going to potentially face minigames for school, may make new friends (which will be generated in this loop, if the option so arises),
     * 
     * the character may gain an heirloom, their family may win the lottery, they may find a wallet on the ground, 
     * 
     * they may face penalties due to slacking (if the player loses the minigame, or doesn't play it at all). this will force a minigame to save the character's school career (exitable turned off)
     * 
     * the character may begin to form romantic feelings for a friend, and can now make some more advancement (though marriage remains locked)
     * 
     * the character will recieve a new personality type during this block. it will be locked in (unless a new personality type is forced by an event). more things are affected by personality type.
     * 
     * the player may get cancer in this era, if they are particularly unlucky. they may lose some health due to an injury or illness. (and may lose some school performance if these things happen)
     * 
     * the player may choose to make the character petty shoplift, which can result in arrest, or the character taking on the "delinquent" or "criminal" personality types, 
     * greatly increasing the odds of injury, arrest, and criminal... opportunities in the future.
     * the player may now also choose to go full delinquent. they may make the character try and steal a car, or rob a house. these crimes can get the character in big trouble, but can be lucrative...
     * 
     * the character can drive now, and if they are lucky they might be gifted a car by their family. if they previously won the lottery (or do now), they WILL be given a luxury car for free.
     */

    private void gameplayLoop4(Boolean doReset){
        if (doReset){
            gameplayLoop4_reset();
        }
    }

    private void gameplayLoop4_reset(){
    
    }



}
