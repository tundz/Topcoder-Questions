package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BadNeighbors {
   
    private static int findMaxDonations(List<Integer> donations) {
        int[] maxDons = new int[donations.size() - 1];
        int[] maxDon = new int[donations.size() - 1];
        int highest;
        maxDons[0] = donations.get(0);
        maxDons[1] = donations.get(1);
        for (int i = 2; i < maxDons.length; i++) {
            
            maxDons[i] = donations.get(i);
            highest = Math.max(maxDons[i - 2] + donations.get(i), maxDons[i - 1]);
            if (highest > maxDons[i])
                maxDons[i] = highest;
        }
        highest = maxDons[maxDons.length - 1];
        //System.out.println(highest);
        
        maxDon[0] = donations.get(1);
        maxDon[1] = donations.get(2);
        for (int i = 2; i < maxDons.length; i++)  {
            maxDon[i] = donations.get(i + 1);
            highest = Math.max(maxDon[i - 2] + donations.get(i + 1), maxDon[i - 1]);
            if (highest > maxDon[i])
                maxDon[i] = highest;
        }
        //System.out.println(maxDon[maxDons.length - 1]);
        return Math.max(maxDon[maxDon.length - 1], maxDons[maxDons.length - 1]);
    }
    
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) 
           sequence.add(scanner.nextInt());
        System.out.println(findMaxDonations(sequence));
        
    }
}
