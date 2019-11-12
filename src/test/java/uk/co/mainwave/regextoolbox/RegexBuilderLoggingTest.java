package uk.co.mainwave.regextoolbox;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static uk.co.mainwave.regextoolbox.RegexQuantifier.atLeast;
import static uk.co.mainwave.regextoolbox.RegexQuantifier.between;
import static uk.co.mainwave.regextoolbox.RegexQuantifier.exactly;
import static uk.co.mainwave.regextoolbox.RegexQuantifier.noMoreThan;
import static uk.co.mainwave.regextoolbox.RegexQuantifier.oneOrMore;
import static uk.co.mainwave.regextoolbox.RegexQuantifier.zeroOrMore;
import static uk.co.mainwave.regextoolbox.RegexQuantifier.zeroOrOne;

public class RegexBuilderLoggingTest {
    private String logOutput;
    private RegexBuilder regexBuilder;

    @Before
    public void setUp() {
        logOutput = "";
        regexBuilder = new RegexBuilder().addLogger(s -> logOutput = s);
    }
    
    @Test
    public void testAddLogger() throws RegexBuilderException {
        final RegexBuilder.Logger logger = mock(RegexBuilder.Logger.class);

        new RegexBuilder()
            .addLogger(logger)
            .text("Hello")
            .whitespace(oneOrMore())
            .text("world", zeroOrOne())
            .buildRegex();

        verify(logger).log("RegexBuilder: text(\"Hello\"): Hello");
        verify(logger).log("RegexBuilder: whitespace(oneOrMore()): \\s+");
        verify(logger).log("RegexBuilder: text(\"world\", zeroOrOne()): (?:world)?");
        verify(logger).log("RegexBuilder: buildRegex(): Hello\\s+(?:world)?");
    }

    @Test
    public void testAddLoggerWithPrefix() throws RegexBuilderException {
        final RegexBuilder.Logger logger = mock(RegexBuilder.Logger.class);

        new RegexBuilder()
            .addLogger(logger, "TEST")
            .text("Hello")
            .whitespace(oneOrMore())
            .text("world", zeroOrOne())
            .buildRegex();

        verify(logger).log("TEST: text(\"Hello\"): Hello");
        verify(logger).log("TEST: whitespace(oneOrMore()): \\s+");
        verify(logger).log("TEST: text(\"world\", zeroOrOne()): (?:world)?");
        verify(logger).log("TEST: buildRegex(): Hello\\s+(?:world)?");
    }

    @Test
    public void testText() {
        regexBuilder.text("[a-z]");
        assertEquals("RegexBuilder: text(\"[a-z]\"): \\[a-z\\]", logOutput);
    }


    @Test
    public void testTextWithQuantifier() {
        regexBuilder.text("[a-z]", zeroOrMore());
        assertEquals("RegexBuilder: text(\"[a-z]\", zeroOrMore()): (?:\\[a-z\\])*", logOutput);
    }

    @Test
    public void testRegexText() {
        regexBuilder.regexText("[a-z]");
        assertEquals("RegexBuilder: regexText(\"[a-z]\"): [a-z]", logOutput);
    }

    @Test
    public void testRegexTextWithQuantifier() {
        regexBuilder.regexText("[a-z]", zeroOrMore());
        assertEquals("RegexBuilder: regexText(\"[a-z]\", zeroOrMore()): (?:[a-z])*", logOutput);
    }

    @Test
    public void testAnyCharacter() {
        regexBuilder.anyCharacter();
        assertEquals("RegexBuilder: anyCharacter(): .", logOutput);
    }

    @Test
    public void testAnyCharacterWithQuantifier() {
        regexBuilder.anyCharacter(zeroOrMore());
        assertEquals("RegexBuilder: anyCharacter(zeroOrMore()): .*", logOutput);
    }

    @Test
    public void testWhitespace() {
        regexBuilder.whitespace();
        assertEquals("RegexBuilder: whitespace(): \\s", logOutput);
    }

    @Test
    public void testWhitespaceWithQuantifier() {
        regexBuilder.whitespace(zeroOrMore());
        assertEquals("RegexBuilder: whitespace(zeroOrMore()): \\s*", logOutput);
    }

    @Test
    public void testNonWhitespace() {
        regexBuilder.nonWhitespace();
        assertEquals("RegexBuilder: nonWhitespace(): \\S", logOutput);
    }

    @Test
    public void testNonWhitespaceWithQuantifier() {
        regexBuilder.nonWhitespace(zeroOrMore());
        assertEquals("RegexBuilder: nonWhitespace(zeroOrMore()): \\S*", logOutput);
    }

    @Test
    public void testPossibleWhitespace() {
        regexBuilder.possibleWhitespace();
        assertEquals("RegexBuilder: possibleWhitespace(): \\s*", logOutput);
    }

    @Test
    public void testSpace() {
        regexBuilder.space();
        assertEquals("RegexBuilder: space():  ", logOutput);
    }

    @Test
    public void testSpaceWithQuantifier() {
        regexBuilder.space(zeroOrMore());
        assertEquals("RegexBuilder: space(zeroOrMore()):  *", logOutput);
    }

    @Test
    public void testTab() {
        regexBuilder.tab();
        assertEquals("RegexBuilder: tab(): \\t", logOutput);
    }

    @Test
    public void testTabWithQuantifier() {
        regexBuilder.tab(zeroOrMore());
        assertEquals("RegexBuilder: tab(zeroOrMore()): \\t*", logOutput);
    }

    @Test
    public void testLineFeed() {
        regexBuilder.lineFeed();
        assertEquals("RegexBuilder: lineFeed(): \\n", logOutput);
    }

    @Test
    public void testLineFeedWithQuantifier() {
        regexBuilder.lineFeed(zeroOrMore());
        assertEquals("RegexBuilder: lineFeed(zeroOrMore()): \\n*", logOutput);
    }

    @Test
    public void testCarriageReturn() {
        regexBuilder.carriageReturn();
        assertEquals("RegexBuilder: carriageReturn(): \\r", logOutput);
    }

    @Test
    public void testCarriageReturnWithQuantifier() {
        regexBuilder.carriageReturn(zeroOrMore());
        assertEquals("RegexBuilder: carriageReturn(zeroOrMore()): \\r*", logOutput);
    }

    @Test
    public void testDigit() {
        regexBuilder.digit();
        assertEquals("RegexBuilder: digit(): \\d", logOutput);
    }

    @Test
    public void testDigitWithQuantifier() {
        regexBuilder.digit(zeroOrMore());
        assertEquals("RegexBuilder: digit(zeroOrMore()): \\d*", logOutput);
    }

    @Test
    public void testNonDigit() {
        regexBuilder.nonDigit();
        assertEquals("RegexBuilder: nonDigit(): \\D", logOutput);
    }

    @Test
    public void testNonDigitWithQuantifier() {
        regexBuilder.nonDigit(zeroOrMore());
        assertEquals("RegexBuilder: nonDigit(zeroOrMore()): \\D*", logOutput);
    }

    @Test
    public void testLetter() {
        regexBuilder.letter();
        assertEquals("RegexBuilder: letter(): \\p{L}", logOutput);
    }

    @Test
    public void testLetterWithQuantifier() {
        regexBuilder.letter(zeroOrMore());
        assertEquals("RegexBuilder: letter(zeroOrMore()): \\p{L}*", logOutput);
    }

    @Test
    public void testNonLetter() {
        regexBuilder.nonLetter();
        assertEquals("RegexBuilder: nonLetter(): \\P{L}", logOutput);
    }

    @Test
    public void testNonLetterWithQuantifier() {
        regexBuilder.nonLetter(zeroOrMore());
        assertEquals("RegexBuilder: nonLetter(zeroOrMore()): \\P{L}*", logOutput);
    }

    @Test
    public void testUppercaseLetter() {
        regexBuilder.uppercaseLetter();
        assertEquals("RegexBuilder: uppercaseLetter(): \\p{Lu}", logOutput);
    }

    @Test
    public void testUppercaseLetterWithQuantifier() {
        regexBuilder.uppercaseLetter(zeroOrMore());
        assertEquals("RegexBuilder: uppercaseLetter(zeroOrMore()): \\p{Lu}*", logOutput);
    }

    @Test
    public void testLowercaseLetter() {
        regexBuilder.lowercaseLetter();
        assertEquals("RegexBuilder: lowercaseLetter(): \\p{Ll}", logOutput);
    }

    @Test
    public void testLowercaseLetterWithQuantifier() {
        regexBuilder.lowercaseLetter(zeroOrMore());
        assertEquals("RegexBuilder: lowercaseLetter(zeroOrMore()): \\p{Ll}*", logOutput);
    }

    @Test
    public void testLetterOrDigit() {
        regexBuilder.letterOrDigit();
        assertEquals("RegexBuilder: letterOrDigit(): [\\p{L}0-9]", logOutput);
    }

    @Test
    public void testLetterOrDigitWithQuantifier() {
        regexBuilder.letterOrDigit(zeroOrMore());
        assertEquals("RegexBuilder: letterOrDigit(zeroOrMore()): [\\p{L}0-9]*", logOutput);
    }

    @Test
    public void testNonLetterOrDigit() {
        regexBuilder.nonLetterOrDigit();
        assertEquals("RegexBuilder: nonLetterOrDigit(): [^\\p{L}0-9]", logOutput);
    }

    @Test
    public void testNonLetterOrDigitWithQuantifier() {
        regexBuilder.nonLetterOrDigit(zeroOrMore());
        assertEquals("RegexBuilder: nonLetterOrDigit(zeroOrMore()): [^\\p{L}0-9]*", logOutput);
    }

    @Test
    public void testHexDigit() {
        regexBuilder.hexDigit();
        assertEquals("RegexBuilder: hexDigit(): [0-9A-Fa-f]", logOutput);
    }

    @Test
    public void testHexDigitWithQuantifier() {
        regexBuilder.hexDigit(zeroOrMore());
        assertEquals("RegexBuilder: hexDigit(zeroOrMore()): [0-9A-Fa-f]*", logOutput);
    }

    @Test
    public void testUppercaseHexDigit() {
        regexBuilder.uppercaseHexDigit();
        assertEquals("RegexBuilder: uppercaseHexDigit(): [0-9A-F]", logOutput);
    }

    @Test
    public void testUppercaseHexDigitWithQuantifier() {
        regexBuilder.uppercaseHexDigit(zeroOrMore());
        assertEquals("RegexBuilder: uppercaseHexDigit(zeroOrMore()): [0-9A-F]*", logOutput);
    }

    @Test
    public void testLowercaseHexDigit() {
        regexBuilder.lowercaseHexDigit();
        assertEquals("RegexBuilder: lowercaseHexDigit(): [0-9a-f]", logOutput);
    }

    @Test
    public void testLowercaseHexDigitWithQuantifier() {
        regexBuilder.lowercaseHexDigit(zeroOrMore());
        assertEquals("RegexBuilder: lowercaseHexDigit(zeroOrMore()): [0-9a-f]*", logOutput);
    }

    @Test
    public void testNonHexDigit() {
        regexBuilder.nonHexDigit();
        assertEquals("RegexBuilder: nonHexDigit(): [^0-9A-Fa-f]", logOutput);
    }

    @Test
    public void testNonHexDigitWithQuantifier() {
        regexBuilder.nonHexDigit(zeroOrMore());
        assertEquals("RegexBuilder: nonHexDigit(zeroOrMore()): [^0-9A-Fa-f]*", logOutput);
    }

    @Test
    public void testWordCharacter() {
        regexBuilder.wordCharacter();
        assertEquals("RegexBuilder: wordCharacter(): [\\p{L}0-9_]", logOutput);
    }

    @Test
    public void testWordCharacterWithQuantifier() {
        regexBuilder.wordCharacter(zeroOrMore());
        assertEquals("RegexBuilder: wordCharacter(zeroOrMore()): [\\p{L}0-9_]*", logOutput);
    }

    @Test
    public void testNonWordCharacter() {
        regexBuilder.nonWordCharacter();
        assertEquals("RegexBuilder: nonWordCharacter(): [^\\p{L}0-9_]", logOutput);
    }

    @Test
    public void testNonWordCharacterWithQuantifier() {
        regexBuilder.nonWordCharacter(zeroOrMore());
        assertEquals("RegexBuilder: nonWordCharacter(zeroOrMore()): [^\\p{L}0-9_]*", logOutput);
    }

    @Test
    public void testAnyCharacterFrom() {
        regexBuilder.anyCharacterFrom("abc");
        assertEquals("RegexBuilder: anyCharacterFrom(\"abc\"): [abc]", logOutput);
    }

    @Test
    public void testAnyCharacterFromWithQuantifier() {
        regexBuilder.anyCharacterFrom("abc", zeroOrMore());
        assertEquals("RegexBuilder: anyCharacterFrom(\"abc\", zeroOrMore()): [abc]*", logOutput);
    }

    @Test
    public void testAnyCharacterExcept() {
        regexBuilder.anyCharacterExcept("abc");
        assertEquals("RegexBuilder: anyCharacterExcept(\"abc\"): [^abc]", logOutput);
    }

    @Test
    public void testAnyCharacterExceptWithQuantifier() {
        regexBuilder.anyCharacterExcept("abc", zeroOrMore());
        assertEquals("RegexBuilder: anyCharacterExcept(\"abc\", zeroOrMore()): [^abc]*", logOutput);
    }

    @Test
    public void testAnyOfNull() {
        final String[] strings = null;
        //noinspection ConstantConditions
        regexBuilder.anyOf(strings);
        assertEquals("RegexBuilder: anyOf(): strings list is empty, so doing nothing", logOutput);
    }

    @Test
    public void testAnyOfNullWithQuantifier() {
        final String[] strings = null;
        //noinspection ConstantConditions
        regexBuilder.anyOf(strings, zeroOrMore());
        assertEquals("RegexBuilder: anyOf(): strings list is empty, so doing nothing", logOutput);
    }

    @Test
    public void testAnyOfEmpty() {
        regexBuilder.anyOf();
        assertEquals("RegexBuilder: anyOf(): strings list is empty, so doing nothing", logOutput);
    }

    @Test
    public void testAnyOfEmptyWithQuantifier() {
        regexBuilder.anyOf(new String[]{}, zeroOrMore());
        assertEquals("RegexBuilder: anyOf(): strings list is empty, so doing nothing", logOutput);
    }

    @Test
    public void testAnyOfSingle() {
        final String[] strings = new String[]{"abc"};
        regexBuilder.anyOf(strings);
        assertEquals("RegexBuilder: anyOf(\"abc\"): abc", logOutput);
    }

    @Test
    public void testAnyOfSingleWithQuantifier() {
        regexBuilder.anyOf(new String[]{"abc"}, zeroOrMore());
        assertEquals("RegexBuilder: anyOf(\"abc\", zeroOrMore()): (?:abc)*", logOutput);
    }

    @Test
    public void testAnyOfParamsSingle() {
        regexBuilder.anyOf("abc");
        assertEquals("RegexBuilder: anyOf(\"abc\"): abc", logOutput);
    }

    @Test
    public void testAnyOfMultiple() {
        final String[] strings = new String[]{"abc", "def"};
        regexBuilder.anyOf(strings);
        assertEquals("RegexBuilder: anyOf(\"abc\", \"def\"): (?:abc|def)", logOutput);
    }

    @Test
    public void testAnyOfMultipleWithQuantifier() {
        final String[] strings = new String[]{"abc", "def"};
        regexBuilder.anyOf(strings, zeroOrMore());
        assertEquals("RegexBuilder: anyOf(\"abc\", \"def\", zeroOrMore()): (?:abc|def)*", logOutput);
    }

    @Test
    public void testAnyOfParamsMultiple() {
        regexBuilder.anyOf("abc", "def");
        assertEquals("RegexBuilder: anyOf(\"abc\", \"def\"): (?:abc|def)", logOutput);
    }

    @Test
    public void testStartOfString() {
        regexBuilder.startOfString();
        assertEquals("RegexBuilder: startOfString(): ^", logOutput);
    }

    @Test
    public void testEndOfString() {
        regexBuilder.endOfString();
        assertEquals("RegexBuilder: endOfString(): $", logOutput);
    }

    @Test
    public void testWordBoundary() {
        regexBuilder.wordBoundary();
        assertEquals("RegexBuilder: wordBoundary(): \\b", logOutput);
    }

    @Test
    public void testStartGroup() {
        regexBuilder.startGroup();
        assertEquals("RegexBuilder: startGroup(): (", logOutput);
    }

    @Test
    public void testStartNonCapturingGroup() {
        regexBuilder.startNonCapturingGroup();
        assertEquals("RegexBuilder: startNonCapturingGroup(): (?:", logOutput);
    }

    @Test
    public void testStartNamedGroup() {
        regexBuilder.startNamedGroup("bert");
        assertEquals("RegexBuilder: startNamedGroup(\"bert\"): (?<bert>", logOutput);
    }

    @Test
    public void testEndGroup() throws RegexBuilderException {
        regexBuilder.startGroup().endGroup();
        assertEquals("RegexBuilder: endGroup(): )", logOutput);
    }

    @Test
    public void testQuantifierzeroOrMore() {
        regexBuilder.anyCharacter(zeroOrMore());
        assertEquals("RegexBuilder: anyCharacter(zeroOrMore()): .*", logOutput);
    }

    @Test
    public void testQuantifierzeroOrMoreButAsFewAsPossible() {
        regexBuilder.anyCharacter(zeroOrMore().butAsFewAsPossible());
        assertEquals("RegexBuilder: anyCharacter(zeroOrMore().butAsFewAsPossible()): .*?", logOutput);
    }

    @Test
    public void testQuantifierOneOrMore() {
        regexBuilder.anyCharacter(oneOrMore());
        assertEquals("RegexBuilder: anyCharacter(oneOrMore()): .+", logOutput);
    }

    @Test
    public void testQuantifierOneOrMoreButAsFewAsPossible() {
        regexBuilder.anyCharacter(oneOrMore().butAsFewAsPossible());
        assertEquals("RegexBuilder: anyCharacter(oneOrMore().butAsFewAsPossible()): .+?", logOutput);
    }

    @Test
    public void testQuantifierZeroOrOne() {
        regexBuilder.anyCharacter(zeroOrOne());
        assertEquals("RegexBuilder: anyCharacter(zeroOrOne()): .?", logOutput);
    }

    @Test
    public void testQuantifierZeroOrOneButAsFewAsPossible() {
        regexBuilder.anyCharacter(zeroOrOne().butAsFewAsPossible());
        assertEquals("RegexBuilder: anyCharacter(zeroOrOne().butAsFewAsPossible()): .??", logOutput);
    }

    @Test
    public void testQuantifierExactly() {
        regexBuilder.anyCharacter(exactly(10));
        assertEquals("RegexBuilder: anyCharacter(exactly(10)): .{10}", logOutput);
    }

    @Test
    public void testQuantifierAtLeast() {
        regexBuilder.anyCharacter(atLeast(10));
        assertEquals("RegexBuilder: anyCharacter(atLeast(10)): .{10,}", logOutput);
    }

    @Test
    public void testQuantifierAtLeastbutAsFewAsPossible() {
        regexBuilder.anyCharacter(atLeast(10).butAsFewAsPossible());
        assertEquals("RegexBuilder: anyCharacter(atLeast(10).butAsFewAsPossible()): .{10,}?", logOutput);
    }

    @Test
    public void testQuantifierNoMoreThan() {
        regexBuilder.anyCharacter(noMoreThan(10));
        assertEquals("RegexBuilder: anyCharacter(noMoreThan(10)): .{0,10}", logOutput);
    }

    @Test
    public void testQuantifierNoMoreThanbutAsFewAsPossible() {
        regexBuilder.anyCharacter(noMoreThan(10).butAsFewAsPossible());
        assertEquals("RegexBuilder: anyCharacter(noMoreThan(10).butAsFewAsPossible()): .{0,10}?", logOutput);
    }

    @Test
    public void testQuantifierBetween() {
        regexBuilder.anyCharacter(between(10, 20));
        assertEquals("RegexBuilder: anyCharacter(between(10, 20)): .{10,20}", logOutput);
    }

    @Test
    public void testQuantifierBetweenbutAsFewAsPossible() {
        regexBuilder.anyCharacter(between(10, 20).butAsFewAsPossible());
        assertEquals("RegexBuilder: anyCharacter(between(10, 20).butAsFewAsPossible()): .{10,20}?", logOutput);
    }

    @Test
    public void testBuildRegex() throws RegexBuilderException {
        regexBuilder
                .text("hello")
                .whitespace(oneOrMore())
                .text("world")
                .buildRegex();
        assertEquals("RegexBuilder: buildRegex(): hello\\s+world", logOutput);
    }

    @Test
    public void testBuildRegexWithOptions() throws RegexBuilderException {
        regexBuilder
                .text("hello")
                .whitespace(oneOrMore())
                .text("world")
                .buildRegex(RegexOptions.MULTILINE, RegexOptions.IGNORE_CASE);
        assertEquals("RegexBuilder: buildRegex(): hello\\s+world", logOutput);
    }
}
