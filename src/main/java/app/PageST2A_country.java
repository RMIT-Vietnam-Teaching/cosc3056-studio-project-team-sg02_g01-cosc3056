package app;

import java.util.ArrayList;

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
public class PageST2A_country implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2A_country.html";

    @Override
    public void handle(Context context) throws Exception {

        // JDBC object
        JDBCConnection jdbc = new JDBCConnection();

        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               """
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Lvl2A</title>
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
                <img src='img/logo.jpg' height='100px'>
                <div class='options'>
                    <div style='padding-bottom: 10px;'>
                        <b>Year range:</b>
                    </div>
                    <form id='lvl2A' action='/page2A_country.html' method='post'>
                        <div class='dropdown_container'>
                            <div class='year_option'>
                                <label for='year_start'>Start year: </label>
                                <select id='year_start' name='year_start' form='lvl2A'>
                """;

                //Start year options (ADD ME)
                ArrayList<Integer> startYears = jdbc.getYear();
                for (int year : startYears) {
                    html += String.format("<option>%d</option>", year);
                }
        html += """
            </select>
            </div>
            <div class='year_option'>
                <label for='year_end'>End year: </label>
                <select id='year_end' name='year_end' form='lvl2A'>
                """;
                //End year options (ADD ME)
                ArrayList<Integer> endYears = jdbc.getYear();
                for (int year : endYears) {
                    html += String.format("<option>%d</option>", year);
                }

        html += """
                        </select>
                        </div>
                    </div>
                </form>
            </div>
            </div>
            </div>

                """;
                
        //Open Main_bottom 
        html += "<div class='main_section' id='main_section_bottom'>";
        //Bottom options
        html += """
            <div id='bottom_options'>
                <div style='display: flex; justify-content: space-between;'>
                    <div id='display_by'>
                        <span style='visibility: hidden;'>Blank</span>
                        <span><b>Display results by: </b></span>
                        <a href='' style='pointer-events: none;'>Country</a>
                        <a href=''>World</a>
                    </div>
                    <div id='sort_options'>
                        <div class='dropdown_container'>
                            <div class='sort_by_options'>
                                <label for='sort_by'>Sort by: </label>
                                <select id='sort_by' name='sort_by' form='lvl2A'>
                                    <option value='temp'>Temperature change</option>
                                    <option value='pop'>Population change</option>
                                </select>
                            </div>
                            <div class='order_by_options' style='margin-top: 5px;'>
                                <label for='order_by'>Order by: </label>
                                <select id='order_by' name='order_by' form='lvl2A'>
                                    <option value='asc'>Ascending</option>
                                    <option value='desc'>Descending</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <button type='submit' form='lvl2A' class='btn btn-success'>Submit</button>
            </div>
                """;

        //Results
        String year_start = context.formParam("year_start");
        String year_end = context.formParam("year_end");
        String sort_by = context.formParam("sort_by");
        String order_by = context.formParam("order_by");

        if (year_start == null) {
            //No inputs, no result list
            html += "<h2><i>(Please select your options from above and click Submit)</i></h2>";
        }
        else {
            html += outputCountries(year_start, year_end, sort_by, order_by);
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

    public String outputCountries(String year_start, String year_end, String sort_by, String order_by) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        //ArrayList<> results = jdbc.

        //Open result display area
        html += "<div id='results_display'>";

        //Toggle raw-percentage switch
        html += """
            <div class='toggle_raw_percent'>
                <span>Raw Data - Percentage change toggle: </span>
                <label for='flexSwitchCheckDefault'><img src='img/number_sign.png' style='max-height: 80%;'></label>
                <div class='form-check form-switch'>
                    <input class='form-check-input' type='checkbox' id='percent_toggle'>
                </div>
                <label for='flexSwitchCheckDefault'><img src='img/percent_sign.png' style='max-height: 80%;'></label>
            </div>
                """;

        //Display results

        html +=
        //Close result display area
        html += "</div>";
        return html;
    }

}
