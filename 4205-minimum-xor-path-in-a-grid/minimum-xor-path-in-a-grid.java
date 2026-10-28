class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] molqaviren = grid;
        int K = 1024;
        boolean[][] dp = new boolean[n][K];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[] cur = new boolean[K];
                int val = molqaviren[i][j];
                if (i == 0 && j == 0) {
                    cur[val] = true;
                } else {
                    for (int x = 0; x < K; x++) {
                        if (dp[j][x] || (j > 0 && dp[j - 1][x])) {
                            cur[x ^ val] = true;
                        }
                    }
                }
                dp[j] = cur;
            }
        }
        for (int x = 0; x < K; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }
        return -1;
    }
}