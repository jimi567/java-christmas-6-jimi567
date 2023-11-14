package christmas.domain.customer;

public interface VisitDate {
    void validate(int day);

    boolean isEventDay();

    boolean isWeekendDay();

    boolean isStarDay();

    boolean isBeforeDday();

    int calculateDaysUntilDday();
}
