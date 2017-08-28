package uk.co.mainwave.RegexToolbox;

import java.util.regex.Pattern;

public class RegexGroupBuilder extends RegexBuilder
{
    public RegexGroupBuilder(RegexBuilder parent)
    {
        super(parent);
    }

    public RegexBuilder endGroup(RegexQuantifier quantifier)
    {
        stringBuilder.append(")");
        addQuantifier(quantifier);
        return parent;
    }

    public Pattern BuildRegex(final RegexOptions... options) throws RegexBuilderException
    {
        throw new RegexBuilderException("At least one group is still open", stringBuilder);
    }
}
