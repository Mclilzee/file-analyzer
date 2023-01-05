package analyzer;

public class PatternDataBaseFactory {

    public static PatternDataBase convertToPatternDatabase(String format) {
        String[] values = format.split(";");
        int priority = Integer.parseInt(values[0]);
        String pattern = values[1].replaceAll("\"", "");
        String output = values[2].replaceAll("\"", "");

        return new PatternDataBase(priority, pattern, output);
    }

}
