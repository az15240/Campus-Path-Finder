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

package marvel;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Parser utility to load the Marvel Comics dataset.
 */
public class MarvelParser {

    /**
     * Reads the Marvel Universe dataset. Each line of the input file contains a character
     * name and a comic book the character appeared in, separated by a comma character
     *
     * @param filename The file that will be read.
     * @spec.requires filename is a valid file in the resources/data folder.
     * @return a map of String-Sring Set pair that contains the data. The first String is the
     *         book name and the String Set is all marvel characters in the book.
     */
    public static Map<String, Set<String>> parseData(String filename) {
        List<String> lines = readLines(filename);
//        System.out.println("readLines successful. printing the top 10 lines:");
//        for (int i = 0; i < 8; i++) {
//            System.out.println(lines.get(i));
//        }
        Map<String, Set<String>> map = new HashMap<>();
        for (String line : lines) {
            String name = line.substring(0, line.indexOf(','));
            String book = line.substring(line.indexOf(',') + 1);
            if (map.containsKey(book)) {
                // if the book name is in the map, then just get it from the map and add our name
                Set<String> names = map.get(book);
                names.add(name);
            } else {
                // if the book name is not in the map, then first construct and initialize it, then
                // add it in the map
                Set<String> names = new HashSet<>();
                map.put(book, names);
                names.add(name);
            }
        }
        System.out.println("Ending parseData!!!!!");
        return map;
    }

    /**
     * Reads all lines contained within the provided data file, which is located
     * relative to the data/ folder in this parser's classpath.
     *
     * @param filename The file to read.
     * @throws IllegalArgumentException if the file doesn't exist, has an invalid name,
                                        or can't be read
     * @return A new {@link List<String>} containing all lines in the file.
     */
    private static List<String> readLines(String filename) {
        // Note:
        // Most students won't re-write this code anywhere, this explanation is just for
        // completeness.
        //
        // You can use this code as an example for getting a file from the resources folder
        // in a project like this. If you access data files elsewhere in your code, you'll
        // need to use similar code. If you use this code elsewhere, don't forget:
        //   - Replace 'MarvelParser' in `MarvelParser.class' with the name of the class you
        //       write this in.
        //   - If the class is in src/main, it'll get resources from src/main/resources
        //   - If the class is in src/test, it'll get resources from src/test/resources
        //   - The "/" at the beginning of the path is important
        InputStream stream = MarvelParser.class.getResourceAsStream("/data/" + filename);
        if (stream == null) {
            // The file doesn't exist. We want to handle this case so we don't try to call
            // readLines and have a null pointer exception.
            throw new IllegalArgumentException("No such file: " + filename);
        }
        return new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.toList());
    }
}
