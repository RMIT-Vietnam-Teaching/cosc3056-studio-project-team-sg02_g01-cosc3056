package helper;

import java.io.File;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stand-alone Java file for processing the database CSV files.
 * <p>
 * You can run this file using the "Run" or "Debug" options
 * from within VSCode. This won't conflict with the web server.
 * <p>
 * This program opens a CSV file from the Closing-the-Gap data set
 * and uses JDBC to load up data into the database.
 * <p>
 * To use this program you will need to change:
 * 1. The input file location
 * 2. The output file location
 * <p>
 * This assumes that the CSV files are the the **database** folder.
 * <p>
 * WARNING: This code may take quite a while to run as there will be a lot
 * of SQL insert statments!
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au

 */
public class AddDataCountries {

   // MODIFY these to load/store to/from the correct locations
   private static final String DATABASE = "jdbc:sqlite:database/climate.db";
   private static final String CSV_POPULATE = "database/Population.csv";
   private static final String CSV_LAND_COUNTRY = "database/GlobalYearlyLandTempByCountry.csv";
   private static final String CSV_LAND_CITY = "database/GlobalYearlyLandTempByCity.csv";
   private static final String CSV_LAND_STATE = "database/GlobalYearlyLandTempByState.csv";


   public static void main (String[] args) {

      // JDBC Database Object
      Connection connection = null;

      // Adding from population table
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_POPULATE));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Connect to JDBC database
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            String[] lineSeparate = line.split(",");

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();

            // Create Insert Statement
            String query = "INSERT into countries (name, code) VALUES ("
                           + "\"" + lineSeparate[0]+ "\"" + ","
                           + "'" + lineSeparate[1]+ "'"
                           + ")";

            // Execute the INSERT
            System.out.println("Executing: " + query);
            try {
               statement.execute(query);
            } catch (Exception e) {
               continue; // Any Primary Key repeats is ignored
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      //Add countries from other tables (if country not added yet)
      addCountry(CSV_LAND_COUNTRY, connection); //From GlobalYearlyLandTempByCountry.csv
      addCountry(CSV_LAND_CITY, connection); //From GlobalYearlyLandTempByCity.csv
      addCountry(CSV_LAND_STATE, connection); //From GlobalYearlyLandTempByState.csv
   }

   // Function for adding countries from the "GlobalYearlyLandTemp..." csv files
   private static void addCountry(String csvFile, Connection connection) {
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(csvFile));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         String[] headerTitles = header.split(",");
         ArrayList<String> headers = new ArrayList<String>(Arrays.asList(headerTitles));
         int countryHeaderIndex = headers.indexOf("Country"); //find the index of the "country" column for country name

         // Read each line of the CSV
         String lastCountry = "";
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            String[] lineSeparate = line.split(",");
            String currentCountry = lineSeparate[countryHeaderIndex];

            //Skips if repeating the same country back to back
            if (currentCountry.equals(lastCountry)) {
               continue;
            }
            else {
               lastCountry = currentCountry;
            }

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();

            // Create Insert Statement
            // Insert country name into PRIMARY KEY "name" field in "countries" table
            String query = "INSERT into countries (name) VALUES ("
                           + "\"" + currentCountry+ "\""
                           + ")";

            // Execute the INSERT
            System.out.println("Executing: " + query);
            try {
               statement.execute(query);
            } catch (Exception e) {
               continue; // Any Primary Key repeats is ignored
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}