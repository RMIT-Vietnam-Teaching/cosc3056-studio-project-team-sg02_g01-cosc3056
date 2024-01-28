package app;

import java.util.ArrayList;

import app.Objects.*;
import app.Page2B.*;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;


/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the 
 * Javalin HTTP Server and our web application.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class App {

    public static final int         JAVALIN_PORT    = 7001;
    public static final String      CSS_DIR         = "css/";
    public static final String      IMAGES_DIR      = "images/";
    public static final String      JAVASCRIPT_DIR  = "javascript/"; // Student add: Added javascript path

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7000
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            
            // Uncomment this if you have files in the CSS Directory
            config.addStaticFiles(CSS_DIR);

            // Uncomment this if you have files in the Images Directory
            config.addStaticFiles(IMAGES_DIR);

            // Student add: Added Javascript folder to Javalin routes
            config.addStaticFiles(JAVASCRIPT_DIR);
        }).start(JAVALIN_PORT);


        // Configure Web Routes
        configureRoutes(app);
        // JDBCConnection jdbc = new JDBCConnection();
        
        

    }

    public static void configureRoutes(Javalin app) {
        // All webpages are listed here as GET pages
        //level 1
        app.get(PageIndex.URL, new PageIndex());
        app.get(PageMission.URL, new PageMission());
        app.get(PageOptions.URL, new PageOptions());
        //level 2A
        app.get(PageST2A_country.URL, new PageST2A_country());
        app.get(PageST2A_world.URL, new PageST2A_world());
        app.get(PageST2A.URL, new PageST2A());
        ///level 2B
        app.get(PageST2B_cities.URL, new PageST2B_cities());
        app.get(PageST2B_states.URL, new PageST2B_states());
        //level 3
        app.get(PageST3A.URL, new PageST3A());
        app.get(PageST3B.URL, new PageST3B());

        // Add / uncomment POST commands for any pages that need web form POSTS
        // app.post(PageIndex.URL, new PageIndex());
        // app.post(PageMission.URL, new PageMission());
        app.post(PageST2A_country.URL, new PageST2A_country());
        app.post(PageST2A_world.URL, new PageST2A_world());
        app.post(PageST2B_cities.URL, new PageST2B_cities());
        app.post(PageST2B_states.URL, new PageST2B_states());
        // app.post(PageST3A.URL, new PageST3A());
        // app.post(PageST3B.URL, new PageST3B());
    }

}
