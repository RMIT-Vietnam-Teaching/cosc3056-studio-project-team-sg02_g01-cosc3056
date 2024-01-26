package helper;

import java.io.File;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.charset.StandardCharsets; //For scanning characters with diacritics


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
public class AddDataStates {

   // MODIFY these to load/store to/from the correct locations
   private static final String DATABASE = "jdbc:sqlite:database/climate.db";
   private static final String CSV_FILE = "database/GlobalYearlyLandTempByState.csv";


   public static void main (String[] args) {

      // JDBC Database Object
      Connection connection = null;

      // Adding from population table
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE), StandardCharsets.UTF_8.name()); //StandardCharset for scanning characters with Diacritics

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");
         String[] headerTitles = header.split(",");
         ArrayList<String> headers = new ArrayList<String>(Arrays.asList(headerTitles));

         // Connect to JDBC database
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            String[] lineSeparate = line.split(",");

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();

            // Create Insert Statement
            String query = "INSERT into states (id, name, countryCode) VALUES ("
                           + row + ","
                           + String.format("\"%s\"", lineSeparate[headers.indexOf("State")]) + ","
                           + String.format("(SELECT code FROM countries WHERE name = "%s")", lineSeparate[headers.indexOf("Country")])
                           + ")";

            // Execute the INSERT
            try {
               statement.execute(query);
               System.out.println("Executing: " + query);
            } catch (SQLException e) {
               continue; // Any Primary Key repeats is ignored
            }
            row++;
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}