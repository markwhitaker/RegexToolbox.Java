package uk.co.mainwave.RegexToolbox;

import java.util.regex.Pattern;

public class RegexBuilder
{
    protected final StringBuilder stringBuilder;
    protected final RegexBuilder parent;

    public RegexBuilder()
    {
        parent = null;
        stringBuilder = new StringBuilder();
    }

    public RegexBuilder(RegexBuilder parent)
    {
        this.parent = parent;
        stringBuilder = parent.stringBuilder;
    }

    public Pattern buildRegex(final RegexOptions... options) throws RegexBuilderException
    {
        int flags = 0;

        for (RegexOptions option : options)
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
        stringBuilder.delete(0, stringBuilder.length() - 1);
        return pattern;
    }

    public RegexBuilder text(String text, RegexQuantifier quantifier) throws RegexBuilderException
    {
        return regexText(makeSafeForRegex(text), quantifier);
    }

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

    public RegexBuilder anyCharacter(final RegexQuantifier quantifier)
    {
        stringBuilder.append(".");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder whitespace(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\s");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder nonWhitespace(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\S");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder digit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\d");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder nonDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\D");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder letter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[a-zA-Z]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder nonLetter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[^a-zA-Z]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder uppercaseLetter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[A-Z]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder lowercaseLetter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[a-z]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder letterOrDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[a-zA-Z0-9]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder nonLetterOrDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[^a-zA-Z0-9]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder hexDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[0-9A-Fa-f]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder uppercaseHexDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[0-9A-F]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder lowercaseHexDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[0-9a-f]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder nonHexDigit(final RegexQuantifier quantifier)
    {
        stringBuilder.append("[^0-9A-Fa-f]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder wordCharacter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\w");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder nonWordCharacter(final RegexQuantifier quantifier)
    {
        stringBuilder.append("\\W");
        addQuantifier(quantifier);
        return this;
    }

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

    public RegexBuilder anyCharacterExcept(final String characters, RegexQuantifier quantifier)
    {
        // Build a character class, remembering to escape any ] character if passed in
        stringBuilder
                .append("[^")
                .append(makeSafeForCharacterClass(characters))
                .append("]");
        addQuantifier(quantifier);
        return this;
    }

    public RegexBuilder anyOf(String[] strings, RegexQuantifier quantifier) throws RegexBuilderException
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

    public RegexBuilder startOfString()
    {
        stringBuilder.append("^");
        return this;
    }

    public RegexBuilder endOfString()
    {
        stringBuilder.append("$");
        return this;
    }

    public RegexBuilder wordBoundary()
    {
        stringBuilder.append("\\b");
        return this;
    }


    // GROUPS

    public RegexBuilder startGroup()
    {
        stringBuilder.append("(");
        return new RegexGroupBuilder(this);
    }

    public RegexBuilder startNonCapturingGroup()
    {
        stringBuilder.append("(?:");
        return new RegexGroupBuilder(this);
    }

    public RegexBuilder startNamedGroup(final String name)
    {
        stringBuilder
                .append("(?<")
                .append(name)
                .append(">");
        return new RegexGroupBuilder(this);
    }

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
