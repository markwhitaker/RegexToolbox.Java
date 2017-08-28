package uk.co.mainwave.RegexToolbox;

public class RegexBuilderException extends Exception
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
