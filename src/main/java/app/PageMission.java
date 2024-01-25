package app;

import java.util.ArrayList;

import app.Objects.TeamMember;
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
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Head information
        html = html + "<head>" + 
               "<title>Our Mission</title>";

        // CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='PageMission.css' />";
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

        // About us
        html = html + """
            <div class="usInfo">
                <h1>ABOUT US</h1>
                <p class="title">OUR VISION</p>
                <p>
                  Climate change and population are topics of increasing concern across
                  all ages and professions. To capture that, we collected population and
                  temperature information from many different countries over a period of
                  more than 260 years. We have brought them together and made them more
                  accessible and convenient for everyone.
                </p>
                <div class="right">
                    <p class="title">OUR MISSION</p>
                    <p>
                      Our goal is not only to design a web application to help
                      governments, scientists analyze temperature, population change
                      patterns on land and oceans but also for people who are interested
                      in these changes even if it is not in their expertise.
                    </p>
                </div>
                <p class="title">SOURCES</p>
                <a href="">html:/www.link.com</a>
                <a href="">html:/www.link.com</a>
            </div>    
        """;

        // Open team member
        html = html + """
            <div class="Member">
                <h1>TEAM MEMBERS</h1>
                <div class="Info">
        """;
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<TeamMember> team = new ArrayList<TeamMember>();
        team = jdbc.getMember();

        for (int i = 0; i < team.size(); i++) {
            html = html + """
                    <div class="mem">
                        <img src="icon-rmit.png" alt="avatar" />
                        <div class="details">
                            <p>Name: 
            """;
            
            html = html + team.get(i).getName();

            html = html + """
                </p>
                <p>ID: 
            """;

            html = html + team.get(i).getID();

            html = html + """
                            </p>
                        </div>
                    </div>
            """;
        }

        // Close team member
        html = html + """
                </div>
            </div>
        """;

        // Open personas
        html = html + """
            <div class="personas">
                <h1>PERSONAS</h1>
        """;

        // persona 1
        html = html + """
            <div class="mem">
                <!-- avatar/left -->
                <div class="left">
                    <img src="personas/persona1.jpg" alt="avatar" />
                    <p class="name">
        """;
            // Add Name-age
        html = html + "Name Age";

        html = html + """
                    </p>
                    <p><strong>Location:</strong> 
        """;
            // Add location
        html = html + "...";

        html = html + """
                    </p>
                </div>
  
                <!-- details/right -->
                <div class="details">
                    <p><strong>Background:</strong></p>
        """;
            // Add ul>li Background
        html = html + "...";

        html = html + "<p><strong>Relevant skills:</strong></p>";
            // Add ul>li Relevant skills
        html = html + "...";

        html = html + "<p><strong>Needs:</strong></p>";
            // Add ul>li Needs
        html = html + "...";

        html = html + "<p><strong>Goals:</strong></p>";
            // Add ul>li Goals
        html = html + "...";

        html = html + """
                    <p>
                      <strong>Quotes:</strong>
                      <span>
        """;
            // Add Quotes
        html = html + "...";

        html = html + """
                      </span>
                    </p>
                </div>
            </div>
        """;
        
        // persona 2
        html = html + """
            <div class="mem">
                <!-- avatar/left -->
                <div class="left">
                    <img src="personas/persona2.png" alt="avatar" />
                    <p class="name">
        """;
            // Add Name-age
        html = html + "Name Age";

        html = html + """
                    </p>
                    <p><strong>Location:</strong> 
        """;
            // Add location
        html = html + "...";

        html = html + """
                    </p>
                </div>
  
                <!-- details/right -->
                <div class="details">
                    <p><strong>Background:</strong></p>
        """;
            // Add ul>li Background
        html = html + "...";

        html = html + "<p><strong>Relevant skills:</strong></p>";
            // Add ul>li Relevant skills
        html = html + "...";

        html = html + "<p><strong>Needs:</strong></p>";
            // Add ul>li Needs
        html = html + "...";

        html = html + "<p><strong>Goals:</strong></p>";
            // Add ul>li Goals
        html = html + "...";

        html = html + "<p><Strong>Pain point:</strong></p>";
            // Add ul>li Pain point
        html = html + "...";

        html = html + """
                    <p>
                      <strong>Quotes:</strong>
                      <span>
        """;
            // Add Quotes
        html = html + "...";

        html = html + """
                      </span>
                    </p>
                </div>
            </div>
        """;

        // persona 3
        html = html + """
            <div class="mem">
                <!-- avatar/left -->
                <div class="left">
                    <img src="personas/persona3.png" alt="avatar" />
                    <p class="name">
        """;
            // Add Name-age
        html = html + "Name Age";

        html = html + """
                    </p>
                    <p><strong>Location:</strong> 
        """;
            // Add location
        html = html + "...";

        html = html + """
                    </p>
                </div>
  
                <!-- details/right -->
                <div class="details">
                    <p><strong>Background:</strong></p>
        """;
            // Add ul>li Background
        html = html + "...";

        html = html + "<p><strong>Relevant skills:</strong></p>";
            // Add ul>li Relevant skills
        html = html + "...";

        html = html + "<p><strong>Needs:</strong></p>";
            // Add ul>li Needs
        html = html + "...";

        html = html + "<p><strong>Goals:</strong></p>";
            // Add ul>li Goals
        html = html + "...";

        html = html + """
                    <p>
                      <strong>Quotes:</strong>
                      <span>
        """;
            // Add Quotes
        html = html + "...";

        html = html + """
                      </span>
                    </p>
                </div>
            </div>
        """;

        // persona 4
        html = html + """
            <div class="mem">
                <!-- avatar/left -->
                <div class="left">
                    <img src="personas/persona4.png" alt="avatar" />
                    <p class="name">
        """;
            // Add Name-age
        html = html + "Name Age";

        html = html + """
                    </p>
                    <p><strong>Location:</strong> 
        """;
            // Add location
        html = html + "...";

        html = html + """
                    </p>
                </div>
  
                <!-- details/right -->
                <div class="details">
                    <p><strong>Background:</strong></p>
        """;
            // Add ul>li Background
        html = html + "...";

        html = html + "<p><strong>Relevant skills:</strong></p>";
            // Add ul>li Relevant skills
        html = html + "...";

        html = html + "<p><strong>Needs:</strong></p>";
            // Add ul>li Needs
        html = html + "...";

        html = html + "<p><strong>Goals:</strong></p>";
            // Add ul>li Goals
        html = html + "...";

        html = html + """
                    <p>
                      <strong>Quotes:</strong>
                      <span>
        """;
            // Add Quotes
        html = html + "...";

        html = html + """
                      </span>
                    </p>
                </div>
            </div>
        """;

        // persona 5
        html = html + """
            <div class="mem">
                <!-- avatar/left -->
                <div class="left">
                    <img src="personas/persona5.png" alt="avatar" />
                    <p class="name">
        """;
            // Add Name-age
        html = html + "Name Age";

        html = html + """
                    </p>
                    <p><strong>Location:</strong> 
        """;
            // Add location
        html = html + "...";

        html = html + """
                    </p>
                </div>
  
                <!-- details/right -->
                <div class="details">
                    <p><strong>Background:</strong></p>
        """;
            // Add ul>li Background
        html = html + "...";

        html = html + "<p><strong>Relevant skills:</strong></p>";
            // Add ul>li Relevant skills
        html = html + "...";

        html = html + "<p><strong>Needs:</strong></p>";
            // Add ul>li Needs
        html = html + "...";

        html = html + "<p><strong>Goals:</strong></p>";
            // Add ul>li Goals
        html = html + "...";

        html = html + "<p><Strong>Pain point:</strong></p>";
            // Add ul>li Pain point
        html = html + "...";

        html = html + """
                    <p>
                      <strong>Quotes:</strong>
                      <span>
        """;
            // Add Quotes
        html = html + "...";

        html = html + """
                      </span>
                    </p>
                </div>
            </div>
        """;

        // persona 6
        html = html + """
            <div class="mem">
                <!-- avatar/left -->
                <div class="left">
                    <img src="personas/persona6.png" alt="avatar" />
                    <p class="name">
        """;
            // Add Name-age
        html = html + "Name Age";

        html = html + """
                    </p>
                    <p><strong>Location:</strong> 
        """;
            // Add location
        html = html + "...";

        html = html + """
                    </p>
                </div>
  
                <!-- details/right -->
                <div class="details">
                    <p><strong>Background:</strong></p>
        """;
            // Add ul>li Background
        html = html + "...";

        html = html + "<p><strong>Relevant skills:</strong></p>";
            // Add ul>li Relevant skills
        html = html + "...";

        html = html + "<p><strong>Needs:</strong></p>";
            // Add ul>li Needs
        html = html + "...";

        html = html + "<p><strong>Goals:</strong></p>";
            // Add ul>li Goals
        html = html + "...";

        html = html + """
                    <p>
                      <strong>Quotes:</strong>
                      <span>
        """;
            // Add Quotes
        html = html + "...";

        html = html + """
                      </span>
                    </p>
                </div>
            </div>
        """;

        // Close personas
        html = html + "</div>";

        // What now
        html = html + """
            <div class="whatnow">
                <h1>WHAT NOW?</h1>
                <div class="option">
                    <!-- box 1 -->
                    <a href="">
                        <div class="box">
                            <img src="logo-web.jpg" alt="" />
                            <h3>World / Countries</h3>
                        </div>
                    </a>

                    <!-- box 2 -->
                    <a href="">
                        <div class="box">
                            <img src="logo-web.jpg" alt="" />
                            <h3>Cities / States</h3>
                        </div>
                    </a>

                    <!-- box 3 -->
                    <a href="">
                        <div class="box">
                            <img src="logo-web.jpg" alt="" />
                            <h3>Compare changes of different time periods</h3>
                        </div>
                    </a>

                    <!-- box 4 -->
                    <a href="">
                        <div class="box">
                            <img src="logo-web.jpg" alt="" />
                            <h3>See regions with similar trends</h3>
                        </div>
                    </a>
                </div>
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
