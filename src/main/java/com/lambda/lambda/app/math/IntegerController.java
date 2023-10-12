package com.lambda.lambda.app.math;

import java.util.function.BiConsumer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.util.LambdaArguments;
import com.lambda.lambda.common.helper.IterationHelper;
import com.lambda.lambda.common.helper.StringHelper;
import com.lambda.lambda.common.util.wrapper.IntegerWrapper;

@CrossOrigin
@RestController
@RequestMapping("/number/integer")
public final class IntegerController {
    @PostMapping("/add")
    public String add(@RequestBody LambdaArguments lambdaArguments) {
        return this.synthesizeArguments(lambdaArguments, 0, 0, IntegerWrapper::increment);
    }

    @PostMapping("/divide")
    public String divide(@RequestBody LambdaArguments lambdaArguments) {
        return this.synthesizeArguments(lambdaArguments, lambdaArguments.getIntegerArgument(0), 1,
                IntegerWrapper::divide);
    }

    @PostMapping("/multiply")
    public String multiply(@RequestBody LambdaArguments lambdaArguments) {
        return this.synthesizeArguments(lambdaArguments, 1, 0, IntegerWrapper::multiply);
    }

    @PostMapping("/subtract")
    public String subtract(@RequestBody LambdaArguments lambdaArguments) {
        return this.synthesizeArguments(lambdaArguments, lambdaArguments.getIntegerArgument(0), 1,
                IntegerWrapper::decrement);
    }

    private String synthesizeArguments(LambdaArguments lambdaArguments, int baseValue, int startIndex,
            BiConsumer<IntegerWrapper, Integer> iterationAction) {
        IntegerWrapper synthesized = IntegerWrapper.newInstance(baseValue);
        IterationHelper.forEach(startIndex, lambdaArguments.getArgumentsSize(),
                (Integer i) -> iterationAction.accept(synthesized, lambdaArguments.getIntegerArgument(i)));
        return StringHelper.toString(synthesized);
    }
}
