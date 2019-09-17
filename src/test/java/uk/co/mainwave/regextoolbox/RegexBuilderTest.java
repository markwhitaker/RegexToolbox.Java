package uk.co.mainwave.regextoolbox;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RegexBuilderTest {
    @Test
    public void testSimpleText() throws RegexBuilderException {
        Pattern regex = new RegexBuilder()
                .text("cat")
                .buildRegex();

        assertEquals("cat", regex.toString());
        assertTrue(regex.matcher("cat").matches());
        assertTrue(regex.matcher("scatter").find());
        assertFalse(regex.matcher("Cat").find());
        assertFalse(regex.matcher("dog").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }


    @Test
    public void testSimpleTextWithQuantifier() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("cat", RegexQuantifier.exactly(2))
                .buildRegex();

        assertEquals("(?:cat){2}", regex.toString());
        assertFalse(regex.matcher("cat").find());
        assertTrue(regex.matcher("catcat").matches());
        assertTrue(regex.matcher("catcatcat").find());
        assertFalse(regex.matcher("scatter").find());
        assertFalse(regex.matcher("Cat").find());
        assertFalse(regex.matcher("dog").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testSimpleTextCaseInsensitive() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("cat")
                .buildRegex(RegexOptions.IGNORE_CASE);

        assertEquals("cat", regex.toString());
        assertTrue(regex.matcher("cat").matches());
        assertTrue(regex.matcher("scatter").find());
        assertTrue(regex.matcher("Cat").matches());
        assertFalse(regex.matcher("dog").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testSimpleTextWithRegexCharacters() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("\\.+*?[]{}()|^$")
                .buildRegex();

        assertEquals("\\\\\\.\\+\\*\\?\\[\\]\\{\\}\\(\\)\\|\\^\\$", regex.toString());
        assertTrue(regex.matcher("\\.+*?[]{}()|^$").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testRegexText() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .regexText("^\\scat\\b")
                .buildRegex();

        assertEquals("^\\scat\\b", regex.toString());
        assertTrue(regex.matcher(" cat").find());
        assertTrue(regex.matcher(" cat.").find());
        assertTrue(regex.matcher("\tcat ").find());
        assertTrue(regex.matcher(" cat-").find());
        assertTrue(regex.matcher(" cat ").find());
        assertFalse(regex.matcher("cat").find());
        assertFalse(regex.matcher(" catheter").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacter()
                .buildRegex();

        assertEquals(".", regex.toString());
        assertTrue(regex.matcher(" ").find());
        assertTrue(regex.matcher("a").find());
        assertTrue(regex.matcher("1").find());
        assertTrue(regex.matcher("\\").find());
        assertFalse(regex.matcher("").find());
        assertFalse(regex.matcher("\n").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testWhitespace() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .whitespace()
                .buildRegex();

        assertEquals("\\s", regex.toString());
        assertTrue(regex.matcher(" ").find());
        assertTrue(regex.matcher("\t").find());
        assertTrue(regex.matcher("\r").find());
        assertTrue(regex.matcher("\n").find());
        assertTrue(regex.matcher("\r\n").find());
        assertTrue(regex.matcher("\t \t").find());
        assertTrue(regex.matcher("                hi!").find());
        assertFalse(regex.matcher("cat").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonWhitespace() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonWhitespace()
                .buildRegex();

        assertEquals("\\S", regex.toString());
        assertTrue(regex.matcher("a").find());
        assertTrue(regex.matcher("1").find());
        assertTrue(regex.matcher("-").find());
        assertTrue(regex.matcher("*").find());
        assertTrue(regex.matcher("abc").find());
        assertTrue(regex.matcher("                hi!").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("\t").find());
        assertFalse(regex.matcher("\r").find());
        assertFalse(regex.matcher("\n").find());
        assertFalse(regex.matcher("\t\t\r\n   ").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testPossibleWhitespace() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonWhitespace()
                .possibleWhitespace()
                .nonWhitespace()
                .buildRegex();

        assertEquals("\\S\\s*\\S", regex.toString());
        assertFalse(regex.matcher("1").find());
        assertFalse(regex.matcher("0").find());
        assertTrue(regex.matcher("999").find());
        assertTrue(regex.matcher("there's a digit in here s0mewhere").find());
        assertFalse(regex.matcher(" ").find());
        assertTrue(regex.matcher("abc").find());
        assertTrue(regex.matcher("xFFF").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testSpace() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .space()
                .buildRegex();

        assertEquals(" ", regex.toString());
        assertTrue(regex.matcher(" ").find());
        assertFalse(regex.matcher("\t").find());
        assertFalse(regex.matcher("\r").find());
        assertFalse(regex.matcher("\n").find());
        assertFalse(regex.matcher("\r\n").find());
        assertTrue(regex.matcher("\t \t").find());
        assertTrue(regex.matcher("                hi!").find());
        assertFalse(regex.matcher("cat").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testTab() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .tab()
                .buildRegex();

        assertEquals("\\t", regex.toString());
        assertFalse(regex.matcher(" ").find());
        assertTrue(regex.matcher("\t").find());
        assertFalse(regex.matcher("\r").find());
        assertFalse(regex.matcher("\n").find());
        assertFalse(regex.matcher("\r\n").find());
        assertTrue(regex.matcher("\t \t").find());
        assertFalse(regex.matcher("                hi!").find());
        assertFalse(regex.matcher("cat").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLineFeed() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .lineFeed()
                .buildRegex();

        assertEquals("\\n", regex.toString());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("\t").find());
        assertFalse(regex.matcher("\r").find());
        assertTrue(regex.matcher("\n").find());
        assertTrue(regex.matcher("\r\n").find());
        assertFalse(regex.matcher("\t \t").find());
        assertFalse(regex.matcher("                hi!").find());
        assertFalse(regex.matcher("cat").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testCarriageReturn() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .carriageReturn()
                .buildRegex();

        assertEquals("\\r", regex.toString());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("\t").find());
        assertTrue(regex.matcher("\r").find());
        assertFalse(regex.matcher("\n").find());
        assertTrue(regex.matcher("\r\n").find());
        assertFalse(regex.matcher("\t \t").find());
        assertFalse(regex.matcher("                hi!").find());
        assertFalse(regex.matcher("cat").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit()
                .buildRegex();

        assertEquals("\\d", regex.toString());
        assertTrue(regex.matcher("1").find());
        assertTrue(regex.matcher("0").find());
        assertTrue(regex.matcher("999").find());
        assertTrue(regex.matcher("there's a digit in here s0mewhere").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("abc").find());
        assertFalse(regex.matcher("xFFF").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonDigit()
                .buildRegex();

        assertEquals("\\D", regex.toString());
        assertTrue(regex.matcher(" 1").find());
        assertTrue(regex.matcher("a0").find());
        assertTrue(regex.matcher("999_").find());
        assertTrue(regex.matcher("1,000").find());
        assertTrue(regex.matcher("there's a digit in here s0mewhere").find());
        assertFalse(regex.matcher("1").find());
        assertFalse(regex.matcher("0").find());
        assertFalse(regex.matcher("999").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLetter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .buildRegex();

        assertEquals("\\p{L}", regex.toString());
        assertTrue(regex.matcher("a").find());
        assertTrue(regex.matcher("A").find());
        assertTrue(regex.matcher("        z").find());
        assertTrue(regex.matcher("text with spaces").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("1").find());
        assertFalse(regex.matcher("%").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonLetter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonLetter()
                .buildRegex();

        assertEquals("\\P{L}", regex.toString());
        assertTrue(regex.matcher(" 1").find());
        assertTrue(regex.matcher("0").find());
        assertTrue(regex.matcher("999_").find());
        assertTrue(regex.matcher("1,000").find());
        assertTrue(regex.matcher("text with spaces").find());
        assertFalse(regex.matcher("a").find());
        assertFalse(regex.matcher("ZZZ").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testUppercaseLetter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .uppercaseLetter()
                .buildRegex();

        assertEquals("\\p{Lu}", regex.toString());
        assertTrue(regex.matcher("A").find());
        assertTrue(regex.matcher("        Z").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("1").find());
        assertFalse(regex.matcher("%").find());
        assertFalse(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLowercaseLetter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .lowercaseLetter()
                .buildRegex();

        assertEquals("\\p{Ll}", regex.toString());
        assertTrue(regex.matcher("a").find());
        assertTrue(regex.matcher("        z").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("1").find());
        assertFalse(regex.matcher("%").find());
        assertFalse(regex.matcher("S").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLetterOrDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letterOrDigit()
                .buildRegex();

        assertEquals("[\\p{L}0-9]", regex.toString());
        assertTrue(regex.matcher("A").find());
        assertTrue(regex.matcher("        Z").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertFalse(regex.matcher(" ").find());
        assertTrue(regex.matcher("1").find());
        assertFalse(regex.matcher("%").find());
        assertFalse(regex.matcher("_").find());
        assertTrue(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonLetterOrDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonLetterOrDigit()
                .buildRegex();

        assertEquals("[^\\p{L}0-9]", regex.toString());
        assertFalse(regex.matcher("A").find());
        assertTrue(regex.matcher("        Z").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertTrue(regex.matcher(" ").find());
        assertFalse(regex.matcher("1").find());
        assertTrue(regex.matcher("%").find());
        assertTrue(regex.matcher("_").find());
        assertFalse(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testHexDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .hexDigit()
                .buildRegex();

        assertEquals("[0-9A-Fa-f]", regex.toString());
        assertTrue(regex.matcher("A").find());
        assertTrue(regex.matcher("        f").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertFalse(regex.matcher(" ").find());
        assertTrue(regex.matcher("1").find());
        assertFalse(regex.matcher("%").find());
        assertFalse(regex.matcher("_").find());
        assertFalse(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testUppercaseHexDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .uppercaseHexDigit()
                .buildRegex();

        assertEquals("[0-9A-F]", regex.toString());
        assertTrue(regex.matcher("A").find());
        assertFalse(regex.matcher("        f").find());
        assertFalse(regex.matcher("text with Spaces").find());
        assertFalse(regex.matcher(" ").find());
        assertTrue(regex.matcher("1").find());
        assertFalse(regex.matcher("%").find());
        assertFalse(regex.matcher("_").find());
        assertFalse(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testLowercaseHexDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .lowercaseHexDigit()
                .buildRegex();

        assertEquals("[0-9a-f]", regex.toString());
        assertFalse(regex.matcher("A").find());
        assertTrue(regex.matcher("        f").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertFalse(regex.matcher(" ").find());
        assertTrue(regex.matcher("1").find());
        assertFalse(regex.matcher("%").find());
        assertFalse(regex.matcher("_").find());
        assertFalse(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonHexDigit() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonHexDigit()
                .buildRegex();

        assertEquals("[^0-9A-Fa-f]", regex.toString());
        assertFalse(regex.matcher("A").find());
        assertTrue(regex.matcher("        f").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertTrue(regex.matcher(" ").find());
        assertFalse(regex.matcher("1").find());
        assertTrue(regex.matcher("%").find());
        assertTrue(regex.matcher("_").find());
        assertTrue(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testWordCharacter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .wordCharacter()
                .buildRegex();

        assertEquals("[\\p{L}0-9_]", regex.toString());
        assertTrue(regex.matcher("A").find());
        assertTrue(regex.matcher("        Z").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertFalse(regex.matcher(" ").find());
        assertTrue(regex.matcher("1").find());
        assertFalse(regex.matcher("%").find());
        assertTrue(regex.matcher("_").find());
        assertTrue(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNonWordCharacter() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .nonWordCharacter()
                .buildRegex();

        assertEquals("[^\\p{L}0-9_]", regex.toString());
        assertFalse(regex.matcher("A").find());
        assertTrue(regex.matcher("        Z").find());
        assertTrue(regex.matcher("text with Spaces").find());
        assertTrue(regex.matcher(" ").find());
        assertFalse(regex.matcher("1").find());
        assertTrue(regex.matcher("%").find());
        assertFalse(regex.matcher("_").find());
        assertFalse(regex.matcher("s").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacterFrom() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("cat")
                .buildRegex();

        assertEquals("[cat]", regex.toString());
        assertTrue(regex.matcher("cat").find());
        assertTrue(regex.matcher("parrot").find());
        assertTrue(regex.matcher("tiger").find());
        assertTrue(regex.matcher("cow").find());
        assertFalse(regex.matcher("CAT").find());
        assertFalse(regex.matcher("dog").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacterFromWithCaretAtStart() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("^abc")
                .buildRegex();

        assertEquals("[\\^abc]", regex.toString());
        assertTrue(regex.matcher("jazz").find());
        assertTrue(regex.matcher("_^_").find());
        assertTrue(regex.matcher("oboe").find());
        assertTrue(regex.matcher("cue").find());
        assertFalse(regex.matcher("CAT").find());
        assertFalse(regex.matcher("dog").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacterFromWithCaretNotAtStart() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("a^bc")
                .buildRegex();

        assertEquals("[a^bc]", regex.toString());
        assertTrue(regex.matcher("jazz").find());
        assertTrue(regex.matcher("_^_").find());
        assertTrue(regex.matcher("oboe").find());
        assertTrue(regex.matcher("cue").find());
        assertFalse(regex.matcher("CAT").find());
        assertFalse(regex.matcher("dog").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyCharacterExcept() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterExcept("cat")
                .buildRegex();

        assertEquals("[^cat]", regex.toString());
        assertFalse(regex.matcher("cat").find());
        assertFalse(regex.matcher("tata").find());
        assertTrue(regex.matcher("parrot").find());
        assertTrue(regex.matcher("tiger").find());
        assertTrue(regex.matcher("cow").find());
        assertTrue(regex.matcher("CAT").find());
        assertTrue(regex.matcher("dog").find());
        assertTrue(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyOf() throws RegexBuilderException {
        final String[] strings = new String[]{"cat", "dog", "|"};
        final Pattern regex = new RegexBuilder()
                .anyOf(strings)
                .buildRegex();

        assertEquals("(?:cat|dog|\\|)", regex.toString());
        assertFalse(regex.matcher("ca do").find());
        assertTrue(regex.matcher("cat").find());
        assertTrue(regex.matcher("dog").find());
        assertTrue(regex.matcher("|").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testVarargAnyOf() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyOf("cat", "dog", "|")
                .buildRegex();

        assertEquals("(?:cat|dog|\\|)", regex.toString());
        assertFalse(regex.matcher("ca do").find());
        assertTrue(regex.matcher("cat").find());
        assertTrue(regex.matcher("dog").find());
        assertTrue(regex.matcher("|").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyOfWithQuantifier() throws RegexBuilderException {
        final String[] strings = new String[]{"cat", "dog", "|"};
        final Pattern regex = new RegexBuilder()
                .anyOf(strings, RegexQuantifier.exactly(2))
                .buildRegex();

        assertEquals("(?:cat|dog|\\|){2}", regex.toString());
        assertTrue(regex.matcher("catdog").find());
        assertTrue(regex.matcher("cat|dog").find());
        assertFalse(regex.matcher("cat").find());
        assertTrue(regex.matcher("catcat").find());
        assertTrue(regex.matcher("catcatcat").find());
        assertFalse(regex.matcher("dog").find());
        assertTrue(regex.matcher("dogdog").find());
        assertTrue(regex.matcher("dogdogdog").find());
        assertFalse(regex.matcher("|").find());
        assertTrue(regex.matcher("||").find());
        assertTrue(regex.matcher("|||").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAnyOfNullEmptyOrSingle() throws RegexBuilderException {
        final String nullString = null;
        //noinspection ConstantConditions
        final Pattern anyOfNullRegex = new RegexBuilder()
                .anyOf(nullString)
                .buildRegex();

        final String[] nullStringArray = null;
        //noinspection ConstantConditions
        final Pattern anyOfNullArrayRegex = new RegexBuilder()
                .anyOf(nullStringArray)
                .buildRegex();

        final String[] emptyStringArray = new String[0];
        final Pattern anyOfEmptyRegex = new RegexBuilder()
                .anyOf(emptyStringArray)
                .buildRegex();

        final String[] singleItemStringArray = new String[]{ "cat" };
        final Pattern anyOfSingleRegex = new RegexBuilder()
                .anyOf(singleItemStringArray)
                .buildRegex();

        assertEquals("", anyOfNullRegex.toString());
        assertEquals("", anyOfNullArrayRegex.toString());
        assertEquals("", anyOfEmptyRegex.toString());
        assertEquals("cat", anyOfSingleRegex.toString());
    }

    @Test
    public void testStartOfString() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .startOfString()
                .text("a")
                .buildRegex();

        assertEquals("^a", regex.toString());
        assertTrue(regex.matcher("a").find());
        assertTrue(regex.matcher("aA").find());
        assertTrue(regex.matcher("a_").find());
        assertTrue(regex.matcher("a        big gap").find());
        assertFalse(regex.matcher(" a space before").find());
        assertFalse(regex.matcher("A capital letter").find());
        assertFalse(regex.matcher("Aa").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testEndOfString() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("z")
                .endOfString()
                .buildRegex();

        assertEquals("z$", regex.toString());
        assertTrue(regex.matcher("z").find());
        assertTrue(regex.matcher("zzz").find());
        assertTrue(regex.matcher("fizz buzz").find());
        assertFalse(regex.matcher("buzz!").find());
        assertFalse(regex.matcher("zzz ").find());
        assertFalse(regex.matcher("zZ").find());
        assertFalse(regex.matcher("z ").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testWordBoundary() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .text("a")
                .wordBoundary()
                .buildRegex();

        assertEquals("a\\b", regex.toString());
        assertTrue(regex.matcher("a").find());
        assertTrue(regex.matcher("spa").find());
        assertTrue(regex.matcher("papa don't preach").find());
        assertTrue(regex.matcher("a dog").find());
        assertTrue(regex.matcher("a-dog").find());
        assertFalse(regex.matcher("an apple").find());
        assertFalse(regex.matcher("asp").find());
        assertFalse(regex.matcher("a1b").find());
        assertFalse(regex.matcher("a_b").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
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

        assertEquals(".*(\\p{L}\\d)", regex.toString());

        Matcher match = regex.matcher("Class A1");
        assertTrue(match.find());
        assertEquals("Class A1", match.group(0));
        assertEquals("A1", match.group(1));

        match = regex.matcher("he likes F1 racing");
        assertTrue(match.find());
        assertEquals("he likes F1", match.group(0));
        assertEquals("F1", match.group(1));

        match = regex.matcher("A4 paper");
        assertTrue(match.find());
        assertEquals("A4", match.group(0));
        assertEquals("A4", match.group(1));

        match = regex.matcher("A 4-legged dog");
        assertFalse(match.find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testRepeatGroup() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .startGroup()
                .letter()
                .digit()
                .endGroup()
                .buildRegex();

        assertEquals("(\\p{L}\\d)", regex.toString());

        final Matcher matcher = regex.matcher("Class A1 f2 ZZ88");
        final List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        assertEquals(3, matches.size());
        assertEquals("A1", matches.get(0));
        assertEquals("f2", matches.get(1));
        assertEquals("Z8", matches.get(2));

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
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

        assertEquals("\\p{Ll}+(?<test123>\\d+)\\p{Ll}+", regex.toString());

        Matcher match = regex.matcher("a99z");
        assertTrue(match.find());
        assertEquals("a99z", match.group(0));
        assertEquals("99", match.group(1));
        assertEquals("99", match.group("test123"));

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
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

        assertEquals("\\p{Ll}+(?:\\d+)\\p{Ll}+", regex.toString());

        Matcher match = regex.matcher("a99z");
        assertTrue(match.find());
        assertEquals("a99z", match.group(0));
        assertEquals(0, match.groupCount());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
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

        assertEquals("(.*)(\\p{L}\\d)", regex.toString());

        Matcher match = regex.matcher("Class A1");
        assertTrue(match.find());
        assertEquals("Class A1", match.group(0));
        assertEquals("Class ", match.group(1));
        assertEquals("A1", match.group(2));

        match = regex.matcher("he likes F1 racing");
        assertTrue(match.find());
        assertEquals("he likes F1", match.group(0));
        assertEquals("he likes ", match.group(1));
        assertEquals("F1", match.group(2));

        match = regex.matcher("A4 paper");
        assertTrue(match.find());
        assertEquals("A4", match.group(0));
        assertEquals("", match.group(1));
        assertEquals("A4", match.group(2));

        match = regex.matcher("A 4-legged dog");
        assertFalse(match.find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
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

        assertEquals(".(.*(\\p{L}\\d))", regex.toString());

        Matcher match = regex.matcher("Class A1");
        assertTrue(match.find());
        assertEquals("Class A1", match.group(0));
        assertEquals("lass A1", match.group(1));
        assertEquals("A1", match.group(2));

        match = regex.matcher("he likes F1 racing");
        assertTrue(match.find());
        assertEquals("he likes F1", match.group(0));
        assertEquals("e likes F1", match.group(1));
        assertEquals("F1", match.group(2));

        match = regex.matcher(" A4 paper");
        assertTrue(match.find());
        assertEquals(" A4", match.group(0));
        assertEquals("A4", match.group(1));
        assertEquals("A4", match.group(2));

        match = regex.matcher("A 4-legged dog");
        assertFalse(match.find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testZeroOrMore() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.zeroOrMore())
                .letter()
                .buildRegex();

        assertEquals("\\p{L}\\d*\\p{L}", regex.toString());
        assertTrue(regex.matcher("ab").find());
        assertTrue(regex.matcher("a1b").find());
        assertTrue(regex.matcher("a123b").find());
        assertFalse(regex.matcher("a 1 b").find());
        assertFalse(regex.matcher("a b").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testOneOrMore() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.oneOrMore())
                .letter()
                .buildRegex();

        assertEquals("\\p{L}\\d+\\p{L}", regex.toString());
        assertFalse(regex.matcher("ab").find());
        assertTrue(regex.matcher("a1b").find());
        assertTrue(regex.matcher("a123b").find());
        assertFalse(regex.matcher("a 1 b").find());
        assertFalse(regex.matcher("a b").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testOneOrNone() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.zeroOrOne())
                .letter()
                .buildRegex();

        assertEquals("\\p{L}\\d?\\p{L}", regex.toString());
        assertTrue(regex.matcher("ab").find());
        assertTrue(regex.matcher("a1b").find());
        assertFalse(regex.matcher("a123b").find());
        assertFalse(regex.matcher("a 1 b").find());
        assertFalse(regex.matcher("a b").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testExactlyNTimes() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.exactly(3))
                .letter()
                .buildRegex();

        assertEquals("\\p{L}\\d{3}\\p{L}", regex.toString());
        assertFalse(regex.matcher("ab").find());
        assertFalse(regex.matcher("a1b").find());
        assertFalse(regex.matcher("a12b").find());
        assertTrue(regex.matcher("a123b").find());
        assertFalse(regex.matcher("a1234b").find());
        assertFalse(regex.matcher("a12345b").find());
        assertFalse(regex.matcher("a 1 b").find());
        assertFalse(regex.matcher("a b").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAtLeastQuantifier() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.atLeast(3))
                .letter()
                .buildRegex();

        assertEquals("\\p{L}\\d{3,}\\p{L}", regex.toString());
        assertFalse(regex.matcher("ab").find());
        assertFalse(regex.matcher("a1b").find());
        assertFalse(regex.matcher("a12b").find());
        assertTrue(regex.matcher("a123b").find());
        assertTrue(regex.matcher("a1234b").find());
        assertTrue(regex.matcher("a12345b").find());
        assertFalse(regex.matcher("a 1 b").find());
        assertFalse(regex.matcher("a b").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNoMoreThanQuantifier() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.noMoreThan(3))
                .letter()
                .buildRegex();

        assertEquals("\\p{L}\\d{0,3}\\p{L}", regex.toString());
        assertTrue(regex.matcher("ab").find());
        assertTrue(regex.matcher("a1b").find());
        assertTrue(regex.matcher("a12b").find());
        assertTrue(regex.matcher("a123b").find());
        assertFalse(regex.matcher("a1234b").find());
        assertFalse(regex.matcher("a12345b").find());
        assertFalse(regex.matcher("a 1 b").find());
        assertFalse(regex.matcher("a b").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testBetweenMinMaxTimes() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .letter()
                .digit(RegexQuantifier.between(2, 4))
                .letter()
                .buildRegex();

        assertEquals("\\p{L}\\d{2,4}\\p{L}", regex.toString());
        assertFalse(regex.matcher("ab").find());
        assertFalse(regex.matcher("a1b").find());
        assertTrue(regex.matcher("a12b").find());
        assertTrue(regex.matcher("a123b").find());
        assertTrue(regex.matcher("a1234b").find());
        assertFalse(regex.matcher("a12345b").find());
        assertFalse(regex.matcher("a 1 b").find());
        assertFalse(regex.matcher("a b").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testOptionMultiLine() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .startOfString()
                .text("find me!")
                .endOfString()
                .buildRegex(RegexOptions.MULTILINE);

        assertEquals("^find me!$", regex.toString());
        assertEquals(Pattern.MULTILINE, (regex.flags() & Pattern.MULTILINE));
        assertTrue(regex.matcher("find me!").find());
        assertTrue(regex.matcher("find me!\nline 2").find());
        assertTrue(regex.matcher("line 1\nfind me!").find());
        assertTrue(regex.matcher("line 1\nfind me!\nline 3").find());
        assertFalse(regex.matcher(" find me!").find());
        assertFalse(regex.matcher("find me! ").find());
        assertFalse(regex.matcher(" find me! ").find());
        assertFalse(regex.matcher("").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testOptionIGNORE_CASE() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("cat")
                .buildRegex(RegexOptions.IGNORE_CASE);

        assertEquals("[cat]", regex.toString());
        assertEquals(Pattern.CASE_INSENSITIVE, (regex.flags() & Pattern.CASE_INSENSITIVE));
        assertTrue(regex.matcher("cat").find());
        assertTrue(regex.matcher("tiger").find());
        assertTrue(regex.matcher("Ant").find());
        assertTrue(regex.matcher("CAT").find());
        assertTrue(regex.matcher("                A").find());
        assertFalse(regex.matcher("dog").find());
        assertFalse(regex.matcher(" ").find());
        assertFalse(regex.matcher("").find());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
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

        assertEquals("^\\S{2,}@\\S{2,}\\.\\S{2,}$", regex.toString());
        assertTrue(regex.matcher("test.user@mainwave.co.uk").find());
        assertTrue(regex.matcher("aa@bb.cc").find());
        assertTrue(regex.matcher("__@__.__").find());
        assertTrue(regex.matcher("..@.....").find());
        assertFalse(regex.matcher("aa@bb.c").find());
        assertFalse(regex.matcher("aa@b.cc").find());
        assertFalse(regex.matcher("a@bb.cc").find());
        assertFalse(regex.matcher("a@b.c").find());
        assertFalse(regex.matcher("  @  .  ").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testUrl() throws RegexBuilderException {
        // Very basic URL checker!
        final Pattern regex = new RegexBuilder()
                .text("http")
                .text("s", RegexQuantifier.zeroOrOne())
                .text("://")
                .nonWhitespace(RegexQuantifier.oneOrMore())
                .anyCharacterFrom("a-zA-Z0-9_/") // Valid last characters
                .buildRegex();

        assertEquals("http(?:s)?://\\S+[a-zA-Z0-9_/]", regex.toString());
        assertTrue(regex.matcher("http://www.mainwave.co.uk").find());
        assertTrue(regex.matcher("https://www.mainwave.co.uk").find());
        assertFalse(regex.matcher("www.mainwave.co.uk").find());
        assertFalse(regex.matcher("ftp://www.mainwave.co.uk").find());

        Matcher match = regex.matcher("Go to http://www.mainwave.co.uk. Then click the link.");
        assertTrue(match.find());
        assertEquals("http://www.mainwave.co.uk", match.group());

        match = regex.matcher("Go to https://www.mainwave.co.uk/test/, then click the link.");
        assertTrue(match.find());
        assertEquals("https://www.mainwave.co.uk/test/", match.group());

        match = regex.matcher("Go to 'http://www.mainwave.co.uk' then click the link.");
        assertTrue(match.find());
        assertEquals("http://www.mainwave.co.uk", match.group());

        match = regex.matcher("Go to \"http://www.mainwave.co.uk\" then click the link.");
        assertTrue(match.find());
        assertEquals("http://www.mainwave.co.uk", match.group());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertFalse(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
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

        assertEquals("^(\\d{1,3}\\.){3}\\d{1,3}$", regex.toString());
        assertTrue(regex.matcher("10.1.1.100").find());
        assertTrue(regex.matcher("1.1.1.1").find());
        assertTrue(regex.matcher("0.0.0.0").find());
        assertTrue(regex.matcher("255.255.255.255").find());
        assertTrue(regex.matcher("999.999.999.999").find());
        assertFalse(regex.matcher("1.1.1.").find());
        assertFalse(regex.matcher("1.1.1.").find());
        assertFalse(regex.matcher("1.1.1.1.").find());
        assertFalse(regex.matcher("1.1.1.1.1").find());
        assertFalse(regex.matcher("1.1.1.1000").find());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.DecimalDigits).find());
        assertFalse(regex.matcher(Strings.BothCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertFalse(regex.matcher(Strings.Ipv6Address).find());
        assertFalse(regex.matcher(Strings.MacAddress).find());
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
        assertNotNull(exception);
        assertEquals("", exception.getRegexString());
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
        assertNotNull(exception);
        assertEquals("(", exception.getRegexString());
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
        assertNotNull(exception);
        assertEquals("(()", exception.getRegexString());
    }

    @Test
    public void testZeroOrMoreButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrMore().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d*?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("", nonGreedyMatch.group());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertTrue(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testOneOrMoreButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.oneOrMore().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d+?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("9", nonGreedyMatch.group());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testAtLeastButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.atLeast(1).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{1,}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("9", nonGreedyMatch.toMatchResult().group());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testBetweenButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.between(2, 100).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{2,100}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("99", nonGreedyMatch.group());

        assertFalse(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertFalse(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertFalse(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertFalse(regex.matcher(Strings.Symbols).find());
        assertFalse(regex.matcher(Strings.WhiteSpace).find());
        assertFalse(regex.matcher(Strings.ControlCharacters).find());
        assertFalse(regex.matcher(Strings.Empty).find());
        assertFalse(regex.matcher(Strings.SimpleName).find());
        assertFalse(regex.matcher(Strings.SimpleEmailAddress).find());
        assertFalse(regex.matcher(Strings.SimpleHttpUrl).find());
        assertFalse(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNoMoreThanButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.noMoreThan(2).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{0,2}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("", nonGreedyMatch.group());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertTrue(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }

    @Test
    public void testNoneOrOneButAsFewAsPossible() throws RegexBuilderException {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrOne().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d??", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("", nonGreedyMatch.group());

        assertTrue(regex.matcher(Strings.BothCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseLatinAlphabet).find());
        assertTrue(regex.matcher(Strings.BothCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.UpperCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.LowerCaseExtendedAlphabet).find());
        assertTrue(regex.matcher(Strings.DecimalDigits).find());
        assertTrue(regex.matcher(Strings.BothCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.UpperCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.LowerCaseHexDigits).find());
        assertTrue(regex.matcher(Strings.Symbols).find());
        assertTrue(regex.matcher(Strings.WhiteSpace).find());
        assertTrue(regex.matcher(Strings.ControlCharacters).find());
        assertTrue(regex.matcher(Strings.Empty).find());
        assertTrue(regex.matcher(Strings.SimpleName).find());
        assertTrue(regex.matcher(Strings.SimpleEmailAddress).find());
        assertTrue(regex.matcher(Strings.SimpleHttpUrl).find());
        assertTrue(regex.matcher(Strings.SimpleHttpsUrl).find());
        assertTrue(regex.matcher(Strings.Ipv4Address).find());
        assertTrue(regex.matcher(Strings.Ipv6Address).find());
        assertTrue(regex.matcher(Strings.MacAddress).find());
    }
}
