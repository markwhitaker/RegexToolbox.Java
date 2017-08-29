package uk.co.mainwave.regextoolbox;

/**
 * A quantifier which defaults to greedy matching: in other words, if used
 * to match a variable number of elements it will match as many as possible.
 */
public final class RegexGreedyQuantifier extends RegexQuantifier
{
    RegexGreedyQuantifier(String regexString)
    {
        super(regexString);
    }

    /**
     * Get a non-greedy version of this quantifier: in other words, if used
     * to match a variable number of elements it will match as few as possible.
     */
    public RegexGreedyQuantifier butAsFewAsPossible()
    {
        makeNonGreedy();
        return this;
    }
}
