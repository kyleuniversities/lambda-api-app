package com.lambda.lambda.app.util.file;

import java.io.File;
import java.util.List;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.file.FileHelper;
import com.lambda.lambda.common.helper.file.FilePathHelper;
import com.lambda.lambda.common.helper.string.StringCodeHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.string.StringMap;
import com.lambda.lambda.common.util.string.StringMapMap;

public final class FileTextFinder {
    // Class Fields
    private static final List<String> TEXT_EXTENSION_LIST = FileTextFinder.makeTextExtensionList();

    // Instance Fields
    private String rootFolderPath;
    private String targetText;

    // New Instance Method
    public static FileTextFinder newInstance() {
        return new FileTextFinder();
    }

    // Constructor Method
    private FileTextFinder() {
        super();
    }

    // Main Instance Method
    public StringList find(String rootFolderPath, String targetText) {
        this.reset(rootFolderPath, targetText);
        StringMapMap findings = StringMapMap.newInstance();
        FileHelper.forEachFileDescendant(rootFolderPath, (File file) -> {
            String path = FilePathHelper.getAbsolutePath(file);
            boolean isTextExtension =
                    FilePathHelper.isMatchingExtension(path, FileTextFinder.TEXT_EXTENSION_LIST);
            ConditionalHelper.ifThen(isTextExtension, () -> this.appendFindings(findings, path));
        });
        return findings.toStringList();
    }

    // Major Methods
    private void appendFindings(StringMapMap findings, String filePath) {
        StringList lines = FileHelper.getStringList(filePath);
        ListHelper.forEach(lines, (Integer lineIndex, String line) -> {
            if (line.contains(this.targetText)) {
                String relativePath = FilePathHelper.getRelativePath(this.rootFolderPath, filePath);
                StringMap fileFindings = findings.getInitalize(relativePath);
                String lineText = "LINE: " + (lineIndex + 1);
                MapHelper.put(fileFindings, lineText, StringCodeHelper.toCode(line));
            }
        });
    }

    // Initialization Methods
    private void reset(String rootFolderPath, String targetText) {
        this.rootFolderPath = StringTrimmerHelper.trimSurroundingWhiteSpace(rootFolderPath);
        this.targetText = targetText;
    }

    private static List<String> makeTextExtensionList() {
        List<String> textExtensionList = ListHelper.newArrayList();
        ListHelper.add(textExtensionList, ".css");
        ListHelper.add(textExtensionList, ".env");
        ListHelper.add(textExtensionList, ".gitignore");
        ListHelper.add(textExtensionList, ".html");
        ListHelper.add(textExtensionList, ".java");
        ListHelper.add(textExtensionList, ".js");
        ListHelper.add(textExtensionList, ".md");
        ListHelper.add(textExtensionList, ".ts");
        ListHelper.add(textExtensionList, ".tsx");
        ListHelper.add(textExtensionList, ".txt");
        ListHelper.add(textExtensionList, ".xml");
        ListHelper.add(textExtensionList, ".yml");
        return textExtensionList;
    }
}
