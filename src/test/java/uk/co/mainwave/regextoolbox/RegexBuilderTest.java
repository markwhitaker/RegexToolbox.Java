package uk.co.mainwave.regextoolbox;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RegexBuilderTest {
    @Test
    public void testNoOptions() {
        Pattern regex = new RegexBuilder()
                .anyCharacter()
                .buildRegex();

        assertEquals(0, regex.flags());
    }

    @Test
    public void testIgnoreCase() {
        Pattern regex = new RegexBuilder()
                .anyCharacter()
                .buildRegex(RegexOptions.IGNORE_CASE);

        assertEquals(Pattern.CASE_INSENSITIVE, regex.flags());
    }

    @Test
    public void testMultiline() {
        Pattern regex = new RegexBuilder()
                .anyCharacter()
                .buildRegex(RegexOptions.MULTILINE);

        assertEquals(Pattern.MULTILINE, regex.flags());
    }

    @Test
    public void testIgnoreCaseAndMultiline() {
        Pattern regex = new RegexBuilder()
                .anyCharacter()
                .buildRegex(RegexOptions.IGNORE_CASE, RegexOptions.MULTILINE);

        assertEquals(Pattern.CASE_INSENSITIVE|Pattern.MULTILINE, regex.flags());
    }

    @Test
    public void testText() {
        Pattern regex = new RegexBuilder()
                .text("a*b")
                .buildRegex();

        assertEquals("a\\*b", regex.toString());
    }

    @Test
    public void testTextWithQuantifier() {
        Pattern regex = new RegexBuilder()
                .text("a*b", RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("(?:a\\*b)+", regex.toString());
    }

    @Test
    public void testRegexText() {
        final Pattern regex = new RegexBuilder()
                .regexText("a*b")
                .buildRegex();

        assertEquals("a*b", regex.toString());
    }

    @Test
    public void testRegexTextWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .regexText("a*b", RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("(?:a*b)+", regex.toString());
    }

    @Test
    public void testAnyCharacter() {
        final Pattern regex = new RegexBuilder()
                .anyCharacter()
                .buildRegex();

        assertEquals(".", regex.toString());
    }

    @Test
    public void testAnyCharacterWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .anyCharacter(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals(".+", regex.toString());
    }

    @Test
    public void testWhitespace() {
        final Pattern regex = new RegexBuilder()
                .whitespace()
                .buildRegex();

        assertEquals("\\s", regex.toString());
    }

    @Test
    public void testWhitespaceWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .whitespace(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\s+", regex.toString());
    }

    @Test
    public void testNonWhitespace() {
        final Pattern regex = new RegexBuilder()
                .nonWhitespace()
                .buildRegex();

        assertEquals("\\S", regex.toString());
    }

    @Test
    public void testNonWhitespaceWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .nonWhitespace(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\S+", regex.toString());
    }

    @Test
    public void testPossibleWhitespace() {
        final Pattern regex = new RegexBuilder()
                .possibleWhitespace()
                .buildRegex();

        assertEquals("\\s*", regex.toString());
    }

    @Test
    public void testSpace() {
        final Pattern regex = new RegexBuilder()
                .space()
                .buildRegex();

        assertEquals(" ", regex.toString());
    }

    @Test
    public void testSpaceWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .space(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals(" +", regex.toString());
    }

    @Test
    public void testTab() {
        final Pattern regex = new RegexBuilder()
                .tab()
                .buildRegex();

        assertEquals("\\t", regex.toString());
    }

    @Test
    public void testTabWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .tab(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\t+", regex.toString());
    }

    @Test
    public void testLineFeed() {
        final Pattern regex = new RegexBuilder()
                .lineFeed()
                .buildRegex();

        assertEquals("\\n", regex.toString());
    }

    @Test
    public void testLineFeedWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .lineFeed(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\n+", regex.toString());
    }

    @Test
    public void testCarriageReturn() {
        final Pattern regex = new RegexBuilder()
                .carriageReturn()
                .buildRegex();

        assertEquals("\\r", regex.toString());
    }

    @Test
    public void testCarriageReturnWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .carriageReturn(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\r+", regex.toString());
    }

    @Test
    public void testDigit() {
        final Pattern regex = new RegexBuilder()
                .digit()
                .buildRegex();

        assertEquals("\\d", regex.toString());
    }

    @Test
    public void testDigitWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\d+", regex.toString());
    }

    @Test
    public void testNonDigit() {
        final Pattern regex = new RegexBuilder()
                .nonDigit()
                .buildRegex();

        assertEquals("\\D", regex.toString());
    }

    @Test
    public void testNonDigitWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .nonDigit(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\D+", regex.toString());
    }

    @Test
    public void testLetter() {
        final Pattern regex = new RegexBuilder()
                .letter()
                .buildRegex();

        assertEquals("\\p{L}", regex.toString());
    }

    @Test
    public void testLetterWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .letter(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\p{L}+", regex.toString());
    }

    @Test
    public void testNonLetter() {
        final Pattern regex = new RegexBuilder()
                .nonLetter()
                .buildRegex();

        assertEquals("\\P{L}", regex.toString());
    }

    @Test
    public void testNonLetterWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .nonLetter(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\P{L}+", regex.toString());
    }

    @Test
    public void testUppercaseLetter() {
        final Pattern regex = new RegexBuilder()
                .uppercaseLetter()
                .buildRegex();

        assertEquals("\\p{Lu}", regex.toString());
    }

    @Test
    public void testUppercaseLetterWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .uppercaseLetter(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\p{Lu}+", regex.toString());
    }

    @Test
    public void testLowercaseLetter() {
        final Pattern regex = new RegexBuilder()
                .lowercaseLetter()
                .buildRegex();

        assertEquals("\\p{Ll}", regex.toString());
    }

    @Test
    public void testLowercaseLetterWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .lowercaseLetter(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("\\p{Ll}+", regex.toString());
    }

    @Test
    public void testLetterOrDigit() {
        final Pattern regex = new RegexBuilder()
                .letterOrDigit()
                .buildRegex();

        assertEquals("[\\p{L}0-9]", regex.toString());
    }

    @Test
    public void testLetterOrDigitWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .letterOrDigit(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[\\p{L}0-9]+", regex.toString());
    }

    @Test
    public void testNonLetterOrDigit() {
        final Pattern regex = new RegexBuilder()
                .nonLetterOrDigit()
                .buildRegex();

        assertEquals("[^\\p{L}0-9]", regex.toString());
    }

    @Test
    public void testNonLetterOrDigitWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .nonLetterOrDigit(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[^\\p{L}0-9]+", regex.toString());
    }

    @Test
    public void testHexDigit() {
        final Pattern regex = new RegexBuilder()
                .hexDigit()
                .buildRegex();

        assertEquals("[0-9A-Fa-f]", regex.toString());
    }

    @Test
    public void testHexDigitWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .hexDigit(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[0-9A-Fa-f]+", regex.toString());
    }

    @Test
    public void testUppercaseHexDigit() {
        final Pattern regex = new RegexBuilder()
                .uppercaseHexDigit()
                .buildRegex();

        assertEquals("[0-9A-F]", regex.toString());
    }

    @Test
    public void testUppercaseHexDigitWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .uppercaseHexDigit(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[0-9A-F]+", regex.toString());
    }

    @Test
    public void testLowercaseHexDigit() {
        final Pattern regex = new RegexBuilder()
                .lowercaseHexDigit()
                .buildRegex();

        assertEquals("[0-9a-f]", regex.toString());
    }

    @Test
    public void testLowercaseHexDigitWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .lowercaseHexDigit(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[0-9a-f]+", regex.toString());
    }

    @Test
    public void testNonHexDigit() {
        final Pattern regex = new RegexBuilder()
                .nonHexDigit()
                .buildRegex();

        assertEquals("[^0-9A-Fa-f]", regex.toString());
    }

    @Test
    public void testNonHexDigitWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .nonHexDigit(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[^0-9A-Fa-f]+", regex.toString());
    }

    @Test
    public void testWordCharacter() {
        final Pattern regex = new RegexBuilder()
                .wordCharacter()
                .buildRegex();

        assertEquals("[\\p{L}0-9_]", regex.toString());
    }

    @Test
    public void testWordCharacterWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .wordCharacter(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[\\p{L}0-9_]+", regex.toString());
    }

    @Test
    public void testNonWordCharacter() {
        final Pattern regex = new RegexBuilder()
                .nonWordCharacter()
                .buildRegex();

        assertEquals("[^\\p{L}0-9_]", regex.toString());
    }

    @Test
    public void testNonWordCharacterWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .nonWordCharacter(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[^\\p{L}0-9_]+", regex.toString());
    }

    @Test
    public void testAnyCharacterFrom() {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("^]")
                .buildRegex();

        assertEquals("[\\^\\]]", regex.toString());
    }

    @Test
    public void testAnyCharacterFromWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .anyCharacterFrom("^]", RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[\\^\\]]+", regex.toString());
    }

    @Test
    public void testAnyCharacterExcept() {
        final Pattern regex = new RegexBuilder()
                .anyCharacterExcept("^]")
                .buildRegex();

        assertEquals("[^\\^\\]]", regex.toString());
    }

    @Test
    public void testAnyCharacterExceptWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .anyCharacterExcept("^]", RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("[^\\^\\]]+", regex.toString());
    }

    @Test
    public void testAnyOf() {
        final String[] strings = new String[]{"cat", "dog", "|"};
        final Pattern regex = new RegexBuilder()
                .anyOf(strings)
                .buildRegex();

        assertEquals("(?:cat|dog|\\|)", regex.toString());
    }

    @Test
    public void testAnyOfWithQuantifier() {
        final String[] strings = new String[]{"cat", "dog", "|"};
        final Pattern regex = new RegexBuilder()
                .anyOf(strings, RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("(?:cat|dog|\\|)+", regex.toString());
    }

    @Test
    public void testVarargAnyOf() {
        final Pattern regex = new RegexBuilder()
                .anyOf("cat", "dog", "|")
                .buildRegex();

        assertEquals("(?:cat|dog|\\|)", regex.toString());
    }

    @Test
    public void testAnyOfNullEmptyOrSingle() {
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
    public void testStartOfString() {
        final Pattern regex = new RegexBuilder()
                .startOfString()
                .buildRegex();

        assertEquals("^", regex.toString());
    }

    @Test
    public void testEndOfString() {
        final Pattern regex = new RegexBuilder()
                .endOfString()
                .buildRegex();

        assertEquals("$", regex.toString());
    }

    @Test
    public void testWordBoundary() {
        final Pattern regex = new RegexBuilder()
                .wordBoundary()
                .buildRegex();

        assertEquals("\\b", regex.toString());
    }

    @Test
    public void testGroup() {
        final Pattern regex = new RegexBuilder()
                .startGroup()
                .text("a")
                .digit()
                .endGroup()
                .buildRegex();

        assertEquals("(a\\d)", regex.toString());
    }

    @Test
    public void testGroupWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .startGroup()
                .text("a")
                .digit()
                .endGroup(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("(a\\d)+", regex.toString());
    }

    @Test
    public void testNestedGroups() {
        final Pattern regex = new RegexBuilder()
                .startGroup()
                .text("a")
                .startGroup()
                .digit()
                .endGroup()
                .endGroup()
                .buildRegex();

        assertEquals("(a(\\d))", regex.toString());
    }

    @Test
    public void testNonCapturingGroup() {
        final Pattern regex = new RegexBuilder()
                .startNonCapturingGroup()
                .text("a")
                .digit()
                .endGroup()
                .buildRegex();

        assertEquals("(?:a\\d)", regex.toString());
    }

    @Test
    public void testNestedNonCapturingGroup() {
        final Pattern regex = new RegexBuilder()
                .startNonCapturingGroup()
                .text("a")
                .startNonCapturingGroup()
                .digit()
                .endGroup()
                .endGroup()
                .buildRegex();

        assertEquals("(?:a(?:\\d))", regex.toString());
    }

    @Test
    public void testNamedGroup() {
        final Pattern regex = new RegexBuilder()
                .startNamedGroup("group")
                .text("a")
                .digit()
                .endGroup()
                .buildRegex();

        assertEquals("(?<group>a\\d)", regex.toString());
    }

    @Test
    public void testNamedGroupWithQuantifier() {
        final Pattern regex = new RegexBuilder()
                .startNamedGroup("group")
                .text("a")
                .digit()
                .endGroup(RegexQuantifier.oneOrMore())
                .buildRegex();

        assertEquals("(?<group>a\\d)+", regex.toString());
    }

    @Test
    public void testNestedNamedGroup() {
        final Pattern regex = new RegexBuilder()
                .startNamedGroup("group1")
                .text("a")
                .startNamedGroup("group2")
                .digit()
                .endGroup()
                .endGroup()
                .buildRegex();

        assertEquals("(?<group1>a(?<group2>\\d))", regex.toString());
    }

    @Test
    public void testOneOrMoreQuantifier() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.oneOrMore())
                .buildRegex();
        final Pattern butAsFewAsPossibleRegex = new RegexBuilder()
                .digit(RegexQuantifier.oneOrMore().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d+", regex.toString());
        assertEquals("\\d+?", butAsFewAsPossibleRegex.toString());
    }

    @Test
    public void testZeroOrMoreQuantifier() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrMore())
                .buildRegex();
        final Pattern butAsFewAsPossibleRegex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrMore().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d*", regex.toString());
        assertEquals("\\d*?", butAsFewAsPossibleRegex.toString());
    }

    @Test
    public void testZeroOrOneQuantifier() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrOne())
                .buildRegex();
        final Pattern butAsFewAsPossibleRegex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrOne().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d?", regex.toString());
        assertEquals("\\d??", butAsFewAsPossibleRegex.toString());
    }

    @Test
    public void testAtLeastQuantifier() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.atLeast(1))
                .buildRegex();
        final Pattern butAsFewAsPossibleRegex = new RegexBuilder()
                .digit(RegexQuantifier.atLeast(1).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{1,}", regex.toString());
        assertEquals("\\d{1,}?", butAsFewAsPossibleRegex.toString());
    }

    @Test
    public void testNoMoreThanQuantifier() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.noMoreThan(2))
                .buildRegex();
        final Pattern butAsFewAsPossibleRegex = new RegexBuilder()
                .digit(RegexQuantifier.noMoreThan(2).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{0,2}", regex.toString());
        assertEquals("\\d{0,2}?", butAsFewAsPossibleRegex.toString());
    }

    @Test
    public void testBetweenQuantifier() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.between(1, 2))
                .buildRegex();
        final Pattern butAsFewAsPossibleRegex = new RegexBuilder()
                .digit(RegexQuantifier.between(1, 2).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{1,2}", regex.toString());
        assertEquals("\\d{1,2}?", butAsFewAsPossibleRegex.toString());
    }

    @Test
    public void testExactlyQuantifier() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.exactly(2))
                .buildRegex();

        assertEquals("\\d{2}", regex.toString());
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
    public void testZeroOrMoreButAsFewAsPossible() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrMore().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d*?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("", nonGreedyMatch.group());
    }

    @Test
    public void testOneOrMoreButAsFewAsPossible() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.oneOrMore().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d+?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("9", nonGreedyMatch.group());
    }

    @Test
    public void testAtLeastButAsFewAsPossible() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.atLeast(1).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{1,}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("9", nonGreedyMatch.toMatchResult().group());
    }

    @Test
    public void testBetweenButAsFewAsPossible() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.between(2, 100).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{2,100}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("99", nonGreedyMatch.group());
    }

    @Test
    public void testNoMoreThanButAsFewAsPossible() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.noMoreThan(2).butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d{0,2}?", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("", nonGreedyMatch.group());
    }

    @Test
    public void testNoneOrOneButAsFewAsPossible() {
        final Pattern regex = new RegexBuilder()
                .digit(RegexQuantifier.zeroOrOne().butAsFewAsPossible())
                .buildRegex();

        assertEquals("\\d??", regex.toString());
        final Matcher nonGreedyMatch = regex.matcher("999");
        assertTrue(nonGreedyMatch.find());
        assertEquals("", nonGreedyMatch.group());
    }
}
