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

        // Finally we return all of the lga

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


    public ArrayList<TempPopDataCountry2A> getTempPopCountry2A(String StartYear, String EndYear, String SortBy, String Order) {
        // Create the ArrayList of LGA objects to return
        ArrayList<TempPopDataCountry2A> AllCountryPopulation = new ArrayList<TempPopDataCountry2A>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        String query = String.format("""
                SELECT c1.name, 
                c1.year AS 'startYear', c1.landAvgTemp AS 'startTemp', c1.populationNum AS 'startPop', 
                c2.year AS 'endYear', c2.landAvgTemp AS 'endTemp', c2.populationNum AS 'endPop',
                (c2.landAvgTemp - c1.landAvgTemp) AS "Temperature change",
                (c2.populationNum - c1.populationNum) AS "Population change"
                FROM countryTempPop c1 JOIN countryTempPop c2 ON c1.name = c2.name
                WHERE c1.code NOT IN ('WLD', 'SAS')
                AND c1.year = %s AND c2.year = %s
                ORDER BY "%s" %s;
                    """, StartYear, EndYear, SortBy, Order);

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
        
            // The Query
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setQueryTimeout(30);
            
            // Get Result
            ResultSet results = pstmt.executeQuery();
            int row = 1;
            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("name");
                int startYear = results.getInt("startYear");
                Double StartTemp = results.getDouble("startTemp");
                int StartPop = results.getInt("startPop");
                int endYear = results.getInt("endYear");
                Double EndTemp = results.getDouble("endTemp");
                int EndPop = results.getInt("endPop");
                Double TemperatureChange = results.getDouble("Temperature change");
                int PopulationChange = results.getInt("Population change");

                TempPopDataCountry2A Country = new TempPopDataCountry2A(String.format("resultNum%d", row), name, startYear, StartTemp, StartPop, endYear, EndTemp, EndPop, TemperatureChange, PopulationChange);
                AllCountryPopulation.add(Country);
                row++;
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
    
    public ArrayList<TempDifference> getData2B (int firstYear, int lastYear, String cmd){ //cmd eligible input: cityAvg, cityMax, cityMin, stateAvg, stateMin, stateMax
        ArrayList<TempDifference> tempDifferences = new ArrayList<TempDifference>();
        ArrayList<TempDifference> Ranking = new ArrayList<TempDifference>();
        //for city
        String query1 ="""
            SELECT c.ID, c.name, ct.year, ct.landAvgTemp, ct.landMinTemp, ct.landMaxTemp, co.code AS country_code, co.name AS country_name
            FROM cityTemp ct LEFT JOIN cities c ON ct.cityID = c.id JOIN countries co ON c.countryCode = co.code
            WHERE c.id IN (
                SELECT c.id
                FROM cityTemp ct LEFT JOIN  cities c ON ct.cityID = c.id
                WHERE YEAR IN (?, ?)
                GROUP BY c.id
                HAVING COUNT(*) = 2
                ORDER BY c.id)
            AND YEAR IN(?, ?)
            ORDER BY ct.cityID;
            """;
        //for state
        String query2 ="""
            SELECT s.id, s.name, st.year, st.landAvgTemp, st.landMinTemp, st.landMaxTemp, co.code AS country_code, co.name AS country_name
            FROM stateTemp st LEFT JOIN  states s ON st.stateID = s.id JOIN countries co ON s.countryCode = co.code
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
            default:
                System.out.println("Wrong input!");
                return Ranking;
            
        }

        //start getting data
        try(Connection conn = DriverManager.getConnection(DATABASE)){

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setQueryTimeout(30);
            pstmt.setInt(1, firstYear);
            pstmt.setInt(2, lastYear);
            pstmt.setInt(3, firstYear);
            pstmt.setInt(4, lastYear);
            
            int count = 0;
            double firstYearTemp = 0;
            try(ResultSet results = pstmt.executeQuery()){
                while (results.next()){
            //get data
                if(count == 0 && results.getInt("year") == firstYear){
                    firstYearTemp = results.getDouble(temp); //get first year temperature
                    count++;
                }
                else if (results.getInt("year") == lastYear){
                    TempDifference data = new TempDifference();
                    data.setID(results.getInt("ID"));
                    data.setName(results.getString("name"));
                    data.setDifference(firstYearTemp, results.getDouble(temp)); //get all else and difference
                    tempDifferences.add(data);
                    count = 0;
                }
            }
        }
            count = 0;
            System.out.println(tempDifferences.size());


            //done getting data
            //sorting now
                for (int i = 0; i < 10; i++) {
                    for(int j = i + 1; j < tempDifferences.size(); j++){
                        if(tempDifferences.get(j).getDifference() > tempDifferences.get(i).getDifference()){
                            Collections.swap(tempDifferences, j, i);
                        };
                    }
                }
                for (TempDifference difference : tempDifferences) {
                Ranking.add(difference);                     //test part
                count++;
                if (count == 10){
                    break;
                } 
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Ranking;
    }

    
    public ArrayList<Integer> getYear() {
        // Create the ArrayList of LGA objects to return
        ArrayList<Integer> AllYears = new ArrayList<Integer>();
 
        // Setup the variable for the JDBC connection
        Connection connection = null;
 
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
 
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
 
            // The Query
            String query = "SELECT DISTINCT year FROM countryTemp;";
           
            // Get Result
            ResultSet results = statement.executeQuery(query);
 
            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int Year = results.getInt("year");
 
               
                AllYears.add(Year);
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
 
       
        return AllYears;
    }



    
}



