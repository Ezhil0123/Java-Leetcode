class Solution {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
                if(grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if(fresh == 0) return 0;
        int time = 0;
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        while(!q.isEmpty()) {
            int size = q.size();
            boolean rotted = false;
            for(int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int x = curr[0], y = curr[1];
                for(int[] d : dir) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    if(nx >= 0 && ny >= 0 && nx < n && ny < m && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2;
                        q.offer(new int[]{nx, ny});
                        fresh--;
                        rotted = true;
                    }
                }
            }
            if(rotted) time++;
        }
        return fresh == 0 ? time : -1;
    }
}