package util.inputvalidator;

import static christmas.contant.Error.INVALID_ORDER_ERROR;
import static christmas.contant.ViewMessage.COMMA;
import static christmas.contant.ViewMessage.DASH;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderMenuInputValidator implements InputValidator {
    private final Pattern pattern = Pattern.compile("^([가-힣a-zA-Z]+)-(\\d)(,([가-힣a-zA-Z]+)-(\\d))*$");

    @Override
    public void validate(String input) {
        if (!isValidFormat(input) || hasDuplication(input)) {
            INVALID_ORDER_ERROR.throwError();
        }

    }

    private boolean isValidFormat(String input) {
        return pattern.matcher(input).matches();
    }

    private boolean hasDuplication(String input) {
        Set<String> uniqueMenuNames = Arrays.stream(input.split(COMMA.get()))
                .map(order -> order.split(DASH.get())[0])
                .collect(Collectors.toSet());

        return uniqueMenuNames.size() != input.split(COMMA.get()).length;
    }
}
