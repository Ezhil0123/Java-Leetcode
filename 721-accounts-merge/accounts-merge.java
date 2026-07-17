class Solution {

    class DSU {
        int[] parent;
        int[] rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py)
                return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DSU dsu = new DSU(n);
        Map<String, Integer> emailToAccount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (!emailToAccount.containsKey(email)) {
                    emailToAccount.put(email, i);
                } else {
                    dsu.union(i, emailToAccount.get(email));
                }
            }
        }
        Map<Integer, TreeSet<String>> merged = new HashMap<>();
        for (String email : emailToAccount.keySet()) {
            int acc = emailToAccount.get(email);
            int parent = dsu.find(acc);
            merged
                .computeIfAbsent(parent, k -> new TreeSet<>())
                .add(email);
        }
        List<List<String>> ans = new ArrayList<>();
        for (int parent : merged.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(accounts.get(parent).get(0));
            list.addAll(merged.get(parent));
            ans.add(list);
        }
        return ans;
    }
}