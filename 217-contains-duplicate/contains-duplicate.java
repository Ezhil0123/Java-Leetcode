class Solution {
    public boolean containsDuplicate(int[] nums) {
        int n=nums.length;
        Map<Integer,Integer> hm=new HashMap<Integer,Integer>();
        for(int i=0;i<n;i++){
            if(hm.containsKey(nums[i])){
                return true;
            }
            else{
                hm.put(nums[i],i);
            }
        }
        return false;
    }
}