package christmas.util.inputvalidator;

import static christmas.contant.Error.INVALID_DATE_ERROR;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.inputvalidator.InputValidator;
import util.inputvalidator.NumberInputValidator;

class NumberInputValidatorTest {
    InputValidator inputValidator = new NumberInputValidator();

    @ParameterizedTest
    @ValueSource(strings = {"asd,qf,22a,-21", "a221"})
    @DisplayName("숫자 입력이 정수가 아닐 경우 예외를 발생한다..")
    void caseInvalidFormat(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> inputValidator.validate(input))
                .withMessage(INVALID_DATE_ERROR.get());
    }
}