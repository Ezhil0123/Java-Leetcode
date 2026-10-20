class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length, sum = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k == 0 && check(nums, k)) sum += k;
        }
        return sum;
    }

    private boolean check(int[] a, int k) {
        int n = a.length;
        int prevMax = 0; // nums[i] >= 1
        for (int start = 0; start < n; start += k) {
            int descents = 0;
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = start; i < start + k; i++) {
                min = Math.min(min, a[i]);
                max = Math.max(max, a[i]);
                int next = (i + 1 < start + k) ? a[i + 1] : a[start];
                if (a[i] > next) descents++;
            }
            if (descents > 1) return false;
            if (prevMax > min) return false;
            prevMax = max;
        }
        return true;
    }
}