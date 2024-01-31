package app;

import java.util.ArrayList;

import app.Objects.WorldTempPop;
import io.javalin.http.Context;
import io.javalin.http.Handler;


/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='PageIndex.css' />";
        html = html + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'>";
        html = html + "</head>";

        // Open the body
        html = html + "<body>";

        // The topnav/header
        html = html + """
            <header>
                <div class="nav">
                    <a class="logo" href="/"><img src="new-logo.png" alt="logo" /></a>
                    <p>Temperature Time Machine</p>
                    <a class="aboutUs visited" href="mission.html">About Us</a>
                    <a class="getStarted" href="options.html">Options</a>
                </div>
            </header>
        """;

        // Open main
        html = html + "<main>";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<WorldTempPop> worldTempPop = new ArrayList<WorldTempPop>();
        worldTempPop = jdbc.getData1A();

        // Temperature
        html = html + """
            <div class="temperature">
                <h1>GLOBAL AVERAGE TEMPERATURE</h1>
                <div class="info">
                    <div class="rangeYr">
                        <p>
        """;
        
        int yearRange = worldTempPop.get(1).getYear() - worldTempPop.get(0).getYear();
        html = html + "In " + yearRange + " years";

        html = html + """
                        </p>
                        <h2>
                """;
        // Add avg temp
        double tempAvg = worldTempPop.get(1).getTemp() - worldTempPop.get(0).getTemp();

        html = html + tempAvg + "&deg;C";

        html = html + """
                    </h2>
                    </div>
                    <img src="icon-increase.jpg" alt="increase" />
                    <div class="years">
                        <p>
        """;
        html = html + worldTempPop.get(0).getYear();

        html = html + """
                        </p>
                        <h3>
                """;

        // Add temp in year 1750
        html = html + worldTempPop.get(0).getTemp() + "&deg;C";

        html = html + """
                        </h3>
                        <p>
        """;

        html = html + worldTempPop.get(1).getYear();

        html = html + """
                        </p>
                        <h3>
                """;

        // Add temp in year 2015
        html = html + worldTempPop.get(1).getTemp() + "&deg;C";

        html = html + """
                        </h3>
                    </div>
                    <img src="icon-temp.jpg" alt="temperature" />
                </div>
            </div>
        """;

        // option
        html = html + """
            <div class="option">
                <img src="logo-web.jpg" alt="logo" />
                <div class="content">
                    <h3>Climate has changed</h3>
                    <p>
                      We provide recorded data of temperature changes around the world
                      over the past 260 years. Want more detailed info?
                    </p>
                    <a class="getStarted" href="options.html">Get started</a>
                </div>
            </div>    
        """;

        // population
        html = html + """
            <div class="population">
                <h1>GLOBAL POPULATION</h1>
                <div class="info">
                    <div class="rangeYr">
                        <p>
        """;

        yearRange = worldTempPop.get(3).getYear() - worldTempPop.get(2).getYear();
        html = html + "In " + yearRange + " years";

        html = html + """
                        </p>
                        <h2>
                """;

        // Add total population
        long totalPop = worldTempPop.get(3).getPop() - worldTempPop.get(2).getPop();

        if (totalPop > 999999999) {
            html = html + (Math.ceil((totalPop/1000000000.0) * 1000) / 1000) + " BIL";
        }
        else if (totalPop > 999999) {
            html = html + (Math.ceil((totalPop/1000000.0) * 1000) / 1000) + " MIL";
        }
        else
            html = html + totalPop;        

        html = html + """
                        </h2>
                    </div>
                    <img src="icon-increase.jpg" alt="increase" />
                    <div class="years">
                        <p>
        """;

        html = html + worldTempPop.get(2).getYear();

        html = html + """
                        </p>
                        <h3>
                """;

        // Add population in year 1960
        long popYear = worldTempPop.get(2).getPop();

        if (popYear > 999999999) {
            html = html + (Math.ceil((popYear/1000000000.0) * 1000) / 1000) + " bil";
        }
        else if (popYear > 999999) {
            html = html + (Math.ceil((popYear/1000000.0) * 1000) / 1000) + " mil";
        }
        else
            html = html + popYear; 

        html = html + """
                        </h3>
                        <p>
        """;

        html = html + worldTempPop.get(3).getYear();

        html = html + """
                        </p>
                        <h3>
                """;

        // Add population in year 2013
        popYear = worldTempPop.get(3).getPop();

        if (popYear > 999999999) {
            html = html + (Math.ceil((popYear/1000000000.0) * 1000) / 1000) + " bil";
        }
        else if (popYear > 999999) {
            html = html + (Math.ceil((popYear/1000000.0) * 1000) / 1000) + " mil";
        }
        else
            html = html + popYear;

        html = html + """
                        </h3>
                    </div>
                    <img src="icon-population.jpg" alt="temperature" />
                </div>
            </div>
        """;
        
        // Close main
        html = html + "</main>";

        // Footer
        html = html + """
            <footer>
                <a href="/"><img src="new-logo.png" alt="logo" /></a>
                <div class="link">
                  <ul><p>Temperature - Population change</p>
                    <li><a href="page2A_world.html">World</a></li>
                    <li><a href="page2A_country.html">Country</a></li>
                    <li><a href="page2B_states">State</a></li>
                    <li><a href="page2B_cities">City</a></li>
                  </ul>
                  <ul><p>Similarity trends of Temperature - Population</p>
                    <li><a href="page3A.html">Different time periods</a></li>
                    <li><a href="page3B.html">Region with similar trends</a></li>
                  </ul>
                </div>
            </footer>
         """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
}
