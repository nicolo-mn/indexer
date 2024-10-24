package indexer.view;

/**
 * The ConsoleView class implements the IndexerView interface and provides
 * a method to display messages to the console.
 */
public class ConsoleView implements IndexerView {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
