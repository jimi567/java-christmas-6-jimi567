package christmas.domain.customer;

import static christmas.contant.Calender.CHRISTMAS_DATE;
import static christmas.contant.Calender.D_DAY_EVENT_DAYS;
import static christmas.contant.Calender.EVENT_DAYS;
import static christmas.contant.Calender.STAR_DAYS;
import static christmas.contant.Calender.WEEKEND_DAYS;
import static christmas.contant.Error.INVALID_DATE_ERROR;

public class DecemberVisitDate implements VisitDate {

    private final int date;

    public DecemberVisitDate(int date) {
        validate(date);
        this.date = date;
    }

    @Override
    public void validate(int date) {
        if (!isDecemberRange(date)) {
            INVALID_DATE_ERROR.throwError();
        }
    }

    private boolean isDecemberRange(int day) {
        return EVENT_DAYS.get().contains(day);
    }

    @Override
    public boolean isEventDay() {
        return EVENT_DAYS.get().contains(date);
    }

    @Override
    public boolean isWeekendDay() {
        return WEEKEND_DAYS.get().contains(date);
    }

    @Override
    public boolean isStarDay() {
        return STAR_DAYS.get().contains(date);
    }

    @Override
    public boolean isBeforeDday() {
        return D_DAY_EVENT_DAYS.get().contains(date);
    }

    @Override
    public int calculateDaysUntilDday() {
        return CHRISTMAS_DATE - date;
    }
}
