class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long score = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                score -= nums[i];
            }
            else {
                score += nums[i];
            }
        }
        return score;
    }
}