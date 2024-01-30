package app;

import java.util.ArrayList;

import app.Objects.Countries;
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
public class PageST3B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3B.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>See similar regions</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='Page3B.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <header>
                <div class="nav">
                    <a class="logo" href="/"><img src="logo-web.jpg" alt="logo" /></a>
                    <p>Website name</p>
                    <a class="aboutUs visited" href="mission.html">About Us</a>
                    <a class="getStarted" href="options.html">Options</a>
                </div>
            </header>
        """;

        // Open main
        html = html + "<main>";

        // Select area
        html = html + """
            <div class="select_form">
                <h1>See similar regions</h1>
        """;

        // Open select region
        html = html + """
                <form class="form" action="/page3B.html" method="post">
                    <fieldset>
                        <legend><h3>Region level</h3></legend>
        """;

        // Radio options
        html = html + """
                        <input style="margin-left: 150px" type="radio" name="region_options" id="country" checked />
                        <label class="radio_option" for="country">Country</label>
                        <input type="radio" name="region_options" id="state" />
                        <label class="radio_option" for="state">State</label>
                        <input type="radio" name="region_options" id="city" />
                        <label class="region_options" for="city">City</label>
        """;

        // Open drop-down options
        html = html + """
                        <div class="area">
        """;

        // Country
        html = html + """
                            <div class="country_dropdown">
                                <label for="countrySubmit"><h4>Country</h4></label>
                                <select name="countrySubmit" id="countrySubmit" required>
                                    <option value="" disabled hidden>-Select country-</option>
                               
        """;
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Countries> countries = jdbc.getCountries();
        for (Countries country : countries){
            html = html + String.format("<option value=\"%s\">%s</option>", country.getCode(), country.getName());
        }

        html = html + """
                                </select>
                            </div>
        """;

        // Tui đang suy nghĩ làm sao để khi vừa set cái country là nó sẽ tự chạy 
        // ArrayList của cái state thuộc country đó luôn
        // Nhưng mà tui không biết, cíu!!
        // State
        html = html + """
                            <div class="state_dropdown">
                                <label for="stateSubmit"><h4>State</h4></label>
                                <select name="stateSubmit" id="stateSubmit" required>
                                    <option value="" disabled hidden>-Select state-</option>
                                
        """;

        // Thêm options vào đây nè

        html = html + """
                                </select>
                            </div>
                """;


        // Close main
        html = html + "</main>";
    
        // Footer
        html = html + """
            <footer>
                <a href="/"><img src="logo-web.jpg" alt="logo"/></a>
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
