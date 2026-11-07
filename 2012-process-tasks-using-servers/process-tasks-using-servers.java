
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return a[0] - b[0];
                return a[1] - b[1];
            }
        );

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        for (int i = 0; i < servers.length; i++) {
            free.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[tasks.length];
        long time = 0;

        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, i);

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] s = busy.poll();
                free.offer(new int[]{(int) s[1], (int) s[2]});
            }

            if (free.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] s = busy.poll();
                    free.offer(new int[]{(int) s[1], (int) s[2]});
                }
            }

            int[] s = free.poll();

            ans[i] = s[1];

            busy.offer(new long[]{
                time + tasks[i],
                s[0],
                s[1]
            });
        }

        return ans;
    }
}

