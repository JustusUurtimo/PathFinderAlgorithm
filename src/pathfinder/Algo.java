/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author uurti
 */
public class Algo {
    private int treesHit = 0;
    
    // code source: https://inginious.org/course/competitive-programming/graphs-maze
    // modified a bit to fit this exercise (refactoring and completing the code)
    
    //the directions we can go from each step
    private int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public ArrayList<int[]> findPaths(int maxY, int maxX, char[][] forest, int[] start, int[] end) {
        // initialize the queue and visited matrix
        Queue<int[]> Q = new LinkedList<>();
        Q.add(start);
        boolean[][] visited = new boolean[maxY][maxX];
        // initialize the parent array
        int[][][] parent = new int[maxY][maxX][];
        visited[start[0]][start[1]] = true;
        
        // we stop when queue is empty or when we have visited the end coordinates
        while (!Q.isEmpty() && !visited[end[0]][end[1]]) {
            
            int[] u = Q.poll();
            // we are now processing node u
            for (int[] d : dir) {

                int i = u[0] + d[0];
                int j = u[1] + d[1];
                // visit the edge from u to (i, j)
                if (i >= 0 && j >= 0 && i < maxY && j < maxX) {
                    
                    //we can't go through rocks and trees and we count the trees hit
                    if (!visited[i][j] && (forest[i][j] == 'X' || forest[i][j] == 'O')) {
                        if(forest[i][j] == 'X') {
                            treesHit++;
                        }
                        visited[i][j] = true;

                    }
                    
                    if (!visited[i][j] && forest[i][j] == '.') {

                        // node (i, j) has not yet been visited and is not a wall, add it
                        Q.add(new int[]{i, j});
                        visited[i][j] = true;
                        // set the parent of (i, j) to be u
                        parent[i][j] = u;
                    }
                }

            }
        }
        
        // check whether a path exists
        if (!visited[end[0]][end[1]]) {
            return null;
        }
        
        // build the path by tracing back from end to start
        ArrayList<int[]> path = new ArrayList<>();
        int[] cur = end;
        
        // loop until we reach start
        while (parent[cur[0]][cur[1]] != null) {
            path.add(cur);
            cur = parent[cur[0]][cur[1]];
        }
        
        // reverse and return the path
        Collections.reverse(path);
        System.out.println("TREES HIT");
        System.out.println(treesHit);
        return path;
    }
}
