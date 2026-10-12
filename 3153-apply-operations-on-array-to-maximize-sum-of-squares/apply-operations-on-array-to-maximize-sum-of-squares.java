class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] cnt = new int[30];
        for (int x : nums) {
            for (int b = 0; b < 30; b++) {
                if ((x >> b & 1) == 1) cnt[b]++;
            }
        }
        long MOD = 1_000_000_007L;
        long res = 0;
        for (int i = 0; i < k; i++) {
            long cur = 0;
            for (int b = 0; b < 30; b++) {
                if (cnt[b] > 0) {
                    cur |= (1L << b);
                    cnt[b]--;
                }
            }
            res = (res + (cur % MOD) * (cur % MOD)) % MOD;
        }
        return (int) res;
    }
}