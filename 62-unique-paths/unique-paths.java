class Solution {
    public int uniquePaths(int m, int n) {
        int[] prev = new int[n];
        for (int i = 0; i < m; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    curr[j] = 1;
                }
                else {
                    int up = (i > 0) ? prev[j] : 0;
                    int left = (j > 0) ? curr[j - 1] : 0;
                    curr[j] = up + left; 
                }
            }
            prev = curr;
        }
        return prev[n - 1];
    }
}