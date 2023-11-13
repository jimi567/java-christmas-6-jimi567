package christmas.domain.visitday;

import static christmas.contant.Error.NOT_VALIDATE_DAY_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecemberVisitDayTest {

    @Test
    @DisplayName("1~31외의 숫자로 생성하면 예외가 발생한다.")
    void createByWrongRangeValue1() {
        assertThatIllegalArgumentException().isThrownBy(() -> new DecemberVisitDay(32))
                .withMessage(NOT_VALIDATE_DAY_ERROR.get());
    }

    @Test
    @DisplayName("1~31외의 숫자로 생성하면 예외가 발생한다.")
    void createByWrongRangeValue2() {
        assertThatIllegalArgumentException().isThrownBy(() -> new DecemberVisitDay(0))
                .withMessage(NOT_VALIDATE_DAY_ERROR.get());
    }

    @Test
    @DisplayName("해당 날짜가 주말인지 아닌지를 판단한다.")
    void testIsWeekendDay() {
        VisitDay weekendDay = new DecemberVisitDay(15);
        VisitDay weekDay = new DecemberVisitDay(10);

        assertThat(weekendDay.isWeekendDay()).isTrue();
        assertThat(weekDay.isWeekendDay()).isFalse();
    }

    @Test
    @DisplayName("해당 날짜가 달력에 별이 존재하는 날인지 아닌지를 판단한다.")
    void testIsStarDay() {
        VisitDay starDay = new DecemberVisitDay(25);
        VisitDay nonStarDay = new DecemberVisitDay(20);

        assertThat(starDay.isStarDay()).isTrue();
        assertThat(nonStarDay.isStarDay()).isFalse();
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
