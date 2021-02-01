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
    private String coords = "";

    //read the file, not refactored since I use to get coords and Matrix
    private char[][] forest = new char[1][1];

    private int rows = 0;
    private int columns = 0;

    public void findPath() throws IOException {
        
        //parse the file and get inforamtion
        parseFile();

        String[] startAndEnd = coords.split(" ");

        //start and end coords
        int[] startC = {Integer.parseInt(startAndEnd[0].split(",")[0]), Integer.parseInt(startAndEnd[0].split(",")[1])};
        int[] endC = {Integer.parseInt(startAndEnd[1].split(",")[0]), Integer.parseInt(startAndEnd[1].split(",")[1])};

        //find the shortest path (this also prints the amount of trees we have walked by)
        ArrayList<int[]> path = algo.algo(rows, columns, forest, startC, endC);

        //print the results
        printPathAndHits(path, startC, endC);

    }

    private void parseFile() throws IOException {
        //parse the file into matrix
        try {
            File forestFile = new File("src/pathfinder/forest.txt");
            Scanner scanner = new Scanner(forestFile);

            //get coords
            coords += scanner.nextLine();
            coords += " " + scanner.nextLine();
            
            //now get the first line of the forest ( but we dont want to skip over it)
            String firstForestLine = Files.lines(Paths.get("src/pathfinder/forest.txt")).skip(3-1).findFirst().get();

            //since the map is always quadrilateral we can establish that it is has as many colmuns as the first row is long
            columns = firstForestLine.length();

            long fileRows = Files.lines(Paths.get("src/pathfinder/forest.txt")).count();

            //rows are fileRows - 2, since first two are coordinates
            rows = (int) fileRows - 2;
            System.out.println(rows);
            forest = new char[columns][rows];

            //skip first two lines since they are coordinates
            
            

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
            System.out.println("Please make sure that the file is in the same directory as the program and that it is named forest.txt");
            e.printStackTrace();
        }
    }

    private void printPathAndHits(ArrayList<int[]> path, int[] startC, int[] endC) {
        System.out.println("Start");
        System.out.println(Arrays.toString(startC));

        System.out.println("End");
        System.out.println(Arrays.toString(endC));

        System.out.println("Path taken");
        if (path == null) {
            System.out.println("No pathfound");
        } else {
            for (int[] pat : path) {
                System.out.println(Arrays.toString(pat));
            }
        }
    }

}
