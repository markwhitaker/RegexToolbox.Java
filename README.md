![icon](artwork/RegexToolbox-icon-100.png)

# RegexToolbox.Java [![Build and test](https://github.com/markwhitaker/RegexToolbox.Java/actions/workflows/build-and-test.yml/badge.svg)](https://github.com/markwhitaker/RegexToolbox.Java/actions/workflows/build-and-test.yml) [![Download from JitPack](https://jitpack.io/v/markwhitaker/RegexToolbox.Java.svg)](https://jitpack.io/#markwhitaker/RegexToolbox.Java)

Regular expression tools for Java developers.


## RegexBuilder

`RegexBuilder` is a class for building regular expressions in a more human-readable way using a fluent API. It offers a number of benefits over using raw regex syntax in strings:

 - No knowledge of regular expression syntax is required: just use simple, intuitively-named classes and methods.
 - Code is easier to read, understand and maintain.
 - Code is safer and far less prone to regular expression syntax errors and programmer errors.

Here's an example:

```java
Pattern regex = new RegexBuilder()
    .text("Hello")
    .whitespace(RegexQuantifier.oneOrMore())
    .text("world!")
    .buildRegex();
```

But that's just a taste of what `RegexBuilder` does: for full API documentation, head over to the [project wiki](https://github.com/markwhitaker/RegexToolbox.Java/wiki).

## Breaking change in 2.0: Logging removed

The logging API added in version 1.3 wasn't adding as much value as intended and incurred an additional maintenance overhead. It is removed in version 2.0.

## Usage (Gradle)

RegexToolbox.Java is [hosted on JitPack](https://jitpack.io/#markwhitaker/RegexToolbox.Java).
Replace `x.y.z` with the latest version (shown in the JitPack badge at the top of this page).

```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}

implementation 'com.github.markwhitaker:RegexToolbox.Java:x.y.z'
```

## Also for Java developers

![icon](https://raw.githubusercontent.com/markwhitaker/MimeTypes.Java/main/artwork/MimeTypes-icon-32.png) [MimeTypes.Java](https://github.com/markwhitaker/MimeTypes.Java): MIME type constants for your Java projects

## RegexToolbox for other languages


![icon](https://raw.githubusercontent.com/markwhitaker/RegexToolbox.kt/master/artwork/RegexToolbox-icon-32.png) [RegexToolbox for Kotlin](https://github.com/markwhitaker/RegexToolbox.kt)

![icon](https://raw.githubusercontent.com/markwhitaker/RegexToolbox.NET/master/Artwork/RegexToolbox-icon-32.png) [RegexToolbox for .NET](https://github.com/markwhitaker/RegexToolbox.NET)

![icon](https://raw.githubusercontent.com/markwhitaker/RegexToolbox.JS/master/artwork/RegexToolbox-icon-32.png) [RegexToolbox for JavaScript](https://github.com/markwhitaker/RegexToolbox.JS)

---
###### **RegexToolbox:** Now you can be a [hero](https://xkcd.com/208/) without knowing regular expressions.
