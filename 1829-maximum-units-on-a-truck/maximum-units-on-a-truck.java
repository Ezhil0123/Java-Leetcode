class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]); 
        int totalUnits = 0;
        for (int[] box : boxTypes) {
            int boxesToTake = Math.min(box[0], truckSize);
            totalUnits += boxesToTake * box[1];
            truckSize -= boxesToTake;
            if (truckSize == 0) {
                break;
            }
        }
        return totalUnits;
    }
}