package GraphQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Marketing {
    static int[] dist;
    static long howMany(String[] stringGraph) {
        ArrayList<Integer>[] graph = createGraph (stringGraph);
        int count = 0;
        int n = graph.length;
        dist = new int[n];
        for (int i = 0; i < n; i++)
            dist[i] = n + 1;
        for (int i = 0; i < graph.length; i++) {
            if (dist[i] == n + 1) {
                boolean isBipartite = checkConnReg(i, graph);
                if (isBipartite) count++;
                else return -1;
            }
                
        }
        return (long) Math.pow(2, (double)count);
    }
    
    static boolean checkConnReg(int p, ArrayList<Integer>[] adj) {
        dist[p] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(p);
        while (!queue.isEmpty()) {
            int k = queue.poll();
            
            for(int i = 0; i < adj[k].size(); i++) {
                if (dist[k] == dist[adj[k].get(i)])
                    return false;
                //If the neighbor hasn't been previously visited
                if (dist[adj[k].get(i)] == adj.length + 1) {
                    queue.add(adj[k].get(i));
                    dist[adj[k].get(i)] = dist[k] + 1;
                }
            }
            
        }
        return true;
    }
    
    static ArrayList<Integer>[] createGraph (String[] stringGraph) {
        int n = stringGraph.length;
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if(!stringGraph[i].equals("")) {
                  String[] neighbors = stringGraph[i].split("\\s+");
                  for ( int j = 0; j < neighbors.length; j++) {
                       int neighbor = Integer.parseInt(neighbors[j]);
                       adj[i].add(neighbor);
                       adj[neighbor].add(i);
                             
                  }
            }
        }
        
        return adj;
    }
    
    public static void  main(String[] args) {
        long count = howMany(new String[]  
                {"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20", "2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20", "3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20", "4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20", "5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20", "6 7 8 9 10 11 12 13 14 15 16 17 18 19 20", "7 8 9 10 11 12 13 14 15 16 17 18 19 20", "8 9 10 11 12 13 14 15 16 17 18 19 20", "9 10 11 12 13 14 15 16 17 18 19 20", "10 11 12 13 14 15 16 17 18 19 20", "11 12 13 14 15 16 17 18 19 20", "12 13 14 15 16 17 18 19 20", "13 14 15 16 17 18 19 20", "14 15 16 17 18 19 20", "15 16 17 18 19 20", "16 17 18 19 20", "17 18 19 20", "18 19 20", "19 20", "20", "", "", "", "", ""}     );
        System.out.println (count);
    }
}
