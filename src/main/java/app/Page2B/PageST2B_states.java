package app.Page2B;

import java.util.ArrayList;

import app.JDBCConnection;
import app.Objects.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST2B_states implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2B_states";

    @Override
    public void handle(Context context) throws Exception {

        // JDBC object
        JDBCConnection jdbc = new JDBCConnection();
        String year_start = context.formParam("year_start"); //get year start into java
        String year_end = context.formParam("year_end");
        String sort_by = context.formParam("sort_by");
        String countryName = context.formParam("country");

        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               """
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>lvl2B States</title>
                       """;

        // Add some CSS (external file)
        html = html + 
        """
            <link href='common.css' rel='stylesheet'>
            <link href='LVL2-A.css' rel='stylesheet'>
            <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>
            <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>
            <script src='lvl2.js'></script>
                """;
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        // Header / topnav
        html = html + """
            <header>
                <div class="nav">
                    <a class="logo" href="/"><img src="logo-web.jpg" alt="logo" /></a>
                    <p>Website name</p>
                    <a class="aboutUs visited" href="mission.html">Our Mission</a>
                    <a class="visited" href="options.html#Basic">Basic options</a>
                    <a class="visited" href="options.html#Advanced">Advanced options</a>
                    <a class="getStarted" href="options.html">Get started</a>
                </div>
            </header>
        """;

        //Open main
        html += "<main>";


        //Main_top - Top options
        html += """
            <div class='main_section' id='main_section_top'>
            <div id='top_options'>
            """;
                   //search bar for country
        html += """                    
                <div class='search bar'>
                    <label for='search_bar'> Search country here </label>
                    <input list='search_bar_list', id='search_bar', name='country', form='lvl2B', placeholder='Search for year here'/>
                    <datalist id ='search_bar_list'>
                """;
                ArrayList<Countries> countries = jdbc.getCountries();
                for (Countries dataCountries : countries) {
                    html+="<option value="+dataCountries.getName()+"></option>";
                }
                            
        html += """
                    </datalist>
                </div>
                """;
        html+= """
            <div class='options'>
                <div style='padding-bottom: 10px;'>
                        <b>Year range:</b>
                    </div>
                    """;   

        //search for year start
        html += """
            <form id='lvl2B' action='/page2B_states' method='post'>

            <div class='year_option'>
                <label for='year_end'>Start year: </label>
                <input list ='year_start_list' id ='year_start' name='year_start' form='lvl2B' placeholder ='Select start year here'/>
                """;
          html+= """
                
                <datalist id='year_start_list'>
                    """;
                    //Start year options (ADD ME)
                    ArrayList<Integer> startYears = jdbc.getYear();
                    for (int year : startYears) {
                            html += String.format("<option value = \"%d\">%d</option>", year, year);
                    }
            
        html += """
                </datalist>
            </div>
        """; 

        //search for year end
        html += """
            <div class='year_option'>
                <label for='year_end'>End year: </label>
                <input list ='year_end_list' id ='year_end' name='year_end' form='lvl2B' placeholder ='Select end year here'/>
                """;
          html+= """
                <datalist id='year_end_list'>
                """;
                //End year options (ADD ME)
                ArrayList<Integer> endYears = jdbc.getYear();
                for (int year : endYears) {
                        html += String.format("<option selected value = \"%d\">%d</option>", year, year);
                }


        html += """
                </datalist>
            </div>
        """;    //end div for year option
        html += """
                    </div>
                </form>
            </div>
            </div>
            </div>

                """;
                
        //Open Main_bottom 
        html += "<div class='main_section' id='main_section_bottom'>";
        html += """
            <div id='bottom_options'>
                <div style='display: flex; justify-content: space-between;'>
                    <div id='display_by'>
                        <span style='visibility: hidden;'>Blank</span>
                        <span><b>Display results by: </b></span>
                        <a href='/page2B_cities' class='select1or2'>Cities</a>
                        <a href='/page2B_states' style='pointer-events: none;' id = 'selected' class='select1or2'>States</a>
                        </div>"""//display by end here
                                +
                       """
                        <div id='sort_options'>
                            <div class='dropdown_container'>
                                <div class='sort_by_options'>
                                    <label for='sort_by'>Sort by: </label>
                                    <select id='sort_by' name='sort_by' form='lvl2B'>
                                    """;
        String[] optionInner = {"Average temperature difference", "Minimum temperature difference", "Maximum temperature difference"};
        String[] optionValue = {"stateAvg", "stateMin", "stateMax"};
        for (int i = 0; i < optionValue.length; i++) {
            if(sort_by != null && sort_by.equals(optionValue[i])){
                html+= String.format("<option selected value = \"%s\">%s</option>", optionValue[i], optionInner[i]);
            }
            else {
                html += String.format("<option value = \"%s\">%s</option>", optionValue[i], optionInner[i]);
            } 
        }
                           
        html+=          """
                                    </select>
                                </div>
                                """//sort by options end here
                                +        
                            """
                            </div>
                        </div>
                    </div>
                    <button type='submit' form='lvl2B' class='btn btn-success'>Submit</button>
                </div>
                    """;//end of all divs

        
      //  String search = context.formParam("countrySearch");//TODO
        System.out.println(year_start + year_end + sort_by);
        if (year_start == ("") || year_end == ("") || year_end == null || year_start == null) { //datalist can return "" new tab will return null values
            //No inputs, no result list
            html += "<h2><i>(Please select your options from above and click Submit)</i></h2>";
        }
        else {
            html += outputCountries(year_start, year_end, sort_by, countryName);
        }
            
        // Close main_bottom
        html += "</div>";
        // Close main
        html += "</main>";
        // Footer
        html = html + """
            <footer>
                <a href="/"><img src="logo-web.jpg" alt="logo" /></a>
                <div class="link">
                    <a href="">Link</a>
                    <a href="">Link</a>
                    <a href="">Link</a>
                    <a href="">Link</a>
                </div>
            </footer>
            """;
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String outputCountries(String year_start, String year_end, String sort_by, String countryName) { //ouput countries method
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<TempDifference> results = jdbc.getData2B(Integer.parseInt(year_start), Integer.parseInt(year_end), sort_by, countryName);
        if (results.size() ==0){
            html+="<h2><i>There is no data for "+ countryName+" for this time period!</i></h2>";
        }
        else{
        String sort_option = null;
        switch (sort_by) {
            case "cityAvg": 
                sort_option = "Average";
                break;
            case "cityMax":
                sort_option ="Maximum";
                break;
            case "cityMin":
                sort_option = "Minimum";
                break;
        }
        boolean sortPop = (sort_by.toLowerCase().contains("population"));

        //Open result display area
        html += "<div id='results_display'><h1>"+ "Country: "+ countryName +"</h1>";

        //Toggle raw-percentage switch
        html += """
            <div class='toggle_raw_percent'>
                <span>Raw Data - Percentage change toggle: </span>
                <label for='flexSwitchCheckDefault'><img src='number_sign.png' style='max-height: 80%;'></label>
                <div class='form-check form-switch'>
                    <input class='form-check-input' type='checkbox' id='percent_toggle'>
                </div>
                <label for='flexSwitchCheckDefault'><img src='percent_sign.png' style='max-height: 80%;'></label>
            </div>
                """;

        //Display results
        for (TempDifference result : results) {

        html += """
            <div class='result_container carousel carousel-dark slide' style='border: solid' id=
            """
                + "'" + result.getID() + "'" +
            """
                 data-bs-ride='carousel' data-bs-interval='false'>
            <div class='carousel-inner'>
                <div class='temp_slide carousel-item
                """
                    + (sortPop?"":" active") + "'>" +
                """
                    <div class='result_row_1'>
                        <div class='name'>
                        """
                            + result.getName() +
                        """
                            </div>
                        <div class='topic'>
                        """
                        +  sort_option +      //min or max or avg temperature change
                                """ 
                                    <b>Temperature</b> change</div>
                        <div style='visibility: hidden;'>Blank</div>
                    </div>
                    <div class='result_row_2'>
                        <div class='num_difference'>
                            <div>In
                            """
                                + " " + (Integer.parseInt(year_end) - Integer.parseInt(year_start)) + " " +
                            """
                                years</div>
                            <div class='data_num uncheck'>
                                <div class='change_value'>
                                """
                                    + String.format("%.2f", result.getDifference()) +
                                """
                                    <span class='change_unit'>°C</span></div>
                            </div>
                            <div class='data_percent uncheck'>
                                <div class='change_value'>
                                """
                                    + String.format("%.2f", result.getDifferencePercent()) +
                                """
                                    <span class='change_unit'>%</span></div>
                            </div>
                        </div>
                        <img src=
                        """
                            + (result.getUp()?"icon-increase.jpg":"icon-decrease.jpg") +
                        """
                            class='trend'>
                        <div class='year_data'>
                            <div class='end_year'>
                                <div class='year'>
                                """
                                    + year_end +
                                """
                                    </div>
                                <div class='data'>
                                """
                                    + result.tLastYear +
                                """
                                    °C</div>
                            </div>
                            <p></p>
                            <div class='start_year'>
                                <div class='year'>
                                """
                                    + year_start +
                                """
                                    </div>
                                <div class='data'>
                                """
                                    + result.tFirstYear +
                                """
                                    °C</div>
                            </div>
                        </div> 
                        <img src='icon-temp.jpg' height='100px'>
                    </div>
                </div>
                
            </div>
        </div>
                """;
        }
    }
        
        //Close result display area
        html += "</div>";
        return html;
    }

}
