//MiniLife Dialog Module
//Version 1.0-InDev1 
//Primary Developer(s) on this file: Celeste Manguso
//Secondary Developer(s) on this file: 
//This code licensed under the GNU GPL Version 3.0 license. See LICENSE file for more information.
//No Artificial Intelligence tools were used in the creation of this source code file.


package com.minilifeteam.minilife;

import java.util.Arrays;
import java.util.List;

public class MiniLifeDialog {

    //initialize variables/create all the arrays
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
        "Game Over! You tried to rob the bank, and got shot by a security guard!", "Game Over! You tried to steal a car, and crashed after a police chase!", "Would you like to purchase a weapon for your bank robbery?",
        "Congratulations! You successfully graduated high school. Would you like to attend one of the following colleges?", " Community College", "University of "

        };

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

        String[] nameArrayNB = {"Kai", "Wynn", "Artemis", "Remy", "Quinn", "Sawyer", "Tree", "Sage", "Robin", "Wren", "Merritt", "Avery", "Ari", "August", "Armani", "Bay", "Clay", 
        "Briar", "Cedar", "Charlie", "Carter", "Cleo", "Cloud", "Echo", "Eden", "Ember", "Ellery", "River", "Kamari", "Reign", "Lennox", "Dakota", "Palmer", "Justice", "Onyx", "Journey",
        "Sunny", "Everest", "Campbell", "Scout", "Rio", "Shea", "Hollis", "Montana", "Ripley", "Brighton", "Kit", "Jupiter", "Indiana", "Kobi", "Indigo", "Cypress", "Henley", "Kingsley",
        "Halston", "Holland", "Leighton", "Majesty", "Wynn", "Huntley", "Dominique", "Jaylin", "Kari", "Genesis", "Koda", "Kitt", "Kiernan", "Porter", "Skyler", "Xen", "Quinn", "Sasha", 
        "Sora", "Daelyn", "Maeson", "Prestyn", "Hart", "Koi", "Mars"

        };

        String[] nameArrayLast = {"Smith", "Nakamura", "Sanders", "O'Brian", "McDonald", "Chalamet", "Mongoose", "Montclare", "Barrymore", "Hylia", "Petersen", "Manguso", "Mancuso", "Munger",
        "Blanchard", "Paulson", "O'Conner", "Takemoto", "Johnson", "Williams", "Anderson", "Baker", "Carpenter", "Remover", "Garcia", "Rodriguez", "Candelaria", "Brown", "Martinez", "Davis",
        "Hernandez", "Thomas", "Moore", "Taylor", "Martin", "Perez", "Miller", "White", "Walker", "Robinson", "Parsons", "Young", "Allen", "Nguyen", "Torres", "Green", "Douglas", "Campbell",
        "Riviera", "Mitchell", "Roberts", "Cruz", "Parker", "Morales", "Collins", "Murphy", "Gutierrez", "Cooper", "Ramos", "Cox", "Chavez", "Wood", "Mendoza", "Price", "Alvarez", "Castillo",
        "Patel", "Ross", "Jimenez", "Myers", "Long", "Foster", "Sanchez", "Wright", "Scott", "Flores", "Wilson", "Carter", "Edwards", "Reyes", "Ward", "Richardson", "Watson", "Moreno", "Gibson",
        "Tran", "Ellis", "Kennedy", "Simpson", "Vargas", "Henry", "Warren", "Daniels", "Ferguson", "Soto", "Weaver", "Delgado", "Johnston", "Navarro", "Miranda", "Ayala", "Frazier",
        "Bates", "Gates", "Figueroa", "McDaniel", "Dawson", "Erickson", "Fletcher", "McKinney", "Reeves", "Cervantes", "Chan", "Xin", "Ruiz", "Satou", "Watanabe", "Shimizu", "Yamaguchi",
        "Suzuki", "Subaru", "Kobayashi", "Wang", "Chang", "Li", "Yang", "Huang", "Chou", "Singh", "Devi", "Mori", "Ishikawa", "Hashimoto", "Kim", "Park", "Kwon", "Shin", "Jang", "Ivanov",
        "Volkov", "Kelly", "O'Ryan", "Russo", "Moretti", "Ferrara", "Santoro", "D'Angelo", "Marinelli", "Serra", "Conte", "Caruso", "Lombardi", "Testa", "Sanna", "Cattaneo", "D'Amico",
        "De Jong", "Van der Berg", "De Groot", "Brouwer", "Smit", "Meijer"
        };

        String[] jobArrayDegreeless = {"Baker", "Butcher", "Data Entry Specialist", "Musician (Classical)", "Musician (Acoustic Guitar)", "Musician (Electric Guitar)",
        "Musician (Piano)", "Musician (Drummer)", "Musician (Singer)", "Music Producer", "Songwriter", "Marketing Specialist", "Fast Food Worker", "Retail Employee", "Construction Worker", "Chef (Diner)", "Chef (Chain Restaurant)", 
        "Chef (High-Class Restaurant)", "Mechanic", "Firefighter", "Used Car Salesperson", "Retail Manager", "Retail Associate Manager", "Fast-Food Assistant Manager", "Fast-Food Manager",
        "Telephone Technician", "Lineman", "Street Sweeper", "Comedian", "YouTuber", "Twitch Streamer", "VTuber", "Furry", "Video Game Tester", "Crash Test Dummy", "Pilot", "Trucker",
        "Gas Station Employee", "Delivery Driver", "IT Specialist", "Call Center Worker", "Car Wash Attendant", "Nail Salon Technician", "Cosmetologist", "Barber", "Hair Stylist",
        "Lumberjack", "News Anchor", "Journalist", "Janitor", "Custodian", "Cafeteria Worker", "Farmer", "Farm Worker", "Computer Technician", "Painter", "Gardener", "Movie Director",
        "Actor (Theater)", "Actor (Commercials)", "Actor (TV)", "Actor (Cinema)", "Actor (Online Productions)", "Local Politician", "Taxi Driver", "Union Leader", "Chiropractor", "Writer",
        "Criminal", "Scam Artist", "Drug Dealer", "Investor", "Professional Gambling Addict", "Head of Criminal Organization"

        };

        String[] jobArrayWithDegree = {"Accountant", "Director", "VP Of Finance", "VP of Marketing", "VP Of Communications", "Software Engineer", "Mechanical Engineer", "Construction Engineer",
        "Architect", "Fashion Designer", "Lawyer", "Pharmacist", "Nurse", "Dentist", "Vision Doctor", "Medical Doctor", "Plastic Surgeon", "Nuclear Pharmacist", "Gynecologist", "Pediatrician",
        "Anesthesiologist", "Emergency Room Doctor", "Emergency Room Nurse", "Computer Hardware Engineer", "Integrated Systems Engineer", "Game Developer", "Furry", "Chip Design Specialist",
        "Managing Director", "Jeweler", "Banker", "Adjunct Professor", "Professor", "Librarian", "Nuclear Power Plant Technician", "Agricultural Systems Engineer", "Archaeologist",
        "Chief Marketing Officer", "Data Scientist", "School Principal", "Superintendent", "School Board Member", "Supply Chain Manager", "Backend Engineer", "Cloud and Network Services Specialist",
        "Chief Cybersecurity Officer", "Director of Information Security", "Security Researcher", "UX Designer", "Principal Engineer", "Art Director", "Graphic Designer", "Creative Director",
        "Senior Financial Analyst", "Operations Analyst", "Civil Engineer", "Transit Engineer", "Judge", "Teacher", "Healthcare Administrator", "Structural Engineer",
        "Insurance Adjuster"

        };

        String[] houseArray = {"127 West Palmero Street (2B, 1BA)", "1147 23rd Street, Suite B (Apt, 2B, 1BA)", "975 East Birch Avenue (5B, 4BA)", "234 East Sunny Grove St. (1B, 1BA)",
        "923 North Windhelm Circle (3B, 2.5BA)", "4132 West 2nd Street, Room 112 (Apt, 1B, 1BA)", "8421 North Salamander Avenue (3B, 2BA)", "234 East Normandy Boulevard (Apt, 1B, 1BA)",
        "2231 East Nottingham Street (6B, 5BA)", "4823 East Ham Avenue, Penthouse Suite (Apt, 5B, 3BA)", "1123 East Vinewood Street (2B, 1BA)", "985 South Elliot Street (1B, 1.5BA)",
        "9821 North Birmingham Avenue (8B, 6BA)", "1750 South Chester Boulevard, Room 223 (Apt, 2B, 1BA)", "1195 East Harrison Circle, Room 23 (Motel, 1B, 1BA)", 
        "1630 North Elm Circle, Lot 23 (Trailer, 1B, 1BA)", "1337 South Pine Boulevard, Lot 15 (Mobile Home, 1B, 1BA)", "Highway 13, Overpass 23 (Tent, 1B, 0BA)",
        "1945 Corrections Avenue, Cell Block 23, Cell 5 (Prison Cell, 1B, 1BA)", "23 Government Avenue, Cell 3 (Jail Cell, 1B, 1BA)", "50 Government Avenue (Mayor's House, 10B, 6BA)",
        "182 East Hylia Avenue, Room 14 (Rehab Facility, 1B, 1BA)", "2 East Medical Boulevard, Inpatient Room 7 (Hospital Room, 1B, 1BA)", 
        "4 East Medical Boulevard, Room 13B (Retirement Facility, 1B, 1BA)", "138 East Montague Avenue, Parking Level 7 (Car, 1B, 0BA)", 
        "1892 West Palmer Avenue, Villa de Criminales, Building 1 (7B, 8BA)", "1892 West Palmer Avenue, Villa de Criminales, Building 3, Room 4 (Rented Room, 1B, 1BA)",
        "1892 West Palmer Avenue, Villa de Criminales, Building 3, Rooms 1 and 2 (Apt, 2B, 1BA)"

        };

        String[] carArray = {"2013 Chevrolet Spark (Sedan)", "2026 Cadillac Escalade (Luxury SUV)", "2026 Ram 1500 (Pickup)", "2022 Jeep Grand Cherokee (4x4 SUV)", "2012 Chevrolet Impala (Sedan)", 
        "2019 GMC Sierra Denali (Pickup)", "2013 Toyota Camry (Sedan)", "2015 Toyota Corolla (Sedan)", "2012 Toyota Prius (Hybrid Sedan)", "2026 Jeep Grand Wagoneer (Luxury SUV)", 
        "2026 Ford F-150 (Pickup)", "2002 Ford F-250 Diesel (Pickup, Diesel)", "2022 GMC Hummer EV (EV, Luxury Pickup)", "2025 Subaru Ascent (3-Row SUV)", "2015 Subaru Impreza (Sedan)", 
        "2022 Subaru WRX STI (Performance Sedan)", "1998 Subaru WRX STI (JDM Performance Sedan)", "1999 Nissan Skyline R34 GT-R (JDM Performance Sedan)", "2013 Nissan Altima (Sedan)", 
        "2010 Nissan Maxima (Sedan)", "1995 Nissan Sentra (Sedan)","2024 Toyota Avalon (Luxury Sedan)", "2026 Toyota Crown (Luxury Sedan)", "2020 Toyota Mirai (Hydrogen, Sedan)", 
        "2003 Toyota Yaris (Hatchback)", "2015 Toyota Highlander (SUV)", "2025 Toyota RAV4 (SUV)", "2026 Toyota 4Runner (SUV)", "2012 Toyota Tundra (Pickup)", "2004 Toyota Sequoia (SUV)", 
        "1998 Toyota Sienna (Minivan)", "2013 Toyota Sienna (Minivan)", "2026 Chrysler Pacifica (Minivan)", "2018 Chrysler 300 (Sedan)", "2017 Dodge Charger SRT Hellcat (Performance Sedan)",
        "2019 Jeep Grand Cherokee SRT TrackHawk (Performance SUV)", "2002 Chrysler PT Cruiser (Uhh... Car?)", "1997 Dodge Neon (Sedan)", "2003 Toyota Camry (Sedan)",
        "1985 AMC Jeep Wrangler (Offroad SUV)", "1987 AMC Jeep Grand Wagoneer (SUV)", "2003 Chrysler Pacifica (Crossover SUV)", "1964 Chevrolet Impala (Performance Sedan)",
        "2013 Hyundai Sonata (Sedan)", "2010 Kia Soul (Compact)", "2019 Kia EV4 (EV, SUV)", "2012 Hyundai Genesis (Luxury Sedan)", "2019 Hyundai Elantra (Sedan)", 
        "2025 Hyundai Ioniq 6 (EV, Sedan)", "2019 Hyundai Kona (Compact SUV)", "2026 Hyundai Santa Fe (SUV)", "2024 Hyundai Ioniq 5 (EV, SUV)", "2026 Hyundai Santa Cruz (Pickup)",
        "2020 Hyundai Veloster (Hatchback)", "2026 Kia K4 (Hatchback)", "2025 Kia K5 (Sedan)", "2019 Kia Sportage (SUV)", "2026 Kia Telluride (3-Row SUV)", "2022 Kia Carnival (Minivan)",
        "2026 Ram 2500 Super Duty (Pickup)", "2026 Genesis GV80 (Luxury SUV)", "2019 Genesis G70 (Luxury Sedan)", "2022 Lexus IS 500 F Sport Performance (Performance Luxury Sedan)",
        "2024 Lexus TX (Luxury SUV)", "2026 Toyota Crown Signia (Luxury SUV)", "2023 Subaru BRZ (Sports Car)", "2026 Subaru Crosstrek (Crossover)", "2022 Subaru Forester (SUV)",
        "2018 Subaru Outback (Station Wagon)", "1995 Subaru Sambar Van (Kei Van, JDM Import)", "2003 Subaru Baja (Compact Pickup)", "2024 BMW i4 (Luxury Sedan)", "2026 BMW 5 Series (Luxury Sedan)",
        "2026 BMW M3 Sedan (Performance Luxury Sedan)", "2026 Mercedes-Benz A-Class Sedan (Luxury Sedan)", "2025 Mercedes-Benz CLA (Luxury Sedan)", 
        "2023 Mercedes-Benz AMG S 63 E Performance (Performance Luxury Sedan)", "2026 Mercedes-Benz G 63 (Luxury Offroad SUV)", "2025 Mercedes-Maybach S 580 (Ultra-Luxury Sedan)",
        "2026 Rolls Royce Phantom (Ultra Luxury Sedan)", "2025 Mercedes-Benz Sprinter (Van)", "2013 Chevrolet Express (Van)", "2019 Ram ProMaster City (Compact Van)"

        };

        String[] citiesArray = {"Denver, Colorado", "Los Angeles, California", "Tampa, Florida", "St. Louis, Missouri", "Houston, Texas", "Dallas, Texas", "Orlando, Florida", 
        "Miami, Florida", "Washington, District of Columbia", "Seattle, Washington", "Portland, Oregon", "San Francisco, California", "Boulder, Colorado", "Minneapolis, Minnesota",
        "Chicago, Illinois", "Milwaukee, Wisconsin", "Columbus, Ohio", "New York City, New York", "Jersey City, New Jersey", "Newark, New Jersey", "Albany, New York", 
        "Boston, Massachusetts", "New Orelans, Louisiana", "Birmingham, Alabama", "Atlanta, Georgia", "Redmond, Washington", "San Bernardino, California", "Richmond, Virginia",
        "Louisville, Kentucky", "Kansas City, Missouri", "Topeka, Kansas", "Oklahoma City, Oklahoma", "Indianapolis, Indiana", "Jacksonville, Florida", "Nashville, Tennessee", 
        "Salt Lake City, Utah", "Pittsburgh, Pennsylvania", "Philadelphia, Pennnsylvania", "Detroit, Michigan", "Des Moines, Iowa", "Cleveland, Ohio", "Buffalo, New York",
        "Rochester, New York", "Providence, Rhode Island", "Portland, Maine", "Baltimore, Maryland", "Winston-Salem, North Carolina", "Charlotte, North Carolina", "Fayetteville, North Carolina",
        "Raleigh, North Carolina", "Augusta, Georgia", "Charleston, South Carolina", "Memphis, Tennessee", "St. Augustine, Florida", "Fort Lauderdale, Florida", "Fort Worth, Texas",
        "San Antonio, Texas", "El Paso, Texas", "Albuquerque, New Mexico", "Austin, Texas", "Colorado Springs, Colorado", "Cheyenne, Wyoming", "Las Vegas, Nevada", "San Jose, California",
        "Sacramento, California", "Fresno, California", "San Diego, California", "Tacoma, Washington", "Bend, Washington", "Boise, Idaho", "Billings, Montana", "Sioux Falls, South Dakota",
        "Honolulu, Hawaii", "Oahu, Hawaii", "Bismarck, North Dakota", "Lincoln, Nebraska", "Wichita, Kansas", "Cincinatti, Ohio", "Bentonville, Arkansas", "Jackson, Mississippi", 
        "Mobile, Alabama", "Montgomery, Alabama", "Huntington, West Virginia", "Dover, Delaware", "New Haven, Connecticut", "Spokane, Washington"

        };

        String[] companiesArray = {"AGH Industries, Inc.", "Sugiri of America, Inc.", "Newark-Penn Paper Company, Inc.", "Palermo Restaurants, LLC.", "Mayor's Office",
        "Callafia Industrial Management Company of America, Inc.", "ICH Distribution, LLC.", "Southern Arch, Inc.", "Sunset Management Corporation, Inc.", "American News Corporation, Inc.",
        "Freelancer", "Noriega Restaurants, Inc,", "American Furnishings, Inc.", "Huang Shenzhen Technology Co. Ltd.", "Shaxa Arcade Corp of America, Inc.", "School District", 
        "Walton Stores, Inc.", "McAfferty's Franchising, Inc.", "Gingham Furnishings, LLC.", "United American Dealerships, Inc.", "Automotive Advantage Insurance Company, Inc.",
        "Appleton Bancorp, Inc.", "IMH Investments, LLC.", "GB & Sons Contracting, LLC.", "HSK Restaurants America, LLC.", "Southern Belle Bancorp, Inc.", "Advantage Credit Services, Inc.",
        "SuperGame, Inc.", "Activator Games, Inc.", "Macrosoft Computing, Inc.", "Pear Corporation, Inc.", "DataWest, Inc.", "NorthWest Accountancy, Inc.", "Harrison-Collier Holdings, LLC.",
        "Applewood Books, Inc.", "Caligraph Digital Research, Inc.", "AMH Digital Media, Inc.", "Warnell Brothers Television Productions, Inc.", "Electronic Artwork, Inc.", 
        "Xi Lin Beijing Semiconductor Group, Co. Ltd.", "American Furries, Inc.", "Lintendo of America, Inc.", "Hanxiang United Technology Co. Ltd.", "Western Telephonics, Inc.", "Pacific Power Corporation, Inc.",
        "Southern PowerCorp, Inc.", "Allied Bancorp, Inc.", "Allied Investment Group, Inc.", "Non-Suspicious Activities Corporation, Inc.", "HGH Medical Clinics, P.C.", 
        "Blue Southern Hospitals, P.C.", "United Workers Corporation", "Allied Strategies, Inc.", "U.S. District Court", "Sanders, Gillham, and Associates, P.C.", 
        "Custer and Dunham, Attorneys at Law, P.C.", 
            
        };



//--BEGIN CODE SECTION--

    public String getDialogWithID(int dialogID){
        //this sets up a list containing all the re-usable dialogs, and then returns the dialog with the provided ID number. see Quick Reference Handbook for ID numbers.
        List<String> dialogList = Arrays.asList(dialogArray);

        return dialogList.get(dialogID);
    }

        public String getMaleNameWithID(int nameID){    
            
        //this sets up a list containing all the re-usable male names, and then returns the name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.
        List<String> nameListMale = Arrays.asList(nameArrayMale);
        
        
        return nameListMale.get(nameID);
    }
        //this sets up a list containing all the re-usable female names, and then returns the name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.
        public String getFemaleNameWithID(int nameID){        
        List<String> nameListFemale = Arrays.asList(nameArrayFemale);
        
        
        return nameListFemale.get(nameID);
    }
        public String getNBNameWithID(int nameID){       
            
        //this sets up a list containing all the re-usable non-binary names, and then returns the name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.
        List<String> nameListNB = Arrays.asList(nameArrayNB);
        
        
        return nameListNB.get(nameID);
    }

        public String getLastNameWithID(int nameID){   
        //this sets up a list containing all the re-usable last names, and then returns the name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.
        List<String> nameListLast = Arrays.asList(nameArrayLast);
        
        
        return nameListLast.get(nameID);
    }


        public String getLowJobNameWithID(int jobID){   
        //this sets up a list containing all the re-usable job titles (without degree requirement), and then returns the job with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.     
        List<String> jobListDegreeless = Arrays.asList(jobArrayDegreeless);
        
        
        return jobListDegreeless.get(jobID);
    }

        public String getHighJobNameWithID(int jobID){   
        //this sets up a list containing all the re-usable job titles (with degree requirement), and then returns the job with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.     
        List<String> jobListWithDegree = Arrays.asList(jobArrayWithDegree);
        
        
        return jobListWithDegree.get(jobID);
    }

        public String getHouseWithID(int houseID){    
        //this sets up a list containing all the re-usable house addresses, and then returns the address with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.    
        List<String> houseList = Arrays.asList(houseArray);
        
        
        return houseList.get(houseID);
    }

        public String getCarsWithID(int carID){       
        //this sets up a list containing all the re-usable car make/models, and then returns the make/model with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers. 
        List<String> carList = Arrays.asList(carArray);
        
        
        return carList.get(carID);
    }    


        public String getCityNameWithID(int cityID){      
        //this sets up a list containing all the re-usable city names, and then returns the city name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.  
        List<String> citiesList = Arrays.asList(citiesArray);
        
        
        return citiesList.get(cityID);
    }

        public String getCompanyNameWithID(int companyID){      
        //this sets up a list containing all the re-usable city names, and then returns the city name with the provided ID number. see Quick Reference Handbook or Search Function for ID numbers.  
        List<String> companiesList = Arrays.asList(companiesArray);
        
        
        return companiesList.get(companyID);
    }

        public int getIndexInArrays(int arrayID, String searchTerm){  
            
        //this allows you to search all of the previous arrays, by providing an arrayID (in the Quick Reference Handbook), and a searchTerm (The actual term to be searched), and it returns the ID number of the entered dialog (It's position within the array)
        
        //setup all the lists
        List<String> dialogList = Arrays.asList(dialogArray);
        List<String> nameListMale = Arrays.asList(nameArrayMale);
        List<String> nameListFemale = Arrays.asList(nameArrayFemale);
        List<String> nameListNB = Arrays.asList(nameArrayNB);
        List<String> nameListLast = Arrays.asList(nameArrayLast);
        List<String> jobListDegreeless = Arrays.asList(jobArrayDegreeless);
        List<String> jobListWithDegree = Arrays.asList(jobArrayWithDegree);
        List<String> houseList = Arrays.asList(houseArray);
        List<String> carList = Arrays.asList(carArray);
        List<String> citiesList = Arrays.asList(citiesArray);
        List<String> companiesList = Arrays.asList(companiesArray);


        if (arrayID == 1){
            return dialogList.indexOf(searchTerm);
        }
        else if (arrayID == 2){
            return nameListMale.indexOf(searchTerm);
        }
        else if (arrayID == 3){
            return nameListFemale.indexOf(searchTerm);
        }
        else if (arrayID == 4){
            return nameListNB.indexOf(searchTerm);
        }
        else if (arrayID == 5){
            return nameListLast.indexOf(searchTerm);
        }
        else if (arrayID == 6){
            return jobListDegreeless.indexOf(searchTerm);
        }
        else if (arrayID == 7){
            return jobListWithDegree.indexOf(searchTerm);
        }
        else if (arrayID == 8){
            return houseList.indexOf(searchTerm);
        }
        else if (arrayID == 9){
            return carList.indexOf(searchTerm);
        }
        else if (arrayID == 10){
            return citiesList.indexOf(searchTerm);
        }
        else if (arrayID == 11){
            return citiesList.indexOf(searchTerm);
        }
        else {
            return 0;
        }
    }
}

