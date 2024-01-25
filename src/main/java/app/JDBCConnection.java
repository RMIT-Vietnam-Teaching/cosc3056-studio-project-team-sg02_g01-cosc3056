package app;

import java.util.ArrayList;

import app.Objects.*;


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
            ORDER BY p.populationNum
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


    public ArrayList<TeamMember> getMember() {
        // Create the ArrayList of LGA objects to return
        ArrayList<TeamMember> TeamMem = new ArrayList<TeamMember>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM teamMembers;";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String memberID  = results.getString("id");
                String name  = results.getString("name");

                
                TeamMember Student = new TeamMember(memberID, name);

                
                TeamMem.add(Student);
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

        
        return TeamMem;
    }

    public ArrayList<Persona> getPersona() {
        // Create the ArrayList of LGA objects to return
        ArrayList<Persona> People = new ArrayList<Persona>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM personas;";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name  = results.getString("name");
                int age  = results.getInt("age");
                String location = results.getString("location");
                String background = results.getString("background");
                String quotes = results.getString("quotes");
                String needs = results.getString("needs");
                String goals = results.getString("goals");
                String skills = results.getString("skills");
                //String imagepath = results.getString("img_path") 

                Persona Person= new Persona(name, age, location, background, quotes, needs, goals, skills /*, imagepath */);
                
                People.add(Person);
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

        return People;
    }


    public ArrayList<PopulationDataCountry> getPopulationCountry(int StartYear, int EndYear) {
        // Create the ArrayList of LGA objects to return
        ArrayList<PopulationDataCountry> AllCountryPopulation = new ArrayList<PopulationDataCountry>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
        
            // The Query
            String query = """
                SELECT p1.year AS year1, c.name AS Name, p1.populationNum AS population1, p2.year AS year2, p2.populationNum AS population2
                FROM population p1 
                JOIN countries c
                ON p1.countryCode = c.code
                JOIN population p2 
                ON p1.countryCode = p2.countryCode
                WHERE p1.year = ? AND p2.year = ? AND c.code NOT IN ('WLD', 'SAS');
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setQueryTimeout(30);
            statement.setInt(1, StartYear);
            statement.setInt(2, EndYear);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int year1 = results.getInt("year1");
                String name = results.getString("Name");
                int population1 = results.getInt("population1");
                int year2 = results.getInt("year2");
                int population2 = results.getInt("population2");

                PopulationDataCountry Country = new PopulationDataCountry(year1, population1, year2, population2, name);
                AllCountryPopulation.add(Country);
            }
            

            // Close the statement because we are done with it
            //statement.close();
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

      
        return AllCountryPopulation;
    }



    public ArrayList<TemperatureDataCountry> getTemperatureCountry(int StartYear, int EndYear) {
        // Create the ArrayList of LGA objects to return
        ArrayList<TemperatureDataCountry> AllCountryTemperature = new ArrayList<TemperatureDataCountry>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
        
            // The Query
            String query = """
                SELECT ct1.year AS year1, ct1.landAvgTemp AS avgTemp1, ct1.landMinTemp AS minTemp1, ct1.landMaxTemp AS maxTemp1, c.name as Name, ct2.year AS year2, ct2.landAvgTemp AS avgTemp2, ct2.landMinTemp AS minTemp2, ct2.landMaxTemp AS maxTemp2 
FROM 
countryTemp ct1 JOIN countries c ON ct1.countryCode = c.code
JOIN countryTemp ct2 ON ct1.countryCode = ct2.countryCode
WHERE ct1.year = ? AND ct2.year = ? AND c.code NOT IN ('WLD', 'SAS');
                    """;
            PreparedStatement statement = connection.prepareStatement(query)
            statement.setQueryTimeout(30);
            statement.setInt(1, StartYear);
            statement.setInt(2, EndYear);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int year1 = results.getInt("year1");
                float avgTemp1 = results.getFloat("avgTemp1");
                float minTemp1 = results.getFloat("minTemp1");
                float maxTemp1 = results.getFloat("maxTemp1");
                String name = results.getString("Name");
                int year2 = results.getInt("year2");
                float avgTemp2 = results.getFloat("avgTemp2");
                float minTemp2 = results.getFloat("minTemp2");
                float maxTemp2 = results.getFloat("maxTemp2");


                TemperatureDataCountry CountryData = new TemperatureDataCountry(year1, avgTemp1, minTemp1, maxTemp1, name, year2, avgTemp2, minTemp2, maxTemp2);
                AllCountryTemperature.add(CountryData);
            }
            

            // Close the statement because we are done with it
            //statement.close();
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

      
        return AllCountryTemperature;
    }



    
}



