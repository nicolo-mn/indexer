package indexer.controller;

/**
 * The IndexerController interface provides methods to control the execution 
 * and display information for an indexing application.
 */
public interface IndexerController {

    /**
     * Executes the main logic of the IndexerController.
     * This method should be called to start the indexing process.
     */
    void run();
    /**
     * Displays the provided informational message.
     *
     * @param message the message to be displayed
     */
    void displayInfo(String message);
}
