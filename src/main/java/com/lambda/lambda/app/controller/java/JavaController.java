package com.lambda.lambda.app.controller.java;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.app.util.LambdaArguments;
import com.lambda.lambda.app.util.java.JavaFolderCopier;
import com.lambda.lambda.app.util.java.JavaFunctionClassMaker;
import com.lambda.lambda.app.util.java.JavaGetterMethodsMaker;
import com.lambda.lambda.app.util.java.JavaListImplementer;
import com.lambda.lambda.app.util.java.JavaMapImplementer;
import com.lambda.lambda.app.util.java.JavaSetImplementer;
import com.lambda.lambda.app.util.java.JavaSetterMethodsMaker;
import com.lambda.lambda.app.util.java.JavaStructureClassMaker;
import com.lambda.lambda.app.util.java.JavaVoidMethodsMaker;
import com.lambda.lambda.common.util.string.StringList;

@CrossOrigin
@RestController
@RequestMapping("/java")
public final class JavaController {
    @PostMapping("/structure")
    public String structure(@RequestBody LambdaArguments lambdaArguments) {
        StringList classLines = JavaStructureClassMaker.newInstance()
                .make(lambdaArguments.getBodyText(), lambdaArguments.argumentsContain("-setters"));
        String classLinesText = classLines.toString();
        return CodeHelper.toCode(classLinesText);
    }

    @PostMapping("/function")
    public String function(@RequestBody LambdaArguments lambdaArguments) {
        StringList classLines = JavaFunctionClassMaker.newInstance().make(lambdaArguments.getBodyText());
        String classLinesText = classLines.toString();
        return CodeHelper.toCode(classLinesText);
    }

    @PostMapping("/folder/copy")
    public String copyFolder(@RequestBody LambdaArguments lambdaArguments) {
        StringList lines = lambdaArguments.getNonEmptyBodyLines();
        String sourceFolderPath = lines.get(0);
        String destinationFolderPath = lines.get(1);
        String backupRepositoryFolderPath = lines.get(2);
        JavaFolderCopier.newInstance().copy(sourceFolderPath, destinationFolderPath,
                backupRepositoryFolderPath);
        return CodeHelper.toCode("The folder has been copied");
    }

    @PostMapping("/void")
    public String voidMethods(@RequestBody LambdaArguments lambdaArguments) {
        String domainText = lambdaArguments.getApparentArgument(0);
        StringList methodLines = JavaVoidMethodsMaker.newInstance(domainText).make(lambdaArguments.getBodyText());
        String methodLinesText = methodLines.toString();
        return CodeHelper.toCode(methodLinesText);
    }

    @PostMapping({ "/get", "/getter", "/getters" })
    public String getterMethods(@RequestBody LambdaArguments lambdaArguments) {
        StringList methodLines = JavaGetterMethodsMaker.newInstance().make(lambdaArguments.getBodyText());
        String methodLinesText = methodLines.toString();
        return CodeHelper.toCode(methodLinesText);
    }

    @PostMapping({ "/set", "/setter", "/setters" })
    public String setterMethods(@RequestBody LambdaArguments lambdaArguments) {
        StringList methodLines = JavaSetterMethodsMaker.newInstance().make(lambdaArguments.getBodyText());
        String methodLinesText = methodLines.toString();
        return CodeHelper.toCode(methodLinesText);
    }

    @PostMapping("/implement/list")
    public String implementList(@RequestBody LambdaArguments lambdaArguments) {
        StringList classLines = JavaListImplementer.newInstance()
                .implement(lambdaArguments.getArgument(0), lambdaArguments.getApparentArgument(1));
        String classLinesText = classLines.toString();
        return CodeHelper.toCode(classLinesText);
    }

    @PostMapping("/implement/set")
    public String implementSet(@RequestBody LambdaArguments lambdaArguments) {
        StringList classLines = JavaSetImplementer.newInstance()
                .implement(lambdaArguments.getArgument(0), lambdaArguments.getApparentArgument(1));
        String classLinesText = classLines.toString();
        return CodeHelper.toCode(classLinesText);
    }

    @PostMapping("/implement/map")
    public String implementMap(@RequestBody LambdaArguments lambdaArguments) {
        StringList classLines = JavaMapImplementer.newInstance()
                .implement(lambdaArguments.getArgument(0), lambdaArguments.getArgument(1),
                        lambdaArguments.getApparentArgument(2));
        String classLinesText = classLines.toString();
        return CodeHelper.toCode(classLinesText);
    }
}
