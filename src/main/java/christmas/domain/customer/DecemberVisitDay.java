package christmas.domain.customer;

import static christmas.contant.Calender.D_DAY_EVENT_DAYS;
import static christmas.contant.Calender.EVENT_DAYS;
import static christmas.contant.Calender.STAR_DAYS;
import static christmas.contant.Calender.WEEKEND_DAYS;
import static christmas.contant.Calender.getChristmasDate;
import static christmas.contant.Error.NOT_VALIDATE_DAY_ERROR;

public class DecemberVisitDay implements VisitDay {

    private final int day;

    public DecemberVisitDay(int day) {
        validate(day);
        this.day = day;
    }

    @Override
    public void validate(int day) {
        if (!isDecemberRange(day)) {
            NOT_VALIDATE_DAY_ERROR.throwError();
        }
    }

    private boolean isDecemberRange(int day) {
        return EVENT_DAYS.get().contains(day);
    }

    @Override
    public boolean isEventDay() {
        return EVENT_DAYS.get().contains(day);
    }

    @Override
    public boolean isWeekendDay() {
        return WEEKEND_DAYS.get().contains(day);
    }

    @Override
    public boolean isStarDay() {
        return STAR_DAYS.get().contains(day);
    }

    @Override
    public boolean isBeforeDday() {
        return D_DAY_EVENT_DAYS.get().contains(day);
    }

    @Override
    public int calculateDaysUntilDday() {
        return getChristmasDate() - day;
    }
}
