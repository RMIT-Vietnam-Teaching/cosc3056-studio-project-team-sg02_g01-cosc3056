package app;

import java.util.ArrayList;

import app.Objects.WorldTempPop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/climate.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<LGA> getLGAs2016() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA WHERE year='2016'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int code     = results.getInt("code");
                String name  = results.getString("name");

                // Create a LGA Object
                LGA lga = new LGA(code, name, 2016);

                // Add the lga object to the array
                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    // TODO: Add your required methods here

    //Get data for Level 1A
        // 1st element: Earliest data of temp
        // 2nd element: Earliest data of population
        // 3rd element: Latest data of population
        // 4th element: Latest data of temp
    public ArrayList<WorldTempPop> getData1A (){
        ArrayList<WorldTempPop> earlyLateData = new ArrayList<WorldTempPop>();

        String query ="""
            SELECT w.year, w.landAvgTemp, p.populationNum
            FROM worldTemp w LEFT JOIN population p ON w.year = p.year AND p.countryCode = 'WLD'
            WHERE w.year = (SELECT MAX(year) FROM worldTemp)
            OR w.year = (SELECT MIN(year) FROM worldTemp)
            OR w.year = (SELECT MAX(year) FROM population WHERE p.countryCode = 'WLD')
            OR w.year = (SELECT MIN(year) FROM population WHERE p.countryCode = 'WLD')
            """;

        try(Connection conn = DriverManager.getConnection(DATABASE)){

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setQueryTimeout(30);

            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                WorldTempPop data = new WorldTempPop();
                data.setYear(results.getInt("year"));
                data.setTemp(results.getDouble("landAvgTemp"));
                data.setPop(results.getLong("populationNum"));
                earlyLateData.add(data);
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return earlyLateData;
    }
}
