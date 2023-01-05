package analyzer;

public record PatternDataBase(int priority, String pattern, String output) implements Comparable<PatternDataBase> {

    @Override
    public int compareTo(PatternDataBase other) {
        return Integer.compare(other.priority, this.priority);
    }
}
