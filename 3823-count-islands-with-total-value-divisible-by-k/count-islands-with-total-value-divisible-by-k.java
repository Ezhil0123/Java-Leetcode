class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        int[] stack = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;

                int top = 0;
                long sum = 0;
                stack[top++] = i * n + j;
                sum += grid[i][j];
                grid[i][j] = 0;
                while (top > 0) {
                    int pos = stack[--top];
                    int r = pos / n;
                    int c = pos % n;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dirs[d];
                        int nc = c + dirs[d + 1];
                        if (nr >= 0 && nr < m &&
                            nc >= 0 && nc < n &&
                            grid[nr][nc] > 0) {
                            sum += grid[nr][nc];
                            grid[nr][nc] = 0;
                            stack[top++] = nr * n + nc;
                        }
                    }
                }
                if (sum % k == 0) count++;
            }
        }
        return count;
    }
}
