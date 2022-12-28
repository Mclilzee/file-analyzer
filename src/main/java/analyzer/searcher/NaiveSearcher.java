package analyzer.searcher;

public class NaiveSearcher implements SubstringSearcher {
    @Override
    public boolean containsSubstring(String content, String pattern) {
        int patternLength = pattern.length();
        for (int i = 0; i < content.length() - patternLength + 1; i++) {
            String substring = content.substring(i, patternLength + i);
            if (pattern.equals(substring)) {
                return true;
            }

        }

        return false;
    }
}
