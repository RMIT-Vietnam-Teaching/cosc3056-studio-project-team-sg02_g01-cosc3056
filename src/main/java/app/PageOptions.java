package app;

import java.util.ArrayList;

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
public class PageOptions implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/options.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Head information
        html = html + "<head>" + 
               "<title>Get started</title>";

        // CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='PageOptions.css' />";
        html = html + "</head>";

        // Open the body
        html = html + "<body>";

        // The topnav/header
        html = html + """
            <header>
                <div class="nav">
                    <a class="logo" href="/"><img src="logo-web.jpg" alt="logo" /></a>
                    <p>Temperature Time Machine</p>
                    <a class="aboutUs visited" href="mission.html">About Us</a>
                    <a class="getStarted" href="options.html">Options</a>
                </div>
            </header>
        """;

        // Open main
        html = html + "<main>";

        html = html + """
            <p class="title">Options</p>
            <h1 id="Basic">Temperature Time Machine</h1>

            <!-- Basic -->
            <p class="title after">Basic</p>
            <hr>
            <p>
              Check out how temperature has changed throughout the years, whether in
              your city, or around the world.
            </p>
            <p class="select">Select your preferred region type</p>
            <div class="option">
                <!-- box 1 -->
                <a href="page2A_world.html">
                    <div class="box">
                        <img src="logo-web.jpg" alt="" />
                        <h3>World</h3>
                    </div>
                </a>

                <!-- box 2 -->
                <a href="page2A_country.html">
                    <div class="box">
                        <img src="country-logo2.png" alt="" />
                        <h3>Countries</h3>
                    </div>
                </a>

                <!-- box 3 -->
                <a href="page2B_states">
                    <div class="box">
                        <img src="state-logo.png" alt="logo" />
                        <h3 id="Advanced">States</h3>
                    </div>
                </a>

                <!-- box 4 -->
                <a href="page2B_cities">
                    <div class="box">
                        <img src="city-logo.png" alt="logo" />
                        <h3 id="Advanced">Cities</h3>
                    </div>
                </a>
            </div>

            <!-- Advanced -->
            <p class="title after" >Advanced</p>
            <hr>
            <p>
              See the difference between <strong>average temperatures</strong> of different time
              periods or regions that shares trends in varying degrees of similarity
            </p>
            <p class="select">Select your preferred feature</p>
            <div class="option">
                <!-- box 3 -->
                <a href="">
                    <div class="box">
                        <img src="logo-web.jpg" alt="">
                        <h3>Compare changes of different time periods</h3>
                    </div>
                </a>

                <!-- box 4 -->
                <a href="page3B.html">
                    <div class="box">
                        <img src="logo-web.jpg" alt="">
                        <h3>See regions with similar trends</h3>
                    </div>
                </a>
            </div>
        """;
        
        // Close main
        html = html + "</main>";

        // Footer
        html = html + """
            <footer>
                <a href="/"><img src="logo-web.jpg" alt="logo" /></a>
                <div class="link">
                  <ul><p>Temperature - Population change</p>
                    <li><a href="page2A_world.html">World</a></li>
                    <li><a href="page2A_country.html">Country</a></li>
                    <li><a href="page2B_states">State</a></li>
                    <li><a href="page2B_cities">City</a></li>
                  </ul>
                  <ul><p>Similarity trends of Temperature - Population</p>
                    <li><a href="">Different time periods</a></li>
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
