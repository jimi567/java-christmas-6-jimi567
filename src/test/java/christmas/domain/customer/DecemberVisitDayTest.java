package christmas.domain.customer;

import static christmas.contant.Error.NOT_VALIDATE_DAY_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DecemberVisitDayTest {

    @ParameterizedTest
    @ValueSource(ints = {32, 0, -15, 2132141})
    @DisplayName("1~31외의 숫자로 생성하면 예외가 발생한다.")
    void createByWrongRangeValue(int date) {
        assertThatIllegalArgumentException().isThrownBy(() -> new DecemberVisitDay(date))
                .withMessage(NOT_VALIDATE_DAY_ERROR.get());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @DisplayName("해당 날짜가 주말인지 아닌지를 판단한다.주말의 경우")
    void testIsWeekendDayCaseWeekendDay(int date) {
        VisitDay visitDay = new DecemberVisitDay(date);

        assertThat(visitDay.isWeekendDay()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 12, 19, 21, 26, 31, 25})
    @DisplayName("해당 날짜가 주말인지 아닌지를 판단한다.평일의 경우")
    void testIsWeekendDayCaseWeekDay(int date) {
        VisitDay visitDay = new DecemberVisitDay(date);

        assertThat(visitDay.isWeekendDay()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 31, 25})
    @DisplayName("해당 날짜가 달력에 별이 존재하는 날인지 아닌지를 판단한다. 별이 있는 날의 경우")
    void testIsStarDayCaseStarDay(int date) {
        VisitDay visitDay = new DecemberVisitDay(date);

        assertThat(visitDay.isStarDay()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 15, 22, 12, 27, 29})
    @DisplayName("해당 날짜가 달력에 별이 존재하는 날인지 아닌지를 판단한다. 별이 없는 날의 경우")
    void testIsStarDayCaseNotStarDay(int date) {
        VisitDay visitDay = new DecemberVisitDay(date);

        assertThat(visitDay.isStarDay()).isFalse();
    }

    @Test
    @DisplayName("해당 날짜가 이벤트 진행 기간에 해당하는지 판단한다.")
    void testIsEventDay() {
        VisitDay visitDay = new DecemberVisitDay(25);

        assertThat(visitDay.isEventDay()).isTrue();
    }

    @Test
    @DisplayName("해당 날짜가 디데이이벤트 적용기간인지 판단한다")
    void testisBeforeDday() {
        VisitDay applicableDdayEvent = new DecemberVisitDay(25);
        VisitDay nonApplicableDdayEvent = new DecemberVisitDay(26);

        assertThat(applicableDdayEvent.isBeforeDday()).isTrue();
        assertThat(nonApplicableDdayEvent.isBeforeDday()).isFalse();
    }

    @Test
    @DisplayName("해당 날짜와 디데이 간의 차이를 계산한다")
    void testCalculateDaysUntilDday() {
        VisitDay visitDay = new DecemberVisitDay(1);

        int result = visitDay.calculateDaysUntilDday();
        int expected = 24;

        assertThat(result).isEqualTo(expected);
    }
}
