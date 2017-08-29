package uk.co.mainwave.regextoolbox;

import java.util.regex.Pattern;

/**
 * Class to build regular expressions in a more human-readable way using a fluent API.
 *
 * To use, chain method calls representing the elements you want to match, and finish with
 * {@link #buildRegex(RegexOptions...)} to build the {@link Pattern}.
 *
 * Example:
 *
 *     Pattern regex = new RegexBuilder()
 *                        .text("cat")
 *                        .endOfString()
 *                     .buildRegex();
 */
public class RegexBuilder
{
    @SuppressWarnings("WeakerAccess")
    protected final StringBuilder stringBuilder;

    @SuppressWarnings("WeakerAccess")
    protected final RegexBuilder parent;

    public RegexBuilder()
    {
        parent = null;
        stringBuilder = new StringBuilder();
    }

    @SuppressWarnings("WeakerAccess")
    protected RegexBuilder(final RegexBuilder parent)
    {
        this.parent = parent;
        stringBuilder = parent.stringBuilder;
    }

    /**
     * Build and return a {@link Pattern} object from the current builder state.
     * After calling this the builder is cleared and ready to re-use.
     * @param options Any number of regex options to apply to the regex
     * @return {@link Pattern} as built
     * @throws RegexBuilderException An error occurred when building the regex
     */
    public Pattern buildRegex(final RegexOptions... options) throws RegexBuilderException
    {
        int flags = 0;

        for (final RegexOptions option : options)
        {
            switch (option)
            {
                case IGNORE_CASE:
                    flags |= Pattern.CASE_INSENSITIVE;
                    break;
                case MULTILINE:
                    flags |= Pattern.MULTILINE;
                    break;
            }
        }

        final Pattern pattern = Pattern.compile(stringBuilder.toString(), flags);
        stringBuilder.setLength(0);
        return pattern;
    }

    /**
     * Add text to the regex. Any regex special characters will be escaped as necessary
     * so there's no need to do that yourself.
     * 
     * Example:
     * 
     * "Hello (world)" will be converted to "Hello \(world\)" so the brackets are treated
     * as normal, human-readable brackets, not regex grouping brackets.
     * It WILL match the string literal "Hello (world)".
     * It WILL NOT match the string literal "Hello world".
     * 
     * @param text Text to add
     */
    public RegexBuilder text(final String text) throws RegexBuilderException
    {
        return text(text, null);
    }

    /**
     * Add text to the regex. Any regex special characters will be escaped as necessary
     * so there's no need to do that yourself.
     *
     * Example:
     *
     * "Hello (world)" will be converted to "Hello \(world\)" so the brackets are treated
     * as normal, human-readable brackets, not regex grouping brackets.
     * It WILL match the string literal "Hello (world)".
     * It WILL NOT match the string literal "Hello world".
     *
     * @param text Text to add
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder text(final String text, final RegexQuantifier quantifier) throws RegexBuilderException
    {
        return regexText(makeSafeForRegex(text), quantifier);
    }

    /**
     * Add literal regex text to the regex. Regex special characters will NOT be escaped.
     * Only call this if you're comfortable with regex syntax.
     *
     * Example:
     *
     * "Hello (world)" will be left as "Hello (world)", meaning that when the regex is built
     * the brackets will be treated as regex grouping brackets rather than normal, human-readable
     * brackets.
     * It WILL match the string literal "Hello world" (and capture the word "world" as a group).
     * It WILL NOT match the string literal "Hello (world)".
     *
     * @param text regex text to add
     */
    public RegexBuilder regexText(final String text) throws RegexBuilderException
    {
        return regexText(text, null);
    }

    /**
     * Add literal regex text to the regex. Regex special characters will NOT be escaped.
     * Only call this if you're comfortable with regex syntax.
     *
     * Example:
     *
     * "Hello (world)" will be left as "Hello (world)", meaning that when the regex is built
     * the brackets will be treated as regex grouping brackets rather than normal, human-readable
     * brackets.
     * It WILL match the string literal "Hello world" (and capture the word "world" as a group).
     * It WILL NOT match the string literal "Hello (world)".
     *
     * @param text regex text to add
     * @param quantifier Quantifier to apply to the whole string
     */
    public RegexBuilder regexText(final String text, final RegexQuantifier quantifier) throws RegexBuilderException
    {
        if (quantifier == null)
        {
            stringBuilder.append(text);
            return this;
        }

        return startNonCapturingGroup()
                .regexText(text, null)
                .endGroup(quantifier);
    }

    /**
     * Add an element to match any character.
     */
    public RegexBuilder anyCharacter()
    {
        return anyCharacter(null);
    }

    /**
     * Add an element to match any character.
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder anyCharacter(final RegexQuantifier quantifier)
    {
        stringBuilder.append(".");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any single whitespace character.
     */
    public RegexBuilder whitespace()
    {
        return whitespace(null);
    }

    /**
     * Add an element to match any single whitespace character.
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder whitespace(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\s");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any single non-whitespace character.
     */
    public RegexBuilder nonWhitespace()
    {
        return nonWhitespace(null);
    }

    /**
     * Add an element to match any single non-whitespace character.
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder nonWhitespace(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\S");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any single decimal digit (0-9).
     */
    public RegexBuilder digit()
    {
        return digit(null);
    }

    /**
     * Add an element to match any single decimal digit (0-9).
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder digit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\d");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any character that is not a decimal digit (0-9).
     */
    public RegexBuilder nonDigit()
    {
        return nonDigit(null);
    }

    /**
     * Add an element to match any character that is not a decimal digit (0-9).
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder nonDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\D");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any letter in the Roman alphabet (a-z, A-Z)
     */
    public RegexBuilder letter()
    {
        return letter(null);
    }

    /**
     * Add an element to match any letter in the Roman alphabet (a-z, A-Z)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder letter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[a-zA-Z]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any character that is not a letter in the Roman alphabet (a-z, A-Z)
     */
    public RegexBuilder nonLetter()
    {
        return nonLetter(null);
    }

    /**
     * Add an element to match any character that is not a letter in the Roman alphabet (a-z, A-Z)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder nonLetter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[^a-zA-Z]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any upper-case letter in the Roman alphabet (A-Z).
     */
    public RegexBuilder uppercaseLetter()
    {
        return uppercaseLetter(null);
    }

    /**
     * Add an element to match any upper-case letter in the Roman alphabet (A-Z).
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder uppercaseLetter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[A-Z]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any lowercase letter in the Roman alphabet (a-z)
     */
    public RegexBuilder lowercaseLetter()
    {
        return lowercaseLetter(null);
    }

    /**
     * Add an element to match any lowercase letter in the Roman alphabet (a-z)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder lowercaseLetter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[a-z]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any letter in the Roman alphabet or decimal digit (a-z, A-Z, 0-9)
     */
    public RegexBuilder letterOrDigit()
    {
        return letterOrDigit(null);
    }

    /**
     * Add an element to match any letter in the Roman alphabet or decimal digit (a-z, A-Z, 0-9)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder letterOrDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[a-zA-Z0-9]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any character that is not letter in the Roman alphabet or a decimal digit (a-z, A-Z, 0-9)
     */
    public RegexBuilder nonLetterOrDigit()
    {
        return nonLetterOrDigit(null);
    }

    /**
     * Add an element to match any character that is not letter in the Roman alphabet or a decimal digit (a-z, A-Z, 0-9)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder nonLetterOrDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[^a-zA-Z0-9]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any hexadecimal digit (a-f, A-F, 0-9)
     */
    public RegexBuilder hexDigit()
    {
        return hexDigit(null);
    }

    /**
     * Add an element to match any hexadecimal digit (a-f, A-F, 0-9)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder hexDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[0-9A-Fa-f]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any uppercase hexadecimal digit (A-F, 0-9)
     */
    public RegexBuilder uppercaseHexDigit()
    {
        return uppercaseHexDigit(null);
    }

    /**
     * Add an element to match any uppercase hexadecimal digit (A-F, 0-9)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder uppercaseHexDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[0-9A-F]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any lowercase hexadecimal digit (a-f, 0-9)
     */
    public RegexBuilder lowercaseHexDigit()
    {
        return lowercaseHexDigit(null);
    }

    /**
     * Add an element to match any lowercase hexadecimal digit (a-f, 0-9)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder lowercaseHexDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[0-9a-f]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any character that is not a hexadecimal digit (a-f, A-F, 0-9)
     */
    public RegexBuilder nonHexDigit()
    {
        return nonHexDigit(null);
    }

    /**
     * Add an element to match any character that is not a hexadecimal digit (a-f, A-F, 0-9)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder nonHexDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[^0-9A-Fa-f]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any Roman alphabet letter, decimal digit, or underscore (a-z, A-Z, 0-9, _)
     */
    public RegexBuilder wordCharacter()
    {
        return wordCharacter(null);
    }

    /**
     * Add an element to match any Roman alphabet letter, decimal digit, or underscore (a-z, A-Z, 0-9, _)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder wordCharacter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\w");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element to match any character that is not a Roman alphabet letter, decimal digit, or underscore (a-z, A-Z, 0-9, _)
     */
    public RegexBuilder nonWordCharacter()
    {
        return nonWordCharacter(null);
    }

    /**
     * Add an element to match any character that is not a Roman alphabet letter, decimal digit, or underscore (a-z, A-Z, 0-9, _)
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder nonWordCharacter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\W");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element (a character class) to match any of the characters provided.
     * @param characters String containing all characters to include in the character class
     */
    public RegexBuilder anyCharacterFrom(final String characters)
    {
        return anyCharacterFrom(characters, null);
    }

    /**
     * Add an element (a character class) to match any of the characters provided.
     * @param characters String containing all characters to include in the character class
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder anyCharacterFrom(final String characters, final RegexQuantifier quantifier)
    {
        // Build a character class, remembering to escape any ] character if passed in
        stringBuilder
                .append("[")
                .append(makeSafeForCharacterClass(characters))
                .append("]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add an element (a character class) to match any character except those provided.
     * @param characters String containing all characters to exclude from the character class
     */
    public RegexBuilder anyCharacterExcept(final String characters)
    {
        return anyCharacterExcept(characters, null);
    }

    /**
     * Add an element (a character class) to match any character except those provided.
     * @param characters String containing all characters to exclude from the character class
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder anyCharacterExcept(final String characters, final RegexQuantifier quantifier)
    {
        // Build a character class, remembering to escape any ] character if passed in
        stringBuilder
                .append("[^")
                .append(makeSafeForCharacterClass(characters))
                .append("]");
        addQuantifier(quantifier);
        return this;
    }

    /**
     * Add a group of alternatives, to match any of the strings provided
     * @param strings A number of strings, any one of which will be matched
     */
    public RegexBuilder anyOf(final String[] strings) throws RegexBuilderException
    {
        return anyOf(strings, null);
    }

    /**
     * Add a group of alternatives, to match any of the strings provided
     * @param strings A number of strings, any one of which will be matched
     * @param quantifier Quantifier to apply to this element
     */
    public RegexBuilder anyOf(final String[] strings, final RegexQuantifier quantifier) throws RegexBuilderException
    {
        if (strings == null || strings.length == 0)
        {
            return this;
        }

        if (strings.length == 1)
        {
            stringBuilder.append(makeSafeForRegex(strings[0]));
            addQuantifier(quantifier);
            return this;
        }

        String[] safeStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++)
        {
            safeStrings[i] = makeSafeForRegex(strings[i]);
        }
        return startNonCapturingGroup()
                .regexText(String.join("|", safeStrings), quantifier)
                .endGroup(quantifier);
    }



    // ZERO-WIDTH ASSERTIONS

    /**
     * Add a zero-width anchor element to match the start of the string
     */
    public RegexBuilder startOfString()
    {
        stringBuilder.append("^");
        return this;
    }

    /**
     * Add a zero-width anchor element to match the end of the string
     */
    public RegexBuilder endOfString()
    {
        stringBuilder.append("$");
        return this;
    }

    /**
     * Add a zero-width anchor element to match the boundary between an alphanumeric/underscore character
     * and either a non-alphanumeric, non-underscore character or the start/end of the string.
     */
    public RegexBuilder wordBoundary()
    {
        stringBuilder.append("\\b");
        return this;
    }


    // GROUPS

    /**
     * Start a capture group. Capture groups have two purposes: they group part of the expression so
     * it can have quantifiers applied to it, and they capture the results of each group match and
     * allow you to access them afterwards using Match.Groups.
     * 
     * If you don't want to capture the group match, use {@link #startNonCapturingGroup()}.
     * 
     * Note: all groups must be ended with {@link #endGroup()} before calling {@link #buildRegex(RegexOptions...)}.
     */
    public RegexBuilder startGroup()
    {
        stringBuilder.append("(");
        return new RegexGroupBuilder(this);
    }

    /**
     * Start a non-capturing group. Non-capturing groups group part of the expression so
     * it can have quantifiers applied to it, but do not capture the results of each group match, meaning
     * you can't access them afterwards using Match.Groups.
     * 
     * If you want to capture group results, use {@link #startGroup()} or {@link #startNamedGroup(String)}.
     *
     * Note: all groups must be ended with {@link #endGroup()} before calling {@link #buildRegex(RegexOptions...)}.
     */
    public RegexBuilder startNonCapturingGroup()
    {
        stringBuilder.append("(?:");
        return new RegexGroupBuilder(this);
    }

    /**
     * Start a named capture group. Capture groups have two purposes: they group part of the expression so
     * it can have quantifiers applied to it, and they capture the results of each group match and
     * allow you to access them afterwards using Match.Groups. Named capture groups can be accessed by
     * indexing into Match.Groups with the assigned name as well as a numerical index.
     *
     * If you don't want to capture the group match, use {@link #startNonCapturingGroup()}.
     *
     * Note: all groups must be ended with {@link #endGroup()} before calling {@link #buildRegex(RegexOptions...)}.
     */
    public RegexBuilder startNamedGroup(final String name)
    {
        stringBuilder
                .append("(?<")
                .append(name)
                .append(">");
        return new RegexGroupBuilder(this);
    }

    /**
     * End the innermost group previously started with {@link #startGroup()}, {@link #startNonCapturingGroup()} or
     * {@link #startNamedGroup(String)}.
     */
    public RegexBuilder endGroup() throws RegexBuilderException
    {
        return endGroup(null);
    }

    /**
     * End the innermost group previously started with {@link #startGroup()}, {@link #startNonCapturingGroup()} or
     * {@link #startNamedGroup(String)}.
     * @param quantifier Quantifier to apply to this group
     */
    public RegexBuilder endGroup(final RegexQuantifier quantifier) throws RegexBuilderException
    {
        throw new RegexBuilderException("Cannot call endGroup() until a group has been started with startGroup()", stringBuilder);
    }
    
    
    
    // PRIVATE

    protected void addQuantifier(final RegexQuantifier quantifier)
    {
        if (quantifier != null)
        {
            stringBuilder.append(quantifier);
        }
    }

    private String makeSafeForCharacterClass(final String s)
    {
        // Replace ] with \]
        String result = s.replace("]", "\\]");

        // replace ^ with \^ if it occurs at the start of the string
        if (result.startsWith("^"))
        {
            result = "\\" + result;
        }

        return result;
    }

    private static String makeSafeForRegex(final String s)
    {
        final String result = s
                // Make sure this always comes first!
                .replace("\\", "\\\\")
                .replace("?", "\\?")
                .replace(".", "\\.")
                .replace("+", "\\+")
                .replace("*", "\\*")
                .replace("^", "\\^")
                .replace("$", "\\$")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace("|", "\\|");

        return result;
    }

}
