package org.mystic;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class RTFReader {

    public static void main(String[] args) throws Exception {

        Stream<Path> files = Files.walk(Paths.get("rtf-data"));
        List<Path> collect = files.filter(Files::isRegularFile).toList();
        for (Path filePath: collect) {
            Document doc = new Document(filePath.toString());
            String docText = doc.toString(SaveFormat.TEXT).trim();
            docText = docText.replaceAll("Evaluation Only. Created with Aspose.Words. Copyright 2003-2022 Aspose Pty Ltd.", "");
            docText = docText.replaceAll("Created with an evaluation copy of Aspose.Words. To discover the full versions of our APIs please visit: https://products.aspose.com/words/", "");
            docText = docText.replaceAll("\n+", "");
            String newFilePath = filePath.toString().replace("rtf-data", "txt-data").replace(".rtf", ".txt");
            PrintWriter printWriter = new PrintWriter(newFilePath);
            printWriter.print(docText);
            printWriter.close();
        }

    }
}
