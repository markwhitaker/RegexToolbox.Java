package uk.co.mainwave.regextoolbox;

/**
 * Exception thrown by {@link RegexBuilder} methods
 */
@SuppressWarnings("WeakerAccess")
public final class RegexBuilderException extends Exception {
    /**
     * The regex string as it currently stands
     */
    private final String regexString;

    /**
     * Constructor
     *
     * @param message       Message describing the error
     * @param stringBuilder The current regex string being built by {@link RegexBuilder}
     */
    public RegexBuilderException(final String message, final StringBuilder stringBuilder) {
        super(message);
        regexString = stringBuilder.toString();
    }

    /**
     * Get the regex string built so far by the {@link RegexBuilder}
     *
     * @return Regex string
     */
    public String getRegexString() {
        return regexString;
    }
}
