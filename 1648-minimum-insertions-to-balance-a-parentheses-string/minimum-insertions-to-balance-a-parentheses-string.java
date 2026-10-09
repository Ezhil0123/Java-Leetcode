class Solution {
    public int minInsertions(String s) {
        int need = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                ans++;
            }
            else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                }
                else {
                    need++;
                }
                if (ans > 0) {
                    ans--;
                }
                else {
                    need++;
                }
            }
        }
        return need + ans * 2;
    }
}