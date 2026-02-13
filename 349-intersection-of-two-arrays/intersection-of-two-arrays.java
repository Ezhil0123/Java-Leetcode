class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1=new HashSet<>();
        Set<Integer> result=new HashSet<>();

        for(int nums:nums1){
            set1.add(nums);
        }

        for(int nums:nums2){
            if(set1.contains(nums)){
                result.add(nums);
            }
        }

        int[] res=new int[result.size()];
        int i=0;
        for(int nums:result){
            res[i++]=nums;
        }

        return res;
    }
}