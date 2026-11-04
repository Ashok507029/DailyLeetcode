
import java.util.*;

class Solution {
    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();

    long sumLow = 0, sumHigh = 0;
    int sizeLow = 0, sizeHigh = 0;

    void add(int num) {
        if (low.isEmpty() || num <= low.lastKey()) {
            low.put(num, low.getOrDefault(num, 0) + 1);
            sumLow += num;
            sizeLow++;
        } else {
            high.put(num, high.getOrDefault(num, 0) + 1);
            sumHigh += num;
            sizeHigh++;
        }
        balance();
    }

    void remove(int num) {
        if (low.containsKey(num)) {
            removeFrom(low, num);
            sumLow -= num;
            sizeLow--;
        } else {
            removeFrom(high, num);
            sumHigh -= num;
            sizeHigh--;
        }
        balance();
    }

    void removeFrom(TreeMap<Integer, Integer> map, int num) {
        int count = map.get(num);
        if (count == 1) map.remove(num);
        else map.put(num, count - 1);
    }

    void balance() {
        while (sizeLow > sizeHigh + 1) {
            int num = low.lastKey();
            removeFrom(low, num);
            sumLow -= num;
            sizeLow--;

            high.put(num, high.getOrDefault(num, 0) + 1);
            sumHigh += num;
            sizeHigh++;
        }

        while (sizeLow < sizeHigh) {
            int num = high.firstKey();
            removeFrom(high, num);
            sumHigh -= num;
            sizeHigh--;

            low.put(num, low.getOrDefault(num, 0) + 1);
            sumLow += num;
            sizeLow++;
        }
    }

    long cost() {
        long median = low.lastKey();

        return median * sizeLow - sumLow
             + sumHigh - median * sizeHigh;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] costs = new long[n - x + 1];

        for (int i = 0; i < n; i++) {
            add(nums[i]);

            if (i >= x) {
                remove(nums[i - x]);
            }

            if (i >= x - 1) {
                costs[i - x + 1] = cost();
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + costs[i - x]
                    );
                }
            }

            prev = curr;
        }

        return prev[n];
    }
}
