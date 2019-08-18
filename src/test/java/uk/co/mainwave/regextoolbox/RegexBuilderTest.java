package uk.co.mainwave.regextoolbox;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexBuilderTest {
    @Test
    public void testSimpleText() throws RegexBuilderException {
        Pattern regex = new RegexBuilder()
                .text("cat")
                .buildRegex();

        Assert.assertEquals("cat", regex.toString());
        Assert.assertTrue(regex.matcher("cat").matches());
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


    @Test
    public void testSimpleTextWithQuantifier() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("cat", RegexQuantifier.exactly(2))
                .buildRegex();

        Assert.assertEquals("(?:cat){2}", regex.toString());
        Assert.assertFalse(regex.matcher("cat").find());
        Assert.assertTrue(regex.matcher("catcat").matches());
        Assert.assertTrue(regex.matcher("catcatcat").find());
        Assert.assertFalse(regex.matcher("scatter").find());
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

    @Test
    public void testSimpleTextCaseInsensitive() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("cat")
                .buildRegex(RegexOptions.IGNORE_CASE);

        Assert.assertEquals("cat", regex.toString());
        Assert.assertTrue(regex.matcher("cat").matches());
        Assert.assertTrue(regex.matcher("scatter").find());
        Assert.assertTrue(regex.matcher("Cat").matches());
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

    @Test
    public void testSimpleTextWithRegexCharacters() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("\\.+*?[]{}()|^$")
                .buildRegex();

        Assert.assertEquals("\\\\\\.\\+\\*\\?\\[\\]\\{\\}\\(\\)\\|\\^\\$", regex.toString());
        Assert.assertTrue(regex.matcher("\\.+*?[]{}()|^$").find());

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

    @Test
    public void testRegexText() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .regexText("^\\scat\\b")
                .buildRegex();

        Assert.assertEquals("^\\scat\\b", regex.toString());
        Assert.assertTrue(regex.matcher(" cat").find());
        Assert.assertTrue(regex.matcher(" cat.").find());
        Assert.assertTrue(regex.matcher("\tcat ").find());
        Assert.assertTrue(regex.matcher(" cat-").find());
        Assert.assertTrue(regex.matcher(" cat ").find());
        Assert.assertFalse(regex.matcher("cat").find());
        Assert.assertFalse(regex.matcher(" catheter").find());

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

    @Test
    public void testAnyCharacter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacter()
                .buildRegex();

        Assert.assertEquals(".", regex.toString());
        Assert.assertTrue(regex.matcher(" ").find());
        Assert.assertTrue(regex.matcher("a").find());
        Assert.assertTrue(regex.matcher("1").find());
        Assert.assertTrue(regex.matcher("\\").find());
        Assert.assertFalse(regex.matcher("").find());
        Assert.assertFalse(regex.matcher("\n").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testWhitespace() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .whitespace()
                .buildRegex();

        Assert.assertEquals("\\s", regex.toString());
        Assert.assertTrue(regex.matcher(" ").find());
        Assert.assertTrue(regex.matcher("\t").find());
        Assert.assertTrue(regex.matcher("\r").find());
        Assert.assertTrue(regex.matcher("\n").find());
        Assert.assertTrue(regex.matcher("\r\n").find());
        Assert.assertTrue(regex.matcher("\t \t").find());
        Assert.assertTrue(regex.matcher("                hi!").find());
        Assert.assertFalse(regex.matcher("cat").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonWhitespace() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonWhitespace()
                .buildRegex();

        Assert.assertEquals("\\S", regex.toString());
        Assert.assertTrue(regex.matcher("a").find());
        Assert.assertTrue(regex.matcher("1").find());
        Assert.assertTrue(regex.matcher("-").find());
        Assert.assertTrue(regex.matcher("*").find());
        Assert.assertTrue(regex.matcher("abc").find());
        Assert.assertTrue(regex.matcher("                hi!").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("\t").find());
        Assert.assertFalse(regex.matcher("\r").find());
        Assert.assertFalse(regex.matcher("\n").find());
        Assert.assertFalse(regex.matcher("\t\t\r\n   ").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testSpace() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .space()
                .buildRegex();

        Assert.assertEquals(" ", regex.toString());
        Assert.assertTrue(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("\t").find());
        Assert.assertFalse(regex.matcher("\r").find());
        Assert.assertFalse(regex.matcher("\n").find());
        Assert.assertFalse(regex.matcher("\r\n").find());
        Assert.assertTrue(regex.matcher("\t \t").find());
        Assert.assertTrue(regex.matcher("                hi!").find());
        Assert.assertFalse(regex.matcher("cat").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testTab() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .tab()
                .buildRegex();

        Assert.assertEquals("\\t", regex.toString());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertTrue(regex.matcher("\t").find());
        Assert.assertFalse(regex.matcher("\r").find());
        Assert.assertFalse(regex.matcher("\n").find());
        Assert.assertFalse(regex.matcher("\r\n").find());
        Assert.assertTrue(regex.matcher("\t \t").find());
        Assert.assertFalse(regex.matcher("                hi!").find());
        Assert.assertFalse(regex.matcher("cat").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
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

    @Test
    public void testLineFeed() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .lineFeed()
                .buildRegex();

        Assert.assertEquals("\\n", regex.toString());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("\t").find());
        Assert.assertFalse(regex.matcher("\r").find());
        Assert.assertTrue(regex.matcher("\n").find());
        Assert.assertTrue(regex.matcher("\r\n").find());
        Assert.assertFalse(regex.matcher("\t \t").find());
        Assert.assertFalse(regex.matcher("                hi!").find());
        Assert.assertFalse(regex.matcher("cat").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
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

    @Test
    public void testCarriageReturn() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .carriageReturn()
                .buildRegex();

        Assert.assertEquals("\\r", regex.toString());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("\t").find());
        Assert.assertTrue(regex.matcher("\r").find());
        Assert.assertFalse(regex.matcher("\n").find());
        Assert.assertTrue(regex.matcher("\r\n").find());
        Assert.assertFalse(regex.matcher("\t \t").find());
        Assert.assertFalse(regex.matcher("                hi!").find());
        Assert.assertFalse(regex.matcher("cat").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
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

    @Test
    public void testDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit()
                .buildRegex();

        Assert.assertEquals("\\d", regex.toString());
        Assert.assertTrue(regex.matcher("1").find());
        Assert.assertTrue(regex.matcher("0").find());
        Assert.assertTrue(regex.matcher("999").find());
        Assert.assertTrue(regex.matcher("there's a digit in here s0mewhere").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("abc").find());
        Assert.assertFalse(regex.matcher("xFFF").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonDigit()
                .buildRegex();

        Assert.assertEquals("\\D", regex.toString());
        Assert.assertTrue(regex.matcher(" 1").find());
        Assert.assertTrue(regex.matcher("a0").find());
        Assert.assertTrue(regex.matcher("999_").find());
        Assert.assertTrue(regex.matcher("1,000").find());
        Assert.assertTrue(regex.matcher("there's a digit in here s0mewhere").find());
        Assert.assertFalse(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("0").find());
        Assert.assertFalse(regex.matcher("999").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLetter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z]", regex.toString());
        Assert.assertTrue(regex.matcher("a").find());
        Assert.assertTrue(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        z").find());
        Assert.assertTrue(regex.matcher("text with spaces").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("%").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonLetter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonLetter()
                .buildRegex();

        Assert.assertEquals("[^a-zA-Z]", regex.toString());
        Assert.assertTrue(regex.matcher(" 1").find());
        Assert.assertTrue(regex.matcher("0").find());
        Assert.assertTrue(regex.matcher("999_").find());
        Assert.assertTrue(regex.matcher("1,000").find());
        Assert.assertTrue(regex.matcher("text with spaces").find());
        Assert.assertFalse(regex.matcher("a").find());
        Assert.assertFalse(regex.matcher("ZZZ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testUppercaseLetter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .uppercaseLetter()
                .buildRegex();

        Assert.assertEquals("[A-Z]", regex.toString());
        Assert.assertTrue(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        Z").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("%").find());
        Assert.assertFalse(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLowercaseLetter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .lowercaseLetter()
                .buildRegex();

        Assert.assertEquals("[a-z]", regex.toString());
        Assert.assertTrue(regex.matcher("a").find());
        Assert.assertTrue(regex.matcher("        z").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("%").find());
        Assert.assertFalse(regex.matcher("S").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLetterOrDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letterOrDigit()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z0-9]", regex.toString());
        Assert.assertTrue(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        Z").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertTrue(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("%").find());
        Assert.assertFalse(regex.matcher("_").find());
        Assert.assertTrue(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonLetterOrDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonLetterOrDigit()
                .buildRegex();

        Assert.assertEquals("[^a-zA-Z0-9]", regex.toString());
        Assert.assertFalse(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        Z").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertTrue(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("1").find());
        Assert.assertTrue(regex.matcher("%").find());
        Assert.assertTrue(regex.matcher("_").find());
        Assert.assertFalse(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testHexDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .hexDigit()
                .buildRegex();

        Assert.assertEquals("[0-9A-Fa-f]", regex.toString());
        Assert.assertTrue(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        f").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertTrue(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("%").find());
        Assert.assertFalse(regex.matcher("_").find());
        Assert.assertFalse(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testUppercaseHexDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .uppercaseHexDigit()
                .buildRegex();

        Assert.assertEquals("[0-9A-F]", regex.toString());
        Assert.assertTrue(regex.matcher("A").find());
        Assert.assertFalse(regex.matcher("        f").find());
        Assert.assertFalse(regex.matcher("text with Spaces").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertTrue(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("%").find());
        Assert.assertFalse(regex.matcher("_").find());
        Assert.assertFalse(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLowercaseHexDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .lowercaseHexDigit()
                .buildRegex();

        Assert.assertEquals("[0-9a-f]", regex.toString());
        Assert.assertFalse(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        f").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertTrue(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("%").find());
        Assert.assertFalse(regex.matcher("_").find());
        Assert.assertFalse(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonHexDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonHexDigit()
                .buildRegex();

        Assert.assertEquals("[^0-9A-Fa-f]", regex.toString());
        Assert.assertFalse(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        f").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertTrue(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("1").find());
        Assert.assertTrue(regex.matcher("%").find());
        Assert.assertTrue(regex.matcher("_").find());
        Assert.assertTrue(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testWordCharacter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .wordCharacter()
                .buildRegex();

        Assert.assertEquals("\\w", regex.toString());
        Assert.assertTrue(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        Z").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertTrue(regex.matcher("1").find());
        Assert.assertFalse(regex.matcher("%").find());
        Assert.assertTrue(regex.matcher("_").find());
        Assert.assertTrue(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonWordCharacter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonWordCharacter()
                .buildRegex();

        Assert.assertEquals("\\W", regex.toString());
        Assert.assertFalse(regex.matcher("A").find());
        Assert.assertTrue(regex.matcher("        Z").find());
        Assert.assertTrue(regex.matcher("text with Spaces").find());
        Assert.assertTrue(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("1").find());
        Assert.assertTrue(regex.matcher("%").find());
        Assert.assertFalse(regex.matcher("_").find());
        Assert.assertFalse(regex.matcher("s").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacterFrom() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("cat")
                .buildRegex();

        Assert.assertEquals("[cat]", regex.toString());
        Assert.assertTrue(regex.matcher("cat").find());
        Assert.assertTrue(regex.matcher("parrot").find());
        Assert.assertTrue(regex.matcher("tiger").find());
        Assert.assertTrue(regex.matcher("cow").find());
        Assert.assertFalse(regex.matcher("CAT").find());
        Assert.assertFalse(regex.matcher("dog").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacterFromWithCaretAtStart() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("^abc")
                .buildRegex();

        Assert.assertEquals("[\\^abc]", regex.toString());
        Assert.assertTrue(regex.matcher("jazz").find());
        Assert.assertTrue(regex.matcher("_^_").find());
        Assert.assertTrue(regex.matcher("oboe").find());
        Assert.assertTrue(regex.matcher("cue").find());
        Assert.assertFalse(regex.matcher("CAT").find());
        Assert.assertFalse(regex.matcher("dog").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacterFromWithCaretNotAtStart() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("a^bc")
                .buildRegex();

        Assert.assertEquals("[a^bc]", regex.toString());
        Assert.assertTrue(regex.matcher("jazz").find());
        Assert.assertTrue(regex.matcher("_^_").find());
        Assert.assertTrue(regex.matcher("oboe").find());
        Assert.assertTrue(regex.matcher("cue").find());
        Assert.assertFalse(regex.matcher("CAT").find());
        Assert.assertFalse(regex.matcher("dog").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacterExcept() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterExcept("cat")
                .buildRegex();

        Assert.assertEquals("[^cat]", regex.toString());
        Assert.assertFalse(regex.matcher("cat").find());
        Assert.assertFalse(regex.matcher("tata").find());
        Assert.assertTrue(regex.matcher("parrot").find());
        Assert.assertTrue(regex.matcher("tiger").find());
        Assert.assertTrue(regex.matcher("cow").find());
        Assert.assertTrue(regex.matcher("CAT").find());
        Assert.assertTrue(regex.matcher("dog").find());
        Assert.assertTrue(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyOf() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyOf(new String[]{"cat", "dog", "|"})
                .buildRegex();

        Assert.assertEquals("(?:cat|dog|\\|)", regex.toString());
        Assert.assertFalse(regex.matcher("ca do").find());
        Assert.assertTrue(regex.matcher("cat").find());
        Assert.assertTrue(regex.matcher("dog").find());
        Assert.assertTrue(regex.matcher("|").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
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

    @Test
    public void testAnyOfNullEmptyOrSingle() throws RegexBuilderException {
        final Pattern anyOfNullRegex = new RegexBuilder()
                .anyOf(null)
                .buildRegex();

        final Pattern anyOfEmptyRegex = new RegexBuilder()
                .anyOf(new String[0])
                .buildRegex();

        final Pattern anyOfSingleRegex = new RegexBuilder()
                .anyOf(new String[]{"cat"})
                .buildRegex();

        Assert.assertEquals("", anyOfNullRegex.toString());
        Assert.assertEquals("", anyOfEmptyRegex.toString());
        Assert.assertEquals("cat", anyOfSingleRegex.toString());
    }

    @Test
    public void testStartOfString() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .startOfString()
                .text("a")
                .buildRegex();

        Assert.assertEquals("^a", regex.toString());
        Assert.assertTrue(regex.matcher("a").find());
        Assert.assertTrue(regex.matcher("aA").find());
        Assert.assertTrue(regex.matcher("a_").find());
        Assert.assertTrue(regex.matcher("a        big gap").find());
        Assert.assertFalse(regex.matcher(" a space before").find());
        Assert.assertFalse(regex.matcher("A capital letter").find());
        Assert.assertFalse(regex.matcher("Aa").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
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

    @Test
    public void testEndOfString() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("z")
                .endOfString()
                .buildRegex();

        Assert.assertEquals("z$", regex.toString());
        Assert.assertTrue(regex.matcher("z").find());
        Assert.assertTrue(regex.matcher("zzz").find());
        Assert.assertTrue(regex.matcher("fizz buzz").find());
        Assert.assertFalse(regex.matcher("buzz!").find());
        Assert.assertFalse(regex.matcher("zzz ").find());
        Assert.assertFalse(regex.matcher("zZ").find());
        Assert.assertFalse(regex.matcher("z ").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
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

    @Test
    public void testWordBoundary() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("a")
                .wordBoundary()
                .buildRegex();

        Assert.assertEquals("a\\b", regex.toString());
        Assert.assertTrue(regex.matcher("a").find());
        Assert.assertTrue(regex.matcher("spa").find());
        Assert.assertTrue(regex.matcher("papa don't preach").find());
        Assert.assertTrue(regex.matcher("a dog").find());
        Assert.assertTrue(regex.matcher("a-dog").find());
        Assert.assertFalse(regex.matcher("an apple").find());
        Assert.assertFalse(regex.matcher("asp").find());
        Assert.assertFalse(regex.matcher("a1b").find());
        Assert.assertFalse(regex.matcher("a_b").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

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

    @Test
    public void testSingleGroup() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacter(RegexQuantifier.zeroOrMore())
                .startGroup()
                .letter()
                .digit()
                .endGroup()
                .buildRegex();

        Assert.assertEquals(".*([a-zA-Z]\\d)", regex.toString());

        Matcher match = regex.matcher("Class A1");
        Assert.assertTrue(match.find());
        Assert.assertEquals("Class A1", match.group(0));
        Assert.assertEquals("A1", match.group(1));

        match = regex.matcher("he likes F1 racing");
        Assert.assertTrue(match.find());
        Assert.assertEquals("he likes F1", match.group(0));
        Assert.assertEquals("F1", match.group(1));

        match = regex.matcher("A4 paper");
        Assert.assertTrue(match.find());
        Assert.assertEquals("A4", match.group(0));
        Assert.assertEquals("A4", match.group(1));

        match = regex.matcher("A 4-legged dog");
        Assert.assertFalse(match.find());

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
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testRepeatGroup() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .startGroup()
                .letter()
                .digit()
                .endGroup()
                .buildRegex();

        Assert.assertEquals("([a-zA-Z]\\d)", regex.toString());

        final Matcher matcher = regex.matcher("Class A1 f2 ZZ88");
        final List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        Assert.assertEquals(3, matches.size());
        Assert.assertEquals("A1", matches.get(0));
        Assert.assertEquals("f2", matches.get(1));
        Assert.assertEquals("Z8", matches.get(2));

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
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNamedGroup() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .lowercaseLetter(RegexQuantifier.oneOrMore())
                .startNamedGroup("test123")
                .digit(RegexQuantifier.oneOrMore())
                .endGroup()
                .lowercaseLetter(RegexQuantifier.oneOrMore())
                .buildRegex();

        Assert.assertEquals("[a-z]+(?<test123>\\d+)[a-z]+", regex.toString());

        Matcher match = regex.matcher("a99z");
        Assert.assertTrue(match.find());
        Assert.assertEquals("a99z", match.group(0));
        Assert.assertEquals("99", match.group(1));
        Assert.assertEquals("99", match.group("test123"));

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
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonCapturingGroup() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .lowercaseLetter(RegexQuantifier.oneOrMore())
                .startNonCapturingGroup()
                .digit(RegexQuantifier.oneOrMore())
                .endGroup()
                .lowercaseLetter(RegexQuantifier.oneOrMore())
                .buildRegex();

        Assert.assertEquals("[a-z]+(?:\\d+)[a-z]+", regex.toString());

        Matcher match = regex.matcher("a99z");
        Assert.assertTrue(match.find());
        Assert.assertEquals("a99z", match.group(0));
        Assert.assertEquals(0, match.groupCount());

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
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testMultipleGroups() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .startGroup()
                .anyCharacter(RegexQuantifier.zeroOrMore())
                .endGroup()
                .startGroup()
                .letter()
                .digit()
                .endGroup()
                .buildRegex();

        Assert.assertEquals("(.*)([a-zA-Z]\\d)", regex.toString());

        Matcher match = regex.matcher("Class A1");
        Assert.assertTrue(match.find());
        Assert.assertEquals("Class A1", match.group(0));
        Assert.assertEquals("Class ", match.group(1));
        Assert.assertEquals("A1", match.group(2));

        match = regex.matcher("he likes F1 racing");
        Assert.assertTrue(match.find());
        Assert.assertEquals("he likes F1", match.group(0));
        Assert.assertEquals("he likes ", match.group(1));
        Assert.assertEquals("F1", match.group(2));

        match = regex.matcher("A4 paper");
        Assert.assertTrue(match.find());
        Assert.assertEquals("A4", match.group(0));
        Assert.assertEquals("", match.group(1));
        Assert.assertEquals("A4", match.group(2));

        match = regex.matcher("A 4-legged dog");
        Assert.assertFalse(match.find());

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
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNestedGroups() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacter() // Omit first character from groups
                .startGroup()
                .anyCharacter(RegexQuantifier.zeroOrMore())
                .startGroup()
                .letter()
                .digit()
                .endGroup()
                .endGroup()
                .buildRegex();

        Assert.assertEquals(".(.*([a-zA-Z]\\d))", regex.toString());

        Matcher match = regex.matcher("Class A1");
        Assert.assertTrue(match.find());
        Assert.assertEquals("Class A1", match.group(0));
        Assert.assertEquals("lass A1", match.group(1));
        Assert.assertEquals("A1", match.group(2));

        match = regex.matcher("he likes F1 racing");
        Assert.assertTrue(match.find());
        Assert.assertEquals("he likes F1", match.group(0));
        Assert.assertEquals("e likes F1", match.group(1));
        Assert.assertEquals("F1", match.group(2));

        match = regex.matcher(" A4 paper");
        Assert.assertTrue(match.find());
        Assert.assertEquals(" A4", match.group(0));
        Assert.assertEquals("A4", match.group(1));
        Assert.assertEquals("A4", match.group(2));

        match = regex.matcher("A 4-legged dog");
        Assert.assertFalse(match.find());

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
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testZeroOrMore() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.zeroOrMore())
                .letter()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z]\\d*[a-zA-Z]", regex.toString());
        Assert.assertTrue(regex.matcher("ab").find());
        Assert.assertTrue(regex.matcher("a1b").find());
        Assert.assertTrue(regex.matcher("a123b").find());
        Assert.assertFalse(regex.matcher("a 1 b").find());
        Assert.assertFalse(regex.matcher("a b").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testOneOrMore() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.oneOrMore())
                .letter()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z]\\d+[a-zA-Z]", regex.toString());
        Assert.assertFalse(regex.matcher("ab").find());
        Assert.assertTrue(regex.matcher("a1b").find());
        Assert.assertTrue(regex.matcher("a123b").find());
        Assert.assertFalse(regex.matcher("a 1 b").find());
        Assert.assertFalse(regex.matcher("a b").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

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
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testOneOrNone() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.noneOrOne())
                .letter()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z]\\d?[a-zA-Z]", regex.toString());
        Assert.assertTrue(regex.matcher("ab").find());
        Assert.assertTrue(regex.matcher("a1b").find());
        Assert.assertFalse(regex.matcher("a123b").find());
        Assert.assertFalse(regex.matcher("a 1 b").find());
        Assert.assertFalse(regex.matcher("a b").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testExactlyNTimes() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.exactly(3))
                .letter()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z]\\d{3}[a-zA-Z]", regex.toString());
        Assert.assertFalse(regex.matcher("ab").find());
        Assert.assertFalse(regex.matcher("a1b").find());
        Assert.assertFalse(regex.matcher("a12b").find());
        Assert.assertTrue(regex.matcher("a123b").find());
        Assert.assertFalse(regex.matcher("a1234b").find());
        Assert.assertFalse(regex.matcher("a12345b").find());
        Assert.assertFalse(regex.matcher("a 1 b").find());
        Assert.assertFalse(regex.matcher("a b").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

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

    @Test
    public void testAtLeastQuantifier() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.atLeast(3))
                .letter()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z]\\d{3,}[a-zA-Z]", regex.toString());
        Assert.assertFalse(regex.matcher("ab").find());
        Assert.assertFalse(regex.matcher("a1b").find());
        Assert.assertFalse(regex.matcher("a12b").find());
        Assert.assertTrue(regex.matcher("a123b").find());
        Assert.assertTrue(regex.matcher("a1234b").find());
        Assert.assertTrue(regex.matcher("a12345b").find());
        Assert.assertFalse(regex.matcher("a 1 b").find());
        Assert.assertFalse(regex.matcher("a b").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

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

    @Test
    public void testNoMoreThanQuantifier() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.noMoreThan(3))
                .letter()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z]\\d{0,3}[a-zA-Z]", regex.toString());
        Assert.assertTrue(regex.matcher("ab").find());
        Assert.assertTrue(regex.matcher("a1b").find());
        Assert.assertTrue(regex.matcher("a12b").find());
        Assert.assertTrue(regex.matcher("a123b").find());
        Assert.assertFalse(regex.matcher("a1234b").find());
        Assert.assertFalse(regex.matcher("a12345b").find());
        Assert.assertFalse(regex.matcher("a 1 b").find());
        Assert.assertFalse(regex.matcher("a b").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testBetweenMinMaxTimes() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.between(2, 4))
                .letter()
                .buildRegex();

        Assert.assertEquals("[a-zA-Z]\\d{2,4}[a-zA-Z]", regex.toString());
        Assert.assertFalse(regex.matcher("ab").find());
        Assert.assertFalse(regex.matcher("a1b").find());
        Assert.assertTrue(regex.matcher("a12b").find());
        Assert.assertTrue(regex.matcher("a123b").find());
        Assert.assertTrue(regex.matcher("a1234b").find());
        Assert.assertFalse(regex.matcher("a12345b").find());
        Assert.assertFalse(regex.matcher("a 1 b").find());
        Assert.assertFalse(regex.matcher("a b").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

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

    @Test
    public void testOptionMultiLine() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .startOfString()
                .text("find me!")
                .endOfString()
                .buildRegex(RegexOptions.MULTILINE);

        Assert.assertEquals("^find me!$", regex.toString());
        Assert.assertEquals(Pattern.MULTILINE, (regex.flags() & Pattern.MULTILINE));
        Assert.assertTrue(regex.matcher("find me!").find());
        Assert.assertTrue(regex.matcher("find me!\nline 2").find());
        Assert.assertTrue(regex.matcher("line 1\nfind me!").find());
        Assert.assertTrue(regex.matcher("line 1\nfind me!\nline 3").find());
        Assert.assertFalse(regex.matcher(" find me!").find());
        Assert.assertFalse(regex.matcher("find me! ").find());
        Assert.assertFalse(regex.matcher(" find me! ").find());
        Assert.assertFalse(regex.matcher("").find());

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

    @Test
    public void testOptionIGNORE_CASE() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("cat")
                .buildRegex(RegexOptions.IGNORE_CASE);

        Assert.assertEquals("[cat]", regex.toString());
        Assert.assertEquals(Pattern.CASE_INSENSITIVE, (regex.flags() & Pattern.CASE_INSENSITIVE));
        Assert.assertTrue(regex.matcher("cat").find());
        Assert.assertTrue(regex.matcher("tiger").find());
        Assert.assertTrue(regex.matcher("Ant").find());
        Assert.assertTrue(regex.matcher("CAT").find());
        Assert.assertTrue(regex.matcher("                A").find());
        Assert.assertFalse(regex.matcher("dog").find());
        Assert.assertFalse(regex.matcher(" ").find());
        Assert.assertFalse(regex.matcher("").find());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testEmailAddress() throws RegexBuilderException {
        // Very basic e-mail address checker!
        final Pattern regex = new RegexBuilder()
                .startOfString()
                .nonWhitespace(RegexQuantifier.atLeast(2))
                .text("@")
                .nonWhitespace(RegexQuantifier.atLeast(2))
                .text(".")
                .nonWhitespace(RegexQuantifier.atLeast(2))
                .endOfString()
                .buildRegex();

        Assert.assertEquals("^\\S{2,}@\\S{2,}\\.\\S{2,}$", regex.toString());
        Assert.assertTrue(regex.matcher("test.user@mainwave.co.uk").find());
        Assert.assertTrue(regex.matcher("aa@bb.cc").find());
        Assert.assertTrue(regex.matcher("__@__.__").find());
        Assert.assertTrue(regex.matcher("..@.....").find());
        Assert.assertFalse(regex.matcher("aa@bb.c").find());
        Assert.assertFalse(regex.matcher("aa@b.cc").find());
        Assert.assertFalse(regex.matcher("a@bb.cc").find());
        Assert.assertFalse(regex.matcher("a@b.c").find());
        Assert.assertFalse(regex.matcher("  @  .  ").find());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testUrl() throws RegexBuilderException {
        // Very basic URL checker!
        final Pattern regex = new RegexBuilder()
                .text("http")
                .text("s", RegexQuantifier.noneOrOne())
                .text("://")
                .nonWhitespace(RegexQuantifier.oneOrMore())
                .anyCharacterFrom("a-zA-Z0-9_/") // Valid last characters
                .buildRegex();

        Assert.assertEquals("http(?:s)?://\\S+[a-zA-Z0-9_/]", regex.toString());
        Assert.assertTrue(regex.matcher("http://www.mainwave.co.uk").find());
        Assert.assertTrue(regex.matcher("https://www.mainwave.co.uk").find());
        Assert.assertFalse(regex.matcher("www.mainwave.co.uk").find());
        Assert.assertFalse(regex.matcher("ftp://www.mainwave.co.uk").find());

        Matcher match = regex.matcher("Go to http://www.mainwave.co.uk. Then click the link.");
        Assert.assertTrue(match.find());
        Assert.assertEquals("http://www.mainwave.co.uk", match.group());

        match = regex.matcher("Go to https://www.mainwave.co.uk/test/, then click the link.");
        Assert.assertTrue(match.find());
        Assert.assertEquals("https://www.mainwave.co.uk/test/", match.group());

        match = regex.matcher("Go to 'http://www.mainwave.co.uk' then click the link.");
        Assert.assertTrue(match.find());
        Assert.assertEquals("http://www.mainwave.co.uk", match.group());

        match = regex.matcher("Go to \"http://www.mainwave.co.uk\" then click the link.");
        Assert.assertTrue(match.find());
        Assert.assertEquals("http://www.mainwave.co.uk", match.group());

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
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testIp4Address() throws RegexBuilderException {
        // Very basic IPv4 address checker!
        // (doesn't check values are in range, for example)
        final Pattern regex = new RegexBuilder()
                .startOfString()
                .startGroup()
                .digit(RegexQuantifier.between(1, 3))
                .text(".")
                .endGroup(RegexQuantifier.exactly(3))
                .digit(RegexQuantifier.between(1, 3))
                .endOfString()
                .buildRegex();

        Assert.assertEquals("^(\\d{1,3}\\.){3}\\d{1,3}$", regex.toString());
        Assert.assertTrue(regex.matcher("10.1.1.100").find());
        Assert.assertTrue(regex.matcher("1.1.1.1").find());
        Assert.assertTrue(regex.matcher("0.0.0.0").find());
        Assert.assertTrue(regex.matcher("255.255.255.255").find());
        Assert.assertTrue(regex.matcher("999.999.999.999").find());
        Assert.assertFalse(regex.matcher("1.1.1.").find());
        Assert.assertFalse(regex.matcher("1.1.1.").find());
        Assert.assertFalse(regex.matcher("1.1.1.1.").find());
        Assert.assertFalse(regex.matcher("1.1.1.1.1").find());
        Assert.assertFalse(regex.matcher("1.1.1.1000").find());

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
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertFalse(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testExceptionGroupMismatch1() {
        RegexBuilderException exception = null;

        try {
            new RegexBuilder()
                    .endGroup()
                    .buildRegex();
        } catch (RegexBuilderException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertEquals("", exception.getRegexString());
    }

    @Test
    public void testExceptionGroupMismatch2() {
        RegexBuilderException exception = null;

        try {
            new RegexBuilder()
                    .startGroup()
                    .buildRegex();
        } catch (RegexBuilderException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertEquals("(", exception.getRegexString());
    }

    @Test
    public void testExceptionGroupMismatch3() {
        RegexBuilderException exception = null;

        try {
            new RegexBuilder()
                    .startGroup()
                    .startGroup()
                    .endGroup()
                    .buildRegex();
        } catch (RegexBuilderException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertEquals("(()", exception.getRegexString());
    }

    @Test
    public void testZeroOrMoreButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrMore().butAsFewAsPossible())
                .buildRegex();

        Assert.assertEquals("\\d*?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        Assert.assertTrue(nonGreedyMatch.find());
        Assert.assertEquals("", nonGreedyMatch.group());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertTrue(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testOneOrMoreButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.oneOrMore().butAsFewAsPossible())
                .buildRegex();

        Assert.assertEquals("\\d+?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        Assert.assertTrue(nonGreedyMatch.find());
        Assert.assertEquals("9", nonGreedyMatch.group());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAtLeastButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.atLeast(1).butAsFewAsPossible())
                .buildRegex();

        Assert.assertEquals("\\d{1,}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        Assert.assertTrue(nonGreedyMatch.find());
        Assert.assertEquals("9", nonGreedyMatch.toMatchResult().group());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testBetweenButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.between(2, 100).butAsFewAsPossible())
                .buildRegex();

        Assert.assertEquals("\\d{2,100}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        Assert.assertTrue(nonGreedyMatch.find());
        Assert.assertEquals("99", nonGreedyMatch.group());

        Assert.assertFalse(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertFalse(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertFalse(regex.matcher(Strings.Symbols).find());
        Assert.assertFalse(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertFalse(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertFalse(regex.matcher(Strings.Empty).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleName).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNoMoreThanButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.noMoreThan(2).butAsFewAsPossible())
                .buildRegex();

        Assert.assertEquals("\\d{0,2}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        Assert.assertTrue(nonGreedyMatch.find());
        Assert.assertEquals("", nonGreedyMatch.group());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertTrue(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNoneOrOneButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.noneOrOne().butAsFewAsPossible())
                .buildRegex();

        Assert.assertEquals("\\d??", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        Assert.assertTrue(nonGreedyMatch.find());
        Assert.assertEquals("", nonGreedyMatch.group());

        Assert.assertTrue(regex.matcher(Strings.BothCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseAlphabet).find());
        Assert.assertTrue(regex.matcher(Strings.DecimalDigits).find());
        Assert.assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        Assert.assertTrue(regex.matcher(Strings.Symbols).find());
        Assert.assertTrue(regex.matcher(Strings.WhiteSpace).find());
        Assert.assertTrue(regex.matcher(Strings.ControlCharacters).find());
        Assert.assertTrue(regex.matcher(Strings.Empty).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleName).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        Assert.assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv4Address).find());
        Assert.assertTrue(regex.matcher(Strings.Ipv6Address).find());
        Assert.assertTrue(regex.matcher(Strings.MacAddress).find());
    }
}
