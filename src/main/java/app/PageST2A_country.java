package app;

import java.util.ArrayList;

import app.Objects.*;
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
public class PageST2A_country implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2A_country.html";

    @Override
    public void handle(Context context) throws Exception {

        // JDBC object
        JDBCConnection jdbc = new JDBCConnection();

        // Create a simple HTML webpage in a String
        String html = "<html>";
        
        // HTML form submitted inputs' values
        String year_start = context.formParam("year_start");
        String year_end = context.formParam("year_end");
        String sort_by = context.formParam("sort_by");
        String order_by = context.formParam("order_by");
        
        // Add some Head information
        html = html + "<head>" + 
               """
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>lvl2A Countries</title>
                       """;

        // Add some CSS (external file)
        html = html + 
        """
            <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>
            <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>
            <link href='LVL2-A.css' rel='stylesheet'>
            <link href='common.css' rel='stylesheet'>
            <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'>
            <script src='lvl2.js'></script>
            <script src='lvl2_yearRangeCheck.js'></script>
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
                    <a class="logo" href="/"><img src="new-logo.png" alt="logo" /></a>
                    <p>Temperature Time Machine</p>
                    <a class="aboutUs visited" href="mission.html">About Us</a>
                    <a class="getStarted" href="options.html">Options</a>
                </div>
            </header>
        """;

        //Open main
        html += "<main>";


        //Open Main_top - Top options
        html += """
            <div class='main_section' id='main_section_top'>
            """;
            
        //Region selector
        html += """
            <!-- Region selector -->
            <div id="region_select" style="padding: 20px; border-bottom: 3px solid grey;">
                <div class="region_boxes">
                    <a href="page2A_world.html">
                        <div class="box">
                            <img src="logo-web.jpg" alt="world">
                            <h5>World</h5>
                        </div>
                    </a>
                    <a href="page2A_country.html" class="select">
                        <div class="box">
                            <img src="country-logo2.png" alt="countries">
                            <h5>Countries</h5>
                        </div>
                    </a>
                    <a href="page2B_states">
                        <div class="box">
                            <img src="state-logo.png" alt="states">
                            <h5>States</h5>
                        </div>
                    </a>
                    <a href="page2B_cities">
                        <div class="box">
                            <img src="city-logo.png" alt="cities">
                            <h5>Cities</h5>
                        </div>
                    </a>
                </div>
            </div>
                """;

        //Open top options
        html += """
                <div id='top_options'>
                    <!-- Input fields -->
                    <div id="input_fields">
                        <!-- Form lvl2A open -->
                        <form id='lvl2A' action='/page2A_country.html' method='post'>
                        <div id="page_title" style="padding-bottom: 30px;">
                            <h6>Basic</h6>
                            <h1>Countries</h1>
                        </div>
                        """;
        //Start row 1 - Year range      
        html += """
                        <!-- Row 1: Year range -->
                        <div class="input_row row_1">
                            <!-- Label -->
                            <div class="input_label">
                                <label for="year_start">Year range: </label>
                            </div>
                            <!-- Options area -->
                            <div class="input_area">
                                <!-- Starting year dropdown -->
                                <div class="year_range start">
                                    <select id='year_start' name='year_start' class="dropdown_top" form='lvl2A'>
                    """;
                    //"Choose" default option
                    html += "<option disabled";
                    //Only set this option as "selected" when no previous year was chosen (aka when the user just enter the page)
                    if (year_start == null){
                        html += " selected";
                    }
                    html += ">Choose</option>";
                    //Start year options (ADD ME)
                    ArrayList<Integer> startYears = jdbc.getYear();
                    for (int year : startYears) {
                        if (year_start != null && year == Integer.parseInt(year_start)) {
                            //Let the previously chosen option as the default selected option
                            html += String.format("<option selected>%d</option>", year);
                        }
                        else {
                            html += String.format("<option>%d</option>", year);
                        }
                    }
            //Close start year select + Open end year select
            html += """
                </select>
                </div>
                <span>&ensp;to&ensp;</span>
                <!-- Starting year dropdown -->
                <div class="year_range end">
                    <select id='year_end' name='year_end' class="dropdown_top" form='lvl2A' disabled>
                    """;
                    //"Choose" default option
                    html += "<option disabled";
                    //Only set this option as "selected" when no previous year was chosen (aka when the user just enter the page)
                    if (year_end == null){ 
                        html += " selected";
                    }
                    html += ">Choose</option>";
                    //End year options (ADD ME)
                    ArrayList<Integer> endYears = jdbc.getYear();
                    for (int year : endYears) {
                        if (year_end != null && year == Integer.parseInt(year_end)) {
                            //Let the previously chosen option as the default selected option
                            html += String.format("<option selected>%d</option>", year);
                        }
                        else {
                            html += String.format("<option>%d</option>", year);
                        }
                    }
            //Close year end select + Row 1 divs
            html += """
                            </select>
                        </div>
                    </div>
                </div>
                    """;

        //Start row 2 - Sort by
            html += """
                <!-- Row 2: Sort by -->
                <div class="input_row row_2">
                    <!-- Label -->
                    <div class="input_label">
                        <label for="">Sort by: </label>
                    </div>
                    <!-- Options area -->
                    <div class="input_area">
                        <!-- Sort by dropdown -->
                        <select id='sort_by' name='sort_by' class="dropdown_top" form='lvl2A'>
                    """;
            //sort_by options
            String[] sortOptions = {"Temperature change", "Population change"};
            for (String option: sortOptions) {
                html += "<option";
                //Let the previously chosen option as the default selected option
                if (sort_by != null && sort_by.equals(option)) {
                    html += " selected";
                }
                html += String.format(">%s</option>", option);
            }     
            
            //Close row 2
            html += """
                        </select>
                    </div>
                </div>
                    """;
        
        //Start row 3 - Order by
            html += """
                <div class="input_row row_3">
                    <!-- Label -->
                    <div class="input_label">
                        <label for="">Order by: </label>
                    </div>
                    <!-- Option area -->
                    <div class="input_area">
                        <select id='order_by' name='order_by' class="dropdown_top" form='lvl2A'>
                    """;
            //Order by options
            html += """
                    <option value='asc'>Ascending</option>
                    <option value='desc'
                    """;
            //Let the previously chosen option as the default selected option
            if (order_by != null && order_by.equals("desc")) {
                html += " selected";
            }                
            html += ">Descending</option>";

            //Close row 3
            html += """
                        </select>
                    </div>
                </div>
                    """;
        
        //Submit button + Close input field
        html += """
                <button type='submit' form='lvl2A' class='btn btn-success' disabled>Choose year range</button>
            </form>
        </div>
                """;
        
        //Close Main_top - Top options
        html += """
                </div>
            </div>
                """;

        //Open Main_bottom 
        html += "<div class='main_section' id='main_section_bottom'>";
        // //Bottom options
        // html += """
        //     <div id='bottom_options'>
        //         <div style='display: flex; justify-content: space-between;'>
        //             <div id='display_by'>
        //                 <span style='visibility: hidden;'>Blank</span>
        //                 <span><b>Display results by: </b></span>
        //                 <a href='page2A_country.html' class='display_current'>Country</a>
        //                 <a href='page2A_world.html' class='display_other'>World</a>
        //             </div>
        //             <div id='sort_options'>
        //                 <div class='dropdown_container'>
        //                     <div class='sort_by_options'>
        //                         <label for='sort_by'>Sort by: </label>
        //                         <select id='sort_by' name='sort_by' form='lvl2A'>
        //                         """;
        // //sort_by options
        // String[] sortOptions = {"Temperature change", "Population change"};
        // for (String option: sortOptions) {
        //     html += "<option";
        //     //Let the previously chosen option as the default selected option
        //     if (sort_by != null && sort_by.equals(option)) {
        //         html += " selected";
        //     }
        //     html += String.format(">%s</option>", option);
        // }

        // html += """
        //                         </select>
        //                     </div>
        //                     <div class='order_by_options' style='margin-top: 5px;'>
        //                         <label for='order_by'>Order by: </label>
        //                         <select id='order_by' name='order_by' form='lvl2A'>
        //                             """;

        // //order_by options
        // html += """
        //         <option value='asc'>Ascending</option>
        //         <option value='desc'
        //         """;
        // //Let the previously chosen option as the default selected option
        // if (order_by != null && order_by.equals("desc")) {
        //     html += " selected";
        // }                
        // html += ">Descending</option>";

        // html += """
        //                         </select>
        //                     </div>
        //                 </div>
        //             </div>
        //         </div>
        //         <button type='submit' form='lvl2A' class='btn btn-success'>Submit</button>
        //     </div>
        //         """;


        if (year_start == null) {
            //No inputs, no result list
            html += "<h2><i>(Please select your options from above and click Submit)</i></h2>";

            // Close main_bottom
            html += "</div>";

        }
        else {
            html += outputCountries(year_start, year_end, sort_by, order_by);
        }
            
        
        // Close main
        html += "</main>";
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
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String outputCountries(String year_start, String year_end, String sort_by, String order) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<TempPopDataCountry2A> results = jdbc.getTempPopCountry2A(year_start, year_end, sort_by, order);
        
        boolean sortPop = (sort_by.toLowerCase().contains("population"));

        //Open result display area
        html += "<div id='results_display'>";

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
        for (TempPopDataCountry2A result : results) {

        html += """
            <div class='result_container carousel carousel-dark slide' style='border: solid' id=
            """
                + "'" + result.id + "'" +
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
                            + result.countryName +
                        """
                            </div>
                        <div class='topic'>Average <b>Temperature</b> change</div>
                        <div style='visibility: hidden;'>Blank</div>
                    </div>
                    <div class='result_row_2'>
                        <div class='num_difference'>
                            <div>In
                            """
                                + " " + (result.EndYear - result.StartYear) + " " +
                            """
                                years</div>
                            <div class='data_num uncheck'>
                                <div class='change_value'>
                                """
                                    + String.format("%.2f", result.TempDifference) +
                                """
                                    <span class='change_unit'>°C</span></div>
                            </div>
                            <div class='data_percent uncheck'>
                                <div class='change_value'>
                                """
                                    + String.format("%.2f", result.TempDifferencePercent) +
                                """
                                    <span class='change_unit'>%</span></div>
                            </div>
                        </div>
                        <img src=
                        """
                            + (result.TempDifference > 0?"icon-increase.jpg":"icon-decrease.jpg") +
                        """
                            class='trend'>
                        <div class='year_data'>
                            <div class='end_year'>
                                <div class='year'>
                                """
                                    + result.EndYear +
                                """
                                    </div>
                                <div class='data'>
                                """
                                    + (result.EndTemp == 0 ? "No Data" : result.EndTemp) +
                                """
                                    °C</div>
                            </div>
                            <p></p>
                            <div class='start_year'>
                                <div class='year'>
                                """
                                    + result.StartYear +
                                """
                                    </div>
                                <div class='data'>
                                """
                                    + (result.StartTemp == 0 ? "No Data" : result.StartTemp) +
                                """
                                    °C</div>
                            </div>
                        </div>
                        <img src='icon-temp.jpg' height='100px'>
                    </div>
                    <div class='result_row_3' style="display: flex; justify-content: center;">
                        """;
                
                html += String.format("""
                        In %d years here, on average, every %.2e people %s corresponds to 1°C %s.
                        """, result.EndYear - result.StartYear, Math.abs(result.PopDifference / result.TempDifference),
                        (result.PopDifference > 0?"increase":"decrease"), (result.TempDifference > 0?"increase":"decrease"));        
                        
                html += """
                    </div>
                </div>
                <div class='pop_slide carousel-item
                """
                    + (sortPop?" active":"") + "'>" +
                """
                    <div class='result_row_1'>
                        <div class='name'>
                        """
                            + result.countryName +
                        """
                            </div>
                        <div class='topic'><b>Population</b> change</div>
                        <div style='visibility: hidden;'>Blank</div>
                    </div>
                    <div class='result_row_2'>
                        <div class='num_difference'>
                            <div>In
                            """
                                + " " + (result.EndYear - result.StartYear) + " " +
                            """
                                years</div>
                            <div class='data_num uncheck'>
                                <div class='change_value'>
                                """
                                    + String.format("%,d", result.PopDifference) +
                                """
                                    </div>
                                <div class='change_unit'>people</div>
                            </div>
                            <div class='data_percent uncheck'>
                                <div class='change_value'>
                                """
                                    + String.format("%.2f", result.PopDifferencePercent) +
                                """
                                    <span class='change_unit'>%</span></div>
                            </div>
                        </div>
                        <img src=
                        """
                            + (result.PopDifference > 0?"icon-increase.jpg":"icon-decrease.jpg") +
                        """
                            class='trend'>
                        <div class='year_data'>
                            <div class='end_year'>
                                <div class='year'>
                                """
                                    + result.EndYear +
                                """
                                    </div>
                                <div class='data'>
                                """
                                    + (result.EndPop == 0 ? "No Data" : String.format("%,d", result.EndPop)) +
                                """
                                    </div>
                                <div class='unit'>people</div>
                            </div>
                            <p></p>
                            <div class='start_year'>
                                <div class='year'>
                                """
                                    + result.StartYear +
                                """
                                    </div>
                                <div class='data'>
                                """
                                    + (result.StartPop == 0 ? "No Data" : String.format("%,d", result.StartPop)) +
                                """
                                    </div>
                                <div class='unit'>people</div>
                            </div>
                        </div>
                        <img src='icon-population.jpg' height='100px'>
                    </div>
                    <div class='result_row_3' style="display: flex; justify-content: center;">
                    """;
            
            html += String.format("""
                    In %d years here, on average, every %.2e people %s corresponds to 1°C %s.
                    """, result.EndYear - result.StartYear, Math.abs(result.PopDifference / result.TempDifference),
                    (result.PopDifference > 0?"increase":"decrease"), (result.TempDifference > 0?"increase":"decrease"));        
                    
            html += """
                </div>
                </div>
                <button class='carousel-control-prev' type='button' data-bs-target=
                """
                    + "'#" + result.id + "'" +
                """
                    data-bs-slide='prev'>
                    <span class='carousel-control-prev-icon' aria-hidden='true'></span>
                    <span class='visually-hidden'>Previous</span>
                </button>
                <button class='carousel-control-next' type='button' data-bs-target=
                """
                    + "'#" + result.id + "'" +
                """
                    data-bs-slide='next'>
                    <span class='carousel-control-next-icon' aria-hidden='true'></span>
                    <span class='visually-hidden'>Next</span>
                </button>
            </div>
        </div>
                """;
        }
        
        //Close result display area
        html += "</div>";
        
        // Close main_bottom
        html += "</div>";
        
        html += """
            <a class = "TopButton" href = #><i class="fa-solid fa-circle-up"></i></a>
            """;
    
        return html;
    }

}
