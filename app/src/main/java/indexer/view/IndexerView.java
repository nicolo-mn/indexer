package indexer.view;

/**
 * The IndexerView interface provides a contract for displaying messages.
 * Implementations of this interface are responsible for presenting messages
 * to the user in a specific manner.
 */
public interface IndexerView {
    /**
     * Displays a message to the user.
     *
     * @param message the message to be displayed
     */
    void displayMessage(String message);
}
