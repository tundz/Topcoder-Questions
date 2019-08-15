package GraphQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class KiloManGraph {
    static boolean[] visited = new boolean[32768];
     static int leastShots(String[] damageChart, int[] bossHealth)  {
         int numWeapons = bossHealth.length;
         int[][] damagechart = new int [numWeapons][numWeapons];
         for (int i = 0; i < numWeapons; i++) {
             for(int j = 0; j < numWeapons; j++) {
                 damagechart[i][j] = Character.getNumericValue(damageChart[i].charAt(j)); 
             }
         }
         PriorityQueue<Node> pq = new PriorityQueue<Node>();
         pq.add(new Node (0, 0));
         
         while(!pq.isEmpty()) {
             Node top = pq.peek();
             pq.poll();
             //If visited
             if(visited[top.weapons]) continue;
             visited[top.weapons] = true;
             //If final state
             if(top.weapons == (1 << numWeapons) - 1)
                 return top.shots;
             //Transition between states
             for (int i = 0; i< numWeapons; i++) {
                 //If boss is already killed
                 if (((top.weapons >> i) & 1) == 1) continue;
                 int best = bossHealth[i];
                 for (int j = 0; j < numWeapons; j++) {
                     if(i == j) continue;
                     if(((top.weapons << j) & 1) == 1 && damagechart[j][i] != 0) {
                          int shotsneeded = bossHealth[i] / damagechart[j][i];
                          if (bossHealth[i] % damagechart[j][i] != 0) 
                              shotsneeded++;
                          best = Math.min(best, shotsneeded);  
                     }
                 }
                 pq.add(new Node (top.weapons | (1 << i), top.shots + best));
             }     
         }
         return -1;
     }
    
     static class Node implements Comparable<Node>{
         int weapons;
         int shots;
         Node (int _weapons, int _shots) {
             weapons = _weapons;
             shots = _shots;
         }
         @Override
         public int compareTo(Node other) {
             return Integer.compare(shots, other.shots);
         }
         
     }
     
     public static void main (String[] args)  {
         System.out.println(leastShots(new String[] {"1542", "7935", "1139", "8882"}, new int[] {150,150,150,150}));
     }
}
