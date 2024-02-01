package app;


import java.util.ArrayList;

import app.Objects.CityWithCountryCode;
import app.Objects.Countries;
import app.Objects.StateWithCountryCode;
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
public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Compare changes of different time periods</title>";

        html += "<meta charset=\"UTF-8\">";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='Page3A.css' />";
        html = html + "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'>";

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
              <a class="logo" href="/"><img src="new-logo.png" alt="logo" /></a>
              <p>Temperature Time Machine</p>
              <a class="aboutUs visited" href="mission.html">About Us</a>
              <a class="getStarted" href="options.html">Options</a>
          </div>
      </header>
        """;

        // main
        html = html + "<main>";

        // select region form
        html = html + """
            <div class="select_form">
                <h1>Compare changes of different time periods</h1>
            <form class="form" action="" method="">
            <!-- select region -->
            <fieldset>
                <legend><h3>Region level</h3></legend>
            """;
        // radio options
        html = html + """
                <input
                  style="margin-left: 150px"
                  type="radio"
                  name="region_options"
                  id="country"
                  class="region_radio"
                  checked
                />
                <label class="radio_option" for="country">Country</label>
                
                <input type="radio" name="region_options" id="state" class="region_radio"/>
                <label class="radio_option" for="state">State</label>
                
                <input type="radio" name="region_options" id="city" class="region_radio"/>
                <label class="radio_option" for="city">City</label>
                
                <input type="radio" name="region_options" id="world" class="region_radio"/>
                <label class="radio_option" for="world">World</label>
                """;
    
        // dropdown options
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
                </div>
              </div>
            </fieldset>
            <br>
            """;

        // Select Year
        html = html + """
            <fieldset>
              <legend><h3>Number of starting years to compare</h3></legend>
              """;
        
        // Radio Options
        html = html + """
            <input type="radio"name="quantity_options"id="one" checked/>
            <label class="radio_option" for="one">1</label>
            
            <input type="radio" name="quantity_options" id="two" />
            <label class="radio_option" for="two">2</label>
            
            <input type="radio" name="quantity_options" id="three" />
            <label class="radio_option" for="three">3</label>
            
            <input type="radio" name="quantity_options" id="four" />
            <label class="radio_option" for="four">4</label>

            <input type="radio" name="quantity_options" id="five" />
            <label class="radio_option" for="five">5</label>
            """;
        
        //input field
        html = html + """
            <label style="display: block; margin-top: 10px; font-weight: bold;">Start year: </label>

            <div class="field field1">
              <input type="number" name="startYear" id="startYear" min="1750" max="2015"required />
            </div>
            
            <div class="field field2">
              <input type="number" name="startYear2" id="startYear2" min="1750" max="2015"required  />
            </div>

            <div class="field field3">
              <input type="number" name="startYear3" id="startYear3" min="1750" max="2015"required  />
            </div>

            <div class="field field4">
              <input type="number" name="startYear4" id="startYear4" min="1750" max="2015"required  />
            </div>

            <div class="field field5">
              <input type="number" name="startYear5" id="startYear5" min="1750" max="2015"required  />
            </div>
                """;
        //year period
        html = html + """
              <label>
                <h4 style="margin:10px 0 0;">Time period:</h4>
                <input type="number"name="rangeYear"id="rangeYear"min="0"max="265"required/></label>
            </div>
          </fieldset>
          """;
        
          //start period
        html = html + """
            <button>START</button>
                </form>
            </div>
            <hr />
            """;
        //filter button
        html = html + 
        """
            <button class="button fbutton">FILTER</button>
        """;

        //table content
        html = html + 
        """
            <div class="ranking">
                <div class="ranking_maintitle">
                    <h4>Regions Ranking</h4>
                </div>
                <div class="elements">
                    <div class="hierachy">
                        <p>Regions are ranked in descending order</p>  
                    </div>
                    <div class="rank_no">
                        <h5>RANK</h5>
                        <p>1</p>
                        <p>2</p>
                        <p>3</p>
                        <p>4</p>
                        <p>5</p>
                        <p>6</p>
                        <p>7</p>
                        <p>8</p>
                        <p>9</p>
                        <p>10</p>
                    </div>
                    
                    <div class="temp_diff">
                        <div class="col_title">
                        <h5>AVERAGE OF TEMEPERATURE DIFFERENCE</h5>
                        </div>
                        <p>1</p>
                        <p>2</p>
                        <p>3</p>
                        <p>4</p>
                        <p>5</p>
                        <p>6</p>
                        <p>7</p>
                        <p>8</p>
                        <p>9</p>
                        <p>10</p>
                    </div>
                    
                    <div class="temp_diff">
                        <h5>LOCATION</h5>
                        <p>1</p>
                        <p>2</p>
                        <p>3</p>
                        <p>4</p>
                        <p>5</p>
                        <p>6</p>
                        <p>7</p>
                        <p>8</p>
                        <p>9</p>
                        <p>10</p>
                    </div>
                    
                    <div class="temp_diff">
                        <h5>TIME PERIOD</h5>
                        <p>1</p>
                        <p>2</p>
                        <p>3</p>
                        <p>4</p>
                        <p>5</p>
                        <p>6</p>
                        <p>7</p>
                        <p>8</p>
                        <p>9</p>
                        <p>10</p>
                    </div>
                </div>
            </div>    
            </div>      
        """;
        
        //temp box
        html = html + 
        """
                <div class="box">
            <p class="title">Average Temperature change</p>
            <!-- top -->
            <div class="top">
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
        """;

         //inside temp box 
         html = html + 
         """
            <div class="contain">
            <div class="contain__left">
                <p>1750 - 1850 </p>
                <p>USA</p>
                <!-- <img style="width: 220px;" src="area.jpg" alt=""> -->
            </div>
            <!-- middle -->
            <div class="middle">
                <h2>+0.5c</h2>
                <img src="icon-increase.jpg" alt="" />
            </div>
          <div class="temp">
            <div class="temp_left">
              <p>1900</p>
              <p style="margin-bottom: 15px"><strong>5 C</strong></p>
              <p>1750</p>
              <p><strong>10 C</strong></p>
            </div>
            <img src="icon-temp.jpg" alt="" />
          </div>
        </div>
    </div>
            """;

            html += """
            <a class = "TopButton" href = #><i class="fa-solid fa-circle-up"></i></a>
            """;
        // Close main
        html = html + "</main>";
            
        // Footer
        html = html + """
            <footer>
                <a href="/"><img src="new-logo.png" alt="logo"/></a>
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

