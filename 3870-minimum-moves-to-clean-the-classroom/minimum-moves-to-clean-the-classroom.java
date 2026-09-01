class Solution {

    static class State {
        int r, c, energy, mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startR = 0, startC = 0;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } 
                else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        if (litterCount == 0) {
            return 0;
        }
        int targetMask = (1 << litterCount) - 1;
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litterCount];
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(startR, startC, energy, 0));
        visited[startR][startC][energy][0] = true;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                State cur = queue.poll();
                if (cur.mask == targetMask) {
                    return moves;
                }
                if (cur.energy == 0) {
                    continue;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    int newEnergy = cur.energy - 1;
                    int newMask = cur.mask;
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'R') {
                        newEnergy = energy;
                    }
                    if (cell == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }
                    if (!visited[nr][nc][newEnergy][newMask]) {
                        visited[nr][nc][newEnergy][newMask] = true;
                        queue.offer(
                            new State(nr, nc, newEnergy, newMask)
                        );
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}