/*
 * Copyright (C) 2023 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2023 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Represents a server using Spark, that takes in requests for path findings for the UW campus.
 */
public class SparkServer {

    /**
     * Stores the CampusMap that helps path findings.
     */
    private static CampusMap map = new CampusMap();

    /**
     * The main method that takes in requests.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().

        // Only for testing purposes
        Spark.get("/hello-world", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                // As a first example, let's just return a static string.
                return "Hello, Spark!";
            }
        });

        // Campus-path finder
        Spark.get("/campus-paths", new Route() {
                    @Override
                    public Object handle(Request request, Response response) throws Exception {
                        String startString = request.queryParams("start");
                        String endString = request.queryParams("end");
                        if (startString == null || endString == null) {
                            Spark.halt(400, "must have start and end");
                        }
                        startString = startString.trim();
                        endString = endString.trim(); // gets rid of leading and trailing spaces
                        if (!map.shortNameExists(startString) || !map.shortNameExists(endString)) {
                            Spark.halt(400, "must have valid start and end short names! Check ");
                        }
                        Path<Point> path = map.findShortestPath(startString, endString);
                        Gson gson = new Gson();
                        String jsonResponse = gson.toJson(path); // converts the path into string (containing JSON) format
                        return jsonResponse;
                    }
                }
        );
    }

}
