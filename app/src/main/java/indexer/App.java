package indexer;

import indexer.controller.ConsoleController;
import indexer.controller.IndexerController;

/**
 * The App class serves as the entry point for the application.
 * It initializes the IndexerController and starts the application.
 */
public class App {
    public static void main(String[] args) {
        IndexerController controller = new ConsoleController();
        controller.run();
    }
}
