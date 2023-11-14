package util.inputvalidator;

import static christmas.contant.Error.INVALID_DATE_ERROR;

import java.util.regex.Pattern;

public class NumberInputValidator implements InputValidator {
    private static final Pattern PATTERN = Pattern.compile("^\\d+$");

    @Override
    public void validate(String input) {
        if (!isNumeric(input)) {
            INVALID_DATE_ERROR.throwError();
        }
    }

    private boolean isNumeric(String input) {
        return PATTERN.matcher(input).matches();
    }
}
