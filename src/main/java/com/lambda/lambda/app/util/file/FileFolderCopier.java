package com.lambda.lambda.app.util.file;

import java.io.File;
import java.util.List;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.file.FileHelper;
import com.lambda.lambda.common.helper.file.FilePathHelper;
import com.lambda.lambda.common.helper.string.StringCodeHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringReplacementHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.string.StringMap;
import com.lambda.lambda.common.util.string.StringMapMap;

public final class FileFolderCopier {
    // Instance Fields
    private String sourceFolderPath;
    private String destinationFolderPath;
    private String fileSearchQuery;
    private StringMap fileReplacementMap;
    private StringMap textReplacementMap;

    // New Instance Method
    public static FileFolderCopier newInstance() {
        return new FileFolderCopier();
    }

    // Constructor Method
    private FileFolderCopier() {
        super();
    }

    // Main Instance Method
    public void copy(StringList paths, String sourceFolderPath, String destinationFolderPath, String fileSearchQuery,
            StringMap fileReplacementMap, StringMap textReplacementMap) {
        FileHelper.forEachFileDescendant(sourceFolderPath, (File file) -> {
            String relativePath = FilePathHelper.getRelativePath(sourceFolderPath, file);
            String sourcePath = FilePathHelper.getAbsolutePath(file);
            String sourceName = FilePathHelper.getFileName(sourcePath);
            if (sourceName.contains(fileSearchQuery)) {
                ListHelper.add(paths, relativePath);
                String destinationName = StringReplacementHelper.replace(sourceName, fileReplacementMap);
                String rawDestinationPath = destinationFolderPath + "/" + relativePath;
                String destinationParentPath = FilePathHelper.getParentFolderPath(rawDestinationPath);
                String destinationPath = destinationParentPath + "/" + destinationName;
                FileHelper.makeNewDirectoryAtPath(destinationParentPath);
                String sourceText = FileHelper.getText(sourcePath);
                String destinationText = StringReplacementHelper.replace(sourceText, textReplacementMap);
                FileHelper.exportText(destinationText, destinationPath);
            }
        });
    }
}