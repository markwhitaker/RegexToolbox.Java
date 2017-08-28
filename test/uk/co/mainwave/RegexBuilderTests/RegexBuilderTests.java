package uk.co.mainwave.RegexBuilderTests;


import org.junit.Assert;
import org.junit.Test;
import uk.co.mainwave.RegexToolbox.RegexBuilder;
import uk.co.mainwave.RegexToolbox.RegexBuilderException;

import java.util.regex.Pattern;

public class RegexBuilderTests
{
    @Test
    public void TestSimpleText() throws RegexBuilderException
    {
        Pattern regex = new RegexBuilder()
                .text("cat", null)
                .buildRegex();

        Assert.assertEquals("cat", regex.toString());
        Assert.assertTrue(regex.matcher("cat").find());
        Assert.assertTrue(regex.matcher("scatter").find());
        Assert.assertFalse(regex.matcher("Cat").find());
        Assert.assertFalse(regex.matcher("dog").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

}
