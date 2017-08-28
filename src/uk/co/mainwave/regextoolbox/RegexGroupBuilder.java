package uk.co.mainwave.regextoolbox;

import java.util.regex.Pattern;

/**
 * Derived class to represent a group within a regex
 */
final class RegexGroupBuilder extends RegexBuilder
{
    RegexGroupBuilder(RegexBuilder parent)
    {
        super(parent);
    }

    public RegexBuilder endGroup(RegexQuantifier quantifier)
    {
        stringBuilder.append(")");
        addQuantifier(quantifier);
        return parent;
    }

    @Override
    public Pattern buildRegex(final RegexOptions... options) throws RegexBuilderException
    {
        throw new RegexBuilderException("At least one group is still open", stringBuilder);
    }
}
