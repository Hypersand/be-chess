package softeer2nd.utils;

public class StringUtils {

    public static final String NEWLINE = System.lineSeparator();

    public static String appendNewLine(String line) {
        return line + NEWLINE;
    }

    private StringUtils() {
    }
}
