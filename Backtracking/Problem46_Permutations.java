package DSA.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Problem46_Permutations {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        int n=nums.length;
        boolean[] exist=new boolean[n];
        List<Integer> temp=new ArrayList<>();
        List<List<Integer>> res=new ArrayList<>();
        getAllPalindrome(nums,exist,temp,res);
        System.out.println("get All permutation "+res);
    }

    private static void getAllPalindrome(int[] nums, boolean[] exist, List<Integer> temp, List<List<Integer>> res) {
        if(temp.size()== nums.length){
            res.add(new ArrayList<>(temp));
            return;

        }
        for(int i=0;i< nums.length;i++){
            if(exist[i]) continue;
            exist[i]=true;
            temp.add(nums[i]);
            getAllPalindrome(nums,exist,temp,res);
            exist[i]=false;
            temp.remove(temp.size()-1);
        }

    }
}
