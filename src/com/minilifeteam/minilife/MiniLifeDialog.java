package com.minilifeteam.minilife;

import java.util.Arrays;
import java.util.List;

public class MiniLifeDialog {

    public String getDialogWithID(int dialogID){
        //this sets up an array containing all the re-usable dialogs, and then returns the dialog with the provided ID number. see Quick Reference Handbook for ID numbers.
        String[] dialogArray = {
        "Welcome to MiniLife", "Save Data Created", "Save Data Loaded", "Error loading Save Data. Try again?", "You have no money!", "You have no assets!", 
        "You have no friends!", "You have been caught shoplifting! Pay your fine, or go to jail!", "You have failed a class! Pay $50 to repeat the class, or drop out?", "Congratulations! You have graduated from college!",
        "Congratulations! You got a promotion!", "Congratulations! you have reached retirement age! You cashed out your pension and received the following: ", "You have been caught using your work computer to watch TV, and have been fired.",
        "Is love in the air?", "You found a wallet on the ground! it contained $50. Keep it?", " has become ill! Would you like to pay $50 to see the doctor?", "Oh no! A rat chewed through your ethernet cable, you must pay $50 to get it fixed.",
        " has passed away.", "Your grandmother has passed away. You got the following inheritance: ", "Welcome to the Doctor's Office. Procedures Available: ", "General Exam - $50", "In-Depth Exam - $100", 
        "Super In-Depth Exam - $500", "The doctor has seen you. You are in great health!", "The doctor has seen you. You have cancer. Pay $500 you like to begin treatment?", "The doctor has seen you. You have the common cold. Pay $25 for cold medicine?",
        "The doctor has seen you. A lifetime of smoking has damaged your lungs. Pay $1,500 to begin treatment?", "Would you like to ", "You started smoking. $200 will be removed from your account every year until you quit.",
        " has fallen in love with ", " has become friends with ", "Are you sure you want to quit your job?", "Are you sure you want to ", "You were caught robbing a bank, and got sent to jail!", "You were caught stealing a car, and will go to jail!",
        "You got away with robbing a bank! You got $5,000.", "You got away with stealing a car! You now have the following car: ", "You quit smoking.", "Congratulations! You got engaged!", "Congratulations! You got married!",
        "Congratulations! You are having a child!", "Game Over! You had a heart attack. The paramedics tried to save you, but it was too late.", "Game Over! You passed away from cancer.", "Game Over! You passed away from lung disease", "Game Over! You were killed in a freak workplace accident.",
        "Game Over! You passed away of natural causes.", "Game Over! You were walking to the hot dog stand, and got struck by a foul ball.", "Game Over! you went to the bank to withdraw some money, and got caught in a bank robbery!",
        "Game Over! You tried to rob the bank, and got shot by a security guard!", "Game Over! You tried to steal a car, and crashed after a police chase!", "Would you like to purchase a weapon for your bank robbery?"

        };
        List<String> dialogList = Arrays.asList(dialogArray);

        return dialogList.get(dialogID);
    }

        public String getMaleNameWithID(int nameID){    
            
        //this sets up an array containing all the re-usable male names, and then returns the name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.
        String[] nameArrayMale = {"Barry", "Michael", "James", "Jimmy", "Eric", "Derek", "Zander", "Dale", "Doug", "Steven", "Steve", "John", "Josiah", "Jeremy", "Jeremiah", "Darnell",
        "Darryl", "Darrel", "Sven", "Tyler", "Tyrone", "Ezra", "Connor", "Conner", "Tariq", "Gary", "Garry", "Garret", "Dustin", "Thomas", "Anthony", "Tony", "Luigi", "Mario",
        "Paul", "Peter", "Joel", "Vincent", "Jerma", "Harrison", "Harry", "Luke", "Lucas", "Lester", "Marcus", "Mark", "Malik", "Martin", "Emmett", "Dallas", "Shintaro", "Shouto", 
        "Xavier", "Garrison", "Elijah", "Liam", "Noah", "Oliver", "Theodore", "Teddy", "Henry", "Matthew", "Matt", "Benjamin", "Sebastian", "Samuel", "Hudson", "Leo", "Daniel", "Danny",
        "Ethan", "Wyatt", "Alex", "Alexander", "Benson", "Mordicai", "Wesley", "Logan", "Weston", "Isaiah", "Grayson", "Aiden", "Nathaniel", "Nolan", "Christopher", "Chris", "Lincoln", "Adrian",
        "Andrew", "Axel", "Aaron", "Ian", "Arthur", "Miles", "Myles", "Colton", "Amir", "Christian", "Micah", "Nikolai", "Caelum", "Lorenzo", "Louis", "Shinji", "Calvin", "Jordan", 
        "Kayden", "Milo", "Charlie", "Charles", "Carlos", "Carlito", "Diego", "Juan", "Ivan", "Jayce", "Antonio", "Matias", "Felix", "Felipe", "Emmanuel", "Tucker", "Rhett", "Link", 
        "Chase", "Alejandro", "Rafael", "Kevin", "Simon", "Patrick", "Muhammad", "Mahmoud", "Harvey", "Cade", "Xander", "Kayson", "Marcus", "Otto", "Lennox", "Tobias", "Kenneth", "Kennedy",
        "Romeo", "Clayton", "Clay", "Archie", "Archer", "Romeo", "Luciano", "Mateo", "Dante", "Tristan", "Mattias", "Cody", "Blake", "Jorge", "Raymond", "Richard", "Rick", "Remmy", 
        "Bradley", "Braydon", "Cayden", "Augustus", "Eduardo", "Francis", "Solomon", "Frederick", "Fred", "Freddy", "Sergio", "Chance", "Pedro", "Esteban", "Jacob", "Reuben", "Pierce", 
        "Jamari", "Jamal", "Camilo", "Vincente", "Jamir", "Keegan", "Carmelo", "Murphy", "Salvador", "Santos", "Wayne", "William", "Will", "Willhelm", "Albert", "Haruki", "Haruto", "Kouji",
        "Masaki", "Kousuke", "Hiroki", "Yutaka", "Akihiro", "Masato", "Takashi", "Mitsuki", "Seiji", "Yoshitaka", "Yoshi", "Satoshi", "Hayato", "Yuusuke", "Keiji", "Míngzé", "Xi", "Wěi", 
        "Míng", "Háo", "Xióng", "Yī Míng", "Hǎi Tāo", "Valentino", "Valentine", "Bruno", "Bruce", "André", "Andreas", "Claude", "Jacques", "Maurice", "Kieran"
        };
        List<String> nameListMale = Arrays.asList(nameArrayMale);
        
        
        return "";
    }
        //this sets up an array containing all the re-usable female names, and then returns the name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.
        public String getFemaleNameWithID(int nameID){        
        String[] nameArrayFemale = {"Melissa", "Melonie", "Mallorie", "Melinda", "Linda", "Lacey", "Brooke", "Louisa", "Celeste", "Kaitlyn", "Mary-Ann", "Sandra", "Sandy", "Christine",
        "Olivia", "Charlotte", "Emma", "Sophia", "Amelia", "Sophia", "Mia", "Isabella", "Evelyn", "Elizabeth", "Eliana", "Sofia", "Sophie", "Aurora", "Harper", "Lily", "Camila", 
        "Penelope", "Chloe", "Charlie", "Lucy", "Anna", "Ava", "Eleanor", "Hazel", "Madison", "Kayla", "Zoey", "Gianna", "Scarlett", "Ella", "Delaney", "Abigail", "Abbey", "Naomi", "Sadie",
        "Delilah", "Emilia", "Emily", "Stella", "Ivy", "Mia", "Maya", "Ruby", "Alice", "Allison", "Leilani", "Genesis", "Iris", "Adeline", "Emery", "Victoria", "Claire", "Madelyn", "Daisy",
        "Melody", "Caroline", "Georgia", "Natalie", "Maria", "Autumn", "Audrey", "Marie", "Aaliyah", "Cora", "Cora-Lynn", "Rose", "Gabriella", "Gabby", "Serenity", "Eva", "Savannah", "Bella",
        "Phoebe", "Samantha", "Natalia", "Sage", "Valeria", "Valerie", "Isabelle", "Ruth", "Nevaeh", "Alani", "Rosalie", "Freya", "Daphne", "Haven", "Mabel", "Jasmine", "Kylie", "Bailey",
        "Dahlia", "Ada", "Jane", "Presley", "Brianna", "Elise", "Grace", "Gracie", "Mackenzie", "Andrea", "Alexandra", "Camille", "Diana", "Juliana", "Kaylee", "Talia", "Aspen", "Khloe",
        "Luciana", "Azalea", "Lena", "Shanique", "Meadow", "Antonella", "Maggie", "Dakota", "Sylvia", "Francesca", "Morgan", "Zelda", "Catherine", "Peach", "Rosalina", "Miku", "Rin", "Yotsuba",
        "Itsuki", "Bocchi", "Hitori", "Heidi", "Paige", "Angelina", "Rebecca", "Miriam", "Marnie", "Violet", "Skyla", "Skyler", "Misty", "Erika", "Sabrina", "Roxanne", "Candice", "Roxie", 
        "Olympia", "Nessa", "Opal", "Iono", "Yuzuki", "Akari", "Ayaka", "Ichika", "Yuki", "Himari", "Saki", "Honoka", "Kanami", "Minori", "Shiori", "Mio", "Miyuki", "Kaori", "Luna", "Catalina",
        "Xuě", "Yíng", "Yǎ", "Lěi", "Mèng Yáo", "Aisha", "Amara", "Amina", "Layla", "Zara", "Khadija", "Saoirse", "Kathleen", "Bridget", "Riley"
        };
        List<String> nameListFemale = Arrays.asList(nameArrayFemale);
        
        
        return "";
    }
        public String getNBNameWithID(int nameID){       
            
        //this sets up an array containing all the re-usable non-binary names, and then returns the name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.
        String[] nameArrayNB = {""};
        List<String> nameListNB = Arrays.asList(nameArrayNB);
        
        
        return "";
    }

        public String getLastNameWithID(int nameID){   
        //this sets up an array containing all the re-usable last names, and then returns the name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.
        String[] nameArrayLast = {""};
        List<String> nameListLast = Arrays.asList(nameArrayLast);
        
        
        return "";
    }


        public String getJobNameWithID(int jobID){   
        //this sets up an array containing all the re-usable job titles, and then returns the job with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.     
        String[] jobArray = {""};
        List<String> jobList = Arrays.asList(jobArray);
        
        
        return "";
    }

        public String getHouseWithID(int houseID){    
        //this sets up an array containing all the re-usable house addresses, and then returns the address with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.    
        String[] houseArray = {""};
        List<String> houseList = Arrays.asList(houseArray);
        
        
        return "";
    }

        public String getCarsWithID(int carID){       
        //this sets up an array containing all the re-usable car make/models, and then returns the make/model with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers. 
        String[] carArray = {""};
        List<String> carList = Arrays.asList(carArray);
        
        
        return "";
    }    

        public String getActionNameWithID(int actionID){      
        //this sets up an array containing all the re-usable action names, and then returns the action with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.  
        String[] actionsArray = {""};
        List<String> actionsList = Arrays.asList(actionsArray);
        
        
        return "";
    }

        public int getIndexInArrays(int arrayID, String searchTerm){  
            
        //this allows you to search all of the previous arrays, by providing an arrayID (in the Quick Reference Handbook), and a searchTerm (The actual term to be searched), and it returns the ID number of the entered dialog (It's position within the array)
        
        String[] dialogArray = {
        "Welcome to MiniLife", "Save Data Created", "Save Data Loaded", "Error loading Save Data. Try again?", "You have no money!", "You have no assets!", 
        "You have no friends!", "You have been caught shoplifting! Pay your fine, or go to jail!", "You have failed a class! Pay $50 to repeat the class, or drop out?", "Congratulations! You have graduated from college!",
        "Congratulations! You got a promotion!", "Congratulations! you have reached retirement age! You cashed out your pension and received the following: ", "You have been caught using your work computer to watch TV, and have been fired.",
        "Is love in the air?", "You found a wallet on the ground! it contained $50. Keep it?", " has become ill! Would you like to pay $50 to see the doctor?", "Oh no! A rat chewed through your ethernet cable, you must pay $50 to get it fixed.",
        " has passed away.", "Your grandmother has passed away. You got the following inheritance: ", "Welcome to the Doctor's Office. Procedures Available: ", "General Exam - $50", "In-Depth Exam - $100", 
        "Super In-Depth Exam - $500", "The doctor has seen you. You are in great health!", "The doctor has seen you. You have cancer. Pay $500 you like to begin treatment?", "The doctor has seen you. You have the common cold. Pay $25 for cold medicine?",
        "The doctor has seen you. A lifetime of smoking has damaged your lungs. Pay $1,500 to begin treatment?", "Would you like to ", "You started smoking. $200 will be removed from your account every year until you quit.",
        " has fallen in love with ", " has become friends with ", "Are you sure you want to quit your job?", "Are you sure you want to ", "You were caught robbing a bank, and got sent to jail!", "You were caught stealing a car, and will go to jail!",
        "You got away with robbing a bank! You got $5,000.", "You got away with stealing a car! You now have the following car: ", "You quit smoking.", "Congratulations! You got engaged!", "Congratulations! You got married!",
        "Congratulations! You are having a child!", "Game Over! You had a heart attack. The paramedics tried to save you, but it was too late.", "Game Over! You passed away from cancer.", "Game Over! You passed away from lung disease", "Game Over! You were killed in a freak workplace accident.",
        "Game Over! You passed away of natural causes.", "Game Over! You were walking to the hot dog stand, and got struck by a foul ball.", "Game Over! you went to the bank to withdraw some money, and got caught in a bank robbery!",
        "Game Over! You tried to rob the bank, and got shot by a security guard!", "Game Over! You tried to steal a car, and crashed after a police chase!", "Would you like to purchase a weapon for your bank robbery?"

        };
        List<String> dialogList = Arrays.asList(dialogArray);

        
        String[] nameArrayMale = {"Barry", "Michael", "James", "Jimmy", "Eric", "Derek", "Zander", "Dale", "Doug", "Steven", "Steve", "John", "Josiah", "Jeremy", "Jeremiah", "Darnell",
        "Darryl", "Darrel", "Sven", "Tyler", "Tyrone", "Ezra", "Connor", "Conner", "Tariq", "Gary", "Garry", "Garret", "Dustin", "Thomas", "Anthony", "Tony", "Luigi", "Mario",
        "Paul", "Peter", "Joel", "Vincent", "Jerma", "Harrison", "Harry", "Luke", "Lucas", "Lester", "Marcus", "Mark", "Malik", "Martin", "Emmett", "Dallas", "Shintaro", "Shouto", 
        "Xavier", "Garrison", "Elijah", "Liam", "Noah", "Oliver", "Theodore", "Teddy", "Henry", "Matthew", "Matt", "Benjamin", "Sebastian", "Samuel", "Hudson", "Leo", "Daniel", "Danny",
        "Ethan", "Wyatt", "Alex", "Alexander", "Benson", "Mordicai", "Wesley", "Logan", "Weston", "Isaiah", "Grayson", "Aiden", "Nathaniel", "Nolan", "Christopher", "Chris", "Lincoln", "Adrian",
        "Andrew", "Axel", "Aaron", "Ian", "Arthur", "Miles", "Myles", "Colton", "Amir", "Christian", "Micah", "Nikolai", "Caelum", "Lorenzo", "Louis", "Shinji", "Calvin", "Jordan", 
        "Kayden", "Milo", "Charlie", "Charles", "Carlos", "Carlito", "Diego", "Juan", "Ivan", "Jayce", "Antonio", "Matias", "Felix", "Felipe", "Emmanuel", "Tucker", "Rhett", "Link", 
        "Chase", "Alejandro", "Rafael", "Kevin", "Simon", "Patrick", "Muhammad", "Mahmoud", "Harvey", "Cade", "Xander", "Kayson", "Marcus", "Otto", "Lennox", "Tobias", "Kenneth", "Kennedy",
        "Romeo", "Clayton", "Clay", "Archie", "Archer", "Romeo", "Luciano", "Mateo", "Dante", "Tristan", "Mattias", "Cody", "Blake", "Jorge", "Raymond", "Richard", "Rick", "Remmy", 
        "Bradley", "Braydon", "Cayden", "Augustus", "Eduardo", "Francis", "Solomon", "Frederick", "Fred", "Freddy", "Sergio", "Chance", "Pedro", "Esteban", "Jacob", "Reuben", "Pierce", 
        "Jamari", "Jamal", "Camilo", "Vincente", "Jamir", "Keegan", "Carmelo", "Murphy", "Salvador", "Santos", "Wayne", "William", "Will", "Willhelm", "Albert", "Haruki", "Haruto", "Kouji",
        "Masaki", "Kousuke", "Hiroki", "Yutaka", "Akihiro", "Masato", "Takashi", "Mitsuki", "Seiji", "Yoshitaka", "Yoshi", "Satoshi", "Hayato", "Yuusuke", "Keiji", "Míngzé", "Xi", "Wěi", 
        "Míng", "Háo", "Xióng", "Yī Míng", "Hǎi Tāo", "Valentino", "Valentine", "Bruno", "Bruce", "André", "Andreas", "Claude", "Jacques", "Maurice"
        };
        List<String> nameListMale = Arrays.asList(nameArrayMale);

        String[] nameArrayFemale = {"Melissa", "Melonie", "Mallorie", "Melinda", "Linda", "Lacey", "Brooke", "Louisa", "Celeste", "Kaitlyn", "Mary-Ann", "Sandra", "Sandy", "Christine",
        "Olivia", "Charlotte", "Emma", "Sophia", "Amelia", "Sophia", "Mia", "Isabella", "Evelyn", "Elizabeth", "Eliana", "Sofia", "Sophie", "Aurora", "Harper", "Lily", "Camila", 
        "Penelope", "Chloe", "Charlie", "Lucy", "Anna", "Ava", "Eleanor", "Hazel", "Madison", "Kayla", "Zoey", "Gianna", "Scarlett", "Ella", "Delaney", "Abigail", "Abbey", "Naomi", "Sadie",
        "Delilah", "Emilia", "Emily", "Stella", "Ivy", "Mia", "Maya", "Ruby", "Alice", "Allison", "Leilani", "Genesis", "Iris", "Adeline", "Emery", "Victoria", "Claire", "Madelyn", "Daisy",
        "Melody", "Caroline", "Georgia", "Natalie", "Maria", "Autumn", "Audrey", "Marie", "Aaliyah", "Cora", "Cora-Lynn", "Rose", "Gabriella", "Gabby", "Serenity", "Eva", "Savannah", "Bella",
        "Phoebe", "Samantha", "Natalia", "Sage", "Valeria", "Valerie", "Isabelle", "Ruth", "Nevaeh", "Alani", "Rosalie", "Freya", "Daphne", "Haven", "Mabel", "Jasmine", "Kylie", "Bailey",
        "Dahlia", "Ada", "Jane", "Presley", "Brianna", "Elise", "Grace", "Gracie", "Mackenzie", "Andrea", "Alexandra", "Camille", "Diana", "Juliana", "Kaylee", "Talia", "Aspen", "Khloe",
        "Luciana", "Azalea", "Lena", "Shanique", "Meadow", "Antonella", "Maggie", "Dakota", "Sylvia", "Francesca", "Morgan", "Zelda", "Catherine", "Peach", "Rosalina", "Miku", "Rin", "Yotsuba",
        "Itsuki", "Bocchi", "Hitori", "Heidi", "Paige", "Angelina", "Rebecca", "Miriam", "Marnie", "Violet", "Skyla", "Skyler", "Misty", "Erika", "Sabrina", "Roxanne", "Candice", "Roxie", 
        "Olympia", "Nessa", "Opal", "Iono", "Yuzuki", "Akari", "Ayaka", "Ichika", "Yuki", "Himari", "Saki", "Honoka", "Kanami", "Minori", "Shiori", "Mio", "Miyuki", "Kaori", "Luna", "Catalina",
        "Xuě", "Yíng", "Yǎ", "Lěi", "Mèng Yáo", "Aisha", "Amara", "Amina", "Layla", "Zara", "Khadija", "Saoirse", "Kathleen", "Bridget", "Riley"
        };
        List<String> nameListFemale = Arrays.asList(nameArrayFemale);



        if (arrayID == 1){
            return dialogList.indexOf(searchTerm);
        }
        else if (arrayID == 2){
            return nameListMale.indexOf(searchTerm);
        }
        else if (arrayID == 3){
            return nameListFemale.indexOf(searchTerm);
        }
        else {
            return 0;
        }
    }
}

