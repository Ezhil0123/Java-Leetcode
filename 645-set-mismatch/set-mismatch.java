class Solution {
    public int[] findErrorNums(int[] nums) {
        long n = nums.length;
        long expectedSum = n * (n + 1) / 2;
        long expectedSqSum = n * (n + 1) * (2 * n + 1) / 6;
        long actualSum = 0;
        long actualSqSum = 0;
        for (int num : nums) {
            actualSum += num;
            actualSqSum += (long) num * num;
        }
        long diff = actualSum - expectedSum;
        long sqDiff = actualSqSum - expectedSqSum;
        long sum = sqDiff / diff;
        int repeating = (int) ((diff + sum) / 2);
        int missing = (int) (sum - repeating);
        return new int[]{repeating, missing};
    }
}