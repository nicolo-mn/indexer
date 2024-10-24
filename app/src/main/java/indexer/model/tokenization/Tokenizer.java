package indexer.model.tokenization;

import java.util.List;

/**
 * Tokenizer object to process text.
 */
public interface Tokenizer {
    /**
     * Applies the tokenization logic
     * @param text
     * @return the tokens contained in the text
     */
    List<String> tokenize(String text);
}
