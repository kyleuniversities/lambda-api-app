package com.lambda.lambda.test;

import com.lambda.lambda.app.util.file.FileTextFinder;
import com.lambda.lambda.app.util.file.FileTextReplacer;

public class FileFinderTester {
    public static void main(String[] args) {
        String rootFolderPath =
                "C:\\Users\\kylec\\Documents\\Document-Pack\\Coding\\Art-Villa\\repos\\#\\common";
        String targetText = "com.lambda.lambda";
        String replacementText = "this.night.is.sparkling";
        System.out.println(FileTextReplacer.newInstance().replace(rootFolderPath, targetText,
                replacementText));
    }
}
