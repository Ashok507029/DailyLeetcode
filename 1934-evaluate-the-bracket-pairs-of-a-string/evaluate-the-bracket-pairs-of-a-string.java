class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();

        HashMap<String, String> mp = new HashMap<>();

        for (List<String> vec : knowledge) {
            mp.put(vec.get(0), vec.get(1));
        }

        String result = "";
        int i = 0;

        while (i < n) {
            if (s.charAt(i) == '(') {

                int j = s.indexOf(')', i + 1);
                String temp = s.substring(i + 1, j);

                result += mp.containsKey(temp) ? mp.get(temp) : "?";
                i = j;

            } else {
                result += s.charAt(i);
            }

            i++;
        }

        return result;
    }
}