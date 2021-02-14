![icon](artwork/RegexToolbox-icon-100.png)

# RegexToolbox.Java [![CircleCI](https://circleci.com/gh/markwhitaker/RegexToolbox.Java.svg?style=shield)](https://circleci.com/gh/markwhitaker/RegexToolbox.Java) [![Download from JitPack](https://jitpack.io/v/markwhitaker/RegexToolbox.Java.svg)](https://jitpack.io/#markwhitaker/RegexToolbox.Java)

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

## New in 1.3: Logging

Use the new `addLogger()` method to connect a logger of your choice and see how your regex is built, step by step. For example:

```java
Pattern regex = new RegexBuilder()
    .addLogger(s -> System.out.println(s))
    .wordBoundary()
    .text("Regex")
    .anyOf("Builder", "Toolbox")
    .wordBoundary()
    .buildRegex();
```

will output this to your console:

```text
RegexBuilder: wordBoundary(): \b
RegexBuilder: text("Regex"): Regex
RegexBuilder: anyOf("Builder", "Toolbox"): (?:Builder|Toolbox)
RegexBuilder: wordBoundary(): \b
RegexBuilder: buildRegex(): \bRegex(?:Builder|Toolbox)\b
```

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

---
![icon](https://raw.githubusercontent.com/markwhitaker/RegexToolbox.kt/master/artwork/RegexToolbox-icon-32.png) **Kotlin developer?** Check out the Kotlin version of this library, [RegexToolbox.kt](https://github.com/markwhitaker/RegexToolbox.kt).

![icon](https://raw.githubusercontent.com/markwhitaker/RegexToolbox.NET/master/Artwork/RegexToolbox-icon-32.png) **.NET developer?** Check out the C# version of this library, [RegexToolbox.NET](https://github.com/markwhitaker/RegexToolbox.NET).

![icon](https://raw.githubusercontent.com/markwhitaker/RegexToolbox.JS/master/artwork/RegexToolbox-icon-32.png) **Web developer?** Check out the web version of this library, [RegexToolbox.JS](https://github.com/markwhitaker/RegexToolbox.JS).

---
###### **RegexToolbox:** Now you can be a [hero](https://xkcd.com/208/) without knowing regular expressions.
