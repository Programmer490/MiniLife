//MiniLife main program file
//version 0.0_inDev1 (Jun 23, 2026)
//this file is licensed under the GNU GPL v3 license. see LICENSE file for more information.
//this file created by Celeste Manguso


//define package
//package com.minilifeteam.minilife;

//import scanner, stuff for debugging
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;


public class MiniLifeMain {
    //create debug logger
    private static final Logger logger = Logger.getLogger(MiniLifeMain.class.getName());

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

        //introduce the program
        System.out.println("MiniLife Version 0.0_InDev1");
        System.out.println("By: The MiniLife Team");
        System.out.println("---------------------------------");
        Date currentDate = new Date();
        logger.info("##DEBUG## - Debug Logging Enabled. Current date and time is " + currentDate);
        }
}
