package com.lambda.lambda.app.util.java;

import java.util.List;

import com.lambda.lambda.app.util.file.FileTextReplacer;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.TimeHelper;
import com.lambda.lambda.common.helper.file.FileHelper;
import com.lambda.lambda.common.helper.file.FilePathHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.wrapper.StringWrapper;

/**
 * Utility class for making void methods
 */
public final class JavaVoidMethodsMaker {
    // Instance Fields
    private String domainText;
    private String text;
    private StringList lines;
    private StringList methodNames;
    private StringList methodLines;

    // New Instance Method
    public static JavaVoidMethodsMaker newInstance(String domainText) {
        return new JavaVoidMethodsMaker(domainText);
    }

    // Constructor Method
    private JavaVoidMethodsMaker(String domainText) {
        super();
        this.domainText = domainText;
    }

    // Main Instance Method
    public StringList make(String text) {
        this.reset(text);
        this.makeCompileMethodNames();
        this.makeCompileMethodLines();
        this.makeCompileMajorMethodLines();
        return StringHelper.copyStringList(this.methodLines);
    }

    // Major Methods
    private void makeCompileMethodNames() {
        ListHelper.forEach(this.lines, (String line) -> {
            String trimmedLine = StringTrimmerHelper.trimLeadingWhiteSpace(line);
            ConditionalHelper.ifThen(StringHelper.isNotEmpty(trimmedLine), () -> {
                int start = StringHelper.indexOf(line, '.') + 1;
                int upTo = StringHelper.indexOf(line, '(');
                ListHelper.add(this.methodNames, line.substring(start, upTo));
            });
        });
    }

    private void makeCompileMethodLines() {
        this.compileMethodLines();
    }

    private void makeCompileMajorMethodLines() {
        ConditionalHelper.ifThen(this.domainText != null, this::compileMajorMethodLines);
    }

    // Minor Methods
    private void compileMethodLines() {
        ListHelper.forEach(this.methodNames, (String methodName) -> {
            ListHelper.add(this.methodLines, "\tprivate void " + methodName + "() {");
            ListHelper.add(this.methodLines, "\t\t// TODO");
            ListHelper.add(this.methodLines, "\t}");
            ListHelper.add(this.methodLines, "");
        });
        this.removeLastMethodLineIfPopulated();
    }

    private void compileMajorMethodLines() {
        List<String> minorMethodLines = StringHelper.copyStringList(this.methodLines);
        ListHelper.clear(this.methodLines);
        ListHelper.addAll(this.methodLines, this.collectDomainMethodInvocationLines());
        ListHelper.add(this.methodLines, "\t// Major Methods");
        ListHelper.forEach(this.methodNames, (String methodName) -> {
            String majorMethodName = this.domainText + StringHelper.capitalizeFirstLetter(methodName);
            ListHelper.add(this.methodLines, "\tprivate void " + majorMethodName + "() {");
            ListHelper.add(this.methodLines, "\t\tthis." + methodName + "();");
            ListHelper.add(this.methodLines, "\t}");
            ListHelper.add(this.methodLines, "");
        });
        ListHelper.add(this.methodLines, "\t// Minor Methods");
        ListHelper.addAll(this.methodLines, minorMethodLines);
    }

    // Private Helper Methods
    private StringList collectDomainMethodInvocationLines() {
        StringList domainMethodInvocationLines = StringList.newInstance();
        ListHelper.forEach(this.methodNames, (String methodName) -> {
            String majorMethodName = this.domainText + StringHelper.capitalizeFirstLetter(methodName);
            ListHelper.add(this.methodLines, "this." + majorMethodName + "();");
        });
        ListHelper.add(this.methodLines, "");
        return domainMethodInvocationLines;
    }

    private void removeLastMethodLineIfPopulated() {
        ConditionalHelper.ifThen(ListHelper.isNotEmpty(this.methodNames),
                () -> ListHelper.removeLastItems(this.methodLines, 1));
    }

    // Initialization Methods
    private void reset(String text) {
        this.text = text;
        this.lines = StringHelper.split(text, "\n");
        this.methodNames = StringHelper.newStringList();
        this.methodLines = StringHelper.newStringList();
    }
}
