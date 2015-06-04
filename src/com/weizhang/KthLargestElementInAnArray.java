package com.weizhang;

/**
 * Created by Wei Zhang on 5/31/15.
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 *
 * For the purpose of practicing three sort algorithm: merge sort, quick sort, heap sort.
 * we'll use these three algorithm to solve the problem
 *
 */
public class KthLargestElementInAnArray {

    // merge sort solution
    public int findKthLargestI(int[] nums, int k) {
        mergeSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    private void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;

        int mid = (lo + hi) / 2;

        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);

        // merge into an aux array
        int[] aux = new int[hi - lo + 1];
        int i = lo;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= hi) {
            if (nums[i] <= nums[j]) {
                aux[k++] = nums[i++];
            } else {
                aux[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            aux[k++] = nums[i++];
        }
        while (j<= hi) {
            aux[k++] = nums[j++];
        }

        // copy back to original array;
        for (int m = lo, n = 0; m <= hi; m++, n++) {
            nums[m] = aux[n];
        }
    }

//TODO: solve it using Heap (Priority Queue)


    public static void main(String[] args) {
        int[] nums = {3,2,7,1,5,6,4};
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();
        int second = test.findKthLargestI(nums, 2);
        int third = test.findKthLargestI(nums, 3);
        int fourth = test.findKthLargestI(nums, 4);
    }
}
