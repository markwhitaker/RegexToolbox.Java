package uk.co.mainwave.regextoolbox;

final class Strings {
    // Character classes
    static final String BothCaseLatinAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String UpperCaseLatinAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String LowerCaseLatinAlphabet = "abcdefghijklmnopqrstuvwxyz";
    static final String BothCaseExtendedAlphabet = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞẞĀĂĄĆĈĊČĎĐĒĔĖĘĚĜĞĠĢĤĦĨĪĬĮİĴĶĹĻĽĿŁŃŅŇŊŌŎŐŒŔŖŘŚŜŞŠŢŤŦŨŪŬŮŰŲŴŶŸŹŻŽàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþßāăąćĉċčďđēĕėęěĝğġģĥħĩīĭįiĵķĺļľŀłńņňŋōŏőœŕŗřśŝşšţťŧũūŭůűųŵŷÿźżž";
    static final String UpperCaseExtendedAlphabet = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞẞĀĂĄĆĈĊČĎĐĒĔĖĘĚĜĞĠĢĤĦĨĪĬĮİĴĶĹĻĽĿŁŃŅŇŊŌŎŐŒŔŖŘŚŜŞŠŢŤŦŨŪŬŮŰŲŴŶŸŹŻŽ";
    static final String LowerCaseExtendedAlphabet = "àáâãäåæçèéêëìíîïðñòóôõöøùúûüýþßāăąćĉċčďđēĕėęěĝğġģĥħĩīĭįiĵķĺļľŀłńņňŋōŏőœŕŗřśŝşšţťŧũūŭůűųŵŷÿźżž";
    static final String DecimalDigits = "0123456789";
    static final String BothCaseHexDigits = "0123456789ABCDEFabcdef";
    static final String UpperCaseHexDigits = "0123456789ABCDEF";
    static final String LowerCaseHexDigits = "0123456789abcdef";
    static final String Symbols = "!\"\\|£$%^&*()-=_+[]{};'#:@~,./<>?";
    static final String WhiteSpace = " \t\n\r\f";
    static final String ControlCharacters = "\b";
    static final String Empty = "";

    // Example static final Strings
    static final String SimpleName = "Jo Smith";
    static final String SimpleEmailAddress = "regex.toolbox@mainwave.co.uk";
    static final String SimpleHttpUrl = "http://www.website.com/";
    static final String SimpleHttpsUrl = "https://www.website.com/";
    static final String Ipv4Address = "172.15.254.1";
    static final String Ipv6Address = "2001:0db8:85a3:0000:0000:8a2e:0370:7334";
    static final String MacAddress = "00:3e:e1:c4:5d:df";

    private Strings() {
    }
}
