
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 17;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[][] next = new int[LOG][n + 1];
        long[][] cost = new long[LOG][n + 1];

        int[] stack = new int[n];
        int top = -1;

        for (int p = 0; p < LOG; p++) {
            next[p][n] = n;
        }

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            int j = top == -1 ? n : stack[top];

            next[0][i] = j;
            cost[0][i] = (long) nums[i] * (j - i)
                       - (prefix[j] - prefix[i]);

            stack[++top] = i;
        }

        for (int p = 1; p < LOG; p++) {
            for (int i = 0; i < n; i++) {
                int mid = next[p - 1][i];

                next[p][i] = next[p - 1][mid];

                cost[p][i] = cost[p - 1][i]
                           + cost[p - 1][mid];
            }
        }

        long ans = 0;

        for (int r = 0; r < n; r++) {
            int low = 0;
            int high = r;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (getCost(mid, r, nums, prefix, next, cost) <= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            ans += r - low + 1;
        }

        return ans;
    }

    private long getCost(int l, int r, int[] nums,
                         long[] prefix, int[][] next,
                         long[][] cost) {

        long total = 0;
        int pos = l;

        for (int p = next.length - 1; p >= 0; p--) {
            int j = next[p][pos];

            if (j <= r) {
                total += cost[p][pos];
                pos = j;
            }
        }

        total += (long) nums[pos] * (r - pos + 1)
               - (prefix[r + 1] - prefix[pos]);

        return total;
    }
}

