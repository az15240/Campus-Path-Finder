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

package pathfinder;

import pathfinder.datastructures.Path;

import java.util.Map;

/**
 * TODO: comment
 * @param <E> tbd
 */
public class CampusMap<E> implements ModelAPI<E> {

    @Override
    public boolean shortNameExists(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        throw new RuntimeException("Not Implemented Yet");
    }

    @Override
    public String longNameForShort(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        throw new RuntimeException("Not Implemented Yet");
    }

    @Override
    public Map<String, String> buildingNames() {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        throw new RuntimeException("Not Implemented Yet");
    }

    @Override
    public Path<E> findShortestPath(String startShortName, String endShortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        throw new RuntimeException("Not Implemented Yet");
    }

}
