package DSA.Arrays.TwoPointer;


import java.util.*;
//"https://leetcode.com/problems/two-sum/description/"
public class TwoSum {
    public static void main(String[] args){
        List<Integer> nums= Arrays.asList(2,7,11,15);
        int target=9;
        System.out.println(bruteForceApproach(nums,target));
        System.out.println(optimizeApproach(nums,target));

    }


    //time complexity ->o(n) for the loop
    //space complexity ->o(n) for the hashmap ->0(n) for the result list if also included
    private static List<Integer> optimizeApproach(List<Integer> nums, int target) {
        int n=nums.size();
        Map<Integer,Integer> mapVal=new HashMap<>();
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<n;i++){
            int k=target-nums.get(i);
            if( mapVal.containsKey(k)){
                res.add(mapVal.get(k));
                res.add(i);
                return res;

            }
            mapVal.put(nums.get(i),i);
        }
        return res;
    }

    //time complexity ->o(n^2) for the loop
    //space complexity ->o(1) no extra space ->0(n) for the result list if also included
    private static List<Integer> bruteForceApproach(List<Integer> nums, int target) {
        int n=nums.size();
        List<Integer> res=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(nums.get(i) + nums.get(j) == target){
                    res.add(i);
                    res.add(j);
                    return res;
                }
            }
        }
        return res;
    }
}
