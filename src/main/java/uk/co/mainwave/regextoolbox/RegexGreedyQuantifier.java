package uk.co.mainwave.regextoolbox;

/**
 * A quantifier which defaults to greedy matching: in other words, if used
 * to match a variable number of elements it will match as many as possible.
 */
public final class RegexGreedyQuantifier extends RegexQuantifier {
    RegexGreedyQuantifier(final String name, final String regexString) {
        super(name, regexString);
    }

    /**
     * Get a non-greedy version of this quantifier: in other words, if used
     * to match a variable number of elements it will match as few as possible.
     *
     * @return A non-greedy quantifier
     */
    public RegexQuantifier butAsFewAsPossible() {
        makeNonGreedy();
        return this;
    }
}
