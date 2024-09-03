package com.lambda.lambda.app.controller.file;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.app.util.LambdaArguments;
import com.lambda.lambda.app.util.file.FileFolderCopier;
import com.lambda.lambda.app.util.file.FileTextFinder;
import com.lambda.lambda.app.util.file.FileTextReplacer;
import com.lambda.lambda.app.util.file.FileTextTimeLastModifiedSorter;
import com.lambda.lambda.app.util.java.JavaStructureClassMaker;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.string.StringMap;

@CrossOrigin
@RestController
@RequestMapping("/file")
public final class FileController {
    @PostMapping("/find")
    public String find(@RequestBody LambdaArguments lambdaArguments) {
        StringList findingsLines = FileTextFinder.newInstance().find(lambdaArguments.getBodyText(),
                lambdaArguments.getArgument(0));
        String findingsLinesText = findingsLines.toString();
        return CodeHelper.toCode(findingsLinesText);
    }

    @PostMapping("/replace")
    public String replace(@RequestBody LambdaArguments lambdaArguments) {
        StringList findingsLines = FileTextReplacer.newInstance().replace(lambdaArguments.getBodyText(),
                lambdaArguments.getArgument(0), lambdaArguments.getArgument(1));
        String findingsLinesText = findingsLines.toString();
        return CodeHelper.toCode(findingsLinesText);
    }

    @PostMapping("/text/sort-by-last-modified")
    public String textSortByLastModified(@RequestBody LambdaArguments lambdaArguments) {
        StringList sortedLines = FileTextTimeLastModifiedSorter.newInstance().sort(lambdaArguments.getBodyLines());
        String sortedLinesText = sortedLines.toString();
        return CodeHelper.toCode(sortedLinesText);
    }

    @PostMapping("/copy")
    public String copy(@RequestBody LambdaArguments lambdaArguments) {
        StringList paths = StringList.newInstance();
        boolean isCased = lambdaArguments.argumentsContain("--cased");
        StringList body = lambdaArguments.getNonEmptyBodyLines();
        String sourceFolderPath = body.get(0);
        String destinationFolderPath = body.get(1);
        String fileSearchQuery = body.get(2);
        StringMap fileReplacementMap = StringHelper.newStringMap();
        StringMap textReplacementMap = StringHelper.newStringMap();
        StringList fileReplacementMapTextParts = body.getTokens(3);
        StringList textReplacementMapTextParts = body.size() >= 5 ? body.getTokens(4) : body.getTokens(3);
        MapHelper.put(fileReplacementMap, fileReplacementMapTextParts.get(0), fileReplacementMapTextParts.get(1));
        if (isCased) {
            MapHelper.put(textReplacementMap, StringHelper.capitalizeFirstLetter(textReplacementMapTextParts.get(0)),
                    StringHelper.capitalizeFirstLetter(textReplacementMapTextParts.get(1)));
            MapHelper.put(textReplacementMap, StringHelper.decapitalizeFirstLetter(textReplacementMapTextParts.get(0)),
                    StringHelper.decapitalizeFirstLetter(textReplacementMapTextParts.get(1)));
        } else {
            MapHelper.put(textReplacementMap, textReplacementMapTextParts.get(0), textReplacementMapTextParts.get(1));
        }
        // String
        FileFolderCopier.newInstance().copy(paths, sourceFolderPath, destinationFolderPath, fileSearchQuery,
                fileReplacementMap, textReplacementMap);
        String pathsText = paths.toString();
        return CodeHelper.toCode(pathsText);
    }
}
