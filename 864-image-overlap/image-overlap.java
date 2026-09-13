class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        for (int i = 0; i < img1.length; i++) {
            for (int j = 0; j < img2.length; j++) {
                if (img1[i][j] == 1) ones1.add(new int[]{i, j});
                if (img2[i][j] == 1) ones2.add(new int[]{i, j});
            }
        }
        Map<String, Integer> shiftCounts = new HashMap<>();
        int max = 0;
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                String shift = (p2[0] - p1[0]) + "," + (p2[1] - p1[1]);
                shiftCounts.put(shift, shiftCounts.getOrDefault(shift, 0) + 1);
                max = Math.max(max, shiftCounts.get(shift));
            }
        }
        return max;
    }
}