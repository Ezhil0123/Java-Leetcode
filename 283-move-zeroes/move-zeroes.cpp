class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int ins=0;
        for(int num:nums){
            if(num!=0){
                nums[ins++]=num;
            }
        }
        while(ins<nums.size()){
            nums[ins++]=0;
        }
    }
};