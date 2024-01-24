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
        html = html + "</head>";

        // Open the body
        html = html + "<body>";

        // The topnav/header
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

        // Open main
        html = html + "<main>";

        // Temperature
        html = html + """
            <div class="temperature">
                <h1>GLOBAL AVERAGE TEMPERATURE</h1>
                <div class="info">
                    <div class="rangeYr">
                        <p>In 265 years</p>
                        <h2>
        """;
        // Add avg temp
        html = html + "...";

        html = html + """
                    </h2>
                    </div>
                    <img src="icon-increase.jpg" alt="increase" />
                    <div class="years">
                        <p>1750</p>
                        <h3>
        """;
        // Add temp in year 1750
        html = html + "...";

        html = html + """
                        </h3>
                        <p>2015</p>
                        <h3>
        """;
        // Add temp in year 2015
        html = html + "...";

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
                        <p>In 53 years</p>
                        <h2>
        """;
        // Add total population
        html = html + "...";

        html = html + """
                        </h2>
                    </div>
                    <img src="icon-increase.jpg" alt="increase" />
                    <div class="years">
                        <p>1960</p>
                        <h3>
        """;
        // Add population in year 1960
        html = html + "...";

        html = html + """
                        </h3>
                        <p>2013</p>
                        <h3>
        """;
        // Add population in year 2013
        html = html + "...";

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
                <a href="/"><img src="logo-web.jpg" alt="logo" /></a>
                <div class="link">
                    <a href="">Link</a>
                    <a href="">Link</a>
                    <a href="">Link</a>
                    <a href="">Link</a>
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
