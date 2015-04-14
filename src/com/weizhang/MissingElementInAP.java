package com.weizhang;

import java.util.Scanner;

/**
 * Created by Wei Zhang on 4/7/15.
 *
 * Missing element in an AP (arithmetic progression), Only one element is missing
 *
 */
public class MissingElementInAP {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int count = s.nextInt();
        int[] nums = new int[count];

        int i = 0;
        while (i < count) {
            nums[i] = s.nextInt();
            i++;
        }
        s.close();

        int a1 = 0, a2 = 0, a = 0, ai;
        int x1 = 0, x2 =0;
        for (i = 0; i < count - 1; i++) {
            ai = nums[i+1] - nums[i];

            if (ai == a1 || ai == a2)
                a = ai;

            if (a1 != 0 && a2 != 0 && a != 0) {
                break;
            }

            if (a1 == 0) {
                a1 = ai;
                x1 = i;
            } else if (a2 == 0 && ai != a1) {
                a2 = ai;
                x2 = i;
            }

        }
        if (a1 != a) {
            System.out.println(nums[x1] + a);
        } else if (a2 != a){
            System.out.println(nums[x2] + a);
        }


    }
}
