package app;

import java.util.ArrayList;

import app.Objects.CityWithCountryCode;
import app.Objects.Countries;
import app.Objects.StateWithCountryCode;
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
        String RegionOption = context.formParam("region_options");

        // Add some Head information
        html = html + "<head>" + 
               "<title>See similar regions</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='Page3B.css' />";

        // Add JavaScript to display Cities / States of selected country
        html += """
          <script src='lvl3B_relevantCityState.js'></script>
            """;

        // Close head
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
                        <input style="margin-left: 150px" type="radio" name="region_options" id="country" class="region_radio" value = "Country" checked />
                        <label class="radio_option" for="country">Country</label>
                        <input type="radio" name="region_options" id="state" class="region_radio" value = "State" />
                        <label class="radio_option" for="state">State</label>
                        <input type="radio" name="region_options" id="city" class="region_radio" value = "City"/>
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

        ArrayList<StateWithCountryCode> States = jdbc.getState();
        for (StateWithCountryCode State : States){
            html = html + String.format("<option class=\"%s\"> %s </option>", State.countryCode, State.name);
        }

        html = html + """
                                </select>
                            </div>
                """;

        // City
        html = html + """
                            <div class="city_dropdown">
                                <label for="citySubmit"><h4>City</h4></label>
                                <select name="citySubmit" id="citySubmit" required>
                                     <option value="" disabled hidden>-Select city-</option>
        """;

        ArrayList<CityWithCountryCode> Cities = jdbc.getCity();
        for (CityWithCountryCode City : Cities) {
            html = html + String.format("<option class=\"%s\"> %s </option>", City.countryCode, City.name);
        }

        html = html + """
                                </select>
                            </div>
                """;

        html = html + """
                               </div>
                            </fieldset>
                """;

        
        //Select year
        
        html = html + """
            <div class="select_year">
            <label>
              <h4>Start year:</h4>
              <input
                type="number"
                name="startYear"
                id="startYear"
                min="1750"
                max="2015"
                required
              />
            </label>
            <label>
              <h4>Time period:</h4>
              <input
                type="number"
                name="rangeYear"
                id="rangeYear"
                min="0"
                max="265"
                required
            /></label>
          </div>
                """;

        
        //button
        html = html + """
            <button>START</button>
            </form>
          </div>
          <hr />
                """;

        
        //content
        //Select options
        if (RegionOption != null){
        html = html + """
            <p
            style="
              display: inline;
              margin-right: 250px;
              margin-left: calc(100% - 97.5%);
            "
          >
            Display similarity by:
          </p>
          <input type="radio" name="select_display" id="temp" checked hidden />
          <label class="display" for="temp"><h3>Temperature</h3></label>
          """;
          if (RegionOption.equals("Country")){
              html = html + """
                <input type="radio" name="select_display" id="pop" hidden />
                <label class="display" for="pop"><h3>Population</h3></label>
                <input type="radio" name="select_display" id="both" hidden />
                <label class="display" for="both"><h3>Both</h3></label>
                  """;}
              
            

        //Result for Temperature

        html = html + """
            <div class="box box_temp">
            <p class="title">Average Temperature change</p>
            <!-- top -->
            <div class="top">
              <p>Selected region</p>
              <div class="button">
                <label>
                  <input hidden checked type="radio" name="type_value" id="absolute" />
                  <span></span>
                </label>
                <label class="percen_button">
                  <input hidden type="radio" name="type_value" id="percentage" />
                  <span></span>
                </label>
              </div>
            </div>
    
            <!-- contain -->
            <div class="contain">
              <img class="region" src="area.jpg" alt="" />
    
              <!-- middle -->
              <div class="middle">
                <div class="middle_left">
                  <p>In 10 years</p>
                  <h2>0.13%</h2>
                </div>
                <img src="icon-increase.jpg" alt="" />
              </div>
    
              <!-- temp -->
              <div class="temp">
                <div class="temp_left">
                  <p>1960</p>
                  <p style="margin-bottom: 15px"><strong>C</strong></p>
                  <p>1960</p>
                  <p><strong>C</strong></p>
                </div>
                <img src="icon-temp.jpg" alt="" />
              </div>
            </div>
          </div>
                """;


        //Result for Population
        html = html + """
            <div class="box box_pop">
            <p class="title">Population change</p>
            <!-- top -->
            <div class="top">
              <p>Selected region</p>
              <div class="button">
                <label>
                  <input hidden checked type="radio" name="type_value" id="absolute" />
                  <span></span>
                </label>
                <label class="percen_button">
                  <input hidden type="radio" name="type_value" id="percentage" />
                  <span></span>
                </label>
              </div>
            </div>
    
            <!-- contain -->
            <div class="contain">
              <img class="region" src="area.jpg" alt="" />
    
              <!-- middle -->
              <div class="middle">
                <div class="middle_left">
                  <p>In 10 years</p>
                  <h2>0.13%</h2>
                </div>
                <img src="icon-increase.jpg" alt="" />
              </div>
    
              <!-- temp -->
              <div class="temp">
                <div class="temp_left">
                  <p>1960</p>
                  <p style="margin-bottom: 15px"><strong>C</strong></p>
                  <p>1960</p>
                  <p><strong>C</strong></p>
                </div>
                <img src="icon-temp.jpg" alt="" />
              </div>
            </div>
          </div>
                """;


        //find x
        html = html + """
            <div class="find_x">
            <h3>X the most similar:</h3>
            <div class="select_form">
              <form action="">
                <label style="margin-left: 100px;" for="">Number of results</label>
                <input type="number" name="number_x" id="number_x" min="1" />
              </form>
              <form action="">
                <label for="">Sort by:</label>
                <select name="sort_by" id="sort_by">
                  <option value="">Absolute values</option>
                  <option value="">Relative change in values</option>
                </select>
              </form>
            </div>
    
            <!-- box -->
            <div class="box box_temp">
              <p class="title">Average Temperature change</p>
              <!-- top -->
              <div class="top">
                <p>Similar region #1</p>
                <div class="button">
                  <label>
                    <input hidden checked type="radio" name="type_value" id="absolute" />
                    <span></span>
                  </label>
                  <label class="percen_button">
                    <input hidden type="radio" name="type_value" id="percentage" />
                    <span></span>
                  </label>
                </div>
              </div>
    
              <!-- contain -->
              <div class="contain">
                <img class="region" src="area.jpg" alt="" />
    
                <!-- middle -->
                <div class="middle">
                  <div class="middle_left">
                    <p>In 10 years</p>
                    <h2>0.13%</h2>
                  </div>
                  <img src="icon-increase.jpg" alt="" />
                </div>
    
                <!-- temp -->
                <div class="temp">
                  <div class="temp_left">
                    <p>1960</p>
                    <p style="margin-bottom: 15px"><strong>C</strong></p>
                    <p>1960</p>
                    <p><strong>C</strong></p>
                  </div>
                  <img src="icon-temp.jpg" alt="" />
                </div>
              </div>
            </div>
    
            <div class="box box_pop">
              <p class="title">Population change</p>
              <!-- top -->
              <div class="top">
                <p>Similar region #1</p>
                <div class="button">
                  <label>
                    <input hidden checked type="radio" name="type_value" id="absolute" />
                    <span></span>
                  </label>
                  <label class="percen_button">
                    <input hidden type="radio" name="type_value" id="percentage" />
                    <span></span>
                  </label>
                </div>
              </div>
    
              <!-- contain -->
              <div class="contain">
                <img class="region" src="area.jpg" alt="" />
    
                <!-- middle -->
                <div class="middle">
                  <div class="middle_left">
                    <p>In 10 years</p>
                    <h2>0.13%</h2>
                  </div>
                  <img src="icon-increase.jpg" alt="" />
                </div>
    
                <!-- temp -->
                <div class="temp">
                  <div class="temp_left">
                    <p>1960</p>
                    <p style="margin-bottom: 15px"><strong>C</strong></p>
                    <p>1960</p>
                    <p><strong>C</strong></p>
                  </div>
                  <img src="icon-temp.jpg" alt="" />
                </div>
              </div>
            </div>
    
            <div class="box box_both">
              <!-- top -->
              <div class="top">
                <p>Similar region #1</p>
                <div class="button">
                  <label>
                    <input hidden checked type="radio" name="type_value" id="absolute" />
                    <span></span>
                  </label>
                  <label class="percen_button">
                    <input hidden type="radio" name="type_value" id="percentage" />
                    <span></span>
                  </label>
                </div>
              </div>
    
              <!-- contain -->
              <div class="contain">
                <img class="region" src="area.jpg" alt="" />
    
                <!-- middle -->
                <div class="info_both">
                  <p class="title">Average Temperature change</p>
                  <div class="middle">
                    <div class="middle_left">
                      <p>In 10 years</p>
                      <h2>0.13%</h2>
                    </div>
                    <img src="icon-increase.jpg" alt="" />
                  </div>
                </div>
    
                <div class="infor_both">
                  <p class="title">Population change</p>
                  <div class="middle">
                    <div class="middle_left">
                      <p>In 10 years</p>
                      <h2>0.13%</h2>
                    </div>
                    <img src="icon-increase.jpg" alt="" />
                  </div>
                </div>
    
                <!-- temp -->
                <div class="temp">
                  <div class="temp_left">
                    <p>1960</p>
                    <p style="margin-bottom: 15px"><strong>C</strong></p>
                    <p>1960</p>
                    <p><strong>C</strong></p>
                  </div>
                  <img src="icon-temp.jpg" alt="" />
                </div>
              </div>
            </div>
          </div>
        """;
        }

        // Close main
        html = html + "</main>";
    
        // Footer
        html = html + """
            <footer>
                <a href="/"><img src="logo-web.jpg" alt="logo"/></a>
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
