package uk.co.mainwave.RegexToolbox;

final class RegexGreedyQuantifier extends RegexQuantifier
{
    RegexGreedyQuantifier(String regexString)
    {
        super(regexString);
    }

    public RegexGreedyQuantifier butAsFewAsPossible()
    {
        addToRegexString("?");
        return this;
    }
}
