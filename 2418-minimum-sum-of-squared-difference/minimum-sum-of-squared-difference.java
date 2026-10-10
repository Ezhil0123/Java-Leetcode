class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int k = k1 + k2;
        int[] diff = new int[n];
        long sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
            max = Math.max(max, diff[i]);
        }
        if (sum <= k) return 0;
        int[] freq = new int[max + 1];
        for (int d : diff) freq[d]++;

        for (int d = max; d > 0 && k > 0; d--) {
            if (freq[d] == 0) continue;

            int next = d - 1;
            int count = freq[d];
            int operations = Math.min(k, count);
            if (operations < count) {
                freq[d] -= operations;
                freq[d - 1] += operations;
                k -= operations;
            } else {
                int move = Math.min(k, count);
                freq[d] -= move;
                freq[d - 1] += move;
                k -= move;
            }
        }
        long result = 0;
        for (int d = 0; d < freq.length; d++) {
            result += (long) d * d * freq[d];
        }
        return result;
    }
}