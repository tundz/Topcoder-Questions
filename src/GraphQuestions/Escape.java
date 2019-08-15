package GraphQuestions;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Escape {
    public static void main (String[] args)  {
        System.out.println(lowest(new String[]  {"100 400 100 0", "400 500 200 100", "500 200 100 0", "400 300 100 400", "300 200 0 500", "100 400 200 0", "0 500 100 400", "500 200 200 300", "0 300 100 400", "500 400 500 0", "300 0 500 500", "500 300 400 100", "500 200 400 500", "400 100 0 300", "400 400 300 100", "400 500 400 0", "500 500 200 400"}, new String[]{"165 250 210 185", "40 180 405 165", "435 320 5 275", "445 195 65 370", "235 235 355 55", "410 10 190 195"}));
    }

    public static class Node implements Comparable<Node> {
        int X, Y;
        int damage;
        
        Node (int x, int y, int _damage) {
            X = x;
            Y = y;
            damage = _damage;
        }
        public int compareTo(Node other) {
            return Integer.compare(damage, other.damage);
        }
    }
    
    public static int lowest(String[] harmful, String[] deadly) {
        int[][] grid = addHarmfulAndDeadlyRegions (harmful, deadly);
        
        boolean[][] visited = new boolean[501][501];
        for (int i = 0; i <= 500; i++) {
            for (int j = 0; j <= 500; j++) 
                visited[i][j] = false;
        }
        Node start = new Node (0, 0, 0);
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        q.add(start);
        int[] addX = {-1, 0, 0, 1};
        int[] addY = {0, -1, 1, 0};
        int newX, newY;
        while (!q.isEmpty())  {
            Node top = q.poll();
            if(visited[top.X][top.Y])
                continue;
            visited[top.X][top.Y] = true;
            
            //If We've reached 500, 500
            if (top.X == 500 && top.Y == 500)
                return top.damage;
            //For all states reachable from start
            for (int i = 0; i < 4; i++) {
                 newX = top.X + addX[i];
                 newY = top.Y + addY[i];
                 //Check if its outside board or on deadly region
                 if (newX < 0 || newX > 500 || newY < 0 || newY > 500 || grid[newY][newX] == -1)
                     continue;
                 Node newNode;
                 if (grid[newY][newX] == 1)
                     newNode = new Node (newX, newY, top.damage + 1);
                 else
                     newNode = new Node (newX, newY, top.damage);
                 q.add(newNode);
            }
            
        }
        
        return -1;
    }
    
    private static int[][] addHarmfulAndDeadlyRegions (String[] harmful, String[] deadly) {
        int[][] grid = new int[501][501];
        String[] reg;
        int[] region = new int[4];
        int X1, X2, Y1, Y2;
        //Add harmful Regions
        for(int i = 0; i < harmful.length; i++) {
            reg = harmful[i].split("\\s+");
            for (int j = 0; j < 4; j++)
                region[j] = Integer.parseInt(reg[j]);
            
                
            X1 = Math.min(region[0], region[2]);
            X2 = Math.max(region[0], region[2]);
            Y1 = Math.min(region[1], region[3]);
            Y2 = Math.max(region[1], region[3]);
            
            for (int k = X1; k <= X2; k++) {
                for (int p = Y1; p <= Y2; p++) 
                    grid[p][k] = 1;
            }
        }
        
        //Add Deadly Regions
        for(int i = 0; i < deadly.length; i++) {
            reg = deadly[i].split("\\s+");
            for (int j = 0; j < 4; j++)
                region[j] = Integer.parseInt(reg[j]);
            
            X1 = Math.min(region[0], region[2]);
            X2 = Math.max(region[0], region[2]);
            Y1 = Math.min(region[1], region[3]);
            Y2 = Math.max(region[1], region[3]);
            
            for (int k = X1; k <= X2; k++) {
                for (int p = Y1; p <= Y2; p++) 
                    grid[p][k] = -1;
            }
        }
        
        return grid;
    }
}
