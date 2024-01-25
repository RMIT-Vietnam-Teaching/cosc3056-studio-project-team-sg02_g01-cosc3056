package app;

import java.util.ArrayList;
import java.util.Collections;
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
                String imagepath = results.getString("img_path") ;

                Persona Person= new Persona(name, age, location, background, quotes, needs, goals, skills , imagepath);
                
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




    public ArrayList<TemperatureDataWorld> getTemperatureWorld(int StartYear, int EndYear) {
        // Create the ArrayList of LGA objects to return
        ArrayList<TemperatureDataWorld> WorldTemperature = new ArrayList<TemperatureDataWorld>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
        
            // The Query
            String query = """
                SELECT * FROM 
                worldTemp w1 JOIN
                worldTemp w2
                WHERE w1.year = ? AND w2.year = ?;
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
                int year1 = results.getInt("year");
                float landAvgTemp = results.getFloat("landAvgTemp");
                float landMinTemp = results.getFloat("landMinTemp");
                float landMaxTemp = results.getFloat("landMaxTemp");
                float landOceanAvgTemp = results.getFloat("landOceanAvgTemp");
                float landOceanMinTemp = results.getFloat("landOceanMinTemp");
                float landOceanMaxTemp = results.getFloat("landOceanMaxTemp");
                int year2 = results.getInt("year:1");
                float landAvgTemp2 = results.getFloat("landAvgTemp:1");
                float landMinTemp2 = results.getFloat("landMinTemp:1");
                float landMaxTemp2 = results.getFloat("landMaxTemp:1");
                float landOceanAvgTemp2 = results.getFloat("landOceanAvgTemp:1");
                float landOceanMinTemp2 = results.getFloat("landOceanMinTemp:1");
                float landOceanMaxTemp2 = results.getFloat("landOceanMaxTemp:1");



                TemperatureDataWorld WorldData = new TemperatureDataWorld(year1, landAvgTemp, landMinTemp, landMaxTemp, landOceanAvgTemp, landOceanMinTemp, landOceanMaxTemp, year2, landAvgTemp2, landMinTemp2, landMaxTemp2, landOceanAvgTemp2, landOceanMinTemp2, landOceanMaxTemp2);
                WorldTemperature.add(WorldData);
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

      
        return WorldTemperature;
    }

    public void getData2B (int firstYear, int lastYear, String cmd){ //cmd eligible input: cityAvg, cityMax, cityMin, stateAvg, stateMin, stateMax
        ArrayList<TempDifference> tempDifferences = new ArrayList<TempDifference>();
        //for city
        String query1 ="""
            SELECT c.ID, c.name, ct.year, ct.landAvgTemp, ct.landMinTemp, ct.landMaxTemp 
            FROM cityTemp ct LEFT JOIN cities c ON ct.cityID = c.id 
            WHERE YEAR = ? OR YEAR = ? 
            ORDER BY ct.cityID; 
            """;
        //for state
        String query2 ="""
            SELECT s.id, s.name, st.year, st.landAvgTemp, st.landMinTemp, st.landMaxTemp
            FROM stateTemp st LEFT JOIN  states s ON st.stateID = s.id
            WHERE s.id IN (
            
            SELECT s.id
            FROM stateTemp st LEFT JOIN  states s ON st.stateID = s.id
            WHERE YEAR IN (?, ?)
            GROUP BY s.id
            HAVING COUNT(*) = 2
            ORDER BY s.id
            
            )
            AND YEAR IN(?, ?)
            ORDER BY s.id;
            """;
        //control here 
        String query = null;
        String temp = null;
        switch (cmd){
            case "cityAvg": 
                query = query1;
                temp = "landAvgTemp";
                break;
            case "cityMax": 
                query = query1;
                temp = "landMaxTemp";
                break;
            case "cityMin": 
                query = query1;
                temp = "landMinTemp";
                break;
            case "stateAvg": 
                query = query2;
                temp = "landAvgTemp";
                break;
            case "stateMax": 
                query = query2;
                temp = "landMaxTemp";
                break;
            case "stateMin": 
                query = query2;
                temp = "landMinTemp";
                break;
            
        }

        //start getting data
        try(Connection conn = DriverManager.getConnection(DATABASE)){

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setQueryTimeout(30);
            pstmt.setInt(1, firstYear);
            pstmt.setInt(2, lastYear);
            if(query.equals(query2)){
                pstmt.setInt(3, firstYear);
                pstmt.setInt(4, lastYear);
            }

            ResultSet results = pstmt.executeQuery();
            int count = 0;
            double firstYearTemp = 0;
            //get data
            while (results.next()) {
                if(count == 0){
                    firstYearTemp = results.getDouble(temp); //get first year temperature
                    count++;
                }
                else{
                    TempDifference data = new TempDifference();
                    data.setID(results.getInt("ID"));
                    data.setName(results.getString("name"));
                    data.setDifference(firstYearTemp, results.getDouble(temp)); //get all else and difference
                    tempDifferences.add(data);
                    count =0;
                }
            }
            count = 0;
            


            //done getting data
            //sorting now
                for (int i = 0; i < 3; i++) {
                    for(int j = i + 1; j < tempDifferences.size(); j++){
                        if(tempDifferences.get(j).getDifference() > tempDifferences.get(i).getDifference()){
                            Collections.swap(tempDifferences, j, i);
                        };
                    }
                }
                for (TempDifference difference : tempDifferences) {
                System.out.print(difference.getDifference());
                System.out.println(difference.getName());                     //test part
                count++;
                if (count ==5){
                    break;
                } 
            }

               
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}



