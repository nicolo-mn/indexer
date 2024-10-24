package indexer.model.tokenization;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tokenizer that divides text based on whitespace characters.
 */
public class SimpleTokenizer implements Tokenizer {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> tokenize(String text) {
        return Arrays.stream(text.split("\\s+"))
                     .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                     .filter(word -> !word.isEmpty())
                     .collect(Collectors.toList());
    }
}
