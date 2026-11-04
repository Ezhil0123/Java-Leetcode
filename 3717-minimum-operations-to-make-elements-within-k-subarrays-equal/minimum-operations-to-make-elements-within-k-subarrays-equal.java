class Solution {
    class Window {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();
        int lowSize = 0, highSize = 0;
        long lowSum = 0, highSum = 0;

        void add(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        void remove(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void rebalance() {
            int target = (lowSize + highSize + 1) / 2;
            while (lowSize > target) {
                int val = low.lastKey();
                remove(low, val);
                lowSize--;
                lowSum -= val;
                add(high, val);
                highSize++;
                highSum += val;
            }
            while (lowSize < target) {
                int val = high.firstKey();
                remove(high, val);
                highSize--;
                highSum -= val;
                add(low, val);
                lowSize++;
                lowSum += val;
            }
        }

        void insert(int val) {
            if (lowSize == 0 || val <= low.lastKey()) {
                add(low, val);
                lowSize++;
                lowSum += val;
            } else {
                add(high, val);
                highSize++;
                highSum += val;
            }
            rebalance();
        }

        void erase(int val) {
            if (low.containsKey(val)) {
                remove(low, val);
                lowSize--;
                lowSum -= val;
            } else {
                remove(high, val);
                highSize--;
                highSum -= val;
            }
            rebalance();
        }
        long getCost() {
            long median = low.lastKey();
            return median * lowSize - lowSum
                    + highSum - median * highSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        Window window = new Window();
        for (int i = 0; i < x; i++) {
            window.insert(nums[i]);
        }
        cost[0] = window.getCost();
        for (int i = x; i < n; i++) {
            window.erase(nums[i - x]);
            window.insert(nums[i]);
            cost[i - x + 1] = window.getCost();
        }
        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];
        for (int t = 1; t <= k; t++) {
            Arrays.fill(curr, INF);
            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }
}
