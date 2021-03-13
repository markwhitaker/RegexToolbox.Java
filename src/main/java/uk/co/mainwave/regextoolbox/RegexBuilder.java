package uk.co.mainwave.regextoolbox;

import java.util.regex.Pattern;

/**
 * Class to build regular expressions in a more human-readable way using a fluent API.
 * <p>
 * To use, chain method calls representing the elements you want to match, and finish with
 * {@link #buildRegex(RegexOptions...)} to build the {@link Pattern}.
 * <p>
 * Example:
 * <pre>
 * final Pattern regex = new RegexBuilder()
 *     .text("cat")
 *     .endOfString()
 *     .buildRegex();
 * </pre>
 */
public final class RegexBuilder {
    private final StringBuilder stringBuilder;
    private int openGroupCount;

    /**
     * Default constructor
     */
    public RegexBuilder() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Build and return a {@link Pattern} object from the current builder state.
     * After calling this the builder is cleared and ready to re-use.
     *
     * @param options Any number of regex options to apply to the regex
     * @return {@link Pattern} as built
     * @throws RegexBuilderException An error occurred when building the regex
     */
    public Pattern buildRegex(final RegexOptions... options) {
        if (openGroupCount == 1) {
            throw new RegexBuilderException("A group has been started but not ended", stringBuilder);
        }
        if (openGroupCount > 1) {
            throw new RegexBuilderException(openGroupCount + " groups have been started but not ended", stringBuilder);
        }

        int flags = 0;

        for (final RegexOptions option : options) {
            switch (option) {
                case IGNORE_CASE:
                    flags |= Pattern.CASE_INSENSITIVE;
                    break;
                case MULTILINE:
                    flags |= Pattern.MULTILINE;
                    break;
                default:
                    break;
            }
        }

        final String stringBuilt = stringBuilder.toString();
        final Pattern pattern = Pattern.compile(stringBuilt, flags);
        stringBuilder.setLength(0);
        return pattern;
    }

    /**
     * Add text to the regex. Any regex special characters will be escaped as necessary
     * so there's no need to do that yourself.
     * <p>
     * Example:
     * <p>
     * "Hello (world)" will be converted to "Hello \(world\)" so the brackets are treated
     * as normal, human-readable brackets, not regex grouping brackets.
     * It WILL match the string literal "Hello (world)".
     * It WILL NOT match the string literal "Hello world".
     *
     * @param text Text to add
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder text(final String text) {
        return text(text, null);
    }

    /**
     * Add text to the regex. Any regex special characters will be escaped as necessary
     * so there's no need to do that yourself.
     * <p>
     * Example:
     * <p>
     * "Hello (world)" will be converted to "Hello \(world\)" so the brackets are treated
     * as normal, human-readable brackets, not regex grouping brackets.
     * It WILL match the string literal "Hello (world)".
     * It WILL NOT match the string literal "Hello world".
     *
     * @param text       Text to add
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder text(final String text, final RegexQuantifier quantifier) {
        final String safeText = makeSafeForRegex(text);
        return (quantifier == null)
                ? addPart(safeText)
                : addPartInNonCapturingGroup(
                safeText, quantifier);
    }

    /**
     * Add literal regex text to the regex. Regex special characters will NOT be escaped.
     * Only call this if you're comfortable with regex syntax.
     * <p>
     * Example:
     * <p>
     * "Hello (world)" will be left as "Hello (world)", meaning that when the regex is built
     * the brackets will be treated as regex grouping brackets rather than normal, human-readable
     * brackets.
     * It WILL match the string literal "Hello world" (and capture the word "world" as a group).
     * It WILL NOT match the string literal "Hello (world)".
     *
     * @param text regex text to add
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder regexText(final String text) {
        return regexText(text, null);
    }

    /**
     * Add literal regex text to the regex. Regex special characters will NOT be escaped.
     * Only call this if you're comfortable with regex syntax.
     * <p>
     * Example:
     * <p>
     * "Hello (world)" will be left as "Hello (world)", meaning that when the regex is built
     * the brackets will be treated as regex grouping brackets rather than normal, human-readable
     * brackets.
     * It WILL match the string literal "Hello world" (and capture the word "world" as a group).
     * It WILL NOT match the string literal "Hello (world)".
     *
     * @param text       regex text to add
     * @param quantifier Quantifier to apply to the whole string
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder regexText(final String text, final RegexQuantifier quantifier) {
        return (quantifier == null)
                ? addPart(text)
                : addPartInNonCapturingGroup(
                text, quantifier);
    }

    /**
     * Add an element to match any character.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder anyCharacter() {
        return anyCharacter(null);
    }

    /**
     * Add an element to match any character.
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder anyCharacter(final RegexQuantifier quantifier) {
        return addPart(".", quantifier);
    }

    /**
     * Add an element to match any single whitespace character.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder whitespace() {
        return whitespace(null);
    }

    /**
     * Add an element to match any single whitespace character.
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder whitespace(final RegexQuantifier quantifier) {
        return addPart("\\s", quantifier);
    }

    /**
     * Add an element to match any single non-whitespace character.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonWhitespace() {
        return nonWhitespace(null);
    }

    /**
     * Add an element to match any single non-whitespace character.
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonWhitespace(final RegexQuantifier quantifier) {
        return addPart("\\S", quantifier);
    }

    /**
     * Add an element to represent any amount of white space, including none. This is just a convenient alias for
     * {@code whitespace(RegexQuantifier.zeroOrMore())}.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder possibleWhitespace() {
        return addPart("\\s*");
    }

    /**
     * Add an element to match a single space character. If you want to match any kind of white space, use
     * {@link #whitespace()}.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder space() {
        return space(null);
    }

    /**
     * Add an element to match a single space character. If you want to match any kind of white space, use
     * {@link #whitespace()}.
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder space(final RegexQuantifier quantifier) {
        return addPart(" ", quantifier);
    }

    /**
     * Add an element to match a single tab character.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder tab() {
        return tab(null);
    }

    /**
     * Add an element to match a single tab character.
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder tab(final RegexQuantifier quantifier) {
        return addPart("\\t", quantifier);
    }

    /**
     * Add an element to match a single line feed character.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder lineFeed() {
        return lineFeed(null);
    }

    /**
     * Add an element to match a single line feed character.
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder lineFeed(final RegexQuantifier quantifier) {
        return addPart("\\n", quantifier);
    }

    /**
     * Add an element to match a single carriage return character.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder carriageReturn() {
        return carriageReturn(null);
    }

    /**
     * Add an element to match a single carriage return character.
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder carriageReturn(final RegexQuantifier quantifier) {
        return addPart("\\r", quantifier);
    }

    /**
     * Add an element to match any single decimal digit (0-9).
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder digit() {
        return digit(null);
    }

    /**
     * Add an element to match any single decimal digit (0-9).
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder digit(final RegexQuantifier quantifier) {
        return addPart("\\d", quantifier);
    }

    /**
     * Add an element to match any character that is not a decimal digit (0-9).
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonDigit() {
        return nonDigit(null);
    }

    /**
     * Add an element to match any character that is not a decimal digit (0-9).
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonDigit(final RegexQuantifier quantifier) {
        return addPart("\\D", quantifier);
    }

    /**
     * Add an element to match any Unicode letter
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder letter() {
        return letter(null);
    }

    /**
     * Add an element to match any Unicode letter
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder letter(final RegexQuantifier quantifier) {
        return addPart("\\p{L}", quantifier);
    }

    /**
     * Add an element to match any character that is not a Unicode letter
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonLetter() {
        return nonLetter(null);
    }

    /**
     * Add an element to match any character that is not a Unicode letter
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonLetter(final RegexQuantifier quantifier) {
        return addPart("\\P{L}", quantifier);
    }

    /**
     * Add an element to match any upper-case Unicode letter
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder uppercaseLetter() {
        return uppercaseLetter(null);
    }

    /**
     * Add an element to match any upper-case Unicode letter
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder uppercaseLetter(final RegexQuantifier quantifier) {
        return addPart("\\p{Lu}", quantifier);
    }

    /**
     * Add an element to match any lowercase Unicode letter
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder lowercaseLetter() {
        return lowercaseLetter(null);
    }

    /**
     * Add an element to match any lowercase Unicode letter
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder lowercaseLetter(final RegexQuantifier quantifier) {
        return addPart("\\p{Ll}", quantifier);
    }

    /**
     * Add an element to match any Unicode letter or decimal digit
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder letterOrDigit() {
        return letterOrDigit(null);
    }

    /**
     * Add an element to match any Unicode letter or decimal digit
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder letterOrDigit(final RegexQuantifier quantifier) {
        return addPart("[\\p{L}0-9]", quantifier);
    }

    /**
     * Add an element to match any character that is not a Unicode letter or a decimal digit
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonLetterOrDigit() {
        return nonLetterOrDigit(null);
    }

    /**
     * Add an element to match any character that is not a Unicode letter or a decimal digit
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonLetterOrDigit(final RegexQuantifier quantifier) {
        return addPart("[^\\p{L}0-9]", quantifier);
    }

    /**
     * Add an element to match any hexadecimal digit (a-f, A-F, 0-9)
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder hexDigit() {
        return hexDigit(null);
    }

    /**
     * Add an element to match any hexadecimal digit (a-f, A-F, 0-9)
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder hexDigit(final RegexQuantifier quantifier) {
        return addPart("[0-9A-Fa-f]", quantifier);
    }

    /**
     * Add an element to match any uppercase hexadecimal digit (A-F, 0-9)
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder uppercaseHexDigit() {
        return uppercaseHexDigit(null);
    }

    /**
     * Add an element to match any uppercase hexadecimal digit (A-F, 0-9)
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder uppercaseHexDigit(final RegexQuantifier quantifier) {
        return addPart("[0-9A-F]", quantifier);
    }

    /**
     * Add an element to match any lowercase hexadecimal digit (a-f, 0-9)
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder lowercaseHexDigit() {
        return lowercaseHexDigit(null);
    }

    /**
     * Add an element to match any lowercase hexadecimal digit (a-f, 0-9)
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder lowercaseHexDigit(final RegexQuantifier quantifier) {
        return addPart("[0-9a-f]", quantifier);
    }

    /**
     * Add an element to match any character that is not a hexadecimal digit (a-f, A-F, 0-9)
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonHexDigit() {
        return nonHexDigit(null);
    }

    /**
     * Add an element to match any character that is not a hexadecimal digit (a-f, A-F, 0-9)
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonHexDigit(final RegexQuantifier quantifier) {
        return addPart("[^0-9A-Fa-f]", quantifier);
    }

    /**
     * Add an element to match any Unicode letter, decimal digit, or underscore
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder wordCharacter() {
        return wordCharacter(null);
    }

    /**
     * Add an element to match any Unicode letter, decimal digit, or underscore
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder wordCharacter(final RegexQuantifier quantifier) {
        return addPart("[\\p{L}0-9_]", quantifier);
    }

    /**
     * Add an element to match any character that is not a Unicode letter, decimal digit, or underscore
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonWordCharacter() {
        return nonWordCharacter(null);
    }

    /**
     * Add an element to match any character that is not a Unicode letter, decimal digit, or underscore
     *
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder nonWordCharacter(final RegexQuantifier quantifier) {
        return addPart("[^\\p{L}0-9_]", quantifier);
    }

    /**
     * Add an element (a character class) to match any of the characters provided.
     *
     * @param characters String containing all characters to include in the character class
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder anyCharacterFrom(final String characters) {
        return anyCharacterFrom(characters, null);
    }

    /**
     * Add an element (a character class) to match any of the characters provided.
     *
     * @param characters String containing all characters to include in the character class
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder anyCharacterFrom(final String characters, final RegexQuantifier quantifier) {
        // Build a character class, remembering to escape any ] character if passed in
        final String safeCharacters = makeSafeForCharacterClass(characters);
        return addPart("[" + safeCharacters + "]", quantifier);
    }

    /**
     * Add an element (a character class) to match any character except those provided.
     *
     * @param characters String containing all characters to exclude from the character class
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder anyCharacterExcept(final String characters) {
        return anyCharacterExcept(characters, null);
    }

    /**
     * Add an element (a character class) to match any character except those provided.
     *
     * @param characters String containing all characters to exclude from the character class
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder anyCharacterExcept(final String characters, final RegexQuantifier quantifier) {
        // Build a character class, remembering to escape any ] character if passed in
        final String safeCharacters = makeSafeForCharacterClass(characters);
        return addPart("[^" + safeCharacters + "]", quantifier);
    }

    /**
     * Add a group of alternatives, to match any of the strings provided
     *
     * @param strings A number of strings, any one of which will be matched
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder anyOf(final String... strings) {
        return anyOf(strings, null);
    }

    /**
     * Add a group of alternatives, to match any of the strings provided
     *
     * @param strings    A number of strings, any one of which will be matched
     * @param quantifier Quantifier to apply to this element
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder anyOf(final String[] strings, final RegexQuantifier quantifier) {
        if (strings == null || strings.length == 0) {
            return this;
        }

        if (strings.length == 1 && quantifier == null) {
            return addPart(makeSafeForRegex(strings[0]));
        }

        final String[] safeStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            safeStrings[i] = makeSafeForRegex(strings[i]);
        }
        return addPartInNonCapturingGroup(String.join("|", safeStrings), quantifier);
    }


    // ZERO-WIDTH ASSERTIONS

    /**
     * Add a zero-width anchor element to match the start of the string
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder startOfString() {
        return addPart("^");
    }

    /**
     * Add a zero-width anchor element to match the end of the string
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder endOfString() {
        return addPart("$");
    }

    /**
     * Add a zero-width anchor element to match the boundary between an alphanumeric/underscore character
     * and either a non-alphanumeric, non-underscore character or the start/end of the string.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder wordBoundary() {
        return addPart("\\b");
    }


    // GROUPS

    /**
     * Start a capture group. Capture groups have two purposes: they group part of the expression so
     * it can have quantifiers applied to it, and they capture the results of each group match and
     * allow you to access them afterwards using {@link java.util.regex.Matcher#group()}.
     * <p>
     * If you don't want to capture the group match, use {@link #startNonCapturingGroup()}.
     * <p>
     * Note: all groups must be ended with {@link #endGroup()} before calling {@link #buildRegex(RegexOptions...)}.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder startGroup() {
        openGroupCount++;
        return addPart("(");
    }

    /**
     * Start a non-capturing group. Non-capturing groups group part of the expression so
     * it can have quantifiers applied to it, but do not capture the results of each group match, meaning
     * you can't access them afterwards using {@link java.util.regex.Matcher#group()}.
     * <p>
     * If you want to capture group results, use {@link #startGroup()} or {@link #startNamedGroup(String)}.
     * <p>
     * Note: all groups must be ended with {@link #endGroup()} before calling {@link #buildRegex(RegexOptions...)}.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder startNonCapturingGroup() {
        openGroupCount++;
        return addPart("(?:");
    }

    /**
     * Start a named capture group. Capture groups have two purposes: they group part of the expression so
     * it can have quantifiers applied to it, and they capture the results of each group match and
     * allow you to access them afterwards using {@link java.util.regex.Matcher#group()}. Named capture groups can be
     * accessed by indexing into {@link java.util.regex.Matcher#group()} with the assigned name as well as a numerical
     * index.
     * <p>
     * If you don't want to capture the group match, use {@link #startNonCapturingGroup()}.
     * <p>
     * Note: all groups must be ended with {@link #endGroup()} before calling {@link #buildRegex(RegexOptions...)}.
     *
     * @param name Name used to identify the group
     * @return The current {@link RegexBuilder} object, for method chaining
     */
    public RegexBuilder startNamedGroup(final String name) {
        openGroupCount++;
        return addPart("(?<" + name + ">");
    }

    /**
     * End the innermost group previously started with {@link #startGroup()}, {@link #startNonCapturingGroup()} or
     * {@link #startNamedGroup(String)}.
     *
     * @return The current {@link RegexBuilder} object, for method chaining
     * @throws RegexBuilderException A group has not been started
     */
    public RegexBuilder endGroup() {
        return endGroup(null);
    }

    /**
     * End the innermost group previously started with {@link #startGroup()}, {@link #startNonCapturingGroup()} or
     * {@link #startNamedGroup(String)}.
     *
     * @param quantifier Quantifier to apply to this group
     * @return The current {@link RegexBuilder} object, for method chaining
     * @throws RegexBuilderException A group has not been started
     */
    public RegexBuilder endGroup(final RegexQuantifier quantifier) {
        if (openGroupCount == 0) {
            throw new RegexBuilderException("Cannot call endGroup() until a group has been started with startGroup()",
                    stringBuilder);
        }

        openGroupCount--;
        return addPart(")", quantifier);
    }


    // PRIVATE

    private RegexBuilder addPart(final String part) {
        return addPart(part, null);
    }

    private RegexBuilder addPart(final String part, final RegexQuantifier quantifier) {
        stringBuilder.append(part);
        if (quantifier != null) {
            stringBuilder.append(quantifier);
        }
        return this;
    }

    private RegexBuilder addPartInNonCapturingGroup(final String part, final RegexQuantifier quantifier) {
        return addPart("(?:" + part + ")", quantifier);
    }

    private String makeSafeForCharacterClass(final String s) {
        String result = s
                // Replace ] with \]
                .replace("]", "\\]")
                // Replace - with \-
                .replace("-", "\\-");

        // replace ^ with \^ if it occurs at the start of the string
        if (result.startsWith("^")) {
            result = "\\" + result;
        }

        return result;
    }

    private static String makeSafeForRegex(final String s) {
        return (s == null) ? "" : s
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
    }
}
