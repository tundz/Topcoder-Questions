package dynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class ChessMatric {
       public static void main(String[] args)  {
            int[] n = new int[2];
            int[] m = new int[2];
            n[0] = 3;
            n[1] = 7;
            m[0] = 11;
            m[1] = 5;
            //System.out.println(calcNumOfPaths(13, n, m, 14));
            System.out.println(0/9);
       }
       
       public static long calcNumOfPaths(int size, int[] start, int[] end, int numOfMoves) {
           long[][] numOfPaths = new long[size][size];
           long[][] newNumOfPaths;
           numOfPaths[start[0]][start[1]] = 1;
           int [] x = new int [] {1, 0, -1, 0, 1, -1, -1, 1, -2, -2, -1, -1, 1, 1, 2, 2}; 
           int [] y = new int [] {0, 1, 0, -1, 1, 1, -1, -1, -1, 1, -2, 2, -2, 2, -1, 1}; 
           for (int k = 0; k < numOfMoves; k++)  {
               newNumOfPaths = new long[size][size];
                 for (int i = 0; i < size; i++) {
                     for(int j = 0; j < size; j++) {
                       long pathsFound = 0;
                       for (int p = 0; p < x.length; p++) {
                           int checki = i + x[p];  int checkj = j + y[p];
                           if(checki >= 0 && checki < size && checkj >= 0 && checkj < size)
                               pathsFound += numOfPaths[checki][checkj];
                       }
                        newNumOfPaths[i][j] = pathsFound;
                     }
                 }
                numOfPaths = newNumOfPaths;
          }
           return numOfPaths[end[0]][end[1]];
       }
       
       private static void clear(long[][] arr) {
           for (int i = 0; i < arr.length; i++) {
               for (int j = 0; j < arr.length; j++)
                   arr[i][j] = 0;
           }
       }
       
}
