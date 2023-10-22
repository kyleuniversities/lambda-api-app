package com.lambda.lambda.common.helper.file;

import java.io.File;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import com.lambda.lambda.common.helper.ArrayHelper;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.FunctionHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringReplacementHelper;

/**
 * Helper class for File Path Operations
 */
public class FilePathHelper {
    /**
     * Gets the absolute path of a file
     */
    public static String getAbsolutePath(File file) {
        return file.getAbsolutePath();
    }

    /**
     * Gets the extensionless file name of a file
     */
    public static String getExtensionlessFileName(File file) {
        return FilePathHelper.getExtensionlessFileName(FilePathHelper.getAbsolutePath(file));
    }

    /**
     * Gets the extensionless file name of a file path
     */
    public static String getExtensionlessFileName(String path) {
        String fileName = FilePathHelper.getFileName(path);
        int lastPeriodIndex = StringHelper.lastIndexOf(fileName, '.');
        return ConditionalHelper.newTernaryOperation(lastPeriodIndex == -1, () -> fileName,
                () -> StringHelper.substring(fileName, 0, lastPeriodIndex));
    }

    /**
     * Gets the file name of a file
     */
    public static String getFileName(File file) {
        return FilePathHelper.getFileName(FilePathHelper.getAbsolutePath(file));
    }

    /**
     * Gets the file name of a file path
     */
    public static String getFileName(String path) {
        String slashedPath = FilePathHelper.toSlashedPath(path);
        int lastSlashIndex = StringHelper.lastIndexOf(slashedPath, '/');
        return StringHelper.substring(slashedPath, lastSlashIndex + 1);
    }

    /**
     * Gets the relative path in relation to a root path
     */
    public static String getRelativePath(File rootFolder, File file) {
        return FilePathHelper.getRelativePath(rootFolder.getAbsolutePath(), file.getAbsolutePath());
    }

    /**
     * Gets the relative path in relation to a root path
     */
    public static String getRelativePath(String rootFolderPath, File file) {
        return FilePathHelper.getRelativePath(rootFolderPath, file.getAbsolutePath());
    }

    /**
     * Gets the relative path in relation to a root path
     */
    public static String getRelativePath(String rootFolderPath, String path) {
        String slashedRootFolderPath = FilePathHelper.toSlashedPath(rootFolderPath);
        String slashedPath = FilePathHelper.toSlashedPath(path);
        return ConditionalHelper.newTernaryOperation(slashedRootFolderPath.equals(slashedPath),
                () -> "",
                () -> StringHelper.substring(slashedPath, slashedRootFolderPath.length() + 1));
    }

    /**
     * Checks if a path has a matching extension
     */
    public static boolean isMatchingExtension(String path, List<String> extensionList) {
        return ListHelper.isTrueForAny(extensionList,
                (String extension) -> StringHelper.endsWith(path, extension));
    }

    /**
     * Converts a path to slashed path
     */
    public static String toSlashedPath(String path) {
        return StringReplacementHelper.replace(path, "\\", "/");
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private FilePathHelper() {
        super();
    }
}
