class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int odd = 0;
        char middle = 0;
        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }
        if (odd > 1) {
            return "";
        }
        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }
        int halfLen = n / 2;
        StringBuilder left = new StringBuilder();
        for (int pos = 0; pos < halfLen; pos++) {
            boolean found = false;
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) {
                    continue;
                }
                half[c]--;
                left.append((char) ('a' + c));
                if (canBeGreater(left, half, middle, target, n)) {
                    found = true;
                    break;
                }
                left.deleteCharAt(left.length() - 1);
                half[c]++;
            }
            if (!found) {
                return "";
            }
        }
        String leftPart = left.toString();
        StringBuilder ans = new StringBuilder();
        ans.append(leftPart);
        if (n % 2 == 1) {
            ans.append(middle);
        }
        for (int i = leftPart.length() - 1; i >= 0; i--) {
            ans.append(leftPart.charAt(i));
        }
        String result = ans.toString();
        return result.compareTo(target) > 0 ? result : "";
    }

    private boolean canBeGreater(
            StringBuilder prefix,
            int[] half,
            char middle,
            String target,
            int n) {

        StringBuilder left = new StringBuilder(prefix);
        for (int c = 25; c >= 0; c--) {
            for (int k = 0; k < half[c]; k++) {
                left.append((char) ('a' + c));
            }
        }
        StringBuilder palindrome = new StringBuilder();
        palindrome.append(left);
        if (n % 2 == 1) {
            palindrome.append(middle);
        }
        for (int i = left.length() - 1; i >= 0; i--) {
            palindrome.append(left.charAt(i));
        }
        return palindrome.toString().compareTo(target) > 0;
    }
}