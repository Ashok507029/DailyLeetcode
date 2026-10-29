import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] size = new int[n];
        int[] order = new int[n];
        int[] stack = new int[n];
        int[] parent = new int[n];

        Arrays.fill(parent, -1);

        int top = 0, timer = 0;
        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];
            tin[u] = timer;
            order[timer++] = u;
            size[u] = 1;

            for (int v : graph[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;
                stack[top++] = v;
            }
        }

        up[0][0] = 0;

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        for (int i = 0; i < n; i++) {
            tout[i] = tin[i] + size[i] - 1;
        }

        BIT bit = new BIT(n + 2);
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            bit.update(tin[i] + 1, mask);
            bit.update(tout[i] + 2, mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                if (chars[u] == c) continue;

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                bit.update(tin[u] + 1, delta);
                bit.update(tout[u] + 2, delta);

                chars[u] = c;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v, depth, up, LOG);

                int mask = bit.query(tin[u] + 1)
                         ^ bit.query(tin[v] + 1)
                         ^ (1 << (chars[lca] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private int getLCA(int u, int v, int[] depth, int[][] up, int LOG) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    static class BIT {
        int[] tree;
        int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int i, int val) {
            while (i <= n) {
                tree[i] ^= val;
                i += i & -i;
            }
        }

        int query(int i) {
            int result = 0;

            while (i > 0) {
                result ^= tree[i];
                i -= i & -i;
            }

            return result;
        }
    }
}