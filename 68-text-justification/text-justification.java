class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int j = i;
            int lineLength = 0;
            while (j < words.length && lineLength + words[j].length() + (j - i) <= maxWidth) {
                lineLength += words[j].length();
                j++;
            }
            int gaps = j - i - 1;
            StringBuilder line = new StringBuilder();
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1) {
                        line.append(" ");
                    }
                }
                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            }
            else {
                int totalSpace = maxWidth - lineLength;
                int evenSpace = totalSpace / gaps;
                int extraSpace = totalSpace % gaps;
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1) {
                        for (int s = 0; s < evenSpace; s++) {
                            line.append(" ");
                        }
                        if (extraSpace > 0) {
                            line.append(" ");
                            extraSpace--;
                        }
                    }
                }
            }
            result.add(line.toString());
            i = j; 
        }
        return result;
    }
}