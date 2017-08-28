package uk.co.mainwave.regextoolbox;

/**
 * Options that can be passed to {@link RegexBuilder#buildRegex(RegexOptions...)}.
 */
public enum RegexOptions
{
    /**
     * Make the regex case-insensitive
     */
    IGNORE_CASE,

    /**
     * Cause {@link RegexBuilder#startOfString()} and {@link RegexBuilder#endOfString()} to also match line breaks
     * within a multi-line string
     */
    MULTILINE
}
