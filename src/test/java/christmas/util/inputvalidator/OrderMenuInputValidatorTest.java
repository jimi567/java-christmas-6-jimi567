package christmas.util.inputvalidator;

import static christmas.contant.Error.INVALID_ORDER_ERROR;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.inputvalidator.InputValidator;
import util.inputvalidator.OrderMenuInputValidator;

class OrderMenuInputValidatorTest {

    InputValidator inputValidator = new OrderMenuInputValidator();

    @ParameterizedTest
    @ValueSource(strings = {"!@#@!#-2,!@#@!#-1", "22222-22,11-2323", "3", "제로콜라-a"})
    @DisplayName("메뉴 형식이 주어진 형태가 아니면 예외를 발생한다.")
    void caseInvalidFormat(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> inputValidator.validate(input))
                .withMessage(INVALID_ORDER_ERROR.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-1,시저샐러드-1", "해산물파스타-2,해산물파스타-1"})
    @DisplayName("메뉴명에 중복이 있으면 예외를 발생한다.")
    void caseHasDuplication(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> inputValidator.validate(input))
                .withMessage(INVALID_ORDER_ERROR.get());
    }
}