class Solution {
    private List<Integer>[] graph;
    private int[][] up;
    private int[] tin, tout, depth, bit, parent;
    private int timer, LOG;
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        timer = 0;
        dfs(0, -1);
        bit = new int[n + 2];
        char[] chars = s.toCharArray();
        int[] mask = new int[n];
        for (int i = 0; i < n; i++) {
            mask[i] = 1 << (chars[i] - 'a');
            update(tin[i], mask[i]);
            update(tout[i] + 1, mask[i]);
        }
        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                int newMask = 1 << (c - 'a');
                if (chars[u] != c) {
                    int diff = mask[u] ^ newMask;
                    update(tin[u], diff);
                    update(tout[u] + 1, diff);
                    chars[u] = c;
                    mask[u] = newMask;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);
                int pathMask = query(tin[u])
                             ^ query(tin[v])
                             ^ mask[l];

                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }
        return ans;
    }

    private void dfs(int u, int par) {
        parent[u] = par == -1 ? u : par;
        up[0][u] = parent[u];
        for (int k = 1; k < LOG; k++) {
            up[k][u] = up[k - 1][up[k - 1][u]];
        }

        tin[u] = ++timer;

        for (int v : graph[u]) {
            if (v == par) continue;
            depth[v] = depth[u] + 1;
            dfs(v, u);
        }

        tout[u] = timer;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int k = LOG - 1; k >= 0; k--) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    private void update(int i, int val) {
        for (; i < bit.length; i += i & -i) {
            bit[i] ^= val;
        }
    }

    private int query(int i) {
        int res = 0;
        for (; i > 0; i -= i & -i) {
            res ^= bit[i];
        }
        return res;
    }
}
