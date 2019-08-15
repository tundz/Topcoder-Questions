package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZigZag {
    
    public static int zigzag (int[] numbers) {
         int[] longseq = new int[numbers.length];
         int[] sign = new int[numbers.length];
         longseq[0] = 1;
         for (int i = 1; i < numbers.length; i++) {
             sign[i] = numbers[i] - numbers[0];
             longseq[i] = 2;
         }
         for(int i = 2; i < numbers.length; i++) {
               for (int j = i - 1; j >= 1; j--) {
                   if(numbers[i] - numbers[j] > 0 && sign[j] < 0 || numbers[i] - numbers[j] < 0 
                           && sign[j] > 0) {
                        if (longseq[j] + 1 > longseq[i]) {
                            longseq[i] = longseq[j] + 1;
                            sign[i] = numbers[i] - numbers[j];
                        }
                        break;
                   }
               }
         }
         
        return longseq[numbers.length - 1];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) 
           sequence[i] = scanner.nextInt();
        System.out.println(zigzag(sequence));
        scanner.close();
    }
}
