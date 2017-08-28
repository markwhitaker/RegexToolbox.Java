package uk.co.mainwave.RegexToolbox;

/**
 * Options that can be passed to RegexBuilder.buildRegex().
 */
public enum RegexOptions
{
    /**
     * Make the regex case-insensitive
     */
    IGNORE_CASE,

    /**
     * Cause startOfString() and endOfString() to also match line breaks within a multi-line string
     */
    MULTILINE
}
