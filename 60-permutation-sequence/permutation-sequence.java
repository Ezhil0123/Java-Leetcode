class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int fact = 1;
        for (int i = 1; i < n; i++) {
            fact *= i;
            nums.add(i);
        }
        nums.add(n);
        k--;
        StringBuilder ans = new StringBuilder();
        while (!nums.isEmpty()) {
            int index = k / fact;
            ans.append(nums.get(index));
            nums.remove(index);
            if(nums.isEmpty()) {
                break;
            }
            k %= fact;
            fact /= nums.size();
        }
        return ans.toString();
    }
}