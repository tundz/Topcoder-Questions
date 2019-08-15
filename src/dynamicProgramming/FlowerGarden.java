package dynamicProgramming;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class flower implements Comparable<flower>{
    int height;
    int grow;
    int wilt;
    
    public flower (int Height, int Grow, int Wilt) {
        height  = Height;
        grow = Grow;
        wilt = Wilt;
    }
    public int compareTo (flower y) {
         if (this.height > y.height) {
             if(this.wilt < y.grow || this.grow > y.wilt)
                 return -1;
             else
                 return 1;
         }
         
         else if (this.height < y.height) {
              if(y.wilt < this.grow || y.grow > this.wilt)
                  return 1;
              else
                  return -1;
         }
         
         return 0;
    }
}
   
class FlowerGarden {
    
    
    public static int[] getOrdering(flower[] flowers)  {
        Arrays.sort(flowers);
        int[] result = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            result[i] = flowers[i].height;
        }
        return result;
    }
  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        flower[] flowers = new flower[n];
        for (int i = 0; i < n; i++) 
           flowers[i] = new flower (scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        int[] result = (getOrdering(flowers));
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        scanner.close();
    }
}
