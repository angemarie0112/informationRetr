package com.infoRetrieval.infoRetrievalApp.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;

public class LuceneSearch {

    private final String indexPath;

    public LuceneSearch(String indexPath) {
        this.indexPath = indexPath;
    }

    public void search(String queryStr) {
        try {
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(indexPath));
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            StandardAnalyzer analyzer = new StandardAnalyzer();
            QueryParser queryParser = new QueryParser("content", analyzer);

            Query query = queryParser.parse(queryStr);

            TopDocs topDocs = indexSearcher.search(query, 10);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for (ScoreDoc scoreDoc : scoreDocs) {
                int docId = scoreDoc.doc;
                Document document = indexSearcher.doc(docId);

                System.out.println("Document ID: " + docId + ", Content: " + document.get("content"));
            }

            indexReader.close();
            directory.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String indexDirectory = "./index";

        String documentsDirectory = "./data";

        try {

            LuceneIndexBuilder.createIndex(indexDirectory, documentsDirectory);

            String queryStr = "your search query";
            LuceneSearch luceneSearch = new LuceneSearch(indexDirectory);
            luceneSearch.search(queryStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
