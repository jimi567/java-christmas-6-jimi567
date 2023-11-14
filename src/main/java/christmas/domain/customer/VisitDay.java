package christmas.domain.customer;

public interface VisitDay {
    void validate(int day);

    boolean isEventDay();

    boolean isWeekendDay();

    boolean isStarDay();

    boolean isBeforeDday();

    int calculateDaysUntilDday();
}