class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int ans[]=new int[n];

        int Prol=1;
        int Pror=1;

        for(int i=n-1;i>=0;i--){
            ans[i]=Pror;
            Pror=Pror*nums[i];
        }

        for(int i=0;i<n;i++){
            ans[i]=ans[i]*Prol;
            Prol=Prol*nums[i];
        }

        return ans;
    }
}