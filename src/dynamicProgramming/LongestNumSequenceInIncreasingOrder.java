package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LongestNumSequenceInIncreasingOrder {
    private static int longNumSequence(List<Integer> numbers) {
        int[] L = new int[numbers.size()];
        for(int i = 0; i < numbers.size(); i++)
            L[i] = 1;
        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (numbers.get(i) > numbers.get(j) && L[j] + 1 > L[i]) {
                    L[i] = L[j] + 1;
                }
            }
        }
        int highest = 0;
        for (int i = 0; i < numbers.size(); i++)
            highest = Math.max(highest, L[i]);
        return highest;
    }
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) 
           sequence.add(scanner.nextInt());
        System.out.println(longNumSequence(sequence));
        scanner.close();
    }
}


