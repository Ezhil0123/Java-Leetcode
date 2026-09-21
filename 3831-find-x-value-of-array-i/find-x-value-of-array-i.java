class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];
        for (int num : nums) {
            int rem = num % k;
            long[] next = new long[k];
            next[rem]++;
            for (int i = 0; i < k; i++) {
                next[(i * rem) % k] += dp[i];
            }
            dp = next;
            for (int i = 0; i < k; i++) {
                result[i] += dp[i];
            }
        }
        return result;
    }
}