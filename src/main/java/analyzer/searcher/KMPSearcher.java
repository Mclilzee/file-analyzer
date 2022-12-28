package analyzer.searcher;

import analyzer.util.StringUtils;

public class KMPSearcher implements SubstringSearcher {

    @Override
    public boolean containsSubstring(String content, String pattern) {
        int[] prefix = StringUtils.prefixFunction(pattern);
        char[] contentChars = content.toCharArray();

        int j = 0;
        int i = 0;
        while (i < contentChars.length) {
            if (contentChars[i] == pattern.charAt(j)) {
                j++;
                if (j == prefix.length) {
                    return true;
                }
                i++;
                continue;
            }

            if (j == 0) {
                i++;
                continue;
            }
            j = prefix[j - 1];
        }

        return false;
    }
}
