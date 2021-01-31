/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author uurti
 */
public class PathFinder {

    private static Algo algo = new Algo();

    public void findPath() throws IOException {

        String coords = "";

        //read the file, not refactored since I use to get coords and Matrix
        char[][] forest = new char[1][1];

        int rows = 0;
        int columns = 0;

        //parse the file into matrix
        try {
            File forestFile = new File("src/pathfinder/forest.txt");
            Scanner scanner = new Scanner(forestFile);
            
            //get coords and the first forest row
            coords += Files.lines(Paths.get("src/pathfinder/forest.txt")).findFirst().get();
            coords += " " + Files.lines(Paths.get("src/pathfinder/forest.txt")).skip(2 - 1).findFirst().get();

            String firstForestLine = Files.lines(Paths.get("src/pathfinder/forest.txt")).skip(3 - 1).findFirst().get();

            //since the map is always quadrilateral we can establish that it is has as many colmuns as the first row is long
            columns = firstForestLine.length();

            long fileRows = Files.lines(Paths.get("src/pathfinder/forest.txt")).count();
            
            //rows are fileRows - 2, since first two are coordinates
            rows = (int) fileRows - 2;
            forest = new char[columns][rows];

            //skip first two lines since they are coordinates
            scanner.nextLine();
            scanner.nextLine();
            
            //We go trough the file and parse each row in to the matrix
            int y = 0;
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                char[] rowChars = row.toCharArray();

                for (int x = 0; x < columns; x++) {
                    forest[x][y] = rowChars[x];

                }
                y++;

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String[] c = coords.split(" ");

        //start and end coords
        int[] startC = {Integer.parseInt(c[0].split(",")[0]), Integer.parseInt(c[0].split(",")[1])};
        int[] endC = {Integer.parseInt(c[1].split(",")[0]), Integer.parseInt(c[1].split(",")[1])};
        

        //find the shortest path (this also prints the hits)
        ArrayList<int[]> path = algo.findPaths(rows, columns, forest, startC, endC);

        //print the results
        printPathAndHits(path, startC, endC);

    }

    public void printPathAndHits(ArrayList<int[]> path, int[] startC, int[] endC) {
        System.out.println("Start");
        System.out.println(Arrays.toString(startC));

        System.out.println("End");
        System.out.println(Arrays.toString(endC));

        System.out.println("PATH");
        if (path == null) {
            System.out.println("No pathfound");
        } else {
            for (int[] pat : path) {
                System.out.println(Arrays.toString(pat));
            }
        }
    }

}
