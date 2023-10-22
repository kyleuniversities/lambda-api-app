package com.lambda.lambda.test;

import com.lambda.lambda.app.util.file.FileTextFinder;

public class FileFinderTester {
    public static void main(String[] args) {
        String rootFolderPath = "../..";
        String targetText = "com.lambda.lambda";
        System.out.println(FileTextFinder.newInstance().find(rootFolderPath, targetText));
    }
}
