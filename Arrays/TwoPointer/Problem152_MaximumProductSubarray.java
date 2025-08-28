package DSA.Arrays.TwoPointer;

public class Problem152_MaximumProductSubarray {

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};

        int bruteForceResult = bruteForceApproach(nums);
        System.out.println("Brute Force Result: " + bruteForceResult);

        int optimizedResult = optimizeApproach(nums);
        System.out.println("Optimized Result: " + optimizedResult);
    }

    // ---------------- Brute Force ----------------
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    private static int bruteForceApproach(int[] nums) {
        int n=nums.length;
        int finalMax=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int max=1;
            for(int j=i;j<n;j++){
                max*=nums[j];
                finalMax=Math.max(finalMax,max);
            }
        }
        return finalMax;
    }

    // ---------------- Optimized Approach  ----------------
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private static int optimizeApproach(int[] nums) {
        int n=nums.length;
        int finalMax=Integer.MIN_VALUE;
        int pre=1;
        int suf=1;

        for(int i=0;i<n;i++){
            pre*=nums[i];
            suf*=nums[n-i-1];
            finalMax=Math.max(finalMax, Math.max(pre,suf));

            if(pre==0) pre=1;
            if(suf==0) suf=1;
        }
        return finalMax;
    }

}
