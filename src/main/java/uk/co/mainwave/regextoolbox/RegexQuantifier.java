package uk.co.mainwave.regextoolbox;

/**
 * Quantifiers that can be applied to regex elements or groups
 */
public class RegexQuantifier {
    private String regexString;

    RegexQuantifier(final String regexString) {
        this.regexString = regexString;
    }

    /**
     * Quantifier to match the preceding element zero or more times
     *
     * @return A greedy quantifier: use {@link RegexGreedyQuantifier#butAsFewAsPossible()} to make it non-greedy
     */
    public static RegexGreedyQuantifier zeroOrMore() {
        return new RegexGreedyQuantifier("*");
    }

    /**
     * Quantifier to match the preceding element one or more times
     *
     * @return A greedy quantifier: use {@link RegexGreedyQuantifier#butAsFewAsPossible()} to make it non-greedy
     */
    public static RegexGreedyQuantifier oneOrMore() {
        return new RegexGreedyQuantifier("+");
    }

    /**
     * Quantifier to match the preceding element once or not at all
     * @deprecated Use {@link #zeroOrOne()} instead
     *
     * @return A greedy quantifier: use {@link RegexGreedyQuantifier#butAsFewAsPossible()} to make it non-greedy
     */
    @Deprecated
    public static RegexGreedyQuantifier noneOrOne() {
        return new RegexGreedyQuantifier("?");
    }

    /**
     * Quantifier to match the preceding element once or not at all
     *
     * @return A greedy quantifier: use {@link RegexGreedyQuantifier#butAsFewAsPossible()} to make it non-greedy
     */
    public static RegexGreedyQuantifier zeroOrOne() {
        return new RegexGreedyQuantifier("?");
    }

    /**
     * Quantifier to match an exact number of occurrences of the preceding element
     *
     * @param times The exact number of occurrences to match
     * @return A non-greedy quantifier
     */
    public static RegexQuantifier exactly(final int times) {
        return new RegexQuantifier(
                "{" + times + "}");
    }

    /**
     * Quantifier to match at least a minimum number of occurrences of the preceding element
     *
     * @param minimum The minimum number of occurrences to match
     * @return A greedy quantifier: use {@link RegexGreedyQuantifier#butAsFewAsPossible()} to make it non-greedy
     */
    public static RegexGreedyQuantifier atLeast(final int minimum) {
        return new RegexGreedyQuantifier(
                "{" + minimum + ",}");
    }

    /**
     * Quantifier to match no more than a maximum number of occurrences of the preceding element
     *
     * @param maximum The maximum number of occurrences to match
     * @return A greedy quantifier: use {@link RegexGreedyQuantifier#butAsFewAsPossible()} to make it non-greedy
     */
    public static RegexGreedyQuantifier noMoreThan(final int maximum) {
        return new RegexGreedyQuantifier(
                "{0," + maximum + "}");
    }

    /**
     * Quantifier to match at least a minimum, and no more than a maximum, occurrences of the preceding element
     *
     * @param minimum The minimum number of occurrences to match
     * @param maximum The maximum number of occurrences to match
     * @return A greedy quantifier: use {@link RegexGreedyQuantifier#butAsFewAsPossible()} to make it non-greedy
     */
    public static RegexGreedyQuantifier between(final int minimum, final int maximum) {
        return new RegexGreedyQuantifier(
                "{" + minimum + "," + maximum + "}");
    }

    @Override
    public final String toString() {
        return regexString;
    }

    final void makeNonGreedy() {
        regexString += "?";
    }
}
