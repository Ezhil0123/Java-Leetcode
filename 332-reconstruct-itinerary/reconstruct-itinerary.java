class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> ans = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph
                .computeIfAbsent(from, k -> new PriorityQueue<>())
                .offer(to);
        }
        dfs("JFK");
        return ans;
    }

    private void dfs(String src) {
        PriorityQueue<String> pq = graph.get(src);
        while (pq != null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }
        ans.addFirst(src);
    }
}