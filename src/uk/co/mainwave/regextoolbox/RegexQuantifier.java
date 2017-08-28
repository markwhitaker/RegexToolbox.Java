package uk.co.mainwave.regextoolbox;

/**
 * Quantifiers that can be applied to regex elements or groups
 */
public class RegexQuantifier
{
    private String regexString;

    RegexQuantifier(String regexString)
    {
        this.regexString = regexString;
    }

    public static RegexGreedyQuantifier zeroOrMore()
    {
        return new RegexGreedyQuantifier("*");
    }

    public static RegexGreedyQuantifier oneOrMore()
    {
        return new RegexGreedyQuantifier("+");
    }

    public static RegexGreedyQuantifier noneOrOne()
    {
        return new RegexGreedyQuantifier("?");
    }

    public static RegexQuantifier exactly(int times)
    {
        return new RegexQuantifier("{" + times + "}");
    }

    public static RegexGreedyQuantifier atLeast(int minimum)
    {
        return new RegexGreedyQuantifier("{" + minimum + ",}");
    }

    public static RegexGreedyQuantifier noMoreThan(int maximum)
    {
        return new RegexGreedyQuantifier("{0," + maximum + "}");
    }

    public static RegexGreedyQuantifier between(int minimum, int maximum)
    {
        return new RegexGreedyQuantifier("{" + minimum + "," + maximum + "}");
    }

    @Override
    public String toString()
    {
        return regexString;
    }

    void makeNonGreedy()
    {
        regexString += "?";
    }
}
