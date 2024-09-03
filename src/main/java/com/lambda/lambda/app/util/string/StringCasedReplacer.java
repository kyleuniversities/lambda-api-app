package com.lambda.lambda.app.util.string;

import java.io.File;
import java.util.List;
import java.util.function.BiConsumer;
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

public final class StringCasedReplacer {
    // New Instance Method
    public static StringCasedReplacer newInstance() {
        return new StringCasedReplacer();
    }

    // Constructor Method
    private StringCasedReplacer() {
        super();
    }

    // Main Instance Method
    public String replaceAll(String text, String target, String replacement) {
        StringMap replacementMap = StringHelper.newStringMap();
        String capitalTarget = StringHelper.capitalizeFirstLetter(text);
        String lowercaseTarget = StringHelper.decapitalizeFirstLetter(text);
        String capitalReplacement = StringHelper.capitalizeFirstLetter(replacement);
        String lowercaseReplacement = StringHelper.decapitalizeFirstLetter(replacement);
        MapHelper.put(replacementMap, capitalTarget, capitalReplacement);
        MapHelper.put(replacementMap, lowercaseTarget, lowercaseReplacement);
        return StringReplacementHelper.replace(text, replacementMap);
    }
}