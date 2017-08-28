package uk.co.mainwave.regextoolbox;

public final class RegexGreedyQuantifier extends RegexQuantifier
{
    RegexGreedyQuantifier(String regexString)
    {
        super(regexString);
    }

    public RegexGreedyQuantifier butAsFewAsPossible()
    {
        makeNonGreedy();
        return this;
    }
}
