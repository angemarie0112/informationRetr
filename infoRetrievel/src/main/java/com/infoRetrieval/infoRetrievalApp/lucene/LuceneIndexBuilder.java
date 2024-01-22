package com.infoRetrieval.infoRetrievalApp.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class LuceneIndexBuilder {

    public static void main(String[] args) {
        String indexDirectory = "./index";

        String documentsDirectory = "./data";

        try {
            createIndex(indexDirectory, documentsDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void createIndex(String indexDirectory, String documentsDirectory) throws IOException {
        Path indexPath = FileSystems.getDefault().getPath(indexDirectory);

        Directory indexDirectoryi = FSDirectory.open(indexPath);

        StandardAnalyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        try (IndexWriter writer = new IndexWriter(indexDirectoryi, config)) {
            for (Document document : getDocuments(documentsDirectory)) {
                writer.addDocument(document);
            }
        }
    }

    private static List<Document> getDocuments(String documentsDirectory) {
        return Collections.emptyList();
    }
}
