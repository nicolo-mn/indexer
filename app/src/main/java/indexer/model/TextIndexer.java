package indexer.model;

import java.io.IOException;
import java.util.Set;

/**
 * The TextIndexer interface provides methods for indexing text files and searching for words within the indexed files.
 */
public interface TextIndexer {
    /**
     * Indexes the text content of the file located at the specified path.
     *
     * @param path the path to the file to be indexed
     * @throws IOException if an I/O error occurs while reading the file
     */
    void index(String path) throws IOException;
    
    /**
     * Searches for the given word in the index and returns a set of documents
     * that contain the word.
     *
     * @param word the word to search for in the index
     * @return a set of document identifiers where the word is found
     */
    Set<String> search(String word);
}
