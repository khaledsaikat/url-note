# Url Note

Keep note of an URL by using a chrome extension.

## Background

The project has two components,
- API developed by Java Spring Boot
- A chrome extension. Chrome extension can be found on `src/chrome` directory.

The chrome extension can be used to mark a URL with some note.
I have used the extension while applying for jobs. The extension can keep track of URL.
When the same URL loaded again on browser pre-saved note will popup.

## Requirements

- Java 8+

## Installation

To build the jar file
`./gradlew build`

Jar file can run directly
`java -jar build/libs/url-note-0.1.0.jar`


Or the project can run using Gradle
`./gradlew bootRun`
