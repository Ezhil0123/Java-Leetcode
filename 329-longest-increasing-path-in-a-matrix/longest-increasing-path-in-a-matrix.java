class Solution {

    private int[][] dp;
    private int[][] matrix;
    private int m, n;

    private final int[][] dirs = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        dp = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int r, int c) {
        if (dp[r][c] != 0)
            return dp[r][c];

        int best = 1;
        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nr < m &&
                nc >= 0 && nc < n &&
                matrix[nr][nc] > matrix[r][c]) {
                best = Math.max(best, 1 + dfs(nr, nc));
            }
        }
        dp[r][c] = best;
        return best;
    }
}