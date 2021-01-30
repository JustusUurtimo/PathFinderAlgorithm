/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @author uurti
 */
public class Main {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) throws IOException, Exception {
        
        PathFinder pathFinder = new PathFinder();
        pathFinder.findPath();

    }

    public static void print2D(String mat[][]) {
        // Loop through all rows 
        for (String[] row : mat) // converting each row as string 
        // and then printing in a separate line 
        {
            System.out.println(Arrays.toString(row));
        }
    }

}
