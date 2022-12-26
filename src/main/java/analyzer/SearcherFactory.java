package analyzer;

public class SearcherFactory {

    public static SubstringSearcher getSearcher(String algorithm) {
        return switch (algorithm.toLowerCase()) {
            case "--naive" -> new NaiveSearcher();
            default -> new KMPSearcher();
        };
    }

}
