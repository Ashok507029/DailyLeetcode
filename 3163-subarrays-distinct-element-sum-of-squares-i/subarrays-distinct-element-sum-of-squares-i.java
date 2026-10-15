
import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int ans = 0;

        for (int i = 0; i < nums.size(); i++) {
            boolean[] seen = new boolean[101];
            int count = 0;

            for (int j = i; j < nums.size(); j++) {
                if (!seen[nums.get(j)]) {
                    seen[nums.get(j)] = true;
                    count++;
                }

                ans += count * count;
            }
        }

        return ans;
    }
}

