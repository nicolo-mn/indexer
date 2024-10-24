package indexer.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import indexer.controller.IndexerController;
import indexer.model.tokenization.Tokenizer;


/**
 * The MapTextIndexer class implements the TextIndexer interface and provides functionality
 * to index text files and directories containing text files. It uses a Map to store the index,
 * where the keys are tokens (words) and the values are sets of file paths where the tokens appear.
 * 
 */
public class MapTextIndexer implements TextIndexer {
    private final Map<String, Set<String>> index;
    private final Tokenizer tokenizer;
    private final IndexerController controller;

    public MapTextIndexer(IndexerController controller, Tokenizer tokenizer) {
        this.controller = controller;
        this.index = new HashMap<>();
        this.tokenizer = tokenizer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void index(String path) throws IOException {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Path does not exist: " + path);
        }

        if (Files.isDirectory(filePath)) {
            indexDirectory(filePath);
        } else if (Files.isRegularFile(filePath)) {
            indexFile(filePath);
        } else {
            throw new IllegalArgumentException("Not a regular file or directory: " + path);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> search(String word) {
        return index.getOrDefault(word.toLowerCase(), Collections.emptySet());
    }
    
    private void indexFile(Path filePath) throws IOException {
        if (!filePath.toString().endsWith(".txt")) {
            this.controller.displayInfo("Skipping non-text file: " + filePath);
            return;
        }

        String content = new String(Files.readAllBytes(filePath));
        List<String> tokens = tokenizer.tokenize(content);
        for (String token : tokens) {
            index.computeIfAbsent(token, k -> new HashSet<>()).add(filePath.toString());
        }
        this.controller.displayInfo("Indexed file: " + filePath);
    }

    private void indexDirectory(Path dirPath) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                    indexDirectory(path);
                } else if (Files.isRegularFile(path)) {
                    indexFile(path);
                }
            }
        }
        this.controller.displayInfo("Indexed directory: " + dirPath);
    }

}