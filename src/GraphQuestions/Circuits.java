package GraphQuestions;

import java.util.ArrayList;

public class Circuits {
    static ArrayList<Integer>[] adj;
    static ArrayList<Integer>[] cost;
    static int[] childpath;
    static int[] dist;
    
    static int howLong(String[] connects, String[] costs) {
        createGraph(connects, costs);
        int n = connects.length;
        dist = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            dist[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] == -1) {
                int p = dfs(i);
                if (p > max)
                    max = p;
            }
        }
        return max;
    }
    
    static int dfs (int u) {
        if (adj[u].size() == 0)
            dist[u] = 0;
        if (dist[u] != -1)
            return dist[u];
        for(int i = 0; i < adj[u].size(); i++) {
            int v = adj[u].get(i);
            int edgeCost = cost[u].get(i);
            int p = dfs(v);
            if (p + edgeCost > dist[u])
                dist[u] = p + edgeCost;
        }
       
        
        return dist[u];
    }
    
   static void createGraph (String[] stringGraph, String[] stringCost) {
        int n = stringGraph.length;
        adj = (ArrayList<Integer>[]) new ArrayList[n];
        cost = (ArrayList<Integer>[]) new ArrayList[n];
            
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
            if(!stringGraph[i].equals("")) {
                  String[] neighbors = stringGraph[i].split("\\s+");
                  String[] corrCost = stringCost[i].split("\\s+");
                  for (int j = 0; j < neighbors.length; j++) {
                       int neighbor = Integer.parseInt(neighbors[j]);
                       int corrcost = Integer.parseInt(corrCost[j]);
                       adj[i].add(neighbor);
                       cost[i].add(corrcost);
                             
                  }
            }
        }
    }
    public static void main (String[] args) {
        long count = howLong(new String[]{"","2 3","3 4 5","4 6","5 6","7","5 7",""}, new String[]{"","30 50","19 6 40","12 10","35 23","8","11 20",""} );
        System.out.println (count);
    }
    
}
