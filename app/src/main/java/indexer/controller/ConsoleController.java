package indexer.controller;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import indexer.view.IndexerView;
import indexer.model.MapTextIndexer;
import indexer.model.TextIndexer;
import indexer.model.tokenization.SimpleTokenizer;
import indexer.view.ConsoleView;


/**
 * The ConsoleController class implements the IndexerController interface and provides
 * a command-line interface for interacting with the file indexer. It uses a ConsoleView
 * to display messages to the user and a TextIndexer to perform indexing and searching
 * operations.
 */
public class ConsoleController implements IndexerController {
    private final IndexerView consoleView;
    private final TextIndexer indexer;

    public ConsoleController() {
        this.consoleView = new ConsoleView();
        this.indexer = new MapTextIndexer(this, new SimpleTokenizer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        this.consoleView.displayMessage("Welcome to the File Indexer!");

        while (true) {
            this.consoleView.displayMessage("\nCommands:");
            this.consoleView.displayMessage("1. Index a file or directory");
            this.consoleView.displayMessage("2. Query for a word");
            this.consoleView.displayMessage("3. Exit");

            this.consoleView.displayMessage("\nEnter a command: ");
            try {
                final boolean stop = processCommand(reader);
                if (stop) {
                    break;
                }
            } catch (IOException e) {
                final String errorMessage = e.getMessage() == null ? "An error occurred" : e.getMessage();
                consoleView.displayMessage("An error occurred: " + errorMessage);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayInfo(String message) {
        this.consoleView.displayMessage("INFO: " + message);
    }

    private boolean processCommand(BufferedReader reader) throws IOException {
        String command = reader.readLine();  
        switch (command) {
            case "1":
                this.consoleView.displayMessage("Enter the file or directory path to index: ");
                final String path = reader.readLine();
                indexer.index(path);
                break;

            case "2":
                this.consoleView.displayMessage("Enter the word to search for: ");
                final String word = reader.readLine();
                final Set<String> results = indexer.search(word);
                if (results.isEmpty()) {
                    this.consoleView.displayMessage("No results found for the word: " + word);
                } else {
                    this.consoleView.displayMessage("Results for the word '" + word + "':");
                    for (final String result : results) {
                        this.consoleView.displayMessage(result);
                    }
                }
                break;

            case "3":
                this.consoleView.displayMessage("Exiting...");
                return true;

            default:
                this.consoleView.displayMessage("Invalid command. Please try again.");
                break;
        }
        return false;
    }
}
