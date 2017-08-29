package uk.co.mainwave.regextoolbox;

/**
 * Exception thrown by {@link RegexBuilder} methods
 */
@SuppressWarnings("WeakerAccess")
public final class RegexBuilderException extends Exception
{
    /**
     * The regex string as it currently stands
     */
    private String regexString;

    public RegexBuilderException(String message, StringBuilder stringBuilder)
    {
        super(message);
        regexString = stringBuilder.toString();
    }

    public String getRegexString()
    {
        return regexString;
    }
}
