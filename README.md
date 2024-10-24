# Text File Indexer

This Java application provides a service for indexing text files using the MVC (Model-View-Controller) design pattern. The console interface allows users to specify indexed files and directories and query files containing a given word. The application is designed to be extensible by the tokenization algorithm, supporting simple splitting by words or more complex lexers.

## Features

- **Indexing**: Specify files and directories to be indexed.
- **Querying**: Search for files containing a given word.
- **Extensible Tokenization**: Easily extend the tokenization algorithm.
- **Consecutive Requests**: Serve multiple requests in a single session.

## Usage

To run the application, use the following command:

```sh
java -jar indexer.jar
```

### Build

This project uses Gradle as its build tool. To build the project, use the following command:

```sh
./gradlew build
```

### Testing

The project includes test cases to ensure the functionality of the indexing and querying features. To run the tests, use the following command:

```sh
./gradlew test
```

## MVC Architecture

The application follows the MVC design pattern:

- **Model**: Manages the data and business logic.
- **View**: Handles the console interface.
- **Controller**: Processes user input and updates the model and view.

## License

This project is licensed under the MIT License.
