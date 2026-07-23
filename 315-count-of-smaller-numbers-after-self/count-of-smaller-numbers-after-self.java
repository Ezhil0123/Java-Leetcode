class Solution {

    class Pair {
        int val;
        int idx;
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    int[] count;
    Pair[] temp;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        temp = new Pair[n];
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        mergeSort(arr, 0, n - 1);
        List<Integer> ans = new ArrayList<>();
        for (int c : count) {
            ans.add(c);
        }
        return ans;
    }

    private void mergeSort(Pair[] arr, int left, int right) {
        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(Pair[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int rightCount = 0;
        while (i <= mid && j <= right) {
            if (arr[j].val < arr[i].val) {
                temp[k++] = arr[j++];
                rightCount++;
            } else {
                count[arr[i].idx] += rightCount;
                temp[k++] = arr[i++];
            }
        }
        while (i <= mid) {
            count[arr[i].idx] += rightCount;
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (int p = left; p <= right; p++) {
            arr[p] = temp[p];
        }
    }
}