class Solution {
    int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {
            char ch = s.charAt(index);

            if (ch == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                index++;
            }
            else {
                Set<String> part;

                if (ch == '{') {
                    index++;
                    part = parse(s);
                    index++;
                }
                else {
                    part = new HashSet<>();
                    part.add(String.valueOf(ch));
                    index++;
                }

                Set<String> next = new HashSet<>();

                for (String a : current) {
                    for (String b : part) {
                        next.add(a + b);
                    }
                }

                current = next;
            }
        }

        result.addAll(current);
        return result;
    }
}