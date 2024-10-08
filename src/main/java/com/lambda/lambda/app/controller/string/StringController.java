package com.lambda.lambda.app.controller.string;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.app.utility.LambdaArguments;
import com.lambda.lambda.app.utility.string.StringSerialReplacer;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.IterationHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringReplacementHelper;
import com.lambda.lambda.common.util.string.StringList;

@CrossOrigin
@RestController
@RequestMapping("/string")
public final class StringController {
    @PostMapping("/capitalize")
    public String capitalize(@RequestBody LambdaArguments lambdaArguments) {
        return CodeHelper.toCode(lambdaArguments.getArgument(0).toUpperCase());
    }

    @PostMapping("/concat")
    public String concat(@RequestBody LambdaArguments lambdaArguments) {
        StringBuilder concatenated = StringHelper.newBuilder();
        IterationHelper.forEach(lambdaArguments.getArgumentsSize(),
                (Integer i) -> concatenated.append(lambdaArguments.getArgument(i)));
        return CodeHelper.toCode(concatenated.toString());
    }

    @PostMapping("/length")
    public String length(@RequestBody LambdaArguments lambdaArguments) {
        return CodeHelper.toCode(StringHelper.toString(lambdaArguments.getArgument(0).length()));
    }

    @PostMapping("/split")
    public String split(@RequestBody LambdaArguments lambdaArguments) {
        String bodyText = lambdaArguments.getBodyText();
        String regex = lambdaArguments.getArgument(0);
        List<String> parts = StringHelper.split(bodyText, regex);
        return CodeHelper.toCode(StringHelper.join(parts, "\n"));
    }

    @PostMapping("/replace")
    public String replace(@RequestBody LambdaArguments lambdaArguments) {
        String bodyText = lambdaArguments.getBodyText();
        String target = lambdaArguments.getArgument(0);
        String replacement = lambdaArguments.getArgument(1);
        String replaced = StringReplacementHelper.replace(bodyText, target, replacement);
        return CodeHelper.toCode(replaced);
    }

    @PostMapping("/replace/serial")
    public String replaceSerial(@RequestBody LambdaArguments lambdaArguments) {
        String bodyText = lambdaArguments.getBodyText();
        StringList arguments = lambdaArguments.getArgumentsClone();
        boolean numberOfReplacementsPerInstanceIsSpecified = arguments.get(0).equals("--n");
        int numberOfReplacementsPerInstance = ConditionalHelper.newTernaryOperation(
                numberOfReplacementsPerInstanceIsSpecified, () -> arguments.getInteger(1), () -> 1);
        int indexShift =
                ConditionalHelper.ifReturnElse(numberOfReplacementsPerInstanceIsSpecified, 2, 0);
        String target = arguments.get(indexShift);
        List<String> replacements = arguments.subList(1 + indexShift, arguments.size());
        StringSerialReplacer replacer = StringSerialReplacer.newInstance();
        String replaced =
                replacer.replace(target, numberOfReplacementsPerInstance, replacements, bodyText);
        return CodeHelper.toCode(replaced);
    }
}
