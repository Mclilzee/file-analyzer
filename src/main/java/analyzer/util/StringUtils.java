package analyzer.util;

public class StringUtils {

    public static int[] prefixFunction(String content) {
        char[] stringChars = content.toCharArray();
        int[] prefix = new int[stringChars.length];

        int j = 0;
        int i = 1;
        while (i < stringChars.length) {
            if (stringChars[i] == stringChars[j]) {
                prefix[i] = j + 1;
                j++;
            } else if (j > 0){
                j = prefix[j - 1];
                continue;
            }

            i++;
        }

        return prefix;
    }

}
